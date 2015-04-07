/**
 * Created by wylipengming on 2015/4/7.
 */

jQuery(document).ready(function() {
    App.init();
    $.ajax({
        type: "GET",
        url: "rest/config/appList",
        success: function(data){
            var json = eval(data);
            if(json.success) {
                var arr = json.data;
                if(arr){
                    var html = "";
                    for(var i in arr){
                        html += "<tr><td>"+arr[i].appName+"</td><td>"+arr[i].description+"</td></tr>"
                    }
                    $("#app_body").html(html);
                }
            }
        }
    });
});

function addApp() {
    var appName = $("#appName").val();
    var desc = $("#appDesc").val();
    var html = "<tr><td>"+appName+"</td><td>"+desc+"</td></tr>"
    $.ajax({
        type: "POST",
        url: "rest/config/addApp",
        data:{appName:appName,description:desc},
        success: function(data){
            var json = eval(data);
            if(json.success) {
                $("#add-success").show();
                $("#appName").val("");
                $("#appDesc").val("");
                //append
                console.log(html);
                $("#app_body").append(html);
            }else{
                $("#add-error").show();
            }
        }
    });
}

