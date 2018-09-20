package com.spatome.boot.util;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.regex.Pattern;

public class SUtil {

	/** 
	 * @Description: 生成流水号
	 */
	public static String createNumberNo() {
		String result = "";

		int machineId = 1;// 最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0
		// 4 代表长度为4
		// d 代表参数为正数型
		result = machineId + String.format("%015d", hashCodeV);

		return result;
	}

	public static boolean isInteger(String str){
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();  
	}

	public static boolean isAmount(String str){
		try {
			new BigDecimal(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String NTS(String s){
		return s==null?"":s;
	}
	public static String NTS(Integer s){
		return s==null?"":String.valueOf(s);
	}
	public static String NTS(Long s){
		return s==null?"":String.valueOf(s);
	}
}