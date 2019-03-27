package com.gupao.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/25 10:44
 * @Description:
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestParam {

    String value() default "";
}
