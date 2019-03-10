package com.gupao.factorypattern.abstractfactory;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/9 01:46
 * @Description:
 */
public class IBaoMaCarFactory implements ICarFactory {

    @Override
    public ITyre createType() {
        return new BaoMaType();
    }

    @Override
    public ICarWindow createCarWindow() {
        return new BaoMaCarWindow();
    }
}
