package com.gupao.factorypattern.abstractfactory;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/9 01:38
 * @Description:  所有工厂类的父类
 */
public interface ICarFactory {

    ITyre createType();

    ICarWindow createCarWindow();
}
