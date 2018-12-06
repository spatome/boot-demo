package com.spatome.boot.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.spatome.boot.common.config.redis.RedissonLockUtil;
import com.spatome.boot.entity.Account;
import com.spatome.boot.service.TranService;
import com.spatome.boot.vo.BaseVO;

import lombok.extern.slf4j.Slf4j;

/** 
 * 测试
 */
@Slf4j
@Service
public class Tran99994ServiceImpl extends BaseService implements TranService {

	@Override
	public Object execute(Map<String, String> dataMap, HttpServletRequest request, HttpServletResponse response) {
		BaseVO<Object> result = new BaseVO<Object>();

		log.debug("获取参数");
		Long id = Long.valueOf(dataMap.get("id"));
		log.debug("检查参数");
		Map<String, String> paramMap = new HashMap<String, String>();
		super.checkNotEmpty(paramMap);

		log.debug("===========================业务处理=========================");
		String key = "lock:activity:account:id:"+id;

		if(RedissonLockUtil.tryLock(key, 3000L)){
			try{
				Account account = daoFactory.getAccountMapper().selectByPrimaryKey(id);
				Account updateAccount = new Account();
				updateAccount.setId(account.getId());
				updateAccount.setDepositAmount(account.getDepositAmount().add(BigDecimal.ONE));
				daoFactory.getAccountMapper().updateByPrimaryKeySelective(updateAccount);
				System.out.println("99998修改");
			}finally{
				RedissonLockUtil.unlock(key);
			}
		}else{
			System.out.println("锁获取失败");
		}

		return result;
	}

}
