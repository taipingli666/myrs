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
		<a href="${base}/others.do" class="maincolor">首页</a> 
		<span class="c-gray en">&gt;</span>系统设置
		<span class="c-gray en">&gt;</span>团队管理
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<form method="post" id="listForm" action="${base}/team/display.do">
			<div class="text-c">
				<input type="hidden" id="page" name="page"/>
				<input type="text" name="name" id="contents" placeholder="团队名称" style="width:250px" class="input-text" value="${team.name!}" />
				<button onclick="refresh()" id="search" class="btn btn-success" type="button" id="search"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			</div>
			</form>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
					<a class="btn btn-primary radius" data-title="添加" _href="article-add.do" onclick="addRow(0)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 新增</a>
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
					<thead>
						<tr class="text-c">
							<th width="4%"><input type="checkbox" name="box1" value=""></th>
							<th width="6%">编号</th>
							<th width="10%">专家</th>
							<th width="10%">所属医院</th>
							<th width="10%">团队名称</th>
							<th width="15%">添加时间</th>
							<th width="10%">添加人</th>
							<th width="15%">编辑时间</th>
							<th width="10%">编辑人</th>
							<th width="">操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<#if page.content?exists>
						<#list page.content as item>
							<tr class="text-c" name="dicttr" onDblClick="addRow(${item.teamId!})">
								<td><input type="checkbox" data-userid="${item.teamId!}" value="" name="box2" ></td>
								<td>${item.teamId!}</td>
								<td>${item.teamLeader!}</td>
								<td>${item.hosId!}</td>
								<td>${item.name!}</td>
								<td><#if item.addTime??>${item.addTime?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
								<td>${item.addPerson!}</td>
								<td><#if item.editTime??>${item.editTime?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
								<td>${item.editPerson!}</td>
								<td><a onclick="addRow(${item.teamId!})" href="javascript:void(0)"><i class="Hui-iconfont"></i>修改</a></td>
							</tr>
						</#list>
						</#if>
					</tbody>
				</table>
				<@choiceSign.signpage page/>
			</div>
		</article>
	<@choiceSign.footer />
	</div>
	<script>
		var contextPath = '${base}';
	</script>
	<script type="text/javascript"  src="${base}/static/base/js/team/teamList.js"></script>
</body>
</html>
