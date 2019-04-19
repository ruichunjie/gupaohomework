package com.rcj.imitation.spring.framework.aop;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/18 22:51
 * @Description:
 */
public interface GFAopProxy {
    Object getProxy();
    Object getProxy(ClassLoader classLoader);
}
