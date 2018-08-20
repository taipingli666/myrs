<#assign base=rc.contextPath />
<!DOCTYPE HTML>
<html>
<head>
 <base id="base" href="${base}">
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Shortcut Icon" href="${base}/static/base/images/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${base}/static/lib/html5.js"></script>
<script type="text/javascript" src="${base}/static/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/min.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/admin.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/base/skin/blue/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://static/lib.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<script type="text/javascript" src="${base}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${base}/static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base}/static/base/js/main.js"></script> 
<script type="text/javascript" src="${base}/static/base/js/admin.page.js"></script> 
<!--/meta 作为公共模版分离出去-->

<title>鄂东医疗集团分级诊疗平台</title>
<meta name="keywords" content="求是健康云全科医生签约平台">
<meta name="description" content="求是健康云全科医生签约平台">
</head>
<body>
<!--_header 作为公共模版分离出去-->
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl">
      		<a class="logo navbar-logo f-l mr-10 hidden-xs" href="#"><div class="logopic"><h1>求是健康云全科医生签约平台</h1></div></a>

			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
				<ul class="cl">
					<li style="margin-right:30px" id="hospitalHeader">${hospitalHeader?if_exists.hosName?if_exists }</li>
					<li>欢迎您：${loginUser?if_exists.trueName?if_exists}</li>
					<li><a href="${base}/loginOut.do">退出登陆</a></li>
				</ul>
			</nav>
		</div>
	</div>
</header>
