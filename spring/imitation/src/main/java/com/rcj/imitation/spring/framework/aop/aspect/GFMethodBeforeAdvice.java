package com.rcj.imitation.spring.framework.aop.aspect;

import com.rcj.imitation.spring.framework.aop.GFAdvice;
import com.rcj.imitation.spring.framework.aop.intercept.GFMethodInterceptor;
import com.rcj.imitation.spring.framework.aop.intercept.GFMethodInvocation;

import java.lang.reflect.Method;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/19 05:38
 * @Description:
 */
public class GFMethodBeforeAdvice extends GFAbstractAspectJAdvice implements GFAdvice, GFMethodInterceptor {
    private GFJoinPoint joinPoint;
    public GFMethodBeforeAdvice(Method aspectMethod, Object target) {
        super(aspectMethod, target);
    }

    public void before(Method method, Object[] args, Object target) throws Throwable {
        invokeAdviceMethod(this.joinPoint,null,null);
    }

    @Override
    public Object invoke(GFMethodInvocation mi) throws Throwable {
        this.joinPoint = mi;
        this.before(mi.getMethod(), mi.getArguments(), mi.getThis());
        return mi.proceed();
    }
}
