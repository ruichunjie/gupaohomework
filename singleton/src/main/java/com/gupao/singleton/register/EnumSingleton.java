package com.gupao.singleton.register;

import org.omg.CORBA.Object;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/10 16:01
 * @Description:
 */
public enum EnumSingleton {

    INSTANCE;
    private Object data;
    public Object getData(){
        return data;
    }

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
