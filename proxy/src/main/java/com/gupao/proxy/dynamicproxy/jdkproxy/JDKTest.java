package com.gupao.proxy.dynamicproxy.jdkproxy;

import com.gupao.proxy.HelloWorld;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Method;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/13 16:19
 * @Description:
 */
public class JDKTest {

    public static void main(String[] args) throws Exception {
        Object o = new JDKProxy().getInstance(new HelloWorldImpl());
        Method method = o.getClass().getMethod("sayHelloWorld");
        method.invoke(o);

        byte [] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{HelloWorld.class});
        FileOutputStream os = new FileOutputStream("/Users/renchunjie/Desktop/咕泡/gupao/proxy/src/main/resources/$Proxy0.class");
        os.write(bytes);
        os.close();
    }

}
