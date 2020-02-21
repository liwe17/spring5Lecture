package com.weiliai.redocase.optimize.redo2;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: 参数取自文件
 */
public class JdbcUtil2 {


    private JdbcUtil2() {
    }

    static Properties p;
    static {
        //1.加载注册驱动
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = contextClassLoader.getResourceAsStream("db.properties");
            p = new Properties();
            p.load(inputStream);
            Class.forName(p.getProperty("driverClassName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        //2.获取数据库连接
        try {
            return DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
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

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
