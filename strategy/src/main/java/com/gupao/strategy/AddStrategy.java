package com.gupao.strategy;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/16 18:29
 * @Description:
 */
public class AddStrategy implements Strategy {
    public int calc(int num1, int num2) {
        return num1+num2;
    }
}
