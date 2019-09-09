package com.shby.message.util;

import org.apache.commons.lang3.RandomUtils;

public class SUtil {

    /**
     * 4位验证码
     */
    public static String getPhoneCode() {
        return String.valueOf(RandomUtils.nextInt(1000, 9999));
    }

	/**
	 * @param ss	数据
	 * @param regex 分割符
	 * @param count 上限数
	 * @return
	 */
	public static boolean isOutTotalLimit(final String ss, String ch, int count) {
		if(ss==null) return false;
		String ssTmp = ss.replaceAll(ch, "");
		return ssTmp.length() - ssTmp.length() >= count;
	}

	/**
	 * 替换模板内容
	 * @param content
	 * @param templetParams
	 * @return
	 */
	public static String updateContent(final String content, String templetParams) {
		if(content==null) return content;
		String result = content;

		String[] params = templetParams.split("#");
		for (int i = 0; i < params.length; i++) {
			result = result.replaceFirst("#", params[i]);
		}

		return result;
	}

    public static void main(String[] args) {
    	for (int i = 0; i < 100; i++) {
    		System.err.println( SUtil.getPhoneCode() );	
		}
	}
}
