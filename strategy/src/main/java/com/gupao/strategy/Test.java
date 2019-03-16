package com.gupao.strategy;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/16 18:32
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        Environment env = new Environment(new AddStrategy());
        int res = env.calculate(20,30);
        System.out.println(res);
    }
}
