package com.weiliai.pattern.decorator.passport.old;

/**
 * @Author: Doug Li
 * @Date 2020/3/12
 * @Describe: TODO
 */
public interface ISignInService {

    ResultMsg register(String username,String password);

    //登录方法
    ResultMsg login(String username,String password);

}
