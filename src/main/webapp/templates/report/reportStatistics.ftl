<#assign base=rc.contextPath />
 <base id="base" href="${base}">
<@choiceSign.header />
<@choiceSign.left />
<style>
	table{
		cursor:pointer;
	}
	.Wdate{width:150px;}
	#search{margin-left: 15px;}
</style>
<script type="text/javascript" src="${base}/static/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<section class="Hui-article-box">
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i>
		<a href="${base}/others.htm" class="maincolor">首页</a> 
		<span class="c-gray en">&gt;</span>系统报表
	</nav>
	<div class="Hui-article">
		<input type="hidden" value="${level!}" id="level">
		<input type="hidden" value="${subordinateId!}" id="subordinateIdFormBackstage">
		<article class="cl pd-20">
			<div class="text-c filter">
				<span class="select-box" style="width:150px;"> 
		        	<select class="select" name="signingTime" id="signingTime">
		        		<#if distinctYears?exists>
						<#list distinctYears as year>
							<option value="${year!}">${year!}</option>
						</#list>
						</#if>
		        	</select> 
	        	</span>
				<div class="doct-hidden" style="width:20px;height:1px;display: inline-block;margin:0 0 3px;padding:0;background-color:#D5D5D5;overflow:hidden;"></div>
				<span class="select-box doct-hidden" style="width:150px;"> 
		        	<select class="select" id="subordinateId">
		        		<option value="${subordinateId!}">请选择</option>
		        		<#if selects?exists>
						<#list selects as option>
							<#if level  == 2>
								<option value="${option.userId}">${option.userName}</option>
							<#elseif level  == 3>
							 	<option value="${option.hosId}">${option.hosName}</option>
							<#else>
							 	<option value="${option.areaId}">${option.areaName}</option>
							</#if>
						</#list>
						</#if>
		        	</select> 
	        	</span>
	        	<button id="search" class="btn btn-success" type="button" id="search"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			</div>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
					<a class="btn btn-primary radius"  id="export" href="javascript:;">导出</a>
				</span>
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="5%">序号</th>
							<th width="23%">日期</th>
							<th width="12%">签约人数</th>
							<th width="12%">签约率</th>
							<th width="12%">续约人数</th>
							<th width="12%">续约率</th>
							<th width="12%">重点人群签约人数</th>
							<th width="12%">重点人群签约率</th>
						</tr>
					</thead>
					<tbody id="tbody" class="text-c">
					<tr>
						<td>1</td>
						<td>2017-10</td>
						<td>0</td>
						<td>0%</td>
						<td>0</td>
						<td>0%</td>
						<td>0</td>
						<td>0%</td>
					</tr>
					<tr>
						<td>2</td>
						<td>2017-11</td>
						<td>0</td>
						<td>0%</td>
						<td>0</td>
						<td>0%</td>
						<td>0</td>
						<td>0%</td>
					</tr>
					<tr>
						<td>4</td>
						<td>2017-12</td>
						<td>0</td>
						<td>0%</td>
						<td>0</td>
						<td>0%</td>
						<td>0</td>
						<td>0%</td>
					</tr>
					<tr>
						<td>5</td>
						<td>2017汇总</td>
						<td>0</td>
						<td>0%</td>
						<td>0</td>
						<td>0%</td>
						<td>0</td>
						<td>0%</td>
					</tr>
					</tbody>
				</table>
			</div>
		</article>
<@choiceSign.footer />
	</div>
	<script type="text/javascript">
		var globalVar = {};			//设置全局变量，添加base属性（项目路径）
		globalVar.base = '${base}';
	</script>
	<script type="text/javascript"  src="${base}/static/base/js/report/reportStatistics.js"></script>
</body>
</html>
