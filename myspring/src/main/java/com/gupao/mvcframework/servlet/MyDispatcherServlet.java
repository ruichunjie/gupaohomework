package com.gupao.mvcframework.servlet;

import com.gupao.mvcframework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/25 09:50
 * @Description:
 */
public class MyDispatcherServlet extends HttpServlet {

    //保存application.properties配置文件中的内容
    private Properties contextConfig = new Properties();

    //保存所有的类名
    private List<String> classNameList = new ArrayList<String>();

    //IOC容器
    private Map<String,Object> ioc = new HashMap<String, Object>();

    //url 和method 的映射
    private List<Handler> handlerMapping = new ArrayList<Handler>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //6.调试
            this.doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exection,Detail: "+Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception{

        Handler handler = this.getHandler(req);
        if(Objects.isNull(handler)||!this.handlerMapping.contains(handler)){
            resp.getWriter().write("404 Not Found!!!");
            return ;
        }

        Class<?>[] paramTypeList = handler.getParamTypeList();
        Object[] paramValues = new Object[paramTypeList.length];
        Map<String,String[]> params = req.getParameterMap();
        for(Map.Entry<String,String[]> param : params.entrySet()){
            String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]","")
                    .replaceAll("\\s",",");
            if(!handler.paramIndexMapping.containsKey(param.getKey())){continue;}
            int index = handler.paramIndexMapping.get(param.getKey());
            paramValues[index] = convert(paramTypeList[index],value);
        }
        if(handler.paramIndexMapping.containsKey(HttpServletRequest.class.getName())) {
            int reqIndex = handler.paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
        }

        if(handler.paramIndexMapping.containsKey(HttpServletResponse.class.getName())) {
            int respIndex = handler.paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;
        }

        Object returnValue = handler.method.invoke(handler.controller,paramValues);
        if(returnValue == null || returnValue instanceof Void){ return; }
        resp.getWriter().write(returnValue.toString());


    }

    private Object convert(Class<?> clazz, String value) {
        if(Integer.class == clazz){
            return Integer.valueOf(value);
        }
        else if(Double.class == clazz){
            return Double.valueOf(value);
        }
        return  value;
    }

    private Handler getHandler(HttpServletRequest req){
        if(this.handlerMapping.isEmpty()){return null;}
        //绝对路径
        String url = req.getRequestURI();
        //处理成相对路径
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");
        for(Handler handler:this.handlerMapping){
            Matcher matcher = handler.getPattern().matcher(url);
            if(!matcher.matches()){ continue;}
            return handler;
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        //1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2.扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));

        //3.初始化扫描到的类,并将它们放入IOC容器之中
        doInstance();

        //4.完成依赖注入
        doAutowired();

        //5.初始化HandlerMapping
        inithandlerMapping();

        System.out.println(" My Spring framework is init.");

    }

    /**
     * @Author ChunJie Ren
     * @Description 初始化url和Method的一对一关系
     * @Date 5:06 PM 2019/3/25
     * @Param
     * @return
     */
    private void inithandlerMapping() {
        if(ioc.isEmpty()){return;}
        for(Map.Entry<String,Object> entry:ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();

            if (!clazz.isAnnotationPresent(MyController.class)) { continue; }

            String baseUrl = "";
            if(clazz.isAnnotationPresent(MyRequestMapping.class)){
                MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            for(Method method :clazz.getMethods()){
                if(!method.isAnnotationPresent(MyRequestMapping.class)){continue;}
                MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
                String regex = ("/"+baseUrl+"/"+requestMapping.value()).replaceAll("/+","/");
                Pattern pattern = Pattern.compile(regex);
                this.handlerMapping.add(new Handler(pattern,entry.getValue(),method));
                System.out.println("Mapped:"+pattern+","+method);
            }

        }
    }

    /**
     * @Author ChunJie Ren
     * @Description url和方法的映射
     * @Date 6:39 PM 2019/3/26
     * @Param
     * @return
     */
    public class Handler{
        private Pattern pattern;
        private Method method;
        private Object controller;
        private Class<?>[] paramTypeList;
        private Map<String,Integer> paramIndexMapping;

        public Handler(Pattern pattern, Object controller, Method method){
            this.pattern = pattern;
            this.method = method;
            this.controller = controller;
            paramTypeList = method.getParameterTypes();
            paramIndexMapping = new HashMap<String, Integer>();
            putParamIndexMapping(method);
        }

        public Pattern getPattern() {
            return pattern;
        }

        public Method getMethod() {
            return method;
        }

        public Object getController() {
            return controller;
        }

        public Class<?>[] getParamTypeList() {
            return paramTypeList;
        }

        public void putParamIndexMapping(Method method){
            Annotation [] [] pa = method.getParameterAnnotations();
            for(int i=0;i<pa.length; i++){
                for(Annotation a : pa[i]){
                    if(a instanceof MyRequestParam){
                        String paramName = ((MyRequestParam)a).value();
                        if(!"".equals(paramName.trim())){
                            paramIndexMapping.put(paramName,i);
                        }
                    }
                }
            }

            Class<?> [] paramsTypeList = method.getParameterTypes();
            for (int i = 0; i < paramsTypeList.length ; i ++) {
                Class<?> type = paramsTypeList[i];
                if(type == HttpServletRequest.class ||
                        type == HttpServletResponse.class){
                    paramIndexMapping.put(type.getName(),i);
                }
            }


        }
    }

    /**
     * @Author ChunJie Ren
     * @Description 自动依赖注入
     * @Date 4:44 PM 2019/3/25
     * @Param
     * @return
     */
    private void doAutowired() {

        if(ioc.isEmpty()){return;}

        for(Map.Entry<String,Object> entry : ioc.entrySet()){
            Field[]  fields =  entry.getValue().getClass().getDeclaredFields();
            for(Field field : fields){
                if(!field.isAnnotationPresent(MyAutowired.class)){ continue;}
                MyAutowired autowired = field.getAnnotation(MyAutowired.class);
                String beanName = autowired.value().trim();
                if("".equalsIgnoreCase(beanName)){
                    beanName = field.getType().getName();
                }

                field.setAccessible(Boolean.TRUE);
                try{
                    field.set(entry.getValue(),ioc.get(beanName));
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * @Author ChunJie Ren
     * @Description 初始化 为DI做准备
     * @Date 4:43 PM 2019/3/25
     * @Param
     * @return
     */
    private void doInstance() {
        if(classNameList.isEmpty()){return;}

        try{
            for(String className:classNameList){
                Class<?> clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(MyController.class)){
                    Object instance = clazz.newInstance();
                    String beanName = clazz.getSimpleName().substring(0,1).toUpperCase()+
                            clazz.getSimpleName().substring(1);
                    ioc.put(beanName,instance);
                }else if(clazz.isAnnotationPresent(MyService.class)){
                    MyService myService = clazz.getAnnotation(MyService.class);
                    String beanName = myService.value();
                    if("".equals(beanName.trim())){
                        beanName =  clazz.getSimpleName().substring(0,1).toUpperCase()+
                                clazz.getSimpleName().substring(1);
                    }

                    Object instance = clazz.newInstance();
                    ioc.put(beanName,instance);
                    for(Class<?> i:clazz.getInterfaces()){
                        if(ioc.containsKey(i.getName())){
                            throw new Exception("The "+i.getName()+" is exist!");
                        }
                        ioc.put(i.getName(), instance);
                    }
                }else{
                    continue;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @Author ChunJie Ren
     * @Description 扫描相关的类
     * @Date 12:44 PM 2019/3/25
     * @Param
     * @return
     */
    private void doScanner(String scanPackage) {

        URL url = this.getClass().getClassLoader().getResource("/"+scanPackage.replaceAll("\\.","/"));
        File classPath = new File(url.getFile());
        for(File file : classPath.listFiles()){
            if(file.isDirectory()){
                doScanner(scanPackage+"."+file.getName());
            }else{
                if(!file.getName().endsWith(".class")){
                    continue;
                }
                String className = scanPackage+"."+file.getName().replace(".class","");
                classNameList.add(className);
            }
        }


    }

    /**
     * @Author ChunJie Ren
     * @Description 加载配置文件
     * @Date 12:40 PM 2019/3/25
     * @Param
     * @return
     */
    private void doLoadConfig(String contextConfigLocation) {

        //从类文件下找到Spring主配置文件所在的路径 将其取出来放到properties对象中 相对于scanPackage=com.gupaoedu.demo 从文件中保存到了内存中
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);

        try{
            contextConfig.load(fis);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(null != fis){
                try {
                    fis.close();
                    fis = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
