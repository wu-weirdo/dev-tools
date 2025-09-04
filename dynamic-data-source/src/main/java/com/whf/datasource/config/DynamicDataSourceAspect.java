package com.whf.datasource.config;

import com.whf.datasource.utils.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
public class DynamicDataSourceAspect {

    @Resource
    private DynamicDataSource dynamicDataSource;

    @Pointcut("@annotation(com.whf.datasource.config.DataSource)")
    public void dynamicDataSource() {
    }

    @Around("dynamicDataSource()")
    public Object datasourceAround(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DataSource ds = method.getAnnotation(DataSource.class);
        if (Objects.nonNull(ds)) {
            //判断数据源是否存在
            if (!dynamicDataSource.existsDataSource(ds.value().name())) {
                throw new RuntimeException("数据源[" + ds.value().name() + "]不存在");
            }
            //设置数据源
            DataSourceContextHolder.setDataSource(ds.value().name());
        }
        try {
            return point.proceed();
        } finally {
            //清除数据源
            DataSourceContextHolder.removeDataSource();
        }
    }
}