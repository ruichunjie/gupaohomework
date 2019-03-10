package com.gupao.factorypattern.abstractfactory;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/9 01:48
 * @Description:
 */
public class IBenChiCarFactory implements ICarFactory {
    @Override
    public ITyre createType() {
        return new BenChiType();
    }

    @Override
    public ICarWindow createCarWindow() {
        return new BenChiCarWindow();
    }
}
