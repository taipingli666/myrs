
$("#formOperated").validate({
	rules:{
		className:{
			required:true
		},
		classID:{
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
		$.ajax({
			cache:false,//是否使用缓存
            url:contextPath+"/commonpackage/operated.do?", 
            async:true,   //是否异步，false为同步
            type:"post",
            data:data,
            error:function(){
            	alert("请求失败");
            },
            success:function(result){
            	if(result !="fail"){
			        layer.msg('操作成功!',{icon:1,time:1000});
            		setTimeout(function(){
            			parent.location.reload();
            		},1200)
		        }else{
		        	layer.msg('操作失败!',{icon:2,time:1000});
		        }
		    } 
		});
	}
}

//$(function(){
//	//绑定事件
//	$('#save').on('click',doSave);
//	
//});
////保存or新增
//function doSave(){
//	if(globalVar.className == ''){
//		alert('名称不能为空！');
//		return;
//	}
//	globalVar.packageId = $("#packageId").val();
//	globalVar.className = $("#className").val();
//	globalVar.classID = $("#classID").val();
//	globalVar.remark = $("#remark").val();
//	//if(window.confirm('是否确定要删除这些数据？')){
//		$.ajax({
//			cache:false,//是否使用缓存
//            url:globalVar.base+"/commonpackage/operated.do?operationType="+$('#operationType').val(), 
//            async:true,   //是否异步，false为同步
//            type:"post",
//            data:globalVar,
//            error:function(){
//            	alert("请求失败");
//            },
//            success:function(reply){
//            	if(reply !="fail"){
//			        layer.msg('操作成功!',{icon:1,time:1000});
//            		setTimeout(function(){
//            			parent.location.href = globalVar.base+'/commonpackage/display.do';
//            			layer_close();
//            		},1200)
//		        }else{
//		        	layer.msg('操作失败!',{icon:2,time:1000});
//		        }
//		    } 
//		});
//	//}		
//}
