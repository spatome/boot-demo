package com.spatome.boot.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spatome.boot.common.config.redis.RedissonLockUtil;
import com.spatome.boot.entity.Account;
import com.spatome.boot.service.TranService;
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

		Long id = Long.valueOf(dataMap.get("id"));
		String name = dataMap.get("name");
		log.debug("检查参数");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("enterpriseNo", enterpriseNo);
		super.checkNotEmpty(paramMap);

		log.debug("===========================业务处理=========================");
		//this.test(id, name);
		//this.test1(id, name);
		//this.test2(id);
		//String ret = super.stringRedisTemplate.opsForValue().get("zw:string:"+id);
		//map.put("zw:string:"+id, ret);

/*		TestVO ret1 = (TestVO)super.redisTemplate.opsForValue().get("zw:bean:"+id);
		map.put("zw:bean:"+id, ret1);*/

/*		Object ret2 = super.redisTemplate.opsForValue().get("zw:bean:long:"+id);
		if(ret2==null){
			map.put("zw:bean:long:"+id, "isNull");
		}else{
			map.put("zw:bean:long:"+id, ret2);
		}*/

		//==============================================================================
		String key = "lock:activity:account:id:"+id;
		if(RedissonLockUtil.tryLock(key, 3000L)){
			try{
				Account account = daoFactory.getAccountMapper().selectByPrimaryKey(id);
				Account updateAccount = new Account();
				updateAccount.setId(account.getId());
				updateAccount.setDepositAmount(account.getDepositAmount().add(BigDecimal.ONE));
				daoFactory.getAccountMapper().updateByPrimaryKeySelective(updateAccount);
				System.out.println("99999修改");
				try {
					Thread.sleep(10 * 1000L);
				} catch (InterruptedException e) {
				}
			}finally{
				RedissonLockUtil.unlock(key);
			}
		}else{
			System.err.println("99999锁获取失败");
		}

		return result;
	}

	public void test(Long id, String name){
		super.stringRedisTemplate.opsForValue().set("zw:string:"+id, name);
	}

	public void test1(Long id, String name){
		TestVO VO = new TestVO();
		VO.setId(id);
		VO.setName(name);
		super.redisTemplate.opsForValue().set("zw:bean:"+id, VO);
	}

	public void test2(Long id){
		super.redisTemplate.opsForValue().set("zw:bean:long:"+id, id);
	}
}
