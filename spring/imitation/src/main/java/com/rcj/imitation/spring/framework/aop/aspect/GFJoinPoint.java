package com.rcj.imitation.spring.framework.aop.aspect;

import java.lang.reflect.Method;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/19 05:23
 * @Description:
 */
public interface GFJoinPoint {

    Method getMethod();

    Object[] getArguments();

    Object getThis();
}
