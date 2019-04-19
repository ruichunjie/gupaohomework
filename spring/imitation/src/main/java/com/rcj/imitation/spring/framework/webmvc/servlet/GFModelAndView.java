package com.rcj.imitation.spring.framework.webmvc.servlet;

import java.util.Map;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/17 19:22
 * @Description:
 */
public class GFModelAndView {

    private String viewName;
    private Map<String,?> model;

    public GFModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public GFModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }
}
