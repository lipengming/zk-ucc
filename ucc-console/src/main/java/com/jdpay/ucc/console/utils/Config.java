/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.console.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.console.utils <br>
 * <b>类名称</b>： Properties <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/5 20:11<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class Config {
    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    /**
     * 系统的配置集合。
     */
    public final static Map<String, String> _CONFIG = new HashMap<String, String>();

    static {
        logger.info("=====开始加载系统的配置文件=====");
        try {
            // 加载系统的配置文件。
            java.util.Properties properties = new java.util.Properties();
            ClassLoader classLoader = Config.class.getClassLoader();
            properties.load(classLoader.getResourceAsStream("config.properties"));
            Iterator<Object> keySet = properties.keySet().iterator();

            // 初始化 系统的配置集合。
            while (keySet.hasNext()) {
                String key = (String) keySet.next();
                _CONFIG.put(key, properties.getProperty(key));
            }

        } catch (Exception e) {
            logger.error("=====加载系统配置文件异常=====", e);
        }
        logger.info("=====成功加载系统的配置文件=====");
    }
}
