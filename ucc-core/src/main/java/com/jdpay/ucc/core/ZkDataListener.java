/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.core;

import org.apache.zookeeper.Watcher;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.core <br>
 * <b>类名称</b>： ZkDataListener <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/2 21:52<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public interface ZkDataListener {
    /**
     * 当监听的节点发送数据变动时出发该方法
     *
     * @param dataPath
     * @param newData
     */
    void onDataChanged(String dataPath,byte[] newData);

    /**
     * 当监听的节点数据被删除时触发
     *
     * @param dataPath
     */
    void onDataDeleted(String dataPath);
}
