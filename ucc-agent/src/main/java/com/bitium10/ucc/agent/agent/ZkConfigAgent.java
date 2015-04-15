/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.ucc.agent.agent;



import com.bitium10.ucc.agent.Constant;
import com.bitium10.ucc.agent.ZkRegister;
import com.bitium10.ucc.agent.ZkRegisterFactory;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.bitium10.ucc.agent <br>
 * <b>类名称</b>： ZkConfigAgent <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/2 10:00<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ZkConfigAgent extends Agent {
    /**
     * 启动方式：
     * -javaagent:agent.jar=zk@localhost:2181#class@com.bitium10.ucc.Demo
     *
     * @param args
     *          启动参数，格式:ZK@s1,s2#CLASS@c1,c2
     * @param inst
     *
     */
    public static void premain(String args, Instrumentation inst) {
        String[] items = args.replaceAll("\\s","").split("#");
        if(items == null || items.length < 1) {
            return;
        }
        Map<String,String> map = new HashMap<String, String>(2);
        for(String item : items) {
            String[] arr = item.split("@");
            if (null != arr && arr.length >= 2) {
                map.put(arr[0], arr[1]);
            }
        }
        agent(map.get(Constant.zk),map.get(Constant.clazz));
    }
}
