package com.rcj.imitation.spring.demo.controller;

import com.rcj.imitation.spring.demo.service.IModifyService;
import com.rcj.imitation.spring.demo.service.IQueryService;
import com.rcj.imitation.spring.framework.annotation.GFAutowired;
import com.rcj.imitation.spring.framework.annotation.GFController;
import com.rcj.imitation.spring.framework.annotation.GFRequestMapping;
import com.rcj.imitation.spring.framework.annotation.GFRequestParam;
import com.rcj.imitation.spring.framework.webmvc.servlet.GFModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/18 19:48
 * @Description:
 */
@GFController
@GFRequestMapping("/my")
public class MyController {

    @GFAutowired
    IQueryService queryService;
    @GFAutowired
    IModifyService modifyService;

    @GFRequestMapping("/query.json")
    public GFModelAndView query(HttpServletRequest request, HttpServletResponse response,
                                @GFRequestParam("name") String name){
        String result = queryService.query(name);
        return out(response,result);
    }

    @GFRequestMapping("/add*.json")
    public GFModelAndView add(HttpServletRequest request,HttpServletResponse response,
                              @GFRequestParam("name") String name,@GFRequestParam("addr") String addr){
        String result = modifyService.add(name,addr);
        return out(response,result);
    }

    @GFRequestMapping("/remove.json")
    public GFModelAndView remove(HttpServletRequest request,HttpServletResponse response,
                                 @GFRequestParam("id") Integer id) {
        String result = modifyService.remove(id);
        return out(response, result);
    }

    @GFRequestMapping("/edit.json")
    public GFModelAndView edit(HttpServletRequest request,HttpServletResponse response,
                               @GFRequestParam("id") Integer id,
                               @GFRequestParam("name") String name){
        String result = modifyService.edit(id,name);
        return out(response,result);
    }

    private GFModelAndView out(HttpServletResponse resp,String str){
        try {
            resp.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
