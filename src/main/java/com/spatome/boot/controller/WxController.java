package com.spatome.boot.controller;

import java.net.URLEncoder;
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
@RequestMapping("wx")
@Slf4j
public class WxController extends BaseController {

	@Autowired
	private MyConfig myConfig;
	
    @RequestMapping(value = "getOpenId", method = RequestMethod.GET)
    public void getOpenId(HttpServletRequest request, HttpServletResponse response, String userName) throws Exception {
		String scope = "snsapi_userinfo";
		@SuppressWarnings("deprecation")
		String redirectUri = URLEncoder.encode(this.getThisPath(request) + "getOpenId_");
		response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize"
				+ "?appid="+""
				+ "&redirect_uri="+redirectUri
				+ "&response_type=code"
				+ "&scope="+scope
				+ "&state="+userName
				+ "#wechat_redirect");
    }

	/**
	 * 只获取openId
	 */
	@RequestMapping(value = "/getOpenId_")
	@ResponseBody
	public Object toAuthorize_(HttpServletRequest request, HttpServletResponse response, String code) throws Exception
	{
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token"
				+ "?appid="+myConfig.getWxAppId()
				+ "&secret="+myConfig.getWxAppSecret()
				+ "&code="+code
				+ "&grant_type=authorization_code";

		String ret = HttpUtil.getInstance().httpGet(url);
		log.debug("toAuthorize_返回结果{}", ret);
		Map<String, Object> map = JUtil.toMap(ret);
		//Object userName = map.get("state");

		return map.get("openid");
	}

    public String getThisPath(HttpServletRequest request){
    	String requestUrl = request.getRequestURL().toString();
   		return requestUrl.substring(0, requestUrl.lastIndexOf("/"))+"/";
    }
}
