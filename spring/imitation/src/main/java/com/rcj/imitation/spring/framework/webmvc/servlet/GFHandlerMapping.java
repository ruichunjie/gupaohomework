package com.rcj.imitation.spring.framework.webmvc.servlet;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/17 18:05
 * @Description:
 */
public class GFHandlerMapping {
    /**保存方法对应的实例*/
    private Object controller;
    /**映射的方法*/
    private Method method;
    /**URL的正则匹配*/
    private Pattern pattern;

    public GFHandlerMapping(Pattern pattern, Object controller, Method method){
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}
