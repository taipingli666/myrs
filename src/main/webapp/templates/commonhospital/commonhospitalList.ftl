<#assign base=rc.contextPath />
 <base id="base" href="${base}">
 
 <script> var contextPath = '${base}';</script>
<script type="text/javascript" src="${base}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript"  src="${base}/static/base/js/hospital/hospitalList.js"></script>
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
		<article class="cl pd-20">
			<form method="post" id="listForm" action="${base}/commonhospital/list">
			<div class="text-c">
				<input type="hidden" id="page" name="page"/>
				<input type="hidden" id="areaCode" name="areaCode" value="${areaCode!}"/>
				<input type="hidden" id="areaPId" name="areaPId" value="${areaPId!}"/>
				<input type="hidden" id="areaLevel" name="areaLevel" value="${areaLevel!}"/>
				<input type="text" name="hosName" id="contents" placeholder="医院名称" style="width:250px" class="input-text" value="${hospital.hosName!}" />
				<button onclick="refresh()" id="search" class="btn btn-success" type="button" id="search"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			</div>
			</form>
			<div class="f-l" style="width: 100%">
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
								<th width="10%">医院id</th>
								<th width="10%">机构编码</th>
								<th width="20%">医院名称</th>
								<th width="15%">上级区域</th>
								<th width="10%">区域等级</th>
								<th width="10%">机构类型</th>
                                <th width="15%">远程会诊</th>
								<th width="">操作</th>
							</tr>
						</thead>
						<tbody id="tbody">
							<#if page.content?exists>
							<#list page.content as item>
								<tr class="text-c" name="dicttr">
									<td><input type="checkbox" data-hosId="${item.hosId!}" value="" name="box2" ></td>
									<td>${item.hosId!}</td>
									<td>${item.hosNum!}</td>
									<td>${item.hosName!}</td>
									<td>${item.parentCode!}</td>
									<td>${item.level!}</td>
									<td>${item.hosType!}</td>
                                    <td>
										<#if item.consultation! =='0'>
                                            <a class="btn btn-success "onclick="setConsultation(${item.hosId!},'1')" href="javascript:void(0)"><i class="Hui-iconfont"></i>已启用</a>
										<#else >
                                            <a class="btn btn-warning "onclick="setConsultation(${item.hosId!},'0')" href="javascript:void(0)"><i class="Hui-iconfont"></i>已停用</a>
										</#if>
									</td>
									<td>
										<#if role! == 1 >
                                            <a class="btn btn-primary "onclick="addRow(${item.hosId!})" href="javascript:void(0)"><i class="Hui-iconfont"></i>修改</a>
										</#if>
									</td>
								</tr>
							</#list>
							</#if>
						</tbody>
					</table>
					<@choiceSign.signpage page/>
					</div>
		</article>
	 </div>
</section>
