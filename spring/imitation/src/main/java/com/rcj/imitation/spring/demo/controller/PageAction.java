package com.rcj.imitation.spring.demo.controller;

import com.rcj.imitation.spring.demo.service.IQueryService;
import com.rcj.imitation.spring.framework.annotation.GFAutowired;
import com.rcj.imitation.spring.framework.annotation.GFController;
import com.rcj.imitation.spring.framework.annotation.GFRequestMapping;
import com.rcj.imitation.spring.framework.annotation.GFRequestParam;
import com.rcj.imitation.spring.framework.webmvc.servlet.GFModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/18 19:58
 * @Description:
 */
@GFController
@GFRequestMapping("/")
public class PageAction {
    @GFAutowired
    IQueryService queryService;

    @GFRequestMapping("/first.html")
    public GFModelAndView query(@GFRequestParam("abc") String abc){
        String result = queryService.query(abc);
        Map<String,Object> model = new HashMap<>();
        model.put("zimou", abc);
        model.put("data", result);
        model.put("token", "123456");
        return new GFModelAndView("first.html",model);
    }
}
