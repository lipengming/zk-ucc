/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.agent.operator;

import com.jdpay.ucc.agent.Resolver;
import com.jdpay.ucc.agent.exception.ConfigureException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.agent.operator <br>
 * <b>类名称</b>： Updater <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/2 21:03<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class Updater {
    private static final Map<String, Resolver> map = Collections.synchronizedMap(new WeakHashMap<String, Resolver>(5));

    /**
     * 注册一个更新
     * @param path
     * @param resolve
     */
    public static void register(String path, Resolver resolve){
        map.put(path, resolve);
    }

    /**
     * 塞值
     * @param path
     * @param value
     */
    public static void update(String path,String value) {
        Resolver resolver = map.get(path);
        if(resolver == null) {
            throw new ConfigureException("unknown resolver find:[path" + path +"]");
        }
        resolver.set(value);
    }
}
