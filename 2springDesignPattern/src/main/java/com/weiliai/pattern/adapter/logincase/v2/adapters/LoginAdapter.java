package com.weiliai.pattern.adapter.logincase.v2.adapters;

import com.weiliai.pattern.adapter.logincase.ResultMsg;

/**
 * @Author: Doug Li
 * @Date 2020/3/11
 * @Describe: TODO
 */
public interface LoginAdapter {

    boolean support(Object adapter);

    ResultMsg login(String id,Object adapter);

}
