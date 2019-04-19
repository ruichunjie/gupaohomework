package com.rcj.imitation.spring.demo.service.impl;

import com.rcj.imitation.spring.demo.service.IModifyService;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/18 19:46
 * @Description:
 */
public class ModifyService implements IModifyService {
    @Override
    public String add(String name, String addr)throws Exception  {
        return "modifyService add,name=" + name + ",addr=" + addr;
    }

    @Override
    public String edit(Integer id, String name) {
        return "modifyService edit,id=" + id + ",name=" + name;
    }

    @Override
    public String remove(Integer id) {
        return "modifyService id=" + id;
    }
}
