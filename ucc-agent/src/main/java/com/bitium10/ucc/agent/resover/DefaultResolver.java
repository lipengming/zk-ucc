/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.ucc.agent.resover;

import com.bitium10.ucc.agent.Resolver;

import java.lang.reflect.Field;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.bitium10.ucc.agent.resover <br>
 * <b>类名称</b>： DefaultResover <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/2 21:14<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class DefaultResolver extends Resolver<String> {

    public DefaultResolver(Class clazz, Field field) {
        super(clazz, field);
    }

    @Override
    public String toString() {
        return "DefaultResolver";
    }

    @Override
    public String get() {
        return getStr(clazz,field);
    }

    @Override
    public void set(String src) {
        Object value = null;
        Class<?> type = field.getType();
        if (type == String.class) {
            value = src;
        } else if (type == Boolean.class || type == boolean.class) {
            value = Boolean.valueOf(src);
        } else if (type == Integer.class || type == int.class) {
            value = Integer.valueOf(src);
        } else if (type == Long.class || type == long.class) {
            value = Long.valueOf(src);
        } else if (type == Double.class || type == double.class) {
            value = Double.valueOf(src);
        } else if (type == Float.class || type == float.class) {
            value = Float.valueOf(src);
        } else if (type == Short.class || type == short.class) {
            value = Short.valueOf(src);
        } else if (FieldType.class.isAssignableFrom(type)) {
            try {
                value = ((FieldType)field.get(clazz)).valueOf(src);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            _LOG.info("UNKNOWN TYPE!");
            return;
        }
        setValue(clazz,field, value);
    }
}
