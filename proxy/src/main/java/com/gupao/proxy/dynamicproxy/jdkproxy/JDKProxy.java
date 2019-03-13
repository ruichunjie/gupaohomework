package com.gupao.proxy.dynamicproxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/13 15:31
 * @Description:
 */
public class JDKProxy implements InvocationHandler {

    private Object target;
    public Object getInstance(Object obj){
        this.target = obj;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(this.target,args);
        after();
        return obj;
    }

    public void before(){
        System.out.println("打招呼前");
    }

    public void after(){
        System.out.println("打招呼后");
    }
}
