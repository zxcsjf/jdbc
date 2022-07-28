package connectionpool.opensourcedbcp;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 使用配置文件的方法
 *
 * @author zxcsjf
 * @since 2022/07/28 19:35
 */
public class C3p0Utils2 {
    static DataSource dataSource;

    static {
        // 创建数据库连接池
        // 44th不配置的话，那么就会使用配置文件中的 <default-config> 标签中的配置
        dataSource = new ComboPooledDataSource("43th");

    }

    // 获取连接的方法
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }



}
