$("#formOperated").validate({
		rules:{
			pNumber:{
				required:true
			},
			countYear:{
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
		$.post(contextPath + '/commonpopulation/operated',data,function(result){
			if(result.success == true){
				//成功
				layer.msg('添加成功！',{icon:1,time:1000});
//				setTimeout(function(){
					parent.location.reload();
//				})
			}else{
				layer.msg(result.errorMsg,{icon:2,time:3000});
			}
		},"json").error(function(){
			layer.msg('网络错误!');
		});	
	}
}

