package com.weiliai.pattern.adapter.logincase.v2.adapters;

import com.weiliai.pattern.adapter.logincase.ResultMsg;

/**
 * @Author: Doug Li
 * @Date 2020/3/11
 * @Describe: TODO
 */
public class LoginForTokenAdapter implements LoginAdapter {

    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForTokenAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        return null;
    }
}
