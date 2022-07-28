package test;

import connectionpool.opensourcedbcp.DBCPUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 用dbcp
 *
 * @author zxcsjf
 * @since 2022/07/28 21:55
 */
public class DemoDBCPUtils {
    public static void main(String[] args) throws SQLException {

        // 1.获取连接
        Connection connection = DBCPUtils.getConnection();

        // 2.创建statement对象
        Statement statement = connection.createStatement();

        // 3.发送sql语句
        ResultSet resultSet = statement.executeQuery("select * from account");

        // 4.解析结果集
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int money = resultSet.getInt("money");
            System.out.println("id:" + id + ", name:" + name  + ", money:" + money);
        }

        // 5.关闭资源
        resultSet.close();
        statement.close();

        // 6.返回链接到连接池
        //      在第三方开源的数据库连接池的使用中，不需要手动去调用返回连接的方法.
        connection.close();

    }
}
