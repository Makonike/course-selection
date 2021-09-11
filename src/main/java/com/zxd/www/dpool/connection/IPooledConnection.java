package com.zxd.www.dpool.connection;


import com.zxd.www.dpool.config.IPooledDataSourceConfig;

import java.sql.Connection;

/**
 * 池化的连接池
 * @author Makonike
 * @date 2021-09-11 9:23
 **/
public interface IPooledConnection extends Connection {

    boolean isBusy();

    void setBusy(boolean busy);

    Connection getConnection();

    void remove();

    void setConnection(Connection connection);

    void setDataSource(final IPooledDataSourceConfig dataSource);

    IPooledDataSourceConfig getDataSource();
}
