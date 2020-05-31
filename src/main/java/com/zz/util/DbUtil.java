package com.zz.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {

    // 关闭连接的方法，后打开的先关闭
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        //关闭ResultSet对象
        if(rs != null) {
            try {
                //关闭rs，设置rs=null，因为java会优先回收值为null的变量
                rs.close();
                rs = null;
            } catch(SQLException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        //关闭Statement对象,因为PrepareStatement和CallableStatement都是Statement的子接口，所以这里只需要有关闭Statement对象的方法就可以了
        if(stmt != null) {
            try {
                stmt.close();
                stmt = null;
            } catch(SQLException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        //关闭Connection对象
        if(conn != null) {
            try {
                conn.close();
                conn = null;
            } catch(SQLException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }

}
