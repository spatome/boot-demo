package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.spatome.boot.netty.proto.ClientMessage;
import com.spatome.boot.netty.proto.UserPro;
import com.spatome.boot.netty.util.ProtostuffUtil;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;

public class App {

	private static RuntimeSchema<UserPro> schema = RuntimeSchema.createFrom(UserPro.class);

	public static void main(String[] args) {
		App app = new App();
		app.test1();
	}

	public void test1(){
		UserPro userPro = new UserPro();
		userPro.setId(1L);
		userPro.setUserName("zw001");
		userPro.setAge(20);

		ClientMessage clientMessage = new ClientMessage(
				"1",
				"1",
				userPro,
				UserPro.class
				);

		try {
			Method method = clientMessage.getMessageObjClass().getMethod("messageHandler", String.class, String.class, clientMessage.getMessageObjClass());
			method.invoke(clientMessage.getMessageObjClass().newInstance(), clientMessage.getServerMessageId(), clientMessage.getClientMessageId(), clientMessage.getMessageObj());
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
	public void test(){
		UserPro userPro = new UserPro();
		userPro.setId(1L);
		userPro.setUserName("zw001");
		userPro.setAge(20);

		byte[] bytes = ProtostuffIOUtil.toByteArray(userPro, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

        UserPro userPro1 = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, userPro1, schema);

        System.out.println(userPro1);

        //序列化
        byte[] bytes1 = ProtostuffUtil.serialize(userPro);
        UserPro newUser = ProtostuffUtil.deserialize(bytes1, UserPro.class);
        System.out.println(newUser);
	}
}
