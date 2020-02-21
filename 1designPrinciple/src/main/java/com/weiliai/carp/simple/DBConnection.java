package com.weiliai.carp.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: 目前是MYSQL,如果切换其他数据库类型需要修改DAO代码或者获取连接方法,违背开闭原则
 */
public class DBConnection {

    public String getConnection(){
        return "MySQL 数据库连接";
    }

    //其他数据库类型需要新增,违背开闭原则
}
