package com.zxd.www.util;

import com.zxd.www.dpool.datasource.PooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Makonike
 * @date 2021-09-12 16:08
 **/
public class JdbcUtils {

    private final static PooledDataSource dataSource = new PooledDataSource();

    public static Connection getConnection(){
        return dataSource.getConnection();
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用于在非查询操作后释放资源
     * @param stmt stmt
     * @param conn 连接
     */
    public static void close(Statement stmt, Connection conn) {
        close(null,stmt,conn);
    }


    public static DataSource getDataSource(){
        return dataSource;
    }

}
