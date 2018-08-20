<#assign base=rc.contextPath />
<base id="base" href="${base}">

<script type="text/javascript"  src="${base}/static/base/js/population/commonPopulationList.js"></script>
<script type="text/javascript" src="${base}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${base}/static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base}/static/base/js/main.js"></script> 
<script type="text/javascript" src="${base}/static/base/js/admin.page.js"></script> 
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/min.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/admin.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/base/skin/blue/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/style.css" />
<style>
	table{
		cursor:pointer;
	}
	.ztree li span {
	    max-width: 199px;
	    display: inline-block;
	    white-space: nowrap ;
	    overflow: hidden;
	    text-overflow: ellipsis;
    }
</style>
<section class="Hui-article-box" style="left:292px;top:0">
	<div class="Hui-article" style="top:0">
		
			<div class="cl pd-20">
				
				<form method="post" id="listForm" action="${base}/commonpopulation/show2">
				<div class="text-c">
					<input type="hidden" id="page" name="page"/>
					<input type="hidden" id="areaCode" name="areaCode" value="${areaCode!}"/>
					<input type="text" name="searchValue" id="searchValue" placeholder="区域编码" style="width:250px" class="input-text" value="${searchValue!}">
					<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜区域人口</button>
				</div>
				</form>
				<div class="f-l" style="width: 100%">
					<div class="cl pd-5 bg-1 bk-gray mt-20"> 
					   <span class="l">
						<a class="btn btn-primary radius" onclick="product_add(0)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加</a>
						<a href="javascript:;" onclick="product_del()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> 
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
									<th width="40"><input name="box1" type="checkbox" value=""></th>
									<th width="40">ID</th>
									<th width="200">行政区划编码</th>
									<th width="100">统计人口</th>
									<th width="100">统计年份</th>
									<th width="">操作</th>
								</tr>
							</thead>
							<tbody id="tbody">
								<#if page.content?exists>
								<#list page.content as item>
									<tr class="text-c" name="dicttr">
										<td><input type="checkbox" data-area="${item.pId!}" value="" name="box2" ></td>
										<td>${item.pId!}</td>
										<td>${item.areaCode!}</td>
										<td>${item.pNumber!}</td>
										<td>${item.countYear!}</td>
										<td><a onclick="product_add(${item.pId!})" href="javascript:void(0)"><i class="Hui-iconfont"></i>修改</a></td>
									</tr>
								</#list>
								</#if>
							</tbody>
						</table>
					<@choiceSign.signpage page/>
					</div>
				</div>
			</div>
		</div>

	</div>
</section>


</div>
<script>
	var contextPath = '${base}';
</script>
<script>
</body>
</html>
