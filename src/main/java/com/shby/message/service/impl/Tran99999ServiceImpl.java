package com.shby.message.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.shby.message.service.BaseService;
import com.shby.message.service.TranService;
import com.shby.message.vo.BaseVO;

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

		return result;
	}
}
