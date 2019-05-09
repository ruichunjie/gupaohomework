package com.gp.orm.common;

import java.io.Serializable;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/26 10:22
 * @Description: 底层结果封装
 */
public class ResultMsg<T> implements Serializable {

    private static final long serialVersionUID = 2635002588308355785L;
    /**状态码*/
    private int status;
    /**解释*/
    private String msg;
    /**数据*/
    private T data;

    public ResultMsg(){}

    public ResultMsg(int status){
        this.status = status;
    }

    public ResultMsg(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    public ResultMsg(int status, T data){
        this.status = status;
        this.data = data;
    }

    public ResultMsg(int status, String msg, T data){
        this.data = data;
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
