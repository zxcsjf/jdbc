package util;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author zxcsjf
 * @since 2022/07/28 11:35
 */
public class JDBCUtils {
    /** 1. 注册驱动
     *
     *  2. 可配置化
     *
     *  3. 关闭ResultSet
     *
     */

    static String url;
    static String username;
    static String password;
    static String driver; // 驱动的Driver路径

    // 给连接用的字段赋值
    static {
        try {
            // 读取配置文件
            Properties properties = new Properties();
            properties.load(new FileInputStream("jdbc.properties"));

            url = properties.getProperty("url");
            username = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");

            // 使用反射，注册驱动
            Class.forName(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取连接的方法 DriverManager.getConnection
    public static Connection getConnection() {
        Connection connection = null;

        // 工具类希望稳定，不抛出异常，而是要try catch
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    // 关闭连接的方法
    public static void closeResources(Statement statement, Connection connection, ResultSet resultSet) {
        try {
            // 如果不为空，就close
            if (resultSet != null) resultSet.close();
            if (statement!= null) statement.close();
            if (connection != null) connection.close();
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
