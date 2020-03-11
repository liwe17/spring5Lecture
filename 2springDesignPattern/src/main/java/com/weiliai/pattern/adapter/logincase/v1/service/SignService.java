package com.weiliai.pattern.adapter.logincase.v1.service;

import com.weiliai.pattern.adapter.logincase.Member;
import com.weiliai.pattern.adapter.logincase.ResultMsg;

/**
 * @Author: Doug Li
 * @Date 2020/3/8
 * @Describe: TODO
 */
public class SignService {

    //注册方法
    public ResultMsg register(String username,String password){
        return new ResultMsg(200,"注册成功",new Member());
    }


    //登录方法
    public ResultMsg login(String username,String password){
        return null;
    }

}
