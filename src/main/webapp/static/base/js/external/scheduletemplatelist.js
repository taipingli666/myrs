/**
 * 条件选择事件
 */
$('.selM').on('click',"li",function(){
    $(this).find('span').addClass('active').parent().siblings().find('span').removeClass('active');
    var type = $(this).find('span').data("type");
    var data = $(this).find('span').data(type.toLowerCase());
    $("#"+type).val(data);
    selectList();
});

/**
 * 初始化
 */
$(function(){
    var hosId=$("#hosCode").val();

    //获取科室
    $.ajax({
        url:globalVar.base +"/department/getDepartmentList",
        dataType:"json",
        contentType:"application/json",
        type:"post",
        data:JSON.stringify({"hosid":hosId}),
        success:function(data){
            if(data !=null){
                var detpHtml = "<option value=\"\" selected>所有科室</option>";
                for(var i = 0;i<data.length;i++){

                    detpHtml += "<option value=\""+data[i].departmentid+"\">"+data[i].departmentname+"</option>";
                }
                $("#deptCodeSelect").html(detpHtml)
                selectList();
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

//  点击复制内容
function copyCon(){
    var arr=[];
    $('#scheduleTemplate').find('dl').each(function(index,ele){
        if(index==0){
            $(this).find('li').each(function(ind,elem){
                if(ind>0){
                    var val=$(this).find('input').val();
                    arr.push(val);
                }

            });

        }else if(index>0){
            $(this).find('li').each(function(a,b){
                if(a>0){
                    $(this).find('input').val(arr[a-1]);
                }

            })
        }
    })
}

/**
 * 科室修改事件
 */
function deptChange() {
    $("#deptCode").val($("#deptCodeSelect").val());
    selectList();
}

/**
 *查询排班模板
 */
function selectList() {
    //获取列表
    $.ajax({
        url:globalVar.base +"/scheduleTemplate/getscheduleTemplateList",
        dataType:"json",
        type:"post",
        data:$("#formDate").serialize(),
        success:function(data){
            if(data !=null ){
                var scheduleTemplateList = "";
                for(var i = 0;i<data.length;i++){

                    scheduleTemplateList += "<dl data-scheduletemplate=\""+JSON.stringify(data[i]).replace(/"([^"]*)"/g, "'$1'")+"\">\n" +
                        "                        <dt>"+data[i].deptName+"</dt>\n" +
                        "                        <dd>\n" +
                        "                            <ul class=\"clearfix doctor\">\n" +
                        "                                <li>"+setNull(data[i].doctorName)+"</li>\n" +
                        "                                <li><input type=\"text\" value=\""+setNull(data[i].workTimeStart)+"\"></li>\n" +
                        "                                <li><input type=\"text\" value=\""+setNull(data[i].workTimeEnd)+"\"></li>\n" +
                        "                                <li><input type=\"text\" value=\""+setNull(data[i].registerQuantity)+"\"></li>\n" ;
                    if( i == 0 ){
                        scheduleTemplateList +="<li><button class=\"clear\" onclick=\"clearInput(this)\">清除</button>&nbsp;<button class=\"copy\" onclick=\"copyCon()\">复制</button></li>\n";
                    }else{
                        scheduleTemplateList +="<li><button class=\"clear\" onclick=\"clearInput(this)\">清除</button>\n";
                    }

                    scheduleTemplateList += "                            </ul>\n" +
                        "                        </dd>\n" +
                        "                    </dl>";
                }
                $("#scheduleTemplate").html(scheduleTemplateList)
            }else{
                initFlag = false;
                alert("排班模板获取失败!");
            }
        },
        error:function(){
            initFlag = false;
            layer.msg("获取排班模板失败,网络连接异常");
        }
    });
}

/**
 * 保存模板
 */
function save() {
    var reg1=/^([0-1][0-9]|2[0-3]):([0-5][0-9])$/;
    var reg2=/^[0-9]*$/;
    var bTime="";
    var eTime="";
    var flag = true;
    $(".doctor").each(function(index,ele){
        $(this).find("li").each(function(ind,elem){
            if($(this).find('input').val()) {
                if(ind == 1) {
                    bTime = $(this).find('input').val();
                    if (!reg1.test(bTime)) {
                        layer.msg('您输入的起始时间有误！');
                        flag = false;
                        return false;
                    }
                }else if(ind == 2) {
                    eTime = $(this).find('input').val();
                    if (!reg1.test(eTime)) {
                        layer.msg('您输入的结束时间有误！');
                        flag = false;
                        return false;
                    }

                } else if(ind == 3) {
                    var val = $(this).find('input').val();
                    if (!reg2.test(val)) {
                        layer.msg('您输入的号源数量有误！');
                        flag = false;
                        return false;
                    }
                }
                if (reg1.test(bTime) && reg1.test(eTime)) {
                    var value1 = bTime.substr(0, 2);
                    var value2 = eTime.substr(0, 2);
                    if (value1 > value2) {
                        layer.msg('您输入的结束时间有误！');
                        flag = false;
                        return false;
                    }
                }
            }

        })
    });
    if(flag == true){
        var domlist = $("#scheduleTemplate").find("dl");
        if(domlist.length<1){
            return;
        }
        var data = [];
        $.each(domlist,function(i,v){
            var scheduleTemplate=  JSON.parse($(v).data("scheduletemplate").replace(/'/g, '"'));
            scheduleTemplate.hosCode = $("#hosCode").val();
            scheduleTemplate.scheduleType = $("#scheduleType").val();
            scheduleTemplate.weekDay = $("#weekDay").val();
            scheduleTemplate.workPeriod = $("#workPeriod").val();
            scheduleTemplate.workTimeStart = $(v).find("input:eq(0)").val();
            scheduleTemplate.workTimeEnd = $(v).find("input:eq(1)").val();
            scheduleTemplate.registerQuantity = $(v).find("input:eq(2)").val();
            data[i]=scheduleTemplate;
        });
        $.ajax({
            url:globalVar.base +"/scheduleTemplate/insertList",
            dataType:"json",
            contentType:"application/json",
            type:"post",
            data:JSON.stringify(data),
            success:function(data){
                layer.msg(data.errorMsg);
                if(data.resultCode == "0"){
                    selectList();
                }

            },
            error:function(){
                initFlag = false;
                layer.msg("保存模板失败,网络连接异常");
            }
        });
    }

}


function setNull(data) {
    if(data==null){
        return "";
    }else{
        return data;
    }

}

/**
 * 清空设置值
 */
function clearInput(obj) {
    obj = $(obj).parent().parent().find("li");
    $(obj).eq(1).find("input").val("");
    $(obj).eq(2).find("input").val("");
    $(obj).eq(3).find("input").val("");
}
