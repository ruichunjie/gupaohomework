package com.rcj.imitation.spring.framework.context;

import com.rcj.imitation.spring.framework.annotation.GFAutowired;
import com.rcj.imitation.spring.framework.annotation.GFController;
import com.rcj.imitation.spring.framework.annotation.GFService;
import com.rcj.imitation.spring.framework.aop.GFAopConfig;
import com.rcj.imitation.spring.framework.aop.GFAopProxy;
import com.rcj.imitation.spring.framework.aop.GFCglibAopProxy;
import com.rcj.imitation.spring.framework.aop.GFJdkDynamicAopProxy;
import com.rcj.imitation.spring.framework.aop.support.GFAdvisedSupport;
import com.rcj.imitation.spring.framework.beans.GFBeanWrapper;
import com.rcj.imitation.spring.framework.beans.config.GFBeanDefinition;
import com.rcj.imitation.spring.framework.beans.config.GFBeanPostProcessor;
import com.rcj.imitation.spring.framework.beans.support.GFBeanDefinitionReader;
import com.rcj.imitation.spring.framework.beans.support.GFDefaultListableBeanFactory;
import com.rcj.imitation.spring.framework.core.GFBeanFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/16 14:57
 * @Description:
 */
public class GFApplicationContext extends GFDefaultListableBeanFactory implements GFBeanFactory {

    private String[] configLocations;
    private GFBeanDefinitionReader reader;
    private Map<String,Object> singletonBeanCacheMap = new HashMap<>();
    private Map<String, GFBeanWrapper> beanWrapperMap = new ConcurrentHashMap<>();

    public GFApplicationContext(String... configLocations){
        this.configLocations = configLocations;
        try{
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void refresh() throws Exception {
        //1.定位配置文件
        reader = new GFBeanDefinitionReader(this.configLocations);
        //2.加载配置文件
        List<GFBeanDefinition> beanDefinitionList = reader.loadBeanDefinitions();
        //3.注册 把配置信息放到伪IOC容器中
        doRegisterBeanDefinition(beanDefinitionList);
        //4.把不是延时加载的类 提前初始化
        doAutowired();
    }

    /**
     * @Author ChunJie Ren
     * @Description 注册信息
     * @Date 5:29 PM 2019/4/16
     * @Param
     * @return
     */
    private void doRegisterBeanDefinition(List<GFBeanDefinition> beanDefinitionList) throws Exception {

        for (GFBeanDefinition obj:beanDefinitionList) {
            if(super.beanDefinitionMap.containsKey(obj.getFactoryBeanName())){
                throw new Exception("The"+obj.getFactoryBeanName()+"is Exist!!");
            }
            super.beanDefinitionMap.put(obj.getFactoryBeanName(),obj);
        }
    }

    /**
     * @Author ChunJie Ren
     * @Description 注入
     * @Date 10:37 AM 2019/4/17
     * @Param
     * @return
     */
    private void doAutowired() {
        super.beanDefinitionMap.entrySet().forEach(obj->{
            String beanName = obj.getKey();
            if(!obj.getValue().isLazyInit()){
                getBean(beanName);
            }
        });
    }

    /**
     * @Author ChunJie Ren
     * @Description 读取BeanDefinition中的信息 通过装饰器模式 1.保留OOP关系2.对它进行扩展
     * @Date 11:34 AM 2019/4/17
     * @Param 
     * @return 
     */
    @Override
    public Object getBean(String beanName) {

        GFBeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        try{
            //生成通知事件
            GFBeanPostProcessor beanPostProcessor = new GFBeanPostProcessor();
            Object instance = instantiateBean(beanDefinition);
            if(null == instance){return null;}
            //在实例初始化以前调用一次
            beanPostProcessor.postProcessBeforeInitialization(instance,beanName);
            GFBeanWrapper beanWrapper = new GFBeanWrapper(instance);
            this.beanWrapperMap.put(beanName,beanWrapper);
            //在实例化后调用一次
            beanPostProcessor.postProcessAfterInitialization(instance,beanName);
            populateBean(beanName,instance);
            return beanWrapperMap.get(beanName).getWrappedInstance();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * @Author ChunJie Ren
     * @Description 根据BeanDefinition 返回具体的实例对象
     * @Date 2:07 PM 2019/4/17
     * @Param 
     * @return 
     */
    private Object instantiateBean(GFBeanDefinition beanDefinition) {

        Object instance = null;
        String className = beanDefinition.getBeanClassName();
        try{

            if(this.singletonBeanCacheMap.containsKey(className)){
                instance = this.singletonBeanCacheMap.get(className);
            }else{
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();

                GFAdvisedSupport config = instantionAopConfig(beanDefinition);
                config.setTargetClass(clazz);
                config.setTarget(instance);
                if(config.pointCutMatch()) {
                    instance = createProxy(config).getProxy();
                }


                this.singletonBeanCacheMap.put(className,instance);
            }
            return instance;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private GFAopProxy createProxy(GFAdvisedSupport config) {
        Class targetClass = config.getTargetClass();
        if (targetClass.getInterfaces().length > 0) {
            return new GFJdkDynamicAopProxy(config); }
        return new GFCglibAopProxy(config);
    }

    private GFAdvisedSupport instantionAopConfig(GFBeanDefinition beanDefinition) {
        GFAopConfig config = new GFAopConfig();
        config.setPointCut(reader.getConfig().getProperty("pointCut"));
        config.setAspectClass(reader.getConfig().getProperty("aspectClass"));
        config.setAspectBefore(reader.getConfig().getProperty("aspectBefore"));
        config.setAspectAfter(reader.getConfig().getProperty("aspectAfter"));
        config.setAspectAfterThrow(reader.getConfig().getProperty("aspectAfterThrow"));
        config.setAspectAfterThrowingName(reader.getConfig().getProperty("aspectAfterThrowingName"));
        return new GFAdvisedSupport(config);
    }

    /**
     * @Author ChunJie Ren
     * @Description 对象注入
     * @Date 2:13 PM 2019/4/17
     * @Param 
     * @return 
     */
    private void populateBean(String beanName, Object instance) {
        Class clazz = instance.getClass();
        if(!(clazz.isAnnotationPresent(GFController.class)||
                clazz.isAnnotationPresent(GFService.class))){
            return;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields) {
            if(!field.isAnnotationPresent(GFAutowired.class)){
                continue;
            }
            GFAutowired autowired = field.getAnnotation(GFAutowired.class);
            String autowiredBeanName = autowired.value().trim();
            if("".equalsIgnoreCase(autowiredBeanName)){
                autowiredBeanName= field.getType().getName();
            }
            field.setAccessible(true);
            try{
                field.set(instance, this.beanWrapperMap.get(autowiredBeanName).getWrappedInstance());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * @Author ChunJie Ren
     * @Description 获取所有的配置对象名称
     * @Date 5:14 PM 2019/4/18
     * @Param
     * @return
     */
    public String[] getBeanDefinitionNames(){
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    /**
     * @Author ChunJie Ren
     * @Description 获取所有的配置对象数目
     * @Date 5:14 PM 2019/4/18
     * @Param
     * @return
     */
    public int getBeanDefinitionCount(){
        return this.beanDefinitionMap.size();
    }

    /**
     * @Author ChunJie Ren
     * @Description 获取配置
     * @Date 5:14 PM 2019/4/18
     * @Param
     * @return
     */
    public Properties getConfig(){
        return this.reader.getConfig();
    }
}
