function parseJsonMessage(data){
    if(data.type == '3'){
        //视频会诊邀请
        layer.confirm(data.content+',确认要加入视频吗?', {
            btn: ['确认','取消'], //按钮
        }, function(index){
            layer.close(index);
            //检测是否已开启
            var state = getCookie("state");
            if(state == '1'){
                layer.msg("请先关闭当前的视频窗口");
                return;
            }
            //window.open("consultationroomtopb?singleId="+data.spare+"&opId="+data.createrCode+"&type=2");
            window.open(globalVar.base+"/consultationSingle/consultationroomtopb?singleId="+data.spare+"&opId="+data.createrCode+"&type=2",'_blank','width=800,height=600,menubar=no,toolbar=no,status=no,scrollbars=yes')
            document.cookie="state="+"1";
            showSingle(data.spare,'1',null);
            //打开会诊单
            /* layer.close(index);
             var parame = data.spare+'/2'+'/'+data.createrCode;
             //window.open("/api/consultation/joinConsultationRoom/"+parame);
             iframeTab('/api/consultation/joinConsultationRoom/'+parame+'','会诊间')*/
        });
    }else if(data.type == '1' || data.type == '2'){
        var node = null;
        $("._online").each(function(m,n){
            node = $(this);
            if(node.attr("data-ysid") == data.id){
                if(data.type == '1'){
                    //上线
                    node.removeClass("text-orange").addClass("text-green");
                    node.html("在线");
                }else {
                    //下线
                    node.removeClass("text-green").addClass("text-orange");
                    node.html("不在线");
                }
            }
        });
    }
}
var mysocket = {
    socket :null,
    //打开连接的方法
    socketOpen:function(ip){
        if('WebSocket' in window){
            mysocket.socket = new WebSocket("ws://"+$("#hidden_socketUrl").val()+"/ws/"+ip);
            mysocket.socket.onopen = mysocket.socketOnOpen;
            mysocket.socket.onmessage = mysocket.socketOnMessage;
            mysocket.socket.onbeforeunload = mysocket.socketOnbeforeunload;
        }
        else{
            alert('该浏览器不兼容部分功能,请使用火狐浏览器')
        }
    },
    //关闭连接的方法
    socketClose:function(){
        if(mysocket.socket != null){
            mysocket.socket.close();
        }else{
            alert("websocket未连接,不能关闭");
        }
    },
    //发送的方法
    socketSend:function(message){
        if(mysocket.socket != null){
            mysocket.socket.send(message);
        }
    },
    socketOnOpen:function(){
        //连接成功的回调函数
        //alert("连接成功");
    },
    socketOnMessage:function(event){
        //接收到消息的回调
        parseJsonMessage(JSON.parse(event.data));
    },
    socketOnbeforeunload:function(){
        //窗口关闭
        if(mysocket.socket != null){
            mysocket.socket.close();
        }
    }

}
function getCookie(name) {
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}