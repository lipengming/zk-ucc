/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.agent.anno;

import com.jdpay.ucc.agent.Notify;
import com.jdpay.ucc.agent.Resolver;
import com.jdpay.ucc.agent.resover.DefaultResolver;

import java.lang.annotation.*;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.agent.anno <br>
 * <b>类名称</b>： ZkNotifyConfigurable <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/4 2:13<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZkNotifyConfigurable {
    /**
     * 该配置在zk上面的节点路径（node path）
     * 缺省按照一下规则：
     *
     * config/center/default/applicationName/className/[field]
     *
     * @return
     */
    String path() default "";

    /**
     * 是否自动更新配置，默认是true
     *
     * @return
     */
    boolean update() default true;

    /**
     * 用于指定 版本变更时接收通知，并且处理的 类
     *
     * @return
     */
    Class<? extends Notify> notification() default Notify.class;
}