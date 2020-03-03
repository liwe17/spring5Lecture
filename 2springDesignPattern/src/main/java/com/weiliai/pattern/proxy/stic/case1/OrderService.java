package com.weiliai.pattern.proxy.stic.case1;

/**
 * @Author: Doug Li
 * @Date 2020/2/26
 * @Describe: TODO
 */
public class OrderService implements IOrderService {

    private OrderDao orderDao;

    public OrderService() {
        //如果使用String应该是自动注入的
        //为了使用方便,我们在构造方法中将orderDao直接初始化
        this.orderDao = new OrderDao();
    }

    @Override
    public int createOrder(Order order) {
        System.out.println("OrderService 调用 orderDao 创建订单");
        return orderDao.insert(order);
    }
}
