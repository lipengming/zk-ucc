/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.ucc.console.db.map;

import com.bitium10.ucc.console.db.IRowMap;
import com.bitium10.ucc.core.dto.ConfigField;
import com.bitium10.ucc.core.dto.ConfigType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.bitium10.ucc.console.db.map <br>
 * <b>类名称</b>： ConfigFieldRowMap <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/7 13:39<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ConfigFieldRowMap implements IRowMap<ConfigField> {
    @Override
    public ConfigField mapRow(ResultSet rs) throws SQLException {
        ConfigField configField = new ConfigField(rs.getString("fieldName"),rs.getString("fieldType"));
        configField.setPath(rs.getString("path"));
        configField.setUpdate(BooleanType.valueOf(rs.getString("update")));
        configField.setId(rs.getLong("id"));
        configField.setResolverClass(rs.getString("resolverClass"));

        configField.setTempKey(rs.getString("tempkey"));
        configField.setExtendClass(rs.getString("extendClass"));
        return configField;
    }
}
