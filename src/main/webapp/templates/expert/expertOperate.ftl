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
	<form id="formOperated">
		<input type="hidden" id="userid" name="userId" value="${(info.userId)!}" />
		<input type="hidden" id="dictionaryid"  value="${dictionaryid!}"/>
		<input type="hidden" id="pageNumber" value="${pageNumber!}"/>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>用户名：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="userName"  name="userName" type="text" class="input-text" value="${(info.userName)!}" placeholder="" />
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>医院编码：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="hosId"  name="hosId" type="text" class="input-text" value="${(info.hosId)!}" placeholder="" />
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>真实姓名：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="trueName"  name="trueName" type="text" class="input-text" value="${(info.trueName)!}" placeholder="" />
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>性别：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<select id="sex" name="sex">
					<option value="">--请选择--</option>
			        <option value="1" <#if info.sex?? && info.sex==1>selected</#if>>男</option>
			        <option value="0" <#if info.sex?? && info.sex==0>selected</#if>>女</option>
				</select>
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1">电话：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="tel"  name="tel" type="text" class="input-text" value="${(info.tel)!}" placeholder="" />
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>手机：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="mobile"  name="mobile" type="text" class="input-text" value="${(info.mobile)!}" placeholder="" />
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>身份证：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="card"  name="card" type="text" class="input-text" value="${(info.card)!}" placeholder="" />
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1">职称：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="title"  name="title" type="text" class="input-text" value="${(info.title)!}" placeholder="" />
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1">角色：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="role"  name="role" type="text" class="input-text" value="${(info.role)!}" placeholder="" />
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>所属团队：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="teamId"  name="teamId" type="text" class="input-text" value="${(info.teamId)!}" placeholder="" />
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1">介&emsp;&emsp;绍：</label>
			<div class="formControls col-xs-6 col-sm-6">
				<textarea id="introduce" cols="" value="" rows="" name="introduce" class="textarea"  placeholder="说点什么..." datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" >${(info.introduce)!}</textarea>
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1">备&emsp;&emsp;注：</label>
			<div class="formControls col-xs-6 col-sm-6">
				<textarea id="remark" cols="" value="" rows="" name="remark" class="textarea"  placeholder="说点什么..." datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" >${(info.remark)!}</textarea>
			</div>
		</div>
		<div class="cl" style="margin-top:1%;>
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2" style="wdith:100%;">
				<button class="btn btn-primary radius" style="margin-left:65%;" type="button"  onclick="operated()" id='save'><i class="Hui-iconfont">&#xe632;</i> 保存</button>
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
	<script type="text/javascript"  src="${base}/static/base/js/expert/expertOperate.js"></script>
</body>
</html>