package com.rcj.imitation.spring.framework.aop;

import lombok.Data;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/19 05:32
 * @Description:
 */
@Data
public class GFAopConfig {

    private String pointCut;
    private String aspectBefore;
    private String aspectAfter;
    private String aspectClass;
    private String aspectAfterThrow;
    private String aspectAfterThrowingName;

}
