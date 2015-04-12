/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.console.controller;


import com.jdpay.ucc.console.db.DBManager;
import com.jdpay.ucc.console.db.map.AppRow;
import com.jdpay.ucc.console.db.map.ConfigFieldRowMap;
import com.jdpay.ucc.console.db.map.ConfigTypeRowMap;
import com.jdpay.ucc.console.pojo.Result;
import com.jdpay.ucc.core.dto.App;
import com.jdpay.ucc.core.dto.ConfigField;
import com.jdpay.ucc.core.dto.ConfigType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.console.controller <br>
 * <b>类名称</b>： ConfigController <br>
 * <b>类描述</b>： 配置信息管理控制器<br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/7 12:54<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
@Path("/config")
public class ConfigController {
    private static Logger _LOG = LoggerFactory.getLogger(ConfigController.class);
    private DBManager db = new DBManager();

    @GET
    @Path("/app_config")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ConfigType> config(@QueryParam("appName") String appName){
        _LOG.info("GET CONFIG INFO BY:" + appName);
        List<ConfigType> list = db.queryForList("select * from tb_app app,tb_service service where app.id=service.aid and app.appName=?", new ConfigTypeRowMap(),appName);
        if(list == null || list.isEmpty()) {
            return  new ArrayList<ConfigType>(0);
        }
        for (ConfigType tp : list) {
            List<ConfigField> arr = db.queryForList("select * from tb_service service，tb_field field where field.sid=service.id ", new ConfigFieldRowMap());
            tp.setConfigFields(arr);
        }
        return list;
    }

    @POST
    @Path("/addApp")
    @Produces({MediaType.APPLICATION_JSON})
    public Result addApp(@FormParam("appName") String appName,
                         @FormParam("description") String description){
        _LOG.info("GET CONFIG INFO BY:" + appName);
        return db.executeSQL("insert into tb_app(appName,description) values (?,?)",appName,description) > 0 ? new Result(true) : new Result(false);
    }

    @GET
    @Path("/appList")
    @Produces({MediaType.APPLICATION_JSON})
    public Result listApp(){
        _LOG.info("GET APP LIST INFO :" );
        List<App> list = db.queryForList("select * from tb_app",new AppRow());
        return new Result(true).setData(list);
    }

    @POST
    @Path("/addService")
    @Produces({MediaType.APPLICATION_JSON})
    public Result addService(@FormParam("serviceName") String serviceName,
                             @FormParam("serviceType") String serviceType,
                             @FormParam("useOwnServers") String useOwnServers,
                             @FormParam("servers") String servers,
                             @FormParam("path") String path,
                             @FormParam("aid") String aid){
        _LOG.info("ADD SERVICE INFO BY:" + serviceName);
        return db.executeSQL("insert into tb_service(aid,serviceName,serviceType,path,servers,useOwnServers) values (?,?,?,?,?,?)",aid,serviceName,serviceType,path,servers,useOwnServers) > 0 ? new Result(true) : new Result(false);
    }

    @GET
    @Path("/serviceList")
    @Produces({MediaType.APPLICATION_JSON})
    public Result listService(@QueryParam("appId") long appId){
        _LOG.info("GET APP LIST INFO :" );
        List<ConfigType> list = db.queryForList("select * from tb_service where aid=?",new ConfigTypeRowMap(),appId);
        return new Result(true).setData(list);
    }

    @POST
    @Path("/addField")
    @Produces({MediaType.APPLICATION_JSON})
    public Result addField(@FormParam("appName") String appName,
                           @FormParam("description") String description){
        _LOG.info("GET CONFIG INFO BY:" + appName);
        return db.executeSQL("insert into tb_app(appName,description) values (?,?)",appName,description) > 0 ? new Result(true) : new Result(false);
    }

    @GET
    @Path("/listField")
    @Produces({MediaType.APPLICATION_JSON})
    public Result listField(@QueryParam("appId") long appId,
                            @QueryParam("serviceId") long serviceId){
        _LOG.info("GET FIELD INFO BY: AID:" + appId + "SID:" + serviceId);
        List<ConfigField> list = db.queryForList("select f.* tb_app a,tb_service s,tb_field f where a.id=s.aid and s.id=f.sid",new ConfigFieldRowMap(),appId,serviceId);
        return new Result(true).setData(list);
    }
}
