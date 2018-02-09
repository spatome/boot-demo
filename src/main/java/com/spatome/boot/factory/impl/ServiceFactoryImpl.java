package com.spatome.boot.factory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.spatome.boot.factory.ServiceFactory;
import com.spatome.boot.service.DemoService;

@Lazy
@Service
public class ServiceFactoryImpl implements ServiceFactory {

/*	@Autowired
	private ApplicationContext applicationContext;*/
	@Autowired
	private DemoService demoServiceImpl;

	@Override
	public DemoService getDemoService() {
		return demoServiceImpl;
	}

/*	@Override
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}*/

}
