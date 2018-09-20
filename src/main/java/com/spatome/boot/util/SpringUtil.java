package com.spatome.boot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/** 
 */
@Component
public final class SpringUtil implements ApplicationContextAware {
	private final static Logger LOGGER = LoggerFactory.getLogger(SpringUtil.class);

	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringUtil.applicationContext == null) {
			SpringUtil.applicationContext = applicationContext;
			LOGGER.info("创建SpringUtil成功!");
		}
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
