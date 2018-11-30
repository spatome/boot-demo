package com.spatome.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spatome.boot.common.exception.SException;
import com.spatome.boot.factory.DaoFactory;
import com.spatome.boot.factory.ServiceFactory;
import com.spatome.boot.vo.BaseVO;

@ControllerAdvice
public class BaseController {
	protected final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	protected DaoFactory daoFactory;
	@Autowired
	protected ServiceFactory serviceFactory;
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public BaseVO<Object> handlerException(Exception ex){
		LOGGER.error("service未知异常:", ex);
		return new BaseVO<Object>("9999", "内部异常，清稍后重试");
	}

	@ExceptionHandler(SException.class)
	@ResponseBody
	public BaseVO<Object> sException(SException ex){
		LOGGER.error("service自定义异常:", ex);
		return new BaseVO<Object>(ex.getCode(), ex.getMessage());
	}
}
