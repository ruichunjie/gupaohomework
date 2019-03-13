package com.gupao.factorypattern.abstractfactory;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/9 01:41
 * @Description:
 */
public class BenChiType implements ITyre {
    @Override
    public void move() {
        System.out.print("奔驰轮胎");
    }
}
