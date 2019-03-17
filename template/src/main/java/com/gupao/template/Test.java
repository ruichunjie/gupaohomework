package com.gupao.template;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/17 16:59
 * @Description:
 */
public class Test{

    public static void main(String[] args) {
        Drink drink = new Drink() {
            @Override
            void addCondiments() {
                System.out.println("加糖");
            }
        };

        StaticedProxy staticedProxy = new StaticedProxy(drink);

        staticedProxy.drinkTea();
    }

}
