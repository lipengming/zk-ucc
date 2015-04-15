/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.ucc.console.controller;

import com.bitium10.ucc.console.pojo.Node;
import com.bitium10.ucc.console.pojo.Result;
import com.bitium10.ucc.console.utils.Constant;
import com.bitium10.ucc.console.utils.StringZkSerializer;
import com.bitium10.ucc.console.utils.ZkUtils;
import org.I0Itec.zkclient.ZkClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.bitium10.ucc.console <br>
 * <b>类名称</b>： ZkController <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/5 20:04<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
@Path("zk-manager")
public class ZkController {
    private static ZkClient zkClient ;

    public ZkController(){
        zkClient = getZkClient();
    }

    @GET
    @Path("list")
    @Produces({MediaType.APPLICATION_JSON})
    public Node getAllNodes() {
        return ZkUtils.getNodeRecursive(getZkClient(), "/");
    }

    @GET
    @Path("detail")
    @Produces({MediaType.APPLICATION_JSON})
    public Node getNode(@QueryParam("path") String path) {
        return ZkUtils.getNode(getZkClient(),path);
    }

    @POST
    @Path("setData")
    @Produces({MediaType.APPLICATION_JSON})
    public Result setData(@FormParam("path") String path,
                          @FormParam("data") String data) {
        try{
            getZkClient().writeData(path,data);
            return new Result(true);
        }catch (Exception e){
            return new Result(false);
        }
    }

    @DELETE
    @Path("delete")
    @Produces({MediaType.APPLICATION_JSON})
    public Result removeNode(@FormParam("path") String path) {
        try {
            getZkClient().delete(path);
            return new Result(true);
        }catch (Exception e){
            return new Result(false);
        }
    }

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    public Result create(@FormParam("path") String path,
                         @FormParam("data") String data) {
        try {
            getZkClient().createPersistent(path, data);
            return new Result(true);
        }catch (Exception e){
            return new Result(false);
        }
    }

    private static ZkClient getZkClient() {
        if(null == zkClient) {
            zkClient = new ZkClient(Constant._ZK_SERVERS,Constant._ZK_TIMEOUT);
            zkClient.setZkSerializer(new StringZkSerializer());
        }
        return zkClient;
    }
}
