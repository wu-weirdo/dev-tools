package com.whf.convert.tree.interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * 树id
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
public @interface TreeId {
}