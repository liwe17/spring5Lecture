package com.weiliai.pattern.adapter.logincase.v2.adapters;

import com.weiliai.pattern.adapter.logincase.ResultMsg;
import com.weiliai.pattern.adapter.logincase.v1.service.SignService;

/**
 * @Author: Doug Li
 * @Date 2020/3/11
 * @Describe: TODO
 */
public class PassportForThirdAdapter extends SignService implements IPassportForThird {

    //这里用到了简单工厂模式及策略模式
    private ResultMsg processLogin(String key,Class<? extends LoginAdapter> clazz){

        final LoginAdapter adapter;
        try {
            adapter = clazz.newInstance();
            if(adapter.support(adapter)){
                return adapter.login(key,adapter);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ResultMsg loginForQQ(String id) {
        return processLogin(id,LoginForQQAdapter.class);
    }

    @Override
    public ResultMsg loginForWechat(String id) {
        return processLogin(id,LoginForWeChatAdapter.class);
    }

    @Override
    public ResultMsg loginForToken(String token) {
        return processLogin(token,LoginForTokenAdapter.class);
    }

    @Override
    public ResultMsg loginForTel(String telephone, String code) {
        return processLogin(telephone,LoginForTelAdapter.class);
    }

    @Override
    public ResultMsg loginForRegister(String username, String password) {
        super.register(username,null);
        return super.login(username,null);
    }
}
