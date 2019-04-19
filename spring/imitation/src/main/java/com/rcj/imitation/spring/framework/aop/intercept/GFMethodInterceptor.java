package com.rcj.imitation.spring.framework.aop.intercept;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/19 05:28
 * @Description:
 */
public interface GFMethodInterceptor {
    Object invoke(GFMethodInvocation mi) throws Throwable;
}
