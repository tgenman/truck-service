package com.mpoznyak.logging.annotation;

import com.mpoznyak.logging.level.LoggingLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Max Poznyak
 * on 11/13/18  at 15:50
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {

    boolean logBefore() default true;

    boolean logResponse() default true;

    boolean logExceptions() default true;

    boolean logOnlyExceptions() default false;

    boolean logArgumentsAndResults() default true;

    LoggingLevel logLevel() default LoggingLevel.INFO;

}
