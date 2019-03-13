package com.gupao.proxy.dynamicproxy.myproxy;

import java.lang.reflect.Method;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/13 17:59
 * @Description:
 */
public class MyProxym implements MyInvocationHandler {

    private Object target;
    public Object getInstance(Object person) throws Exception{
        this.target = person;
        Class<?> clazz = target.getClass();
        return MyProxy.newProxyInstance(new MyClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
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
