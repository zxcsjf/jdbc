package homework;

import connectionpool.opensourcedbcp.DruidUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用Druid
 * 删：
 *
 * @author zxcsjf
 * @since 2022/07/28 22:16
 */
public class DemoDruidUtils3 {
    public static void main(String[] args) throws SQLException {
        // 1.获取连接
        Connection connection = DruidUtils.getConnection();

        // 2.创建statement对象
        Statement statement = connection.createStatement();

        // 3.发送sql语句
        int affectedRows = statement.executeUpdate("delete from account where id = 1012");

        // 4.解析结果集
        System.out.println("affectedRows = " + affectedRows);

        // 5.关闭资源
        statement.close();

        // 6.返回链接到连接池
        //      在第三方开源的数据库连接池的使用中，不需要手动去调用返回连接的方法.
        connection.close();

    }
}
