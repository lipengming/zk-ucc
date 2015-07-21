/**
 * Copyright (c) 2014, shouli1990@gmail.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.bitium10.ucc.console.utils;

import com.bitium10.ucc.console.pojo.Node;
import org.I0Itec.zkclient.ZkClient;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.bitium10.ucc.console.utils <br>
 * <b>类名称</b>： ZkUtils <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:shouli1990@gmail.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/5 20:17<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ZkUtils {

    public static Node getNodeRecursive(ZkClient client,String path){
        Node node = getNode(client,path);
        List<String> childes = client.getChildren(path);
        List<Node> list = new ArrayList<Node>(childes.size());
        for(String child : childes) {
            try {
                list.add(getNodeRecursive(client, getPath(path, child)));
            }catch (Exception e){
                continue;
            }
        }
        node.setChildren(list);
        return node;
    }

    public static Node getCurrentNode(ZkClient client,String path){
        Node node = getNode(client,path);
        List<String> childes = client.getChildren(path);
        List<Node> list = new ArrayList<Node>(childes.size());
        for(String child : childes) {
            try {
                list.add(getNode(client, getPath(path, child)));
            }catch (Exception e){
                continue;
            }
        }
        node.setChildren(list);
        return node;
    }

    public static Node getNode(ZkClient client,String path) {
        try {
            return new Node(path,(String)client.readData(path,true),path.substring(path.lastIndexOf("/") + 1));
        }catch (Exception e) {
            return new Node(path,null);
        }
    }

    private static String getPath(String parent,String path) {
        if(parent.endsWith("/") ){
            if(path.startsWith("/")){
                return parent + path.substring(1);
            }
            return parent + path;
        }
        if(path.startsWith("/")){
            return parent + path;
        }
        return parent + "/" + path;
    }
}
