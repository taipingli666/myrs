$("#formOperated").validate({
		rules:{
			areaName:{
				required:true
			},
			code:{
				required:true
			},
			level:{
				required:true
			}
		},
		onkeyup:true,
		focusCleanup:true,
		success:"valid"
	});
function operated(){
	if($("#formOperated").valid()){
		var data= $("#formOperated").serialize();
		$.post(contextPath + '/commonarea/operated',data,function(result){
			if(result.success == true){
				//成功
				layer.msg('操作成功!',{icon:1,time:1000});
				setTimeout(function(){
					parent.parent.initTree();
					parent.location.reload();
				})
			}else{
				layer.msg('操作失败!',{icon:2,time:1000});
			}
		},"json").error(function(){
			layer.msg('网络错误!');
		});
	}
}

