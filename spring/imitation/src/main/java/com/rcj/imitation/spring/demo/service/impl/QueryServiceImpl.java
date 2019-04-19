package com.rcj.imitation.spring.demo.service.impl;

import com.rcj.imitation.spring.demo.service.IQueryService;
import com.rcj.imitation.spring.framework.annotation.GFService;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/18 19:43
 * @Description:
 */
@GFService
@Slf4j
public class QueryServiceImpl implements IQueryService {

    @Override
    public String query(String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        String json = "{name:\"" + name + "\",time:\"" + time + "\"}";
        log.info("这是在业务方法中打印的:" + json);
        return json;
    }
}
