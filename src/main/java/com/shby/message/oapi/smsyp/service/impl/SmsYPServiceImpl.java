package com.shby.message.oapi.smsyp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shby.message.oapi.smsyp.HttpRequestProxy;
import com.shby.message.oapi.smsyp.SmsDto;
import com.shby.message.oapi.smsyp.SmsSendTypeEnum;
import com.shby.message.oapi.smsyp.service.SmsBaseService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * TODO 云片网络短信接口
 */
@Service
@Slf4j
public class SmsYPServiceImpl implements SmsBaseService {
	/**
	 * 内容匹配方式发送
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean sendMessage(SmsDto smdDto, SmsSendTypeEnum type, String phone, String content) {
		boolean result = false;

		String smsPwd = SmsSendTypeEnum.SYSTEM == type ? smdDto.getSmsportPwdSystem() : smdDto.getSmsportPwd();
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("apikey", smsPwd);
		parameters.put("mobile", phone);
		parameters.put("text", content);
		String ret = HttpRequestProxy.doPost(smdDto.getSmsportUrl(), parameters, "UTF-8");

		ObjectMapper OM = new ObjectMapper();
		try {
			Map<String, Object> retMap = OM.readValue(ret, Map.class);
			Integer code = (Integer) retMap.get("code");
			if (code.intValue() == 0) {
				log.debug("返回JSON:" + ret);
				result = true;
			} else {
				log.error("Remote service invoke error[" + ret + "]");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void release() {
	}
}
