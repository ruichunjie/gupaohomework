package com.rcj.imitation.spring.framework.webmvc.servlet;

import java.io.File;
import java.util.Locale;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/17 19:26
 * @Description:
 */
public class GFViewResolver {

    private final String DEFAULT_TEMPLATE_SUFFIX = ".html";
    private File templateRootDir;
    private String viewName;

    public GFViewResolver(String templateRoot){
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        this.templateRootDir = new File(templateRootPath);
    }

    public GFView resolveViewName(String viewName, Locale locale) throws Exception{
        this.viewName = viewName;
        if(null == viewName || "".equals(viewName.trim())){ return null;}
        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFIX) ? viewName : (viewName +
                DEFAULT_TEMPLATE_SUFFIX);
        File templateFile = new File((templateRootDir.getPath() + "/" + viewName).replaceAll("/+",
                "/"));
        return new GFView(templateFile);
    }
}
