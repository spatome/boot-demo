package com.spatome.boot.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TranService {

	public Object execute(Map<String, String> dataMap, HttpServletRequest request, HttpServletResponse response);

}
