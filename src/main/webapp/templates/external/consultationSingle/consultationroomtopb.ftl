<#assign base=rc.contextPath />
 
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/video.css"/>
 <script type="text/javascript" src="${base}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
 <script type="text/javascript" src="${base}/static/lib/bootstrap.min.js"></script>
 <script rel="stylesheet" type="text/javascript" src="${base}/static/base/js/external/indexsocket2.js"/></script>
<script type="text/javascript" src="${base}/static/lib/layer/2.4/layer.js"></script>
<!-- 会诊聊天 -->
<head>
    <meta charset="UTF-8" />
    <title>Title</title>
 
    <style>
        .layui-layer-prompt textarea.layui-layer-input{
            font-size: 14px;
        }
        #modal_queue span{
            font-size: 14px;
        }
    </style>
</head>
<style>
*{
margin:0;
padding:0;
}
p{
margin-bottom:0;
}
.videoEvent-docList .item{
height:70px;
}
.videoEvent-docList .item .title{
font-size:15px;
line-height:20px;
color: #666;
font-weight:400;
 
}
.videoEvent-docList .item .name {
    font-size: 13px;
    line-height:20px;
    color:#666;
}
.videoEvent-docList .item img {
    width:50px;
    height:50px;
    border-radius: 50%;
    position: absolute;
    left:10px;
    top:50%;
    margin-top:-25px;
}
 
	 
 
</style>
<body onbeforeunload="return stateChange()">
<!-- 隐藏 -->
<input id="hidden_socketUrl" value="${socketUrl}" type="hidden">
<input id="hidden_operIp" value="${operIp}" type="hidden">
<input id="hidden_operIpMy" value="${operIpMy}" type="hidden">
<input id="hidden_singleId" value="${singleId}" type="hidden">
<input id="hidden_type" value="${type}" type="hidden">
<div class="tab-content">
    <!-- 视频 -->
    <div class="tab-pane active videoMainBox" id="show_video">
        <ul class="videoMainBox-tabList clearfix" id="J_TabList">
       <!--     <li class="item">
                <a href="#" data-value="1" onclick="javascript:tabSelect(this)">待诊列表</a>
            </li>
            <li class="item">
                <a href="#" data-value="2" onclick="javascript:tabSelect(this)">预约列表</a>
            </li>-->
            <li class="item active" style="width: 100%">
                <a href="#" data-value="3" onclick="javascript:tabSelect(this)">会诊间</a>
            </li>
        </ul>
        <em class="videoMainBox-hint" id="J_Await">当前共有<span class="text-blue">9</span>条排队记录</em>
        <em class="videoMainBox-hint" id="J_Await2" style="display: none">当前共有<span class="text-blue">9</span>条排队记录</em>
        <ul class="videoMainBox-showList" id="J_ShowList">
            <li class="item">
                <div class="videoEvent">
                    <div class="btns" id="J_Btns" style="display: block;">
                        <button onclick="up(1)" id="J_Start" type="button" class="btn btn-primary" style="display: inline-block;">开始</button>
                        <button onclick="up(2)" id="J_End" type="button" class="btn btn-primary" style="display: none">结束</button>
                        <button onclick="up(3)" type="button" class="btn btn-primary">异常挂断</button>
                    </div>
                    <ul class="videoEvent-docList" id="J_VideoDoc"><li class="item"><p class="title">申请医生:金华医生01</p><p class="name">申请医院:金华市中心医院</p><p class="name">医生电话:15162250500</p><img src="/consultationSingle/getsrcimg?path=912000000145524553/295522ccb06e426f943b6cfd3150e6e3.jpg&amp;type=1"></li></ul>
                    <div class="chatRoom " id="J_ChatRoom">
                        <div class="chatRoom-list" id="chatRoom-box">
                            <ul class="chatRoom-ul" id="chatRoom-list" style="overflow: hidden;" tabindex="0"></ul>
                        <div id="ascrail2000" class="nicescroll-rails" style="width: 5px; z-index: auto; cursor: default; position: absolute; top: 10px; left: 208px; height: 0px; opacity: 1; display: none;"><div style="position: relative; top: 0px; float: right; width: 5px; height: 0px; background-color: rgb(0, 173, 238); background-clip: padding-box; border-radius: 5px;"></div></div><div id="ascrail2000-hr" class="nicescroll-rails" style="height: 5px; z-index: auto; top: 5px; left: 10px; position: absolute; opacity: 1; cursor: default; display: none;"><div style="position: absolute; top: 0px; height: 5px; width: 0px; background-color: rgb(0, 173, 238); background-clip: padding-box; border-radius: 5px;"></div></div></div>
                        <div class="form-chat">
                            <div class="input-group input-group-sm">
                                <input class="form-control" id="J_Write" type="text"><span class="input-group-btn"> <button class="btn btn-primary" type="button" onclick="sendMessage()">发送</button> </span>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
        <div class="videoMain">
            <em class="icon-refresh swopBtn" id="swopBtn" style="display: none" onclick="swop()"></em>
            <div id="J_Him" class="videoContainer  videoBox-my">
                <video id="localVideo" oncontextmenu="return false;" autoplay="autoplay"></video>
                <div id="localVolume" class="volume_bar"></div>
            </div>
            <!--<div id="remotes" class="videoContainer videoBox"></div>-->
            <div id="remotes" class="videoContainer videoBox">
                <p class='videoHint'>对方未加入</p>
            </div>
        </div>
    </div>
</div>
<!-- 报告单
<div class="modal fade" id="writeReport" tabindex="-1" role="dialog" aria-labelledby="writeReport">
    <div class="modal-dialog" role="document" style="width: 900px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="font-size: 14px">会诊报告</h4>
            </div>
            <div class="modal-body popupBox">
                <div  class="tab-pane">
                    <div class="panel panel-default" id="J_UserInfo">
                        &lt;!&ndash;<div class="panel-heading">
                            <h3 class="panel-title">
                                会诊患者
                            </h3>
                        </div>
                        <div class="panel-body">
                            <div class="patientInfo-3 clearfix">
                                <p>姓名：<span ></span></p>
                            </div>
                            <div class="patientInfo-3 clearfix">
                                <p>性别：</p>
                                <p>年龄：<span id="J_Age"></span></p>
                                <p>出生日期：<span id="J_Birthday"></span></p>
                            </div>
                            <div class="patientInfo-3 clearfix">
                                <p>身份证号：<span></span></p>
                                <p>手机号：<span></span></p>
                            </div>
                        </div>&ndash;&gt;
                    </div>
                    <div class="opinionBox">
                        <div class="opinionBox-hd">会诊意见:
                            <div class="opinionBox-text">
                                <textarea id="update_hzyj" name=""></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div  class="modal-footer">
                    <button type="button" onclick="up(0)" class="btn btn-primary">保存</button>
                </div>
            </div>
        </div>
    </div>
</div>-->


<script src="${base}/static/base/js/external/consultationroom2.js"></script>
<script type="text/javascript" src="${base}/static/base/js/external/jquery.nicescroll.js"></script>


<script>

    /*<![CDATA[*/
    var globalVar = {};
	globalVar.base = '${base}';

    var path = "/consultationSingle/";//全局url
    $(document).ready(function() {
        $("#chatRoom-list").niceScroll({cursorborder:"",cursorcolor:"#00adee",autohidemode:false});
    });
    $(function(){
        //开启rtc
        monitor.getUserMedia();
    })
    function stateChange(){
        document.cookie="state="+"0";
    }
    function loadSingle(){
        //加载会诊单
        startVideo($("#hidden_singleId").val(),$("#hidden_operIp").val(),$("#hidden_type").val());
    }
    //视频开始
    var sqname = '',
        hzname = '',
        lsh="";
    //获取预约和待诊列表
    var ImgUrl=globalVar.base+path+"/getsrcimg?path=";//头像
    var ImgUrl2 = ""
    function startVideo(singleId,operId,type,me){
        $.ajax({
            dataType:'json',
            data:{"singleId":singleId,"operId":operId,"type":type,"ksbz":"1"},
            url:globalVar.base+path+'/inviteconsultation',
            type:'post',
            success:function(data){
                if(data.success == true){
                	
                	if($("#hidden_operIpMy").val()==data.data.huizhenysid){
	                    monitor.stopConsultation();//关闭连接 
	                  //发送视频邀请
	                    monitor.initialize("ws://"+$("#hidden_socketUrl").val()+"/video/",data.data.liushuihao);
                	}else if($("#hidden_operIpMy").val()==data.data.shenqingysid){
                		monitor.stopConsultation();//关闭连接 
                		//发送视频邀请
                        monitor.initialize("ws://"+$("#hidden_socketUrl").val()+"/video/",data.data.liushuihao);
                		/* monitor.pc.onaddstream = monitor.onRemoteStreamAdded; */
                	}else if($("#hidden_operIpMy").val()!=data.data.huizhenysid && $("#hidden_operIpMy").val()!=data.data.shenqingysid){
                		monitor.stopConsultation();//关闭连接 
                		//发送视频邀请
                        monitor.initialize("ws://"+$("#hidden_socketUrl").val()+"/video/",data.data.liushuihao);
                		monitor.createPeerConnection();
                		monitor.pc.onaddstream = monitor.onRemoteStreamAdded;
                	}
             /*       $("#J_TabList li").eq(2).find("a").trigger("click");//模拟触发tab*/
                    $("#J_VideoDoc").html("");
            /*        $("#J_UserInfo").html("");*/
                    clearVar();//还原按钮
                    lsh=singleId;
                    if(type==1){
                        btnsShow(data.data.huizhenzt) //按钮显示
                        hzname=data.data.huizhenysmc;
                        sqname=data.data.shenqingysmc;
                /*        $("#J_UserInfo").html(" ");
                        var sex="";
                        (data.data.xingbie==1) ? sex="男":sex="女";
                        var age=ages(data.data.chushengrq);
                        var st="";
                        st+='<div class="panel-heading"><h3 class="panel-title">会诊患者</h3></div>';
                        st+='<div class="panel-body"><div class="patientInfo-3 clearfix"><p>姓名：<span >'+data.data.xingming+'</span></p></div>';
                        st+='<div class="patientInfo-3 clearfix"><p>性别：'+sex+'</p><p>年龄：'+age+'</p><p>出生日期：'+data.data.chushengrq+'</p>';
                        st+='</div><div class="patientInfo-3 clearfix"><p>身份证号：'+data.data.shenfenzh+'<span></span></p><p>手机号：'+data.data.shoujihm+'<span></span></p></div></div>';
                        $("#J_UserInfo").append(st);*/
                        $("#update_hzyj").html(data.data.zhenduanbg);
                        (data.data.shenqingystx ==0) ? ImgUrl2='/dist/login/common/img/userImg.png' : ImgUrl2=ImgUrl+data.data.shenqingystx+"&type=1";
                        $("#J_VideoDoc").html('<li class="item"><p class="title">申请医生:'+data.data.shenqingysmc+'</p><p class="name">申请医院:'+data.data.shenqingyymc+'</p><p class="name">医生电话:'+data.data.shenqingysdh+'</p><img  src=\"'+ImgUrl2+'\"/></li>')
                    }else if(type==2){
                        hzname=data.data.shenqingysmc;
                        sqname=data.data.huizhenysmc;
                        (data.data.huizhenystx ==0) ? ImgUrl2='/dist/login/common/img/userImg.png' : ImgUrl2=ImgUrl+data.data.huizhenystx+"&type=1";
                        $("#J_VideoDoc").html('<li class="item"><p class="title">会诊医生:'+data.data.huizhenysmc+'</p><p class="name">会诊医院:'+data.data.huizhenyymc+'</p><p class="name">医生电话:'+data.data.huizhenysdh+'</p><img  src=\"'+ImgUrl2+'\"/></li>')
                        $("#J_ChatRoom").addClass("chatRoom2");
                    }
                }else{
                    layer.msg(data.message);
                }
            },
            error:function(){

            }
        });
    }
    console.log("流水号0："+lsh)
    function addReport(){
        //开启
        $('#writeReport').modal('show');
    }
    function btnsShow(hzzt) {
        $("#J_Btns").show();//按钮显示
        if(hzzt==0){
            $("#J_Start").show();
        }else if(hzzt==1){
            $("#J_Start").hide();
            $("#J_End").show();
        }
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
    //窗口聊天消息超出时  滚动
    function msgScroll() {
        var winH=$("#chatRoom-box").height(),
            docH=$("#chatRoom-list").outerHeight(true);
        //docH.scrollTop(docH)
        if(docH>winH){
            $("#chatRoom-box").scrollTop(docH)
        }
    }
    //窗口互换
    /* function swop() {
     if($("#remotes").hasClass("videoBox-my")){
     $("#remotes").removeClass("videoBox-my").addClass("videoBox");
     $("#J_Him").removeClass("videoBox").addClass("videoBox-my");
     }else{
     $("#remotes").addClass("videoBox-my").removeClass("videoBox");
     $("#J_Him").removeClass("videoBox-my").addClass("videoBox");
     }
     }*/
    var webrtc = null;
    $(function(){
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
    })
    function showVolume(el, volume) {
        if (!el) return;
        if (volume < -45) { // vary between -45 and -20
            el.style.height = '0px';
        } else if (volume > -20) {
            el.style.height = '100%';
        } else {
            el.style.height = '' + Math.floor((volume + 100) * 100 / 25 - 220) + '%';
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
            /*var hzyj = $("#update_hzyj").val();
            if(hzyj == null ||　hzyj == ''){
                layer.msg("请先完善会诊报告");
                return;
            }*/
            //parame.zhenduanbg = hzyj;
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
                url:globalVar.base+path+'/consultationUpdate',
                type:'post',
                success:function(data){
                    layer.close(index);
                    if(data.success == true){
                        //成功
                        layer.msg(messageSu);
                        //业务方法
                        if(type == 0){
                            //只更新会诊报告
                            $('#writeReport').modal('hide');
                        }
                        if(type == 1){
                            changebrn(type)//调用按钮方法
                            //正常开始
                            monitor.dataChanneSend(JSON.stringify({"type":"alertMsg"}));
                        }else if(type == 2){
                            //正常结束
                            monitor.dataChanneSend(JSON.stringify({"type":"stop"}));
                            monitor.stopConsultation();
                            clearVar();
                            layer.msg("2秒后自动关闭窗口");
                            window.setTimeout("ycClose()",'2000');
                        }
                        else if(type == 3){
                            //异常挂断
                            monitor.dataChanneSend(JSON.stringify({"type":"alertHint"}));
                            monitor.stopConsultation();
                            clearVar();
                            layer.msg("2秒后自动关闭窗口");
                            window.setTimeout("ycClose()",'2000');
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
    function ycClose(){
        window.close();
    }
    //聊天方法相关-------------
    //解析rtc交互消息
    function parse(message){
        var data = JSON.parse(message);
        if(data.type === 'chat'){
            //说明是聊天消息
            var msg = data.message;//发送时间和内容  2017-05-16 16:04:44//消息内容
            var time=msg.substring(0,msg.indexOf("//"));
            var msgg=msg.substring(msg.indexOf("//")+2);
            $("#chatRoom-list").append('<li class="left"><p class="name">'+sqname+'<small class="chat-date">'+time+'</small></p><p class="text">'+msgg+'</p></li>')
            msgScroll()//消息超出滚动
            //拿出消息展示界面
        }else if(data.type === 'stop'){
            //会诊方结束会诊,接收方弹出会诊单详情
            layer.confirm('会诊结束，是否查看会诊单？', {
                btn: ['是','否'] //按钮
            }, function(goto){
                //是
                var liushuihao=lsh;
                console.log("流水号2："+lsh)
                layer.close(goto);
                window.location=globalVar.base+path+'/lookConsultationSingle/1/'+lsh;
            }, function(){
                //否
                monitor.stopConsultation();//关闭连接
                window.close();
            });
        }else if(data.type === 'alertMsg'){
            //说明是推送给对方的提示消息
            layer.msg("会诊开始");
        }else if(data.type === 'alertHint'){
            //异常挂断视频
            layer.alert('对方异常挂断视频,关闭会诊间', function(goto){
                //是
                layer.close(goto);
                window.close();
            });
        }
    }
    //发送消息
    function sendMessage(){
        //消息格式为   2017-05-16 16:04:44//消息内容
        //先判断是否接通
        if($("#remotes").find("video").length==0){
            layer.msg("抱歉,必须视频接通才能发送消息");
            return;
        }
        //获取发送框消息
        var message = $("#J_Write").val();
        if(message==0){
            layer.msg("不能发送空消息");
            //提示,不能发送空
            return;
        }
        var time = '';
        //同步获取服务器时间
        var index = layer.load(1);
        $.ajax({
            url:globalVar.base+path+"/getServerTime",
            dataType:"json",
            type:"post",
            async: false,
            success:function(data){
                layer.close(index);
                $("#J_Write").val(" ")//消息发送成功清空输入框
                if(data.success == true){
                    time = data.data; // 2019-02-21 12:22:22
                    //拼接消息
                    $("#chatRoom-list").append('<li class="right"><p class="name">'+hzname+'<small class="chat-date">'+time+'</small></p><p class="text">'+message+'</p></li>')
                    message=time+"//"+message;
                    msgScroll()//消息超出滚动
                }
            },
            error:function(){
                layer.close(index);
                layer.msg("发送消息失败,获取服务器时间异常");
                return;
            }
        });
        monitor.dataChanneSend(JSON.stringify({type:"chat",message:message, name: hzname }));
    }
    function changebrn(num) {
        if(num==1){
            $("#J_Start").hide();//隐藏开始按钮
            $("#J_End").show();//显示结束按钮
        }
    }
    //聊天相关结束-------------
    //窗口聊天消息超出时  滚动
    function clearVar() {
        sqname = '';
        hzname = '';
        lsh="";
        $("#chatRoom-list").html("");
        $("#J_Btns,#J_Start,#J_End").hide();//按钮还原
    }
    var _xh = null;
    //查看预约队列
    function getQueue(id,xuhao){
        var index = layer.load(1);
        $.post(globalVar.base+path+"/getconwaitQueue",{id:id},function(data){
            layer.close(index);
            if(data.success == true){
                if(data.data.length==0){
                    layer.msg("查看错误");
                    _xh = null;
                }else{
                    _xh = xuhao;
                    var t_q = $("#table_queue")
                    var st = '';
                    t_q.html('<tr><td><span>序号</span></td><td><span>时间段</span></td><td><span>状态</span></td></tr>');
                    $(data.data).each(function(m,n){
                        var st2 = '';
                        if(n.yuyuexh == _xh){
                            st2 = '<span style="color: red">*</span>'
                        }
                        if(n.huizhenzt == '1') {
                            st = '会诊中' + st2;
                        }else if(n.huizhenzt == '0'){
                            st = '等待中' + st2;
                        }else{
                            st = '会诊结束' + st2;
                        }
                        t_q.append('<tr><td><span>'+n.yuyuexh+'</span></td><td><span>'+n.yuyuesj+'</span></td><td><span>'+st+'</span></td></tr>')
                    });
                    $("#modal_queue").modal("show");
                }
            }else{
                _xh = null;
                layer.msg("查看错误");
            }
        },"json").error(function(){
            layer.close(index);
            layer.msg("服务器错误");
        })
    }

    /*]]>*/
</script>
</body>
</html>