package com.gupao.prototype.deep;

import java.io.*;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/13 12:27
 * @Description:
 */
public class SerializableTest {

    public static void main(String[] args) {

        User u1 = new User();
        u1.setAge(1);
        u1.setName("a");
        Address address = new Address("家住河边");
        u1.setAddress(address);

        User u2 = u1.deepClone();
        System.out.println("u1==u2:"+(u1 == u2));
        System.out.println("u1.getAddress() == u2.getAddress():" + (u1.getAddress() == u2.getAddress()));
        System.out.println("u1.getAddress().getStreet() == u2.getAddress().getStreet():" + (u1.getAddress().getStreet() == u2.getAddress().getStreet()));

        u2.getAddress().setStreet("家住山边");
        u2.setAge(2);
        System.out.println(u1.getAge());
        System.out.println(u1.getAddress().getStreet());

    }


}
