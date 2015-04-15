/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.ucc.agent.anno;

import java.lang.annotation.*;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.bitium10.ucc.agent.anno <br>
 * <b>类名称</b>： ZkClassConfigurable <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/2 10:02<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ZkTypeConfigurable {

    /**
     * 该配置在zk上面的节点路径（node path）
     * 缺省按照一下规则：
     *
     * config/center/default/[applicationName]/[className]/
     *
     * @return
     */
    String path() default "";


    /**
     * 使用当前的zk servers 配置
     *
     * @return
     */
    boolean useOwnServer() default false;

    /**
     * 当前zk servers
     *
     * @return
     */
    String servers() default "";
}
