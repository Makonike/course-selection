package com.zxd.www.dpool.datasource;

/**
 * @author Makonike
 * @date 2021-09-11 10:51
 **/
public abstract class AbstractDataSourceConfig extends DataSourceConfigAdaptor {

    protected String driverClass;
    protected String jdbcUrl;
    protected String username;
    protected String password;

    public String getDriverClass() {
        return driverClass;
    }

    @Override
    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    @Override
    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
