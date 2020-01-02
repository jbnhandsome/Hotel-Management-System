package com.jbn.common;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库工具类 - 使用连接池进行连接
 */
public class DBUtil {
    //用线程池存放连接
    public static ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");
    public static ThreadLocal<java.sql.Connection> threadLocal = new ThreadLocal<java.sql.Connection>();
    //读取配置文件中的mysql连接字段，自动连接到数据库


    /**
     * 通过连接池对象返回数据库连接
     *
     * @return Connection 连接对象
     * @throws SQLException 数据库错误
     */

    public static java.sql.Connection getConnection() throws SQLException {
        java.sql.Connection con = threadLocal.get(); // 返回一个connection连接

        //如果连接对象被关闭了 或者不存在 就从连接池里取出一个连接
        if (con == null || con.isClosed()) {
            con = dataSource.getConnection();
            threadLocal.set(con);
        }
        return con;

    }
    public static void close() {
        // 从本地线程中获得连接
        Connection conn = threadLocal.get();
        try {
            // 在连接不为空 且 没关闭时
            if (conn != null && !conn.isClosed()) {
                // 解除绑定
                threadLocal.set(null);
                // 关闭连接
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}