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
		<span class="c-gray en">&gt;</span>用户管理
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<form method="post" id="listForm" action="${base}/user/display">
			<div class="text-c">
				<input type="hidden" id="page" name="page"/>
                <input type="text" name="hosName" id="hosName" placeholder="医院" style="width:250px" class="input-text" value="${user.hosName!}" />
				<input type="text" name="userName" id="contents" placeholder="账号" style="width:250px" class="input-text" value="${user.userName!}" />
				<button onclick="refresh()" id="search" class="btn btn-success" type="button" id="search"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			</div>
			</form>
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
					<thead>
						<tr class="text-c">
							<th width="4%"><input type="checkbox" name="box1" value=""></th>
							<th width="6%">编号</th>
							<th width="10%">账号</th>
							<th width="20%">所属机构</th>
							<th width="10%">真实姓名</th>
							<th width="5%">性别</th>
							<th width="10%">手机</th>
							<th width="18%">身份证号</th>
							<th width="">操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<#if page.content?exists>
						<#list page.content as item>
							<tr class="text-c" name="dicttr">
								<td><input type="checkbox" data-userid="${item.userId!}" value="" name="box2" ></td>
								<td>${item.userId!}</td>
								<td>${item.userName!}</td>
								<td>${item.hosName! }</td>
								<td>${item.trueName!}</td>
								<td>
										<#if item.sex?? && item.sex==1>
											男
										<#elseif item.sex?? && item.sex==0> 
										女
										<#else> 
										</#if>
								</td>
								<td>${item.tel!}</td>
								<td>${item.card!}</td>
								<td>
								<a onclick="addRow(${item.userId!})" href="javascript:void(0)"><i class="Hui-iconfont"></i>修改资料</a>
								<a onclick="addRow(${item.userId!},'1')" href="javascript:void(0)"><i class="Hui-iconfont"></i>修改密码</a>
								<a onclick="role(${item.userId!},'${item.userName!}')" href="javascript:void(0)"><i class="Hui-iconfont"></i>设置角色</a>
								</td>
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
</section>
	<script>
		var contextPath = '${base}';
	</script>
	<script type="text/javascript"  src="${base}/static/base/js/user/userList.js"></script>
</body>
</html>
