package com.whf.lock.aspect;

import com.whf.lock.annotation.AutoLock;
import com.whf.lock.annotation.LockField;
import com.whf.lock.utils.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 自动锁切面
 * 处理加锁解锁逻辑
 *
 * @author wuhaifeng
 * @description
 * @date 2024/1/12 16:37
 */

@Slf4j
@Component
public class AutoLockAspect {

    @Resource
    private RedissonClient redissonClient;

    private static final String REDIS_LOCK_PREFIX = "autoLock";

    private static final String SEPARATOR = ":";

    @Pointcut("@annotation(com.whf.lock.annotation.AutoLock)")
    public void autoLock() {
    }

    @Around("autoLock()")
    public Object doLock(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //获取参数
        Object[] args = joinPoint.getArgs();
        // 获取锁注解
        AutoLock autoLock = method.getAnnotation(AutoLock.class);
        // 获取锁前缀
        String prefix = autoLock.prefix();
        // 获取不同的业务参数值构造key 如用户id等
        String lockValue;
        //1. 通过el表达式获取key值
        String field = autoLock.field();
        if (StringUtils.hasText(field)) {
            lockValue = getValueFromEl(method, args, field);
        } else {
            //2.通过LockField注解获取key值
            // 获取方法参数
            Parameter[] parameters = method.getParameters();
            StringBuilder lockKey = new StringBuilder();
            //遍历获取参数值，拼凑key值
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                LockField lockField = parameter.getAnnotation(LockField.class);
                if (lockField == null) {
                    continue;
                }
                String[] fieldNames = lockField.fieldNames();
                if (fieldNames != null && fieldNames.length != 0) {
                    List<Object> filedValues = ReflectionUtil.getFiledValues(parameter.getType(), args[i], fieldNames);
                    for (Object value : filedValues) {
                        lockKey.append(SEPARATOR).append(value);
                    }
                }
            }
            lockValue = lockKey.toString();
        }
        //构建锁名称
        String lockKey = REDIS_LOCK_PREFIX + SEPARATOR + prefix + SEPARATOR + lockValue;
        RLock lock = redissonClient.getLock(lockKey);
        boolean lockFlag = false;
        try {
            long lockTime = autoLock.lockTime();
            long waitTime = autoLock.waitTime();
            TimeUnit timeUnit = autoLock.timeUnit();
            boolean tryLock = autoLock.tryLock();
            //加锁
            try {
                if (tryLock) {
                    lockFlag = lock.tryLock(lockTime, waitTime, timeUnit);
                } else {
                    lock.lock();
                    lockFlag = true;
                }
            } catch (Exception e) {
                log.error("加锁失败！，错误信息", e);
                throw new RuntimeException("加锁失败！");
            }
            if (!lockFlag) {
                throw new RuntimeException("加锁失败！");
            }
            return joinPoint.proceed();
        } finally {
            if (lockFlag) {
                lock.unlock();
                log.info("释放锁完成，key：{}", lockKey);
            }
        }
    }

    /**
     * 根据el表达式获取参数值
     *
     * @param method
     * @param args
     * @param el
     * @return
     */
    private String getValueFromEl(Method method, Object[] args, String el) {
        //获取方法参数名列表
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);
        if (parameterNames == null) {
            return null;
        }
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(el);
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < args.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        return expression.getValue(context, String.class);
    }

}
