package com.spatome.boot.vo;

import java.io.Serializable;

public class BaseVO<T> implements Serializable {
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