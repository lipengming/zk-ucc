/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.ucc.agent.agent;

import com.bitium10.ucc.agent.Constant;

import java.lang.instrument.Instrumentation;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.bitium10.ucc.agent.agent <br>
 * <b>类名称</b>： ZkConfigPropertiesAgent <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/2 23:27<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ZkConfigPropertiesAgent  extends Agent {

    /**
     * 启动方式：
     * -javaagent:agent.jar -DZK=localhost:2181 -DCLASS=c1,c2,c3
     *
     * @param args
     * @param inst
     */
    public static void premain(String args, Instrumentation inst) {
        String servers = System.getProperty(Constant.zk, "localhost:2181").trim();
        String clazz = System.getProperty(Constant.clazz, "").trim();
        agent(servers,clazz);
    }
}
