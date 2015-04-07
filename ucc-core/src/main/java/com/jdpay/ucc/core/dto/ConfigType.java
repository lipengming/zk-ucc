/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.core.dto;

import java.io.Serializable;
import java.util.List;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.core.dto <br>
 * <b>类名称</b>： ConfigType <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/7 12:38<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ConfigType extends BasrDto {
    private String appName;
    private String serviceName;
    private String serviceType;
    private String path;
    private String servers;
    private boolean useOwnServers;
    private List<ConfigField> configFields;


    public ConfigType(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public boolean isUseOwnServers() {
        return useOwnServers;
    }

    public void setUseOwnServers(boolean useOwnServers) {
        this.useOwnServers = useOwnServers;
    }

    public List<ConfigField> getConfigFields() {
        return configFields;
    }

    public void setConfigFields(List<ConfigField> configFields) {
        this.configFields = configFields;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
