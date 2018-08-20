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
		.form li{
			margin-top:10px;
		}
		.form-examiner-info .form-label{
			width:100px;
		}
		.top-oprt-bar{
		    width: 100%;
			text-align: center;
			margin-top:10px;
		}
		.top-oprt-bar input{
			margin-left:25px;
		}
        input[type="text"]{
			background-color: #C9C9C9;
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
</head>
<body>
		<div class="row cl btn-line-top mb-20" style="position:relative; margin: 0;">
				<div class="panel-body nopd" style="padding-left:30px">
					<form action="" id="contForm" method="post" class="form form-horizontal form-examiner-info" >
						<input type="hidden" class="input-text" value="${info.id!}" id="id">
						<div class="row cl">
							<ul id="displayUL">
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">姓名：</label>
							        <div class="formControls">
                                        <input type="text" class="input-text" value="${info.patientName!}" readonly>
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">性别：</label>
							        <div class="formControls">
                                        <#if info.patientGender! == "1">
                                            <input type="text" class="input-text" value="男" readonly>
                                        <#elseif info.patientGender! == "2">
                                            <input type="text" class="input-text" value="女" readonly>
                                        <#else>
                                            <input type="text" class="input-text" value="" readonly>
                                        </#if>

							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">年龄：</label>
							        <div class="formControls">
                                        <input type="text" class="input-text" value="${info.patientAge!}" readonly>
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">身份证号：</label>
							        <div class="formControls">
                                        <input type="text" class="input-text" value="${info.patientIdCard!}" readonly>
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">手机：</label>
							        <div class="formControls">
                                        <input type="text" class="input-text" value="${info.patientPhone!}" readonly>
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">转入医院：</label>
							        <div class="formControls">
                                        <input type="text" class="input-text" value="${info.hosName!}" readonly>
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">预约科室：</label>
							        <div class="formControls">
                                        <input type="text" class="input-text" value="${info.deptName!}" readonly>
							        </div>
							    </li>
							    
							     <li class="col-md-6 col-lg-4">
							    	<label class="form-label">预约医生：</label>
							        <div class="formControls">
                                        <input type="text" class="input-text" value="${info.patientName!}" readonly>
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">预约日期：</label>
							        <div class="formControls">
                                        <input type="text" class="input-text" value="${info.visitDate!?string("yyyy-MM-dd")}" readonly>
							        </div>
							    </li>
                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">号源顺序：</label>
                                    <div class="formControls">
                                        <input type="text" class="input-text" value="${info.sequenceNumber!}" readonly>
                                    </div>
                                </li>
                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">就诊时段：</label>
                                    <div class="formControls">
                                        <input type="text" class="input-text" value="${info.workPeriod}" readonly>
                                    </div>
                                </li>
                                <#--<#if hosId! == info.hosCode! && info.registerStatus! = "0">-->
                                <#--<li class="col-md-6 col-lg-4">-->
                                    <#--<label class="form-label">预约状态：</label>-->
                                    <#--<div class="formControls">-->
                                        <#--<span class="select-box">-->
								        	<#--<select class="select" id="registerStatus">-->
								        		<#--<option value="0" <#if info.registerStatus! = "0">selected</#if>>预约中</option>-->
								        		<#--<option value="2" <#if info.registerStatus! = "2">selected</#if>>预约成功</option>-->
								        		<#--<option value="3" <#if info.registerStatus! = "3">selected</#if>>预约失败</option>-->
								        	<#--</select>-->
							        	<#--</span>-->
                                    <#--</div>-->
                                <#--</li>-->

                                <#--<li class="col-md-6 col-lg-4">-->
                                    <#--<label class="form-label">反馈信息：</label>-->
                                    <#--<div class="formControls">-->
                                        <#--<input type="text" class="input-text" value="${info.feedback!}" id="feedback" >-->
                                    <#--</div>-->
                                <#--</li>-->
                                <#--<#else>-->
                                 <li class="col-md-6 col-lg-4">
                                     <label class="form-label">预约状态：</label>
                                     <div class="formControls">
                                         <#if info.registerStatus! == "0" >
                                             <input type="text" class="input-text" value="预约中" readonly>
                                         <#elseif info.registerStatus! == "2">
                                             <input type="text" class="input-text" value="预约成功" readonly>
                                         <#elseif info.registerStatus! == "3">
                                             <input type="text" class="input-text" value="预约失败" readonly>
										 <#elseif info.registerStatus! == "8">
                                             <input type="text" class="input-text" value="取消预约" readonly>
                                         </#if>

                                     </div>
                                 </li>

                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">反馈信息：</label>
                                    <div class="formControls">
                                        <input type="text" class="input-text" value="${info.feedback!}"  readonly>
                                    </div>
                                </li>
                                <#--</#if>-->

							</ul>
						</div>
					</form>
				</div>
			</div>
			
	<div class="col-md-12">					
		<span class="l top-oprt-bar">
			  <#if hosId! == info.hosCode!>
			      <input class=" btn btn-primary radius" type="submit"  onClick="save();" value="保存">
			  </#if>
			<input class=" btn btn-primary radius" type="submit"  onClick="layer_close();" value="关闭">
		</span>
	</div>
	
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>		
	<script type="text/javascript">
		var globalVar = {};
		globalVar.base = '${base}';
	</script>
	<script type="text/javascript" src="${base}/static/base/js/external/register.js"></script>
</body>
</html>