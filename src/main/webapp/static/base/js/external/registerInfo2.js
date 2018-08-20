/**
 * Created by administer on 2018-01-23.
 */
var deptList = [];
var firstDeptList = [];
var secondDeptList = [];
var doctorList = [];
var scheduleList =[] ;
var registerSource =[] ;
var initFlag = true;
var schedule = {};
/**
 *初始化
 * */
$(function(){
    var request1 = $.post(base + "/register/getDeptList?hosCode="+$("#hosCode").val());

    request1.done(function(data){
        if( data.resultCode == "0" ){
            deptList = data.resultObjects;
            secondDeptList = deptList;
            var firstStr = "";
            $.each(deptList ,function(i,n){
                firstStr += "<tr>"+
                    "<td class=\"td_first\">"+
                    "<input type=\"checkbox\" class=\"sin_point\" data-classname = \""+n.className+"\">"+
                    "</td>"+
                    "<td>"+n.deptName+"</td>"+
                    "</tr>";
            });
            $("#firstDept").html(firstStr);
            //绑定点击事件
            bindSinPointClick();
        }else{
            initFlag = false;
            alert("科室数据获取失败!"+data.errorMsg);
        }
    });


    //数据获取成功后生成
    // $.when(request1).done(function(){
    //     if(initFlag){
    //         //一级科室筛选
    //         for(var i = 0; i < deptList.length; i++){
    //             for(var j = 0; j < secondDeptList.length; j++){
    //                 if( deptList[i].deptCode == secondDeptList[j].deptCode){
    //                     var firstDept ={};
    //                     firstDept.className = deptList[i].className;
    //                     firstDeptList[firstDeptList.length] =firstDept;
    //                     secondDeptList[j].className= deptList[i].className;
    //                 }
    //             }
    //         }
    //         //过滤
    //         firstDeptList = uniqueArray(firstDeptList,"className");
    //         //生成一级科室
    //         var firstStr = "";
    //         $.each(firstDeptList ,function(i,n){
    //             firstStr += "<tr>"+
    //                 "<td class=\"td_first\">"+
    //                 "<input type=\"checkbox\" class=\"sin_point\" data-classname = \""+n.className+"\">"+
    //                 "</td>"+
    //                 "<td>"+n.className+"</td>"+
    //                 "</tr>";
    //         });
    //         $("#firstDept").html(firstStr);
    //         //绑定点击事件
    //         bindSinPointClick();
    //     }else{
    //         alert("预约挂号数据获取失败！");
    //     }
    // });
});

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
            // $(".appointment_kinds").css('display','block')
            //二级操作控制
            $('.sin_kinds').click();
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
            var request2 = $.post(base + "/register/getScheduleList?hosCode="+$("#hosCode").val()+"&deptCode="+deptCode);

            request2.done(function(data){
                if( data.resultCode == "0" ){
                    scheduleList = data.resultObjects;
                    registerSource = scheduleList;
                    $.each(scheduleList ,function(i,n){
                        var doctor =  {};
                        var secondDept =  {};
                        doctor.title = n.PlanID;
                        doctor.deptCode = n.divisionID;
                        doctor.deptName = n.divisionName;
                        doctor.doctorCode = n.doctorID;
                        doctor.doctorName = n.doctorName;
                        doctorList[i] = doctor;
                    });
                    //重复项过滤
                    // secondDeptList = uniqueArray(secondDeptList,"deptCode");
                    // doctorList = uniqueArrayTwoKey(doctorList,"deptCode","doctorCode");
                    scheduleList = uniqueArrayTwoKey(scheduleList,"PlanID","PlanDate");
                    doctorList = uniqueArray(doctorList,"title");
                    $.each(doctorList ,function(i,n){
                        doctorStr += "<tr>"+
                            "<td class=\"td_first\">"+
                            "<input type=\"checkbox\" class=\"sin_items\" data-deptcode = \""+ n.deptCode+ "\" data-doctorcode = \""+ n.title+ "\">"+
                            "</td>"+
                            "<td>"+n.title+"</td>"+
                            "</tr>";
                    });
                    $("#doctor").html(doctorStr);
                    bindSinItemsClick();
                    $(".appointment_item").css('display','block')
                }else{
                    initFlag = false;
                    alert("排班数据获取失败!"+data.errorMsg);
                }
            });

        }else{
            $("#doctor").html("")
        }
    });
}

/**
 * 医生绑定点击事件
 * */
function bindSinItemsClick() {
    $('.sin_items').click(function(){
        if($(this).prop('checked')){
            $('.sin_items').prop('checked',false);
            $(this).prop('checked',true);
            var deptCode = $(this).data("deptcode");
            var doctorCode = $(this).data("doctorcode");
            var scheduleStr="";
            $.each(scheduleList ,function(i,n){
                if( deptCode == n.divisionID && doctorCode == n.PlanID){

                    scheduleStr += '<tr style="border-top:1px solid #E7E7E7">'+
                        '<td>'+n.divisionName+'</td>'+
                        '<td>'+n.PlanID+'</td>'+
                        '<td>'+ formatWorkDate(n.PlanDate) +'</td>';
                    // if(n.PlanType == "all"){
                    //     scheduleStr +=  '<td>全天</td>';
                    // }else if(n.PlanType =="am"){
                    //     scheduleStr +=  '<td>上午</td>';
                    // }else if(n.PlanType =="am"){
                    //     scheduleStr +=  '<td>下午</td>';
                    // }else{
                    //     scheduleStr +=  '<td>'+n.PlanType+'</td>';
                    // }
                    scheduleStr += '<td>'+n.RegTypeName+'</td>'+
                        '<td style="color:red;cursor:pointer">'+
                        '<a href="javascript:;" style="color:red" onclick="register(this)" data-schedule="'+JSON.stringify(n).replace(/"([^"]*)"/g, "'$1'")+'">'+
                        '预约'+
                        '</a>'+
                        '</td>'+
                        '</trstyle>';
                }

            });
            $("#schedule").html(scheduleStr);
        }else{
            $("#schedule").html("");
        }
    });
}


/**
 * 预约界面生成
 * */
function register(e) {
    //获取排班数据
    var aa = $(e).data("schedule");
    var patient = $("#patient").serializeObject();
    console.info(patient);
    schedule = JSON.parse(aa.replace(/'/g, '"'));
    $("#deptName").val(schedule.divisionName);
    $("#doctorName").val(schedule.doctorName);
    $("#workDate").val(formatWorkDate(schedule.PlanDate));
    $("#patientName").val(patient.patientName);
    $("#patientMediumCode").val(patient.patientMediumCode);
    $("#scheduleCode").val(schedule.PlanID);
    //时段控制
    var workPeriodStr = "";
    // if( schedule.workPeriod == "all"){
    //     workPeriodStr += "<option value=\"AM\">上午</option><option value=\"PM\">下午</option>";
    //     //绑定事件
    //     $("#workPeriod").off("change").change(function(){
    //         getRegisterSource($(this).val());
    //     });
    // }else{
    //     if( schedule.workPeriod =="AM"){
    //         workPeriodStr += "<option value=\"AM\">上午</option>";
    //     }else if( schedule.workPeriod =="pm"){
    //         workPeriodStr += "<option value=\"PM\">下午</option>";
    //     }
    // }

    // $("#workPeriod").html(workPeriodStr);
    //获取号源
    getRegisterSource(schedule.PlanID,schedule.PlanDate);
    $('.zhezhao').css('display','block');
    $('.alertBox').css('display','block');
    $('#tijiao').attr("disabled",false);
}

/**
 * 界面关闭
 * */
function registerClose() {
    $('.zhezhao').css('display','none');
    $('.alertBox').css('display','none');
}

/**
 * 获取号源
 * */
function getRegisterSource(title,date) {
    $("#registerSource").html("");
    //过滤排班
    var sourceStr = "";
    //显示排班
    $.each(registerSource,function(i,n){
        if( n.PlanID === title && n.PlanDate === date ){
            sourceStr +="<option value=\""+n.ScheduleId+"\" data-source = \""+JSON.stringify(n).replace(/"([^"]*)"/g, "'$1'")+"\">"+ n.PlanType +"</option>";
        }

    });
    $("#registerSource").html(sourceStr);

}

/**
 * 保存预约信息
 * */
function saveRegisterInfo() {
    $('#tijiao').attr("disabled",true);
    var patient = $("#patient").serializeObject();
    console.info(patient);
    // var data = $.extend({}, patient, schedule);
    var data = patient;
    data.deptCode = schedule.divisionID;
    data.deptName = schedule.divisionName;
    data.doctorCode = schedule.doctorID;
    data.doctorName = schedule.doctorName;
    data.workDate = formatWorkDate(schedule.PlanDate);
    data.workPeriod = schedule.PlanType;
    data.registerType = schedule.RegTypeName;
    data.scheduleCode = schedule.ScheduleId;
    data.sequenceNumber = schedule.ScheduleId;
    // //获取号源和排班号
    // var sourceStr = $("#registerSource").find("option:selected").data("source");
    // var source = JSON.parse(sourceStr.replace(/'/g, '"'));
    // var data = $.extend({}, data, source);
    console.info(JSON.stringify(data));
    $.ajax({
        url: base + "/register/registerReservation",
        type: "post",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        dataType: "json",
        success: function (data) {
            if( data.resultCode == "0"){
                window.location.href = base + "/reserve/registerCarryOut.do";
            }else{
                alert("预约挂号失败！");
                $('#tijiao').attr("disabled",false);
            }
        },
        error: function (e) {
            alert("接口调用错误！");
            $('#tijiao').attr("disabled",false);
        }
    });
}

/**
 * 根据一个keyJSON数组去重
 * @param array json Array
 * @param key 唯一的key名，根据此键名进行去重
 * @returns {[*]}
 */
function uniqueArray(array, key){
    var result = [array[0]];
    for(var i = 1; i < array.length; i++){
        var item = array[i];
        var repeat = false;
        for (var j = 0; j < result.length; j++) {
            if (item[key] == result[j][key]) {
                repeat = true;
                break;
            }
        }
        if (!repeat) {
            result.push(item);
        }
    }
    return result;
}

/**
 * 根据两个keyJSON数组去重
 * @param array
 * @param key1
 * @param key2
 * @returns {[*]}
 */
function uniqueArrayTwoKey(array, key1,key2){
    var result = [array[0]];
    for(var i = 1; i < array.length; i++){
        var item = array[i];
        var repeat = false;
        for (var j = 0; j < result.length; j++) {
            if (item[key1] == result[j][key1] && item[key2] == result[j][key2]) {
                repeat = true;
                break;
            }
        }
        if (!repeat) {
            result.push(item);
        }
    }
    return result;
}

$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


function formatWorkDate(date) {
    var newDate = new Date(date);
    return newDate.format('yyyy-MM-dd')
}


Date.prototype.format = function(format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
};