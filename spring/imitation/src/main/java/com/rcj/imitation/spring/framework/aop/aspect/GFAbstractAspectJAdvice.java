package com.rcj.imitation.spring.framework.aop.aspect;

import com.rcj.imitation.spring.framework.aop.GFAdvice;

import java.lang.reflect.Method;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/19 05:34
 * @Description:
 */
public class GFAbstractAspectJAdvice implements GFAdvice {

    private Method aspectMethod;
    private Object aspectTarget;

    public GFAbstractAspectJAdvice(Method aspectMethod, Object aspectTarget) {
        this.aspectMethod = aspectMethod;
        this.aspectTarget = aspectTarget;

    }
    protected Object invokeAdviceMethod(GFJoinPoint joinPoint,Object returnValue,Throwable ex) throws Throwable {
        Class<?> [] paramsTypes = this.aspectMethod.getParameterTypes();
        if(null == paramsTypes || paramsTypes.length == 0) {
            return this.aspectMethod.invoke(aspectTarget);
        }else {
            Object[] args = new Object[paramsTypes.length];
            for (int i = 0; i < paramsTypes.length; i++) {
                if(paramsTypes[i] == GFJoinPoint.class){
                    args[i] = joinPoint;
                }else if(paramsTypes[i] == Throwable.class){
                    args[i] = ex;
                }else if(paramsTypes[i] == Object.class){
                    args[i] = returnValue;
                }
            }
            return this.aspectMethod.invoke(aspectTarget,args);
        }
    }



}
