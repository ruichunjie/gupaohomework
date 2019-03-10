package com.gupao.singleton.register;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/10 16:19
 * @Description:
 */
public class ContainerSingletonTest {

    public static void main(String[] args) {
        Object obj = ContainerSingleton.getBean("com.gupao.singleton.register.Animal");
        System.out.println(obj);
    }
}
