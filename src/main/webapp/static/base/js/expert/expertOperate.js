$("#formOperated").validate({
	rules:{
		userName:{
			required:true
		},
		hosId:{
			required:true
		},
		trueName:{
			required:true
		},
		sex:{
			required:true
		},
		mobile:{
			required:true
		},
		card:{
			required:true
		},
		teamId:{
			required:true
		}
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
});

function operated(){
	if($("#formOperated").valid()){
		var data = $("#formOperated").serialize();
		$.post(contextPath+"/expert/operated",data,function(data){
			if(data.success == true){
				//成功
				layer.msg('操作成功!',{icon:1,time:1000});
	    		setTimeout(function(){
	    			parent.location.href = contextPath+'/expert/display.do';
	    			layer_close();
	    		},1200)
			}else{
				layer.msg('操作失败!',{icon:2,time:1000});
			}
		},"json").error(function(){
			layer.msg("网络错误");
		});
	}
}