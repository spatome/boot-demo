package com.spatome.boot.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spatome.boot.common.exception.SException;
import com.spatome.boot.factory.ServiceFactory;

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
