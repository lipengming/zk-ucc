/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.agent;

import com.jdpay.ucc.agent.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.agent <br>
 * <b>类名称</b>： Resolver <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/2 21:04<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public abstract class Resolver<T> {
    protected final static Logger _LOG = LoggerFactory.getLogger(Resolver.class);

    protected Class clazz;
    protected Field field;

    public Resolver(Class clazz, Field field) {
        this.clazz = clazz;
        this.field = field;
    }

    /**
     * 获取配置Field的值
     *
     * @return
     */
    public abstract T get();

    /**
     * 设置Field的值
     *
     * @param src
     */
    public abstract void set(String src);


    protected String getStr(Class clazz,Field field){
        try {
            ReflectionUtils.makeAccessible(field);
            return field.get(clazz).toString();//can not be (String)field.get(clazz)
        } catch (IllegalAccessException e) {
            _LOG.debug("illegal access exception..",e);
        }
        return "";
    }

    protected void setValue(Class clazz,Field field,Object value) {
        try {
            ReflectionUtils.makeAccessible(field);
            field.set(clazz, value);
        } catch (IllegalArgumentException e) {
            _LOG.debug("illegal agreements exception..",e);
        } catch (IllegalAccessException e) {
            _LOG.debug("illegal access exception..",e);
        }
    }
}
