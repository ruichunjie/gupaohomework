package com.rcj.imitation.spring.framework.beans.support;

import com.rcj.imitation.spring.framework.beans.config.GFBeanDefinition;
import com.rcj.imitation.spring.framework.context.support.GFAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/16 15:11
 * @Description:
 */
public class GFDefaultListableBeanFactory extends GFAbstractApplicationContext {

    protected final Map<String, GFBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, GFBeanDefinition>();
}
