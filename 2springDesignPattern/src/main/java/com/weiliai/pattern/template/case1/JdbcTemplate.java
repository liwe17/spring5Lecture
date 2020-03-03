package com.weiliai.pattern.template.case1;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: TODO
 */
public abstract class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<?> executeQuery(String sql,RowMapper<?> rowMapper,Object[] values){
        try{
            //创建语句连接
            Connection conn = this.getConnection();
            //创建语句集
            PreparedStatement pstm = this.createPrepareStatement(conn,sql);
            //执行语句集
            ResultSet rs = this.executeQuery(pstm,values);
            //处理结果集
            List<?> result = this.paresResultSet(rs,rowMapper);
            //关闭结果集
            this.closeResultSet(rs);
            //关闭语句集
            this.closeStatement(pstm);
            //关闭连接
            this.closeConnection(conn);
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    protected void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }

    protected void closeStatement(PreparedStatement pstm) throws SQLException {
        pstm.close();
    }

    protected void closeResultSet(ResultSet rs) throws SQLException {
        rs.close();
    }

    protected List<?> paresResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception {
        List<Object> result = new ArrayList<>();
        int rowNum =1;
        while(rs.next()){
            result.add(rowMapper.mapRow(rs,rowNum++));
        }
        return result;
    }

    protected ResultSet executeQuery(PreparedStatement pstm, Object[] values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            pstm.setObject(i+1,values[i]);
        }
        return pstm.executeQuery();
    }

    public PreparedStatement createPrepareStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    public  Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    };
}
