package com.spatome.boot.source.lifecycle;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * SmartLifecycle的作用
 * 当Spring容器加载所有bean并完成初始化之后，会接着回调实现该接口的类中对应的方法（start()方法）
 * 逻辑太复杂，后续深入
 */
@Component
public class MyLifecycleBean implements SmartLifecycle {

	private boolean running = false;

    /**
     * 1. 我们主要在该方法中启动任务或者其他异步服务，比如开启MQ接收消息<br/>
     * 2. 当上下文被刷新（所有对象已被实例化和初始化之后）时，将调用该方法，默认生命周期处理器将检查每个SmartLifecycle对象的isAutoStartup()方法返回的布尔值。
     * 如果为“true”，则该方法会被调用，而不是等待显式调用自己的start()方法。
     */
	@Override
	public void start() {
		System.out.println("(refresh)MyLifecycleBean->start()");
		// 执行完其他业务后，可以修改 isRunning = true
		running = true;
	}

	@Override
	public void stop() {
		running = false;
		System.out.println("(refresh)MyLifecycleBean->stop()");
	}

    /**
     * 1. 只有该方法返回false时，start方法才会被执行。<br/>
     * 2. 只有该方法返回true时，stop(Runnable callback)或stop()方法才会被执行。
     */
	@Override
	public boolean isRunning() {
		return running;
	}

    /**
     * 如果工程中有多个实现接口SmartLifecycle的类，则这些类的start的执行顺序按getPhase方法返回值从小到大执行。<br/>
     * 例如：1比2先执行，-1比0先执行。 stop方法的执行顺序则相反，getPhase返回值较大类的stop方法先被调用，小的后被调用。
     */
	@Override
	public int getPhase() {
		return 0;
	}

	/*
	 * start()的开关，默认关闭false
	 */
	@Override
	public boolean isAutoStartup() {
		return true;
	}

    /**
     * SmartLifecycle子类的才有的方法，当isRunning方法返回true时，该方法才会被调用。
     */
	@Override
	public void stop(Runnable callback) {
		running = false;
		System.out.println("(refresh)MyLifecycleBean->stop(callback)");
		//callback.run();
	}


}
