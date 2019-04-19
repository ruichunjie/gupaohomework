package com.rcj.imitation.spring.demo.aspect;

import com.rcj.imitation.spring.framework.aop.aspect.GFJoinPoint;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/19 05:54
 * @Description:
 */
@Slf4j
public class LogAspect {
    public void before(GFJoinPoint joinPoint){

        log.info("Invoker Before Method!!!" +
                "\nTargetObject:" + joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArguments()));
    }

    public void after(GFJoinPoint joinPoint){
        log.info("Invoker After Method!!!" +
                "\nTargetObject:" + joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArguments()));
    }

    public void afterThrowing(GFJoinPoint joinPoint, Throwable ex){
        log.info("出现异常" +
            "\nTargetObject:" + joinPoint.getThis() +
            "\nArgs:" + Arrays.toString(joinPoint.getArguments()) + "\nThrows:" + ex.getMessage());
    }
}
