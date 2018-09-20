package com.spatome.boot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spatome.boot.vo.BaseVO;
import com.spatome.boot.vo.SSVO;

@RestController
@RequestMapping("demo")
public class DemoController extends BaseController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public Object test(HttpServletRequest request, HttpServletResponse response) {
    	BaseVO<SSVO> result = new BaseVO<SSVO>();

    	LOGGER.info("this is demo/test");

    	result.setBody(new SSVO("boot.demo"));

        return result;
    }
}
