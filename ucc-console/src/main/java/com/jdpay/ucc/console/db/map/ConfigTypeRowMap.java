/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.console.db.map;

import com.jdpay.ucc.console.db.IRowMap;
import com.jdpay.ucc.core.dto.ConfigType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.console.db.map <br>
 * <b>类名称</b>： ConfigTypeRowMap <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/7 13:20<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ConfigTypeRowMap implements IRowMap<ConfigType> {
    @Override
    public ConfigType mapRow(ResultSet rs) throws SQLException {
        ConfigType configType = new ConfigType(rs.getLong("aid"));

        configType.setPath(rs.getString("path"));
        configType.setServiceName(rs.getString("serviceName"));
        configType.setServiceType(rs.getString("serviceType"));
        configType.setUseOwnServers(BooleanType.valueOf(rs.getString("useOwnServers")));
        configType.setId(rs.getLong("id"));
        configType.setServers(rs.getString("servers"));

        return configType;
    }
}
