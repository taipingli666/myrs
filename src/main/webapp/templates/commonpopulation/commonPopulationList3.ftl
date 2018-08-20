<#assign base=rc.contextPath />
<base id="base" href="${base}">
<html>
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
<body>
<section class="Hui-article-box" style="left:292px;top:0">
	<div class="Hui-article" style="top:0">
			<div class="cl pd-20">
				
				<form method="post" id="listForm" action="${base}/commonpopulation/show3.do">
					<div class="text-c">
						<input type="hidden" id="page" name="page"/>
						<input type="hidden" id="areaCode" name="areaCode" value="${areaCode!}"/>
						<input type="hidden" id="yearval" name="yearval" value="${selectYear!}"/>
						<span class="select-box" style="width:150px;">
				        	<select class="select" name="year" id="year">
								<#if yearList?exists>
									<#list yearList as year>
                                        <option value="${year!}">${year!}</option>
									</#list>
								</#if>
				        	</select>
		        		</span>
		        		<input type="text" id="areaName" name="areaName" placeholder="区域名称" style="width:250px" class="input-text" value="" />
						<button name="" id="search" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
					</div>
				</form>
				<div class="f-l" style="width: 100%">
					<#--<div class="cl pd-5 bg-1 bk-gray mt-20">
					   <span class="r">共有数据：<strong>
					</strong> 条</span>
                </div>-->
					<div class="mt-20">
						<table class="table table-border table-bordered table-bg table-hover table-sort">
							<thead>
								<tr class="text-c">
                                    <th width="100">年份</th>
                                    <th width="100">地区名称</th>
									<th width="100">人口总数</th>
									<th width="100">签约人数</th>
									<th width="100">签约率</th>
									<th width="100">续约人数</th>
									<th width="100">续约率</th>
									<th width="100">重点人群签约人数</th>
									<th width="100">重点人群签约率</th>
								</tr>
							</thead>
							<tbody id="tbody">
								<#if list?exists>
								<#list list as item>
									<tr class="text-c" name="dicttr">
                                        <td>${item.year!}</td>
										<td>${item.areaName!}</td>
										<td>${item.peopleNumber!}</td>
										<td>${item.signingNum!}</td>
										<td>${item.signatureRate!}</td>
										<td>${item.renewNum!}</td>
										<td>${item.renewRate!}</td>
										<td>${item.focusGroupsNum!}</td>
										<td>${item.focusGroupsRate!}</td>
									</tr>
								</#list>
								</#if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
</section>
</body>
<script>
	$(document).ready(function() {
	    var year = $("#yearval").val();
	    if (year != '') {
            $("#year").val(year);
		}
	})
</script>

</html>

