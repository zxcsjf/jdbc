package dbutils;

import bean.User;
import connectionpool.opensourcedbcp.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *创建一个User表，使用DBUtils进行增删改查
 *
 * 并查询需要使用上课讲到的BeanHandler、BeanListHandler、ColumnListHandler、ScalarHandler 只有查询需要用到这些，
 * 如果是：增、删、改 直接用  queryRunner.update()
 * @author zxcsjf
 * @since 2022/07/28 19:37
 */
public class QueryDemo1 {
    public static void main(String[] args) throws SQLException {

        // 获取数据库连接池
        DataSource dataSource = DruidUtils.getDataSource();

        // 构建QueryRunner
        QueryRunner queryRunner = new QueryRunner(dataSource);

        // 执行SQL语句

        // 1. BeanHandler 这个类可以帮助我们把结果集的第一行记录 解析为单个JavaBean。
        User user = queryRunner.query("select * from user", new BeanHandler<>(User.class));
        System.out.println(user);

        // 2. BeanListHandler 这个类可以帮助我们把结果集解析为一个Bean的List。
        List<User> users = queryRunner.query("select * from user", new BeanListHandler<>(User.class)); // 查
        System.out.println(users);
        int affectedRows = queryRunner.update("update user set name = '景天' where name = '长风'"); // 改
        int affectedRows2 = queryRunner.update("insert into user values(3, '景天', '奶茶', 'male', '1996-10-03', '中国')"); // 增
        int affectedRows3 = queryRunner.update("delete from user where id = 3"); // 删

        System.out.println("affectedRows = " + affectedRows);
        System.out.println("affectedRows2 = " + affectedRows2);
        System.out.println("affectedRows3 = " + affectedRows3);


        // 3.MapHandler 可以把查询结果的第一行记录转化为一个map，在这个map中，key列名；value：这一列对应的值。
        Map<String, Object> map = queryRunner.query("select * from user", new MapHandler());
        System.out.println(map);

        // 4.MapListHandler 可以把结果转化为一个Map的List。在这个List中，有多个Map，每一个Map代表结果集中的一行记录。
        List<Map<String, Object>> maps = queryRunner.query("select * from user", new MapListHandler());
        System.out.println(maps);



        // 5.ColumnListHandler 其实这个Handler是针对查询结果是单列值的这么一种情况的。
        //      当查询的结果是单列值的时候，可以把单列值封装到一个List中去。
        List<Object> columnList = queryRunner.query("select * from user", new ColumnListHandler<>());
        System.out.println(columnList);



        // 6.这个Handler是用来接收SQL语句的结果是单个值的情况的。
        Long num = queryRunner.query("select count(*) from user;", new ScalarHandler<>());
        int num2 = queryRunner.update("update user set name = '景天' where name = '长风' "); // 改
        int num3 = queryRunner.update("update user set name = '景天' where name = '长风' "); // 改
        System.out.println(num);
        System.out.println(num2);
        System.out.println(num3);


    }
}
