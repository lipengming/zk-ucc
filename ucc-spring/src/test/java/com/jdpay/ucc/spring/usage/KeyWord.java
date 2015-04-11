package com.jdpay.ucc.spring.usage;

import com.jdpay.ucc.agent.anno.ZkExtendConfigurable;
import com.jdpay.ucc.agent.anno.ZkTypeConfigurable;
import com.jdpay.ucc.spring.usage.ds.CacheDataStore;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("kw")
@ZkTypeConfigurable(useOwnServer = false,path = "/conf/test/demo")
public class KeyWord implements Config {
    @ZkExtendConfigurable(path = "keyWords",update = true,tempKey = "key_words",dataStroe = CacheDataStore.class)
    public static Map<String,String> config = new HashMap<String, String>(0);

    @Override
    public void print() {
        System.out.println(config);
    }
}