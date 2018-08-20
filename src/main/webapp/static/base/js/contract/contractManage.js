$(function(){
	//下拉框数据回显
	$('#contractOfConditions select').each(function(){
		$(this).val($(this).parents('li').find('input').val());
	});
	//当前页数
	globalVar.currentPage = $("#currentPage").val();
	//导出事件
	$('#export').on('click',exports);
	//查询
	//$('#query').on('click',doSearch);
    $('#query').click(function() {
        doSearch(1);
    });
	//简单身份证号码验证
	jQuery.validator.addMethod("isIdCardNo", function(value, element) {
		return this.optional(element) || (/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(element));
	}, "请正确输入您的身份证号码");
	//tr双击触发修改
	$('#tbody').on('click dblclick','tr',function(e){
		var $curTr = $(this);
		if (e.type === "click") {
			$curTr.addClass('curTr').siblings().removeClass('curTr');
			globalVar.contractId = $(this).data('contid');
	    } else if(e.type === "dblclick") {
			addRow('修改合同信息',globalVar.base+'/contract/operatedUI.do?operationType=edit&contractId='+globalVar.contractId);
	    }
	});
	
	$('#referral,#renew,#cancel').on('click',function(){
		var url = '';
		var $window=$(window);
		if(globalVar.contractId == undefined){
			alert('请先点击选中合同！');
			return;
		}
		if(globalVar.contractId != '' && /^[0-9]*[1-9][0-9]*$/.test(globalVar.contractId)){
			url = globalVar.base+'/contract/operatedUI.do?operationType='+$(this).attr('id')+'&contractId='+globalVar.contractId;
			if($(this).attr('id') == 'renew'){
				layer_show('续约合同',globalVar.base+'/contract/getSignYear.do?contractId='+globalVar.contractId,0.4*$window.width(),0.4*$window.height());
			}else if($(this).attr('id') == 'referral'){
				layer_show('转介合同',url,0.4*$window.width(),0.4*$window.height());
			}else{
				terminate(globalVar.contractId);			
			}
		}
	});
});

$("#contractOfConditions").validate({
	rules:{
		card:{
		isIdCardNo:true
		}
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid"
});
//导出
function exports(){
	var $form = $('#contractOfConditions'),url;
	url  = globalVar.base+'/contract/export.do?'+$form.serialize();
	$form.attr("action",url);
	$form.submit();
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
//search
function doSearch(pageNumber){
 var $form = $('#contractOfConditions'),
	 url = globalVar.base+'/contract/display.do?';
 if($("#contractOfConditions").valid()){	
// 	pageNumber = $('.paging .num_cur').text();
	url += 'pageNumber=' + pageNumber +'&';
	url += $form.serialize();
	window.location = url;
 }
}
function changePage(pageNumber){
	doSearch(pageNumber);
}
//解约
function terminate(contractId){
	if(window.confirm('是否确定解约？')){
		$.ajax({
			cache:false,//是否使用缓存
            url:globalVar.base+"/contract/operated.do", 
            async:true,   //是否异步，false为同步
            type:"post",
            data:{'contractId':contractId,'isDelete':1},
            error:function(){
            	alert("请求失败！");
            },
            success:function(data){
            	if(data.success==true){
					layer.msg('操作成功!',{icon:1,time:1000});
	    			setTimeout(function(){
	    				doSearch(1);
	    			},1200)
				}else{
					layer.msg("删除失败");
				}
		    } 
		});
	}		
}

