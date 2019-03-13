package com.gupao.factorypattern;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/9 01:12
 * @Description:    宝马
 */
public class BaoMaCar implements ICar{

    @Override
    public void drive() {
        System.out.print("开宝马汽车");
    }
}
