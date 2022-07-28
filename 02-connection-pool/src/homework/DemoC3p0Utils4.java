package homework;

import connectionpool.opensourcedbcp.C3p0Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 不使用配置文件的c3p0
 * 删 delete from 表名 [where where_condition];
 * @author zxcsjf
 * @since 2022/07/28 22:02
 */
public class DemoC3p0Utils4 {
    public static void main(String[] args) throws SQLException {

        // 1.获取连接
        Connection connection = C3p0Utils.getConnection();

        // 2.创建statement对象
        Statement statement = connection.createStatement();

        // 3.发送sql语句
        int affectedRows = statement.executeUpdate("delete from account where id = 1011");

        // 4.解析结果集
        System.out.println("affectedRows = " + affectedRows);

        // 5.关闭资源
        statement.close();

        // 6.返回链接到连接池
        //      在第三方开源的数据库连接池的使用中，不需要手动去调用返回连接的方法.
        connection.close();

    }
}
