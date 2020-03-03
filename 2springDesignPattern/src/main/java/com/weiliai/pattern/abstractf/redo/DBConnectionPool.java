package com.weiliai.pattern.abstractf.redo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * @Author: Doug Li
 * @Date 2020/2/22
 * @Describe: 数据库连接池管理类
 */
public class DBConnectionPool extends Pool {

    private int checkedOut; //正在使用的连接数

    private Vector<Connection> freeConnections = new Vector<Connection>(); //存放产生的连接对象容器

    private String userName; // 用户名

    private String passWord; // 密码

    private String url; // 连接字符串

    private static int num = 0;// 空闲连接数

    private static int numActive = 0;// 当前可用的连接数

    private static DBConnectionPool pool;// 连接池实例变量

    //产生数据库连接池
    public static synchronized DBConnectionPool getInstance() {
        if (pool == null) {
            pool = new DBConnectionPool();
        }
        return pool;
    }

    //获得一个数据库连接池
    private DBConnectionPool() {
        try {
            init();
            for (int i = 0; i < normalConnect; i++) { //初始normalConn个连接
                Connection c = newConnection();
                if (c != null) {
                    freeConnections.addElement(c); //往容器中添加一个连接对象
                    num++; //记录总连接数
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //初始化
    private void init() throws IOException {
        InputStream is = DBConnectionPool.class.getResourceAsStream(propertiesName);
        Properties p = new Properties();
        p.load(is);
        this.userName = p.getProperty("userName");
        this.passWord = p.getProperty("passWord");
        this.url = p.getProperty("url");
        this.driverName = p.getProperty("driverName");
        this.maxConnect = Integer.parseInt(p.getProperty("maxConnect"));
        this.normalConnect = Integer.parseInt(p.getProperty("normalConnect"));
    }

    //创建一个连接
    private Connection newConnection() {
        Connection con = null;
        try {
            if (userName == null && passWord == null) {
                con = DriverManager.getConnection(url);
            }else{
                con = DriverManager.getConnection(url,userName,passWord);
            }
            System.out.println("连接池创建一个新的连接");
        } catch (SQLException e) {
            System.err.println("无法创建这个URL连接:"+url+",错误:"+e);
        }
        return con;
    }

    @Override
    public void createPool() {
        pool = new DBConnectionPool();
        System.out.println("创建连接池成功");
    }

    @Override
    public synchronized Connection getConnection() {
        Connection con = null;
        if(freeConnections.size()>0){
            num--;
            con = freeConnections.firstElement();
            freeConnections.removeElementAt(0);
            try {
                if(con.isClosed()){
                    System.out.println("从数据库删除一个无效连接");
                    con=getConnection();
                }
            } catch (SQLException e) {
                System.out.println("从数据库删除一个无效连接");
                con=getConnection();
            }
        }else if(maxConnect ==0 || checkedOut<maxConnect){ //没有最大连接或者当前连接数小于最大连接
            con=getConnection();
        }
        if(con !=null){ //当前连接数+1
            checkedOut++;
        }
        numActive++;
        return con;
    }

    @Override
    public synchronized Connection getConnection(long timeout) {
        long startTime = new Date().getTime();
        Connection con;
        while((con=getConnection())==null){
            try{
                wait(timeout); //线程等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if((new Date().getTime()-startTime)>=timeout){
                return null; //超时返回
            }
        }
        return con;
    }

    public synchronized void release() {
        //将当前连接赋值到枚举中
        try{
            Enumeration<Connection> allConnections = freeConnections.elements();
            try{
                //循环关闭连接
                while(allConnections.hasMoreElements()){
                    allConnections.nextElement().close();
                    num--;
                }
            }catch (SQLException e){
                System.err.println("无法关闭连接池中的连接,错误:"+e);
            }
            freeConnections.removeAllElements();
            numActive=0;
        }finally {
            super.release();
        }
    }

    @Override
    public synchronized void freeConnection(Connection con) {
        freeConnections.addElement(con);
        num++;
        checkedOut--;
        numActive--;
        notifyAll();//解锁
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public int getNumActive() {
        return numActive;
    }


}
