/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.cuc.usage;


/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.cuc.test <br>
 * <b>类名称</b>： ConfigTest <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/3 13:01<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ConfigTest {
    public static void main(String[] args) {
        while (true){
            System.out.println("Requirments name:"+Requirements.name);
            System.out.println("Requirments cpu:"+Requirements.cpu);
            System.out.println("Key words :"+KeyWord.config);

            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
