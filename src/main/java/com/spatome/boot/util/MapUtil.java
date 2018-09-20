///**
// * <pre>
// * Author:		zhangwei
// * Create:	 	2018年3月29日 下午1:51:05
// * Copyright: 	Copyright (c) 2018
// * Company:		Shenzhen Helper
// * <pre>
// */
//package com.hengpeng.util;
//
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.LinkedHashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//
///**
// * <pre>
// * TODO 输入类型说明
// * </pre>
// * @author zhangwei
// * @version 1.0, 2018年3月29日
// */
//public class MapUtil
//{
//	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueASC(Map<K, V> map)
//	{
//		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
//		Collections.sort(list, new Comparator<Map.Entry<K, V>>()
//		{
//			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
//			{
//				return (o1.getValue()).compareTo(o2.getValue());
//			}
//		});
//		Map<K, V> result = new LinkedHashMap<K, V>();
//		for (Map.Entry<K, V> entry : list)
//		{
//			result.put(entry.getKey(), entry.getValue());
//		}
//		return result;
//	}
//
//	public static <K, V extends Comparable<V>> Map<K, V> sortByValueDESC(final Map<K, V> map)
//	{
//		Comparator<K> valueComparator = new Comparator<K>()
//		{
//			public int compare(K k1, K k2)
//			{
//				int compare = map.get(k2).compareTo(map.get(k1));
//				if (compare == 0)
//					return 1;
//				else
//					return compare;
//			}
//		};
//		Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
//		sortedByValues.putAll(map);
//		return sortedByValues;
//	}
//	
//	public static void main(String[] args)
//	{
//	}
//}
