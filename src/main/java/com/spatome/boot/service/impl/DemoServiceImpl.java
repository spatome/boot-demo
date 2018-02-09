package com.spatome.boot.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.spatome.boot.common.exception.SException;
import com.spatome.boot.service.BaseService;
import com.spatome.boot.service.DemoService;
import com.spatome.boot.vo.LoginRVO;
import com.spatome.boot.vo.LoginSVO;

@Service
public class DemoServiceImpl extends BaseService implements DemoService {

	@Override
	public LoginSVO login(LoginRVO data) {
		LoginSVO result = new LoginSVO();

		try {
			if(data.getUserName().equals(data.getPassword())){
				result.setId(1l);
				result.setToken(UUID.randomUUID().toString());
			}else{
				throw new SException("9998", "用户名和密码不匹配");
			}
		} catch (SException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SException("9999", "login");
		}

		return result;
	}
}
