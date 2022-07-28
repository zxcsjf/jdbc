package jdbc;

import connectionpool.MyConnectionPoolV2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用V2
 * 自己写的数据库连接池，操作数据库
 * @author zxcsjf
 * @since 2022/07/28 19:37
 */
public class DemoMyConnectionPoolV2 {
    public static void main(String[] args) throws SQLException {

        // 1.获取连接
        Connection connection = MyConnectionPoolV2.getConnection();

        // 2.创建statement对象
        Statement statement = connection.createStatement();

        // 3.发送sql语句,获得结果集
        ResultSet resultSet = statement.executeQuery("select * from account");

        // 4.解析结果集
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int money = resultSet.getInt("money");
            System.out.println("id:" + id + ", name:" + name  + ", money:" + money);
        }

        // 5.关闭资源
        // JDBCUtils.closeResources(statement, connection, resultSet);
        resultSet.close();
        statement.close();

        // 6.返回链接到连接池
        MyConnectionPoolV2.recycleConnection(connection);
    }
}
