package com.rcj.imitation.spring.framework.context;

import com.rcj.imitation.spring.framework.beans.config.GFBeanDefinition;
import com.rcj.imitation.spring.framework.beans.support.GFBeanDefinitionReader;
import com.rcj.imitation.spring.framework.beans.support.GFDefaultListableBeanFactory;
import com.rcj.imitation.spring.framework.core.GFBeanFactory;

import java.util.List;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/16 14:57
 * @Description:
 */
public class GFApplicationContext extends GFDefaultListableBeanFactory implements GFBeanFactory {

    private String[] configLocations;
    private GFBeanDefinitionReader reader;

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

    private void doAutowired() {
        super.beanDefinitionMap.entrySet().forEach(obj->{
            String beanName = obj.getKey();
            if(!obj.getValue().isLazyInit()){
                getBean(beanName);
            }
        });
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }
}
