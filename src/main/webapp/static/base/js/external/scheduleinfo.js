var weekday=new Array(7);
weekday[0]="星期天",
weekday[1]="星期一",
weekday[2]="星期二",
weekday[3]="星期三",
weekday[4]="星期四",
weekday[5]="星期五",
weekday[6]="星期六";
//根据时间获得星期几
function getWeek(dp){
	var _date = dp.cal.getNewDateStr();  
    var d=new Date(_date);
    $("#J_Week").val(weekday[d.getDay()]);
}
var initFlag = true;
var deptList = null;

$("#formOperated").validate({
    rules:{
        scheduleType:{
            required:true
        },
        deptCode:{
            required:true
        },
        doctorCode:{
            required:true
        },
        workDate:{
            required:true
        },
        workPeriod:{
            required:true
        },
        workTimeStart:{
            required:true
        },
        workTimeEnd:{
            required:true
        }
    },
    onkeyup:true,
    focusCleanup:true,
    success:"valid"
});

/**
 * 初始化
 */
$(function(){
	var hosId=$("#hosId").val();

    $.ajax({
        url:base +"/department/getDepartmentList",
        dataType:"json",
        contentType:"application/json",
        type:"post",
        data:JSON.stringify({"hosid":hosId}),
        success:function(data){
            if(data !=null && data.length > 0){
                deptList = data;
                setData();
            }else{
                initFlag = false;
                alert("科室数据获取失败!");
            }
        },
        error:function(){
            initFlag = false;
            layer.msg("获取科室失败,网络连接异常");
        }
    });
});

/**
 * 初始化设置数据
 */
function setData() {
    changeScheduleType();

    var workTimeStart = $("#workTimeStart").val();
    var workTimeEnd = $("#workTimeEnd").val();

    hiddenChange($("#deptCode"),'2');
    changeWuBie();
    $("#workTimeStart").val(workTimeStart);
    $("#workTimeEnd").val(workTimeEnd);
    var d=new Date($("#workDate").val());
    $("#J_Week").val(weekday[d.getDay()]);
}
/**
 * 科室分类绑定点击事件
 * */
function bindSinPointClick() {
    $('.sin_point').click(function(){
        if($(this).prop('checked')){
            $(".appointment_item").css('display','none');
            $('.sin_point').prop('checked',false);
            $(this).prop('checked',true);
            var className = $(this).data("classname");
            var secondStr="";
            $.each(secondDeptList ,function(i,n){
                if( className == n.className ){
                    secondStr += "<tr>"+
                        "<td class=\"td_first\">"+
                        "<input type=\"checkbox\" class=\"sin_kinds\" data-deptcode = \""+ n.deptCode+ "\">"+
                        "</td>"+
                        "<td>"+n.deptName+"</td>"+
                        "</tr>";
                }
            });
            $("#secondDept").html(secondStr);
            bindSinKindsClick();
            $(".appointment_kinds").css('display','block')
        }else{
            $("#secondDept").html("")
            $("#doctor").html("")
        }
    });
}



/**
 * 科室绑定点击事件
 * */
function bindSinKindsClick() {
    $('.sin_kinds').click(function(){
        if($(this).prop('checked')){
            $('.sin_kinds').prop('checked',false);
            $(this).prop('checked',true);
            var deptCode = $(this).data("deptcode");
            var doctorStr="";
            $.each(scheduleList ,function(i,n){
                if( deptCode == n.deptCode ){
                    doctorStr += "<tr>"+
                        "<td class=\"td_first\">"+
                        "<input type=\"checkbox\" class=\"sin_items\" data-deptcode = \""+ n.deptCode+ "\" data-doctorcode = \""+ n.doctorCode+ "\">"+
                        "</td>"+
                        "<td>"+n.doctorName+"</td>"+
                        "</tr>";
                }
            });
            $("#doctor").html(doctorStr);
            bindSinItemsClick();
            $(".appointment_item").css('display','block')
        }else{
            $("#doctor").html("")
        }
    });
}


/**
 * 下拉选择科室或者医生事件
 * @param obj
 * @param bz
 */
function hiddenChange(obj,bz){
    var val = $(obj).find("option:selected").html();
    if(bz == '1'){
        $("#doctorName").val(val);
    }else if(bz == '2'){
        $("#deptName").val(val);
        $("#doctorName").val("");
        var doctorCode = $("#doctorCode").val();

        $("#doctorCode").html('<option value="">-请选择-</option>');
        // $('#doctorCode').selectpicker('refresh');
        // $('#addForm').bootstrapValidator('resetForm');
        $.ajax({
            url:base + "/user/selectByUserInfo",
            dataType:"json",
            type:"post",
            contentType:"application/json",
            data:JSON.stringify({"hosId":$("#hosId").val(),"deptCode":$("#deptCode").val()}),
            success:function(data){
                if(data.success == true){
                    //赋值
                    $("#doctorCode").html('<option value="">-请选择-</option>');
                    var st = '';
                    $(data.data).each(function(m,n){
                        st += '<option  value="'+n.userId+'">'+n.trueName+'</option>';
                    });
                    $("#doctorCode").append(st);
                    //刷新
                    // $('#doctors').selectpicker('refresh')

                    if(doctorCode !=""){
                        $("#doctorCode").val(doctorCode);
                    }
                }
            },
            error:function(){
                layer.msg("获取医生失败,网络连接异常");
            }
        });
    }
}
//选择午别事件
function changeWuBie(){
    var s = $("#workPeriod").val();
    var sjd,
        amList='<option value="" >未选择时间</option><option value="08:00">08:00</option><option value="09:00">09:00</option><option value="10:00">10:00</option><option value="11:00">11:00</option><option value="12:00">12:00</option>',
        pmList='<option value="" >未选择时间</option><option value="13:00">13:00</option><option value="14:00">14:00</option><option value="15:00">15:00</option><option value="16:00">16:00</option><option value="17:00">17:00</option>';
    if(s == 'AM'){
        // sjd = $("#AM").val();
        $("#workTimeStart").html(amList);
        $("#workTimeEnd").html(amList);

    }else if(s == 'PM'){
        // sjd = $("#PM").val();
        $("#workTimeStart").html(pmList);
        $("#workTimeEnd").html(pmList);
    }
    // var split = sjd.split("-");
    // $("#kaishisj").val(split[0]);
    // $("#jieshusj").val(split[1]);
}

/**
 * 分类选择事件
 */
function changeScheduleType() {

    var scheduleType = $("#scheduleType").val();

    if(initFlag && deptList != null){
        var detpHtml = "<option value=\"\">未选择科室</option>";
        for(var i = 0;i<deptList.length;i++){
            if(scheduleType == "1" && deptList[i].distanceFlag=="0"){
                continue;
            }
            detpHtml += "<option value=\""+deptList[i].departmentid+"\"";
            if(deptList[i].departmentid == $("#deptCode").val()){
                detpHtml += "selected";
            }

            detpHtml += ">"+deptList[i].departmentname+"</option>";
        }
        $("#deptCode").html(detpHtml)
    }

}

function saveScheduleInfo() {
    $("#save").attr("disabled",true);
    if($("#formOperated").valid()){
        $.ajax({
            url:base + "/scheduleInfo/saveScheduleInfo",
            dataType:"json",
            type:"post",
            data: $("#formOperated").serialize(),
            success:function(data){
                if(data.success == true){
                    $("#scheduleInfoId").val(data.data)
                    layer.msg("保存成功");
                }else{
                    layer.msg("保存失败");
                }
                $('#save').attr("disabled","disabled");
            },
            error:function(){
                layer.msg("保存排班失败,网络连接异常");
                $('#save').attr("disabled","disabled");
            }
        });
    }else{
        $('#save').attr("disabled","disabled");
    }

}