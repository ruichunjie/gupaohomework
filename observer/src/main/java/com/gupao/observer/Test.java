package com.gupao.observer;

import com.google.common.eventbus.EventBus;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/18 19:20
 * @Description:
 */
public class Test {
    public static void main(String[] args) {

        EventBus eventBus = new EventBus();
        eventBus.register(new Teacher("Tom"));
        eventBus.post(new Question("小A","观察者模式怎么用"));
    }
}
