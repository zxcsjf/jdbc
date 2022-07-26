package utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author zxcsjf
 * @since 2022/07/26 14:53
 */
public class JDBCUtils2 {

    static Connection connection;

    // 1. 注册驱动
    static {
        try {
            // 可配置化
            Properties properties = new Properties();
            properties.load(new FileInputStream("jdbc.properties"));
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            // System.out.println(url);
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            // 通过反射注册驱动
            Class.forName(driver);
            // 建立链接
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2. 获取连接的方法
    public static Connection getConnection() {
        return connection;
    }

    // 3. 关闭资源的方法
    public static void closeResources(Statement statement, Connection connection, ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
