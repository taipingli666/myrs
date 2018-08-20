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
function product_del(){
	var value = window.document.getElementById("areaCode").value;
	layer.confirm('是否要删除数据?', {
	  btn: ['是','否'] //按钮
	}, function(){
	 	var $tbody = $("#tbody input[type='checkbox']:checked"),ids = '';
	 	if($tbody.length==0){
			layer.msg('请先选择要删除的数据！');
			return;
		}
		$tbody.each(function(i){
		 	ids += $(this).data('area')+",";
		});
		$.post(contextPath+"/commonpopulation/deletepopulations",{"ids":ids},function(data){
			if(data.success==true){
				layer.msg('操作成功!',{icon:1,time:1000});
    			setTimeout(function(){
    			window.location=contextPath+"/commonpopulation/show2?areaCode="+value;
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
function product_add(id){
	/*if(id == 0){
		if(tbody.rows.length > 0){
			layer.alert("已经维护数据,不可以新增数据,请进行修改数据操作!");
			return;
		}
	}*/
	
	var value = window.document.getElementById("areaCode").value;
	var index = layer.open({
		type: 2,
		area: ['340px', '215px'],
		title: id==0?"新增用户":"更新用户", 
		content: contextPath+"/commonpopulation/operatedui?id="+id+"&areaCode="+value
	});
	layer.full(index);
}



