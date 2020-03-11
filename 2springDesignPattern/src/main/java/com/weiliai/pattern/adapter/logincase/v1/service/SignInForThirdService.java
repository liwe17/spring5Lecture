package com.weiliai.pattern.adapter.logincase.v1.service;

import com.weiliai.pattern.adapter.logincase.ResultMsg;

/**
 * @Author: Doug Li
 * @Date 2020/3/8
 * @Describe: 第三方登录
 */
public class SignInForThirdService extends SignService{

    public ResultMsg loginForQQ(String openId){
        //1.openId是全局唯一,可以当作一个用户名
        //2.密码默认为QQ_EMPTY
        //3.注册(在原有系统里面创建一个用户)
        //4.调用原来的登录方法
        return loginForRegister(openId,null);

    }


    public ResultMsg loginForWeChat(String openId){
        return null;
    }


    public ResultMsg loginForTelephone(){
        return null;
    }

    public ResultMsg loginForRegister(String username, String password) {

        super.register(username,null);
        return super.login(username,null);
    }

}
