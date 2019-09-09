package com.shby.message.common.enums;

public enum ChannelTypeEnum {

	PHONE("PHONE"), WX("WX"), MAIL("MAIL"), APP("APP"), SITE("SITE");

	private String text;

	ChannelTypeEnum(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
