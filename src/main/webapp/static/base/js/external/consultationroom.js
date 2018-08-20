var monitor ={
    initSucess:false,
    socket    :null,
    PeerConnection:null,
    pc:null,
    dataChannel:null,
    started:false,
    localStream:null,
    remoteVideo:null,
    localVideo:$("#localVideo"),
    remoteStream:null,
    scoketPath:null,
    roomId:null,
    userId:null,
    socketSate:false,
    iceServer:{//打洞服务器地址配置
    	"iceServers": [{
            url: "stun:192.168.1.211:8818"
        },{
            url: "turn:192.168.1.211:8818",
            username: "qsjkyt1",
            credential: "qsjkyt1234"
        }]},
    log:function(msg){

            console.log(msg);

    },//初始化一些信息
    initialize:function(scoketPath,roomId){
        //PeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection;
        //monitor.localVideo = $("#"+localVideoId);
        monitor.scoketPath   = scoketPath;
        monitor.roomId = roomId;
        monitor.userId = $("#hidden_operIp").val();
        monitor.openChannel();
    },//打开webscoket
    openChannel:function(){
        monitor.socketSate=true;
        monitor.socket = new WebSocket(monitor.scoketPath+monitor.roomId+"/"+monitor.userId);
        monitor.socket.onopen = monitor.onopen;
        monitor.socket.onmessage = monitor.onChannelMessage;
        monitor.socket.onclose = monitor.onChannelClosed;
    },
    onopen:function(onopenCallBack){
        console.log("websocket打开");
        monitor.socketSate = true;
        //monitor.getUserMedia();
        monitor.sendMessageByString("query-On-line");
    },
    onChannelClosed:function(){
        console.log("websocket关闭");
        //monitor.socketSate=false;
        //monitor.openChannel();
    },
    onChannelMessage:function(message){
        console.log("收到信息 : " + message.data);
        /*if(message.data.startsWith("chat")){
            //聊天信息
            parse( message.data.substring(4));
            return;
        }*/
        if(message.data=="query-On-line-Yes"){
            //说明查询到对方
            monitor.maybeStart();
        }else if(message.data=="peopleMax"){
            alert("人数已经超过限制");
        }else if(message.data=="bye"){
            monitor.onRemoteClose();
        }else if(message.data!="query-On-line-No"){
            monitor.processSignalingMessage(message.data);//建立视频连接
        }
    },
    getUserMedia:function(){
        PeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection;
        //console.log("获取用户媒体");
        navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;
        navigator.getUserMedia({
            "audio" : true,
            "video" : true
        },monitor.onUserMediaSuccess, monitor.onUserMediaError);
    },
    onUserMediaSuccess:function(stream){
        monitor.localStream=stream;
        var url = window.URL.createObjectURL(stream);
        console.log(url);
        monitor.localVideo.attr("src",url);
        console.log("获取媒体成功");
        monitor.localVideo[0].muted=true;
        //console.log("发送查询");
        //monitor.sendMessageByString("query-On-line");
    },
    onUserMediaError:function(){
        //console.log("获取用户流失败！");
        layer.alert("抱歉,获取摄像头失败");
    },
    maybeStart:function(){
        if (!monitor.started) {
            //如果没有开始,创建一个连接
            monitor.createPeerConnection();
            //本地添加媒体
            monitor.pc.addStream(monitor.localStream);
            //变成开始状态
            monitor.started = true;
            //呼叫对方
            monitor.doCall();
        }
    },
    createPeerConnection:function(){
        monitor.pc = new PeerConnection(monitor.iceServer);
        monitor.pc.ondatachannel = function(ev) {
            //console.log(ev);
            //monitor.dataChannel.close();
            monitor.dataChannel = ev.channel;
            monitor.dataChannel.onopen  = monitor.onDataChannelOpen;
            monitor.dataChannel.onclose   = monitor.onDataChannelClose;
            monitor.dataChannel.onmessage  = monitor.onDataChannelMessage;
        };
        monitor.dataChannel = monitor.pc.createDataChannel("chat",{reliable: true});
        monitor.dataChannel.onopen  = monitor.onDataChannelOpen;
        monitor.dataChannel.onclose   = monitor.onDataChannelClose;
        monitor.dataChannel.onmessage  = monitor.onDataChannelMessage;
        monitor.pc.onicecandidate =monitor.onIceCandidate;
        monitor.pc.onconnecting = monitor.onSessionConnecting;
        monitor.pc.onopen = monitor.onSessionOpened;
        monitor.pc.onaddstream = monitor.onRemoteStreamAdded;
        monitor.pc.onremovestream = monitor.onRemoteStreamRemoved;
    },
    onSessionConnecting:function(message){
        //console.log("开始连接");
    },
    onSessionOpened:function(message){
        //console.log("连接打开");
    },
    onRemoteStreamAdded:function(event){
        //console.log("远程视频添加");
            //添加视频
           $("#remotes").html('<video id="remoteAudio" autoplay="autoplay"></video>');
           var url = window.URL.createObjectURL(event.stream);
            $("#remoteAudio").attr("src",url);
            monitor.remoteStream = event.stream;
            monitor.waitForRemoteVideo();
    },
    onDataChannelOpen:function(data){
        //数据通道打开
    },
    onDataChannelMessage:function(event){
        //接收到消息,解析
        parse(event.data);
    },
    onDataChannelClose:function(){
        //console.log("数据通道断开");
        //layer.msg("数据通道断开,对方将接收不到您发送的消息");
    },
    dataChanneSend:function(data){
        monitor.dataChannel.send(data);
    },
    waitForRemoteVideo:function(){
        if ($("#remoteAudio")[0].currentTime > 0) { // 判断远程视频长度
            monitor.transitionToActive();
        } else {
            setTimeout(monitor.waitForRemoteVideo, 100);
        }
    },
    transitionToActive:function(){
        //console.log("连接成功！");
        //monitor.sendMessageByString("connection_open");
    },
    onRemoteStreamRemoved:function(event){
        //console.log("远程视频移除");
    },
    onIceCandidate:function(event){
        if (event.candidate) {
            monitor.sendMessage({
                type : "candidate",
                label : event.candidate.sdpMLineIndex,
                id : event.candidate.sdpMid,
                candidate : event.candidate.candidate
            });
        } else {
            //console.log("End of candidates.");
        }
    },
    processSignalingMessage:function(message){
        var msg = JSON.parse(message);
        monitor.log(msg);
        //说明收到的是媒体对象offer
        if (msg.type === "offer") {
            if (!monitor.started)
                //开启视频,如果视频已开启,此操作return
                monitor.maybeStart();
            //设置对方的媒体数据
            monitor.pc.setRemoteDescription(new RTCSessionDescription(msg),function(){
                //发送应答
                monitor.doAnswer();
            },function(){
            });
        } else if (msg.type === "answer" && monitor.started) {
            monitor.pc.setRemoteDescription(new RTCSessionDescription(msg));
        } else if (msg.type === "candidate" && monitor.started) {
            var candidate = new RTCIceCandidate({
                sdpMLineIndex : msg.label,
                candidate : msg.candidate
            });
            monitor.pc.addIceCandidate(candidate);
        }
    },
    doAnswer:function (){
        //创建应答者
        monitor.pc.createAnswer(monitor.setLocalAndSendMessage, function(e){
            //console.log("创建相应失败"+e);
        });
    },
    doCall:function(){
        //monitor.log("开始呼叫");
        //创建offer,调用方法
        monitor.pc.createOffer(monitor.setLocalAndSendMessage,function(){
        });
    },
    setLocalAndSendMessage:function(sessionDescription){
        //添加本地媒体描写
        monitor.pc.setLocalDescription(sessionDescription);
        //本地媒体描写发送
        monitor.sendMessage(sessionDescription);
    },
    sendMessage:function(message){
        //格式化描写
        var msgJson = JSON.stringify(message);
        monitor.sendMessageByString(msgJson);
    },
    sendMessageByString:function(message){
        monitor.socket.send(message);
        //console.log("发送信息 : " + message);
    },
    onRemoteClose:function(){
        monitor.started = false;
        monitor.pc.close();
        //monitor.sendMessageByString("connection_colse");
        $("#remotes").html("<p class='videoHint'>对方未加入</p>")
    },
    //停止会诊
    stopConsultation:function(){
        var i = false;
        if(monitor.socketSate == true){
            monitor.socket.close();
            i = true;
        }
        if(monitor.started == true){
            monitor.onRemoteClose();//关闭
            i = true;
        }
        if(i == true){
            //回到初始化
            monitor.initSucess=false,
                monitor.socket=null,
                monitor.PeerConnection=null,
                monitor.pc=null,
                monitor.dataChannel=null,
                monitor.started=false,
                monitor.remoteVideo=null,
                monitor.localVideo=null,
                monitor.remoteStream=null,
                monitor.scoketPath=null,
                monitor.roomId=null,
                monitor.userId=null,
                monitor.socketSate=false;
        }
    }
};