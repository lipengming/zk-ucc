/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.console.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.console.pojo <br>
 * <b>类名称</b>： Node <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/4 10:46<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class Node implements Serializable {
    private String path;//全路径
    private String data;
    private String name;//当前名称
    private List<Node> children ;

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public Node(String path, String data, String name) {
        this.path = path;
        this.data = data;
        this.name = name;
    }

    public Node(String name, String data, List<Node> childes) {
        this.name = name;
        this.data = data;
        this.children = childes;
    }

    public Node() {
    }

    public String getName() {
        return name;
    }

    public Node setName(String name) {
        this.name = name;
        return this;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public String getData() {
        return data;
    }

    public Node setData(String data) {
        this.data = data;
        return this;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
