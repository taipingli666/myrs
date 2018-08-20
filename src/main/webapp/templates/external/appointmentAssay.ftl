<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<#assign base=rc.contextPath />
 <base id="base" href="${base}">
<@choiceSign.header />
<@choiceSign.left />
<html>
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/external/appointment-check.css"/>
<body>

<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span>
		双向转诊管理
		<span class="c-gray en">&gt;</span>
		预约化验
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
		<div class="pd-20">
			<div class="navBox">
				  <label for="">
				  <img src="${base}/static/lib/h-ui.admin/images/basic-news/alist_2.png" alt="">
				  </label>
			</div>
  			<div class="appointment">
			<form action="${base}/assay/assayFinish.do" method="post" id="form">
			<!--form提交InspectInfo赋值-->
  			<input type="hidden" value="${hosCode}" id="hosCode" name="hosCode">
  			<input type="hidden" value="" id="itemCode" name="itemCode">
  			<input type="hidden" value="" id="itemName" name="itemName">
  			<input type="hidden" value="" id="itemPrice" name="itemPrice">
  			<input type="hidden" value="assay" id="appointmentType" name="type">
  			<input type="hidden" value="" id="cost" name="cost">
  			<input type="hidden" value="${patientInfo.patientName! }" id="name" name="name">
  			<input type="hidden" value="${patientInfo.patientAge! }" id="age" name="age">
  			<input type="hidden" value="${patientInfo.patientPhone! }" id="phoneNumber" name="phoneNumber">
  			<input type="hidden" value="${diag! }" id="diagnose" name="diagnose">
  			<input type="hidden" value="${icd10! }" id="icd10" name="icd10">
  			<!-- 身份证 -->
  			<input type="hidden" value="${patientInfo.patientIdCard! }" id="idCard" name="idCard">
  			<!-- 健康卡 -->
  			<input type="hidden" value="${patientInfo.patientMediumCode! }" id="cardId" name="cardId">
  			<input type="hidden" value="${patientInfo.patientGender! }" id="sex" name="sex">
				<dl>
					<dt>预约日期：</dt>
					<dd>
				       <input type="text" readonly="readonly" name="appointmentTime" id="appointmentTime" class="Wdate" 
				       onFocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" 
				       style="width:160px;padding:4px;font-size:14px;">
					</dd>	
				</dl>
				<div class="appointment_select">
					<!-- 选择大类 -->
					 <div class="appointment_office" style="width:225px;">
						<table class="office" cellpadding='0' cellspacing='0' border='0'>
						<tr style="position:relative;z-index:20">
								<th colspan="2" class="sel_title">选择大类</th>
							</tr>
							<tr id="ksmc">
								<td class="td_first"></td>
								<td style="font-weight:bold;">名称</td>
							</tr>
						</table>
					 </div>
					  <!-- 检查样本 -->
					 <div class="appointment_sample" style="width:225px;">
						<table class="office" cellpadding='0' cellspacing='0' border='0'>
							 <tr>
								<th colspan="2" class="sel_title">化验样本</th>
							</tr>
							<tr id="sampleTr">
								<td class="td_first"></td>
								<td style="font-weight:bold;">名称</td>
							</tr>
						</table>
					 </div>
					 <!-- 选择类别 -->
					 <div class="appointment_kinds"style="width:225px;display:none">
						<table class="office kinds" cellpadding='0' cellspacing='0' border='0'>
					 		<tr>
								<th colspan="2" class="sel_title">选择小类</th>
							</tr>
							<tr id="xzlb">
								<td class="td_first"></td>
								<td style="font-weight:bold;">名称</td>
							</tr>
						</table>
					 </div>
					 <!-- 化验项目 -->
					 <div class="appointment_item" style="width:225px;display:none">
						<table class="office" cellpadding='0' cellspacing='0' border='0'>
							 <tr>
								<th colspan="2" class="sel_title">化验项目</th>
							</tr>
							<tr id="assayItemTr">
								<td class="td_first"></td>
								<td style="font-weight:bold;">名称</td>
							</tr>
						</table>
					 </div>
				</div>
				<!-- 所选项目统计 -->
				<div class="selected_items"style="width:225px">
					<span class="selected_title">所选项目统计：</span>
					<table border="0" cellpadding="0" cellspacing="0" class="selected_detail">
						<tr>
							<th width="200">选择大类</th>
							<th width="200">选择小类</th>
							<th width="200">选择样本</th>
							<th>化验项目名称</th>
							<th width="130">金额(元)</th>
							<th width="100">操作</th>
						</tr>				 
					</table>
					 <div class="all_num">金额总计：<span id="price">0.0</span>元</div>
				</div>
				</form>
  			</div>
			<div class="submit" id="submit">
				<a>提交</a>
			</div>
		</div>
		<@choiceSign.footer />
	</div>
</section>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base}/static/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript">
	var globalVar = {};
	globalVar.base = '${base}';
</script>
<!--/请在上方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base}/static/base/js/external/appointmentAssay.js"></script>
</body>
</html>