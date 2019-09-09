package com.shby.message.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shby.message.vo.BaseVO;
import com.shby.message.vo.SSVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("demo")
@Slf4j
public class DemoController extends BaseController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public Object test(HttpServletRequest request, HttpServletResponse response) {
    	BaseVO<SSVO> result = new BaseVO<SSVO>();

    	log.info("this is demo/test");
    	result.setBody(new SSVO("boot.demo集群0"));

        return result;
    }
}
