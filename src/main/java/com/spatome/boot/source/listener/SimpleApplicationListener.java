package com.spatome.boot.source.listener;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.context.support.RequestHandledEvent;

import com.spatome.boot.source.event.TestBean;
import com.spatome.boot.source.event.TestBeanUpdateEvent;

/*只要是继承了ApplicationEvent,都可以监听*/

public class SimpleApplicationListener implements ApplicationListener<ApplicationEvent> {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof ApplicationStartedEvent){
			System.out.println("==>application事件监听->ApplicationStartedEvent");
		}else if(event instanceof ApplicationPreparedEvent){
			System.out.println("==>application事件监听->ApplicationPreparedEvent");
		}else if(event instanceof RequestHandledEvent){
			System.out.println("==>application事件监听->RequestHandledEvent");
		}else if(event instanceof TestBeanUpdateEvent){
			System.out.println("==>application事件监听->TestBeanUpdateEvent");
			TestBean testBean = ((TestBeanUpdateEvent) event).getDataBean();
			System.out.println("==>处理TestBeanUpdateEvent:"+testBean.getName());
		}
		
	}

}
