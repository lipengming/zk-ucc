/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.ucc.console.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.shouli1990@gmail. <br>
 * <b>类名称</b>： DBManager <br>
 * <b>类描述</b>： 由于测试过程中，部分数据库操作没有现成的接口，所以，可以使用jdbc直接操作<br>
 *                 例如：测试用例完成后，需要清理现场，这样的场合就可以直接写sql来搞定。
 *                 示例：参考测试代码：DBManagerTest.java
 *                 说明：所有此类中的一次，均打印出堆栈，方便找到错误
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2014/12/17 11:01
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class DBManager<T> {
    private final static Logger logger = LoggerFactory.getLogger(DBManager.class);

    /**
     * 获取数据库连接
     * @return
     */
    public Connection getConnection() {
        return ConnectionFactory.getConnection();
    }

    /**
     * 执行insert update delete SQl
     * @param sql SQL语句
     * @param params 参数列表
     * @return
     */
    public int executeSQL(String sql, Object... params) {
    	logger.info(String.format("executeSQL:"+sql.replace("?", "%s"),params));
        Connection conn = getConnection();
        PreparedStatement ps = null;
        int rows = 0;
        try {
            ps = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {

                    ps.setObject(i + 1, params[i]);
                }
            }
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(ps, conn);
        }
        return rows;
    }

    /**
     * 根据Select查询产生Object对象
     * @param sql
     * @param map
     * @param params
     * @return
     */
    public T queryForObject(String sql, IRowMap<T> map, Object... params) {
    	logger.info(String.format("executeSQL:"+sql.replace("?", "%s"),params));
        T obj = null;
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                obj = map.mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, conn);
        }
        return obj;
    }

    /**
     * 根据SQL查询 返回int类型结果
     * @param sql
     * @param params
     * @return
     */
    public int queryForInt(String sql, Object... params) {
    	logger.info(String.format("executeSQL:"+sql.replace("?", "%s"),params));
        int obj = 0;
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);

            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }

            rs = ps.executeQuery();
            if (rs.next()) {
                obj = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, conn);
        }
        return obj;
    }

    /**
     * 根据Select查询产生List集合
     * @param sql
     * @param map
     * @param params
     * @return
     */
    public List<T> queryForList(String sql, IRowMap<T> map, Object... params) {
    	logger.info(String.format("executeSQL:"+sql.replace("?", "%s"),params));
        List<T> list = null;
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);

            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<T>();
                }
                T obj = map.mapRow(rs);
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, conn);
        }
        return list;
    }

    /**
     * 关闭资源
     * @param ps
     * @param conn
     */
    public void close(PreparedStatement ps, Connection conn) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    /**
     * 关闭资源
     * @param rs
     * @param ps
     * @param conn
     */
    public void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}
