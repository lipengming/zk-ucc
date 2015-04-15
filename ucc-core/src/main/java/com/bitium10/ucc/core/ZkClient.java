/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.ucc.core;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.bitium10.ucc.core <br>
 * <b>类名称</b>： ZkClient <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/1 20:39<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ZkClient {
    private final String servers;
    private final int sessionTimeout;
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private volatile ZooKeeper zk;
    private final AtomicLong zkSessionId = new AtomicLong();
    private final List<ZkSessionListener> sessionListeners = new CopyOnWriteArrayList<ZkSessionListener>();

    public ZkClient(String servers, int sessionTimeout) throws InterruptedException, IOException {
        this.servers = servers;
        this.sessionTimeout = sessionTimeout;
        connectZk();
    }

    public void watchNode(final String path, final ZkDataListener listener){
        final Watcher dataGetter = new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                if (closed.get()){
                    return;
                }
                Event.EventType eventType = event.getType();
                switch (eventType) {
                    case NodeDataChanged:
                        try {
                            updateData(path,this,listener);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        }
                        break;
                    case NodeDeleted:
                        try {
                            deleteData(path,this,listener);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        };
    }

    private void updateData(String path, Watcher dataGetter, ZkDataListener listener) throws InterruptedException, KeeperException {
        try {
            listener.onDataChanged(path,zk.getData(path, dataGetter, null));
        } catch (KeeperException e) {
            deleteData(path, dataGetter, listener);
        }
    }

    public void deleteData(String path, Watcher dataGetter, ZkDataListener listener) throws KeeperException, InterruptedException {
        listener.onDataDeleted(path);
        if (zk.exists(path, dataGetter) != null) {
            // Node was re-created by the time we called zk.exist
            updateData(path, dataGetter, listener);
        }
    }

    private void connectZk() throws InterruptedException, IOException {
        final CountDownLatch connectionLatch = new CountDownLatch(1);
        final CountDownLatch assignLatch = new CountDownLatch(1);

        if (zk != null) {
            zk.close();
            zk = null;
        }
        zk = new ZooKeeper(servers, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (closed.get()) {
                    return;
                }
                try {
                    sessionEvent(assignLatch, connectionLatch, event);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
        });
        assignLatch.countDown();
        connectionLatch.await();
    }

    private void sessionEvent(final CountDownLatch assignLatch,
                              final CountDownLatch connectionLatch,
                              final WatchedEvent event) throws InterruptedException, IOException {
        assignLatch.await();
        switch (event.getState()) {
            case SyncConnected: {
                long newSessionId = zk.getSessionId();
                long oldSessionId = zkSessionId.getAndSet(newSessionId);

                if (oldSessionId != newSessionId) {
                    for (ZkSessionListener listener : sessionListeners) {
                        try {
                            listener.onSessionCreated(this);
                        } catch (Exception e) {
                        }
                    }
                }
                connectionLatch.countDown();
                break;
            }
            case Expired: {
                // Session was expired; create a new zookeeper connection
                connectZk();
                break;
            }
            default:
                // Disconnected -- zookeeper library will handle reconnects
                break;
        }
    }

    /**
     * 读取path的值
     *
     * @param path
     * @param returnNullIfNotExist
     * @return
     */
    public String readData(String path, boolean returnNullIfNotExist) {
        try {
            byte[] data = zk.getData(path, false, null);
            return new String(data);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void persistentNode(String fieldPath,String data) {
        try {
            zk.create(fieldPath, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void writeData(String fieldPath, String defaultValue) {
        byte[] data = defaultValue.getBytes();
        try {
            zk.setData(fieldPath, data, -1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
