package com.gupao.singleton.threadlocal;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/10 16:28
 * @Description: 伪线程安全 ThreadLocalMap
 *          使用ThreadLocal 来实现多数据源动态切换
 */
public class ThreadLocalSingleton {

    private ThreadLocalSingleton(){}

    private static final ThreadLocal<ThreadLocalSingleton> threadLocalInstance =
            new ThreadLocal<ThreadLocalSingleton>(){
                @Override
                protected ThreadLocalSingleton initialValue(){
                    return  new ThreadLocalSingleton();
                }
            };

    public static ThreadLocalSingleton getInstance(){
        return threadLocalInstance.get();
    }
}
