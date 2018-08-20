function changePage(page){
	refresh(page); 
}
function refresh(page){
	if(page != null){
			$("#page").val(page);
		}else{
			$("#page").val("1");
		}
		$("#listForm").submit();
	}
function dels(){
	layer.confirm('是否要删除数据?', {
	  btn: ['是','否'] //按钮
	}, function(){
	 	var $tbody = $("#tbody input[type='checkbox']:checked"),ids = '';
		if($tbody.length==0){
			layer.msg('请先选择要删除的数据！');
			return;
		}
		$tbody.each(function(i){
		 	ids += $(this).data('userid')+",";
		});
		$.post(contextPath+"/team/deleteteams",{"ids":ids},function(data){
			if(data.success==true){
				layer.msg('操作成功!',{icon:1,time:1000});
    			setTimeout(function(){
    			window.location=contextPath+"/team/display.do";
    		},1200)
			}else{
				layer.msg("删除失败");
			}
		},"json").error(function(){
			layer.msg("网络异常");
		});
	}, function(){
	 
	});
	
}
function addRow(id){
	var index = layer.open({
		type: 2,
		title: id==0?"新增团队":"更新团队", 
		content: contextPath+"/team/operatedui?&id="+id
	});
	layer.full(index);
}