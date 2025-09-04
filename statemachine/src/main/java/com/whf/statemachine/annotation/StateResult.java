package com.whf.statemachine.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface StateResult {
    /**
     * 执行的业务key
     *
     * @return String
     */
    String key();
}