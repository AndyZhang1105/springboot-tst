package com.zz.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.zz.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class DataService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    DruidDataSource druidDataSource;

    public String getData() {
        String rString = "";

        try {
            Connection conn = druidDataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ORDER_HEADER") ;

            //使用结果集（ResultSet）对象的访问方法获取数据：
            while(rs.next()) {
                rString += rs.getString("order_id") + ",";
            }

            //操作完成以后关闭JDBC对象,要把所有使用的JDBC对象全都关闭，以释放JDBC资源，关闭顺序和声明顺序相反：
            //关闭顺序1、关闭记录集,2、关闭声明,3、关闭连接对象
            DbUtil.close(conn, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        logger.info("Data fetching is successful.");

        return rString;
    }
}
