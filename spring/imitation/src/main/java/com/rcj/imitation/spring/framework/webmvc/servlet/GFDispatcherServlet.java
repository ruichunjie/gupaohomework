package com.rcj.imitation.spring.framework.webmvc.servlet;

import com.rcj.imitation.spring.framework.annotation.GFController;
import com.rcj.imitation.spring.framework.annotation.GFRequestMapping;
import com.rcj.imitation.spring.framework.context.GFApplicationContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/16 13:03
 * @Description: 启动入口
 */
@Slf4j
public class GFDispatcherServlet extends HttpServlet {

    private final String LOCATION="contextConfigLocation";
    private List<GFHandlerMapping> handlerMappings = new ArrayList<>();
    private Map<GFHandlerMapping, GFHandlerAdapter> handlerAdapters = new HashMap<>();
    private List<GFViewResolver> viewResolvers = new ArrayList<>();
    private GFApplicationContext context;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatcher(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws Exception{

        GFHandlerMapping handler = getHandler(req);
        if(handler == null){
            processDispatchResult(req,resp,new GFModelAndView("404"));
            return;
        }
        GFHandlerAdapter ha = getHandlerAdapter(handler);
        GFModelAndView mv = ha.handle(req, resp, handler);
        processDispatchResult(req,resp, mv);
    }

    private GFHandlerMapping getHandler(HttpServletRequest req) {
        if(this.handlerMappings.isEmpty()){return null;}
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath,"").replaceAll("/+","");
        for(GFHandlerMapping handler: this.handlerMappings){
            Matcher matcher = handler.getPattern().matcher(url);
            if(!matcher.matches()){continue;}
            return handler;
        }
        return  null;
    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, GFModelAndView mv)
            throws Exception{
        if(null == mv){return;}
        if(this.viewResolvers.isEmpty()){return;}
        for(GFViewResolver viewResolver:this.viewResolvers){
            GFView view = viewResolver.resolveViewName(mv.getViewName(),null);
            if(view != null){
                view.render(mv.getModel(),req,resp);
                return;
            }
        }

    }

    private GFHandlerAdapter getHandlerAdapter(GFHandlerMapping handler) {
        if(this.handlerAdapters.isEmpty()){return  null;}
        GFHandlerAdapter ha = this.handlerAdapters.get(handler);
        if(ha.supports(handler)){
            return ha;
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = new GFApplicationContext(config.getInitParameter(LOCATION));
        initStrategies(context);
    }

    /**
     * @Author ChunJie Ren
     * @Description 初始化策略
     * @Date 11:39 AM 2019/4/18
     * @Param
     * @return
     */
    protected void initStrategies(GFApplicationContext context){
        /**多文件上传解析*/
        initMultipartResolver(context);
        /**本地化解析*/
        initLocaleResolver(context);
        /**主题解析*/
        initThemeResolver(context);
        /**初始化映射器*/
        initHandlerMappings(context);
        /**初始化适配器*/
        initHandlerAdapters(context);
        /**初始化异常处理*/
        initHandlerExceptionResolvers(context);
        /**直接解析请求到视图名*/
        initRequestToViewNameTranslator(context);
        /**初始化视图*/
        initViewResolvers(context);
        /**初始化缓存映射处理器*/
        initFlashMapManager(context);
    }

    /**
     * @Author ChunJie Ren
     * @Description 文件上传解析
     * @Date 11:22 AM 2019/4/18
     * @Param
     * @return
     */
    private void initMultipartResolver(GFApplicationContext context) {}

    /**
     * @Author ChunJie Ren
     * @Description 本地化解析
     * @Date 11:23 AM 2019/4/18
     * @Param
     * @return
     */
    private void initLocaleResolver(GFApplicationContext context) {}

    /**
     * @Author ChunJie Ren
     * @Description 主题解析
     * @Date 11:26 AM 2019/4/18
     * @Param
     * @return
     */
    private void initThemeResolver(GFApplicationContext context) {}

    /**
     * @Author ChunJie Ren
     * @Description 初始化映射器
     * @Date 11:28 AM 2019/4/18
     * @Param
     * @return
     */
    private void initHandlerMappings(GFApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();
        try{
            for (String beanName:beanNames) {
                Object controller = context.getBean(beanName);
                Class clazz = controller.getClass();
                if(!clazz.isAnnotationPresent(GFController.class)){
                    continue;
                }
                String baseUrl = "";
                if(clazz.isAnnotationPresent(GFRequestMapping.class)){
                    GFRequestMapping requestMapping = (GFRequestMapping) clazz.getAnnotation(GFRequestMapping.class);
                    baseUrl = requestMapping.value();
                }
                for (Method method:clazz.getMethods()){
                    if(!method.isAnnotationPresent(GFRequestMapping.class)){
                        continue;
                    }
                    GFRequestMapping requestMapping = method.getAnnotation(GFRequestMapping.class);
                    String regex = ("/" + baseUrl + requestMapping.value()
                            .replaceAll("\\*", ".*")).replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);
                    this.handlerMappings.add(new GFHandlerMapping(pattern,controller,method));
                    log.info("Mapping"+regex+","+method);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @Author ChunJie Ren
     * @Description 初始化适配器
     * @Date 11:30 AM 2019/4/18
     * @Param
     * @return
     */
    private void initHandlerAdapters(GFApplicationContext context) {
        for(GFHandlerMapping handlerMapping : this.handlerMappings){
            this.handlerAdapters.put(handlerMapping,new GFHandlerAdapter());
        }
    }

    /**
     * @Author ChunJie Ren
     * @Description 初始化异常处理
     * @Date 11:31 AM 2019/4/18
     * @Param
     * @return
     */
    private void initHandlerExceptionResolvers(GFApplicationContext context) {
    }

    /**
     * @Author ChunJie Ren
     * @Description 直接解析请求到视图名
     * @Date 11:32 AM 2019/4/18
     * @Param
     * @return
     */
    private void initRequestToViewNameTranslator(GFApplicationContext context) {}

    /**
     * @Author ChunJie Ren
     * @Description 初始化视图处理
     * @Date 11:34 AM 2019/4/18
     * @Param
     * @return
     */
    private void initViewResolvers(GFApplicationContext context) {
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getResource(templateRoot).getFile();
        File templateRootDir = new File(templateRootPath);
        for (File template : templateRootDir.listFiles()) {
            this.viewResolvers.add(new GFViewResolver(templateRoot));
        }
    }

    /**
     * @Author ChunJie Ren
     * @Description 初始化缓存映射处理器
     * @Date 11:35 AM 2019/4/18
     * @Param
     * @return
     */
    private void initFlashMapManager(GFApplicationContext context) {
    }
}
