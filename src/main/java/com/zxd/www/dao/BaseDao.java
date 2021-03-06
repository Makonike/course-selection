package com.zxd.www.dao;

import com.zxd.www.exception.DaoException;
import com.zxd.www.util.JdbcUtils;
import com.zxd.www.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础增删改查基类
 * @author Makonike
 * @date 2021-09-12 16:09
 */
public abstract class BaseDao {

    /**
     * 增删改操作
     * @param sql sql
     * @param args args
     * @return >0-成功
     */
    public int update(String sql, Object... args){
        Connection conn = JdbcUtils.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("预编译更新语句异常：" + sql, e);
        } finally {
            JdbcUtils.close(ps,conn);
        }
    }

    /**
     * 通用查询操作 单个
     * @param clazz 映射类
     * @param sql sql
     * @param args args
     * @param <T> T
     * @return T
     */
    public <T> T getOne(Class<T> clazz, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = execQuery(conn, sql, args);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                return pressIn(clazz, rs, rsmd, columnCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("预编译更新语句异常：" + sql, e);
        } finally {
            JdbcUtils.close(rs,ps,conn);
        }
        return null;
    }

    /**
     * 通用类，反射实例化对象并为每个字段赋值
     * @return 实例化对象
     */
    public <T> T pressIn(Class<T> clazz, ResultSet rs, ResultSetMetaData rsmd, int columnCount){
        try {
            // 实例化对象
            T t = clazz.newInstance();
            for (int i = 0; i < columnCount; i++) {
                // 从1开始为每个字段赋值
                Object columnValue = rs.getObject(i + 1);
                // 获取字段名
                String columnLabel = rsmd.getColumnLabel(i + 1);
                // 将字段名改为属性名
                columnLabel = StringUtils.changeColumnName(columnLabel);
                // 获取set方法名
                String setMethodName = StringUtils.changeToUpper(columnLabel);
                // 获取属性
                Field field = clazz.getDeclaredField(columnLabel);
                // 获取set方法
                Method method = clazz.getDeclaredMethod(setMethodName, field.getType());
                // 调用set方法
                method.invoke(t,columnValue);
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException(" 字段赋值失败 ", e);
        }
    }

    public PreparedStatement execQuery(Connection conn, String sql, Object... args){

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
    }


    /**
     * 获取
     * @param clazz 映射类
     * @param sql sql
     * @param args args
     * @param <T> T
     * @return list-T
     */
    public <T> List<T> getList(Class<T> clazz, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = execQuery(conn, sql, args);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            ArrayList<T> list = new ArrayList<>();
            while (rs.next()) {
                list.add(pressIn(clazz, rs, rsmd, columnCount));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("预编译更新语句异常：" + sql, e);
        } finally {
            JdbcUtils.close(rs,ps,conn);
        }
    }


    /**
     * 查询特殊值
     * @param sql sql
     * @param args args
     * @return object
     */
    @SuppressWarnings("all")
    public <T> List<T> getValue(Class<T> clazz, String sql,Object...args){
        Connection conn = JdbcUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1, args[i]);
            }

            ArrayList<T> list = new ArrayList<>();
            // 执行查询语句
            rs = ps.executeQuery();
            while (rs.next()){
                list.add((T) rs.getObject(1));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("预编译更新语句异常：" + sql, e);
        }finally{
            JdbcUtils.close(rs,ps,conn);
        }
    }
}