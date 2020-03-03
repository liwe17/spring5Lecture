package com.weiliai.pattern.template.case1;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: TODO
 */
public class MemberDao extends JdbcTemplate {
    
    public MemberDao(DataSource dataSource) {
        super(dataSource);
    }
    
    public List<?> selectAll(){
        String sql = "SELECT * FROM t_member";
        return super.executeQuery(sql, (rs, rowNum) ->{
            final Member member = new Member();
            member.setUsername(rs.getString("username"));
            member.setNickName(rs.getString("nickName"));
            member.setAddr(rs.getString("addr"));
            member.setPassword(rs.getString("password"));
            member.setAge(rs.getInt("age"));
            return member;
        },null);
    }
}
