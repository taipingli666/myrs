<!DOCTYPE HTML>
<#assign base=rc.contextPath />
 <base id="base" href="${base}">
<@choiceSign.header />

<@choiceSign.left />

<!-- 将传到页面的json字符串定义为一个json对象 -->
<#assign json="${jsonString }"?eval>
<html>
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/external/appointment-check.css"/>
<body>
<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span>
		双向转诊管理
		<span class="c-gray en">&gt;</span>
		预约检查
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<form action="${base }/appointmentCheck/saveCheckList" method="post">
		<!-- 隐藏存值区 start -->
			<!-- 医院编码 -->
			<input type="hidden" id="hosCode" name="hosCode" value="${hosCode }"/>
			<!-- 病人卡号 -->
			<input type="hidden" id="cardId" name="cardId" value="${patientInfo.patientMediumCode }"/>
			<!-- 预约类型 -->
			<input type="hidden" id="type" name="type" value="check"/>
			<!-- 检查总费用 -->
			<input type="hidden" id="cost" name="cost" value=""/>
			<!-- 初步诊断名称 -->
			<input type="hidden" id="diagnose" name="diagnose" value="${diag }"/>
			<!-- 初步诊断编码 -->
			<input type="hidden" id="icd10" name="icd10" value="${icd10 }"/>
			<!-- 姓名 -->
			<input type="hidden" id="name" name="name" value="${patientInfo.patientName }"/>
			<!-- 性别 -->
			<input type="hidden" id="sex" name="sex" value="${patientInfo.patientGender }"/>
			<!-- 年龄 -->
			<input type="hidden" id="age" name="age" value="${patientInfo.patientAge }"/>
			<!-- 手机号 -->
			<input type="hidden" id="phoneNumber" name="phoneNumber" value="${patientInfo.patientPhone }"/>
			<!-- 身份证号 -->
			<input type="hidden" id="idCard" name="idCard" value="${patientInfo.patientIdCard }"/>
		<!-- 隐藏存值区 end -->
		<div class="Hui-article">
			<div class="pd-20">
				<div class="navBox">
					  <label for="">
							<img src="${base }/static/lib/h-ui.admin/images/basic-news/list_2.png" alt="">
					  </label>
				</div>
	  			<div class="appointment">
					<!-- <dl>
						<dt>预约日期：</dt>
						<dd>
					       <input type="text" name="appointmentTime"  class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" style="width:160px;padding:4px;font-size:14px;">
						</dd>	
					</dl> -->
					<div class="appointment_select">
						<!-- 选择检查大类-->
						 <div class="appointment_office">
							<table class="office" cellpadding='0' cellspacing='0' border='0'>
								<tr style="position:relative;z-index:20">
									<th colspan="2" class="sel_title">选择检查大类</th>
								</tr>
								<tr>
									<td class="td_first"></td>
									<td style="font-weight:bold;">大类名称</td>
								</tr>
								<#list json.checkClassList as checkClass>
									<tr>
										<td class="td_first">
											<input type="checkbox" class="sin_point" value="${checkClass.checkClassCode }">
										</td>
										<td>${checkClass.checkClassName }</td>
									</tr>
								</#list>
							</table>
						 </div>
						 <!-- 选择类别 -->
						 <div class="appointment_kinds" style="display:none">
							<table id="miniCheckClassList" class="office kinds" cellpadding='0' cellspacing='0' border='0'>
								<tr>
									<th colspan="2" class="sel_title">选择类别</th>
								</tr>
								<tr>
									<td class="td_first"></td>
									<td style="font-weight:bold;">名称</td>
								</tr>
							</table>
						 </div>
						 <!-- 检查项目 -->
						 <div class="appointment_item item" style="display:none">
							<table id="checkItemList" class="office" cellpadding='0' cellspacing='0' border='0'>
								<tr>
									<th colspan="2" class="sel_title">检查项目</th>
								</tr>
								
								<tr>
									<td class="td_first"></td>
									<td style="font-weight:bold;">名称</td>
								</tr>
							</table>
						 </div>
					</div>
					<!-- 所选项目统计 -->
					<div class="selected_items">
						<span class="selected_title">所选项目统计：</span>
						<table border="0" cellpadding="0" cellspacing="0" class="selected_detail">
							<tr>
								<th width="100">大类名称</th>
								<th width="100">小类名称</th>
								<th width="200">检查项目名称</th>
								<th>预约开始时间</th>
								<th>预约结束时间</th>
								<th width="130">金额</th>
								<th width="100">操作</th>
							</tr>				 
						</table>
						 <div class="all_num">金额总计：<span id="cost-show"></span></div>
					</div>
	  			</div>
				<div class="submit">
					<a href="javascript:;" id="submit">提交</a>
				</div>
			</div>
			<@choiceSign.footer />
		</div>
	</form>
</section>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${base }/static/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base }/static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.page.js"></script>
<!--/_footer /作为公共模版分离出去-->

<script type="text/javascript">
	var globalVar = {};
	globalVar.base = '${base}';
</script>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base }/static/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base }/static/base/js/external/appointmentCheck.js"></script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>