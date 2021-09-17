package com.zxd.www.util.dpool.config;

/**
 * 生命周期管理
 * @author Makonike
 * @date 2021-09-11 10:54
 **/
public interface ILifeCycle {
    /**
     * 初始化
     */
    void init();

    /**
     * 销毁
     * 这个暂时不写，因为连接关闭是因为一个连接的创建和销毁需要花费很长时间，且不需使用时会占内存空间
     * 其次是连接有一定限制，如果连接不及时关闭可能会导致数据库崩溃
     * 而数据库连接池解决了这些问题
     */
    void destroy();
}
