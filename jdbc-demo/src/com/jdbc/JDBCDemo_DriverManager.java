package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 驱动管理
 *
 * @author zxcsjf
 * @since 2022/06/12 17:33
 */
public class JDBCDemo_DriverManager {
    public static void main(String[] args) throws Exception {
        // 1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 2. 获取连接:如果链接的是本机并且端口号是3306，
        // 可以简化书写：String url = "jdbc:mysql:///db1?useSSL=false";
        String url = "jdbc:mysql:///db1?useSSL=false";
        String username = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 3. 定义sql
        String sql = "update stu set score = 100 where id = 1";

        // 4. 获取执行sql的对象 Statement
        Statement stmt = conn.createStatement();

        // 5. 执行sql
        int count = stmt.executeUpdate(sql); // 受影响的行数

        // 6. 处理结果
        System.out.println(count);

        // 7. 释放资源
        stmt.close();
        conn.close();

    }

}
