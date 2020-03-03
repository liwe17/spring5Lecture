package com.weiliai.pattern.template.case1;

import java.sql.ResultSet;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: TODO
 */
public interface RowMapper<T> {

    T mapRow(ResultSet rs,int rowNum) throws Exception;

}
