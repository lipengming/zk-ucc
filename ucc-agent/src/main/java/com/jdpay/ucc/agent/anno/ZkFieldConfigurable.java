/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.agent.anno;

import com.jdpay.ucc.agent.ExtendDataStore;
import com.jdpay.ucc.agent.Resolver;
import com.jdpay.ucc.agent.resover.DefaultResolver;
import com.jdpay.ucc.agent.resover.RedisExtendDataStore;

import java.lang.annotation.*;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.agent.anno <br>
 * <b>类名称</b>： ZkFieldConfigurable <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/2 10:03<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZkFieldConfigurable {
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
     * 用于指定当前类型的类型处理器
     *
     * @return
     */
    Class<? extends Resolver> resolver() default DefaultResolver.class;

    /**
     * 用于实现扩展存储数据操作的类,默认给出redis的操作方案（应该是不给出，并且不许指定的项）
     *
     * @return
     */
    Class<? extends ExtendDataStore> dataStore() default RedisExtendDataStore.class;
}
