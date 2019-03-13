package com.gupao.prototype.deep;

import java.io.*;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/13 12:19
 * @Description:
 */
public class User implements Serializable {

    /**年龄*/
    private Integer age;
    /**名字*/
    private String name;
    /**地址*/
    private Address address;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User deepClone(){

        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
//            if( User.class.isInstance(ois.readObject())){
//                User u = (User) ois.readObject();
//                return u;
//            }
            User u = (User) ois.readObject();
            return u;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
