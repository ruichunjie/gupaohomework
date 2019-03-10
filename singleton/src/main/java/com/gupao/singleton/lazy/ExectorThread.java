package com.gupao.singleton.lazy;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/10 14:13
 * @Description:
 */
public class ExectorThread implements Runnable{

    @Override
    public void run() {
        //LazySimpleSingleton lazySimpleSingleton = LazySimpleSingleton.getInstance();
        LazyDoubleCheck lazyDoubleCheck = LazyDoubleCheck.getInstance();
        System.out.println(Thread.currentThread().getName()+":"+lazyDoubleCheck);
    }

}
