package com.gupao.observer;

import com.google.common.eventbus.Subscribe;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/18 19:18
 * @Description:
 */
public class Teacher {

    private String name;

    public Teacher(String name){
        this.name = name;
    }

    @Subscribe
    public void subscribe(Question question){
        System.out.printf("提出人: %s\n问题: %s\n回复人:%s",question.getName(),question.getContent(),name);
    }
}
