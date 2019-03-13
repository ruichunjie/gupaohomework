package com.gupao.factorypattern.factorymethod;

import com.gupao.factorypattern.ICar;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/9 01:34
 * @Description:
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        ICarFactory baoMaCarFactory = new BaoMaCarFactory();
        ICar car = baoMaCarFactory.createCar();
        car.drive();
    }
}
