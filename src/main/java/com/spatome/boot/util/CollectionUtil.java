//package com.spatome.boot.util;
//
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.fasterxml.jackson.core.JsonParser.Feature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.spatome.demo.core.exception.SException;
//
///** 
// * @ClassName: CollectionUtil 
// * @Description: TODO
// * @author: zhangwei
// * @date: 2017年9月18日 下午6:24:53  
// */
//public class CollectionUtil {
//	private static final Logger LOGGER = LoggerFactory.getLogger(CollectionUtil.class);
//
//	/**
//	 * List 分页
//	 */
//	public static <T> List<List<T>> splitList(List<T> list, int len) {
//		if (list == null || list.size() == 0 || len < 1) {
//			return null;
//		}
//
//		List<List<T>> result = new ArrayList<List<T>>();
//
//		int size = list.size();
//		int count = (size + len - 1) / len;
//
//		for (int i = 0; i < count; i++) {
//			List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
//			result.add(subList);
//		}
//		return result;
//	}
//
///*	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public static <T> void sort(List<T> list, String property, boolean asc) {
//        Comparator<?> comparator = ComparableComparator.getInstance();
//        comparator = ComparatorUtils.nullLowComparator(comparator);
//        if (!asc) {
//            comparator = ComparatorUtils.reversedComparator(comparator);
//        }
//        Collections.sort(list, new BeanComparator(property, comparator));
//    }*/
//
//    /** 
//     * @Description: 分组
//     */
//    public static <K, T> boolean listGroup2Map(List<T> list, Map<K, List<T>> map, String methodName, Class<T> clazz) {
//    	try {
//			Method method = clazz.getDeclaredMethod(methodName);
//
//			List<T> listTmp = null;
//			for (T t : list) {
//				@SuppressWarnings("unchecked")
//				K key = (K)method.invoke(t);
//				listTmp = map.get(key);
//				if(listTmp==null){
//					listTmp = new ArrayList<T>();
//					map.put(key, listTmp);
//				}
//				listTmp.add(t);
//			}
//
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//    }
//
//	/**
//	 * @Description: Bean转Map
//	 * 去除value是null的
//	 */
///*	public static <T> Map<String, String> beanToMap(T bean) {
//		Map<String, String> map = Maps.newHashMap();
//		if (bean != null) {
//			BeanMap beanMap = BeanMap.create(bean);
//			for (Object key : beanMap.keySet()) {
//				if(beanMap.get(key)!=null){
//					map.put(key + "", beanMap.get(key)+"");	
//				}
//			}
//		}
//
//		return map;
//	}*/
//
//    /** 
//     * @Description: JSON字符串转MAP
//     */
//    @SuppressWarnings("unchecked")
//	public static Map<String, String> toMap(String data) throws SException {
//    	Map<String, String> result = new HashMap<String, String>();
//
//    	try {
//			if(StringUtils.isBlank(data)){
//			}else{
//				ObjectMapper om = new ObjectMapper();
//				om.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//				result = om.readValue(data, Map.class);
//			}
//		} catch (Exception e) {
//			LOGGER.error("toMap", e);
//			throw new SException("9999", "JSON String 转Map失败:"+e.getMessage());
//		}
//
//    	return result;
//    }
//
//	/**
//	 * MAP转JSON字符串
//	 */
//	public static <T> String toJson(Map<String, T> map) throws SException {
//    	try {
//			if(map==null || map.size()==0){
//				return "{}";
//			}else{
//				ObjectMapper om = new ObjectMapper();
//				om.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//				return om.writeValueAsString(map);
//			}
//		} catch (Exception e) {
//			LOGGER.error("toJson", e);
//			throw new SException("9999", "Map转JSON失败");
//		}
//    }
//    
///*    public static <T> String toString(Map<String, T> map, String div){
//    	if(map==null){
//    		return "";
//    	}
//
//    	StringBuilder sb = new StringBuilder();
//    	for (Map.Entry<String, T> f : map.entrySet()) {
//			sb.append(f.getKey()).append("{").append(f.getValue()).append("}").append(div);
//		}
//
//    	return sb.toString();
//    }*/
//    
//	public static void ps(List<?> resultList){
//		System.out.println("******************开始打印数据*****************");
//		if(resultList!=null){
//			System.out.println("当前记录条数为："+resultList.size());
//			for(Object result : resultList){
//				System.out.println(result);
//			}
//		}
//		System.out.println("*****************打印结束****************");
//	};
//
//	@SuppressWarnings("rawtypes")
//	public static boolean isSetEqual(Set set1, Set set2)
//	{
//
//		if (set1 == null && set2 == null)
//		{
//			return true;
//		}
//		if(set1!=null && set1.size()==0 && set2!=null && set2.size()==0){
//			return true;
//		}
//		if (set1 == null || set2 == null || set1.size() != set2.size())
//		{
//			return false;
//		}
//
//		Iterator ite2 = set2.iterator();
//
//		boolean isFullEqual = true;
//
//		while (ite2.hasNext())
//		{
//			if (!set1.contains(ite2.next()))
//			{
//				isFullEqual = false;
//			}
//		}
//
//		return isFullEqual;
//	}
//	
//	public static void main(String[] args) {
//	}
//}