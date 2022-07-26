package com.jdbc.homework;

import utils.JDBCUtils2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 某大学研究生院有若干研究生导师，包括职工编号、姓名、职称、研究方向，其中每个导师的职工编号是唯一的。
 * 若干研究生，包括学号、姓名、性别、入学日期，其中每个研究生的学号是唯一的。
 * 每个导师可以带若干研究生，但每个研究生只能有一个导师。
 * 请设计一个数据库，要求可以正确体现导师和研究生之间的关系。
 * 设计完毕之后，请插入一定量的数据，并验证你设计的数据库是否满足要求。
 * <p>
 * 在你设计的表中插入若干数据。
 * <p>
 * 1.请查出每个导师所带研究生的姓名。
 * 2.清查出特定姓名的导师所带研究生的姓名。
 * 3.请查出每个导师所带研究生的数量。
 * 4.请查出每个导师所带的男研究生的数量。
 * 5.请找出选择哪个研究方向的导师最多。
 * 6.请找统计不同职称的导师的个数。
 * <p>
 * 这是昨天的其中一题，今天用JDBC来实现一遍
 *
 * @author zxcsjf
 * @since 2022/07/26 21:11
 */
public class Ex1 {

    public static void main(String[] args) throws SQLException {

        query1();
        System.out.println("-------------------");

        query2();
        System.out.println("-------------------");

        query3();
        System.out.println("-------------------");

        query4();
        System.out.println("-------------------");

        query5();
        System.out.println("-------------------");

        query6();
        System.out.println("-------------------");

        query7();
        System.out.println("-------------------");


    }

    private static void query7() throws SQLException {
        Connection connection = JDBCUtils2.getConnection();
        Statement statement = connection.createStatement();
        // -- 6.请统计不同职称下 导师的个数。
        statement.executeQuery("SELECT b.title, COUNT(b.name) AS '导师人数' FROM boss as b GROUP BY b.title");
        ResultSet resultSet = statement.getResultSet();
        // 解析结果集
        // 当游标成功向下移动的时候，返回true，当移动到最后一行之后，游标不能继续向下移动的时候，返回false
        while (resultSet.next()) {

            String bname = resultSet.getString("title");
            int num = resultSet.getInt("导师人数");

            System.out.println("职称 = " + bname + "；导师人数 = " + num);
        }
    }

    private static void query6() throws SQLException {
        Connection connection = JDBCUtils2.getConnection();
        Statement statement = connection.createStatement();
        // -- 5.请找出选择哪个研究方向的导师最多。
        statement.executeQuery("select direction ,count(*) as number from boss GROUP BY direction ORDER BY number desc LIMIT 1");

        ResultSet resultSet = statement.getResultSet();
        // 解析结果集
        // 当游标成功向下移动的时候，返回true，当移动到最后一行之后，游标不能继续向下移动的时候，返回false
        while (resultSet.next()) {

            String bname = resultSet.getString("direction");

            System.out.println("最多导师的研究方向 = " + bname);
        }
    }

    private static void query5() throws SQLException {
        Connection connection = JDBCUtils2.getConnection();
        Statement statement = connection.createStatement();

        // -- 4.请查出每个导师所带的男研究生的数量。
        statement.executeQuery("SELECT b.name AS '导师', COUNT(b.name)AS '男研究生数量' FROM boss AS b JOIN postgraduate AS p ON b.id = p.boss_id AND p.gender = 'male' GROUP BY b.id");
        ResultSet resultSet = statement.getResultSet();
        // 解析结果集
        // 当游标成功向下移动的时候，返回true，当移动到最后一行之后，游标不能继续向下移动的时候，返回false
        while (resultSet.next()) {

            String bname = resultSet.getString("导师");
            int num = resultSet.getInt("男研究生数量");

            System.out.println("导师 = " + bname + "；男研究生数量 = " + num);
        }
    }

    private static void query4() throws SQLException {
        Connection connection = JDBCUtils2.getConnection();
        Statement statement = connection.createStatement();

        // -- 3.请查出每个导师所带研究生的数量。
        statement.executeQuery("SELECT b.name AS '导师', COUNT(b.name)AS '研究生数量' FROM boss AS b INNER JOIN postgraduate AS p ON b.id = p.boss_id GROUP BY b.name");

        ResultSet resultSet = statement.getResultSet();
        // 解析结果集
        // 当游标成功向下移动的时候，返回true，当移动到最后一行之后，游标不能继续向下移动的时候，返回false
        while (resultSet.next()) {

            String bname = resultSet.getString("导师");
            int num = resultSet.getInt("研究生数量");

            System.out.println("导师 = " + bname + "；研究生数量 = " + num);
        }
        //
        // // 关闭资源
        // JDBCUtils2.closeResources(statement, connection, resultSet);
    }

    private static void query3() throws SQLException {
        Connection connection = JDBCUtils2.getConnection();

        Statement statement = connection.createStatement();
        // -- 2.清查出特定姓名的导师所带研究生的姓名。
        statement.executeQuery("SELECT b.name AS '导师', p.name AS '研究生' FROM boss AS b, postgraduate AS p WHERE b.id = p.boss_id AND b.name = '马宏昊'");

        ResultSet resultSet = statement.getResultSet();
        // 解析结果集
        // 当游标成功向下移动的时候，返回true，当移动到最后一行之后，游标不能继续向下移动的时候，返回false
        while (resultSet.next()) {

            String bname = resultSet.getString("导师");
            String cname = resultSet.getString("研究生");

            System.out.println("导师 = " + bname + "；研究生 = " + cname);

        }

        // 关闭资源
        // JDBCUtils2.closeResources(statement, connection, resultSet);
    }

    private static void query2() throws SQLException {
        Connection connection = JDBCUtils2.getConnection();

        Statement statement = connection.createStatement();

        // -- 1.请查出每个导师所带研究生的姓名。
        // -- b.用显示内连接,省略inner
        statement.executeQuery("SELECT b.name AS '导师', p.name AS '研究生' FROM boss AS b JOIN postgraduate AS p ON b.id = p.boss_id");

        ResultSet resultSet = statement.getResultSet();
        // 解析结果集
        // 当游标成功向下移动的时候，返回true，当移动到最后一行之后，游标不能继续向下移动的时候，返回false
        while (resultSet.next()) {

            String bname = resultSet.getString("导师");
            String cname = resultSet.getString("研究生");

            System.out.println("导师 = " + bname + "；研究生 = " + cname);

        }

        // 关闭资源
        // JDBCUtils2.closeResources(statement, connection, resultSet);
    }

    private static void query1() throws SQLException {
        Connection connection = JDBCUtils2.getConnection();

        Statement statement = connection.createStatement();

        // -- 1.请查出每个导师所带研究生的姓名。
        // -- a.用隐式内连接
        statement.executeQuery("SELECT b.name AS '导师', p.name AS '研究生' FROM boss AS b, postgraduate AS p WHERE b.id = p.boss_id");
        ResultSet resultSet = statement.getResultSet();
        // 解析结果集
        // 当游标成功向下移动的时候，返回true，当移动到最后一行之后，游标不能继续向下移动的时候，返回false
        while (resultSet.next()) {

            String bname = resultSet.getString("导师");
            String cname = resultSet.getString("研究生");

            System.out.println("导师 = " + bname + "；研究生 = " + cname);

        }

    }


}
