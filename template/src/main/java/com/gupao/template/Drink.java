package com.gupao.template;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/17 16:53
 * @Description:
 */
public abstract class Drink {

    public final void prepareDrink(){
        //烧水
        this.boilWater();
        //倒入杯中
        pourInCup();
        if(isTea()){
            addCondiments();
        }

    }


    abstract void addCondiments();

    private void pourInCup() {
        System.out.println("将饮料倒入杯中");
    }

    private boolean isTea() {
        return Boolean.TRUE;
    }

    private void boilWater() {
        System.out.println("开始烧水");
    }

}
