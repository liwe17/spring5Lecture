package com.weiliai.redocase.optimize.redo;

import com.weiliai.redocase.simple.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: TODO
 */
public class Case {

    //新增学生
    public void save(Student student){
        String sql = "INSERT INTO t_student(name,age) VALUES (?,?)";
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            //获取数据库连接
            connection = JdbcUtil.getConnection();
            //创建语句对象
            ps = connection.prepareStatement(sql);
            ps.setString(1,student.getName());
            ps.setInt(2,student.getAge());
            //执行SQL
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            JdbcUtil.close(null,ps,connection);
        }
    }

    //删除学生
    public void delete(Long id){
        String sql = "DELETE t_student WHERE id = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //获取数据库连接
            connection = JdbcUtil.getConnection();
            //创建语句对象
            ps = connection.prepareStatement(sql);
            ps.setLong(1,id);
            //执行SQL
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            JdbcUtil.close(null,ps,connection);
        }
    }

    //修改学生
    public void update(Student student){
        String sql = "UPDATE t_student SET name = ?,age = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //获取数据库连接
            connection = JdbcUtil.getConnection();
            //创建语句对象
            ps = connection.prepareStatement(sql);
            ps.setString(1,student.getName());
            ps.setInt(2,student.getAge());
            ps.setLong(3,student.getId());
            //执行SQL
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            JdbcUtil.close(null,ps,connection);
        }
    }

    //查询学生
    public Student get(Long id){
        String sql = "SELECT * FROM t_student WHERE id = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取数据库连接
            connection = JdbcUtil.getConnection();
            //创建语句对象
            ps = connection.prepareStatement(sql);
            ps.setLong(1,id);
            //执行SQL
            rs = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("name");
                long id1 = rs.getLong("id");
                int age = rs.getInt("age");
                return new Student(id,name,age);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            JdbcUtil.close(rs,ps,connection);
        }
        return null;
    }

    //查询学生
    public List<Student> list(){
        List<Student> students = new ArrayList<Student>();
        String sql = "SELECT * FROM t_student";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取数据库连接
            connection = JdbcUtil.getConnection();
            //创建语句对象
            ps = connection.prepareStatement(sql);
            //执行SQL
            rs = ps.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                long id = rs.getLong("id");
                int age = rs.getInt("age");
                students.add(new Student(id,name,age));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            JdbcUtil.close(rs,ps,connection);
        }
        return students;
    }

}
