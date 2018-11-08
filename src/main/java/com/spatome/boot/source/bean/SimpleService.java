package com.spatome.boot.source.bean;

import org.springframework.stereotype.Component;

/**
 * Created by Format on 2017/5/4.
 */
@Component
public class SimpleService {

    private String name = "simple";

    public SimpleService() {
        System.out.println("------ SimpleService constructor invoke");
    }

    public void test() {
        System.out.println(name);
    }

}
