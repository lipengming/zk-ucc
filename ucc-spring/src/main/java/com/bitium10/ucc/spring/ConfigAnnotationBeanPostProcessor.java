package com.bitium10.ucc.spring; /**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */

import com.bitium10.ucc.agent.ZkRegister;
import com.bitium10.ucc.agent.ZkRegisterFactory;
import com.bitium10.ucc.agent.anno.ZkTypeConfigurable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： PACKAGE_NAME <br>
 * <b>类名称</b>： ConfigAnnotationBeanPostProcessor <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/10 23:21<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ConfigAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    private static String _G_SERVERS = "127.0.0.1:2181";
    private static Boolean _FORCE_WHEN_NULL = Boolean.TRUE;


    public ConfigAnnotationBeanPostProcessor(String severs,Boolean forceWhenNull) {
        _G_SERVERS = severs;
        _FORCE_WHEN_NULL = forceWhenNull;
    }

    //实例化
    @Override
    public boolean postProcessAfterInstantiation(final Object bean, String beanName) throws BeansException {
        Class clazz = bean.getClass();
        ZkTypeConfigurable cfg = (ZkTypeConfigurable) clazz.getAnnotation(ZkTypeConfigurable.class);
        if(cfg != null) {
            ZkRegisterFactory.getZkRegister(_G_SERVERS).register(bean.getClass(),_FORCE_WHEN_NULL);
        }
        return true;
    }

    //初始化
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return super.postProcessAfterInitialization(bean, beanName);
    }
}
