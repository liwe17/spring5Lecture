package com.weiliai.pattern.adapter.logincase.v2.adapters;

import com.weiliai.pattern.adapter.logincase.ResultMsg;

/**
 * @Author: Doug Li
 * @Date 2020/3/11
 * @Describe: TODO
 */
public class LoginForWeChatAdapter implements LoginAdapter {

    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForWeChatAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        return null;
    }
}
