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
		.lable1{margin-left:12%;}
	 	td{vertical-align:top;}
	 	.ybtn{display:inline-block;margin-left:10px;}
		.hand{cursor:pointer;}
		.tableHover{background-color:#E4E4E4;}
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
		<input type="hidden" id="pageNumber" value="${pageNumber!}"/>
		<input type="hidden" id="teamId" name="teamId" value="${teamId!}"/>
		<input type="hidden" id="prevIds" name="prevIds" value="${prevIds!}"/>
		<input type="hidden" id="isSelect" name="isSelect" value="false"/>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-2 col-sm-2 lable1"><span class="c-red">*</span>团队名称：</label>
			<div class="formControls col-xs-2 col-sm-6">
				<input id="name"  name="name" type="text" class="input-text" value="${(info.name)!}" placeholder="" />
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-2 col-sm-2 lable1"><span class="c-red">*</span>所属医院：</label>
			<div class="formControls col-xs-2 col-sm-6">
				<#if method??&&method=="1">
					<input id="hosId"  name="hosId" type="text" class="input-text" value="${(info.hosId)!}" placeholder="" readOnly style="background-color:#e4e4e4"/>
				</#if>
				<#if method??&&method=="0">
					<input id="hosId"  name="hosId" type="text" class="input-text" value="${hosId!}" placeholder="" readOnly style="background-color:#e4e4e4"/>
				</#if>
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-2 col-sm-2 lable1"><span class="c-red">*</span>专家（团队长）：</label>
			<div class="formControls col-xs-2 col-sm-2">
				<span class="select-box">
					<select id="teamLeader" name="teamLeader"  class="select" >
						<option value="">--请选择--</option>
				        <#if experts??>
				        	<#list experts as item>
				        		<option value="${item.userId!}">${item.userName!}</option>
				        	</#list>
				        </#if>
				        <#if method??&&method=="1">
		        			<option value="${info.teamLeader!}" selected="selected">${leaderName!}</option>
		        		</#if>
					</select>
				</span>
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-2 col-sm-2 lable1"><span class="c-red">*</span>团队成员：</label>
			<div class="formControls col-xs-2 col-sm-6">
					<input type="text" name="selectName" id="selectName" placeholder="用户名(真实姓名)" style="width:250px" class="input-text" value="" />
					<button onclick="doSearch();" id="search" class="btn btn-success" type="button" id="search"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			</div>
		</div>
	</form>
	
	<div class="row cl" style="margin-top:1%;">
		<label class="form-label col-xs-1 col-sm-2 lable1"></label>
		<div class="formControls col-xs-1 col-sm-6">
			<table class="table1">
				<tr>
					<td style="width:49%;">
						<div class="ydiv">
							<table class="table table-border table-bordered table-bg table-sort table2 table-hover">
								<thead>
									<tr class="text-c">
										<th width="20%">操作</th>
										<th width="25%">用户名</th>
										<th width="25%">真实姓名</th>
										<th width="">手机</th>
									</tr>
								</thead>
								<tbody id="userList">
									<#if users??>
									<#list users as item>
										<tr class="text-c" name="dicttr">
											<td><input type="hidden" data-userid="${item.userId!}"><img class="hand" src="${base}/static/base/images/validform/add.jpg" onClick="changeMember(1,this);"></td>
											<td>${item.userName!}</td>
											<td>${item.trueName!}</td>
											<td>${item.mobile!}</td>
										</tr>
									</#list>
									</#if>
								</tbody>
							</table>
						</div>	
						<div id="pagination1" style="margin-top:1%">
							<a onClick="changePage(0);" href="javascript:;"> 上一页 </a>
							<a onClick="changePage(1);" href="javascript:;"> 下一页 </a>
						</div>
					</td>
					<td style="width:2%">
					</td>
					<td style="width:49%;padding:0">
						<div class="ydiv groupDiv">
							<table class="table table-border table-bordered table-bg table-hover table-sort table2">
								<thead>
									<tr class="text-c">
										<th width="20%">操作</th>
										<th width="25%">用户名</th>
										<th width="25%">真实姓名</th>
										<th width="">手机</th>
									</tr>
								</thead>
								<tbody id="memberList">
									<#if members??>
									<#list members as item>
										<tr class="text-c" name="dicttr">
											<td><input type="hidden" data-userid="${item.userId!}"><img class="hand" src="${base}/static/base/images/validform/del.jpg" onClick="changeMember(0,this);"></td>
											<td>${item.userName!}</td>
											<td>${item.trueName!}</td>
											<td>${item.mobile!}</td>
										</tr>
									</#list>
									</#if>
								</tbody>
							</table>
						</div>
					</td>
				</tr>
			</table>
			</div>
		</div>
		<div class="cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2" style="wdith:100%;margin-top:1%">
				<button class="btn btn-primary radius" style="margin-left:65%;" type="button"  onclick="operated()" id='save'><i class="Hui-iconfont">&#xe632;</i> 保存</button>
				<button onClick="layer_close();" class="btn btn-default radius" style="margin-left:2%;" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var contextPath = '${base}';
	</script>
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<script type="text/javascript" src="${base}/static/base/js/team/teamOperate.js"></script>
	
</body>
</html>