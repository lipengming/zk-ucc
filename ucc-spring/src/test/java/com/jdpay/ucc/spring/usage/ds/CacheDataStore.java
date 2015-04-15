/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.spring.usage.ds;

import com.jdpay.ucc.agent.ExtendDataStore;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.cuc.test.ds <br>
 * <b>类名称</b>： CacheDataStore <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/6 9:57<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class CacheDataStore implements ExtendDataStore<Map<String,String>> {
    public final static Map<String,Map<String,String>> _CACHE = new HashMap<String, Map<String,String>>(10);
    static {
        //init value
        Map<String,String> map = new HashMap<String, String>(4);
        map.put("d1","d1");
        map.put("d2","d2");
        map.put("d3","d3");
        map.put("d4","d4");
        _CACHE.put("key_words",map);

        Map<String,String> map1 = new HashMap<String, String>(4);
        map1.put("a1", "a1");
        map1.put("a2","a2");
        map1.put("a3","a3");
        map1.put("a4","a4");
        _CACHE.put("key_words1",map1);

        Map<String,String> map2= new HashMap<String, String>(4);
        map2.put("咚咚1","咚咚1");
        map2.put("咚咚2","咚咚2");
        map2.put("咚咚3","咚咚3");
        map2.put("咚咚4","咚咚4");
        _CACHE.put("key_words2",map2);
    }

    @Override
    public void setValue(String key, Map<String, String> map) {
        _CACHE.put(key,map);
    }

    @Override
    public Map<String, String> getValue(String key) {
        return _CACHE.get(key);
    }
}
