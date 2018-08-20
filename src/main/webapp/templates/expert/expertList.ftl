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
		<span class="c-gray en">&gt;</span>专家管理
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<form method="post" id="listForm" action="${base}/expert/display.do">
			<div class="text-c">
				<input type="hidden" id="page" name="page"/>
				<input type="text" name="userName" id="contents" placeholder="用户名" style="width:250px" class="input-text" value="${user.userName!}" />
				<button onclick="refresh()" id="search" class="btn btn-success" type="button" id="search"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			</div>
			</form>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
					<#--<a class="btn btn-primary radius" data-title="修改" _href="article-add.do" onclick="addRow(0)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 编辑</a>-->
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
							<th width="10%">用户名</th>
							<th width="10%">真实姓名</th>
							<th width="10%">性别</th>
							<th width="20%">手机</th>
							<th width="30%">身份证号</th>
							<th width="">操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<#if page.content?exists>
						<#list page.content as item>
							<tr class="text-c" name="dicttr" onDblClick="addRow(${item.userId!})">
								<td><input type="checkbox" data-userid="${item.userId!}" value="" name="box2" ></td>
								<td>${item.userId!}</td>
								<td>${item.userName!}</td>
								<td>${item.trueName!}</td>
								<td>
									
										<#if item.sex?? && item.sex==1>
											男
										<#elseif item.sex?? && item.sex==0> 
										女
										<#else> 
										</#if>
								</td>
								<td>${item.mobile!}</td>
								<td>${item.card!}</td>
								<td><a onclick="addRow(${item.userId!})" href="javascript:void(0)"><i class="Hui-iconfont"></i>修改</a></td>
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
	<script type="text/javascript"  src="${base}/static/base/js/expert/expertList.js"></script>
</body>
</html>
