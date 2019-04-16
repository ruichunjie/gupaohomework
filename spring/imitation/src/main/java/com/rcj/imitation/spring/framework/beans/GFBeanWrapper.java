package com.rcj.imitation.spring.framework.beans;

import lombok.Data;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/16 14:53
 * @Description:
 */
@Data
public class GFBeanWrapper {
    private Object wrappedInstance;
    private Class<?> wrappedClass;
    public Class<?> getWrappedClass(){
        return this.wrappedInstance.getClass();
    }
}
