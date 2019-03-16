package com.gupao.strategy;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/16 18:30
 * @Description:
 */
public class SubStrategy implements Strategy{

    public int calc(int num1, int num2) {
        return num1-num2;
    }

}
