package com.spatome.boot.util;

import java.util.HashMap;
import java.util.Map;

public class App {

	public static void main(String[] args) {
		App app = new App();
		app.mapTest();
	}

	public void mapTest(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "11111");
		map.put("b", "22222");
		map.put("c", "33333");

		map.forEach((key, value) -> {
			System.out.println(String.format("key:%s,value:%s", key, value));
		});
	}
}
