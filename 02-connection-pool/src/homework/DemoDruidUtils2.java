package homework;

import connectionpool.opensourcedbcp.DruidUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用Druid
 * 增 insert into 表名 [col1,col2,col3 ...] values (value1,value2,value3 ...),(value1,value2,value3 ...);
 *
 * @author zxcsjf
 * @since 2022/07/28 22:16
 */
public class DemoDruidUtils2 {
    public static void main(String[] args) throws SQLException {
        // 1.获取连接
        Connection connection = DruidUtils.getConnection();

        // 2.创建statement对象
        Statement statement = connection.createStatement();

        // 3.发送sql语句
        int affectedRows = statement.executeUpdate("insert into account value(1012, '单纪飞', 19000)");

        // 4.解析结果集
        System.out.println("affectedRows = " + affectedRows);

        // 5.关闭资源
        statement.close();

        // 6.返回链接到连接池
        //      在第三方开源的数据库连接池的使用中，不需要手动去调用返回连接的方法.
        connection.close();

    }
}
