package com.weiliai.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @Author: Doug Li
 * @Date 2020/3/15
 * @Describe: TODO
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPRequestMapping {

    String value() default "";
}
