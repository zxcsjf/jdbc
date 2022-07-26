package com.jdbc.homework;

import utils.JDBCUtils2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 1. 采用三种不同的方式插入10000条数据，比较速度差别
 * <p>
 * 2. 采用PrepareStatement插入1000w条数据，
 * 然后去根据主键查询和根据普通字段查询一条数据，
 * 比较查询速度差别（电脑性能不够的同学可以适当少插入一些数据，以免插入的时间太长）
 *
 * @author zxcsjf
 * @since 2022/07/26 21:45
 */
public class Ex2 {
    static Connection connection = JDBCUtils2.getConnection();

    public static void main(String[] args) throws SQLException {
        // int nums = 10_000_000;
        // long t1 = System.currentTimeMillis();
        //
        // testForInsert(nums);
        // long t2 = System.currentTimeMillis();
        //
        // insertUseStatementBatch(nums);
        //
        // long t3 = System.currentTimeMillis();
        // insertUsePreparedStatement(nums);
        // long t4 = System.currentTimeMillis();
        //
        // System.out.println("foreach循环插入的时间是：" + (t2 - t1) + " ms");
        // System.out.println("statement批量插入的时间是：" + (t3 - t2) + " ms");
        // System.out.println("PreparedStatement批量插入的时间是：" + (t4 - t3) + " ms");

        Connection connection = JDBCUtils2.getConnection();
        long t5 = System.currentTimeMillis();
        Statement statement = connection.createStatement();
        statement.executeQuery("SELECT * FROM user WHERE id = 5000000");
        statement.getResultSet();
        long t6 = System.currentTimeMillis();

        System.out.println("按主键查询时间：" + (t6 - t5) + "ms");

        Connection connection2 = JDBCUtils2.getConnection();
        long t7 = System.currentTimeMillis();
        Statement statement2 = connection.createStatement();
        statement2.executeQuery("SELECT * FROM user WHERE name = 'preparedStatement:5000000'");
        statement2.getResultSet();
        long t8 = System.currentTimeMillis();
        System.out.println("按普通字段查询时间：" + (t8 - t7) + "ms");
    }


    // 3. 用preparedStatement，2次通信，1次编译，1次执行
    private static void insertUsePreparedStatement(int nums) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into user (name) values (?)");

        for (int i = 0; i < nums; i++) {
            String name = "preparedStatement:" + i;
            // 设置参数
            preparedStatement.setString(1, name);

            // 添加到批处理
            preparedStatement.addBatch();
        }

        // 发送参数，执行SQL语句
        preparedStatement.executeBatch();
        preparedStatement.close();
    }

    // 2. 用statementBatch，1次通信，n次编译，n次执行
    private static void insertUseStatementBatch(int nums) throws SQLException {
        Statement statement = connection.createStatement();

        for (int i = 0; i < nums; i++) {
            String name = "statementBatch:" + i;

            // 添加SQL语句
            statement.addBatch("insert into user (name) values ( '" + name + "')");
        }
        // 执行SQL语句
        statement.executeBatch();
        // 关闭资源
        statement.close();
    }

    // 1. for循环实现，n次通信，n次编译，n次执行
    public static void testForInsert(int nums) throws SQLException {
        for (int i = 0; i < nums; i++) {
            Statement statement = connection.createStatement();
            String name = "forloop:" + i;
            statement.executeUpdate("insert into user (name) values ( '" + name + "')");
            statement.close();
        }

    }

}
