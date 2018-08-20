$(function(){

	//当前页数
	globalVar.currentPage = $("#currentPage").val();
	//绑定事件
	$('#search').on('click',doSearch);
	$('#remove').on('click',doRemove);
	$('#add').on('click',function(){
		addRow('科室新增',globalVar.base+'/department/operating.do?operationType=add');
	});
	//模糊查询体检更改及时更新
	$('#contents').on('change',function(){
		globalVar.contents = encodeURI(encodeURI($("#contents").val()));
	});
	
	$('#hosid').on('change',function(){
		globalVar.hosid = encodeURI(encodeURI($("#hosid").val()));
		globalVar.hosnum = $("#hosid").find("option:selected").data('hosnum');
	});
});
//search
function doSearch(pageNumber){
	var $form = $("form");
	// var url = globalVar.base+'/referral/display.do?patName='+$("#patName").val()
	// + '&refStatus='+$("#refStatus").val()+'&refType='+$("#refType").val() ;
    var url = globalVar.base+'/referral/display.do?';
    url += $form.serialize();
	window.location = url;
}
function changePage(pageNumber){
    var $form = $("form");
    var url = globalVar.base+'/referral/display.do?pageNumber=' + pageNumber+'&';
    url += $form.serialize();
    window.location = url;

}

function edit(id){
	var index = layer.open({
		type: 2,
		content: globalVar.base+'/department/operating.do?operationType=update&id='+id+"&pageNumber="+globalVar.currentPage
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
	 	ids += $(this).parents('tr').data('departmentid')+',';
	});
	//if(window.confirm('是否确定要删除这些数据？')){
		$.ajax({
			cache:false,//是否使用缓存
            url:globalVar.base+"/department/delete.do", 
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

//查看转出病人详细详细信息
$(".check").on("click",function(){
	var url = globalVar.base+'/referral/checkDetail?id='+$(this).next().val();
	var $window = $(window);
	layer_show("病人详情",url,$window.width(),$window.height());
});

//打印
$(".print").click(function () {
    var url = globalVar.base+'/referral/print?id='+$(this).next().val();
    var $window = $(window);
    layer_show("转诊单",url,$window.width(),$window.height());
})

//操作
function operation(id, statu) {
    var data = {};
    data.id = id;
	if (statu == '3') {
        layer.prompt({
            formType: 2,
            title: '请输入备注',
			value:$("#"+id+"").val(),
            area: ['400px', '200px'] //自定义文本域宽高
        },function(value, index){

            data.feedback = value;
            updateStatus(data);
            layer.close(index);
        });
	} else {
        updateStatus(data);
	}


}

function  updateStatus(data) {
    $.ajax({
        url:globalVar.base+'/referral/changeDetail.do',
        dataType:"json",
        contentType:"application/json",
        type:"post",
        data:JSON.stringify(data),
        success:function(data){
            if(data.resultCode == 0){
                layer.msg(data.errorMsg);
                window.location.reload();
            }else{
                layer.msg(data.errorMsg);
            }
        },
        error:function(){
            initFlag = false;
            layer.msg("操作失败,网络连接异常");
        }
    });
}
