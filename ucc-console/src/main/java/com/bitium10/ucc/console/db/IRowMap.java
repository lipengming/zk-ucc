/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.ucc.console.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <b>项目名</b>： wallet-customer <br>
 * <b>包名称</b>： com.wangyin.customer.common <br>
 * <b>类名称</b>： IRowMap <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2014/12/17 11:03
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public interface IRowMap<T> {
    T mapRow(ResultSet rs)throws SQLException;
}
