package connectionpool.opensourcedbcp;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author zxcsjf
 * @since 2022/07/28 19:36
 */
public class DruidUtils {
    static DataSource dataSource;

    static {

        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception ex) {
            ex.printStackTrace();
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

    public static DataSource getDataSource() {
        return dataSource;
    }
}
