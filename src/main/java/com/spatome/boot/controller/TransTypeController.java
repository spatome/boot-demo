package com.spatome.boot.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spatome.boot.service.TranService;
import com.spatome.boot.util.SpringUtil;

@RestController
@RequestMapping(value = "/process")
public class TransTypeController extends BaseController {

	@RequestMapping(value = "{transType}", method = RequestMethod.POST)
	public Object process(@PathVariable String transType, @RequestParam Map<String, String> dataMap,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Object result = null;

		try {
			Object bean = SpringUtil.getApplicationContext().getBean("tran" + transType + "ServiceImpl");
			result = ((TranService) bean).execute(dataMap, request, response);
		} catch (Exception e) {
			LOGGER.error("transType{}data{}", transType, dataMap);
			throw e;
		}

		return result;
	}

}
