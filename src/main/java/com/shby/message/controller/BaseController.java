package com.shby.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shby.message.common.exception.SException;
import com.shby.message.factory.ServiceFactory;
import com.shby.message.vo.BaseVO;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class BaseController {

	@Autowired
	protected ServiceFactory serviceFactory;

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public BaseVO<Object> handlerException(Exception ex){
		log.error("service未知异常:", ex);
		return new BaseVO<Object>("9999", ex.getMessage());
	}

	@ExceptionHandler(SException.class)
	@ResponseBody
	public BaseVO<Object> sException(SException ex){
		log.error("service自定义异常:", ex);
		return new BaseVO<Object>(ex.getCode(), ex.getMessage());
	}

}
