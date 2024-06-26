package com.pinde.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextRegister implements ApplicationContextAware {
	private static Logger logger = LoggerFactory.getLogger(ApplicationContextRegister.class);

	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		SpringUtil.setApplicationContext(applicationContext);
		logger.debug("ApplicationContext registed");
	}

}
