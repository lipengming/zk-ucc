/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.console.controller;

import com.jdpay.ucc.console.pojo.Node;
import org.apache.log4j.Logger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.console <br>
 * <b>类名称</b>： BaseController <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/4 10:40<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
@Path("/node")
public class BaseController {
    private static Logger logger = Logger.getLogger(BaseController.class);
    private static AtomicInteger index = new AtomicInteger(1);
    private static Map<Integer,Node> nodeList = new HashMap<Integer, Node>(2);

    public BaseController() {
        if(nodeList.size()==0) {
            nodeList.put(index.get(), new Node( "Frank",  "CS"));
            nodeList.put(index.get(), new Node( "Jersey", "Math"));
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Node getMetadata(@PathParam("id") int id) {
        if(nodeList.containsKey(id))
            return nodeList.get(id);
        else
            return new Node("Nil", "Nil");
    }

    @GET
    @Path("list")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Node> getAllStudents() {
        List<Node> students = new ArrayList<Node>();
        students.addAll(nodeList.values());
        return students;
    }

    @POST
    @Path(" add")
    @Produces("text/plain")
    public String addStudent(@FormParam("name") String name,
                             @FormParam("data") String data) {
        nodeList.put(index.get(), new Node(name, data));
        return String.valueOf(index.get());
    }

    @DELETE
    @Path("delete/{id}")
    @Produces("text/plain")
    public String removeStudent(@PathParam("id") int studentid) {
        logger.info("Receieving quest for deleting node: " + studentid);

        Node removed = nodeList.remove(studentid);
        if(removed==null) return "failed!";
        else   return "true";
    }

    @PUT
    @Path("put")
    @Produces("text/plain")
    public String putStudent(@QueryParam("id") int id,
                             @QueryParam("name") String name,
                             @QueryParam("data") String data
    ) {
        logger.info("Receieving quest for putting node: " + data);
        if(!nodeList.containsKey(id))
            return "failed!";
        else
            nodeList.put(id, new Node(name, data));
        return String.valueOf(id);
    }
}
