package com.spatome.boot.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.spatome.boot.controller.BaseController.BaseVO;
import com.spatome.boot.netty.NettyClient;
import com.spatome.boot.service.BaseService;
import com.spatome.boot.service.TranService;

import lombok.extern.slf4j.Slf4j;

/** 
 * 测试
 */
@Slf4j
@Service
public class Tran99999ServiceImpl extends BaseService implements TranService {

	@Override
	public Object execute(Map<String, String> dataMap, HttpServletRequest request, HttpServletResponse response) {
		BaseVO<Object> result = new BaseVO<Object>();

		log.debug("获取参数");
		String userName = dataMap.get("userName");
		log.debug("检查参数");
		Map<String, String> paramMap = new HashMap<String, String>();
		//paramMap.put("userName", userName);
		super.checkNotEmpty(paramMap);

		log.debug("===========================业务处理=========================");
		NettyClient.getInstance().send(userName+"\n");

		return result;
	}
}
