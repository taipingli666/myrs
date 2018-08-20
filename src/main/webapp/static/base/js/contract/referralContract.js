$(function(){
	init();
	$('#referral').on('click',referral);
});

function init(){
	//将原来的医生去除
	$('#expertId').val($('#hExpertId').val());
}
function referral(){
	$.post(globalVar.base+"/contract/operated.do",{expertId:$('#expertId').val(),contractId:$('#contractId').val()},function(data){
		if(data.success == true){
			layer.msg('操作成功!',{icon:1,time:1000});
    		setTimeout(function(){
    			parent.doSearch(1);
    			layer_close();
    		},1200);
		}else{
			layer.msg('操作失败!',{icon:2,time:1000});
		}
	},"json").error(function(){
		layer.msg("网络错误");
	});
}