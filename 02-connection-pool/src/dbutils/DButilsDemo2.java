package dbutils;

import connectionpool.opensourcedbcp.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zxcsjf
 * @since 2022/07/28 19:36
 */
public class DButilsDemo2 {
    public static void main(String[] args) throws SQLException {

        // 获取连接
        Connection connection = DruidUtils.getConnection();


        // 创建一个QueryRunner
        // 传入数据库连接池
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

        // 执行SQL语句
        // 执行SQL的时候可以不传连接对象
        int affectedRows = queryRunner.update("update account set money = ? where id = ?", 2000, 1001);


        System.out.println("affectedRows = " + affectedRows);

        // 回收连接
        connection.close();


    }
}
