package com.spatome.boot.service;

import com.spatome.boot.vo.LoginRVO;
import com.spatome.boot.vo.LoginSVO;

public interface DemoService {

	public LoginSVO login(LoginRVO data);

}
