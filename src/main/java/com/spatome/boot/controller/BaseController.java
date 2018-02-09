package com.spatome.boot.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spatome.boot.common.config.ConfProperties;
import com.spatome.boot.common.exception.SException;
import com.spatome.boot.factory.ServiceFactory;

@ControllerAdvice
public class BaseController {
	protected final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	protected ConfProperties confProperties;
	
	@Autowired
	protected ServiceFactory serviceFactory;

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public BaseVO<String> handlerException(Exception ex){
		LOGGER.error("service未知异常:", ex);
		return new BaseVO<String>("9999", ex.getMessage());
	}

	@ExceptionHandler(SException.class)
	@ResponseBody
	public BaseVO<String> sException(SException ex){
		LOGGER.error("service自定义异常:", ex);
		return new BaseVO<String>(ex.getCode(), ex.getMessage());
	}
	
	public static class BaseVO<T> implements Serializable {
		private static final long serialVersionUID = 1L;

		private String code;
		private String message;
		private T body;

		public BaseVO() {
			this.code="0000";
			this.message = "操作成功";
		}
		
		public BaseVO(String code, String message) {
			this.code = code;
			this.message = message;
		}
		
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public T getBody() {
			return body;
		}

		public void setBody(T body) {
			this.body = body;
		}
	}
}
