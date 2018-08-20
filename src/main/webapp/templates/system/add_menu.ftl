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
	<form  id="formadd" >
		<input type="hidden" id="operationType" name="operationType"   value="${operationType!}" />
		<input type="hidden" id="menuId" name="menuId"  value="${menuId!}"/>
		<input type="hidden" id="pageNumber" value="${pageNumber!}"/>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2 lable1">父节点：</label>
			<div class="formControls col-xs-6 col-sm-6">
				<span class="select-box">
				<select id="parentId" name="parentId"  class="select">
					<option value=0>系统根节点</option>
					<#if fristList?exists>
						<#list fristList as frist>
							<option value=${frist.menuId} <#if editmenu?exists && frist.menuId == editmenu.parentId> selected</#if>>${frist.menuName}</option>
						</#list>
					</#if>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>栏目名称：</label>
			<div class="formControls col-xs-6 col-sm-6">
				<input id="menuName" name="menuName" datatype="*2-10" value="${editmenu?if_exists.menuName?if_exists}"  type="text" class="input-text"  placeholder="" />
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>url：</label>
			<div class="formControls col-xs-6 col-sm-6">
				<input id="url" name="url"  type="text" value="${editmenu?if_exists.url?if_exists}"  class="input-text"  placeholder="" />
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1">是否显示：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<span class="select-box">
				<select id="display" name="display"  class="select">
					<option value=0 <#if editmenu?exists && editmenu.display == 0> selected</#if>>显示</option>
					<option value=1 <#if editmenu?exists && editmenu.display == 1> selected</#if>>不显示</option>
				</select>
				</span>
			</div>
		</div>
		
		<div class="cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2" style="wdith:100%;margin-top:3%">
				<button class="btn btn-primary radius" style="margin-left:65%;" type="button" onclick="save()" ><i class="Hui-iconfont">&#xe632;</i> 保存</button>
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
	<script>
	$("#formadd").validate({
		rules:{
			menuName:{
				required:true,
				rangelength:[2,10]

			},
			url:{
				required:true
				
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}
	});
	function save(){
		if($("#formadd").valid()){	
			var data = $("#formadd").serialize();
			$.post("${base}/commonmenu/save",data,function(data){
				
				if(data.success == true){
					
					//成功
					layer.msg('操作成功!',{icon:1,time:1000});
		    		setTimeout(function(){
		    			parent.location.href = contextPath+'/commonmenu/show';
		    			layer_close();
		    		},1200)
				}else{
					layer.msg('操作失败!',{icon:2,time:1000});
				}
			},"json").error(function(){
				layer.msg("网络错误");
			});
		}
	}
	</script>
</body>
</html>