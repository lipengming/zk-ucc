function zookeeper_create(){
    $("#create-error").hide();
    $("#create-success").hide();
    var path=$("#zookeeper-create-name").val();
    var data=$("#zookeeper-create-data").val();
    var post_data={path:path,data:data};
    $.ajax({
        type: "POST",
        url: "rest/zk-manager/create",
        data: post_data,
        success: function(data){
            if(data.success){
                $("#create-success").show();
            }else{
                $("#create-error").show();
            }
        }
    });
}

function zookeeper_set_data(){
    $("#udpate-error").hide();
    $("#update-success").hide();
    var path=$("#zookeeper-edit-name").val();
    var data=$("#zookeeper-edit-data").val();
    var post_data={path:path,data:data};
    $.ajax({
        type: "POST",
        url: "rest/zk-manager/setData",
        data: post_data,
        success: function(data){
            if(data.success){
                $("#update-success").show();
            }else{
                $("#update-error").show();
            }
        }
    });
}

function zookeeper_delete_node(){
    $("#delete-error").hide();
    $("#delete-success").hide();
    var path=$("#zookeeper-edit-name").val();
    var post_data={path:path};
    console.log(post_data);
    $.ajax({
        type: "DELETE",
        url: "rest/zk-manager/delete",
        data: post_data,
        success: function(data){
            if(data.success){
                $("#zookeeper-edit-name").val("");
                $("#zookeeper-edit-data").val("");
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                var nodes = treeObj.getSelectedNodes();
                for (var i=0, l=nodes.length; i < l; i++) {
                    treeObj.removeNode(nodes[i]);
                }
                $("#delete-success").show();
            }else{
                $("#delete-error").show();
            }
        }
    });
}

function refreshNode(e) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        type = e.data.type,
        silent = e.data.silent,
        nodes = zTree.getSelectedNodes();
    if (nodes.length == 0) {
        alert("请先选择一个父节点");
    }
    for (var i=0, l=nodes.length; i<l; i++) {
        zTree.reAsyncChildNodes(nodes[i], type, silent);
        if (!silent) zTree.selectNode(nodes[i]);
    }
}

function fetchNode(event, treeId, treeNode) {
    console.log(treeNode.path);
    $("#zookeeper-edit-path").val("");
    $("#zookeeper-edit-data").val("");
    $("#zookeeper-create-name").val(treeNode.path);
    var post_data={path:treeNode.path};
    $.ajax({
        type: "GET",
        url: "rest/zk-manager/detail",
        data: post_data,
        success: function(data){
            $("#zookeeper-edit-name").val(treeNode.path);
            $("#zookeeper-edit-data").val(data.data);
        }
    });
}


jQuery(document).ready(function() {
    App.init();
    var setting = {
        async: {
            enable: true,
            url:"rest/zk-manager/list",
            type:'get',
            autoParam:["path", "name"]
        },callback: {
            onClick: fetchNode
        }
    };
    $.fn.zTree.init($("#treeDemo"), setting);
    $("#refreshNode").bind("click", {type:"refresh", silent:false}, refreshNode);
});
	