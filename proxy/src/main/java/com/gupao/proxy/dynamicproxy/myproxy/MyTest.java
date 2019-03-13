package com.gupao.proxy.dynamicproxy.myproxy;

import com.gupao.proxy.HelloWorld;
import com.gupao.proxy.dynamicproxy.jdkproxy.HelloWorldImpl;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/13 18:01
 * @Description:
 */
public class MyTest {

    public static void main(String[] args) {

        try{
            HelloWorld helloWorld = (HelloWorld)new MyProxym().getInstance(new HelloWorldImpl());
            System.out.println(helloWorld.getClass());
            helloWorld.sayHelloWorld();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
