package com.weiliai.redocase.optimize.redo;

import java.sql.*;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: TODO
 */
public class JdbcUtil {

    private JdbcUtil() {
    }

    static {
        //1.加载注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        //2.获取数据库连接
        try {
            return DriverManager.getConnection("jdbc:mysql:///jdbcdemo", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet rs, Statement st,Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if(st!=null){
                    try {
                        st.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally {
                        if(conn!=null){
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

    }
}
