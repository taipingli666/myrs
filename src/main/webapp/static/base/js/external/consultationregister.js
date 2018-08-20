var apipath="/consultationSingle/";
var orgCode = $("#startHosId").val();//默认的医院
var keshi = '';
var paibanrq = '';
var wubie = 'AM';
var start = 0;
var day = $("#time_day").val();//日期
var erweimaurl = $("#erweimaurl").val();//二维码地址
var imgs = '';
var timeIn;
var payIn;
$(function() {
    if($("#hid_liushui").val() != ''){
        //步入
        changeUI(1);
        initEWM();
    }
    //初始化日期
    initDate();
    //判断是否是完善问诊信息

    //初始化默认排班
    initKeShi();
    initPaiBan();
    timeIn = setInterval(ajaxGetImg, 5000);//加载资料的轮询
    $(".j_Tab").delegate("a","click",function(ev){
        ev.preventDefault();
        var me=$(this);
        if(me.hasClass("active")){
            return
        }else {
            me.addClass("active").parent().siblings().children().removeClass("active");
        }
    })
})
function planLine(){
    var n=$(".j_Progress").find(".pass").size(),
        lineW=$(".j_StraightLine").width(),
        n2=parseFloat((n-1)*0.5*lineW);
    $(".j_MaskLine").width(n2);
}
function changeUI(type){
    if(type == 1){
        //从选择医生界面跳到填写问诊信息的界面
        $("#arrangeBox").hide();
        $("#successBox").hide();
        $("#writeInfoBox").show();
        $(".j_Progress li:eq(1)").addClass("pass");//加进度条状态
         planLine()//调用进度条函数
    }else if(type == 2){
        //从填写问诊信息界面跳转到医生界面
        $("#writeInfoBox").hide();
        $("#successBox").hide();
        $("#arrangeBox").show();
        var num=$(".j_Progress").find(".pass").size();
        $(".j_Progress li").eq(1).removeClass("pass");
        planLine();//进度条
    }else if(type == 3){
        //从填写问诊界面跳转到预约结果界面
        $("#writeInfoBox").hide();
        $("#arrangeBox").hide();
        $("#successBox").show();
        //关闭查找图片的定时器
        window.clearInterval(timeIn);
    }

}
function initEWM(){
    var qrcode = new QRCode('qrcode', {
        text: erweimaurl + "?type=2&sysdateFileIdValue=" + $("#save_liushuihao").val(),
        width: 200,
        height: 200,
        colorDark: '#000000',
        colorLight: '#ffffff',
        correctLevel: QRCode.CorrectLevel.H
    });
}
function initDate(){
    var days = getAfterDays(day,7);
    var st = '';
    $(days).each(function(m,n){
        var sp = n.split("/");
        var ch = sp[1]+"月"+sp[2]+"日";
        if(m == 0){
            //设置默认全局变量
            paibanrq =  n;
            st += '<li><a href="javascript:void(0)" onclick="changeParame(3,\''+ n+'\')" class="active">'+ch+'</a></li>'
        }else {
            st += '<li><a href="javascript:void(0)" onclick="changeParame(3,\''+ n+'\')">'+ch+'</a></li>'
        }
    });
    $("#dateUl").html(st);
}
//生成明天之后的7天
function getAfterDays(da,count){
    var datestr;
    var date = new Array();
    var myDate = new Date(da);
    //date.push(myDate.getFullYear()+"/"+(Number(+myDate.getMonth()) + 1)+ "/" + myDate.getDate());
    myDate.setTime(myDate.getTime() + 1000*60*60*24);
    for (var i = 0; i < count; i++) {
        var s = "";
        datestr=myDate.getFullYear()+"/"+(Number(+myDate.getMonth()) + 1)+ "/" + myDate.getDate();
        date.push(datestr);
        myDate.setTime(myDate.getTime() + 1000*60*60*24);
    }
    return date;
}
//加载科室
function initKeShi(){
    var json = {
        "hosid":orgCode
    }
    $.ajax({
        url:globalVar.base+apipath+"getConsultationDeptByJiGouBH.do",
        dataType:"json",
        type:"post",
        data:json,
        success:function(data) {
        	if(data.success){
                //赋值
                var a = '';
                $("#keshiUl").html("");
                var st = '<li><a href="javascript:void(0);" class="active" onclick="changeParame(2,\''+a+'\')">全部</a></li>';
                $(data.data).each(function(m,n){
                    st += '<li><a href="javascript:void(0);" onclick="changeParame(2,\''+n.departmentid+'\')">'+n.departmentname+'</a></li>'
                });
                $("#keshiUl").html(st);
            }else{
                //提示加载错误
                $("#keshiUl").html('<li><a href="javascript:void(0);">未加载到数据</a></li>');
            }
        },
        error:function(){
        	alert("网络连接异常");
        }
    });
}
//改变条件,发起请求
function changeParame(type,val){
    //1为种类,2为值
    if(type == 1){
        //说明改变的是医院,还要加载科室
        orgCode = val;
        keshi = '';
        start = 0;
        initKeShi();
    }else if(type == 2){
        //说明改变的是科室
        keshi = val;
        start = 0;
    }else if(type == 3){
        //说明改变的是预约日期
        paibanrq = val;
        start = 0;
    }else if(type == 4){
        //说明改变的是午别
        wubie = val;
        start = 0;
    }else{
        alert("未知的操作");
        return;
    }
    //发送请求
    initPaiBan();
}
//加载排班
function initPaiBan(biaozhi){
    var json = {
        "hosCode":orgCode,
        "deptCode":keshi,
        "workPeriod":wubie,
        "start":start
    };
    if(paibanrq != null ){
        json.paibanrq = paibanrq;
    };
    $.ajax({
        url:globalVar.base+apipath+"getSchedulingByRegister",
        dataType:"json",
        type:"post",
        data:json,
        success:function(data){
            if(data.success == true){
                //判断,是否还有下一页
                var currentPage =  data.data.pageIndex;//当前页
                var countPage  = data.data.totalPage;//总页数
                if(currentPage == countPage){
                    //说明没有下一页,
                    //更多专家消失
                    $("#loading").html('<div class="morefamdoc"><span id="J_More">没有更多</span></div>');
                }else{
                    //有下一页
                    //更多专家出现
                    $("#loading").html('<div onclick="initPaiBan(1)" class="morefamdoc"><span id="J_More">更多专家</span></div>');
                    start = start + 10;
                }
                var st = '';
                $(data.data.content).each(function(m,n){
                	var registerQuantity=0;
                	var remainderQuantity=0;
                	if(n.registerQuantity!=null && n.registerQuantity!=''){
                		registerQuantity=Number(n.registerQuantity);
                	}
                	if(n.remainderQuantity!=null && n.remainderQuantity!=''){
                		remainderQuantity=Number(n.remainderQuantity);
                	}
                    var img =  globalVar.base+'/static//base/images/userImg.png';
                    st += '<li class="item"><div class="docimg" style="background-image: url('+img+')"></div><div class="docname">'+n.doctorName+'</div> <div class="deptname" title="'+n.deptName+'">'+n.deptName+'</div>'+
                        '<div class="docgoodin" title="'+n.introduce+'">简介:'+n.introduce+'</div> <div class="orderdocbut"><a onclick="writeInformation(\''+n.id+'\',\''+registerQuantity+'\',\''+(registerQuantity-remainderQuantity)+'\',\''+n.doctorCode+'\')" href="javascript:void(0);">预约<br class="xs-block">('+registerQuantity+'/'+(registerQuantity-remainderQuantity)+')<a/></div> </li>';
                });
                if(biaozhi == 1){
                    //追加
                    $("#doctorUl").append(st);
                }else{
                    $("#doctorUl").html(st);
                }
            }else{
                //如果不是追加.清空
                if(biaozhi != 1){
                    $("#doctorUl").html('');
                }
                //提示加载错误
                $("#loading").html('<div class="morefamdoc"><span id="J_More">'+data.message+'</span></div>');
            }
        },
        error:function(){
        	alert("网络连接异常");
        }
    });
}


//填写问诊信息相关
function writeInformation(pbid,zs,sys,hzysid){
    if(hzysid == $("#hidde_operId").val()){
        layer.msg("抱歉,您不能预约您自己的号源");
        return;
    }
    if(sys * 1>= zs * 1){
        //说明已经预约满了
        alert("该号源已满");
    }
    //获取该排版id下的信息,加上该排版下所有可以使用的号源
    var index = layer.load(1);
    $.ajax({
        url:globalVar.base+apipath+"getSchedulingSource",
        dataType:"json",
        type:"post",
        data:{"id":pbid,"singleId":$("#save_liushuihao").val()},
        success:function(data){
            layer.close(index);
            if(data.success == true){
                //获取成功,赋值隐藏域中的的流水号
                if(data.message == ''){
                    alert("加载错误");
                    return;
                };
                $("#save_liushuihao").val(data.message);
                $("#save_paibanId").val(pbid);
                $("#qrcode").html("");
                //生成二维码
//                initEWM();
                //给默认赋值
                $(data.data).each(function (n,m) {
                    Simulate();
                    $("#keshimc").val(m.deptName);
                    $("#yishengxm").val(m.doctorName);
                    var wd=(m.workDate).toString();
                    wd.substring(0,(wd.length-1));
                    var wde= new Date(parseInt(wd));
                    $("#paibanrq").val(wde.format("yyyy-MM-dd"));
                    if(m.workPeriod.toUpperCase() == 'AM'){
                        $("#shangxiawubz").val("上午");
                    }
                    if(m.workPeriod.toUpperCase() == 'PM'){
                        $("#shangxiawubz").val("下午");
                    }
                    $("#huizhenfei").val(m.registerCost);
                    //$("#list").parents(".j_SimulateBox").find(".j_SelectList").html("");
                    initHaoYuan(m.list)//初始化列表
                })
                planLine();//调用进度条函数
                $(window).scrollTop(0);//滚动条归零
                changeUI(1);
                // 赋值完显示新界面
            }else{
                //失败,弹出提示
                alert(data.message);
            }
        },
        error:function(){
            layer.close(index);
            layer.msg("网络连接异常");
        }
    });
}
//#region下拉赋值
function Simulate() {
    $("#J_List").off().on("change",function () {
        var me=$(this);
        $("#J_SelectVal").val(me.find("option:selected").attr("data-value"));
//        if($("#shangxiawubz").val() == ''){
            if(me.find("option:selected").attr("data-time") != ''){
                var a = me.find("option:selected").attr("data-time").substring(0,2)
                if(a == '07'||a == '08'||a == '09'||a == '10'||a == '11'||a == '12'){
                    $("#shangxiawubz").val('上午');
                }else if(a == '13'||a == '14'||a == '15'||a == '16'||a == '17'||a == '18'){
                    $("#shangxiawubz").val('下午');
                }
            }
//        }
        //改变序号,给input赋时间段
        $("#save_yuyuesj").val(me.find("option:selected").attr("data-time"));
        $("#save_hyid").val(me.find("option:selected").attr("data-mid"));
 /*       console.log(hyid)*/

    })
}
//初始化号源
function initHaoYuan(list) {
    //初始化的时候赋值
    var inputHaoYuan=$("#J_List"),
         inputVal=$("#J_SelectVal");
        //清空号源
        inputHaoYuan.html(" ");
        //清空隐藏域序号
        inputVal.val(" ");
    $(list).each(function (n,m) {
        //赋值列表第一个号源
        if(n==0){
            if(m.sequenceNumber==" "){
                inputHaoYuan.append('<option value="0">未加载到号源！</option>');
                inputVal.val(" ");
            }else{
                //textHaoYuan.html(m.xuhao+'号'+'--'+'时间段'+m.jiuzhensjd);//赋值列表第一个号源
                inputVal.val(m.sequenceNumber);//赋值列表第一个号源的序号
                changeXuHao(m.visitStart+'-'+m.visitEnd,m.id);
            }
        }
        inputHaoYuan.append('<option onchange="changeXuHao(\''+m.visitStart+'-'+m.visitEnd+'\',\''+m.id+'\')" data-time="'+m.visitStart+'-'+m.visitEnd+'" data-mid="'+m.id+'" data-value="'+m.sequenceNumber+'">'+m.sequenceNumber+'号'+'--'+'时间段'+m.visitStart+'-'+m.visitEnd+'</option>');
    })
    //重置当前input验证报错
    //$('#saveForm').data('bootstrapValidator')
    //    .updateStatus('yuyuexh', 'NOT_VALIDATED',null)
    //    .validateField('yuyuexh');
    //重置当前input验证报错
}

function changeXuHao(sjd,hyid){
    if($("#shangxiawubz").val() == ''){
        if(sjd != ''){
            var a = sjd.substring(0,2)
            if(a == '07'||a == '08'||a == '09'||a == '10'||a == '11'||a == '12'){
                $("#shangxiawubz").val('上午');
            }else if(a == '13'||a == '14'||a == '15'||a == '16'||a == '17'||a == '18'){
                $("#shangxiawubz").val('下午');
            }
        }
    }
    //改变序号,给input赋时间段
    $("#save_yuyuesj").val(sjd);
    $("#save_hyid").val(hyid);
}
/**
 * 时间对象的格式化;
 */
Date.prototype.format = function(format){ 
	var o = { 
	"M+" : this.getMonth()+1, //month 
	"d+" : this.getDate(), //day 
	"h+" : this.getHours(), //hour 
	"m+" : this.getMinutes(), //minute 
	"s+" : this.getSeconds(), //second 
	"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
	"S" : this.getMilliseconds() //millisecond 
	} 

	if(/(y+)/.test(format)) { 
	format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 

	for(var k in o) { 
	if(new RegExp("("+ k +")").test(format)) { 
	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
	} 
	} 
	return format; 
} 
//添加一个内容
function addVideoN(){
    layer.open({
        type: 2,
        title: "选择影片",
        shadeClose: true,
        shade: 0.8,
        area: ['80%','70%'],
        content:globalVar.base+apipath+"/yxzltable"
    });
}
function saveaddVideoN(yxbh,code){
    var a = code + ":" + yxbh;
    $("#nrList").append('<div class="proName"><span class="name_nrs" onclick="javascript:editNr(this)">'+a+'</span><span onclick="javascript:rmNr(this)" class="event icon-remove" ></span></div>');
}
function editNr(node){
    layer.prompt({
        formType: 2,
        value: $(node).text(),
        title: '编辑内容'
    }, function(value, index, elem){
        //如果保存
        $(node).text(value);
        layer.close(index);
    });
}
function rmNr(node){
    $(node).parent().remove();
}
function lookSingle(){
//    iframeTab(globalVar.base+apipath+'/lookConsultationSingle/1/'+$("#save_liushuihao").val(),'查看详情');
  //查看会诊单
	$(".Hui-article").html();
    $(".Hui-article").html('<iframe frameborder ="0" scrolling="no" ' +
        'style="width: 100%;height: 100%;" src="'+globalVar.base+'/consultationSingle/lookConsultationSingle/1/'+$("#save_liushuihao").val()+'"></iframe>');
}
function sctpUI(){
	$('#myModal').modal('show');
}
function centerModals() {   
　　$('#myModal').each(function(i) {   
　　　　	var $clone = $(this).clone().css('display','block').appendTo('.Hui-article-box');
		var left=Math.round(($clone.width() - $clone.find('.modal-content').width()) / 2)
　　　　	var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);
　　　　	top = top > 0 ? top : 0; 
　　　　	$clone.remove();   
　　　　	$(this).find('.modal-content').css("margin-top", top);   
　　});   
} 
$('#myModal').on('show.bs.modal', centerModals);
/*监听保存按钮*/
function submitF() {
    $('#saveForm').submit();
    //验证销毁重置
    $('#writeInfoBox').on('hidden.bs.modal', function() {
        $("#saveForm").data('bootstrapValidator').destroy();
        $('#saveForm').data('bootstrapValidator', null);
        $('#saveForm').bootstrapValidator();
    });
}
$('#saveForm').bootstrapValidator({
    message: '这个值是无效的',
    excluded : [':disabled'],//[':disabled', ':hidden', ':not(:visible)']排除禁用控件
    feedbackIcons: {/*input状态样式图片*/
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        yuyuexh: {
            message:'请选择预约号源',
            validators: {
                notEmpty: {
                    message: '预约号源不能为空'
                },
            }
        },
        xingming:{
            message:'请输入患者姓名',
            validators: {
                notEmpty: {
                    message: '患者姓名不能为空'
                },
            }
        },
        shoujihm:{
            message:'请输入手机号',
            validators: {
                stringLength: {
                    min: 11,
                    max: 11,
                    message: '请输入11位手机号码'
                },
                regexp: {
                    regexp: /^1[3|4|5|7|8][0-9]\d{8}$/,
                    message: '请输入正确的手机号码'
                },
                notEmpty: {
                    message: '手机号码不能为空'
                }
            }
        },
        bingrenid:{
        	validators: {
        		callback:{//自定义验证规则
        			message:'身份证号码不正确',
	                callback: function(value, validator) {
	                	var flag = false;
	                    if (validateIdCard(value)) {
	                        flag = true;
	                    }
	                    return flag;
	                }
        		},
                notEmpty: {
                    message: '身份证号码不能为空'
                }
        	}
        },
        bingqingms:{
            validators: {
                notEmpty: {
                    message: '病情描述不能为空'
                }
            }
        },
        binglixx:{
            validators: {
                notEmpty: {
                    message: '病史小结不能为空'
                },
            }
        }
    }
}).on('success.form.bv', function(e) {//点击提交之后
    e.preventDefault();
    save();
});
//从身份证获取出生年月日
function birthday(idCard) {
    var birthday = "";
    if(idCard != null && idCard != ""){
        if(idCard.length == 15){
            birthday = "19"+idCard.substr(6,6);
        } else if(idCard.length == 18){
            birthday = idCard.substr(6,8);
        }
        birthday = birthday.replace(/(.{4})(.{2})/,"$1-$2-");
    }
    return birthday;
}
//从身份证获取性别
function getSex(UUserCard) {
    var sex = "";
    if(UUserCard != null && UUserCard != ""){
        if (parseInt(UUserCard.substr(16, 1)) % 2 == 1) {
            sex="1"
        } else {
            sex="2"
        }
    }

    return sex;
}
function save(){
    $("#chushengrq").val(birthday($("#shenfenzh").val()));//返回年月日
    //如果身份证不为空以身份证为准
    //alert($("#shenfenzh").val());
    if($("#shenfenzh").val()!=''&&$("#shenfenzh").val()!='undefined'){
        $("#xingbie").val(getSex($("#shenfenzh").val()));//返回性别
    }else{
        //alert($("#sexSelect").val());
        $("#xingbie").val($("#sexSelect").val())
    }

    var yingxiangbh='';
    $(".name_nrs").each(function (n,m) {
        var me=$(this);
            yingxiangbh+=me.html()+",";
    })
    var da = $("#saveForm").serialize();
        da+='&yingxiangbh='+yingxiangbh;
        console.log(yingxiangbh)
    var index = layer.load(1);
    $.ajax({
        url:globalVar.base+apipath+"createConsultationSingle",
        dataType:"json",
        data:da,
        type:'POST',
        success:function(data){
            layer.close(index);
            $(".j_Progress li:eq(2)").addClass("pass");//加进度条状态
            planLine()//调用进度条函数
            $("#successBox .j_hintList").html(" ");
            //赋值
            $(data.data).each(function (n,m) {
                //</li><li class="item">会诊费用：<span>'+$("#huizhenfei").val()+'</span></li>
                var hintL='<li class="item">预约医生：<span>'+m.huizhenysmc+'</span></li><li class="item">预约科室：<span>'+m.huizhenksmc+'</span><li class="item">就诊序号：<span>'+m.yuyuexh+'</span></li><li class="item">预约时间：<span>'+$("#paibanrq").val()+'    '+m.yuyuesj+'</span></li>';
                $("#successBox .j_hintList").append(hintL);
            })
            if(data.success == true){
                //成功
                changeUI(3);
            }else{
                layer.msg(data.message);
            }

        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
        	alert(XMLHttpRequest.readyState);
            //即使错误,也要关闭加载的loading效果
            layer.close(index);
            layer.msg("网络连接异常");
        }
    });
}
/*
 * 身份证15位编码规则：dddddd yymmdd xx p
 * dddddd：6位地区编码
 * yymmdd: 出生年(两位年)月日，如：910215
 * xx: 顺序编码，系统产生，无法确定
 * p: 性别，奇数为男，偶数为女
 * 
 * 身份证18位编码规则：dddddd yyyymmdd xxx y
 * dddddd：6位地区编码
 * yyyymmdd: 出生年(四位年)月日，如：19910215
 * xxx：顺序编码，系统产生，无法确定，奇数为男，偶数为女
 * y: 校验码，该位数值可通过前17位计算获得
 * 
 * 前17位号码加权因子为 Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ]
 * 验证位 Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]
 * 如果验证码恰好是10，为了保证身份证是十八位，那么第十八位将用X来代替
 * 校验位计算公式：Y_P = mod( ∑(Ai×Wi),11 )
 * i为身份证号码1...17 位; Y_P为校验码Y所在校验码数组位置
 */
 
 function calendar(idCardWiSum,idCard,idCardWi){
	 var ii = 0;
	 while(17-ii >0) {
     	idCardWiSum += idCard.substring(ii, ii + 1) * idCardWi[ii];
     	ii++;
     }
	 return idCardWiSum;
 }
 
function validateIdCard(idCard) {
	if(idCard!=null && idCard.length!=0){
	    //15位和18位身份证号码的正则表达式
	    var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
	
	    //如果通过该验证，说明身份证格式正确，但准确性还需计算
	    if (regIdCard.test(idCard)) {
	        if (idCard.length == 18) {
	            var idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
				//将前17位加权因子保存在数组里
	            var idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2);
				//这是除以11后，可能产生的11位余数、验证码，也保存成数组
	            var idCardWiSum = 0;
	            //用来保存前17位各自乖以加权因子后的总和
	            idCardWiSum = calendar(idCardWiSum,idCard,idCardWi);
	
	            var idCardMod = idCardWiSum % 11; //计算出校验码所在数组的位置
	            var idCardLast = idCard.substring(17); //得到最后一位身份证号码
	            //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
	            if (idCardMod == 2) {
	                if (idCardLast == "X" || idCardLast == "x") {
	                    return true;
	                } else {
	                    return false;
	                }
	            } else {
	                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
	                if (idCardLast == idCardY[idCardMod]) {
	                    return true;
	                } else {
	                    return false;
	                }
	            }
	        }
	    } else {
	        return false;
	    }
	}else{
		return true;
	}
}
//获取上传的图片
function ajaxGetImg(){
    if($("#save_liushuihao").val() == ''){
        //如果没有会诊单id,直接return
        return;
    }
    var data = {
        "singleLiushuihao":$("#save_liushuihao").val(),
        "fileName":imgs
    }
    $.post(globalVar.base+apipath+"getConsultationImgById",data,function(data){
        if(data.success == true){
            //获取成功
            $(data.data).each(function(m,n){
                imgs += "'"+n.id+"',";
                //赋值图片????
                var imgPath=globalVar.base+apipath+'showConsultationImg?path='+n.filePath
                $("#imgs").append('<div class="item"><a target="_Blank" href="'+imgPath+'">'+n.fileName+'</a><input value="删除" type="button" onclick="javascript:delImg(this,\''+n.id+'\')" /></div>')
            });
        }
    },'json');
}
//删除会诊图片
function delImg(me,id){
    var index = layer.load(1);
    $.ajax({
        url:globalVar.base+apipath+"delImg",
        dataType:"json",
        data:{"id":id},
        type:'POST',
        success:function(data){
            layer.close(index);
            if(data.success == true){
                //成功
                alert("删除成功");
                $(me).parent().remove();
            }else{
                alert("删除失败");
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            //即使错误,也要关闭加载的loading效果
            layer.close(index);
            alert("网络故障");
        }
    });
}
Date.prototype.toString = function() {
  return this.getFullYear()
        + "-" + (this.getMonth()>8?(this.getMonth()+1):"0"+(this.getMonth()+1))
        + "-" + (this.getDate()>9?this.getDate():"0"+this.getDate())
        + " " + (this.getHours()>9?this.getHours():"0"+this.getHours())
        + ":" + (this.getMinutes()>9?this.getMinutes():"0"+this.getMinutes())
        + ":" + (this.getSeconds()>9?this.getSeconds():"0"+this.getSeconds());
}