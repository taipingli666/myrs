$("#formOperated").validate({
		rules:{
			roleName:{
				required:true
			}
		},
		onkeyup:true,
		focusCleanup:true,
		success:"valid"
	});
function operated(){
	if($("#formOperated").valid()){
		var data = $("#formOperated").serialize();
		$.post(contextPath+"/role/operated",data,function(data){
			if(data.success == true){
				//成功
				layer.msg('操作成功!',{icon:1,time:1000});
	    		setTimeout(function(){
	    			parent.location.href = contextPath+'/role/display';
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