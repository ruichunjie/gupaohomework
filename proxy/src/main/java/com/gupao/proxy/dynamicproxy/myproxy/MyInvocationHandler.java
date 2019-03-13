package com.gupao.proxy.dynamicproxy.myproxy;

import java.lang.reflect.Method;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/13 16:56
 * @Description:
 */
public interface MyInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
