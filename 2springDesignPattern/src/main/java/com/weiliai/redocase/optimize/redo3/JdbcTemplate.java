package com.weiliai.redocase.optimize.redo3;

import com.weiliai.redocase.simple.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: 模板类
 */
public class JdbcTemplate {

    private JdbcTemplate() {
    }

    //查询统一模板
    public static <T> T query(String sql,IRowMapper<T> rsh,Object...params){
        List<Student> list = new ArrayList<Student>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            //获取连接
            conn = JdbcUtil2.getConnection();
            ps = conn.prepareStatement(sql);
            //设置值
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1,params[0]);
            }
            rs = ps.executeQuery();
            return rsh.mapping(rs);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            JdbcUtil2.close(rs,ps,conn);
        }
        return null;
    }

    //更新删除模版
    public static int update(String sql,Object...params){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取连接
            conn = JdbcUtil2.getConnection();
            ps = conn.prepareStatement(sql);
            //设置值
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[0]);
            }
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            JdbcUtil2.close(rs,ps,conn);
        }
        return 0;
    }

}
