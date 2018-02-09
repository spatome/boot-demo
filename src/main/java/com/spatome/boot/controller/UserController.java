package com.spatome.boot.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spatome.boot.vo.LoginRVO;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(@RequestBody LoginRVO loginRVO) {
    	BaseVO<Object> result = new BaseVO<Object>();

    	result.setBody(serviceFactory.getDemoService().login(loginRVO));

        return result;
    }
}
