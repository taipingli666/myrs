/**
 * Created by administer on 2018-02-06.
 */
$(function(){
    var hosId = $("#hosId").val();
    $('#search').click(function(){
        doSearch(1);
    });


    //下拉框数据回显
    $('#contractOfConditions select').each(function(){
        $(this).val($(this).parents('li').find('input').val());
    });
});

//search
function doSearch(pageNumber) {
    var $form = $('#contractOfConditions');
    url = contextPath+'/scheduleInfo/getScheduleInfoList?';
    if($("#contractOfConditions").valid()){
        url += 'page=' + pageNumber +'&';
        url += $form.serialize();
        window.location = url;
    }
}
function changePage(pageNumber){
    doSearch(pageNumber);
}

function addRow(id){
    var tit = "";
    tit = id==0?"新增排班":"更新排班";
    var index = layer.open({
        type: 2,
        title: tit,
        content: contextPath+"/scheduleInfo/scheduleInfo?&id="+id
    });
    layer.full(index);
}

/**
 * 生成排班界面
 */
function buildSchedulePage() {
    layer.open({
        type: 1,
        shade: false,
        zIndex:9999999999,
        title: false, //不显示标题
        skin: 'layui-layer-rim', //加上边框
        area: ['420px', '100px'], //宽高
        content: $("#buildSchedule")
    });
    // layer.open({
    //     type: 1,
    //     skin: 'layui-layer-rim', //加上边框
    //     area: ['420px', '140px'], //宽高
    //     content: $("#buildSchedule").html()
    // });
}

/**
 * 生成排班
 */
function buildSchedule() {
    $("#buildScheduleBtn").attr("disabled",true);
    var bulidDate = $("#bulidDate").val();
    if( bulidDate == ""){
        layer.msg("没有选择日期！");
        return;
    }

    $.ajax({
        url:contextPath +"/scheduleTemplate/buildSchedule?buildDate=" + bulidDate,
        dataType:"json",
        type:"post",
        success:function(data){
            $("#buildScheduleBtn").attr("disabled",false);
            layer.msg(data.errorMsg);
            if(data.resultCode == "0"){
                $("#search").click();
            }

        },
        error:function(){
            initFlag = false;
            layer.msg("保存模板失败,网络连接异常");
            $("#buildScheduleBtn").attr("disabled",false);
        }
    });
}

function dels(){
    layer.confirm('是否要删除数据?', {
        btn: ['是','否'] //按钮
    }, function(){
        var $tbody = $("#tbody input[type='checkbox']:checked"),ids = '';
        if($tbody.length==0){
            layer.msg('请先选择要删除的数据！');
            return;
        }
        $tbody.each(function(i){
            ids += $(this).data('id')+",";
        });
        $.post(contextPath+"/scheduleInfo/deleteSchedule",{"ids":ids},function(data){
            if(data.success==true){
                layer.msg(data.message,{icon:1,time:2000});
                setTimeout(function(){
                    window.location=contextPath+"/scheduleInfo/getScheduleInfoList.do";
                },1200)
            }else{
                layer.msg("删除失败");
            }
        },"json").error(function(){
            layer.msg("网络异常");
        });
    }, function(){

    });
}
