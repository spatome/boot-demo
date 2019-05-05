package com.spatome.boot.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

import com.spatome.boot.controller.BaseController.BaseVO;
import com.spatome.boot.service.BaseService;
import com.spatome.boot.service.TranService;

import lombok.extern.slf4j.Slf4j;

/** 
 * 测试
 * rabbitmq默认direct
 * 在此类型下创建的Queue有一个默认的routing key，这个routing key一般同Queue同名
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
		CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
		super.rabbitTemplate.convertAndSend("fanout.test", "", (Object)userName, correlationData);

		result.setBody(":"+userName);

		return result;
	}
}
