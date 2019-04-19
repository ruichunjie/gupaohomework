package com.rcj.imitation.spring.framework.aop;

import com.rcj.imitation.spring.framework.aop.intercept.GFMethodInvocation;
import com.rcj.imitation.spring.framework.aop.support.GFAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/18 22:54
 * @Description:
 */
public class GFJdkDynamicAopProxy implements GFAopProxy, InvocationHandler {

    private GFAdvisedSupport config;
    public GFJdkDynamicAopProxy(GFAdvisedSupport config){
        this.config = config;
    }

    @Override
    public Object getProxy() {
        return getProxy(this.config.getTargetClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader,this.config.getTargetClass().getInterfaces(),this);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> interceptorsAndDynamicMethodMatchers =
                config.getInterceptorsAndDynamicInterceptionAdvice(method,this.config.getTargetClass());
        GFMethodInvocation invocation = new
                GFMethodInvocation(proxy,this.config.getTarget(),method,args,this.config.getTargetClass(),
                interceptorsAndDynamicMethodMatchers);
        return invocation.proceed();
    }
}
