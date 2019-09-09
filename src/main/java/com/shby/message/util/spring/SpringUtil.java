package com.shby.message.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/** 
 * 上下文句柄
 */
@Component
@Slf4j
public final class SpringUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringUtil.applicationContext == null) {
			SpringUtil.applicationContext = applicationContext;
			log.info("创建SpringUtil成功!");
		}
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
