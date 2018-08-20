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
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${base}/static/lib/html5.js"></script>
	<script type="text/javascript" src="${base}/static/lib/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="${base}/static/base/css/min.css" />
	<link rel="stylesheet" type="text/css" href="${base}/static/base/css/admin.css" />
	<link rel="stylesheet" type="text/css" href="${base}/static/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${base}/static/base/skin/blue/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="${base}/static/base/css/style.css" />
	<style>
		.col-md-12{
			margin-top:50px;
		}
		.formControls{
			width:60%;
			margin:0 20% 0 20%;
		}
		.top-oprt-bar{
		    width: 100%;
			text-align: center;
			margin-top:10px;
		}
	</style>
	<!--[if IE 6]>
	<script type="text/javascript" src="http://static/lib.net/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
	<!--/meta 作为公共模版分离出去-->
	<script type="text/javascript" src="${base}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="${base}/static/base/js/main.js"></script> 
	<script type="text/javascript" src="${base}/static/base/js/admin.page.js"></script> 
</head>
<section class="Hui-article-box">
	<input type="hidden" id="contractId" name="contractId" value="${contractId! }"/>
	<div class="col-md-12">		
	        <div class="formControls">
	        <span class=""> 
	        	签约年份:<input style="width:60px;margin:0 15px" readonly="readonly" value="${signYear!}" name="signYear" type="text" class="input-text" value="" >
	        	续约年份:<input style="width:60px;margin:0 15px" readonly="readonly"  value="${renewYear!}" name="renewYear" type="text" class="input-text" value="" >
	        </span>
	        </div>
	</div>
	<div class="col-md-12">					
		<span class="l top-oprt-bar">
			<input class=" btn btn-primary radius" type="submit" id="renew" value="确认">
			<input class=" btn btn-primary radius" type="submit"  onClick="layer_close();" value="取消">
		</span>
	</div>
			
	<script type="text/javascript">
		var globalVar = {};
		globalVar.base = '${base}';
	</script>
	<script type="text/javascript" src="${base}/static/base/js/contract/contractRenew.js"></script>
</section>
</html>