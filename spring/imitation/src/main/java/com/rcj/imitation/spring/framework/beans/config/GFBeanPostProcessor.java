package com.rcj.imitation.spring.framework.beans.config;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/17 11:52
 * @Description:
 */
public class GFBeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }
}
