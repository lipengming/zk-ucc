/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.console.db.map;

import com.jdpay.ucc.console.db.IRowMap;
import com.jdpay.ucc.core.dto.App;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.console.db.map <br>
 * <b>类名称</b>： AppRow <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/7 14:55<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class AppRow implements IRowMap<App> {
    @Override
    public App mapRow(ResultSet rs) throws SQLException {
        App app = new App();
        app.setId(rs.getLong("id"));
        app.setAppName(rs.getString("appName"));
        app.setDescription(rs.getString("description"));
        return app;
    }
}
