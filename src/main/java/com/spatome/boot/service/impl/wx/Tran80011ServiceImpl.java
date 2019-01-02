package com.spatome.boot.service.impl.wx;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spatome.boot.controller.BaseController.BaseVO;
import com.spatome.boot.service.BaseService;
import com.spatome.boot.service.TranService;
import com.spatome.boot.util.HttpUtil;
import com.spatome.boot.util.convert.JUtil;

import lombok.extern.slf4j.Slf4j;

/** 
 * 判断是否关注
 * openId:ojAM11gpuuzopQNmpp_3cRQGpVTY
 */
@Slf4j
@Service
public class Tran80011ServiceImpl extends BaseService implements TranService {

	public final static String WX_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	@Autowired
	private Tran80010ServiceImpl tran80010ServiceImpl;
	
	@SuppressWarnings("unchecked")
	@Override
	public Object execute(Map<String, String> dataMap, HttpServletRequest request, HttpServletResponse response) {
		BaseVO<Object> result = new BaseVO<Object>();

		log.debug("获取参数");
		String openId = dataMap.get("openId");
		log.debug("检查参数");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("openId", openId);
		super.checkNotEmpty(paramMap);

		log.debug("===========================业务处理=========================");
		BaseVO<Object> ret = (BaseVO<Object>)tran80010ServiceImpl.execute(null, null, null);
		String url = WX_URL.replace("ACCESS_TOKEN", String.valueOf(ret.getBody())).replace("OPENID", openId);
		String ret1 = HttpUtil.getInstance().httpGet(url);
		System.out.println("ret:"+ret1);
		Map<String, Object> map = JUtil.toMap(ret1);
		//https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839
		if("1".equals(String.valueOf(map.get("subscribe")))){
			System.out.println("已关注");
		}

		result.setBody(ret1);

		return result;
	}
}
