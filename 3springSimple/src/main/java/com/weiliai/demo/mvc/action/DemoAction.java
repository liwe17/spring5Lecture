package com.weiliai.demo.mvc.action;

import com.weiliai.demo.service.IDemoService;
import com.weiliai.mvcframework.annotation.GPAutowired;
import com.weiliai.mvcframework.annotation.GPController;
import com.weiliai.mvcframework.annotation.GPRequestMapping;
import com.weiliai.mvcframework.annotation.GPRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/3/15
 * @Describe: TODO
 */
@GPController
@GPRequestMapping("/demo")
public class DemoAction {

    @GPAutowired
    private IDemoService demoService;

    @GPRequestMapping("/query")
    public void query(HttpServletRequest req, HttpServletResponse resp,@GPRequestParam("name") String name){

        String result = demoService.get(name);
        try {
            resp.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
