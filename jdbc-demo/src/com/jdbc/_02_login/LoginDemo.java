package com.jdbc._02_login;

import utils.JDBCUtils2;

import java.sql.*;

/**
 * @author zxcsjf
 * @since 2022/07/26 20:44
 */
public class LoginDemo {
    public static void main(String[] args) throws SQLException {
        Boolean ret1 = login("changfeng", "xxx");

        Boolean ret2 = login("lJflksnfkl", "xxx' or '1=1");

        Boolean ret3 = loginUsePreparedStatement("lJflksnfkl", "xxx' or '1=1");

        if (ret1) {
            System.out.println("登录成功！");
        } else {
            System.out.println("登录失败！");
        }


    }

    // 使用预编译登录
    private static Boolean loginUsePreparedStatement(String username, String password) throws SQLException {
        // 获取连接
        Connection connection = JDBCUtils2.getConnection();

        // 获取PreparedStatement
        String sql = "select * from user where username = ? and password = ?";
        // 创建PreparedStatement的时候，JDBC会把这个SQL语句发送给MySQL服务器，
        // 让MySQL服务器对SQL语句进行预编译，后续再传过来的这些参数就不会参与编译了，
        // 只会被MySQL当成纯字符串来处理了
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 设置参数
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        // 发送参数，获得结果集
        ResultSet resultSet = preparedStatement.executeQuery(sql);

        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    // 根据用户名和密码登录的方法
    public static boolean login(String username, String password) throws SQLException {

        // 获取连接
        Connection connection = JDBCUtils2.getConnection();

        // 创建Statement
        Statement statement = connection.createStatement();

        // 执行SQL语句
        String sql = "select * from user where username = '" + username + "' and passward = '" + password + "'";
        ResultSet resultSet = statement.executeQuery(sql);

        // 判断结果集是否存在数据
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }

    }
}
