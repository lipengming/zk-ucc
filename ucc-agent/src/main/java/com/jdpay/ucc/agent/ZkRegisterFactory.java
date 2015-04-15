/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.agent;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.agent <br>
 * <b>类名称</b>： ZkRegisterFactory <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/2 23:18<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ZkRegisterFactory {
    private static final Map<String, ZkRegister> cache = Collections.synchronizedMap(new WeakHashMap<String, ZkRegister>(5));

    /**
     * 获取配置中心
     * @param servers
     * @return
     */
    public static synchronized ZkRegister getZkRegister(String servers) {
        if(!cache.containsKey(servers)) {
            ZkRegister register = new ZkRegister(servers);
            cache.put(servers,register);
        }
        return cache.get(servers);
    }
}
