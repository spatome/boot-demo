package com.spatome.boot.source.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

/**
 * 自定义修改测试事件
 * 
 * @author zhangwei
 */
@Getter
public class TestBeanUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	private TestBean dataBean;
	
	public TestBeanUpdateEvent(Object source, TestBean dataBean) {
		super(source);

		this.dataBean = dataBean;
	}

}
