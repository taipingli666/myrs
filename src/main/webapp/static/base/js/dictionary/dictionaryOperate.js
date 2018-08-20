$(function(){
	globalVar.valueTrNum = 0;			//数据行数
	globalVar.flag = false;				//编码检验
	//绑定事件
	$('#save').on('click',doSave);
	$('#addRow').on('click',addRow);
	$('.delete-row').on('click',function(){
		deleteRow($(this));
	});
});
$("#dictForm").validate({
	rules:{
		name:{
			required:true
		},
		code:{
			number:true,
			remote:globalVar.base+"/dictionary/checkCode.do"
		}
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid"
});
//生成当前行的值编码
function generativeCoding(){
	return $("#code").val()+''+globalVar.valueTrNum;
}
//及时检验必须用ajax（），并添加async:false，globalVar.flag设置完成，再继续执行
function checkoutCode(){
	var code = $("#code").val(),flag = false;
	if(code == '') {
		alert('请先输入编码！');
	}
	$.ajax({
		cache:false,//是否使用缓存
	    url:globalVar.base+"/dictionary/checkCode.do", 
	    async:false,   //是否异步，false为同步
	    type:"post",
	    data:{'code':code},
	    error:function(){
	    	layer.msg("检验编码时网络错误");
	    },
	    success:function(data){
	    	if(data == 'true'){
	    		globalVar.flag  = true;
			}
	    } 
	});
}
function deleteRow($this){
	var value = {};
	value.id = $this.data('id');
	value.parentCode = $('#code').val();
	$.post(globalVar.base+"/dictionary/deleteValue",value,function(data){
		if(data.success==true){
					
			layer.msg('操作成功!',{icon:1,time:1000});
		}else{
			layer.msg("删除失败");
		}
	},"json")
	.error(function(){
		layer.msg("网络异常");
	});
}
function addRow(){
	var flag = false;
	if($('#operationType').val() != 'update'){
		//如果是新增，需要检验编码是否重复
		checkoutCode();
		if(!globalVar.flag){
			if($("#code").val() != '') alert('编码检验失败！');
			return;
		}
	}else{
		//如果是更新，若没有一行数据则跳过，有数据则将preDiv（要新增div的前一个div）移至正确位置，然后更正全局变量valueTrNum。
		var $temp = $('.value_div:last'),$code = $('#code');
		if($temp.length != 0){
			$('.preDiv').removeClass('preDiv');
			$temp.addClass('preDiv');
			globalVar.valueTrNum = $temp.find('input:eq(1)').val()
						.substring($code.val().length,$code.val().length+1);
		}
	}
	if(globalVar.valueTrNum == 0){
		flag = true;
	}else{		//如果不是第一行，必须将当前行值填入后再添加下一行
		if($('.preDiv input:eq(0)').val() != ''){
			flag = true;
			if($('.preDiv input:eq(1)').val() != ''){
				
			}
		}else{
			alert('请先填好当前行信息！');
		}
	}
	if(flag){
		var html = '<div class="row cl preDiv value_div">'+
        '<label class="form-label col-xs-4 col-sm-2 lable1">值'+(++globalVar.valueTrNum)+'：</label>'+
        '<div class="formControls col-xs-4 col-sm-2">'+
        '<input  type="text" class="input-text" value="" placeholder="" />'+
        '</div>'+
        '<label class="form-label col-xs-4 col-sm-2 lable1" style="margin-left:0;">值编码：</label>'+
        '<div class="formControls col-xs-4 col-sm-2">'+
        '<input  readonly="readonly" name="code" type="text" class="input-text" value="'+
        generativeCoding()+'" placeholder="" />'+
        '</div>'+
        '</div>';
		$('.preDiv').eq(0).removeClass('preDiv').after(html);
	}
}
//保存or新增
function doSave(){
	var valueString = '',codes = '',$this;
	globalVar = setParam(globalVar,'dictionaryId,code,name,remark,operationType');
	$('.value_div').each(function(){
		valueString +=  $(this).find('input:eq(0)').val()+'|';
		codes       +=  $(this).find('input:eq(1)').val()+'|';
	});
	globalVar.valueString = valueString.substring(0,valueString.length-1);
	globalVar.codes = codes.substring(0,codes.length-1);
	if($("#dictForm").valid()){	
		$.post(globalVar.base+"/dictionary/operated",globalVar,function(data){
			if(data.success == true){
				layer.msg('操作成功!',{icon:1,time:1000});
	    		setTimeout(function(){
	    			parent.location.href = globalVar.base+'/dictionary/display';
	    			layer_close();
	    		},1200);
			}else{
				layer.msg('操作失败!',{icon:2,time:1000});
			}
		},"json").error(function(){
			layer.msg("网络错误");
		});
	}
}
//根据id将值设置到传入的对象中
function setParam(param,ids){
    var idStr = '';
    ids = ids.split(',');
    for(var i in ids){
        idStr = '#'+ids[i];
        param[ids[i]] = $(idStr).val() == ''?$(idStr).text():$(idStr).val();
    }
    return param;
}