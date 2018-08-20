var size = 10;
var page=1; 
    
$(function () {
  /*  $('#sel_search_orderstatus').multipleSelect({
    	  placeholder: "请选择"
    });
    */
  //给身份证input绑定失去焦点事件
    $("#card_id").blur(function () {
    	discriCard($("#card_id").val());
    });
    
  //赋予ICD10 赋值方法
    $('#icd10modeltable tbody').on('dblclick', 'tr', function () {
        var data = $(this);
        $("#icd10").val(data.children("td:eq(0)").text());
        $("#diag").val(data.children("td:eq(1)").text());
        //赋值完毕后， 关闭model层
        $('#icd10model').modal('hide');
    });
   
});

//icd10 展现
showicd10model = function() {
  $("#icd10model").modal('show');
  searchicd10();
}
//验证代码
function bootstrapValidator() {
    var validvalue = true;
    //检测患者姓名
    if ($("#pat_name").val() == "") {
        $("#pat_name_validvalue").html("患者姓名不能为空！");
        $("#pat_name").focus();
        validvalue = false;
        return false;
    } else {
        $("#pat_name_validvalue").html("");
    }
    
    //身份证
    var cardReg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
    if ($("#card_id").val() == "" || !cardReg.test($("#card_id").val())) {
        $("#card_id_validvalue").html("患者身份证不能为空！或填写错误。");
        $("#card_id").focus();
        validvalue = false;
        return false;
    } else {
        $("#card_id_validvalue").html("");
    }
    //判断性别
    if ($("#sex").val() == "" || $("#sex").val() == null) {
        $("#sex_validvalue").html("请选择性别。");
        $("#sex").focus();
        validvalue = false;
        return false;
    } else {
        $("#sex_validvalue").html("");
    }
    
    //判断年龄
    if ($("#age").val() == "" || $("#age").val() == null) {
        $("#_age").html("请输入年龄。");
        $("#age").focus();
        validvalue = false;
        return false;
    } else {
        $("#age").html("");
    }
    //判断年龄是否是数字
    var _age = $("#age").val();
    if (age != null && age != "") {
        if (!isNaN(_age)) {
            //说明是输入的数字
            $("#_age").html("");
        } else {
            $("#_age").html("年龄必须是数字");
            $("#age").focus();
            return false;
        }
    }
    var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    if ($("#tel").val() == "" || !(myreg.test($("#tel").val()))) {
        $("#tel_validvalue").html("患者手机不能为空,或填写错误。");
        $("#tel").focus();
        validvalue = false;
        return false;
    } else {
        $("#tel_validvalue").html("");
    }
    //初步诊断
    if ($("#diag").val() == "" || $("#diag").val() == null) {
        $("#diag_validvalue").html("请输入或选择初步诊断结果。");
        $("#diag").focus();
        validvalue = false;
        return false;
    } else {
        $("#diag_validvalue").html("");
    }
    return validvalue;
}//验证结束 		

  	//根据身份证号算出性别和年龄并且赋值
    function discriCard(UUserCard) {
        var _options = $("#sex")[0].options;
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
        $("#age").val(age);
        //年龄 age
    }
    
    
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
    function entersearchicd10() {
    	page = 1;
    	searchicd10();
    }
    //搜索icd10
    function searchicd10() {
    	var icdvalue = $('#searchIcdcode').val();
    	$("#icd10tbody").empty();
        $.get(globalVar.base+"/referral/icd10list.do", {"searchValue":icdvalue, "pageNum":page, "pageSize":size}, function (response) {
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
    
//提交预约单
function ss() {
   /* if (!bootstrapValidator()) {
        return false;
    }*/
    $("#orgNameTo").val($("#tohos").find("option:selected").text());
    //$("#_sub").attr({"disabled": "disabled"});//关闭提交键
    var referral = $("#form-referral-info").serialize();
   /* $.get(globalVar.base+"/reserve/addinfo.do",referral,function(data) {
    	//window.location.href = globalVar.base+"/referral/assaychoose.do";
    })
    //跳转controller 
    var a = {"hosCode":10,"accessToken":"sss"};
    $.ajax({
    	type:"post",
    	url:globalVar.base+"/reserve/getDeptList",
    	data:a,
    	dataType:"json",
    	success:function(data){
    		console.info(data);
    	},
    	error:function(){
    		alert("失败");
    	}
    	
    });*/
    window.location.href = globalVar.base+"/reserve/addinfo.do?assayInfo="+referral;
}//-------提交方法结束
