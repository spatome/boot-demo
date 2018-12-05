package com.spatome.boot.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class TestVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String pwd;
}
