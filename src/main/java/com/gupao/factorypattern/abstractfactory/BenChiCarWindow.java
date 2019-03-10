package com.gupao.factorypattern.abstractfactory;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/9 01:43
 * @Description:
 */
public class BenChiCarWindow implements ICarWindow {
    @Override
    public void clean() {
        System.out.print("奔驰车的车窗擦洗干净");
    }
}
