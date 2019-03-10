package com.gupao.factorypattern;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/9 01:14
 * @Description: 奔驰
 */
public class BenChiCar implements ICar {

    @Override
    public void drive() {
        System.out.print("开奔驰车");
    }

}
