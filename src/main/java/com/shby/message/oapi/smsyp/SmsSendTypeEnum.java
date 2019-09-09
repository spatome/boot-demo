package com.shby.message.oapi.smsyp;

public enum SmsSendTypeEnum
{
	/**
	 * 系统通道
	 */
	SYSTEM("系统通道"),
	/**
	 * 营销通道
	 */
	MARKETING("营销通道");

	private String text;

	SmsSendTypeEnum(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

}
