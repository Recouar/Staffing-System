package com.hxy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class ConnectionPool {
    /**
     * 连接池中多个连接放到一个List中
     * 获取连接是对List进行删除操作
     * 放回连接是对List进行添加操作
     * 考虑到添加、删除操作使用频繁，建议使用LinkedList
     */
    private static LinkedList<Connection> list = new LinkedList<Connection>();
    //第一次加载ConnectionPool类执行，并且只执行一次
    //在此处创建多个连接，并放入到list中，只执行了一次
    static{
        try{
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //创建10个数据库连接
            for(int i= 0 ; i<10;i++){
                Connection conn = newConnection();
                list.addLast(conn);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 新建一个连接
     */
    public static Connection newConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu","root","123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection(){
        if(list.size()>0){//连接池中有空闲的连接，直接返回
            return list.removeFirst();
        }else{//连接池没有空闲的连接，可以直接创建一个新的，不等待
            Connection conn = newConnection();
            return conn;
        }
    }
    public static void returnConnection(Connection conn){
        if(list.size()<10){//如果连接池中连接数量不足10个
            //加入到连接池
            list.addLast(conn);
        }else{//连接池已满
            //关闭物理连接
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        for(int i = 0;i<10;i++){
            //使用传统模式
            //Connection conn = newConnection();
            //使用连接池
            Connection conn = getConnection();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}
