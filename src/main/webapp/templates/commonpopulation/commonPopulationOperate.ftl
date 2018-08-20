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
		.lable1{
			margin-left:12%;
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
<body style="margin-left:295px;">
	<div class="page-container">
	<form id="formOperated">
	    <input type="hidden" id="pId" name="pId" value="${(info.pId)!}"/>
	    <input type="hidden" id="areaCode" name="areaCode" value="${(info.areaCode)!}"/>
		<input type="hidden" id="pageNumber" value="${pageNumber!}"/>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>地区编码：</label>
			<div class="formControls col-xs-4 col-sm-20">
				<input disabled="false" id="areaCode1"  name="areaCode1" type="text" class="input-text" value="${(info.areaCode)!}" placeholder="" />
			</div>
		</div>
		
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>人口数量：</label>
			<div class="formControls col-xs-4 col-sm-20">
				<input id="pNumber"  name="pNumber" type="text" class="input-text" value="${(info.pNumber)!}" placeholder="" />
			</div>
		</div>
	    
	    <div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>统计年份：</label>
			<div class="formControls col-xs-4 col-sm-20">
				<input id="countYear"  name="countYear" type="text" onfocus="WdatePicker({dateFmt:'yyyy'})"  class="Wdate" value="${(info.countYear)!}" placeholder=""/>
			</div>
		</div>
		
		<div class="cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2" style="wdith:100%;margin-top:3%">
				<button class="btn btn-primary radius"  type="button"  onclick="operated()" id='save'><i class="Hui-iconfont">&#xe632;</i> 保存</button>
				<button onClick="layer_close();" class="btn btn-default radius" style="margin-left:2%;" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
	</div>
	<script type="text/javascript">
		var contextPath = '${base}';
	</script>
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<script type="text/javascript"  src="${base}/static/base/js/population/commonPopulationOperate.js"></script>
</body>
</html>