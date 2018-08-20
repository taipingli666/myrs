var webrtc = null;
var ImgUrl="/consultationSingle/getsrcimg?path=";//头像
var ImgUrl2 = "";
var sqname = '',
    hzname = '',
    lsh="";
$(function(){
	getList(1);//页面加载时初始化 待诊列表
    //开启在线
    //mysocket.socketOpen($("#hidden_operIp").val());
    /* if($("#hidden_role").val() == '1'){
     ysmc = $("#hidden_huizhenysmc").val();
     }else{
     ysmc = $("#hidden_shenqingysmc").val();
     $("#show_bg").css("display","none");
     }*/

    $('#myTab a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })
    //开启rtc
    //monitor.getUserMedia();//获取本地视频
});

//获取预约和待诊列表
function getList(typejosn){
    var pd01=0;
    var pd02=0;
    $(".videoMainBox-hint").html('当前共有<span class="text-blue" >0</span>条排队记录');
    $.ajax({
        dataType:'json',
        data:{"type":typejosn},
        url:globalVar.base+'/consultationSingle/gettodayconsultationlist',
        type:'post',
        success:function(data){
            if(typejosn==1){
                $("#J_Tab01").html(" ");
            }else if(typejosn==2){
                $("#J_Tab02").html(" ")
            }
            var zt0='',zt3 = '',zt4 = '',zt5='';
            var zt6='',zt7 = '',zt8 = '',zt9='';
            var elstate="", elonLine="";
            $(data).each(function (n,m) {
                if(typejosn==1){
                    var video = '';
                    //(m.onLine == 1) ? elonLine='<p style="color:#18a689;">在线</p>' : elonLine='<p>不在线</p>';
                    if(m.shenqingzt == '3'){
                        pd01+=1;
                        //video = '<a href="javascript:void(0);" onclick="javascript:startVideo(\''+m.liushuihao+'\',\''+m.shenqingysid+'\',1,this)" class="btn2">发起视频</a>';
                        var ltype=1;
                        if($("#hidden_operIp").val()!=m.shenqingysid && $("#hidden_operIp").val()!=m.huizhenysid){
                        	ltype=99;
                        }else{
                        	ltype=1;
                        }
                        video = '<a href="javascript:void(0);" onclick="openVideo(\''+m.liushuihao+'\',\''+m.shenqingysid+'\',\''+m.huizhenysid+'\',\''+ltype+'\')" class="btn2">进入会诊间</a>';
                        if(m.huizhenzt == '0'){
                            elstate='<p class="state text-blue">等待中</p>';
                            zt3+=initListString(m,elstate,elonLine,video);
                        }else if(m.huizhenzt == '1'){
                            elstate='<p class="state text-green">会诊中</p>';
                            zt0+=initListString(m,elstate,elonLine,video);
                        }
                    }else if(m.shenqingzt == '4'){
                        elstate ='<p class="state">已完成</p>';
                        zt4+=initListString(m,elstate,elonLine,video);
                    }else if(m.shenqingzt == '5'){
                        elstate = '<p class="state">异常结束</p>';
                        zt5+=initListString(m,elstate,elonLine,video);
                    }
                }else if(typejosn==2){
                    var elstate2="";
                    var elevent="";
                    //(m.onLine == 1) ? elonLine='<p style="color:#18a689;">在线</p>' : elonLine='<p>不在线</p>';
                    if(m.shenqingzt == '3'){
                        //(m.huizhenzt == 0) ? elevent='<a href="javascript:void(0);"  onclick="javascript:startVideo(\''+m.liushuihao+'\',\''+m.huizhenysid+'\',2,this)" class="btn2" style="display: none">重新连接</a>' : elevent='<a href="javascript:void(0);"  onclick="javascript:startVideo(\''+m.liushuihao+'\',\''+m.huizhenysid+'\',2,this)" class="btn2">重新连接</a>';
						elevent = '<a href="javascript:void(0);" onclick="openVideo(\''+m.liushuihao+'\',\''+m.shenqingysid+'\',\''+m.huizhenysid+'\',\'1\')" class="btn2">进入会诊间</a>';
                        //(m.huizhenzt == 0) ? elevent='<a href="javascript:void(0);" onclick="javascript:openVideo(\''+m.liushuihao+'\',\''+m.huizhenysid+'\',\''+m.shenqingysid+'\',2,this)"  class="btn2" style="display: none">重新连接</a>' : elevent='<a onclick="javascript:openVideo(\''+m.liushuihao+'\',\''+m.huizhenysid+'\',\''+m.shenqingysid+'\',2,this)"  href="javascript:void(0);"  class="btn2">重新连接</a>';
                        if(m.huizhenzt == '0'){
                            pd02+=1;
                            elstate2='<p class="state text-blue">等待中</p>';
                            zt7+=initListString2(m,elstate2,elevent,elonLine);
                        }else if(m.huizhenzt == '1'){
                            elstate2='<p class="state text-green">会诊中</p>';
                            zt6+=initListString2(m,elstate2,elevent,elonLine);
                        }
                    }else if(m.shenqingzt == '4'){
                        elstate2 ='<p class="state">已完成</p>';
                        zt8+=initListString2(m,elstate2,elevent,elonLine);
                    }else if(m.shenqingzt == '5'){
                        elstate2 = '<p class="state">异常结束</p>';
                        zt9+=initListString2(m,elstate2,elevent,elonLine);
                    }
                    /*    (m.huizhenzt == 0) ? elstate2='<p class="state text-blue">等待中</p>' : elstate2='<p class="state text-green">会诊中</p>';*/
                    /*elList2+='<li><div class="waitItem"><img src=\"'+ImgUrl+''+m.shenqingystx+'\"/><div class="heard clearfix"><p class="name">'+m.huizhenysmc+'</p><p class="time">时间：'+m.yuyuesj+'</p>'+elstate2+'</div>';
                     elList2+='<div class="main clearfix"><p class="other-name">病人:'+m.xingming+'</p><div class="event clearfix"><a href="javascript:void(0);"  onclick="javascript:iframeTab(\'/api/consultationSingle/lookConsultationSingle/1/'+m.liushuihao+'\',\'查看详情\')" class="btn2">查看详情</a>'+elstate3+'</div></div>';
                     elList2+='</div></li>';*/

                }
            });
            if(typejosn == 1){
                $("#J_Tab01").html(zt0+zt3+zt4+zt5);
                $(".videoMainBox-hint").html('当前共有<span class="text-blue" >'+pd01+'</span>条排队记录').show();
                $("#J_Await2").hide();
            }else if(typejosn==2){
                $("#J_Tab02").append(zt6+zt7+zt8+zt9);
                $("#J_Await").hide();
                $(".videoMainBox-hint").html('当前共有<span class="text-blue" >'+pd02+'</span>条排队记录').show();
            }

        },
        error:function(){

        }
    });
}
function initListString(m,elstate,elonLine,video){
    var elList = "";
    (m.shenqingystx ==0) ? ImgUrl2=globalVar.base+'/static/base/images/userImg.png' : ImgUrl2=ImgUrl+m.shenqingystx+"&type=1";
//    elList+='<li style="cursor:pointer" onclick="javascript:showSingle(\''+m.liushuihao+'\',2,this)"><div class="waitItem"><img src=\"'+ImgUrl2+'\"/><div class="heard clearfix"><p class="name">'+m.shenqingysmc+'</p><p class="time">'+m.yuyuexh+'号('+m.yuyuesj+')</p>'+elstate+'</div>';
//    elList+='<div class="main clearfix"><p class="other-name other-name2">病人姓名:'+m.xingming+'</p><div class="event event2 clearfix"><a href="javascript:void(0);" onclick="initHzbg(\''+m.liushuihao+'\',\''+m.shenqingysid+'\',1)" class="btn2 reportBtn">会诊报告</a>'+video+'</div></div>';
///*
//    elList+='<div class="main clearfix"><p class="other-name other-name2">病人:'+m.xingming+'</p><div class="event event2 clearfix"><a href="javascript:void(0);" onclick="javascript:addReport()" class="btn2 reportBtn">会诊报告</a><a href="javascript:void(0);" onclick="javascript:iframeTab(\'/api/consultationSingle/lookConsultationSingle/2/'+m.liushuihao+'\',\'查看详情\')" class="btn2">查看详情</a>'+video+'</div></div>';
//*/
//    elList+=''+elonLine+'</div></li>';
    elList+='<li style="cursor:pointer" onclick="javascript:showSingle(\''+m.liushuihao+'\',2,this)"><div class="waitItem"><div class="head"><img src="'+ImgUrl2+'" alt="">'+elonLine+'</div><div class="head-detail clearfix"><p class="name">'+m.shenqingysmc+'</p><p class="time">'+m.yuyuexh+'号('+m.yuyuesj+')</p>'+elstate+'</div><div class="main clearfix"><span class="other-name">病人姓名:'+m.xingming+'</span><a href="javascript:void(0);" onclick="initHzbg(\''+m.liushuihao+'\',\''+m.shenqingysid+'\',1)" class="btn2 reportBtn">会诊报告</a>'+video+'</div><p style="font-size: 12px" class="other-name">医生医院:'+m.shenqingyymc+'&nbsp;&nbsp;&nbsp;&nbsp;电话:'+m.shenqingysdh+'</p></div></li>'
    return elList;
}
function initListString2(m,elstate2,elevent,elonLine){
    var elList2 = '';
    (m.shenqingystx ==0) ? ImgUrl2=globalVar.base+'/static/base/images/userImg.png' : ImgUrl2=ImgUrl+m.huizhenystx+"&type=1";
//    elList2+='<li style="cursor:pointer"  onclick="javascript:showSingle(\''+m.liushuihao+'\',1,this)"><div class="waitItem"><img src=\"'+ImgUrl2+'\"/><div class="heard clearfix"><p class="name">'+m.huizhenysmc+'</p><p class="time">时间：'+m.yuyuesj+'</p>'+elstate2+'</div>';
//    if(m.huizhenzt=='0'){
//        elList2+='<div  class="main clearfix"><p class="other-name">病人:'+m.xingming+'</p><div class="event clearfix"><a href="javascript:void(0);" onclick="getQueue(\''+m.huizhenysid+'\',\''+m.yuyuexh+'\')"  class="btn2">查看队列</a>'+elevent+'</div></div>';
///*
//        elList2+='<div  class="main clearfix"><p class="other-name">病人:'+m.xingming+'</p><div class="event clearfix"><a href="javascript:void(0);" onclick="getQueue(\''+m.huizhenysid+'\',\''+m.yuyuexh+'\')"  class="btn2">查看队列</a><a href="javascript:void(0);"  onclick="javascript:iframeTab(\'/api/consultationSingle/lookConsultationSingle/1/'+m.liushuihao+'\',\'查看详情\')" class="btn2">查看详情</a>'+elevent+'</div></div>';
//*/
//    }else {
//        elList2+='<div class="main clearfix"><p class="other-name">病人:'+m.xingming+'</p><div class="event clearfix">'+elevent+'</div></div>';
//    }
//    elList2+=elonLine+'</div></li>';
//    elList2+='<li style="cursor:pointer" onclick="javascript:showSingle(\''+m.liushuihao+'\',1,this)"><div class="waitItem"><div class="head"><img src="'+ImgUrl2+'" alt=""><p>在线</p></div><div class="head-detail clearfix"><p class="name">'+m.shenqingysmc+'</p><p class="time">'+m.yuyuexh+'号('+m.yuyuesj+')</p><p class="state text-blue">'+elstate2+'</p></div><div class="main clearfix"><span class="other-name">病人姓名:'+m.xingming+'</span><a href="##"   class="btn2 reportBtn">会诊报告</a>'+elonLine+'</div><p style="font-size: 12px" class="other-name">医生医院:'+m.shenqingyymc+'&nbsp;&nbsp;&nbsp;&nbsp;电话:'+m.shenqingysdh+'</p></div></li>'
    elList2+='<li style="cursor:pointer" onclick="javascript:showSingle(\''+m.liushuihao+'\',1,this)"><div class="waitItem"><div class="head"><img src=\"'+ImgUrl2+''+m.shenqingystx+'\" alt="">'+elonLine+'</div><div class="head-detail clearfix"><p class="name">'+m.huizhenysmc+'</p><p class="time">时间：'+m.yuyuesj+'</p>'+elstate2+'</div><div class="main clearfix"><span class="other-name">病人姓名:'+m.xingming+'</span><a href="javascript:void(0);"   class="btn2">查看详情</a>'+elevent+'</div></div></li>'
    return elList2;
}
//视频开始按钮
function btnsShow(hzzt) {
    $("#J_Btns").show();//按钮显示
    if(hzzt==0){
        $("#J_Start").show();
    }else if(hzzt==1){
        $("#J_Start").hide();
        $("#J_End").show();
    }
}
function showSingle(singleId,type,me){
    if(me != null){
        $('.selectDiv').removeClass('selectDiv');
        $(me).addClass('selectDiv');
    }
    $("#J_UserInfo").parent().hide();
    //查看会诊单
    $('#iFrame_div').html('');
    $("#iFrame_div").html('<iframe frameborder ="0" scrolling="no" ' +
        'style="width: 100%;height: 100%;" src="'+globalVar.base+'/consultationSingle/lookConsultationSingle/'+type+'/'+singleId+'"></iframe>');
    changeDiv(1);
     
}
function changeDiv(type){
    if(type == '1'){
        $("#zl_div").hide();
        $("#iFrame_div").show();
    }else if(type == '2'){
        $("#iFrame_div").html('');
        $("#zl_div").show();
        $("#iFrame_div").hide();
        $("#J_UserInfo").parent().show();
        $("#report-null").hide();
    }else if(type == '3'){
        //视频没有连接成功的时候
        //$("#J_UserInfo").html("");
        $("#zl_div").show();
        $("#iFrame_div").html('');
        $("#report-null").show();
        $("#iFrame_div").hide();
        $("#J_UserInfo").parent().hide();
    }
}
//窗口聊天消息超出时  滚动
function clearVar() {
    sqname = '';
    hzname = '';
    lsh="";
    $("#chatRoom-list").html("");
    $("#J_Btns,#J_Start,#J_End").hide();//按钮还原
}
//打开视频
function openVideo(singleId,operId,otheroperId,type,me){
    var a = window.event||arguments.callee.caller.arguments[0];//.stopPropagation();
    a.stopPropagation();
    //检测是否已开启
    //var state = getCookie("state");
    //if(state == '1'){
    //    layer.msg("请先关闭当前的视频窗口");
    //    return;
    //}
    var index = layer.load(1);
    var data= {"singleId":singleId,"operId":operId,"type":type};
    $.post(globalVar.base+'/consultationSingle/inviteconsultation',data,function(data){
        layer.close(index);
        if(data.success == true){
                document.cookie="state="+"1";
                $("#J_UserInfo").html("");
                clearVar();//还原按钮
                lsh=singleId;
                if(type==1){
                    hzname=data.data.huizhenysmc;
                    sqname=data.data.shenqingysmc;
                    $("#J_UserInfo").html(" ");
                    var sex="";
                    (data.data.xingbie==1) ? sex="男":sex="女";
//                    var age=ages(data.data.chushengrq);
                    var age = '';
                    var tel = '';
                    var shenfenzh = '';
                    var chushengrq = '';
                    if (data.data.age != undefined) {
                        age = data.data.age;
                    }
                    if (data.data.shoujihm != undefined) {
                        tel = data.data.shoujihm;
                    }
                    if (data.data.shenfenzh != undefined) {
                        shenfenzh = data.data.shenfenzh;
                    }
                    if (data.data.chushengrq != undefined) {
                        chushengrq = data.data.chushengrq;
                    }
                    var st="";
                    st+='<div class="panel-heading"><h3 class="panel-title">会诊患者</h3></div>';
                    st+='<div class="panel-body"><div class="patientInfo-3 clearfix"><p>姓名：<span >'+data.data.xingming+'</span></p></div>';
                    st+='<div class="patientInfo-3 clearfix"><p>性别：'+sex+'</p><p>年龄：'+age+'</p><p>出生日期：'+chushengrq+'</p>';
                    st+='</div><div class="patientInfo-3 clearfix"><p>身份证号：'+shenfenzh+'<span></span></p><p>手机号：'+tel+'<span></span></p></div></div>';
                    $("#J_UserInfo").append(st);
                    $("#update_hzyj").html(data.data.zhenduanbg);
                    $("#bingqingms").html(data.data.bingqingms);
                    $("#binglixx").html(data.data.binglixx);
                    $("#iFrame_div").css("display","none");
                    $("#J_UserInfo").parent().show();
                    $("#report-null").hide();
//                    (data.data.shenqingystx ==0) ? ImgUrl2='/dist/login/common/img/userImg.png' : ImgUrl2=ImgUrl+data.data.shenqingystx+"&type=1";
                    /*$("#J_VideoDoc").html('<li class="item"><p class="title">申请医生:'+data.data.shenqingysmc+'</p><p class="name">申请医院:'+data.data.shenqingyymc+'</p><img  src=\"'+ImgUrl2+'\"/></li>')*/
                }else if(type==2){
                    showSingle(data.data.liushuihao,"1",$(me).parent().parent().parent().parent());
                }
            //成功,才可以跳入
            //window.open("consultationroomtopb?singleId="+singleId+"&opId="+operId+"&type="+type);
            window.open(globalVar.base+"/consultationSingle/consultationroomtopb?singleId="+singleId+"&opId="+operId+"&type="+type,'_blank','width=800,height=600,menubar=no,toolbar=no,status=no,scrollbars=yes')
//			window.location.href=globalVar.base+"/consultationSingle/consultationroomtopb?singleId="+singleId+"&opId="+operId+"&type="+type;
        }else{
            changeDiv(3);
            layer.msg(data.message);
        }
    },"json").error(function(){
        changeDiv(3);
        layer.close(index);
        layer.msg("服务器错误");
    })
}

function openwin(url) {
	var a = document.createElement("a");
	a.setAttribute("href", url);
	a.setAttribute("target", "_blank");
	a.setAttribute("id", "camnpr");
	document.body.appendChild(a);
	a.click();
}

function initHzbg(singleId,operId,type,ab){
    //var a=arguments.callee.caller.arguments[0];
    if(ab == null){
        var a = window.event||arguments.callee.caller.arguments[0];//.stopPropagation();
        a.stopPropagation();
    }
//    if(lsh==singleId){
//        changeDiv(2);
//        return;
//    }
//    var index = layer.load(1);
    var data= {"singleId":singleId,"operId":operId,"type":type,"ksbz":"1"};
    $.post(globalVar.base+'/consultationSingle/getbg',data,function(data){
//        layer.close(index);
        if(data.success == true){
            $("#J_UserInfo").html("");
            lsh=singleId;
            if(type==1){
                hzname=data.data.huizhenysmc;
                sqname=data.data.shenqingysmc;
                var chushengrq=data.data.chushengrq;
                $("#J_UserInfo").html("");
                var sex="";
                (data.data.xingbie==1) ? sex="男":sex="女";
//                var age=ages(data.data.chushengrq);
                var st="";
                st+='<div class="panel-heading"><h3 class="panel-title">会诊患者</h3></div>';
                st+='<div class="panel-body"><div class="patientInfo-3 clearfix"><p>姓名：<span >'+data.data.xingming+'</span></p></div>';
                st+='<div class="patientInfo-3 clearfix"><p>性别：</p><p>年龄：</p><p>出生日期：'+chushengrq+'</p>';
                st+='</div><div class="patientInfo-3 clearfix"><p>身份证号：'+data.data.shenfenzh+'<span></span></p><p>手机号：'+data.data.shoujihm+'<span></span></p></div></div>';
                $("#J_UserInfo").append(st);
                $("#update_hzyj").val(data.data.zhenduanbg);
                $("#bingqingms").html(data.data.bingqingms);
                $("#binglixx").html(data.data.binglixx);
                $("#iFrame_div").css("display","none");
                $("#J_UserInfo").parent().show();
                $("#report-null").hide();

            }
        }else{
            layer.msg('打开错误');
        }
        changeDiv(2);
    },"json").error(function(){
        layer.msg("服务器错误");
    })

    return false;
}

//计算周岁
function  ages(str) {
    var   r   =   str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if(r==null)return   false;
    var   birth=   new   Date(r[1],   r[3]-1,   r[4]);
    if   (birth.getFullYear()==r[1]&&(birth.getMonth()+1)==r[3]&&birth.getDate()==r[4])
    {
        var today = new Date();
        var age = today.getFullYear()-r[1];

        if(today.getMonth()>birth.getMonth()){
            return age;
        }
        if(today.getMonth()==birth.getMonth()){
            if(today.getDate()>=birth.getDate()){
                return age;
            }else{
                return age-1;
            }
        }
        if(today.getMonth()<birth.getMonth()){
            return age-1;
        }

    }
}

//webrtc end ----------
function up(type){
    var messageSu = '';
    var messageEr = '';
    var parame = {
        "liushuihao":lsh
    };
    if(type == 0){
        messageSu = '报告保存成功';
        messageEr = '报告保存失败';
        parame.zhenduanbg = $("#update_hzyj").val();//诊断报告
        parame.type = '0';
        btnAJAX(parame);
    }else if(type == 1){
        //开始会诊
        parame.type = '1';
        messageSu = '开始会诊';
        messageEr = '开始出错,请重新点击开始按钮';
        btnAJAX(parame)
    }else if(type ==2 ){
        //结束会诊
        parame.type = '2';
        var hzyj = $("#update_hzyj").val();
        if(hzyj == null ||　hzyj == ''){
            layer.msg("请先完善会诊报告");
            return;
        }
        parame.zhenduanbg = hzyj;
        messageSu = '结束会诊成功';
        messageEr = '结束会诊出错';
        layer.confirm('确定要结束会诊吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            btnAJAX(parame);
        }, function(){
        });
    }else if(type ==3 ){
        parame.type = '3';
        messageSu = '异常挂断成功';
        messageEr = '异常挂断出错';
        layer.prompt({title: '填写挂断原因',formType:2,maxlength:20}, function(text, index){
            parame.jvjueyy =text;
            layer.close(index);
            btnAJAX(parame)
        });
    }
    function  btnAJAX(parame) {
        var index = layer.load(1);
        $.ajax({
            dataType:'json',
            data:parame,
            url:globalVar.base+'/consultationSingle/consultationUpdate',
            type:'post',
            success:function(data){
                layer.close(index);
                if(data.success == true){
                    //成功
                    layer.msg(messageSu);
                    //业务方法
                    if(type == 0){
                        //只更新会诊报告
                    /*    $('#writeReport').modal('hide');*/
                    }
                    if(type == 1){
                        changebrn(type)//调用按钮方法
                        //正常开始
                        monitor.dataChanneSend(JSON.stringify({"type":"alertMsg"}));
                    }else if(type == 2){
                        //正常结束
                        monitor.dataChanneSend(JSON.stringify({"type":"stop"}));
                        monitor.stopConsultation();
                        clearVar()
                        //childCloseTab()//关闭会诊间
                    }
                    else if(type == 3){
                        //异常挂断
                        monitor.dataChanneSend(JSON.stringify({"type":"alertHint"}));
                        monitor.stopConsultation();
                        clearVar()
                        //childCloseTab()//关闭会诊间返回上一个tab页
                    }
                }else{
                    layer.msg(messageEr);
                }
            },
            error:function(){
                layer.close(index);
                layer.msg("网络连接异常");
            }
        });
    }
}