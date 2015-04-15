/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.core.lock;

/**
 * <b>项目名</b>： com.jdpay.customer <br>
 * <b>包名称</b>： com.jdpay.customer.lock <br>
 * <b>类名称</b>： LockException <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/3/23 11:47<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class LockException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LockException(String e){
        super(e);
    }

    public LockException(Exception e){
        super(e);
    }
}
