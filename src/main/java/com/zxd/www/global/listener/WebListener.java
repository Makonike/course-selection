package com.zxd.www.global.listener;

import com.zxd.www.util.JdbcUtils;
import com.zxd.www.util.dpool.datasource.PooledDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
