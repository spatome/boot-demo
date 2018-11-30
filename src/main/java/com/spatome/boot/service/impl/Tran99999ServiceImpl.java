package com.spatome.boot.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spatome.boot.entity.basic.SysUser;
import com.spatome.boot.entity.zjd.Enterprise;
import com.spatome.boot.service.TranService;
import com.spatome.boot.vo.BaseVO;

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
		log.debug("检查参数");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("enterpriseNo", enterpriseNo);
		super.checkNotEmpty(paramMap);

		log.debug("===========================业务处理=========================");
		Enterprise updateEnterprise = new Enterprise();
		updateEnterprise.setId(1L);
		updateEnterprise.setEnterpriseName(enterpriseName);
		daoFactory.getEnterpriseMapper().updateByPrimaryKeySelective(updateEnterprise);

		SysUser updateSysUser = new SysUser();
		updateSysUser.setId(1L);
		updateSysUser.setUserName(enterpriseName);
		daoFactory.getSysUserMapper().updateByPrimaryKeySelective(updateSysUser);

		return result;
	}
}
