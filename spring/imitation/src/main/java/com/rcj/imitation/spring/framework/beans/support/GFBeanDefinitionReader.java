package com.rcj.imitation.spring.framework.beans.support;

import com.rcj.imitation.spring.framework.beans.config.GFBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/16 16:59
 * @Description:
 */
public class GFBeanDefinitionReader {

    private List<String> registryBeanClassList = new ArrayList<String>();
    private Properties config = new Properties();
    //固定文件key，相当于spring规范
    private String SCAN_PACKAGE ="scanPackage";
    public Properties getConfig(){
        return this.config;
    }

    /**
     * @Author ChunJie Ren
     * @Description 加载文件
     * @Date 5:04 PM 2019/4/16
     * @Param
     * @return
     */
    public GFBeanDefinitionReader(String... locations){
        //通过URL定位找到其所对应的文件，然后转换为文件流
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:",""));
        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    /**
     * @Author ChunJie Ren
     * @Description 扫描
     * @Date 5:04 PM 2019/4/16
     * @Param
     * @return
     */
    private void doScanner(String scanPackage) {
        //转换为文件路径，实际上就是把.替换为/就OK了
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.","/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if(file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            }else{
                if(!file.getName().endsWith(".class")){ continue;}
                String className = (scanPackage + "." + file.getName().replace(".class",""));
                registryBeanClassList.add(className);
            }
        }
    }

    /**
     * @Author ChunJie Ren
     * @Description 加载BeanDefinition
     * @Date 5:09 PM 2019/4/16
     * @Param
     * @return
     */
    public List<GFBeanDefinition> loadBeanDefinitions(){
        List<GFBeanDefinition> result = new ArrayList<GFBeanDefinition>();
        try {
            for (String className : registryBeanClassList) {
                Class<?> beanClass = Class.forName(className);
                if(beanClass.isInterface()) { continue; }
                //beanName有三种情况:
                //1、默认是类名首字母小写
                //2、自定义名字
                //3、接口注入
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()),beanClass.getName()));

                Class<?> [] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    //如果是多个实现类，只能覆盖
                    result.add(doCreateBeanDefinition(i.getName(),beanClass.getName()));
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Author ChunJie Ren
     * @Description 创建BeanDefinition对象
     * @Date 5:08 PM 2019/4/16
     * @Param
     * @return
     */
    private GFBeanDefinition doCreateBeanDefinition(String factoryBeanName,String beanClassName){
        GFBeanDefinition beanDefinition = new GFBeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(factoryBeanName);
        return beanDefinition;
    }

    /**
     * @Author ChunJie Ren
     * @Description 大写转小写
     * @Date 5:07 PM 2019/4/16
     * @Param
     * @return
     */
    private String toLowerFirstCase(String simpleName) {
        char [] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
