package com.spatome.boot.factory;

import com.spatome.boot.service.DemoService;

public interface ServiceFactory {

	//public ApplicationContext getApplicationContext();
	public DemoService getDemoService();

}
