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
	<input id="contractId" type="hidden" value="${info.contractId!}">
	<input id="hExpertId" type="hidden" value="${info.expertId!}">
	<div class="col-md-12">		
	        <div class="formControls">
	        <span class="select-box"> 
	        	<select class="select" id="expertId">
	        		<option>-请选择-</option>
	        		<#if otherExperts?exists>
					<#list otherExperts as user>
						<option value="${user.userId!}">${user.trueName!}</option>
					</#list>
					</#if>
	        	</select> 
	        </span>
	        </div>
	</div>
	<div class="col-md-12">					
		<span class="l top-oprt-bar">
			<input class=" btn btn-primary radius" type="submit" id="referral" value="转介">
			<input class=" btn btn-primary radius" type="submit" id="printReferral" value="打印转介协议书">
			<input class=" btn btn-primary radius" type="submit"  onClick="layer_close();" value="取消">
		</span>
	</div>
			
	<script type="text/javascript">
		var globalVar = {};
		globalVar.base = '${base}';
	</script>
	<script type="text/javascript" src="${base}/static/base/js/contract/referralContract.js"></script>
</section>
</html>