/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.ucc.console.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.bitium10.ucc.console.utils.Config;
import com.bitium10.ucc.console.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.bitium10.ucc.console.db <br>
 * <b>类名称</b>： ConnectionFactory <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/7 13:12<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ConnectionFactory {
    private final static Logger logger = LoggerFactory.getLogger(DBManager.class);
    public final static DruidDataSource ds = new DruidDataSource();

    static {
        ds.setDriverClassName(Config._CONFIG.get(Constant.DRIVER));
        ds.setUsername(Config._CONFIG.get(Constant.USERNAME));
        ds.setPassword(Config._CONFIG.get(Constant.PASSWORD));
        ds.setUrl(Config._CONFIG.get(Constant.URL));
        ds.setInitialSize(40); // 初始的连接数；
        ds.setMaxActive(40);
        ds.setMaxIdle(40);
        ds.setMinIdle(10);
        ds.setMaxWait(5);
    }

    public static Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            logger.debug("get connections error!",e);
            return null;
        }
    }
}
