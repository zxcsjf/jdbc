package dbutils;

import connectionpool.opensourcedbcp.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zxcsjf
 * @since 2022/07/28 19:36
 */
public class DButilsDemo1 {
    public static void main(String[] args) throws SQLException {

        // 获取连接
        Connection connection = DruidUtils.getConnection();


        // 创建一个QueryRunner
        // 无参构造.
        QueryRunner queryRunner = new QueryRunner();


        // 执行SQL语句 ，传入connection对象
        // int affectedRows = queryRunner.update(connection, "delete from account where id = 1005");
        // int affectedRows = queryRunner.update(connection,"update account set money = ? where id = ?",5000,1003);

        // 会报错
        int affectedRows = queryRunner.update("update account set money = ? where id = ?",2000,1001);


        System.out.println("affectedRows = " + affectedRows);

        // 回收连接
        connection.close();


    }
}
