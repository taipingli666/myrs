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
</head>
<body>
	<div class="page-container">
	<form action="" method="post" class="form form-horizontal" id="dictForm" >
		<input type="hidden" id="operationType"   value="${operationType!}" />
		<input type="hidden" id="dictionaryId"  value="${(info.dictionaryId)!}"/>
		<input type="hidden" id="valueString"  value="${(info.valueString)!}"/>
		<input type="hidden" id="pageNumber" value="${pageNumber!}"/>
		<div class="row cl preDiv" style="margin-top:2%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>名称：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="name"  name="name" type="text" class="input-text" value="${(info.name)!}" placeholder="" />
			</div>
		</div>
		<div class="row cl preDiv" style="margin-top:2%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>编码：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="code" name="code" type="text" class="input-text" value="${(info.code)!}" placeholder="" />
			</div>
		</div>
		<#if values?exists>
		<#list values as value>
			<div class="row cl value_div">
				<label class="form-label col-xs-4 col-sm-2 lable1">值${value_index+1}：</label>
				<div class="formControls col-xs-4 col-sm-2">
					<input type="text" class="input-text" value="${value.word!}" placeholder="">
				</div>
				<label class="form-label col-xs-4 col-sm-2 lable1" style="margin-left:0;">值编码：</label>
				<div class="formControls col-xs-4 col-sm-2">
					<input readonly="readonly" name="code" type="text" class="input-text valid" value="${value.code!}" placeholder="" aria-invalid="false">
				</div>
			</div>
		</#list>
		</#if>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2 lable1">备&emsp;&emsp;注：</label>
			<div class="formControls col-xs-6 col-sm-6">
				<textarea id="remark" cols="" value="" rows="" class="textarea"  placeholder="说点什么..." datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" >${(info.remark)!}</textarea>
			</div>
		</div>
		<div class="cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2" style="wdith:100%;margin-top:3%">
				<button class="btn btn-primary radius " style="margin-left:12%;" type="button"  id='addRow'><i class="Hui-iconfont">&#xe632;</i> 添加行</button>
				<button class="btn btn-primary radius" style="margin-left:45%;" type="button"  id='save'><i class="Hui-iconfont">&#xe632;</i> 保存</button>
				<button onClick="layer_close();" class="btn btn-default radius" style="margin-left:2%;" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
	</div>
	
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<script type="text/javascript">
		var globalVar = {};
		globalVar.base = '${base}';
	</script>
	<script type="text/javascript" src="${base}/static/base/js/dictionary/dictionaryOperate.js"></script>
</body>
</html>