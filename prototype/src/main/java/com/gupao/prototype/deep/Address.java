package com.gupao.prototype.deep;

import java.io.Serializable;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/13 12:22
 * @Description:
 */
public class Address implements Serializable {

    private String street;

    public Address(String street){
        this.street = street;
    }

    public String getStreet(){
        return street;
    }

    public void setStreet(String street){
        this.street = street;
    }

}
