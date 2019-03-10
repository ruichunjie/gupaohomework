package com.gupao.singleton.lazy;


import java.util.Objects;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/10 14:09
 * @Description: 懒汉式
 */
public class LazySimpleSingleton {

    private static LazySimpleSingleton lazySimpleSingleton = null;
    private LazySimpleSingleton(){}

    /**
     * @Author ChunJie Ren
     * @Description 加上synchronized关键字 一个线程RUNNING 其他线程MONITOR 保障线程安全
     *      但是有性能问题
     * @Date 2:29 PM 2019/3/10
     * @Param
     * @return
     */
    public static synchronized LazySimpleSingleton getInstance(){
        if(Objects.isNull(lazySimpleSingleton)){
            //当在此打线程端点 都进行到此处时 一个线程直接 到return处代码 查看对象地址 为1  一个在之后也到return处代码 查看对象地址 为2
            //此时不论时哪个线程 地址都为2  后来的线程覆盖了第一个线程 结果地址相同
            lazySimpleSingleton = new LazySimpleSingleton();
        }
        return  lazySimpleSingleton;
    }

}
