package connectionpool.opensourcedbcp;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0 不使用配置文件的方法
 *
 * @author zxcsjf
 * @since 2022/07/28 19:35
 */
public class C3p0Utils {
    static ComboPooledDataSource dataSource;

    static {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        // 设置
        try {
            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/43th?useSSL=false&characterEncoding=utf8");
            comboPooledDataSource.setUser("root");
            comboPooledDataSource.setPassword("123456");

            // 非必须
            comboPooledDataSource.setInitialPoolSize(20);
            comboPooledDataSource.setMinPoolSize(2);

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        dataSource = comboPooledDataSource;
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
