/**
 * Created by wylipengming on 2015/4/7.
 */


jQuery(document).ready(function() {
    App.init();

    //初始化下拉框
    var select_data;
    $.ajax({
        url:"/rest/config/appList",
        type:"GET",
        dataType:"json",
        timeout:5000,
        success:function(json){
            var json = eval(json);
            if(json.success) {
                var arr = json.data;
                if(arr){
                    var html = "<option >请选择</option>";
                    for(var i in arr){
                        html +="<option value="+arr[i].id+">"+arr[i].appName+"</option>";
                    }
                    select_data = arr;
                    $("#select_app").html(html);
                    $("#service_list").html("");
                }
            }
        },
        error:function(xhr, status){
            console.log("xhr:"+xhr);
        }
    });

    $("#select_app").change(function(event){
        var aid = $("#select_app").find("option:selected").val();
        var appName = $("#select_app").find("option:selected").text();
        $("#service_list").html("");
        $("#appName").val(appName);

        $.ajax({
            url:"/rest/config/serviceList",
            type:"GET",
            dataType:"json",
            data:{'appId':aid},
            timeout:5000,
            success:function(json){
                var json = eval(json);
                if(json.success) {
                    var arr = json.data;
                    if(arr){
                        var html = "";
                        for(var i in arr){
                            html += "<tr><td>"+appName+"</td><td>" +arr[i].serviceName+"</td><td>" +arr[i].serviceType+"</td><td>" +arr[i].path+"" +
                                "</td><td>" +arr[i].servers+"</td><td>" +arr[i].useOwnServers+"" +
                                "</td></tr>"
                        }
                        $("#service_list").html(html);
                    }
                }
            },
            error:function(xhr, status){
                console.log("xhr:"+xhr);
            }
        });
    });
});

function create_service() {
    var aid = $("#select_app").find("option:selected").val();
    var serviceName = $("#serviceName").val();
    var serviceType = $("#serviceType").val();
    var path = $("#path").val();
    var services = $("#services").val();
    var useOwnServers = $("#useOwnServers").val();

    $.ajax({
        url:"/rest/config/addService",
        type:"POST",
        dataType:"json",
        data:{'aid':aid,'serviceName':serviceName,'serviceType':serviceType,
        'path':path,'services':services,'useOwnServers':useOwnServers},
        timeout:5000,
        success:function(json){
            var json = eval(json);
            if(json.success) {
                $("#add-success").show();
            }
        },
        error:function(xhr, status){
            console.log("xhr:"+xhr);
        }
    });
}