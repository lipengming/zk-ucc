/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.cuc.usage;

import com.bitium10.ucc.agent.anno.ZkFieldConfigurable;
import com.bitium10.ucc.agent.anno.ZkTypeConfigurable;
import com.bitium10.ucc.agent.resover.FieldType;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.bitium10.cuc.test <br>
 * <b>类名称</b>： Requirements <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/2 23:54<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
@ZkTypeConfigurable(servers = "localhost:2181",useOwnServer = true,path = "/conf/test/demo")
public class Requirements {
    @ZkFieldConfigurable(path = "name",update = true)
    public static String name = "default_name";

    @ZkFieldConfigurable(path = "cpu",update = true,resolver = CpuResolver.class)
    public static Cpu cpu = new Cpu("default","default","default");

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public static class Cpu extends FieldType {
        String type;
        String kernal;
        String cache;

        public Cpu(){}

        public Cpu(String type, String kernal, String cache) {
            this.type = type;
            this.kernal = kernal;
            this.cache = cache;
        }

        @Override
        public FieldType valueOf(String src) {
            String[] arr = src.split(",");
            if(arr.length != 3) {
                return null;
            }
            return new Cpu(arr[0],arr[1],arr[2]);
        }

        @Override
        public String toString() {
            return type+","+kernal+","+cache;
        }
    }
}
