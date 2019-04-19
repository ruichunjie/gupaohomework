package com.rcj.imitation.spring.framework.aop.aspect;

import com.rcj.imitation.spring.framework.aop.GFAdvice;
import com.rcj.imitation.spring.framework.aop.intercept.GFMethodInterceptor;
import com.rcj.imitation.spring.framework.aop.intercept.GFMethodInvocation;

import java.lang.reflect.Method;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/19 05:47
 * @Description:
 */
public class GFAfterThrowingAdvice extends GFAbstractAspectJAdvice implements GFAdvice, GFMethodInterceptor {

    private String throwingName;
    private GFMethodInvocation mi;
    public GFAfterThrowingAdvice(Method aspectMethod, Object target) {
        super(aspectMethod, target);
    }
    public void setThrowingName(String name) {
        this.throwingName = name;
    }

    @Override
    public Object invoke(GFMethodInvocation mi) throws Throwable {
        try{
            return mi.proceed();
        }catch (Throwable ex) {
            invokeAdviceMethod(mi,null,ex.getCause());
            throw ex;
        }
    }
}
