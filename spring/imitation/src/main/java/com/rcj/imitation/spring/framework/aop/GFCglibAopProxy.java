package com.rcj.imitation.spring.framework.aop;

import com.rcj.imitation.spring.framework.aop.support.GFAdvisedSupport;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/18 22:52
 * @Description:
 */
public class GFCglibAopProxy implements GFAopProxy {

    private GFAdvisedSupport config;

    public GFCglibAopProxy(GFAdvisedSupport config){
        this.config = config;
    }

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }
}
