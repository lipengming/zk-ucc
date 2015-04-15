/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.cuc.usage;


import com.jdpay.ucc.agent.Resolver;

import java.lang.reflect.Field;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.cuc.test <br>
 * <b>类名称</b>： CpuResolver <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/3 14:12<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class CpuResolver extends Resolver {

    public CpuResolver(Class clazz, Field field) {
        super(clazz, field);
    }

    @Override
    public String get() {
        try {
            Requirements.Cpu cpu = (Requirements.Cpu)field.get(clazz);
            return cpu.toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return getStr(clazz,field).toString();
    }

    @Override
    public void set(String src) {
        Class c = field.getType();
        if(c == Requirements.Cpu.class){
            Requirements.Cpu cpu = (Requirements.Cpu) new Requirements.Cpu().valueOf(src);
            if(cpu == null){
                return;
            }
            field.setAccessible(true);
            try {
                field.set(clazz,cpu);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
