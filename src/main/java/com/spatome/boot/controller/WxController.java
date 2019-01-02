package com.spatome.boot.controller;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spatome.boot.common.config.MyConfig;
import com.spatome.boot.util.HttpUtil;
import com.spatome.boot.util.convert.JUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/wx")
@Slf4j
public class WxController extends BaseController {

	@Autowired
	private MyConfig myConfig;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public void wxInitValid(HttpServletRequest request, HttpServletResponse response,
			String signature,	//微信加密签名
			String timestamp, 	//时间戳
			String nonce, 		//随机数
			String echostr		//随机字符串
			) throws Exception {
		log.info("==>微信验证来了...");
		
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			System.out.println(name+":"+request.getParameter(name));
		}

		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(echostr);
		} finally {
			if(out!=null){
				out.close();
			}
		}
	}

	/**
	 * 只获取openId
	 */
	@RequestMapping(value = "/getOpenId")
	@ResponseBody
	public Object getOpenId(HttpServletRequest request, HttpServletResponse response, String code) throws Exception
	{
		BaseVO<Object> result = new BaseVO<Object>();

		System.out.println("2");
		
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token"
				+ "?appid="+myConfig.getWxAppId()
				+ "&secret="+myConfig.getWxAppSecret()
				+ "&code="+code
				+ "&grant_type=authorization_code";

		String ret = HttpUtil.getInstance().httpGet(url);
		log.info("toAuthorize_返回结果{}", ret);
		Map<String, Object> map = JUtil.toMap(ret);
		//mapVO.put("state", map.get("state"));
		result.setBody(map.get("openid"));

		return result;
	}

}
