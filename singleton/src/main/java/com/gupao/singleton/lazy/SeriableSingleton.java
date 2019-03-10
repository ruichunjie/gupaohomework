package com.gupao.singleton.lazy;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/10 15:40
 * @Description:
 */
public class SeriableSingleton {

    private final static SeriableSingleton seriableSingleton = new SeriableSingleton();

    private SeriableSingleton(){}

    public static SeriableSingleton getInstance(){return seriableSingleton;}

    // 重写readResolve 方法 只不过是覆盖了反序列化的对象
    //还是创建了两次 发生在JVM层面 相对较为安全 之前的对象会被回收
    private Object readResolve(){return seriableSingleton;}
}
