<#assign base=rc.contextPath />
 <base id="base" href="${base}">
<@choiceSign.header />

<@choiceSign.left />
<style >
	tbody th{height:46px !important ;}
	.Wdate{padding: 0;}
	.select-box{height:30px;}
	.col-md-10{margin-top:10px;}
	.col-md-5{margin-bottom:10px;}
	 label{width:70px;}
	 #sampleNo{width:100%;height:100%;padding:0;border:none;}
	 .btn{ cursor:pointer;}
	 #tPage{overflow:scroll;height:400px;}
	 #tPagei .pt_th{padding:0;}
	 #tPage th input{display:inline-block;width:100%;height:100%;}
	 .oprt-bar{width: 100%;padding:0px;margin:10px 0 10px;}
	 .oprt-bar>.btn{ margin:0 5px 0; }
	 .top-oprt-bar{
	 	text-align: center;
    	display: block;
     }
	 #tbody .curTr{
		background-color:#e4e4e4;
	}
	#contractOfConditions .cl{
		margin-top: 0;
	}
	#query{
		margin-right:25px;
	}
</style>
<script type="text/javascript" src="${base}/static/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<input type="hidden" id="workGroupId" value="${workGroupId!}" />
<section class="Hui-article-box">
	<nav class="breadcrumb">
		<i class="Hui-iconfont"></i>
		<a href="/" class="maincolor">首页</a> 
		<span class="c-gray en">&gt;</span>签约管理
		<span class="c-gray en">&gt;</span>签约管理
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<div class="row cl btn-line-top mb-20" style="position:relative; margin: 0;">
				<div class="panel-body nopd">
					<form  method="post" class="form form-horizontal form-examiner-info" id="contractOfConditions">
						<div class="row cl">
							<ul>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">开始时间：</label>
									<div class="formControls">
										<input value="${contract.startTime!}" name="startTime" type="text" onfocus="WdatePicker()"  class="input-text Wdate">
									</div>
								</li>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">结束时间：</label>
									<div class="formControls">
										<input value="${contract.endTime!}" name="endTime" type="text" onfocus="WdatePicker()"  class="input-text Wdate">
									</div>
								</li>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">签约年度：</label>
									<div class="formControls" >
										<span class="select-box" style="width:;">
											<select name="signYear" class="select"  size="1">
												<option value="">请选择</option>
													<#if signyears?exists>
													<#list signyears as signyear>
														<option value="${signyear!}">${signyear!}</option>
													</#list>
													</#if>>	
											</select>
										</span>
									</div>
									<input type="hidden" class="input-text" value="${contract.signYear!}">
								</li>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">医保类型：</label>
									<div class="formControls skin-minimal" >
										<span class="select-box" style="width:;">
											<select name="insuranceType" class="select"  size="1">
													<option value="">请选择</option>
													<#if insuranceTypes?exists>
													<#list insuranceTypes as insuranceType>
														<option value="${insuranceType.code!}">${insuranceType.word!}</option>
													</#list>
													</#if>
											</select>
										</span>
									</div>
									<input type="hidden" class="input-text" value="${contract.insuranceType!}">
								</li>	
								<li class="col-md-6 col-lg-4">
									<label class="form-label">居民姓名：</label>
									<div class="formControls">
										<input value="${contract.trueName!}" name="trueName" type="text" class="input-text" value="" >
									</div>
								</li>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">身份证号：</label>
									<div class="formControls">
										<input  value="${contract.card!}" name="card" type="text" class="input-text" value="" >
									</div>
								</li>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">签约医生：</label>
									<div class="formControls" >
										<span class="select-box" style="width:;">
											<select name="expertId" class="select"  size="1">
												<option value="">请选择</option>
													<#if users?exists>
													<#list users as user>
														<option value="${user.userId!}">${user.userName!}</option>
													</#list>
													</#if>
											</select>
										</span>
									</div>
									<input type="hidden" class="input-text" value="${contract.expertId!}">
								</li>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">签约状态：</label>
									<div class="formControls skin-minimal" >
										<span class="select-box" style="width:;">
											<select name="status" class="select">
												<option value="">请选择</option>
												<option value="0">未审核</option>
												<option value="1">签约成功</option>
												<option value="2">签约失败</option>
												<option value="3">续约</option>
												<option value="4">解约</option>
												<option value="5">转介</option>
											</select>
										</span>
									</div>
									<input type="hidden" class="input-text" value="${contract.status!}">
								</li>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">是否续约：</label>
									<div class="formControls skin-minimal" >
										<span class="select-box" style="width:;">
											<select name="isRenew" class="select"  size="1">
													<option value="">请选择</option>
													<option value="1">是</option>
													<option value="0">否</option>
											</select>
										</span>
									</div>
									<input type="hidden" class="input-text" value="${contract.isRenew!}">
								</li>
							</ul>
						</div>
					</form>
				</div>
				<div class="col-md-12" >					
					<span class="top-oprt-bar">
						<button name=""  class="btn btn-success" type="button" id="query" "><i class="Hui-iconfont"></i> 查询</button>
						<input class=" btn btn-primary radius" type="button" id="export"  value="导出">
					</span>
				</div> 
			</div>
			<div class="col-md-7 oprt-bar" style="">		  
					<input class=" btn btn-primary radius " type="submit" id="renew" value="续约">
					<input class=" btn btn-primary radius " type="submit" id="referral" value="转介">
					<input class=" btn btn-primary radius " type="submit" id="cancel" value="解约" >
					
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
							<th width="5%">姓名</th>
							<th width="12%">身份证号</th>
							<th width="10%">手机号</th>
							<th width="6%">医保类型</th>
							<th width="7%">人群分类</th>
							<th width="5%">签约状态</th>
							<th width="8%">签约时间</th>
							<th width="5%">签约医生</th>
							<th width="5%">签约人</th>
							<th width="5%">签约年度</th>
							<th width="9%">签约开始时间</th>
							<th width="9%">签约结束时间</th>
							<th width="8%">签约类型</th>
							<th width="5%">是否续约</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<#if page.content?exists>
						<#list page.content as cont>
							<tr class="text-c" name="dicttr" data-contid="${cont.contractId!}">
								<td>${cont.trueName!}</td>
								<td>${cont.card!}</td>
								<td>${cont.mobile!}</td>
								<td>${cont.insuranceType!}</td>
								<td>${cont.crowdType!}</td>
								<#switch cont.status>
									<#case 0><td>未审核</td><#break>
									<#case 1><td>签约成功</td><#break>
									<#case 2><td>签约失败</td><#break>
									<#case 3><td>续约</td><#break>
									<#case 4><td>解约</td><#break>
									<#case 5><td>转介</td><#break>
								</#switch>
								<td>${cont.addTime!}</td>
								<td>${cont.doctName!}</td>
								<td>${cont.addPerson!}</td>
								<td>${cont.signYear!}</td>
								<td>${cont.startTime!}</td>
								<td>${cont.endTime!}</td>
								<td>${cont.signType!}</td>
								<#switch cont.isRenew>
									<#case 0><td>否</td><#break>
									<#case 1><td>是</td><#break>
								</#switch>
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
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
	var globalVar = {};
	globalVar.base = '${base}';
</script>
<script type="text/javascript" src="${base}/static/base/js/contract/contractManage.js"></script>
</body>
</html>