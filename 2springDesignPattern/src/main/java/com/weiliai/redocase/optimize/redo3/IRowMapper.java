package com.weiliai.redocase.optimize.redo3;

import java.sql.ResultSet;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: 结果集处理
 */
public interface IRowMapper<T> {

    T mapping(ResultSet rs) throws Exception;
}
