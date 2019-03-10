package com.gupao.singleton.lazy;

import java.lang.reflect.Constructor;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/10 14:55
 * @Description:
 */
public class LazyInnerClassSingletonTest {

    public static void main(String[] args) {

        try {
            Class<?> clazz = LazyInnerClassSingleton.class;
            Constructor c = clazz.getDeclaredConstructor(null);
            c.setAccessible(Boolean.TRUE);
            Object o1 = c.newInstance();
            Object o2 = c.newInstance();
            System.out.println(o1 == o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

