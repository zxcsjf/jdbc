package connectionpool.opensourcedbcp;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用开源的数据库连接池 DBCP 的工具类
 * 只提供：获取连接的方法
 * 不提供：返回连接的方法
 * @author zxcsjf
 * @since 2022/07/28 19:36
 */
public class DBCPUtils {

    static DataSource dataSource;

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("dbcp.properties"));
            // dbcp的固定写法
            dataSource = BasicDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 获取连接的方法
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }









}
