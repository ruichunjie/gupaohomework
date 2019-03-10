package com.gupao.singleton.lazy;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/10 14:11
 * @Description:
 */
public class LazySimpleSingletonTest {

    public static void main(String[] args) {

        Thread t1 = new Thread(new ExectorThread());
        Thread t2 = new Thread(new ExectorThread());

        t1.start();
        t2.start();

        System.out.println("Exctor End");
    }
}
