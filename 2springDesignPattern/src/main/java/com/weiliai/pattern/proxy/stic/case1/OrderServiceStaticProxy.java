package com.weiliai.pattern.proxy.stic.case1;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Doug Li
 * @Date 2020/2/26
 * @Describe: TODO
 */
public class OrderServiceStaticProxy implements IOrderService {

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    private OrderService orderService;

    public OrderServiceStaticProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public int createOrder(Order order) {
        before();
        Long time = order.getCreateTime();
        final Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));
        DynamicDataSourceEntry.set(dbRouter);
        System.out.println("静态代理类自动分配到[DB_"+dbRouter+"]数据源进行处理数据");
        orderService.createOrder(order);
        after();
        return 0;
    }

    private void before(){
        System.out.println("Proxy before method");
    }

    private void after(){
        System.out.println("Proxy after method");
    }

    public static void main(String[] args) throws Exception {
        final Order order = new Order();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        final Date date = sdf.parse("2017/02/01");
        order.setCreateTime(date.getTime());

        new OrderServiceStaticProxy(new OrderService()).createOrder(order);
    }
}


