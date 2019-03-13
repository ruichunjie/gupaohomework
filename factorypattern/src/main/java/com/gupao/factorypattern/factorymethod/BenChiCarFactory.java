package com.gupao.factorypattern.factorymethod;

import com.gupao.factorypattern.BenChiCar;
import com.gupao.factorypattern.ICar;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/9 01:33
 * @Description:
 */
public class BenChiCarFactory implements ICarFactory {

    @Override
    public ICar createCar() {
        return new BenChiCar();
    }

}
