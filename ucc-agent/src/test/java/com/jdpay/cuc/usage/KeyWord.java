/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.cuc.usage;

import com.jdpay.cuc.usage.ds.CacheDataStore;
import com.jdpay.ucc.agent.anno.ZkExtendConfigurable;
import com.jdpay.ucc.agent.anno.ZkTypeConfigurable;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.cuc.test <br>
 * <b>类名称</b>： KeyWord <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/6 9:55<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
@ZkTypeConfigurable(useOwnServer = false,path = "/conf/test/demo")
public class KeyWord {
    @ZkExtendConfigurable(path = "keyWords",update = true,tempKey = "key_words",dataStroe = CacheDataStore.class)
    public static Map<String,String> config = new HashMap<String, String>(0);

}
