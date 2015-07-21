package com.bitium10.cuc.usage.ds;

import com.bitium10.ucc.agent.ExtendDataStore;

import java.util.Map;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.bitium10.cuc.usage.ds <br>
 * <b>类名称</b>： RedisDataStroe <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/21 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class RedisDataStroe implements ExtendDataStore<Map<String,String>> {
    @Override
    public void setValue(String key, Map<String, String> stringStringMap) {
        //todo set
    }

    @Override
    public Map<String, String> getValue(String key) {
        //todo get
        return null;
    }
}
