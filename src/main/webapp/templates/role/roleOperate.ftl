<#assign base=rc.contextPath />
 <base id="base" href="${base}">
<@choiceSign.header />

<@choiceSign.left />
<style>
	table{
		cursor:pointer;
	}
</style>
<section class="Hui-article-box">
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i>
		<a href="${base}/others.htm" class="maincolor">首页</a> 
		<span class="c-gray en">&gt;</span>系统设置
		<span class="c-gray en">&gt;</span>角色管理
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<form method="post" id="listForm" action="${base}/role/display">
			<div class="text-c">
				<input type="hidden" id="page" name="page"/>
				<input type="text" name="roleName" id="contents" placeholder="名称" style="width:250px" class="input-text" value="${roleName!}" />
				<button onclick="refresh()" id="search" class="btn btn-success" type="button" id="search"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			</div>
			</form>
			<div class="f-l" style="width: 70%">
				<div class="cl pd-5 bg-1 bk-gray mt-20">
					<span class="l">
						<a class="btn btn-primary radius" data-title="添加" _href="article-add.html" onclick="addRow(0)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 新增</a>
						<a href="javascript:;" class="btn btn-danger radius" onclick="dels()"><i class="Hui-iconfont">&#xe6e2;</i> 删除</a>
					</span>
					<span class="r">共有数据：<strong>
						<#if page.totalRecord??>
						${page.totalRecord}
						<#else>
						0
						</#if>
					</strong> 条</span>
				</div>
				<div class="mt-20">
					<table class="table table-border table-bordered table-bg table-hover table-sort">
						<thead  class="text-c">
								<th width="4%"><input type="checkbox" name="box1" value=""></th>
								<th width="10%">编号</th>
								<th >角色名称</th>
								
								<th width="15%">类别</th>
								<th width="15%">操作</th>
							</tr>
						</thead>
						<tbody id="tbody">
							<#if page.content?exists>
							<#list page.content as item>
								<tr class="text-c" name="dicttr" data-roleid="${item.roleId!}">
									<td><input type="checkbox" data-roleid="${item.roleId!}" value="" name="box2" ></td>
									<td>${item.roleId!}</td>
									<td>${item.roleName!}</td>
								
									<td><#if item.flag?? && item.flag==0>系统角色</#if>
										<#if item.flag?? && item.flag==1>自定义角色</#if>
									</td>
									<td>
										<a onclick="addRow(${item.roleId!})" href="javascript:void(0)"><i class="Hui-iconfont"></i>修改</a>
										<a onclick="jurisdiction(${item.roleId!},'${item.roleName!}')" href="javascript:void(0)"><i class="Hui-iconfont"></i>设置权限</a>
									</td>
								</tr>
							</#list>
							</#if>
						</tbody>
					</table>
					<@choiceSign.signpage page/>
				</div>
			</div>
			<!-- 菜单 -->
				<div id="_menus" class="f-l" style="display:none;width: 20%;padding-top: 20px;padding-left: 75px">
					    <div class="panel panel-primary">
					    	<div class="panel-header" id="roleName"></div>
					    	<div class="panel-body">
					            <ul id="_tree" class="ztree"></ul>
					     		<input onclick="operationRoleMenu()" class="btn btn-success radius" type="button" value="保存">
					    	</div>
					    </div>
				</div>
		</article>
<@choiceSign.footer />
	</div>
	<script>
		var contextPath = '${base}';
	</script>
	<link rel="stylesheet" type="text/css" href="${base}/static/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" />
	<script type="text/javascript"  src="${base}/static/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript"  src="${base}/static/base/js/role/roleOperate.js"></script>
</body>
</html>
