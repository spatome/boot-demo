package com.spatome.boot.util.convert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * JSON工具类
 */
public class JUtil {

    private static final SerializeConfig config;
    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer());	 // 使用和json-lib兼容的日期输出格式
    }
 
    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, 	// list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, 	// 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, 	// Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty 	// 字符类型字段如果为null，输出为""，而不是null
    };

    /**
     * 对象转JSON
     * 格式化
     */
    public static String toJsonFeature(Object object) {
        return JSON.toJSONString(object, config, features);
    }
    /**
     * 对象转JSON
     * 不格式化
     */
    public static String toJson(Object object) {
        return JSON.toJSONString(object, config);
    }

    /**
     * json转对象
     */
    public static Object toBean(String json) {
        return JSON.parse(json);
    }
    /**
     * json转对象
     */
    public static <T> T toBean(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 转换为数组
     */
    public static <T> Object[] toArray(String json) {
        return toArray(json, null);
    }

    /**
     * 转换为数组
     */
    public static <T> Object[] toArray(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz).toArray();
    }

    /**
     * 转换为List
     */
    public static <T> List<T> toList(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    /**
     * json字符串转化为map
     * @param s
     * @return
     */
    public static <K, V> Map<K, V>  toMap(String json) {
    	if(json==null || !json.startsWith("{") || !json.endsWith("}")){
    		return new HashMap<K, V>();
    	}

    	@SuppressWarnings("unchecked")
		Map<K, V> m = (Map<K, V>) JSON.parseObject(json);
        return m;
    }

    public static void main(String[] args) {
/*		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("a", "1");
		map1.put("b", "2");
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("aa", "11");
		map2.put("bb", "21");
		map.put("m1", map1);
		map.put("m2", map2);

		String ret = JsonUtil.toJson(map);
		System.out.println(ret);*/

//    	String json = "{\"m1\":{\"a\":\"1\",\"b\":\"2\"},\"m2\":{\"aa\":\"11\",\"bb\":null}}";
//    	Map<String, Map<String, String>> map = JUtil.toMap(json);
//    	for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
//    		System.out.println("key:" + entry.getKey());
//    		for (Map.Entry<String, String> entry1 : entry.getValue().entrySet()) {
//    			System.out.println("key:" + entry1.getKey() + "	value:" + entry1.getValue());	
//    		}
//		}

    	Map<String, String> map = JUtil.toMap(null);
    	System.out.println(map);
	}
}
 