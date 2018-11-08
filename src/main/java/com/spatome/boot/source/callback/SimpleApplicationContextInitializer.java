package com.spatome.boot.source.callback;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import com.spatome.boot.vo.SSVO;

public class SimpleApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		SSVO ssVO = new SSVO();
		ssVO.setValue("接口回调：自定义上下文,可以在这初始化类并注入");
		System.out.println("==>"+ssVO.getValue());
		applicationContext.getBeanFactory().registerSingleton("ssVO", ssVO);
	}

}
