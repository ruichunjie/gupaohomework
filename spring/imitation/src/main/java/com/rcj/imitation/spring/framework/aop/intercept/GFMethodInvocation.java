package com.rcj.imitation.spring.framework.aop.intercept;

import com.rcj.imitation.spring.framework.aop.aspect.GFJoinPoint;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/19 05:25
 * @Description:
 */
public class GFMethodInvocation implements GFJoinPoint {

    private Object proxy;
    private Method method;
    private Object target;
    private Class<?> targetClass;
    private Object[] arguments;
    private List<Object> interceptorsAndDynamicMethodMatchers;

    private int currentInterceptorIndex = -1;

    public GFMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, Class<?> targetClass, List<Object>
            interceptorsAndDynamicMethodMatchers) { this.proxy = proxy;
        this.target = target;
        this.targetClass = targetClass;
        this.method = method;
        this.arguments = arguments;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }

    public Object proceed() throws Throwable {
        if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
            return this.method.invoke(this.target,this.arguments); }
        Object interceptorOrInterceptionAdvice = this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
        if (interceptorOrInterceptionAdvice instanceof GFMethodInterceptor) {
            GFMethodInterceptor mi = (GFMethodInterceptor) interceptorOrInterceptionAdvice;
            return mi.invoke(this);
        } else {
            return proceed();
        }
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Object[] getArguments() {
        return this.arguments;
    }

    @Override
    public Object getThis() {
        return this.target;
    }
}
