package homework;

import connectionpool.opensourcedbcp.C3p0Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 不使用配置文件的c3p0
 * 增
 * @author zxcsjf
 * @since 2022/07/28 22:02
 */
public class DemoC3p0Utils3 {
    public static void main(String[] args) throws SQLException {

        // 1.获取连接
        Connection connection = C3p0Utils.getConnection();

        // 2.创建statement对象
        Statement statement = connection.createStatement();

        // 3.发送sql语句
        int affectedRows = statement.executeUpdate("insert into account values(1011, '单纪飞', 18000)");

        // 4.解析结果集
        System.out.println("affectedRows = " + affectedRows);

        // 5.关闭资源
        statement.close();

        // 6.返回链接到连接池
        //      在第三方开源的数据库连接池的使用中，不需要手动去调用返回连接的方法.
        connection.close();

    }
}
