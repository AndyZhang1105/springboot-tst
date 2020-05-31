package com.zz.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil extends DbUtil {

    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static String driverClass = null;

    // 读取配置文件内容,放在静态代码块中就行,因为只需要加载一次就可以了
    static {
        try {
            Properties props = new Properties();

            //使用类路径加载的方式读取配置文件，读取的文件路径要以“/”开头,使用"/"开头会直接定位到工程的src路径下
            InputStream in = JdbcUtil.class.getResourceAsStream("/application.properties");

            props.load(in); //加载配置文件

            //读取配置文件信息
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
            driverClass = props.getProperty("db.driverClass");

            //动态注册mysql驱动程序
            Class.forName(driverClass);
            System.out.println("成功加载驱动程序");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("驱动程序注册失败！！！");
        }
    }

    // 获取连接对象Connection
    public static Connection getConnection(){
        try {
            // 要连接数据库,需要向java.sql.DriverManager请求并获得Connection对象
            return DriverManager.getConnection(url, user, password);
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
