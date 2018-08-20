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
<section class="Hui-article-box">
	<nav class="breadcrumb">
		<i class="Hui-iconfont"></i>
		<a href="/" class="maincolor">首页</a> 
		<span class="c-gray en">&gt;</span>双向转诊管理
		<span class="c-gray en">&gt;</span>预约检查列表
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<input type="hidden" id="hosId" value="${hosId }">
			<!-- 搜索表单区域start -->
			<div class="row cl btn-line-top mb-20" style="position:relative; margin: 0;">
				<div class="panel-body nopd">
					<form  method="post" class="form form-horizontal form-examiner-info" id="contractOfConditions">
						<div class="row cl">
							<ul>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">开始时间：</label>
									<div class="formControls">
										<input value="${itemListParam.startTime! }" name="startTime" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss' })"  class="input-text Wdate">
									</div>
								</li>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">结束时间：</label>
									<div class="formControls">
										<input value="${itemListParam.endTime! }" name="endTime" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss' })"  class="input-text Wdate">
									</div>
								</li>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">转入/转出：</label>
									<div class="formControls" >
										<span class="select-box" style="width:;">
											<select id="refType" name="refType" class="select"  size="1">
												<option value="">请选择</option>
												<option value="1">转出</option>
												<option value="2">转入</option>
											</select>
										</span>
									</div>
									<input type="hidden" class="input-text" value="${itemListParam.refType! }">
								</li>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">姓名：</label>
									<div class="formControls skin-minimal" >
										<input  name="name" type="text" class="input-text" value="${itemListParam.name! }" >
									</div>
									<input type="hidden" class="input-text" value="">
								</li>	
								<li class="col-md-6 col-lg-4">
									<label class="form-label">身份证号：</label>
									<div class="formControls">
										<input  name="idCard" type="text" class="input-text" value="${itemListParam.idCard! }" >
									</div>
								</li>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">手机号：</label>
									<div class="formControls">
										<input   name="tel" type="text" class="input-text" value="${itemListParam.tel! }" >
									</div>
								</li>
							</ul>
						</div>
					</form>
				</div>
				<div class="col-md-12" >					
					<span class="top-oprt-bar">
						<button name=""  class="btn btn-success" type="button" id="search" "><i class="Hui-iconfont"></i> 查询</button>
						<input class=" btn btn-primary radius" type="button" id="export"  value="导出">
					</span>
				</div> 
			</div>
			<!-- 搜索表单区域end -->
			<div class="col-md-7 oprt-bar" style="">		  
					<span class="r">共有数据：<strong>
				</strong>${page.totalRecord}条</span>
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="6%">姓名</th>
							<th width="15%">身份证</th>
							<th width="5%">性别</th>
							<th width="10%">联系电话</th>
							<th width="7%">健康卡号</th>
							<th width="8%">检查项目</th>
							<th width="5%">金额(元)</th>
							<th width="5%">初步诊断</th>
							<th width="13%">开始时间</th>
							<th width="13%">结束时间</th>
						</tr>
					</thead>
					<tbody id="tbody" class="text-c">
					<#list page.content as obj>
					<tr>
						<td>${obj.name! }</td>
						<td>${obj.id_card! }</td>
						<td>${obj.sex! }</td>
						<td>${obj.phone_number! }</td>
						<td>${obj.card_id! }</td>
						<td>${obj.item_name! }</td>
						<td>${obj.price! }</td>
						<td>${obj.diagnose! }</td>
						<td>${obj.start_time! }</td>
						<td>${obj.end_time! }</td>
					</tr>
					</#list>
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
<script type="text/javascript" src="${base }/static/base/js/external/checkList.js"></script>
</body>
</html>