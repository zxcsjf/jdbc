package com.jdbc._01_crud;

import utils.JDBCUtils2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author zxcsjf
 * @since 2022/07/26 20:38
 */
public class UpdateDemo {
    public static void main(String[] args) throws SQLException {
        // 注册驱动
        // 建立连接
        Connection connection = JDBCUtils2.getConnection();

        // 获取statement对象
        Statement statement = connection.createStatement();

        // 执行sql
        int affectedRows = statement.executeUpdate("delete from city where id = 12");
        System.out.println("affectedRows = " + affectedRows);

        // 关闭资源
        JDBCUtils2.closeResources(statement, connection, null);


    }
}
