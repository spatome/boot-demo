package com.spatome.boot.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.spatome.boot.service.TranService;
import com.spatome.boot.vo.BaseVO;

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
		String enterpriseNo = dataMap.get("enterpriseNo");
		log.debug("检查参数");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("enterpriseNo", enterpriseNo);
		super.checkNotEmpty(paramMap);

		log.debug("===========================业务处理=========================");

		return result;
	}
}
