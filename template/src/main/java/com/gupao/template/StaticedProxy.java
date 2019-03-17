package com.gupao.template;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/17 17:06
 * @Description:
 */
public class StaticedProxy {

    private Drink drink;

    public StaticedProxy(Drink drink){
        this.drink = drink;
    }

    public void drinkTea(){
        drink.prepareDrink();
    }
}
