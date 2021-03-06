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
		<span class="c-gray en">&gt;</span>套餐设置
	</nav>
	<input type="hidden" id="currentPage" value="${page.currentPage}" />
	<div class="Hui-article">
		<article class="cl pd-20">
			<div class="text-c">
				<input type="text" id="className" placeholder="名称" style="width:250px" class="input-text" value="${className!}" />
				<button id="search" class="btn btn-success" type="button" id="search"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			</div>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
					<a class="btn btn-primary radius" data-title="添加" _href="article-add.html" id="add" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 新增</a>
					<a href="javascript:;" class="btn btn-danger radius" id="remove"><i class="Hui-iconfont">&#xe6e2;</i> 删除</a>
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
							<th width="6%">序号</th>
							<th width="20%">类型</th>
							<th width="26%">名称</th>
							<th width="30%">备注</th>
							<th width="26%">操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<#if page.content?exists>
						<#list page.content as package>
							<tr class="text-c" name="package" data-packageId="${package.packageId?if_exists}">
								<td><input type="checkbox" value="" name="box2" ></td>
								<td>${package_index+1}</td>
								<td><#if list?exists>
										<#list list as item>
											<#if item.code! == package.classID!>${item.word!}</#if>
										</#list>
								</#if></td>
								<td>${package.className!}</td>
								<td>${package.remark!}</td>
								<td><a onclick="edit(${package.packageId!})" href="javascript:void(0)"><i class="Hui-iconfont"></i>修改</a></td>
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
	<script type="text/javascript">
		var globalVar = {};			//设置全局变量，添加base属性（项目路径）
		globalVar.base = '${base}';
	</script>
	<script type="text/javascript"  src="${base}/static/base/js/common/commonpackageList.js"></script>
</body>
</html>
