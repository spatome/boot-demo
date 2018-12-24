package com.spatome.boot.netty.handler;

import com.spatome.boot.netty.proto.EnterprisePro;
import com.spatome.boot.netty.proto.UserPro;

public class ProFactory {

	public static IMessage<?> getMessageObject(Class<?> clazz) throws Exception{
		if(String.class == clazz){
			return new StringHandler();
		}else if(UserPro.class==clazz){
			return new UserProHandler();
		}else if(EnterprisePro.class==clazz){
			return new EnterpriseProHandler();
		}else{
			throw new Exception(clazz+"没定义");
		}
	}
}
