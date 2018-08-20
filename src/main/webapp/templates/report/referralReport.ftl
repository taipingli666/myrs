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
		<span class="c-gray en">&gt;</span>双向转诊报表
	</nav>
	<div class="Hui-article">
		<input type="hidden" value="${year!}" id="year">
		<input type="hidden" value="${refType!}" id="reftype">
		<article class="cl pd-20">
			<div class="text-c filter">
				<span class="select-box" style="width:150px;"> 
		        	<select class="select" name="year" id="years">
		        		<#if years?exists>
						<#list years as y>
							<option value="${y.YEAR!}">${y.YEAR!}</option>
						</#list>
						</#if>
		        	</select> 
	        	</span>
				<div class="doct-hidden" style="width:20px;height:1px;display: inline-block;margin:0 0 3px;padding:0;background-color:#D5D5D5;overflow:hidden;"></div>
				<span class="select-box doct-hidden" style="width:150px;"> 
		        	<select class="select" id="refType" name="refType">
		        		<option value="1">转出</option>
		        		<option value="2">转入</option>
		        	</select> 
	        	</span>
	        	<button id="search" class="btn btn-success" type="button" id="search"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			</div>
		<!-- 	<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
					<a class="btn btn-primary radius"  id="export" href="javascript:;">导出</a>
				</span>
			</div> -->
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th>月份</th>
							<th>人数</th>
						</tr>
					</thead>
					<tbody id="tbody" class="text-c">
						<#if list?exists>
						<#list list as obj>
							<tr>
								<td>${obj.month }</td>
								<td class="count">${obj.count }</td>
							</tr>
						</#list>
						</#if>
					</tbody>
				</table>
			</div>
		</article>
<@choiceSign.footer />
	</div>
	<script type="text/javascript">
		var globalVar = {};			//设置全局变量，添加base属性（项目路径）
		globalVar.base = '${base}';
		
		$(document).ready(function() {
			//数据回显
			$("#refType").val($("#reftype").val());
			$("#years").val($("#year").val());
			//最后一行汇总
			var sum = 0;
			$(".count").each(function() {
				sum += parseInt($(this).html());
			})
			//获取做后一行的序列号
			//var id = parseInt($("#tbody").find("tr:last td:first").html())+1;
			var str = '<tr><td>汇总</td><td>'+sum+'</td></tr>';
			$("#tbody").append(str);
		})
		$("#search").click(function() {
			//传参
			var refType = $("#refType").val();
			var year = $("#years").val();
			window.location = '${base}/reportStatistics/referralReport?refType='+refType+'&year='+year;
		})
	</script>
</body>
</html>
