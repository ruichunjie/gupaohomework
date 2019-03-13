package com.gupao.proxy.staticed;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/13 15:27
 * @Description:
 */
public class StaticTest {

    public static void main(String[] args) {
        StaticProxy staticProxy = new StaticProxy(new HelloWorldImpl());
        staticProxy.sayHelloWorld();
    }


}
