package test;

import com.spatome.boot.netty.proto.UserPro;
import com.spatome.boot.netty.util.ProtostuffUtil;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;

public class App {

	private static RuntimeSchema<UserPro> schema = RuntimeSchema.createFrom(UserPro.class);

	public static void main(String[] args) {
		App app = new App();
		app.test();
	}

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
