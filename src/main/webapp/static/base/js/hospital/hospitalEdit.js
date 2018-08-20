function operated(){
	var data = $("#formOperated").serialize();
	$.post(contextPath+"/commonhospital/edit",data,function(data){
		if(data.success == true){
			//成功
			layer.msg('操作成功!',{icon:1,time:1000});
    		setTimeout(function(){
    			parent.location.href = contextPath+'/commonhospital/list';
    			layer_close();
    		},1200)
		}else{
			layer.msg('操作失败!',{icon:2,time:1000});
		}
	},"json").error(function(){
		layer.msg("网络错误");
	});
}