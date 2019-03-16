package com.gupao.strategy;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/16 18:31
 * @Description:
 */
public class Environment {

    private Strategy strategy;
    public Environment(Strategy strategy){
        this.strategy = strategy;
    }

    public int calculate(int a, int b){
        return strategy.calc(a,b);
    }

}
