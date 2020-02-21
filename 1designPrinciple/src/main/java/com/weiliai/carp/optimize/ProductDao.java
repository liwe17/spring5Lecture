package com.weiliai.carp.optimize;

import com.weiliai.carp.simple.DBConnection;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: TODO
 */
public class ProductDao {

    private DBConnection dbConnection;

    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addProduct(){
        String conn = dbConnection.getConnection();
        System.out.println("使用"+conn+"增加产品");
    }
}
