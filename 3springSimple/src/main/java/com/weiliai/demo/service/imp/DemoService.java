package com.weiliai.demo.service.imp;

import com.weiliai.demo.service.IDemoService;
import com.weiliai.mvcframework.annotation.GPService;

/**
 * @Author: Doug Li
 * @Date 2020/3/15
 * @Describe: TODO
 */
@GPService
public class DemoService implements IDemoService {

    @Override
    public String get(String name) {
        return "My name is "+name;
    }
}
