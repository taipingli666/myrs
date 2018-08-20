//表单验证
$("#formOperated").validate({
	rules:{
		name:{
			required:true,
			rangelength:[2,10]
		},
		hosId:{
			required:true
		},
		teamLeader:{
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

//保存
function operated(){
	if($("#formOperated").valid()){
		var expertId = $("#teamLeader").find("option:selected").val();
		var ids="";
		$('#memberList').find("input").each(function(i){
		 	if(0==i){
	        	ids = $(this).attr("data-userid");
	       	}else{
	       	 	ids += (","+$(this).attr("data-userid"));
	       	}
		});
		if(ids==""){
			alert("团队里必须要有一名以上普通成员");
			return;
		}
		ids = expertId+","+ids;
		var data = $("#formOperated").serialize();
		$.post(contextPath+"/team/operated?ids="+ids,data,function(data){
			if(data.success == true){
				layer.msg('操作成功!',{icon:1,time:1000});
	    		setTimeout(function(){
	    			parent.location.href = contextPath+'/team/display.do';
	    			layer_close();
	    		},1200)
			}else{
				layer.msg('操作失败!',{icon:2,time:1000});
			}
		},"json").error(function(){
			layer.msg("网络错误",{icon:2,time:1000});
		});
	}
	
}

//ajax分页
function changePage(i){
	var pageNumber = $("#pageNumber").val();
	var isSelect = $("#isSelect").val();
	var selectName = "";
	if(isSelect=="true"){
		selectName = $("#selectName").val();
	}
	var id = $("#teamId").val();
	var size = 5;
	if(typeof(pageNumber)==undefined){
		pageNumber = 1;
	}
	pageNumber = parseInt(pageNumber);
	if(i==1){
		//下一页
		pageNumber++;
		$("#pageNumber").val(pageNumber);
		$.ajax({
			cache:false,   //是否使用缓存
	        url:contextPath+"/team/page.do", 
	        async:false,   //是否异步，false为同步
	        type:"post",
	        dataType:"json",
	        data:"pageNumber="+pageNumber+"&size="+size+"&id="+id+"&selectName="+selectName,
	        error:function(){
	        	alert("ajax请求失败");
	        },
	        success:function(data){
	        	if(data!=""){
	        		$("#userList").html("");
	        		for (var i = 0; i < data.length; i++) {
	        			var trs = '<tr class="text-c" name="dicttr">'+
										'<td><input type="hidden" data-userid="'+data[i].userId+'"><img class="hand" src="'+contextPath+'/static/base/images/validform/add.jpg" onClick="changeMember(1,this);"></td>'+
										'<td>'+data[i].userName+'</td>'+
										'<td>'+data[i].trueName+'</td>'+
										'<td>'+data[i].mobile+'</td>'+
								  '</tr>';
	        			$("#userList").append(trs);
					}
	        		
		        	layer.msg("操作成功",{icon:1,time:1000});
	        	}else{
	        		layer.msg("已经是最后一页了",{icon:2,time:1000});
	        		pageNumber--;
	        		$("#pageNumber").val(pageNumber);
	        	}
	        	
	        }
	    });
	}else{
		//下一页
		if(pageNumber==1){
			layer.msg("没有上一页了",{icon:2,time:1000});
		}else{
			pageNumber--;
			$.ajax({
				cache:false,   //是否使用缓存
		        url:contextPath+"/team/page.do", 
		        async:false,   //是否异步，false为同步
		        type:"post",
		        dataType:"json",
		        data:"pageNumber="+pageNumber+"&size="+size+"&id="+id+"&selectName="+selectName,
		        error:function(){
		        	alert("ajax请求失败");
		        },
		        success:function(data){
	        		$("#userList").html("");
	        		for (var i = 0; i < data.length; i++) {
	        			var trs = '<tr class="text-c" name="dicttr">'+
										'<td><input type="hidden" data-userid="'+data[i].userId+'"><img class="hand" src="'+contextPath+'/static/base/images/validform/add.jpg" onClick="changeMember(1,this);"></td>'+
										'<td>'+data[i].userName+'</td>'+
										'<td>'+data[i].trueName+'</td>'+
										'<td>'+data[i].mobile+'</td>'+
								  '</tr>';
	        			$("#userList").append(trs);
					}
		        	layer.msg("操作成功",{icon:1,time:1000});
		        	$("#pageNumber").val(pageNumber);
		        }
		    });
		}
	}
}


//增删
function changeMember(i,obj){
	if(i==0){
		//移除
		$(obj).parent().parent().remove();
	}else{
		//加入
		if(jugeJoin(obj)){
			var userId = $(obj).parent().find("input").attr("data-userid");
			var userName = $(obj).parent().parent().find("td").eq(1).text();
			var trueName = $(obj).parent().parent().find("td").eq(2).text();
			var mobile = $(obj).parent().parent().find("td").eq(3).text();
			var tr = '<tr class="text-c" name="dicttr">'+
					'<td><input type="hidden" data-userid="'+userId+'"><img class="hand" src="'+contextPath+'/static/base/images/validform/del.jpg" onClick="changeMember(0,this);"></td>'+
					'<td>'+userName+'</td>'+
					'<td>'+trueName+'</td>'+
					'<td>'+mobile+'</td>'+
			  '</tr>';
			$("#memberList").append(tr);
		}else{
			layer.msg("该用户已经添加了",{icon:2,time:1000});
		}
		
	}
}


//判断是否已经添加
function jugeJoin(obj){
	var flag = true;
	var userId = $(obj).parent().find("input").attr("data-userid");
	var ids="";
	$('#memberList').find("input").each(function(i){
	 	if(0==i){
        	ids = $(this).attr("data-userid");
       	}else{
       	 	ids += (","+$(this).attr("data-userid"));
       	}
	});
	var str_ids = new Array();
	if(ids!=""){
		str_ids = ids.split(",");
		for (var i = 0; i < str_ids.length; i++) {
			if(userId==str_ids[i]){
				flag = false;
				break;
			}
		}
	}
	return flag;
}

//筛选
function doSearch(){
	var selectName = $("#selectName").val();
	var pageNumber = 1;
	var id = $("#teamId").val();
	var size = 5;
	$.ajax({
		cache:false,   //是否使用缓存
        url:contextPath+"/team/page.do", 
        async:false,   //是否异步，false为同步
        type:"post",
        dataType:"json",
        data:"pageNumber="+pageNumber+"&size="+size+"&id="+id+"&selectName="+selectName,
        error:function(){
        	alert("ajax请求失败");
        },
        success:function(data){
        	$("#userList").html("");
        	if(data!=""){
        		for (var i = 0; i < data.length; i++) {
        			var trs = '<tr class="text-c" name="dicttr">'+
									'<td><input type="hidden" data-userid="'+data[i].userId+'"><img class="hand" src="'+contextPath+'/static/base/images/validform/add.jpg" onClick="changeMember(1,this);"></td>'+
									'<td>'+data[i].userName+'</td>'+
									'<td>'+data[i].trueName+'</td>'+
									'<td>'+data[i].mobile+'</td>'+
							  '</tr>';
        			$("#userList").append(trs);
				}
        		
	        	layer.msg("操作成功",{icon:1,time:1000});
	        	$("#isSelect").val(true);
	        	$("#pageNumber").val(pageNumber);
        	}
        	
        }
    });
}






