/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.agent.resover;

import com.jdpay.ucc.agent.ExtendDataStore;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.agent.resover <br>
 * <b>类名称</b>： RedisExtendDataStore <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/6 8:53<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
@Deprecated
public class RedisExtendDataStore implements ExtendDataStore {
    @Override
    public void setValue(String key, Object o) {

    }

    @Override
    public Object getValue(String key) {
        return null;
    }
}
