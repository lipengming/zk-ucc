/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.ucc.agent.agent;

import com.bitium10.ucc.agent.ZkRegister;
import com.bitium10.ucc.agent.ZkRegisterFactory;

import java.io.IOException;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.bitium10.ucc.agent.agent <br>
 * <b>类名称</b>： Agent <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/2 23:43<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public abstract class Agent {
    protected static void agent(final String servers, final String clas){
        if (null == servers || servers.isEmpty()) {
            return;
        }
        final String[] clazzes = clas.split(",");
        if (null == clazzes || clazzes.length == 0) {
            return;
        }
        final ZkRegister register = ZkRegisterFactory.getZkRegister(servers);
        for(String clzStr : clazzes) {
            Class clazz = getClass(clzStr,2);//循环取三次
            if(clazz != null) {
                register.register(clazz,true);
            }
        }
    }

    private static Class getClass(String clzStr,int time) {
        try {
            if(time < 1) return null;
            return Class.forName(clzStr);
        } catch (ClassNotFoundException e) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e1) {}
            return getClass(clzStr,time--);
        }
    }
}
