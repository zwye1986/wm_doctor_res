package com.pinde.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import java.util.Locale;

/**
 * SPRING工具实现类
 * 
 * @author shijian
 * @create 2014.04.2
 * 
 */
public class SpringUtil{
	private static Logger logger = LoggerFactory.getLogger(ApplicationContextRegister.class);

	private static ApplicationContext applicationContext;

	public static void setApplicationContext(ApplicationContext applicationContext) {
		synchronized (SpringUtil.class) {
			logger.debug("setApplicationContext, notifyAll");
			SpringUtil.applicationContext = applicationContext;
			SpringUtil.class.notifyAll();
		}
	}

	public static ApplicationContext getApplicationContext() {
		synchronized (SpringUtil.class) {
			while (applicationContext == null) {
				try {
			    	logger.debug("getApplicationContext, wait...");
			    	SpringUtil.class.wait(60000);
		    		if (applicationContext == null) {
		    			logger.warn("Have been waiting for ApplicationContext to be set for 1 minute", new Exception());
		    		}
				} catch (InterruptedException ex) {
					logger.debug("getApplicationContext, wait interrupted");
				}
			}
			return applicationContext;
		  }
	}

	/**
	 * 获取spring管理的Bean
	 * 
	 * @param name
	 * @return Object
	 */
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	public static <T> T getBean(Class<T> cls) {
		return getApplicationContext().getBean(cls);
	}

	public static String getMessage(String code) {
		return getApplicationContext().getMessage(code, null, Locale.CHINA);
	}

	public static Resource getResource(String name) {
		return getApplicationContext().getResource(name);
	}

}
