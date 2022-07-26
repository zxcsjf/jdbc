package com.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC 快速入门
 *
 * @author zxcsjf
 * @since 2022/06/11 11:36
 */
public class JDBCDemo {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("jdbc.properties"));
        String driver = properties.getProperty("driver");

        // 1. 注册驱动
        // DriverManager.registerDriver(new Driver());
        Class.forName(driver);


        // 2. 获取连接,
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3. 定义sql
        String sql = "update stu set score = 100 where id = 1";
        String sql2 = "insert into stu (id, name) values (6, '赵六')";

        // 4. 创建statement对象，用来执行sql
        Statement statement = connection.createStatement();

        // 5. 执行sql, 获取返回结果
        int count = statement.executeUpdate(sql); // 受影响的行数，update增删改
        int i = statement.executeUpdate(sql2);
        // ResultSet resultSet = statement.executeQuery(sql2);


        // 6. 解析结果集
        System.out.println(count);

        // 7. 释放资源，断开连接
        statement.close();
        connection.close();

    }
}
