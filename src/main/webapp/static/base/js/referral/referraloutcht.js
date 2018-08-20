        
var size = 10;
var page=1; 

$(function() {
	/**
	 * 表单验证
	 */
	var demo=$("#form-referral-info").Validform({
	    btnSubmit:"#_sub",
	    tiptype:4,
	    showAllError:true,
	    datatype:{//传入自定义datatype类型【方式二】;
	        "idCard":function(gets,obj,curform,datatype){
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
	        "name":/^[\u4E00-\u9FA5\uf900-\ufa2d]{2,6}$/,
	        "age":/^([1-9]|[0-9]{2}|1000)$/,
	        "tel":/^1[3|4|5|7|8][0-9]\d{4,8}$/,
	      /*  "illnessDescription":/^\S{30,}$/,*/
	        /*"healthid":/\d/,*/
			"orderDate": /\S/
	    },
	    ajaxPost:true,
	    beforeSubmit:function(curform){
            printOutch();
	    },
	});

	/**
	 * 设置表单验证的提示信息
	 */
	demo.addRule([
		{
			ele:"#card_id",
			datatype:"idCard",
			nullmsg:"请输入您的身份证号！",
			errormsg:"请输入正确的身份证号！"
		},
		{
			ele:"#pat_name",
			datatype:"name",
			nullmsg:"请输入您的姓名！",
			errormsg:"请输入2到6个中文字符！"
		},
		{
			ele:"#age",
			datatype:"age",
			nullmsg:"请输入您的年龄！",
			errormsg:"请输入正确的年龄！"
		},
		{
			ele:"#tel",
			datatype:"tel",
			nullmsg:"请输入您的手机号！",
			errormsg:"请输入正确的手机号！"
		},
		/*{
			ele:"#healthid",
			datatype:"healthid",
			nullmsg:"请输入您的就诊卡号！",
			errormsg:"请输入正确的就诊卡号！"
		},*/
        {
            ele:"#orderDate",
            datatype:"orderDate",
            nullmsg:"请选择预约住院日期！",
            errormsg:"请选择预约住院日期！"
        }
		/*{
			ele:"#illnessDescription",
			datatype:"illnessDescription",
			nullmsg:"请输入病情描述！",
			errormsg:"病情描述不得小于30个字！"
		},*/
		/*{
			ele:"#refdepart",
			datatype:"*",
			nullmsg:"请选择意向科室！"
		}*/
	]);
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
        content:globalVar.base+"/patientInfo/patients.do?" +$form.serialize()
    });
});

//双击带入病人信息
function addInfo(data){
    $("#card_id").val(data.cardId);
    $("#pat_name").val(data.name);
    if (data.gender == '男'){
        $("#sex").val(1);
    }else {
        $("#sex").val(2);
    }
    $("#age").val(data.age);
    $("#tel").val(data.tel);
    $("#healthid").val(data.mediumId);
}

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

$("select[name='checkCht_list_length']").width(50);
$("select[name='icd10modeltable_length']").width(50);





$("#purpose").change(function () {
    if ($("#purpose").val() == "1") {
        //说明是急诊病人,隐藏手机号和身份证号后面的*
        $("#_tel").css("display", "none");
        $("#_card_id").css("display", "none");
        $("#_textDescription2").css("display", "none");
        $("#_sex").css("display", "none");
        $("#_age2").css("display", "none");
        $("#_textDescription").attr("placeholder", "");

//            if (jizhen != null) {
//                dept(jizhen);
//            }
    } else {
        //不是急诊病人,要求展示*
        $("#_tel").css("display", "inline");
        $("#_card_id").css("display", "inline");
        $("#_textDescription2").css("display", "inline");
        $("#_sex").css("display", "inline");
        $("#_age2").css("display", "inline");
        $("#_textDescription").attr("placeholder", "病情描述不能为空或者不能少于30字");
//            if ($("#purpose").val() == "0") {
//                dept(menzhen);
//            } else if ($("#purpose").val() == "2") {
//                dept(zhuyuan);
//            }
    }

});
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

    //身份证失去焦点计算年龄
    $("#card_id").blur(function () {
        discriCard($(this).val().replace(/\s/g, ""));
    })

    //获得中文字符串长度
    GetLength = function (str) {
        var realLength = 0;
        for (var i = 0; i < str.length; i++) {
            charCode = str.charCodeAt(i);
            if (charCode >= 0 && charCode <= 128)
                realLength += 1;
            else
                realLength += 2;
        }
        return realLength;
    }

    //弹出打印保存
    function printOutch() {
    	var data = {};
    	data.patName = $("#pat_name").val();
    	data.sex = $("#sex").val();
    	data.age = $("#age").val();
    	data.tel = $("#tel").val();
    	data.cardId = $("#card_id").val();
    	data.orgNameTo = $("#tohos").find("option:selected").text();
    	data.orderDate = $("#orderDate").val();
        layer.open({
            type: 2,
            title: "",
            shadeClose: true,
            shade: 0.8,
            area: ['600px','90%'],
            content:globalVar.base+"/referral/savePrint.do?patName="+$("#pat_name").val()+
			"&sex="+$("#sex").val()+"&age="+$("#age").val()+"&tel="+$("#tel").val()+"&cardId="+$("#card_id").val()+
				"&orgNameTo="+$("#tohos").find("option:selected").text()+"&orderDate="+$("#orderDate").val()
        });
    }

  //提交转诊单
    function ss() {

    	var result = 1;
    	$("#orgNameTo").val($("#tohos").find("option:selected").text());
        $("#_sub").attr({"disabled": "disabled"});//关闭提交键
        $("#_sub").css({"background": "grey"});//置灰
        var referral = $("#form-referral-info").serialize();
    	/*$.get(globalVar.base+"/referral/addoutcht.do",referral,function(data){
    		if(data == 1) {
    			alert("转诊单提交成功，已经短信提醒接受人， 接受人确认后， 会短信提醒你。");
    			window.location.href = globalVar.base+"/referral/display.do";
    		}else{
				  alert("转诊单提交失败，请联系管理人员！");
	              $("#_sub").removeAttr("disabled");
    		}
    	});*/

        $.ajax({
            type: "POST",
            cache: false,
            data: referral,
            async: false,
            url: globalVar.base+"/referral/addoutcht.do",
            success: function(data) {
            	if (data == 1) {
                    alert("转诊单提交成功，已经短信提醒接受人， 接受人确认后， 会短信提醒你。");
                    window.location.href = globalVar.base+"/referral/display.do";
                }else {
            		result = 0;
                    alert("转诊单提交失败，请联系管理人员！");
				}
            }, error: function(e) {
                result = 0;
                alert(e + "接口调用错误！");
            }
        });
        return result;
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
    