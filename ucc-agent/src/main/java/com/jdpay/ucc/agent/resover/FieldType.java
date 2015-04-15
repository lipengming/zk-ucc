/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.agent.resover;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.agent.resover <br>
 * <b>类名称</b>： FieldType <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/2 21:20<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * 如果配置项是一个复合对象，那么这个对象必须继承自FieldType
 *
 * 例如：有一个应用程序，他必须运行在一个特定的配置环境下，那么我们可以这样干：
 *
 * 1、现定义一个环境需求的类型，且继承自FieldType：
 * class Requirements extends FieldType {
 *      String cpu;
 *      String cache;
 *      String ...
 * }
 *
 * 2、使用这个类型：
 *
 * @ZkFieldConfigurable(....)
 * Requirements　requirements
 *
 * @version 1.0.0 <br>
 */
public abstract class FieldType {
    /**
     *
     * @param src
     * @return
     */
    public abstract FieldType valueOf(String src);

    /**
     * use a string to description the class
     * @return
     */
    public abstract String toString();
}
