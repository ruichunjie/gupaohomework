package com.gupao.proxy.dynamicproxy.cglib;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/13 16:42
 * @Description:
 */
public class CglibTest {

    public static void main(String[] args) {
        try{
            HelloWorldImpl obj = (HelloWorldImpl) new CglibProxy().getInstance(HelloWorldImpl.class);
            System.out.println(obj);
            obj.sayHelloWorld();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
