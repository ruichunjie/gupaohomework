package com.gupao.delegate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ChunJieRen
 * @Date:2019/3/16 18:03
 * @Description:
 */
public class DispatcherServlet extends HttpServlet {

    private List<Handler> handlerList = new ArrayList<Handler>(10);

    @Override
    public void init(){
        try{
            Class<?> orderControllerClass = OrderController.class;
            Handler h = new Handler();
            h.setController(orderControllerClass.newInstance());
            h.setMethod(orderControllerClass.getMethod("getOrderById"), String.class);
            h.setUrl("/getOrderById");
            handlerList.add(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    public void doDispatcher(HttpServletRequest request, HttpServletResponse response){
        String uri = request.getRequestURI();
        Handler handler = null;
        for(Handler h:handlerList){
            if(uri.equals(h.url)){
                handler =h;
                break;
            }
        }

        try {
            Object obj = handler.getMethod().invoke(handler.getController(),request.getParameter(""));
            response.getWriter().write(obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class Handler{
        private Object controller;
        private Method method;
        private String url;

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method, Class<String> stringClass) {
            this.method = method;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
