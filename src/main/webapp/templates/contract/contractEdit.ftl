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
				<div class="panel-body nopd">
					<form action="" id="contForm" method="post" class="form form-horizontal form-examiner-info" >
						<input type="hidden" class="input-text" value="${info.contractId!}" name="contractId">
						<div class="row cl">
							<ul id="displayUL">
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">姓名：</label>
							        <div class="formControls">
							        	<input type="text" class="input-text" value="${info.trueName!}" name="trueName">
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">性别：</label>
							        <div class="formControls">
							        	<span class="select-box"> 
								        	<select class="select" name="sex">
								        		<option value="">请选择</option>
								        		<option value="1">男</option>
								        		<option value="0">女</option>
								        	</select> 
							        	</span>
							        </div>
							        <input type="hidden" class="input-text" value="${info.sex!}">
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">身份证号：</label>
							        <div class="formControls">
							        	<input type="text" class="input-text" value="${info.card!}" name="card">
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">手机：</label>
							        <div class="formControls">
							        	<input type="text" class="input-text" value="${info.mobile!}" name="mobile">
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">居民类型：</label>
							        <div class="formControls">
							        	<span class="select-box"> 
							        		<select class="select" name="personType">
							        			<option value="">请选择</option>
							        			<#if personTypes?exists>
												<#list personTypes as personType>
													<option value="${personType.code!}">${personType.word!}</option>
												</#list>
												</#if>
							        		</select> 
							        	</span>
							        </div>
							        <input type="hidden" class="input-text" value="${info.personType!}" name="">
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">医保类型：</label>
							        <div class="formControls">
							        	<span class="select-box"> 
							        		<select class="select" name="insuranceType">
							        			<option value="">请选择</option>
							        			<#if insuranceTypes?exists>
												<#list insuranceTypes as insuranceType>
													<option value="${insuranceType.code!}">${insuranceType.word!}</option>
												</#list>
												</#if>
							        		</select> 
							        	</span>
							        </div>
							        <input type="hidden" class="input-text" value="${info.insuranceType!}">
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">医保编号：</label>
							        <div class="formControls">
							        	<input type="text" class="input-text" value="${info.insuranceId!}" name="insuranceId">
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">来源：</label>
							        <div class="formControls">
							        	<span class="select-box"> 
							        		<select class="select" name="source">
							        			<option value="">请选择</option>
							        			<#if sources?exists>
												<#list sources as source>
													<option value="${source.code!}">${source.word!}</option>
												</#list>
												</#if>
							        		</select> 
							        	</span>
							        </div>
							        <input type="hidden" class="input-text" value="${info.source!}">
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">签约年份：</label>
							        <div class="formControls">
							        	<input type="text" readonly="readonly" class="input-text" name="signYear" value="${info.signYear!}">
							        </div>
							        
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">服务期限：</label>
							        <div class="formControls" style="width:220px">
							        	<input type="text" style="width:92px;display:inline" readonly="readonly" class="input-text" value="${info.startTime!}" id="">&nbsp;&nbsp;至&nbsp;&nbsp;<input type="text" style="width:92px;display:inline" readonly="readonly" class="input-text" value="${info.endTime!}" id="">
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">签约机构：</label>
							        <div class="formControls">
							        	<span class="select-box"> 
							        		<select class="select" name="hosId">
							        			<option value="">请选择</option>
							        			<#if hospitals?exists>
												<#list hospitals as hospital>
													<option value="${hospital.hosId!}">${hospital.hosName!}</option>
												</#list>
												</#if>
							        		</select> 
							        	</span>
							        </div>
							        <input type="hidden" class="input-text" value="${info.hosId! }" id="">
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">县市：</label>
							        <div class="formControls">
							        	<span class="select-box"> 
							        		<select class="select" id="county" name="county" onchange="getTown()" jump="true">
							        			<option value="">请选择</option>
							        			<#if commonAreas?exists>
												<#list commonAreas as commonArea>
													<option value="${commonArea.code}">${commonArea.areaName}</option>
												</#list>
												</#if>
							        		</select> 
							        	</span>
							        </div>
							        <input type="hidden" class="input-text" value="${info.county!}">
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">乡镇：</label>
							        <div class="formControls">
							        	<span class="select-box"> 
							        		<select class="select" id="town" name="town" onchange="getVillage()" jump="true">
							        		<#if commonTownAreas?exists>
												<#list commonTownAreas as commonArea>
													<option value="${commonArea.code}">${commonArea.areaName}</option>
												</#list>
											</#if>
							        		</select> 
							        	</span>
							        </div>
							        <input type="hidden" class="input-text" value="${info.town!}">
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">村街：</label>
							        <div class="formControls">
							        	<span class="select-box"> 
							        		<select class="select" id="village" name="village" jump="true">
							        		<#if commonVillageAreas?exists>
												<#list commonVillageAreas as commonArea>
													<option value="${commonArea.code}">${commonArea.areaName}</option>
												</#list>
											</#if>
							        		</select> 
							        	</span>
							        </div>
							        <input type="hidden" class="input-text" value="${info.village!}" >
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">门牌路巷：</label>
							        <div class="formControls">
							        	<input type="text" class="input-text" value="${info.address!}" name="address">
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">套餐：</label>
							        <div class="formControls">
							        	<span class="select-box"> 
							        		<select class="select" name="sClass">
							        			<option value="">请选择</option>
							        			<#if commonPackages?exists>
												<#list commonPackages as commonPackage>
													<option value="${commonPackage.classID!}">${commonPackage.className!}</option>
												</#list>
												</#if>
							        		</select> 
							        	</span>
							        </div>
							        <input type="hidden" class="input-text" value="${info.sClass!}">
							    </li>
							        
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">签约医生：</label>
							        <div class="formControls">
							        	<span class="select-box"> 
							        		<select class="select" name="expertId">
							        			<#if expert?exists>
													<option value="${expert.userId!}">${expert.trueName!}</option>
												</#if>
							        		</select> 
							        	</span>
							        </div>
							        <input type="hidden" class="input-text" value="${info.expertId!}">
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">纸质留底：</label>
							        <div class="formControls">
							        	<span class="select-box"> 
							        		<select class="select" name="havePaper">
							        			<option value="">请选择</option>
							        			<option value="1">是</option>
							        			<option value="0">否</option>
							        		</select> 
							        	</span>
							        </div>
							        <input type="hidden" class="input-text" value="${info.havePaper!}">
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">签约人：</label>
							        <div class="formControls">
							        	 <input type="text"  readonly="readonly" class="input-text" value="${channelUser.trueName! }" >
							        </div>
							   </li>
							        
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">签约手机：</label>
							        <div class="formControls">
							        	<input type="text" readonly="readonly" class="input-text" value="${channelUser.tel! }" >
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">签约人证件：</label>
							        <div class="formControls">
							        	<input type="text" readonly="readonly" class="input-text" value="${channelUser.card! }" >
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">签约类型：</label>
							        <div class="formControls">
							        	<span class="select-box"> 
							        		<select class="select" name="signType">
												<option value="100062">团队签约</option>
							        		</select> 
							        	</span>		
							        </div>
							        <input type="hidden" class="input-text" value="${info.signType!}">
							    </li>
							        
							    <li class="col-md-6 col-lg-4 checkboxLI" style="width:100%">
							    	<label class="form-label">人群分类：</label>
							        <div id="crowdType" class="formControls" style="margin-top:5px;width:90%" >
							        	<#if crowdTypes?exists>
										<#list crowdTypes as crowdType>
											<input type="checkbox"  class="chebox test" value="${crowdType.code!}" id="crowdType${crowdType.code!}"/>
											<label for="crowdType${crowdType.code!}">${crowdType.word!}</label>
										</#list>
										</#if>
							        </div>
							         <input type="hidden" class="input-text " name="crowdType" value="${info.crowdType!}">
							    </li>
							    
							    <li class="col-md-6 col-lg-4 checkboxLI" style="width:100%">
							    	<label class="form-label">疾病分类：</label>
							        <div class="formControls" style="margin-top:5px;width:90%" >
							        	<#if diseaseTypes?exists>
										<#list diseaseTypes as diseaseType>
								        	<input type="checkbox"  class="chebox" value="${diseaseType.code!}" id="diseaseType${diseaseType.code!}"/>
								        	<label for="diseaseType${diseaseType.code!}">${diseaseType.word!}</label>
							        	</#list>
										</#if>
						            </div>
						             <input type="hidden" class="input-text" name="diseaseType" value="${info.diseaseType!}">
						       </li>
							</ul>
						</div>
					</form>
				</div>
			</div>
			
	<div class="col-md-12">					
		<span class="l top-oprt-bar">
			<input class=" btn btn-primary radius" type="submit" id="save" value="保存">
			<input class=" btn btn-primary radius" type="submit" id="printLiability" value="打印签约责任书">
			<input class=" btn btn-primary radius" type="submit"  onClick="layer_close();" value="取消">
		</span>
	</div>
	
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>		
	<script type="text/javascript">
		var globalVar = {};
		globalVar.base = '${base}';
	</script>
	<script type="text/javascript" src="${base}/static/base/js/contract/contractEdit.js"></script>
</body>
</html>