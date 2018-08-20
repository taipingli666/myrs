$(function(){
	
	/**
	 * 表单验证
	 */
	var demo=$("#form-contract-info").Validform({
        btnSubmit:"#signCheckBtn",
        tiptype:1,
        showAllError:false,
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
            "tel":/^1[3|4|5|7|8][0-9]\d{4,8}$/,
            "insuranceId":/\d/,
            "sex":/^[0|1]$/,


        },
        ajaxPost:true,
        beforeSubmit:function(){
        	signCheck();
        }
    });
	
	/**
	 * 设置表单验证的提示信息
	 */
	demo.addRule([
		{
			ele:"#card",
			datatype:"idCard",
			nullmsg:"请输入您的身份证号！",
			errormsg:"请输入正确的身份证号！"
		},
		{
			ele:"#trueName",
			datatype:"name",
			nullmsg:"请输入您的姓名！",
			errormsg:"请输入2到6个中文字符！"
		},
		{
			ele:"#sex",
			datatype:"sex",
			nullmsg:"请选择您的性别！",
			errormsg:"请选择正确的性别！"
		},
		{
			ele:"#mobile",
			datatype:"tel",
			nullmsg:"请输入您的手机号！",
			errormsg:"请输入正确的手机号！"
		},
		{
			ele:"#personType",
			datatype:"*",
			nullmsg:"请选择居民类型"
		},
		{
			ele:"#insuranceType",
			datatype:"*",
			nullmsg:"请选择医保类型"
		},
		{
			ele:"#insuranceId",
			datatype:"insuranceId",
			nullmsg:"请输入您的医保编号！",
			errormsg:"请输入正确的医保编号！"
		},
		{
			ele:"#sClass",
			datatype:"*",
			nullmsg:"请选择套餐类型"
		}
	]);
	
	
	//签约
	/*$('#signCheckBtn').click(signCheck);*/
	//读卡
	$('#readCardBtn').click(readCard);
	//回车事件 input select
	$("input[jump='true'],select[jump='true']").bind("keydown", function (ev) {
		var event=ev||window.event;
		var keyCode=checkBrowser()=="IE"?event.keyCode:event.which;
		if (keyCode == 13) {
			jump(this);
        }
    });
    //锁定姓名
	$("#trueName").focus();
});

//跳转
function jump(obj){
	var nextObj = $("input[jump='true'],select[jump='true']")[$("input[jump='true'],select[jump='true']").index(obj) + 1];
    if (nextObj != undefined) {
    	nextObj.focus();
    	nextObj.click();
    }
}

//获取签约确认信息
function signCheck(){
	var url = globalVar.base+'/contract/contractRegistCheck.do';
	var title = "签约信息确认";
	$("input").each(function(){
		$(this).attr("value",$(this).val())
	});
	$("select").each(function(){
		$(this).find("option[value='"+$(this).val()+"']").attr("selected",true);
	});
	$("input:checkbox:checked").each(function(){
		$(this).attr('checked',true);
	});
	
	var index = layer.open({
		type: 2,
		title: title, 
		content: url
	});
	layer.full(index);
}

//读卡获取人员信息
function readCard(){
	layer.msg('读卡功能暂未开放!');
}

//保存验证
function check(){
	return true;
}

//人群分类变更
function isKeyChange(){
	var isKey = $("#isKey").val();
	if(isKey == "0"){
		$("#categoryLi").css("display","none");
		$("#categoryDiv input:checkbox").attr("checked",false);
		$("#crowdType100011").attr("checked",true);
	}else{
		$("#categoryLi").css("display","");
		$("#crowdType100011").attr("checked",false);
	}
}

//签约登记
function registContract(){
	if(check()){
		var data = $("#form-contract-info").serialize();
		$.post(globalVar.base+"/contract/contractSigned.do",data,function(reply){
			if(reply.success == true){
				//成功
				layer.msg('签约成功!',{icon:1,time:1000});
				setTimeout(function(){
					clear();
    			},1200)
			}else{
				layer.msg(reply.message,{icon:2,time:3000});
			}
		},"json").error(function(){
			layer.msg("网络错误");
		});
	}
}

//重新加载
function clear(){
	window.location=globalVar.base+"/contract/contractRegist";
}

//获取乡镇列表
function getTown(){
	getArea(1)
}

//获取村庄列表
function getVillage(){
	getArea(2)
}

//根据父节点获取地区信息
function getArea(type){
	var pSelect , cSelect;
	if(type==1){
		pSelect = $("#county");
		cSelect = $("#town");
		$("#village").html("<option></option>");
	}else{
		pSelect = $("#town");
		cSelect = $("#village");
	}
	var parentCode = $(pSelect).val();
	$(cSelect).html("<option></option>");
	if(parentCode!=""){
		$.post(globalVar.base+"/contract/getAreaByParent",{"parentCode":parentCode},function(data){
			if(data.success==true){
				var commonAreas = data.message;
				for(var i=0;i<commonAreas.length;i++){
					$(cSelect).append("<option value='"+commonAreas[i].code+"'>"+commonAreas[i].areaName+"</option>");
				}
			}else{
				layer.msg("删除失败");
			}
		},"json").error(function(){
			layer.msg("网络异常");
		});
	}	
}

function checkBrowser(){
	 var cb = "Unknown";
	    if(window.ActiveXObject){
	        cb = "IE";
	    }else if(navigator.userAgent.toLowerCase().indexOf("firefox") != -1){
	        cb = "FireFox";
	    }else if((typeof document.implementation != "undefined") && (typeof document.implementation.createDocument != "undefined") && (typeof HTMLDocument != "undefined")){
	        cb = "Mozilla";
	    }else if(navigator.userAgent.toLowerCase().indexOf("opera") != -1){
	        cb = "Opera";
	    }
	    return cb;
}