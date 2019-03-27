package com.gupao.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/25 10:40
 * @Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyService {
    String value() default "";
}
