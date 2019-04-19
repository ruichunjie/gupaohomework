package com.rcj.imitation.spring.demo.service;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/18 19:45
 * @Description:
 */
public interface IModifyService {

    public String add(String name, String addr) throws Exception;

    public String edit(Integer id, String name);

    public String remove(Integer id);
}
