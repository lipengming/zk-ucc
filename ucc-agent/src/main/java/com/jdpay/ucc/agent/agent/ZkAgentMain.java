/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.agent.agent;

import com.jdpay.ucc.agent.Constant;
import com.jdpay.ucc.agent.ZkRegister;
import com.jdpay.ucc.agent.ZkRegisterFactory;
import com.jdpay.ucc.agent.anno.ZkTypeConfigurable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.agent.agent <br>
 * <b>类名称</b>： ZkAgentMain <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/3 16:19<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ZkAgentMain extends Agent {
    protected final static Logger _LOG = LoggerFactory.getLogger(ZkAgentMain.class);

    public static void premain(String agentArgs, final Instrumentation inst) {
        String servers = System.getProperty(Constant.zk, "localhost:2181").trim();
        if (null == servers || servers.isEmpty()) {
            return;
        }
        final ZkRegister register = ZkRegisterFactory.getZkRegister(servers);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000L);//默认10s后启动解析器
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resolve(inst,register);
            }
        }).start();
    }

    public static void resolve(Instrumentation inst,ZkRegister register){
        try{
            for(Class clazz : inst.getAllLoadedClasses()){
                ZkTypeConfigurable typeConfigurable = (ZkTypeConfigurable) clazz.getAnnotation(ZkTypeConfigurable.class);
                if(null != typeConfigurable) {
                    //解析
                    register.register(clazz,true);
                }
            }
        }catch (Exception e) {
            _LOG.info("error!",e);
        }
    }
}
