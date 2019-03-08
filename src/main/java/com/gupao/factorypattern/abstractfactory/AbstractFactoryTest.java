package com.gupao.factorypattern.abstractfactory;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/9 01:49
 * @Description:
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        ICarFactory factory = new IBaoMaCarFactory();
        factory.createCarWindow().clean();
        factory.createType().move();
    }
}
