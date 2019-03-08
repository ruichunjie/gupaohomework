package com.gupao.factorypattern.simplefactory;

import com.gupao.factorypattern.BaoMaCar;
import com.gupao.factorypattern.ICar;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/9 01:24
 * @Description:
 */
public class SimpleFactoryTest {

    /**
     * @Author ChunJie Ren
     * @Description 测试简单工厂
     * @Date 1:25 AM 2019/3/9
     * @Param
     * @return
     */
    public static void main(String[] args) {
        CarFactory factory = new CarFactory();
        ICar car = factory.createCar(BaoMaCar.class);
        car.drive();
    }
}
