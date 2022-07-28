package connectionpool;

import util.JDBCUtils;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 自己实现的数据库连接池 V2
 * 增加：扩容功能
 * 缺点：写死数据，不能动态调整
 *
 * @author zxcsjf
 * @since 2022/07/28 19:36
 */
public class MyConnectionPoolV2 {
    // 维护一个容器 这个容器里面放的是连接对象
    // 从头部存，从尾部取

    // 定义一个数据库连接池
    static LinkedList<Connection> pool;

    static {
        // 初始化
        pool = new LinkedList<>();

        // 添加连接
        addCapacity(10);
    }

    // 1. 从连接池获取连接的方法
    public static Connection getConnection() {
        // 判断是否要扩容
        if (pool.size() < 2) {
            addCapacity(5);
        }

        Connection connection = pool.removeLast();
        return connection;
    }

    private static void addCapacity(int nums) {

        if (pool == null) return;

        for (int i = 0; i < nums; i++) {
            Connection connection = JDBCUtils.getConnection();
            pool.addFirst(connection);
        }
    }

    // 2. 把连接放回连接池的方法
    public static void recycleConnection(Connection connection) {
        pool.addFirst(connection);
    }
}
