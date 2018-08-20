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
	<form action="" method="post" class="form form-horizontal" id="formOperated" >
		<input type="hidden" id="operationType"   value="${operationType!}" />
		<input type="hidden" id="departmentid"  value="${departmentid!}"/>
		<input type="hidden" id="pageNumber" value="${pageNumber!}"/>
		<div class="row cl" style="margin-top:2%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>科室名称：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="departmentname"  name="departmentname" type="text" class="input-text" value="${(info.departmentname)!}" placeholder="" />
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>医院名称：</label>
			<div class="formControls col-xs-6 col-sm-2">
				<select id="hosid" name="hosid" type="select" class="select-box"  placeholder="医院选择" onchange="gethosnum()" >
						<option data-hosnum="" value="">--请选择--</option>
						<#if list?exists>
								<#list list as item>
									<#if info?exists>
										<option value=${item.hosId!} data-hosnum="${item.hosNum!}"  <#if item.hosId! ==  info.hosid!>selected</#if>>${item.hosName!}</option>
									</#if>
									<#if info?exists == false>
										<option data-hosnum="${item.hosNum!}" value=${item.hosId!}>${item.hosName!}</option>
									</#if>
								</#list>
						</#if>
				</select>
			</div>
		</div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>远程会诊启用：</label>
            <div class="formControls col-xs-6 col-sm-2">
                <select id="distanceFlag" name="distanceFlag" type="select" class="select-box"  placeholder="医院选择" onchange="gethosnum()" >
                    <option data-hosnum="" value="">--请选择--</option>
                    <option value="0"  <#if info?exists ><#if info.distanceFlag??&&info.distanceFlag=="0">selected</#if></#if>>未启用</option>
                    <option value="1"  <#if info?exists ><#if info.distanceFlag??&&info.distanceFlag=="1">selected</#if></#if>>已启用</option>
                </select>
            </div>
        </div>
		
		<div class="row cl" style="margin-top:2%;display:none">
			<div class="formControls col-xs-4 col-sm-2">
				<input id="departmentid" name= "departmentid" type="text" class="input-text" value="${(info.departmentid)!}" placeholder="" />
			</div>
		</div>
		
		<div class="row cl" style="margin-top:2%;display:none">
			<div class="formControls col-xs-4 col-sm-2">
				<input id="hosnum" name= "hosnum" type="text" class="input-text" value="${(info.hosnum)!}" placeholder="" />
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2 lable1">备&emsp;&emsp;注：</label>
			<div class="formControls col-xs-6 col-sm-6">
				<textarea id="remark" name="remark" cols="" value="" rows="" class="textarea"  placeholder="说点什么..." datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" >${(info.remark)!}</textarea>
			</div>
		</div>
		<div class="cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2" style="wdith:100%;margin-top:3%">
				<button class="btn btn-primary radius" style="margin-left:65%;" type="button"  onclick="operated()"  id='save'><i class="Hui-iconfont">&#xe632;</i> 保存</button>
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
	<script type="text/javascript" src="${base}/static/base/js/department/departmentOperate.js"></script>
</body>
</html>