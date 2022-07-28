package connectionpool.myconnectionpool;

import util.JDBCUtils;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 自己实现的数据库连接池 V1
 *
 * @author zxcsjf
 * @since 2022/07/28 19:36
 */
public class MyConnectionPoolV1 {
    // 维护一个容器 这个容器里面放的是连接对象
    // 从头部存，从尾部取

    // 定义一个数据库连接池
    static LinkedList<Connection> pool;

    static {
        // 初始化
        pool = new LinkedList<>();
        // 添加连接
        for (int i = 0; i < 10; i++) {
            Connection connection = JDBCUtils.getConnection();
            pool.addFirst(connection);
        }
    }

    // 1. 从连接池获取连接的方法
    public static Connection getConnection() {
        Connection connection = pool.removeLast();
        return connection;
    }

    // 2. 把连接放回连接池的方法
    public static void recycleConnection(Connection connection) {
        pool.addFirst(connection);
    }
}
