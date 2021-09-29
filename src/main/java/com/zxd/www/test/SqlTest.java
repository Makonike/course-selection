package com.zxd.www.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

/**
 * @author Makonike
 * @date 2021-09-10 16:31
 **/
public class SqlTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        //注册驱动：DriverManager.registerDriver(new com.mysql.jdbc.Driver());等同上面的
        InputStream in = SqlTest.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        properties.load(in);
        //2. 用户信息和 url
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        //3. 连接成功，数据库对象  Connection（url,用户,密码）
        Connection connection = DriverManager.getConnection(url, username, password);

        //4. 执行SQL的对象
        Statement statement = connection.createStatement();

        //5. 用执行SQL的对象去执行SQL语句
        String sql = "SELECT * FROM t_student";
        //返回的结果集,结果集中封装了我们全部的查询出来的结果
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            System.out.println("id=" + resultSet.getObject("student_id"));
            System.out.println("no=" + resultSet.getObject("student_no"));
            System.out.println("name=" + resultSet.getObject("student_name"));
            System.out.println("sex=" + resultSet.getObject("student_sex"));
            System.out.println("==================================");
        }
        //6、释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
