package com.gupao.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/25 10:35
 * @Description:
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutowired {
    String value() default "";
}
