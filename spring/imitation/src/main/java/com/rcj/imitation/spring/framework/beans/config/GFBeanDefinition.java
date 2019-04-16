package com.rcj.imitation.spring.framework.beans.config;

import lombok.Data;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/16 14:50
 * @Description:
 */
@Data
public class GFBeanDefinition {

    private String beanClassName;
    private boolean lazyInit = false;
    private String factoryBeanName;

}
