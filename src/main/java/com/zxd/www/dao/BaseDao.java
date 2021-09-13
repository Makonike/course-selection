package com.zxd.www.dao;

import com.zxd.www.util.JdbcUtils;
import com.zxd.www.util.StringUtils;

import java.lang.reflect.Field;
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
     * @return >0-成功 0-失败
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
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(ps,null);
        }
        return 0;
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
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,ps,null);
        }
        return null;
    }

    public <T> T pressIn(Class<T> clazz, ResultSet rs, ResultSetMetaData rsmd, int columnCount){
        try {
            T t = clazz.newInstance();
            for (int i = 0; i < columnCount; i++) {
                Object columnValue = rs.getObject(i + 1);
                String columnLabel = rsmd.getColumnLabel(i + 1);
                columnLabel = StringUtils.changeColumnName(columnLabel);
                Field field = clazz.getDeclaredField(columnLabel);
                field.setAccessible(true);
                field.set(t, columnValue);
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
        }
        return null;
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs,ps,null);
        }
        return null;
    }


    /**
     * 查询特殊值
     * @param sql sql
     * @param args args
     * @return object
     */
    public Object getValue(String sql,Object...args){
        Connection conn = JdbcUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.close(rs,ps,null);
        }
        return null;
    }
}