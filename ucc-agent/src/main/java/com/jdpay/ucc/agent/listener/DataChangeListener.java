/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.agent.listener;

import com.jdpay.ucc.agent.operator.Updater;
import org.I0Itec.zkclient.IZkDataListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.agent.listener <br>
 * <b>类名称</b>： DataChangeListener <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/2 23:02<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class DataChangeListener implements IZkDataListener {//ZkDataListener,
    private final static Logger _LOG = LoggerFactory.getLogger(DataChangeListener.class);

//    @Override
//    public void onDataChanged(String dataPath, byte[] newData) {
//        String data = new String(newData);
//        _LOG.warn("change event : " + dataPath + " data:" + data);
//        Updater.update(dataPath, data);
//    }
//
//    @Override
//    public void onDataDeleted(String dataPath) {
//        _LOG.warn("delete event : " + dataPath );
//        //TODO
//    }

    @Override
    public void handleDataChange(String dataPath, Object data) throws Exception {
        _LOG.warn("change event : " + dataPath + " data:" + data);
        Updater.update(dataPath, data.toString());
    }

    @Override
    public void handleDataDeleted(String s) throws Exception {

    }
}
