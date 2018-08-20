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
		<span class="c-gray en">&gt;</span>预约化验列表
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<div class="text-c">
			<input type="hidden" value="${page.currentPage! }" id="pageIndex">
			<div class="row cl btn-line-top mb-20" style="position:relative; margin: 0;">
				<form action="${base}/assay/assayList"id="form" class="form form-horizontal form-examiner-info">
					<div class="row cl">
						<ul>
							<li class="col-md-6 col-lg-4">
								<label class="form-label">预约时间：</label>
								<div class="formControls">
									<input value="${itemListParam.appointmentTime!}" name=appointmentTime type="text" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'})"  class="input-text Wdate">
								</div>
							</li>
							<li class="col-md-6 col-lg-4">
								<label class="form-label">姓名：</label>
								<div class="formControls">
									<input value="${itemListParam.name!}" name="name" type="text" class="input-text">
								</div>
							</li>
							<li class="col-md-6 col-lg-4">
								<label class="form-label">身份证号：</label>
								<div class="formControls">
									<input  value="${itemListParam.idCard!}" name="idCard" type="text" class="input-text">
								</div>
							</li>
							<li class="col-md-6 col-lg-4">
								<label class="form-label">手机号：</label>
								<div class="formControls">
									<input  value="${itemListParam.tel!}" name="tel" type="text" class="input-text">
								</div>
							</li>
							<li class="col-md-6 col-lg-4">
								<label class="form-label">转入转出：</label>
								<div class="formControls">
									<span class="select-box">
											<select id="refType" name="refType" class="select">
												<option value="">请选择</option>
												<option value="1">转出</option>
												<option value="2">转入</option>
											</select>
										</span>
									<input type="hidden" class="reftype" value="${itemListParam.refType!}">
								</div>
							</li>
						</ul>
					</div>
					<button id="search" class="btn btn-success" type="button" id="search"><i class="Hui-iconfont">&#xe665;</i>查询</button>
				</form>
				</div>
			</div>
			<div class="col-md-7 oprt-bar" style="">		  
					<span class="r">共有数据：<strong>
				</strong>${page.totalRecord}条</span>
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="3%">序号</th>
							<th width="5%">姓名</th>
							<th width="11%">身份证</th>
							<th width="5%">年龄</th>
							<th width="5%">性别</th>
							<th width="8%">联系电话</th>
							<th width="8%">健康卡号</th>
							<th width="5%">化验项目</th>
							<th width="5%">金额(元)</th>
							<th width="8%">初步诊断</th>
							<th width="10%">预约时间</th>
						</tr>
					</thead>
					<tbody id="tbody" class="text-c">
					<#list page.content as obj>
					<tr>
						<td>${obj_index+1 }</td>
						<td>${obj.name! }</td>
						<td>${obj.id_card! }</td>
						<td>${obj.age! }</td>
						<td>${obj.sex! }</td>
						<td>${obj.phone_number! }</td>
						<td>${obj.card_id! }</td>
						<td>${obj.item_name! }</td>
						<td>${obj.price! }</td>
						<td>${obj.diagnose! }</td>
						<td>${obj.appointment_time! }</td>
					</tr>
					</#list>
					</tbody>
				</table>
				<@choiceSign.signpage page/>
			</div>
		</article>
<@choiceSign.footer />
	</div>
</section>
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
	var globalVar = {};
	globalVar.base = '${base}';
	//当前页码
	var pageIndex = $("#pageIndex").val();
	$(document).ready(function(){
		//转入还是转出赋值
		$("#refType").val($(".refType").val());
	})
	//$('#search').on('click',doSearch);
	$('#search').click(function() {
		doSearch(1);
	});
	//search
	function doSearch(pageNumber) {
		var $form = $('#form');
 		url = globalVar.base+'/assay/assayList?';
	 	if($("#form").valid()){	
			url += 'pageNum=' + pageNumber +'&';
			url += $form.serialize();
			window.location = url;
	 	}
	}
	//翻页
	function changePage(pageNumber){
		doSearch(pageNumber);
	}
</script>
</body>
</html>