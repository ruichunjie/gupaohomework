package com.rcj.imitation.spring.framework.webmvc.servlet;

import com.rcj.imitation.spring.framework.annotation.GFRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/17 19:22
 * @Description:
 */
public class GFHandlerAdapter {
    public boolean supports(Object handler){ return (handler instanceof GFHandlerMapping);}


    GFModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        GFHandlerMapping handlerMapping = (GFHandlerMapping)handler;
        Map<String, Integer> paramMapping = new HashMap<String,Integer>();

        Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
        for (int i = 0; i < pa.length ; i ++) {
            for (Annotation a : pa[i]) {
                if(a instanceof GFRequestParam){
                    String paramName = ((GFRequestParam) a).value();
                    if(!"".equals(paramName.trim())){
                        paramMapping.put(paramName,i);
                    }
                }
            }
        }

        Class<?>[] paramTypes = handlerMapping.getMethod().getParameterTypes();
        for (int i = 0;i < paramTypes.length; i ++) {
            Class<?> type = paramTypes[i];
            if(type == HttpServletRequest.class ||
                    type == HttpServletResponse.class){
                paramMapping.put(type.getName(),i);
            }
        }
        Map<String,String[]> reqParameterMap = request.getParameterMap();
        Object [] paramValues = new Object[paramTypes.length];
        for (Map.Entry<String,String[]> param : reqParameterMap.entrySet()) {
            String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]","")
                    .replaceAll("\\s","");
            if(!paramMapping.containsKey(param.getKey())){continue;}
            int index = paramMapping.get(param.getKey());
            paramValues[index] = caseStringValue(value,paramTypes[index]);
        }

        if(paramMapping.containsKey(HttpServletRequest.class.getName())) {
            int reqIndex = paramMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = request;
        }
        if(paramMapping.containsKey(HttpServletResponse.class.getName())) {
            int respIndex = paramMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = response;
        }
        Object result =handlerMapping.getMethod().invoke(handlerMapping.getController(),paramValues);
        if(result == null){ return null; }
        boolean isModelAndView = handlerMapping.getMethod().getReturnType() == GFModelAndView.class;
        if(isModelAndView){
            return (GFModelAndView)result;
        }else{
            return null;
        }
    }

    private Object caseStringValue(String value, Class<?> clazz) {
        if(clazz == String.class){
            return value;
        }else if(clazz == Integer.class){
            return Integer.valueOf(value);
        }else if(clazz == int.class){
            return Integer.valueOf(value);
        }
        return null;
    }
}
