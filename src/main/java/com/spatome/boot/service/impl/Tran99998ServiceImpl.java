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
public class Tran99998ServiceImpl extends BaseService implements TranService {

	private static List<Long> prizeIdList = new ArrayList<Long>();
	static{
		prizeIdList.add(1L);
		prizeIdList.add(2L);
		prizeIdList.add(3L);
	}
	
	@Override
	public Object execute(Map<String, String> dataMap, HttpServletRequest request, HttpServletResponse response) {
		BaseVO<Object> result = new BaseVO<Object>();

		log.debug("获取参数");
		String oneKey = dataMap.get("oneKey");
		Long activityId = Long.valueOf(dataMap.get("activityId"));
		Integer count = Integer.valueOf(dataMap.get("count"));
		log.debug("检查参数");
		Map<String, String> paramMap = new HashMap<String, String>();
		super.checkNotEmpty(paramMap);

		Date date = new Date();
		count=count==null?1:count;

		log.debug("===========================业务处理=========================");
		Long prizeId = prizeIdList.get(RandomUtils.nextInt(3));

		long start = System.currentTimeMillis();

		Prize prize = daoFactory.getPrizeMapper().selectByPrimaryKey(prizeId);
		
		long start1 = System.currentTimeMillis();
		
		Activity activity = daoFactory.getActivityMapper().selectByPrimaryKey(activityId);

		long start2 = System.currentTimeMillis();
		
		for (int i = 0; i < count; i++) {
			DrawLog newDrawLog = new DrawLog();
			newDrawLog.setCreateTime(date);
			newDrawLog.setCreateDate(date);
			newDrawLog.setOneKey(oneKey);
			newDrawLog.setPrizeId(prizeId);
			newDrawLog.setPrizeEnterpriseNo(prize.getEnterpriseNo());
			newDrawLog.setActivityId(activityId);
			newDrawLog.setActivityEnterpriseNo(activity.getEnterpriseNo());
			insert(newDrawLog);
		}

		long start3 = System.currentTimeMillis();
		System.out.println("==>S1:"+(start1-start) +"	S2:"+(start2-start1) +"	S3:"+(start3-start2));

		return result;
	}

	@Transactional
	public void insert(DrawLog record){
		daoFactory.getDrawLogMapper().insertSelective(record);
	}
}
