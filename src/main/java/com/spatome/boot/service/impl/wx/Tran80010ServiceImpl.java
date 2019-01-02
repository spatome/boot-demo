package com.spatome.boot.service.impl.wx;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.spatome.boot.common.constants.RedisConstants;
import com.spatome.boot.controller.BaseController.BaseVO;
import com.spatome.boot.service.BaseService;
import com.spatome.boot.service.TranService;
import com.spatome.boot.util.HttpUtil;
import com.spatome.boot.util.convert.JUtil;

import lombok.extern.slf4j.Slf4j;

/** 
 * 获取access_token
 */
@Slf4j
@Service
public class Tran80010ServiceImpl extends BaseService implements TranService {

	@Override
	public Object execute(Map<String, String> dataMap, HttpServletRequest request, HttpServletResponse response) {
		BaseVO<Object> result = new BaseVO<Object>();

		log.debug("获取参数");
		log.debug("检查参数");
		Map<String, String> paramMap = new HashMap<String, String>();
		//paramMap.put("userName", userName);
		super.checkNotEmpty(paramMap);

		log.debug("===========================业务处理=========================");
		String key = RedisConstants.WX_ACCESS_TOKEN+super.myConfig.getWxAppId();

		String accessToken = super.stringRedisTemplate.boundValueOps(key).get();

		if(StringUtils.isBlank(accessToken)){
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET"
					.replace("APPID", super.myConfig.getWxAppId()).replace("APPSECRET", super.myConfig.getWxAppSecret());
			String ret = HttpUtil.getInstance().httpGet(url);
			log.info("getToken结果:"+ret);
			Map<String, Object> map = JUtil.toMap(ret);
			accessToken = (String)map.get("access_token");
			super.stringRedisTemplate.boundValueOps(key).set(accessToken, 100L, TimeUnit.MINUTES);
		}

		result.setBody(accessToken);

		return result;
	}
}
