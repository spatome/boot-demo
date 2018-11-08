package com.spatome.boot.source.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.spatome.boot.source.event.TestBean;
import com.spatome.boot.source.event.TestBeanUpdateEvent;

/**
 * @author zhangwei
 * 
 * SimpleApplicationListener类中处理事件更原始，好理解
 * 此处处理不好理解，但方便开发
 */
@Component
@EnableAsync
public class MyEventHandler {

	@EventListener
	@Async
	public void testBeanUpdateListener(TestBeanUpdateEvent event){
		System.out.println("==>方便的处理event(异步10秒)");

		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
		}

		TestBean testBean = event.getDataBean();
		System.out.println("==>方便的处理事件(异步10秒)结果->testBeanUpdateEvent:"+testBean.getName());
	};

}
