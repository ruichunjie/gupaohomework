package com.gupao.singleton.lazy;

import java.util.Objects;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/10 14:47
 * @Description: 没有用到synchronized 内部类比大类优先加载 性能最优
 */
public class LazyInnerClassSingleton {

    private LazyInnerClassSingleton(){
        if(!Objects.isNull(LazyHolder.LAZY)){
            System.out.println("该类是单例,不允许构建多个实例");
        }

    }

    private static final LazyInnerClassSingleton  getInstance(){
        return LazyHolder.LAZY;
    }

    //LazyHolder里面的逻辑需要等到外部逻辑调用才会调用
    //JVM 底层逻辑 完美避免了线程安全问题
    private static class LazyHolder{
        private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
    }


}
