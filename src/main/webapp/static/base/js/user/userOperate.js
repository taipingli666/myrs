$("#formOperated").validate({
		rules:{
			userName:{
				required:true
			},
			password:{
				required:true
			},
			hosId:{
				required:true
			},
            deptCode:{
                required:true
            },
			trueName:{
				required:true
			},
			mobile:{
				number:true
			},
			card:{
				number:true
			},
			repassword:{
				equalTo:"#password"
			}

		},
		onkeyup:true,
		focusCleanup:true,
		success:"valid"
	});
function operated(){
	if($("#formOperated").valid()){
		var data = $("#formOperated").serialize();
		$.post(contextPath+"/user/operated",data,function(data){
			if(data.success == true){
				//成功
				layer.msg('操作成功!',{icon:1,time:1000});
	    		setTimeout(function(){
	    			parent.location.href = contextPath+'/user/display';
	    			layer_close();
	    		},1200)
			}else{
				layer.msg(data.message,{icon:2,time:1000});
			}
		},"json").error(function(){
			layer.msg("网络错误");
		});
	}
}


function changeHospital() {
    var detpHtml = "<option value=\"\">未选择科室</option>";
    $("#deptCode").html(detpHtml);
    var hosId = $("#hosId").val();
    console.info(hosId);
    if($("#hosId").val()!=""){
        $.ajax({
            url:contextPath +"/department/getDepartmentList",
            dataType:"json",
            contentType:"application/json",
            type:"post",
            data:JSON.stringify({"hosid":$("#hosId").val()}),
            success:function(data){
                if(data !=null && data.length > 0){
                    for(var i = 0;i<data.length;i++){
                        detpHtml += "<option value=\""+data[i].departmentid+"\">"+data[i].departmentname+"</option>";
                    }
                    $("#deptCode").html(detpHtml);
                }else{
                    // alert("科室数据获取失败!");
                }
            },
            error:function(){
                layer.msg("获取科室失败,网络连接异常");
            }
        });
	}

}