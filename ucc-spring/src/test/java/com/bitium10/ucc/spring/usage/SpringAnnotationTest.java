/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.ucc.spring.usage;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.bitium10.ucc.spring.usage <br>
 * <b>类名称</b>： SpringAnnotationTest <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/11 10:07<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class SpringAnnotationTest {

    private ApplicationContext ct;

    @Before
    public void before(){
        ct = new ClassPathXmlApplicationContext("spring.xml");
    }

    @Test
    public void done(){
        Config kw = ct.getBean("kw",KeyWord.class);
        while (true){
            kw.print();
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
