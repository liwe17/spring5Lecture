package com.weiliai.redocase;

import com.weiliai.redocase.simple.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: TODO
 */
public class Case {

    //新增学生
    public void save(Student student){
        String sql = "INSERT INTO t_student(name,age) VALUES (?,?)";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1.加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            connection = DriverManager.getConnection("jdbc://mysql:///jdbcdemo", "root", "root");
            //3.创建语句对象
            ps = connection.prepareStatement(sql);
            ps.setString(1,student.getName());
            ps.setInt(2,student.getAge());
            //4.执行SQL
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //5.释放资源
            if(ps!=null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    if(connection!=null){
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    //删除学生
    public void delete(Long id){
        String sql = "DELETE t_student WHERE id = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1.加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            connection = DriverManager.getConnection("jdbc://mysql:///jdbcdemo", "root", "root");
            //3.创建语句对象
            ps = connection.prepareStatement(sql);
            ps.setLong(1,id);
            //4.执行SQL
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //5.释放资源
            if(ps!=null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    if(connection!=null){
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    //修改学生
    public void update(Student student){
        String sql = "UPDATE t_student SET name = ?,age = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1.加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            connection = DriverManager.getConnection("jdbc://mysql:///jdbcdemo", "root", "root");
            //3.创建语句对象
            ps = connection.prepareStatement(sql);
            ps.setString(1,student.getName());
            ps.setInt(2,student.getAge());
            ps.setLong(3,student.getId());
            //4.执行SQL
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //5.释放资源
            if(ps!=null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    if(connection!=null){
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
