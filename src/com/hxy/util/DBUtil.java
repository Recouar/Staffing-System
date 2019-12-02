package com.hxy.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * @BelongsProject: myJava
 * @BelongsPackage: com.hxy.dao.impl
 * @Author: 胡晓洋
 * @CreateTime: 2019-11-28 18:08
 * @Description:
 */
public abstract class DBUtil {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    //定义一个日志记录器
    private static Logger logger = Logger.getLogger(DBUtil.class.getName());
    //在合适的位置记录相应级别的日志
    static{
        //创建Properties对象
        Properties prop = new Properties();
        //读取属性文件，内容存入Properties对象
        InputStream is = DBUtil.class.getResourceAsStream("/jdbc.properties");
        try{
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        driver = prop.getProperty("driver");
        url = prop.getProperty("url");
        user = prop.getProperty("user");
        password = prop.getProperty("pwd");
        logger.info("已经读取了属性文件的信息："+driver+","+url+","+user+","+password);
    }
    public static Connection getConnection(){
        Connection conn = null;
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }*/
       /* String url = "jdbc:mysql://127.0.0.1:3306/stu";
        String user = "root";
        String password = "123456";*/
        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("获取连接失败"+e);
        }
        logger.info("获取连接失败："+conn);
        return  conn;

    }
    public  static void closeAll(Connection conn, Statement stmt, ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }
            logger.debug("正确关闭了结果集");
        }
            catch (SQLException e) {
                e.printStackTrace();
            }

        try{
        if (stmt != null) {
            stmt.close();
        }logger.debug("正确关闭了statement");
    }catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null){
                conn.close();
            } logger.debug("正确关闭了数据库连接");
            }catch (SQLException e) {
                e.printStackTrace();
            }
    }
    public static  int  executeUpdate(String sql,Object[] params){
        int n = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            for(int  i = 0;i<params.length;i++){
                pstmt.setObject(i+1,params[i]);
            }
                n= pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn,pstmt,rs);
        }
        return n;
    }
    public static<T> List<T> executeQuery(String sql,Object params[],Class clazz){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<T>();
        try{
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);

            for(int i = 0;i<params.length;i++){
                pstmt.setObject(i+1,params[i]);
            }
            rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while(rs.next()){
                T entity = (T)clazz.newInstance();
                for(int i = 0;i<columnCount;i++){
                    String columnName = rsmd.getColumnName(i+1).toLowerCase();
                    Object value  = rs.getObject(columnName);
                    String methodName = "set"+columnName.substring(0,1).toUpperCase()+columnName.substring(1);
                    String paramClassName = rsmd.getColumnClassName(i+1);
                    Method method = clazz.getMethod(methodName,Class.forName(paramClassName));
                    method.invoke(entity,value);
                }
                list.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn,pstmt,rs);
        }
        return list;
    }
}
