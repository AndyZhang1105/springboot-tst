package com.zz.mvctst.controller;

import com.zz.util.JdbcUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class JdbcController {

    @GetMapping("/jdbc/getData")
    public void getData() {
        Connection conn = JdbcUtil.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ORDER_HEADER") ;
            //使用结果集（ResultSet）对象的访问方法获取数据：
            while(rs.next()) {
                String name = rs.getString("order_id") ;
                System.out.println(name);
                String pass = rs.getString(1); //此方法比较高效,列是从左到右编号的，并且从列1开始
                System.out.println(pass);
            }
            //操作完成以后关闭JDBC对象,要把所有使用的JDBC对象全都关闭，以释放JDBC资源，关闭顺序和声明顺序相反：
            //关闭顺序1、关闭记录集,2、关闭声明,3、关闭连接对象
            JdbcUtil.close(conn, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
