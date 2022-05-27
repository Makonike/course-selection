package com.zxd.www.test;

import com.zxd.www.util.dpool.datasource.PooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

/**
 * @author Makonike
 * @date 2021-09-16 0:40
 **/
public class DataSourceTest {

    @Test
    public void dataSourceTest(){
        PooledDataSource source = PooledDataSource.getInstance();
        int threadCount = 100;
        CountDownLatch cdl = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    Connection conn = source.getConnection();
                    System.out.println(conn);
                    Thread.sleep(2000);
                    conn.close();
                } catch (InterruptedException | SQLException e) {
                    e.printStackTrace();
                } finally {
                    cdl.countDown();
                }
            }).start();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
