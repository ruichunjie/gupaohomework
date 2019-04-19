package com.rcj.imitation.spring.framework.aop.aspect;

import com.rcj.imitation.spring.framework.aop.GFAdvice;
import com.rcj.imitation.spring.framework.aop.intercept.GFMethodInterceptor;
import com.rcj.imitation.spring.framework.aop.intercept.GFMethodInvocation;

import java.lang.reflect.Method;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/19 05:40
 * @Description:
 */
public class GFAfterReturningAdvice  extends GFAbstractAspectJAdvice implements GFAdvice, GFMethodInterceptor {

    private GFJoinPoint joinPoint;
    public GFAfterReturningAdvice(Method aspectMethod, Object target) {
        super(aspectMethod, target); }

    @Override
    public Object invoke(GFMethodInvocation mi) throws Throwable {
        Object retVal = mi.proceed();
        this.joinPoint = mi;
        this.afterReturning(retVal, mi.getMethod(), mi.getArguments(), mi.getThis());
        return retVal;
    }


    public void afterReturning(Object returnValue, Method method, Object[] args,Object target) throws Throwable{
        invokeAdviceMethod(joinPoint,returnValue,null);
    }
}
