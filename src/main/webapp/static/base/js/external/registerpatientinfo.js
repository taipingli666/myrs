$(function(){
    //$(".registerform").Validform();  //就这一行代码！;

    var demo=$(".registerform").Validform({
        btnSubmit:"#next",
        tiptype:4,
        showAllError:true,
        datatype:{//传入自定义datatype类型【方式二】;
            "idcard":function(gets,obj,curform,datatype){
                //该方法由佚名网友提供;

                var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子;
                var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值，10代表X;

                if (gets.length == 15) {
                    return isValidityBrithBy15IdCard(gets);
                }else if (gets.length == 18){
                    var a_idCard = gets.split("");// 得到身份证数组
                    if (isValidityBrithBy18IdCard(gets)&&isTrueValidateCodeBy18IdCard(a_idCard)) {
                        return true;
                    }
                    return false;
                }
                return false;

                function isTrueValidateCodeBy18IdCard(a_idCard) {
                    var sum = 0; // 声明加权求和变量
                    if (a_idCard[17].toLowerCase() == 'x') {
                        a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作
                    }
                    for ( var i = 0; i < 17; i++) {
                        sum += Wi[i] * a_idCard[i];// 加权求和
                    }
                    valCodePosition = sum % 11;// 得到验证码所位置
                    if (a_idCard[17] == ValideCode[valCodePosition]) {
                        return true;
                    }
                    return false;
                }

                function isValidityBrithBy18IdCard(idCard18){
                    var year = idCard18.substring(6,10);
                    var month = idCard18.substring(10,12);
                    var day = idCard18.substring(12,14);
                    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
                    // 这里用getFullYear()获取年份，避免千年虫问题
                    if(temp_date.getFullYear()!=parseFloat(year) || temp_date.getMonth()!=parseFloat(month)-1 || temp_date.getDate()!=parseFloat(day)){
                        return false;
                    }
                    return true;
                }

                function isValidityBrithBy15IdCard(idCard15){
                    var year =  idCard15.substring(6,8);
                    var month = idCard15.substring(8,10);
                    var day = idCard15.substring(10,12);
                    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
                    // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
                    if(temp_date.getYear()!=parseFloat(year) || temp_date.getMonth()!=parseFloat(month)-1 || temp_date.getDate()!=parseFloat(day)){
                        return false;
                    }
                    return true;
                }

            },
            "zh1-6":/^[\u4E00-\u9FA5\uf900-\ufa2d]{1,6}$/,
            "age":/^([1-9]|[0-9]{2}|1000)$/,
            'phone':/^1[3|4|5|7|8][0-9]\d{4,8}$/,
            "meCard":/\d/



        },
        ajaxPost:true,
        beforeSubmit:function(){
            //在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
            //这里明确return false的话表单将不会提交;
            updatePatientInfo();
        },
        callback:function(data){
        	return false;
        }
    });
    demo.tipmsg.w["zh1-6"]="请输入1到6个中文字符！";
    demo.tipmsg.w['age']="请输入正确的年龄";
    demo.tipmsg.w['phone']='请输入正确的手机号';
    $('#next').click(function(){
        console.log(demo);
    });

});

//病人信息列表
$(".button").on("click",function(){
    var $form = $("form .content");
    layer.open({
        type: 2,
        title: "患者列表",
        shadeClose: true,
        shade: 0.8,
        area: ['80%','70%'],
        content:base+"/patientInfo/patients.do?" +$form.serialize()
    });
});

//根据身份证号算出性别和年龄并且赋值
function discriCard(UUserCard) {
    var _options = $("#patientGender")[0].options;
    //获取出生日期
    UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14);
    //获取性别
    if (parseInt(UUserCard.substr(16, 1)) % 2 == 1) {
        //是男则执行代码 ...
        $(_options).each(function (m, n) {
            if (n.value == "1") {
                $(n).attr("selected", "selected");
            }
        });
    } else {
        //是女则执行代码 ...
        $(_options).each(function (m, n) {
            if (n.value == "2") {
                $(n).attr("selected", "selected");
            }
        });
    }
    //获取年龄
    var myDate = new Date();
    var month = myDate.getMonth() + 1;
    var day = myDate.getDate();
    var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
    if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
        age++;
    }
    $("#patientAge").val(age);
    //年龄 age
}

//身份证失去焦点计算年龄
$("#patientIdCard").blur(function () {
    discriCard($(this).val().replace(/\s/g, ""));
})

//双击带入病人信息
function addInfo(data){
    $("#patientIdCard").val(data.cardId);
    $("#patientName").val(data.name);
    if (data.gender == '男'){
        $("#patientGender").val(1);
    }else {
        $("#patientGender").val(2);
    }
    $("#patientAge").val(data.age);
    $("#patientPhone").val(data.tel);
    $("#patientMediumCode").val(data.mediumId);
}

//icd10事件

//赋予ICD10 赋值方法
$('#icd10modeltable tbody').on('dblclick', 'tr', function () {
    var data = $(this);
    // $("#icd10").val(data.children("td:eq(0)").text());
    $("#diag").val(data.children("td:eq(1)").text());
    //赋值完毕后， 关闭model层
    $('#icd10model').modal('hide');
});
//icd10 展现
showicd10model = function() {
    $("#icd10model").modal('show');
    searchicd10();

}
//icd10分页
var size = 10;
var page=1;
$("#lastpage").click(function () {
    if (page > 1) {
        page = page - 1;
        searchicd10();
    }
});
$("#nextpage").click(function () {
    var pageIndex = $("#all_page").html();
    if(page < pageIndex) {
        page = parseInt(page) + 1;
        searchicd10();
    }
});

$("#jumppage").click(function() {
    pageIndex = $("#all_page").html();
    jumpto = $("#pageinput").val();
    if(parseInt(jumpto) <= pageIndex) {
        page = jumpto;
        searchicd10();
    }else {
        alert("超出范围！");
    }
});


//搜索icd10
function searchicd10() {
    var icdvalue = $('#searchIcdcode').val();
    $("#icd10tbody").empty();
    $.get(base+"/referral/icd10list.do", {"searchValue":icdvalue, "pageNum":page, "pageSize":size}, function (response) {
        var total = response.total;
        $("#all_page").html(Math.round((total+size-1)/size));
        $("#pageshow").val(page);
        $.each(response.icd10List,function(m,k){
            $("#icd10tbody").append("<tr><td>"+k.zhenDuanicd+"</td>" +
                "<td>"+k.zhenDuanName+"</td>" +
                "<td>"+k.zhenDuanPinYin+"</td></tr>");
        })
    });
}

//回车
function entersearchicd10() {
    page = 1;
    searchicd10();
}

/**
 * 更新患者信息
 */
function updatePatientInfo() {
    var hosNum = $("[name='hosCode'] option:selected").attr("id");
    $.ajax({
        url: base + "/patientInfo/updateByPatientIdCard",
        type: "post",
        data: $("form").serialize(),
        dataType: "json",
        success: function (data) {
            if(data.success == true){
                window.location.href = base + "/reserve/registerInfo.do?" + $("form").serialize() +"&hosNum="+hosNum;
                //console.log(curform);
            }else{
                alert("患者信息保存失败！");
            }
        },
        error: function (e) {
            alert("接口调用错误！");
        }
    });
}