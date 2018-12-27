package com.spatome.boot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spatome.boot.vo.SSVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("demo")
@Slf4j
public class DemoController extends BaseController {



    @RequestMapping(value = "test", method = RequestMethod.GET)
    public Object test(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	BaseVO<SSVO> result = new BaseVO<SSVO>();

    	log.info("this is demo/test");
    	result.setBody(new SSVO("boot.demo集群0"));

        return result;
    }

    @RequestMapping(value = "test1", method = RequestMethod.POST)
    public Object test1(HttpServletRequest request, HttpServletResponse response) {
    	BaseVO<Object> result = new BaseVO<Object>();
    	Map<String, String> map = new HashMap<String, String>();
    	result.setBody(map);

    	String a = request.getParameter("a");
    	map.put("a", a);

    	log.info("this is demo/test1 a:"+a);

        return result;
    }
}