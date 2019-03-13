package com.gupao.proxy.staticed;

import com.gupao.proxy.HelloWorld;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/13 15:18
 * @Description:
 */
public class StaticProxy {

    private HelloWorld helloWorld;

    public StaticProxy(HelloWorld helloWorld){
        this.helloWorld = helloWorld;
    }

    /**
     * @Author ChunJie Ren
     * @Description 静态代理 只能代理这个类 不符合开闭原则 如果要代理其他类 只能重写代理类 代理其他方法要修改当前代理类
     * @Date 3:25 PM 2019/3/13
     * @Param
     * @return
     */
    public void sayHelloWorld() {
        this.before();
        helloWorld.sayHelloWorld();
        this.after();
    }

    public void before(){
        System.out.println("打招呼前");
    }

    public void after(){
        System.out.println("打招呼后");
    }
}
