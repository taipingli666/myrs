$(function(){
	//当前页数
	globalVar.currentPage = $("#currentPage").val();
	//绑定事件
	$('#search').on('click',doSearch);
	$('#remove').on('click',doRemove);
	$('#add').on('click',function(){
		addRow('字典新增',globalVar.base+'/commonpackage/operating.do?operationType=add');
	});
	//模糊查询体检更改及时更新
	$('#className').on('change',function(){
		globalVar.className = encodeURI(encodeURI($("#className").val()));
	});
	//tr双击触发修改
	$('#tbody').on('dblclick','tr',function(){
		var id = $(this).data('packageid');
		addRow('修改字典信息',globalVar.base+'/commonpackage/operating.do?operationType=update&id='+id);
	});
});

function doSearch(pageNumber){
	var url = globalVar.base+'/commonpackage/display.do?className='+(globalVar.className == undefined?"":globalVar.className);
	window.location = url;
}

function changePage(pageNumber){
	var url = globalVar.base+'/commonpackage/display.do?pageNumber=' + pageNumber+"&className="
		+(globalVar.className == undefined?"":globalVar.className);
	window.location = (url);
}

function edit(id){
	var index = layer.open({
		type: 2,
		content: globalVar.base+'/commonpackage/operating.do?operationType=update&id='+id+"&pageNumber="+globalVar.currentPage
	});
	layer.full(index);
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
	$tbody.each(function(i){
	 	ids += $(this).parents('tr').data('packageid')+',';
	});
	//if(window.confirm('是否确定要删除这些数据？')){
		$.ajax({
			cache:false,//是否使用缓存
            url:globalVar.base+"/commonpackage/delete.do", 
            async:true,   //是否异步，false为同步
            type:"post",
            data:"ids=" + ids,
            error:function(){
            	alert("请求失败！");
            },
            success:function(reply){
            	if(reply !="fail"){
			        layer.msg('成功删除!',{icon:1,time:1000});
			        doSearch();
		        }else{
		        	layer.msg('删除失败!',{icon:2,time:1000});
		        }
		    } 
		});
	//}		
}
