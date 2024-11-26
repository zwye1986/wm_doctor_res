package com.pinde.app.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by jiayf on 2016/4/1.
 */
public class BaseInitConfig implements ServletContextListener {
    private final static Logger logger = LoggerFactory.getLogger(BaseInitConfig.class);

    private static ServletContextEvent servletContext;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContext = servletContextEvent;
        toLoad(servletContextEvent);
    }

    private static void toLoad(ServletContextEvent servletContextEvent){
        //加载表单配置
        //logger.debug("开始加载表单配置...");
        GlobalUtil.setCfg();
        //logger.debug("表单配置加载完成...");
    }

    public static void reload(){
        toLoad(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}
