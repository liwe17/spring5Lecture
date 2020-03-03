package com.weiliai.pattern.proxy.dynamic.case1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author: Doug Li
 * @Date 2020/2/26
 * @Describe: TODO
 */
public class OrderServiceDynamicProxy implements InvocationHandler {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    private Object target;

    public Object getTarget(Object target) {
        this.target = target;
        final Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(args[0]);
        final Object obj = method.invoke(target, args);
        after(Arrays.toString(args));
        return obj;
    }



    private void before(Object target) {
        System.out.println("Proxy before method");
        try {
            final Long time = (Long)target.getClass().getMethod("getCreateTime").invoke(target);
            final int dbRouter = Integer.parseInt(sdf.format(new Date(time)));
            System.out.println("自动分配到[DB_"+dbRouter+"]数据源进行处理");
            DynamicDataSourceEntry.set(dbRouter);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void after(String toString) {
        System.out.println("Proxy after method:"+toString);
    }

    public static void main(String[] args) throws ParseException {
        final Order order = new Order();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        final Date date = sdf.parse("2018/02/01");
        order.setCreateTime(date.getTime());

        final IOrderService proxy = (IOrderService)new OrderServiceDynamicProxy().getTarget(new OrderService());
        proxy.createOrder(order);
    }
}