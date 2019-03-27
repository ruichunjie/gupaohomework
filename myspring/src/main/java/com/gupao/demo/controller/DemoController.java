package com.gupao.demo.controller;

import com.gupao.demo.service.IDemoService;
import com.gupao.mvcframework.annotation.MyAutowired;
import com.gupao.mvcframework.annotation.MyController;
import com.gupao.mvcframework.annotation.MyRequestMapping;
import com.gupao.mvcframework.annotation.MyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/25 09:53
 * @Description:
 */
@MyController
@MyRequestMapping("/demo")
public class DemoController {

    @MyAutowired private IDemoService demoService;

    @MyRequestMapping("/query")
    public void query(HttpServletRequest req, HttpServletResponse resp,
                      @MyRequestParam("name") String name){

        String result = "My name is "+ name;
        try{
            resp.getWriter().write(result);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @MyRequestMapping("/add")
    public void add(HttpServletRequest req, HttpServletResponse resp,
                      @MyRequestParam("a")Integer a, @MyRequestParam("b") Integer b){
        try{
            resp.getWriter().write("a+b="+(a+b));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
