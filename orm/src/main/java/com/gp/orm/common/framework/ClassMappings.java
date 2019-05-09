package com.gp.orm.common.framework;

import org.apache.commons.collections.map.HashedMap;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/26 14:02
 * @Description:
 */
public class ClassMappings {

    private ClassMappings(){}

    static final Set<Class<?>> SUPPORTED_SQL_OBJECTS = new HashSet<Class<?>>();

    static{
        /**默认支持自动转换*/
        Class<?>[] classes ={
                boolean.class,Boolean.class,
                short.class,Short.class,
                int.class,Integer.class,
                long.class,Long.class,
                float.class,Float.class,
                double.class,Double.class,
                String.class,
                Date.class,
                Timestamp.class,
                BigDecimal.class
        };
        SUPPORTED_SQL_OBJECTS.addAll(Arrays.asList(classes));
    }

    /**
     * @Author ChunJie Ren
     * @Description 是否支持自动转换
     * @Date 2:50 PM 2019/4/26
     * @Param
     * @return
     */
    static boolean isSupportedSQLObject(Class<?> clazz){
        return clazz.isEnum() || SUPPORTED_SQL_OBJECTS.contains(clazz);
    }

    /**
     * @Author ChunJie Ren
     * @Description 查找public权限的get方法
     * @Date 2:50 PM 2019/4/26
     * @Param 
     * @return 
     */
    public static Map<String, Method> findPublicGetters(Class<?> clazz){
        Map<String, Method> map = new HashMap<>();
        Method[] methods = clazz.getMethods();
        for(Method method:methods){
            if(Modifier.isStatic(method.getModifiers())){
                continue;
            }
            if(method.getParameterTypes().length != 0){
                continue;
            }
            if(method.getName().equalsIgnoreCase("getClass")){
                continue;
            }
            Class<?> returnTypes = method.getReturnType();
            if(void.class.equals(returnTypes)){
                continue;
            }
            if(!isSupportedSQLObject(returnTypes)){
                continue;
            }
            if((returnTypes.equals(boolean.class)
                    || returnTypes.equals(Boolean.class))
                    && method.getName().startsWith("is")
                    && method.getName().length() >2){
                map.put(getGetterName(method),method);
                continue;
            }
            if(!method.getName().startsWith("get")){
                continue;
            }
            if(method.getName().length()<4){
                continue;
            }
            map.put(getGetterName(method),method);
        }
        return map;
    }

    /**
     * @Author ChunJie Ren
     * @Description 查找所有属性
     * @Date 2:53 PM 2019/4/26
     * @Param
     * @return
     */
    public static Field[] findFields(Class<?> clazz){
        return clazz.getDeclaredFields();
    }

    /**
     * @Author ChunJie Ren
     * @Description 查找所有public修饰的Setter方法
     * @Date 2:53 PM 2019/4/26
     * @Param
     * @return
     */
    public static Map<String, Method> findPublicSetters(Class<?> clazz){
        Map<String, Method> map = new HashMap<String, Method>();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (Modifier.isStatic(method.getModifiers())) {
                continue;
            }
            if ( !void.class.equals(method.getReturnType())) {
                continue;
            }
            if (method.getParameterTypes().length != 1) {
                continue;
            }
            if ( !method.getName().startsWith("set")) {
                continue;
            }
            if (method.getName().length() < 4) {
                continue;
            }
            if(!isSupportedSQLObject(method.getParameterTypes()[0])){
                continue;
            }
            map.put(getSetterName(method), method); }
        return map;
    }


    /**
     * @Author ChunJie Ren
     * @Description 得到get方法的属性名
     * @Date 2:46 PM 2019/4/26
     * @Param
     * @return
     */
    public static String getGetterName(Method getter){
        String name = getter.getName();
        if(name.startsWith("is")){
            name = name.substring(2);
        }else {
            name = name.substring(3);
        }

        return Character.toLowerCase(name.charAt(0)) + name.substring(1);
    }

    /**
     * @Author ChunJie Ren
     * @Description 得到set方法的属性名
     * @Date 2:51 PM 2019/4/26
     * @Param
     * @return
     */
    public static String getSetterName(Method setter){
        String name = setter.getName().substring(3);
        return Character.toLowerCase(name.charAt(0))+name.substring(1);
    }

}
