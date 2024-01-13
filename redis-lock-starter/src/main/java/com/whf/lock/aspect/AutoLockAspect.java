package com.whf.lock.aspect;

import com.whf.lock.annotation.AutoLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.lang.reflect.Method;
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
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        AutoLock autoLock = method.getAnnotation(AutoLock.class);

        String lockKey = REDIS_LOCK_PREFIX + SEPARATOR;
        RLock lock = redissonClient.getLock(lockKey);
        boolean lockFlag = false;
        //TODO 根据参数值生成锁的key
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
            } catch (Exception e){
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
                log.info("释放锁完成，key：{}",lockKey);
            }
        }
    }

}
