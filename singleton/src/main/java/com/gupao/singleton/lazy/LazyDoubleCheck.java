package com.gupao.singleton.lazy;

import java.util.Objects;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/10 14:33
 * @Description:
 */
public class LazyDoubleCheck {

    private static LazyDoubleCheck lazyDoubleCheck;

    private LazyDoubleCheck(){}

    public static LazyDoubleCheck getInstance(){
        if(Objects.isNull(lazyDoubleCheck)){
            synchronized (LazyDoubleCheck.class){
                if(Objects.isNull(lazyDoubleCheck)){
                    //CPU执行转换成JVM
                    //2 3 可能会乱序 指令重排序问题 volatile
                    //1.分配内存
                    //2.初始化对象
                    //3.将初始化好的对象和内存建立关联
                    //4.初次访问
                    lazyDoubleCheck = new LazyDoubleCheck();
                }
            }
        }
        return lazyDoubleCheck;
    }

}
