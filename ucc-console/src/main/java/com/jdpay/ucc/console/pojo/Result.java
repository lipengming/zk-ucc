/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.console.pojo;

import java.io.Serializable;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.console.pojo <br>
 * <b>类名称</b>： Result <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/5 21:56<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class Result implements Serializable {
    private boolean success;
    private String code;
    private Object data;

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String code) {
        this.success = success;
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Result setCode(String code) {
        this.code = code;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
}
