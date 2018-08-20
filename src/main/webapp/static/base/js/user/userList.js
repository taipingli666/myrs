var userId,userName;
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
		$.post(contextPath+"/user/deleteusers",{"ids":ids},function(data){
			if(data.success==true){
				layer.msg('操作成功!',{icon:1,time:1000});
    			setTimeout(function(){
    			window.location=contextPath+"/user/display";
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
function addRow(id,type){
	var tit = "";
	if(type != null && type == '1'){
		tit = "修改密码";
	}else{
		tit = id==0?"新增用户":"更新用户";
	}
	var index = layer.open({
		type: 2,
		title: tit,
		content: contextPath+"/user/operatedui?&id="+id+"&type="+type
	});
	layer.full(index);
}
function role(id,name){
	if(id == null ||id == 0){
		layer.msg("打开错误");
	}
	userId = id;
	userName = name;
	layer.open({
	      type: 2,
	      title: '选择角色',
	      shadeClose: true,
	      shade: 0.8,
	      area: ['400px', '400px'],
	      content: contextPath+'/user/userroleui'
    });
}