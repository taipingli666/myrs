$(function(){
	var hosId = $("#hosId").val();
	$('#search').click(function(){
	    $("#page").val(1);
		doSearch(1);
	});


	//下拉框数据回显
	/*$('#contractOfConditions select').each(function(){
		$(this).val($(this).parents('li').find('input').val());
	});*/
});
//search
function doSearch(pageNumber) {
    var $form = $('form');
    url = globalVar.base+'/register/getRegisterInfoList?';
    if($("form").valid()){
        url += 'page=' + pageNumber +'&';
        url += $form.serialize();
        window.location = url;
    }
}
//翻页
function changePage(pageNumber){

    var $form = $('form');
    url = globalVar.base+'/register/getRegisterInfoList.do?';
    if($("form").valid()){
        url += 'page=' + pageNumber +'&';
        url += $form.serialize();
        window.location = url;
    }

}

/**
 * 取消
 * @param id
 */
function feedback(id) {
	var data={};
    data.id = id;
    layer.confirm('您是否取消预约？', {
        btn: ['同意','不同意'] //按钮
    }, function(){
        // data.registerStatus = "8";
        changeInfo(data);
    }, function(){
        // data.registerStatus = "3";
        // changeInfo(data);
    });
}
// 备注
function feedback2(id) {
    var data={};
    data.id = id;
    layer.prompt({
        formType: 2,
        title: '请填写备注',
        value:$("#"+id+"").val(),
        area: ['400px', '200px'] //自定义文本域宽高
    },function(value, index){
        data.feedback = value;
        feedbackinfo(data);
        layer.close(index);
    });
}

/**
 * 修改状态
 * @param data
 */
function changeInfo(data) {
    $.ajax({
        url:globalVar.base +"/register/cancelRegisterReservation",
        dataType:"json",
        contentType:"application/json",
        type:"post",
        data:JSON.stringify(data),
        success:function(data){
            if(data.resultCode == 0){
                layer.msg(data.errorMsg);
                doSearch($("#page").val());
            }else{
                layer.msg(data.errorMsg);
            }
        }
    });
}
// 备注信息
function feedbackinfo(data) {
    $.ajax({
        url:globalVar.base +"/register/changeRegisterInfo",
        dataType:"json",
        contentType:"application/json",
        type:"post",
        data:JSON.stringify(data),
        success:function(data){
            if(data.resultCode == 0){
                layer.msg(data.errorMsg);
                doSearch($("#page").val());
            }else{
                layer.msg(data.errorMsg);
            }
        }
    });
}


//查看转出病人详细详细信息
$(".check").on("click",function(){
    var url = globalVar.base+'/register/register?id='+$(this).next().val();
    var $window = $(window);
    layer_show("预约信息",url,$window.width(),$window.height());
});


//挂号打印
function registerPrint(id) {
    var url = globalVar.base+'/register/registerPrint?id='+id;
    var $window = $(window);
    layer_show("门诊转诊打印单",url,$window.width(),$window.height());
}

