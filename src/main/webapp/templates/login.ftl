<#assign base=rc.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <title>登录</title>
    <link rel="stylesheet" href="${base}/static/base/css/common.css"/>
    <link rel="stylesheet" href="${base}/static/base/css/login.css"/>
    <link rel="stylesheet" href="${base}/static/base/css/icomoon/fontIcon.css"/>
    <script type="text/javascript" src="${base}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
</head>
<body>
    <div class="loginheard">
        <img src="${base}/static/base/images/logo1.png"/>
    </div>
    <div class="loginMain">
        <div class="loginFormBox">
            <div class="loginFormBox-hd">
                欢迎登陆
            </div>
            <form id="LoginForm">
                <div class="loginFormBox-bd">
                    <div class="input-text input-text-hasicon">
                        <em class="login-icon icofont">&#xe907;</em>
                        <input type="text" name="userName" id="userName" data-name="用户名" placeholder="请输入用户名" value="" />
                    </div>
                    <div class="input-text input-text-hasicon j_BindSwatch">
                        <em class="login-icon icofont">&#xe903;</em>
                        <input type="password" name="password" id="password" data-name="密码" placeholder="请输入密码" value="" />
                    </div>
                </div>
                <button type="button" onclick="doLogin();" class="btn-blue" id="J_Submit">登录</button>
            </form>
            
        </div>
    </div>
 </body>
</html>
<script>

$(function(){ 
	$(document).keydown(function(event){ 
		if(event.keyCode==13){ 
			doLogin(); 
		} 
	}); 
}) 


function doLogin(){
	if($("#userName").val() == ""){
		alert("请输入用户名");
		return;
	}
	if($("#password").val() == ""){
		alert("请输入密码");
		return;
	}
	
	var data = $("#LoginForm").serialize();
	$.post("${base}/checkUser.do",data,function(data){
		//alert(data.success);
		if(data.success == true){
			location.href = '${base}/index.do';
    	}else{
			alert("用户名或密码错误");
		}
	},"json").error(function(){
		alert("网络错误");
	});
	
}
</script>