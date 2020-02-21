package com.weiliai.redocase.optimize.redo2;

import com.weiliai.redocase.simple.Student;

import java.util.List;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: TODO
 */
public class Case {

    //增加学生
    public void save(Student student){
        String sql = "INSERT INTO t_student(name,age) VALUES (?,?)";
        Object[] params = {student.getName(), student.getAge()};
        JdbcTemplate.update(sql,params);
    }

    //删除学生
    public void delete(Long id){
        String sql = "DELETE t_student WHERE id = ?";
        JdbcTemplate.update(sql,id);
    }


    //修改学生
    public void update(Student student) {
        String sql = "UPDATE t_student SET name = ?,age = ? WHERE id = ?";
        Object[] params = {student.getName(), student.getAge(), student.getId()};
        JdbcTemplate.update(sql,params);
    }

    //查询学生
    public Student get(Long id){
        String sql = "SELECT * FROM t_student WHERE id = ?";
        List<Student> list = JdbcTemplate.query(sql, id);
        return list.size()>0? list.get(0) :null;
    }

    //查询学生列表
    public List<Student> list(){
        String sql = "SELECT * FROM t_student";
        return JdbcTemplate.query(sql);
    }

}
