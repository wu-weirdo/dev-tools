package com.whf.lock.annotation;

import java.lang.annotation.*;

/**
 * 构建锁的业务数据
 *
 * @author whf
 * @date 2024/01/12
 */
@Target({ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LockField {

    String[] fieldNames() default {};

}