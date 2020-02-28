package com.weiliai.pattern.p2p.dynamic.case1;

/**
 * @Author: Doug Li
 * @Date 2020/2/26
 * @Describe: TODO
 */
public class OrderDao {

    public int insert(Order order){
        System.out.println("OrderDao 创建 Order 成功");
        return 1;
    }

}
