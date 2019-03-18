package com.gupao.observer;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/18 19:16
 * @Description:
 */
public class Question {

    private String content;

    private String name;

    public Question(String name,String content){
        this.name = name;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
