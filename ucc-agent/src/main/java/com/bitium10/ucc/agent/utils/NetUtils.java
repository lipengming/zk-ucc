/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.ucc.agent.utils;

import java.net.InetSocketAddress;
import java.net.Socket;

import static com.bitium10.ucc.agent.utils.StringUtils.isEmpty;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.bitium10.ucc.agent.utils <br>
 * <b>类名称</b>： NetUtils <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/9 13:46<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class NetUtils {
    public static boolean isConnectAble(String all, int timeout) {
        if (!isEmpty(all) && timeout > 0) {
            try {
                String[] str = all.split("/")[0].split(":", 2);
                return isConnectAble(str[0], Integer.valueOf(str[1]), timeout);
            } catch (Throwable e) { }
        }
        return false;
    }

    public static boolean isConnectAble(String host, int port, int timeout) {
        boolean isReachable = false;
        Socket socket = null;
        try {
            socket = new Socket();
            InetSocketAddress endpointSocketAddr = new InetSocketAddress(host, port);
            socket.connect(endpointSocketAddr, timeout);
            isReachable = true;
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Throwable e) {}
            }
        }
        return isReachable;
    }
}
