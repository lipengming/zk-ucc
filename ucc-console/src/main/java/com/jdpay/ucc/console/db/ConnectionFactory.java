/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.console.db;

import com.jdpay.ucc.console.utils.Config;
import com.jdpay.ucc.console.utils.Constant;
import com.wangyin.commons.cp.WangyinCPDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.console.db <br>
 * <b>类名称</b>： ConnectionFactory <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/7 13:12<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ConnectionFactory {
    private final static Logger logger = LoggerFactory.getLogger(DBManager.class);
    public final static WangyinCPDataSource dataSource ;

    static {
        dataSource = new WangyinCPDataSource();
        dataSource.setDriver(Config._CONFIG.get(Constant.DRIVER));
        dataSource.setUrl(Config._CONFIG.get(Constant.URL));
        dataSource.setUsername(Config._CONFIG.get(Constant.USERNAME));
        dataSource.setPassword(Config._CONFIG.get(Constant.PASSWORD));
        dataSource.setMinConnections(1);
        dataSource.setMaxConnections(5);
        dataSource.setCheckoutTimeoutMilliSec(3000L);
    }

    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.debug("get connections error!",e);
            return null;
        }
    }
}
