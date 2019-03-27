package com.gupao.demo.service.impl;

import com.gupao.demo.service.IDemoService;
import com.gupao.mvcframework.annotation.MyService;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/25 10:38
 * @Description:
 */
@MyService
public class DemoService implements IDemoService {

    public String get(String name) {
        return "My name is "+name;
    }
}
