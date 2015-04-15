/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.ucc.core;

import org.apache.zookeeper.KeeperException;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.bitium10.ucc.core <br>
 * <b>类名称</b>： ZkSessionListener <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/1 20:39<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public interface ZkSessionListener {
    /**
     * 监听会话创建（zkClient连接server）、会话超时重连
     * <p>
     *     <b>所有：EPHEMERAL类型节点，节点：exists/getData/getChildren 的所有watchers在session重连后会被再次创建 </b>
     * </p>
     * @param client ZkClient 实例
     *
     * @throws org.apache.zookeeper.KeeperException
     * @throws InterruptedException
     */
    void onSessionCreated(ZkClient client) throws KeeperException, InterruptedException;
}
