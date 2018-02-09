package com.spatome.boot.vo;

import java.io.Serializable;

/**
 * 只有一个字符串值
 */
public class SSVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String value;

	public SSVO() {
	}

	public SSVO(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
