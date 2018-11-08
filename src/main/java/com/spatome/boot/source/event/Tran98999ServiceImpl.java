package com.spatome.boot.source.event;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.spatome.boot.service.TranService;
import com.spatome.boot.service.impl.BaseService;
import com.spatome.boot.util.spring.SpringUtil;
import com.spatome.boot.vo.BaseVO;

import lombok.extern.slf4j.Slf4j;

/** 
 * 修改TestBean后执行保存事件测试
 */
@Slf4j
@Service
public class Tran98999ServiceImpl extends BaseService implements TranService {

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
		TestBean testBean = new TestBean();
		testBean.setName(enterpriseNo);

		System.out.println("==>修改之前发布事件TestBeanUpdateEvent");
		SpringUtil.getApplicationContext().publishEvent(new TestBeanUpdateEvent(this, testBean));
		this.updateTestBean(testBean);

		return result;
	}

	public void updateTestBean(TestBean testBean){
		System.out.println("==>数据库修改testBean,用户名:"+testBean.getName());
	}
}
