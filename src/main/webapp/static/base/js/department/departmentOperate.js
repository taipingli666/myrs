
$("#formOperated").validate({
	rules:{
		departmentname:{
			required:true
		},
		hosid:{
			required:true
		},
        distanceFlag:{
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
//		$.post(contextPath + '/department/operated',data,function(result){
//			if(result.success == true){
//				//成功
//				layer.msg('操作成功!',{icon:1,time:1000});
//				setTimeout(function(){
//					parent.location.reload();
//				})
//			}else{
//				layer.msg('操作失败!',{icon:2,time:1000});
//			}
//		},"json").error(function(){
//			layer.msg('网络错误!');
//		});	
		
		
		$.ajax({
			cache:false,//是否使用缓存
            url:contextPath+"/department/operated.do?", 
            async:true,   //是否异步，false为同步
            type:"post",
            data:data,
            error:function(){
            	alert("请求失败");
            },
            success:function(result){
            	if(result.success == true){
			        layer.msg('操作成功!',{icon:1,time:1000});
            		setTimeout(function(){
            			parent.location.reload();
            		},1200)
		        }else{
		        	layer.msg(result.message,{icon:2,time:1000});
		        }
		    } 
		});
	}
}




//$(function(){
//	//绑定事件
//	$('#save').on('click',doSave);
//	
////	setHos();
//});
//
//$("#formOperated").validate({
//	rules:{
//		departmentname:{
//			required:true
//		},
//		hosid:{
//			required:true
//		}
//	},
//	onkeyup:true,
//	focusCleanup:true,
//	success:"valid"
//});
//
//
////保存or新增
//function doSave(){
//	if(globalVar.name == ''){
//		alert('名称不能为空！');
//		return;
//	}
//	globalVar.departmentid = $("#departmentid").val();
//	globalVar.departmentname = $("#departmentname").val();
//	globalVar.remark = $("#remark").val();
//	globalVar.hosnum = $("#hosnum").val();
//	globalVar.hosid = $("#hosid").val();
//	//if(window.confirm('是否确定要删除这些数据？')){
//		$.ajax({
//			cache:false,//是否使用缓存
//            url:globalVar.base+"/department/operated.do?operationType="+$('#operationType').val(), 
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
//            			parent.location.href = globalVar.base+'/department/display.do';
//            			layer_close();
//            		},1200)
//		        }else{
//		        	layer.msg('操作失败!',{icon:2,time:1000});
//		        }
//		    } 
//		});
//	//}		
//}
//
function gethosnum(select){ 
	var i = $("#hosid").find("option:selected").data('hosnum');
	var hosnum = document.getElementById("hosnum");
	hosnum.value=i;
	}
////
////function setHos(){ 
////	var hosnum = document.getElementById("hosnum");
////	var hosid = document.getElementById("hosid");
////	hosnum.value=globalVar.hosnum;
////	hosid.value=globalVar.hosid;
////	}