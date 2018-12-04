package com.spatome.boot.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spatome.boot.entity.Activity;
import com.spatome.boot.entity.DrawLog;
import com.spatome.boot.entity.Prize;
import com.spatome.boot.service.TranService;
import com.spatome.boot.vo.BaseVO;

import lombok.extern.slf4j.Slf4j;

/** 
 * 测试
 */
@Slf4j
@Service
public class Tran29000ServiceImpl extends BaseService implements TranService {

	private static List<Long> prizeIdList = new ArrayList<Long>();
	static{
		prizeIdList.add(1L);
		prizeIdList.add(2L);
		prizeIdList.add(3L);
	}

	@Transactional
	@Override
	public Object execute(Map<String, String> dataMap, HttpServletRequest request, HttpServletResponse response) {
		BaseVO<Object> result = new BaseVO<Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		result.setBody(map);

		log.debug("获取参数");
		String oneKey = dataMap.get("oneKey");
		Long activityId = Long.valueOf(dataMap.get("activityId"));
		log.debug("检查参数");
		Map<String, String> paramMap = new HashMap<String, String>();
		super.checkNotEmpty(paramMap);

		Date date = new Date();

		log.debug("===========================业务处理=========================");
		Long prizeId = prizeIdList.get(RandomUtils.nextInt(3));
		Prize prize = daoFactory.getPrizeMapper().selectByPrimaryKey(prizeId);
		Activity activity = daoFactory.getActivityMapper().selectByPrimaryKey(activityId);

		DrawLog newDrawLog = new DrawLog();
		newDrawLog.setCreateTime(date);
		newDrawLog.setCreateDate(date);
		newDrawLog.setOneKey(oneKey);
		newDrawLog.setPrizeId(prizeId);
		newDrawLog.setPrizeEnterpriseNo(prize.getEnterpriseNo());
		newDrawLog.setActivityId(activityId);
		newDrawLog.setActivityEnterpriseNo(activity.getEnterpriseNo());
		daoFactory.getDrawLogMapper().insertSelective(newDrawLog);

		return result;
	}
}
