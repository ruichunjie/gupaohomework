package com.gupao.factorypattern.simplefactory;

import com.gupao.factorypattern.ICar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;


/**
 * @Author:ChunJieRen
 * @Date:2019/3/9 01:15
 * @Description:
 */
public class CarFactory {

    private static final Logger logger = LoggerFactory.getLogger(CarFactory.class);

    /**
     * @Author ChunJie Ren
     * @Description 依据反射来使用工厂方法
     * @Date 1:22 AM 2019/3/9
     * @Param
     * @return
     */
    public ICar createCar(Class<? extends ICar> clazz){
        try{
            if(!Objects.isNull(clazz)){
                return clazz.newInstance();
            }
        }catch (Exception e){
            logger.error("出现异常:", e);
        }
        return null;
    }

}
