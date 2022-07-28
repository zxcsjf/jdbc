package connectionpool;

import util.JDBCUtils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.Properties;

/**
 * 自己实现的数据库连接池
 * V2
 * 增加：扩容功能
 * 缺点：写死数据，不能动态调整
 *
 * V3
 * 增加：去掉魔法值、
 *       可配置
 * @author zxcsjf
 * @since 2022/07/28 19:36
 */
public class MyConnectionPoolV3 {
    // 维护一个容器 这个容器里面放的是连接对象
    // 从头部存，从尾部取

    // 定义一个数据库连接池
    static LinkedList<Connection> pool;
    static int INIT_SIZE = 10;
    static int MIN_SIZE = 2;
    static int INCREMENT = 5;

    static {
        // 读取配置文件
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("pool.properties"));

            String initSize = properties.getProperty("init_size");
            String minSize = properties.getProperty("min_size");
            String increment = properties.getProperty("increment");

            // 如果配置文件中，配置了，并且不为空字符串，就用配置文件的value
            if (initSize != null && increment != "") {
                INIT_SIZE = Integer.valueOf(initSize);
            }
            if (minSize != null && minSize != "") {
                MIN_SIZE = Integer.valueOf(minSize);
            }
            if (increment != null && increment != "") {
                INCREMENT = Integer.valueOf(increment);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取配置文件失败！！！");
        }



        // 初始化数据库连接池
        pool = new LinkedList<>();

        // 添加连接
        addCapacity(INIT_SIZE);
    }

    // 1. 从连接池获取连接的方法
    public static Connection getConnection() {
        // 判断是否要扩容
        if (pool.size() < MIN_SIZE) {
            addCapacity(INCREMENT);
        }

        Connection connection = pool.removeLast();
        return connection;
    }

    // 2. 把连接放回连接池的方法
    public static void recycleConnection(Connection connection) {
        pool.addFirst(connection);
    }

    private static void addCapacity(int nums) {

        if (pool == null) return;
        for (int i = 0; i < nums; i++) {
            Connection connection = JDBCUtils.getConnection();
            pool.addFirst(connection);
        }
    }
}
