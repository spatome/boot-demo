package com.spatome.boot.vo;

import java.io.Serializable;

/**
 * @api{POST}/user/login/ 用户登录请求
 * @apiGroup User
 * @apiVersion 0.1.0
 * @apiDescription 用户登录请求
 * 
 * @apiParam {String} userName 登录用户名
 * @apiParam {String} password 登录用户密码
 */
public class LoginRVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
