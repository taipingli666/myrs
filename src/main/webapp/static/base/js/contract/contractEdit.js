$(function(){
	//绑定事件		
	$('#save').on('click',doSave);
	$('#displayUL select').each(function(){
		$(this).val($(this).parents('li').find('input').val());
	});
	//checkbox数据回显	
	var temp,ids;								        		
	$('#displayUL .checkboxLI').each(function(){
		temp = $(this).find('input:last').val();
		if(temp != ''){
			ids = temp.split(',');
			for(var i in ids){
				$(this).find('#'+$(this).find('input:last').attr('name')+ids[i])
					.prop("checked",true);
			}
		}
	});
});
$("#contForm").validate({
	rules:{
		name:{
			required:true,
		}
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid"
});
//设置上传参数
function setCheckBox(){
	$('#displayUL .checkboxLI').each(function(){
		var val = '';
		$(this).find("input:checked").each(function(){
			val += $(this).val()+',';
		});
		if(val != '')  $(this).find('input:last').val(val.substring(0,val.length-1));
	});
}
//保存or新增
function doSave(){
	if($("#contForm").valid()){	
		setCheckBox();
		$.post(globalVar.base+"/contract/operated.do",$("#contForm").serialize(),function(data){
			if(data.success == true){
				layer.msg('操作成功!',{icon:1,time:1000});
	    		setTimeout(function(){
	    			parent.doSearch();
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
//下面是2017.12.26 新增加内容
//跳转
function jump(obj){
	var nextObj = $("input[jump='true'],select[jump='true']")[$("input[jump='true'],select[jump='true']").index(obj) + 1];
    if (nextObj != undefined) {
    	nextObj.focus();
    	nextObj.click();
    }
}

//获取乡镇列表
function getTown(){
	getArea(1)
}

//获取村庄列表
function getVillage(){
	getArea(2)
}

//根据父节点获取地区信息
function getArea(type){
	var pSelect , cSelect;
	if(type==1){
		pSelect = $("#county");
		cSelect = $("#town");
		$("#village").html("<option></option>");
	}else{
		pSelect = $("#town");
		cSelect = $("#village");
	}
	var parentCode = $(pSelect).val();
	$(cSelect).html("<option></option>");
	if(parentCode!=""){
		$.post(globalVar.base+"/contract/getAreaByParent",{"parentCode":parentCode},function(data){
			if(data.success==true){
				var commonAreas = data.message;
				for(var i=0;i<commonAreas.length;i++){
					$(cSelect).append("<option value='"+commonAreas[i].code+"'>"+commonAreas[i].areaName+"</option>");
				}
			}else{
				layer.msg("删除失败");
			}
		},"json").error(function(){
			layer.msg("网络异常");
		});
	}	
}