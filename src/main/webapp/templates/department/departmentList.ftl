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
		<span class="c-gray en">&gt;</span>科室维护
	</nav>
	<input type="hidden" id="currentPage" value="${page.currentPage}" />
	<div class="Hui-article">
		<article class="cl pd-20">
		
		
			<input type="hidden" id="hosid-hidden" value="${hosid }"/>
			<div class="text-c">医院：
				<select  id="hosid" type="select" placeholder="医院" style="width:250px" class="select-box"  >
						<option data-hosnum="" value="0">--请选择--</option>
						<#if list?exists>
								<#list list as item>
										<option data-hosnum="${item.hosNum!}" value="${item.hosId!}">${item.hosName!}</option>
								</#list>
						</#if>
				</select>
				<input type="text" id="contents" placeholder="科室名称" style="width:250px" class="input-text" value="${contents!}" />
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
							<th width="26%">医院名称</th>    
							<th width="16%">科室名称</th>
							<th width="10%">状态</th>
							<th width="30%">备注</th>
							<th width="26%">操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<#if page.content?exists>
						<#list page.content as department>
							<tr class="text-c" name="departmenttr" data-departmentid="${department.departmentid?if_exists}">
								<td><input type="checkbox" value="" name="box2" ></td>
								<td>${department_index+1}</td>
								<td><#if list?exists>
										<#list list as item>
											<#if item.hosId! ==  department.hosid!>${item.hosName!}</#if>
										</#list>
								</#if></td>
								<td>${department.departmentname?if_exists}</td>
								<#switch department.distanceFlag>
									<#case "0"><td>未启用</td><#break>
									<#case "1"><td>已启用</td><#break>
								</#switch>
								<td>${department.remark!}</td>
								<td><a onclick="edit(${department.departmentid!})" href="javascript:void(0)"><i class="Hui-iconfont"></i>修改</a></td>
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
	<script type="text/javascript"  src="${base}/static/base/js/department/departmentList.js"></script>
</body>
</html>
