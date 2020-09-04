package com.chinadass.api.config.annotation;

import java.lang.annotation.*;

/**
 * 此注解加入,记录日志
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LogPrint {
     String description() default "";

}
