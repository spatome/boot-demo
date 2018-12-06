package com.spatome.boot.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spatome.boot.service.TranService;
import com.spatome.boot.util.convert.JUtil;
import com.spatome.boot.vo.BaseVO;
import com.spatome.boot.vo.TestVO;

import lombok.extern.slf4j.Slf4j;

/** 
 * 测试
 */
@Slf4j
@Service
public class Tran99999ServiceImpl extends BaseService implements TranService {

	@Transactional
	@Override
	public Object execute(Map<String, String> dataMap, HttpServletRequest request, HttpServletResponse response) {
		BaseVO<Object> result = new BaseVO<Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		result.setBody(map);

		log.debug("获取参数");
		String enterpriseNo = dataMap.get("enterpriseNo");
		String enterpriseName = dataMap.get("enterpriseName");

		String isGet = dataMap.get("isGet");
		log.debug("检查参数");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("enterpriseNo", enterpriseNo);
		super.checkNotEmpty(paramMap);

		log.debug("===========================业务处理=========================");
		long start = System.currentTimeMillis();

		if(StringUtils.isNotBlank(isGet) && "TRUE".equals(isGet)){
			String ret = (String)this.get(enterpriseNo);
			//TestVO VO = JUtil.toBean(ret, TestVO.class);
			Map<String, String> VO = JUtil.toMap(ret);
			map.put("enterpriseName", VO);
		}else{
			TestVO VO = new TestVO();
			VO.setName(enterpriseName);
			VO.setPwd(enterpriseNo);
			this.set(enterpriseNo, 0, JUtil.toJson(VO));
		}

		long start1 = System.currentTimeMillis();
		System.out.println("==>"+(start1-start));

		return result;
	}

	public Object get(String key){
		return super.memcachedUtil.getMemcachedClient().get(key);
	}

	public void set(String key, int exp, Object value){
		super.memcachedUtil.getMemcachedClient().set(key, exp, value);
	}
}
