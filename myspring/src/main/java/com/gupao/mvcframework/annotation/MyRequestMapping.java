package com.gupao.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/25 10:05
 * @Description:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMapping {

    String value() default "";
}
