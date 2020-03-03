package com.weiliai.pattern.abstractf.redo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: Doug Li
 * @Date 2020/2/22
 * @Describe: 连接池
 *  自定义连接池 getInstance(),返回pool的唯一实例,第一次调用时将执行构造函数
 *  构造函数Pool()调用驱动装载loadDrivers()函数;createPool()函数创建连接池,loadDrivers()装载驱动
 *  getConnection()返回一个连接实例,getConnection(long time)添加时间限制
 *  freeConnection(Connection con)将con连接实例放回连接池,getNum()返回空闲连接数
 *  getNumActive()返回当前使用的连接数
 */
public abstract class Pool {

    public String propertiesName = "connection-INF.properties";

    private static Pool instance; //定义唯一实例

    protected int maxConnect = 100; //最大连接数

    protected int normalConnect = 10; //保持连接数

    protected String driverName; //驱动字符串

    protected Driver driver; //驱动变量

    //构造函数
    protected Pool() {
        try{
            init();
            loadDrivers(driverName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //初始化所有从配置文件读取的成员变量
    private void init() throws IOException {
        InputStream is = Pool.class.getResourceAsStream(propertiesName);
        Properties p = new Properties();
        p.load(is);
        this.driverName=p.getProperty("driverName");
        this.maxConnect=Integer.parseInt(p.getProperty("maxConnect"));
        this.normalConnect=Integer.parseInt(p.getProperty("normalConnect"));
    };

    //装载和注册驱动
    protected void loadDrivers(String driverName){
        String driverClassName = driverName;
        try {
            driver = (Driver) Class.forName(driverClassName).newInstance();
            DriverManager.registerDriver(driver); //有些数据库已经在静态代码快中注册
            System.out.println("成功注册JDBC驱动程序:"+driverClassName);
        } catch (Exception e) {
            System.err.println("无法注册JDBC驱动程序:"+driverClassName+",错误:"+e);
        }
    };

    public static synchronized Pool getInstance() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        if(instance==null){
            //抽象类不能被实例化,只能通过子类实例化
            instance=(Pool) Class.forName("com.weiliai.pattern.abstractf.redo.Pool").newInstance();
        }
        return instance;
    }

    //创建连接池
    public abstract void createPool();

    //获取一个可用的连接
    public abstract Connection getConnection();

    //获取一个可用连接,有时间限制
    public abstract Connection getConnection(long time);

    //释放连接,将连接放回连接池
    public abstract void freeConnection(Connection con);

    //返回当前空闲连接数
    public abstract int getNum();

    //返回当前工作的连接数
    public abstract int getNumActive();

    //撤销驱动
    protected synchronized void release(){
        try {
            DriverManager.deregisterDriver(driver);
            System.out.println("撤销JDBC驱动程序:"+driver.getClass().getName());
        } catch (SQLException e) {
            System.err.println("无法撤销JDBC驱动程序:"+driver.getClass().getName()+",错误:"+e);
        }
    }
}
