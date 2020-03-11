package com.weiliai.pattern.adapter.logincase.v2.adapters;

import com.weiliai.pattern.adapter.logincase.ResultMsg;

/**
 * @Author: Doug Li
 * @Date 2020/3/11
 * @Describe: TODO
 */
public interface IPassportForThird {

    ResultMsg loginForQQ(String id);

    ResultMsg loginForWechat(String id);

    ResultMsg loginForToken(String token);

    ResultMsg loginForTel(String telephone,String code);

    ResultMsg loginForRegister(String username,String password);

}
