/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.agent;

import com.jdpay.ucc.agent.anno.ZkFieldConfigurable;
import com.jdpay.ucc.agent.anno.ZkTypeConfigurable;
import com.jdpay.ucc.agent.exception.ConfigureException;
import com.jdpay.ucc.agent.listener.DataChangeListener;
import com.jdpay.ucc.agent.operator.Updater;
import com.jdpay.ucc.agent.resover.DefaultResolver;
import com.jdpay.ucc.agent.utils.StringZkSerializer;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.agent <br>
 * <b>类名称</b>： ZKRegister <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/2 20:52<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ZkRegister {
    private static final Map<String, ZkClient> cache = Collections.synchronizedMap(new WeakHashMap<String, ZkClient>(5));

    protected final static Logger _LOG = LoggerFactory.getLogger(ZkRegister.class);
    private final String globalZkServer;//全局的zk server 配置
    private final static int timeOut = 50000;

    public ZkRegister(String globalZkServer) {
        this.globalZkServer = globalZkServer;
    }

    public final synchronized void register(final Class<?> clazz,final boolean forceWhenNull) {
        if(!clazz.isAnnotationPresent(ZkTypeConfigurable.class)) {
            throw new ConfigureException("Not Register!");
        }

        ZkTypeConfigurable type = clazz.getAnnotation(ZkTypeConfigurable.class);
        final String server = type.useOwnServer() ? type.servers() : globalZkServer;
        if(server == null || server.equals("")) {
            _LOG.info("zk server must not null!");
            System.exit(0);
        }

        ZkClient zkClient = makeZkClient(server,timeOut);
        String root = type.path().trim();
        if(null == root || "".equals(root)) {
            root = clazz.getPackage().getName().replaceAll("\\.", "/");
        }
        //构造zk 路径
        final String path = getZkPath(root,clazz.getSimpleName());

        //开始遍历field
        {
            final Field[] fields = clazz.getDeclaredFields();
            for(Field f : fields) {
                if (!f.isAnnotationPresent(ZkFieldConfigurable.class)) {
                    continue;
                }
                _LOG.debug("field:" + f.getName() + "type:" + f.getType().getSimpleName());
                ZkFieldConfigurable field = f.getAnnotation(ZkFieldConfigurable.class);

                String fieldPath = "".equals(field.path()) ? getZkPath(path,f.getName()) : getZkPath(path,field.path());
                String value = zkClient.readData(fieldPath, true);
                _LOG.debug("ZK PATH :" + fieldPath + " value:" + value);

                Resolver resolver = null;
                try {
                    resolver = field.resolver().getConstructor(Class.class, Field.class).newInstance(clazz,f);
                } catch (Exception e) {
                    _LOG.debug("get resolver fail!");
                    continue;
                }

                if(value == null && !forceWhenNull) {
                    continue;
                } else if(value == null && forceWhenNull) {
                    zkClient.createPersistent(fieldPath, true);
                    String defaultValue = resolver.get();
                    zkClient.writeData(fieldPath, defaultValue);
                } else {
                    //设置值
                    resolver.set(value);
                }

                //动态更新
                if(field.update()) {
                    _LOG.debug("FIELD DYNAMIC UPDATE:" + field);
                    Updater.register(fieldPath,resolver);
                    //zk订阅
                    zkClient.subscribeDataChanges(fieldPath,new DataChangeListener());
                }
                _LOG.debug("REGISTER OK!");
            }
        }

    }

    public String getZkPath(String parent,String pathName){
        final String separator = Constant.separator;
        if (!parent.startsWith(separator)) {
            parent = separator + parent;
        }
        if (!parent.endsWith(separator)) {
            parent = parent + separator;
        }
        if (pathName.startsWith(separator)) {
            pathName = pathName.substring(1);
        }
        return parent + pathName;
    }

    private final ZkClient makeZkClient(String server,int timeOut) {
        if (this.cache.containsKey(server)) {
            return this.cache.get(server);
        }
        final ZkClient zkClient = new ZkClient(server, timeOut, timeOut,new StringZkSerializer());
        this.cache.put(server, zkClient);
        return zkClient;
    }
}
