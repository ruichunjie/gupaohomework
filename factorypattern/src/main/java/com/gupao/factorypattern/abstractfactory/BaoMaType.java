package com.gupao.factorypattern.abstractfactory;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/9 01:40
 * @Description:
 */
public class BaoMaType implements ITyre {

    @Override
    public void move() {
        System.out.print("宝马轮胎");
    }

}
