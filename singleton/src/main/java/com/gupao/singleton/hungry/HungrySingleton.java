package com.gupao.singleton.hungry;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/10 14:05
 * @Description: 饿汉式单例
 * 在单例类首次加载创建单例
 *  浪费内存空间
 */
public class HungrySingleton {

    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }
}
