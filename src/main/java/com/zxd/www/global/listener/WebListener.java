package com.zxd.www.global.listener;

import com.zxd.www.dpool.datasource.PooledDataSource;
import com.zxd.www.util.JdbcUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Makonike
 * @date 2021-09-13 21:55
 **/
public class WebListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        PooledDataSource dataSource = JdbcUtils.getDataSource();
        dataSource.destroy();
        System.out.println("tomcat shutdown");
    }
}
