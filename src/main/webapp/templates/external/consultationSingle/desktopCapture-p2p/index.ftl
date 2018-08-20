<#assign base=rc.contextPath />
<@choiceSign.header />
<@choiceSign.left />
<html lang="en">

<head>
    <script>
        if(!location.hash.replace('#', '').length) {
            location.href = location.href.split('#')[0] + '#' + ${singleId};
            location.reload();
        }
        var lsh=${singleId};
        var globalVar = {};
		globalVar.base = '${base}';
		var path = "/consultationSingle/";//全局url
    </script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <link rel="stylesheet" href="${base}/static/lib/RTCMultiConnection-master/style.css">

    <title>Audio/Video/Screen sharing and recording using RTCMultiConnection</title>
    
    <meta name="description" content="Audio/Video/Screen sharing and recording using RTCMultiConnection" />
    <meta name="keywords" content="Audio Recording, Video Recording, Screen Recording, RTCMultiConnection" />

    <style>
	   *{
		font-size:14px;
	   }
        video {
             width:25%;	  
        }
		 
        button {
           
            margin: 5px 10px;
            font-size:16px;
        }

        hr {
            border: 0;
            border-top: 1px solid rgb(189, 189, 189);
        }
		li {
			padding:0;
			border-bottom:0;
			border-left:0;
		}
		#logoImg{
			display:block;
			overflow: hidden;
			margin-top: 12px;
			width: 384px;
			height: 44px;
			background: url(${base}/static/base/images/logo1.png) no-repeat center left;
			border-bottom:0;
		}
		
		 
		.Hui-article{
			top:0;
		}
		 
		.message{
		  float:right;
		  width:15%;
		  height:400px;
		  padding:15px;
		}
		 
		#videos-container{
			width:80%;
			float:left;
			height:400px;
			background:#ccc;
			margin:20px 10px;
			position:relative;
		}
		#videos-container1 video{
			float:left;
			width:50%;
		}
		#videos-container2 video{
			float:right;
		}
		
		 
		.pd-20{
			position:absolute;
			left:0;
			top:0;
			right:0;
			bottom:0;
		}
    </style>
</head>

<body>
   

       

    
	<section class="Hui-article-box">
	  <div class="Hui-article">
		<div class="pd-20">
            <div style="text-align:center;margin-top:20px;">
				
				<button id="openRoom" <#if flagsq==1>disabled="true"</#if>>开启房间</button>
				
                <button id="joinRoom">加入房间</button>
				 
                <button id="shareScreen" disabled>分享屏幕</button>
				<button id="return" onclick="window.history.go(-1)">返回</button>
				<button onclick="up(1)" id="J_Start" type="button" <#if flagsq==1>disabled="true"</#if> style="display: inline-block;">开始</button>
                <button onclick="up(2)" id="J_End" type="button" <#if flagsq==1>disabled="true"</#if> style="display: none">结束</button>
                <button onclick="up(3)" <#if flagsq==1>disabled="true"</#if> type="button">异常挂断</button>
                 
                 
            </div>

            
            <div class="videoBox">
	            <div id="videos-container">
					<div id="videos-container1" style="width:100%;position:absolute;left:0;top:0;">
						
					</div>
					<div id="videos-container2" style="position:absolute;right:0;bottom:0;z-index:999">
					
					</div>
				</div>
				<div class="message">
					<!--<span>当前<span id="onlinec">0</span>人在线</span>-->
					<lable>会诊意见：</lable>
					<textarea id="update_hzyj" name="" style="height: 300px;width: 150px;resize: none;"></textarea>
					<button type="button" onclick="up(0)" <#if flagsq==1>disabled="true"</#if> class="btn btn-primary">保存</button>
				</div>
				
			</div>
            <br>
			
			</div>
		</div>
	</section>
         <script src="${base}/static/lib/RTCMultiConnection-master/RTCMultiConnection.js"></script>
        <!-- for p2p streaming -->
        <script src="${base}/static/lib/RTCMultiConnection-master/RecordRTC.js"></script>
        <!-- for recording -->
        <script src="${base}/static/lib/RTCMultiConnection-master/socket.io.js"></script>

        <script>
            // http://www.rtcmulticonnection.org/docs/constructor/
            var rmc = new RTCMultiConnection();

            // https://github.com/muaz-khan/WebRTC-Experiment/tree/master/socketio-over-nodejs
            var SIGNALING_SERVER = 'https://view.jkytest.com:8818/';
            rmc.openSignalingChannel = function(config) {
                var channel = config.channel || rmc.channel || 'default-namespace';
                //var sender = Math.round(Math.random() * 9999999999) + 9999999999;

				var sender=${operIpMy};
				
                io.connect(SIGNALING_SERVER).emit('new-channel', {
                    channel: channel,
                    sender: sender
                });

                var socket = io.connect(SIGNALING_SERVER + channel);
                socket.channel = channel;

                socket.on('connect', function() {
                    if (config.callback) config.callback(socket);
                });

                socket.send = function(message) {
                    socket.emit('message', {
                        sender: sender,
                        data: message
                    });
                };

                socket.on('message', config.onmessage);
            };

            rmc.body = document.getElementById('videos-container1');

            // http://www.rtcmulticonnection.org/docs/#getExternalIceServers
            rmc.getExternalIceServers = false;

            document.getElementById('openRoom').onclick = function() {
                this.disabled = true;
                // http://www.rtcmulticonnection.org/docs/open/
                rmc.open();
            };

            document.getElementById('joinRoom').onclick = function() {
                this.disabled = true;

                // http://www.rtcmulticonnection.org/docs/connect/
                rmc.connect();
            };

            window.onbeforeunload = function() {
                // Firefox
                document.getElementById('openRoom').disabled = false;
                document.getElementById('joinRoom').disabled = false;
            };

            rmc.onMediaCaptured = function() {
                document.getElementById('openRoom').disabled = true;
                document.getElementById('joinRoom').disabled = true;
            };

            rmc.onstream = function(event) {
				if(event.type === 'local'){
					rmc.body.appendChild(event.mediaElement);
				}else{
					document.getElementById('videos-container2').appendChild(event.mediaElement);
				}
                if(event.type === 'remote' && !recorders.length) {
                    document.getElementById('shareScreen').disabled = false;
                }
            };

            document.getElementById('shareScreen').onclick = function() {
                this.disabled = true;

                // http://www.rtcmulticonnection.org/docs/addStream/
                rmc.addStream({
                    screen: true,
                    oneway: true
                });
            };

            var recorders = [];

             
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
		    function changebrn(num) {
		        if(num==1){
		            $("#J_Start").hide();//隐藏开始按钮
		            $("#J_End").show();//显示结束按钮
		        }
		    }
        </script>

  

     

</body>

</html>

