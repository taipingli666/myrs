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
			width:80%;
			margin:0 auto;
		}
		.top-oprt-bar{
		    width: 100%;
			text-align: center;
			margin-top:30px;
		}
		.formControls dl{
			height:30px;
			line-height:40px;
			margin-top:12px;
		}
		.formControls dl dt{
			float:left;
			width:80px;
			text-align:right;
		}
		.formControls dl dd{
		 	float:left;
		}
	#time{
		width:150px;
		height:30px;
		border:1px solid #ddd;
		padding:3px 5px;
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
	<script type="text/javascript" src="${base}/static/lib/My97DatePicker/4.8/WdatePicker.js"></script>
</head>
<section class="Hui-article-box">
	<!-- 隐藏存值区 start -->
	<!-- 医院编码 -->
	<input id="hosCode" type="hidden" value="${hosCode }"/>
	<!-- 大类编码 -->
	<input id="classCode" type="hidden" value="${classCode }"/>
	<!-- 隐藏存值区 end -->
	<div class="col-md-12">		
	        <div class="formControls">
	        <dl>
	        	<dt>日期：</dt>
	        	<dd>
	        		<input id="date" type="text"  class="Wdate" onchange="pickTime(this)" onFocus="WdatePicker({lang:'zh-cn'})" style="width:120px;padding:3px 4px;font-size:14px;border:1px solid #ddd;height:30px;float:left;box-sizing:border-box">
	        	</dd>
	        </dl>
	        <dl>
	        	<dt>时间段：</dt>
	        	<dd>
	        		<select name="" id="time" style="width:200px">
		                <!-- <option value="">10:50 - 11:50</option>
		                <option value="">11:50 - 12:50</option>
		                <option value="">13:50 - 14:50</option>
		                <option value="">15:50 - 16:50</option> -->
	                </select>
	        	</dd>
	        </dl>
	        </div>
	</div>
 					
		<div class="l top-oprt-bar">
			<input class=" btn btn-primary radius" type="submit" id="referral" value="确定" onclick="sure()">
			<input class=" btn btn-primary radius" type="submit"  onClick="layer_close();" value="取消">
		</div>
 
			
	<script type="text/javascript">
		var globalVar = {};
		globalVar.base = '${base}';
	</script>
	<script type="text/javascript" src="${base}/static/base/js/external/checkSchedule.js"></script>
</section>
</html>