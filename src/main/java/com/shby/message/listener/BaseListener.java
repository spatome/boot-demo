package com.shby.message.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.shby.message.factory.ServiceFactory;

public abstract class BaseListener {

	@Autowired
	protected ServiceFactory serviceFactory;
	
}
