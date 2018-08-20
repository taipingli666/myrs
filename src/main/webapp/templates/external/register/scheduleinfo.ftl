<#assign base=rc.contextPath />
<!DOCTYPE HTML>
<html>
<head>
 	<base id="base" href="${base}">
	<meta charset="utf-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${base}/static/lib/html5.js"></script>
	<script type="text/javascript" src="${base}/static/lib/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="${base}/static/base/css/min.css" />
	<link rel="stylesheet" type="text/css" href="${base}/static/base/css/admin.css" />
	<link rel="stylesheet" type="text/css" href="${base}/static/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${base}/static/base/skin/blue/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="${base}/static/base/css/style.css" />
	<style>
		.lable1{
			margin-left:12%;
		}
	</style>
	<!--[if IE 6]>
	<script type="text/javascript" src="http://static/lib.net/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
	<!--/meta 作为公共模版分离出去-->
	<script type="text/javascript" src="${base}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="${base}/static/base/js/main.js"></script> 
	<script type="text/javascript" src="${base}/static/base/js/admin.page.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/My97DatePicker/4.8/WdatePicker.js"></script>
</head>
<body>
	<div class="page-container">
	<form id="formOperated">
		<input type="hidden" id="scheduleInfoId" name="id" value="${scheduleInfo.id!}"/>
		<input type="hidden" id="hosId" name="hosCode" value="${hosId!}"/>
        <input type="hidden" id="hosName" name="hosName" value="${hosName!}"/>
		<input type="hidden" id="AM"  value="${AM!}"/>
		<input type="hidden" id="PM"  value="${PM!}"/>
        <div class="row cl" style="margin-top:1%;">
            <label class="form-label col-xs-4 col-sm-2 lable1">医院：</label>
            <div class="formControls col-xs-4 col-sm-2">${hosName!}</div>
        </div>
        <div class="row cl" style="margin-top:1%;">
            <label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>排班分类：</label>
            <div class="formControls col-xs-4 col-sm-2">
				<span class="select-box">
				<select class="select" id="scheduleType" name="scheduleType" datatype="*" nullmsg="请选择！" onchange="changeScheduleType()" >
					<option value="" >未选择分类</option>
			        <#--<option value="0" <#if scheduleInfo.scheduleType?? && scheduleInfo.scheduleType== "0" >selected</#if>>预约挂号</option>-->
			        <option value="1" <#if scheduleInfo.scheduleType?? && scheduleInfo.scheduleType== "1" >selected</#if>>远程会诊</option>
				</select>
				</span>
            </div>
        </div>
        <div class="row cl" style="margin-top:1%;">
            <label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>科室：</label>
            <div class="formControls col-xs-4 col-sm-2">
                <span class="select-box">
                <select  name="deptCode" id="deptCode" class="select" datatype="*" nullmsg="请选择！" onchange="javascrpit:hiddenChange(this,'2')" >
                    <option value="0">未加载到数据</option>
                    <#if scheduleInfo.deptCode??><option value="${scheduleInfo.deptCode!}" selected>${scheduleInfo.deptName!}</option></#if>

                </select>
                <!-- 保存科室名称 -->
                <input name="deptName" id="deptName"  type="hidden" value="${scheduleInfo.deptName!}"/>
				</span>
            </div>
        </div>
        <div class="row cl" style="margin-top:1%;">
            <label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>医生：</label>
            <div class="formControls col-xs-4 col-sm-2">
                <span class="select-box">
                <select  name="doctorCode" id="doctorCode" class="select" datatype="*" nullmsg="请选择！" onchange="javascrpit:hiddenChange(this,'1')" >
                    <option value="0">未加载到数据</option>
                    <#if scheduleInfo.doctorCode??><option value="${scheduleInfo.doctorCode!}" selected>${scheduleInfo.doctorName!}</option></#if>
                </select>
                <!-- 保存出诊医生姓名 -->
                <input name="doctorName" id="doctorName" type="hidden" value="${scheduleInfo.doctorName!}"/>
				</span>
            </div>
        </div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>日期：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="workDate" name="workDate"  type="text" datatype="*" nullmsg="请选择！" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd',onpicking: getWeek,minDate:'%y-%M-{%d+1}' })"  class="input-text Wdate" value="${(scheduleInfo.workDate?string("yyyy-MM-dd"))!} ">
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1">星期：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input type="text" readonly="true" id="J_Week" class="input-text"/>
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>午别：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<span class="select-box">
				<select class="select" id="workPeriod" name="workPeriod" datatype="*" nullmsg="请选择！" onchange="changeWuBie()" >
					<option value="" >未选择午别</option>
			        <option value="AM" <#if scheduleInfo.workPeriod?? && scheduleInfo.workPeriod=="AM">selected</#if>>上午</option>
			        <option value="PM" <#if scheduleInfo.workPeriod?? && scheduleInfo.workPeriod=="PM">selected</#if>>下午</option>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>开始时间：</label>
			<div class="formControls col-xs-4 col-sm-2">
                <select class="select" datatype="*" nullmsg="请选择！" id="workTimeStart" name="workTimeStart" >
                    <option value="" >未选择时间</option>
                     <#if scheduleInfo.workTimeStart??><option value="${scheduleInfo.workTimeStart!}" selected>${scheduleInfo.workTimeStart!}</option></#if>
                </select>
				<#--<input id="workTimeStart"  name="workTimeStart" type="text" class="input-text" value="${(info.tel)!}" placeholder="" />-->
			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>结束时间：</label>
			<div class="formControls col-xs-4 col-sm-2">
                <select class="select" datatype="*" nullmsg="请选择！" id="workTimeEnd" name="workTimeEnd" >
                    <option value="" >未选择时间</option>
                     <#if scheduleInfo.workTimeEnd??><option value="${scheduleInfo.workTimeEnd!}" selected>${scheduleInfo.workTimeEnd!}</option></#if>
                </select>
				<#--<input id="workTimeEnd"  name="workTimeEnd" type="text" class="input-text" value="${(info.mobile)!}" placeholder="" />-->

			</div>
		</div>
		<div class="row cl" style="margin-top:1%;">
			<label class="form-label col-xs-4 col-sm-2 lable1">号源数量：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="registerQuantity"  name="registerQuantity" type="text" class="input-text" value="${(scheduleInfo.registerQuantity)!}" placeholder="" />
			</div>
		</div>
		
		<div class="cl" style="margin-top:1%;">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2" >
				<button class="btn btn-primary radius" style="margin-left:65%;" type="button"  onclick="saveScheduleInfo()" id='save'><i class="Hui-iconfont">&#xe632;</i> 保存</button>
				<button onClick="layer_close();" class="btn btn-default radius" style="margin-left:2%;" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
	</div>
	<script type="text/javascript">
		var base = '${base}';
	</script>
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
    <script type="text/javascript"  src="${base}/static/base/js/external/scheduleinfo.js"></script>
</body>
</html>