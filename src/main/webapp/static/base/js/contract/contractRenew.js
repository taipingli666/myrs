$(function(){
	$("#renew").on("click",function(){
		$.post(globalVar.base+"/contract/renew.do",{contractId:$("#contractId").val()},function(reply){
			if(reply.success == true){
				//成功
				layer.msg('续约成功!',{icon:1,time:1000});
				setTimeout(function(){
					parent.doSearch(1);
					layer_close();
    			},1200);
			}else{
				layer.msg(reply.message,{icon:2,time:3000});
			}
		});
	});
});

//重新加载
function clear(){
	window.location=globalVar.base+"/contract/display";
}