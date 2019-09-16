package com.shby.message.oapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultBO {

	private boolean isSend;
	private String code;
	private String message;

	public void setCodeMessage(boolean isSend, String code, String message){
		this.isSend = isSend;
		this.code = code;
		this.message = message;
	};
}
