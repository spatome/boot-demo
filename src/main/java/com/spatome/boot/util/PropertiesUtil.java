///**   
// * Copyright © 2017 恒朋科技. All rights reserved.
// * 
// * @Title: ConfigUtil.java 
// * @Prject: api-dhtz
// * @Package: com.hengpeng.api.dhtz.util 
// * @Description: TODO
// * @author: zhangwei   
// * @date: 2017年7月26日 下午6:33:18 
// * @version: V1.0   
// */
//package com.hengpeng.util;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Properties;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
//
///**
// * @ClassName: PropertiesUtil
// * @Description: 读取配置文件数据
// * @author: zhangwei
// * @date: 2017年7月26日 下午6:33:18
// */
//public class PropertiesUtil extends PropertyPlaceholderConfigurer {
//
//	private static Map<String, String> properties = new HashMap<String, String>();
//
//	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
//			throws BeansException {
//		// cache the properties
///*		PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(DEFAULT_PLACEHOLDER_PREFIX,
//				DEFAULT_PLACEHOLDER_SUFFIX, DEFAULT_VALUE_SEPARATOR, false);*/
//		for (Entry<Object, Object> entry : props.entrySet()) {
//			String stringKey = String.valueOf(entry.getKey());
//			String stringValue = String.valueOf(entry.getValue());
////			stringValue = helper.replacePlaceholders(stringValue, props);
//			properties.put(stringKey, stringValue);
//		}
//		super.processProperties(beanFactoryToProcess, props);
//	}
//
//	public static Map<String, String> getProperties() {
//		return properties;
//	}
//
//	public static String getProperty(String key) {
//		return properties.get(key);
//	}
//
//}