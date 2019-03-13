package com.gupao.factorypattern.factorymethod;

import com.gupao.factorypattern.BaoMaCar;
import com.gupao.factorypattern.ICar;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/9 01:32
 * @Description:     宝马工厂
 */
public class BaoMaCarFactory implements  ICarFactory {

    @Override
    public ICar createCar() {
        return new BaoMaCar();
    }
}
