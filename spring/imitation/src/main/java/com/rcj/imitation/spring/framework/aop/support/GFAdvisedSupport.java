package com.rcj.imitation.spring.framework.aop.support;

import com.rcj.imitation.spring.framework.aop.GFAopConfig;
import com.rcj.imitation.spring.framework.aop.aspect.GFAfterReturningAdvice;
import com.rcj.imitation.spring.framework.aop.aspect.GFAfterThrowingAdvice;
import com.rcj.imitation.spring.framework.aop.aspect.GFMethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/19 04:39
 * @Description:
 */
public class GFAdvisedSupport {

    private Class targetClass;
    private Object target;
    private Pattern pointCutClassPattern;
    private transient Map<Method, List<Object>> methodCache;
    private GFAopConfig config;

    public GFAdvisedSupport(GFAopConfig config){
        this.config = config;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
        parse();
    }
    public Object getTarget() {
        return target;
    }
    public void setTarget(Object target) {
        this.target = target;
    }

    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) throws Exception {
        List<Object> cached = methodCache.get(method);
        if (cached == null) {
            Method m = targetClass.getMethod(method.getName(),method.getParameterTypes());
            cached = methodCache.get(m);
            // 存入缓存
            this.methodCache.put(m, cached); }
            return cached;
        }
    public boolean pointCutMatch(){
        return pointCutClassPattern.matcher(this.targetClass.toString()).matches();
    }

    private void parse() {
        //pointCut 表达式
        String pointCut = config.getPointCut()
                .replaceAll("\\.", "\\\\.").replaceAll("\\\\.\\*", ".*").replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
        String pointCutForClass = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        pointCutClassPattern = Pattern.compile("class " + pointCutForClass.substring(pointCutForClass.lastIndexOf(" ") + 1));

        methodCache = new HashMap<Method, List<Object>>();
        Pattern pattern = Pattern.compile(pointCut);
        try {
            Class aspectClass = Class.forName(config.getAspectClass());
            Map<String, Method> aspectMethods = new HashMap<String, Method>();
            for (Method m : aspectClass.getMethods()) {
                aspectMethods.put(m.getName(), m);
            }
            //在这里得到的方法都是原生的方法
            for (Method m : targetClass.getMethods()) {
                String methodString = m.toString();
                if (methodString.contains("throws")) {
                    methodString = methodString.substring(0, methodString.lastIndexOf("throws")).trim();
                }
                Matcher matcher = pattern.matcher(methodString);
                if (matcher.matches()) {
                    List<Object> advices = new LinkedList<Object>();
                    if (!(null == config.getAspectBefore() ||
                            "".equals(config.getAspectBefore().trim()))) {
                        advices.add(new GFMethodBeforeAdvice(aspectMethods.get(config.getAspectBefore()), aspectClass.newInstance()));
                    }
                    if (!(null == config.getAspectAfter() || "".equals(config.getAspectAfter().trim()))) {
                        advices.add(new GFAfterReturningAdvice(aspectMethods.get(config.getAspectAfter()), aspectClass.newInstance()));
                    }
                    if (!(null == config.getAspectAfterThrow() || "".equals(config.getAspectAfterThrow().trim()))) {
                        GFAfterThrowingAdvice afterThrowingAdvice = new GFAfterThrowingAdvice(aspectMethods.get(config.getAspectAfterThrow()),
                                aspectClass.newInstance());
                        afterThrowingAdvice.setThrowingName(config.getAspectAfterThrowingName());
                        advices.add(afterThrowingAdvice);
                    }
                    methodCache.put(m,advices); }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
