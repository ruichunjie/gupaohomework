package com.gupao.singleton.hungry;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/10 14:07
 * @Description:
 */
public class HungryStaticHungrySingleton {

    private static final HungryStaticHungrySingleton hungryStaticHungrySingleton;

    static {
        hungryStaticHungrySingleton = new HungryStaticHungrySingleton();
    }

    private HungryStaticHungrySingleton(){}

    public static HungryStaticHungrySingleton getInstance(){
        return hungryStaticHungrySingleton;
    }


}
