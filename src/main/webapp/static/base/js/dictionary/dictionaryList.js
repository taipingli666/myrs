$(function(){
	//当前页数
	globalVar.currentPage = $("#currentPage").val();
	//绑定事件
	$('#search').on('click',doSearch);
	$('#remove').on('click',doRemove);
	$('#add').on('click',function(){
		addRow('字典新增',globalVar.base+'/dictionary/operating?operationType=add');
	});
	//模糊查询体检更改及时更新
	$('#contents').on('change',function(){
		globalVar.contents = encodeURI(encodeURI($("#contents").val()));
	});
	//tr双击触发修改
	$('#tbody').on('dblclick','tr',function(){
		var id = $(this).data('dictid');
		addRow('修改字典信息',globalVar.base+'/dictionary/operating?operationType=update&id='+id);
	});
});
//search
function doSearch(pageNumber){
	var url = globalVar.base+'/dictionary/display?contents='+(globalVar.contents == undefined?"":globalVar.contents);
	window.location = url;
}
function changePage(pageNumber){
	var url = globalVar.base+'/dictionary/display?pageNumber=' + pageNumber+"&contents="
		+(globalVar.contents == undefined?"":globalVar.contents);
	window.location = (url);
}
//新增或修改
function addRow(title,url){
	var index = layer.open({
		type: 2,
		title: title, 
		content: url+"&pageNumber="+globalVar.currentPage
	});
	layer.full(index);
}

//删除
function doRemove(){
	var $tbody = $("#tbody input[type='checkbox']:checked"),ids = '';
	if($tbody.length==0){
		alert('请先选择要删除的数据！');
		return;
	}
	layer.confirm('是否要删除数据?', {
	  btn: ['是','否'] //按钮
	},function(){ 
		$tbody.each(function(i){
		 	ids += $(this).parents('tr').data('dictid')+',';
		});
		$.post(globalVar.base+"/dictionary/delete",{"ids":ids},function(data){
			if(data.success==true){
				layer.msg('操作成功!',{icon:1,time:1000});
				setTimeout(function(){
				 	doSearch();
			},1200)
			}else{
				layer.msg("删除失败");
			}
		},"json")
		.error(function(){
			layer.msg("网络异常");
		});
	}, function(){
	 
	});
}

