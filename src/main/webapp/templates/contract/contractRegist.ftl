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
	 label{width:90px;}
	 #sampleNo{width:100%;height:100%;padding:0;border:none;}
	 .btn{ cursor:pointer;}
	 #tPage{overflow:scroll;height:400px;}
	 #tPagei .pt_th{padding:0;}
	 #tPage th input{display:inline-block;width:100%;height:100%;}
	 .oprt-bar{width: 100%;padding:0px;margin:10px 0 10px;}
	 .oprt-bar>.btn{ margin:0 5px 0; }
	 .top-oprt-bar{float:right;}
	 #tbody .curTr{
		background-color:#e4e4e4;
	}
	.form-examiner-info .form-label{
		display: inline-block;
		float: left;
		width: 90px;
		padding: 0 0 0 15px;
		text-align:right;
	}
	.form-examiner-info .input-text{width:220px}
	.form-examiner-info span{width:220px}
	.chebox{margin-right:2px;margin-left:13px}
	
	.desease-label{
		float:left;
	}
	#diseaseDiv:after,#common:after,#regist:after{
		display:block;
		content:"";
		clear:both;
	}
	 
	
	 
</style>
<link rel="stylesheet" type="text/css" href="${base}/static/lib/validform/Validform.css"/>
<script type="text/javascript" src="${base}/static/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<section class="Hui-article-box">
	<nav class="breadcrumb">
		<i class="Hui-iconfont"></i>
		<a href="/" class="maincolor">首页</a> 
		<span class="c-gray en">&gt;</span>签约管理
		<span class="c-gray en">&gt;</span>签约备案
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<div class="panel panel-moudle mb-20" style="width:100%;position:relative;margin: 0" id="contractInfo">
				<div class="panel-header" >签约信息</div>
					<div class="panel-body nopd" >
						<form action="" method="post" class="form form-horizontal form-examiner-info" id="form-contract-info">
							<input type="hidden" id="contractId" name="contractId"/>
							<input type="hidden" name="isRenew" value="0"/>
							<div class="row cl" style="margin-left: 30px;">
								<ul id="regist">
									<li class="col-md-6 col-lg-4"><label class="form-label">姓名：</label>

							        <div class="formControls"><input type="text" class="input-text" id="trueName" name="trueName" jump="true"/></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">性别：</label>
							
							        <div class="formControls"><span class="select-box"> <select class="select" id="sex" name="sex" jump="true"/>
							        	<option value=""></option>
							        	<option value="1">男</option>
										<option value="0">女</option>
							        </select> </span></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">身份证号：</label>
							
							        <div class="formControls"><input type="text" class="input-text" id="card" name="card" jump="true"/></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">手机：</label>
							
							        <div class="formControls"><input type="text" class="input-text" id="mobile" name="mobile"  jump="true"></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">居民类型：</label>
							
							        <div class="formControls"><span class="select-box"> <select class="select" id="personType" name="personType"  jump="true">
							        	<option value=""></option>
							        	<#foreach personType in personTypes> 
							        		<option value="${personType.code}">${personType.word}</option>
							        	</#foreach>
							        </select> </span></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">医保类型：</label>
							
							        <div class="formControls"><span class="select-box"> <select class="select" id="insuranceType" name="insuranceType"  jump="true">
							        	<option value=""></option>
							        	<#foreach insuranceType in insuranceTypes> 
							        		<option value="${insuranceType.code}">${insuranceType.word}</option>
							        	</#foreach>
							        </select> </span></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">医保编号：</label>
							
							        <div class="formControls"><input type="text" class="input-text" value="" id="insuranceId" name="insuranceId"  jump="true"></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">来源：</label>
							
							        <div class="formControls"><span class="select-box"> <select class="select" id="source" name="source" jump="true">
							        	<option value=""></option>
							        	<#foreach source in sources> 
							        		<option value="${source.code}">${source.word}</option>
							        	</#foreach>
							        </select> </span></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">签约年份：</label>
							
							        <div class="formControls"><span class="select-box"> <select class="select" id="signYear" name="signYear">
							        	<option value="${year}">${year}</option>
							        </select> </span></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">服务期限：</label>
							
							        <div class="formControls" style="width:220px"><input type="text" class="input-text" id="startTime" name="startTime" value="${year}-01-01" style="width:92px;display:inline" readonly/>&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;<input type="text" class="input-text" style="width:92px;display:inline" value="${year}-12-31" id="endTime" name="endTime" readonly/></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">签约机构：</label>
							
							        <div class="formControls"><span class="select-box"> 
							        <select class="select" id="hosId" name="hosId" >
							        	<#if hospital?exists>
							        		<option value="${hospital.hosId }">${hospital.hosName }</option>
							        	</#if>
							        </select> </span></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">区县：</label>
							        <div class="formControls"><span class="select-box"> <select class="select" id="county" name="county" onchange="getTown()" jump="true">
							        	<option value=""></option>
							        	<#foreach commonArea in commonAreas> 
							        		<option value="${commonArea.code}">${commonArea.areaName}</option>
							      		</#foreach>
							        </select> </span></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">乡镇：</label>
							        <div class="formControls"><span class="select-box"> <select class="select" id="town" name="town" onchange="getVillage()" jump="true">
							        	
							        </select> </span></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">村街：</label>
							
							        <div class="formControls"><span class="select-box"> <select class="select" id="village" name="village" jump="true">
							        	
							        </select> </span></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">门牌路巷：</label>
							
							        <div class="formControls"><input type="text" class="input-text" id="address" name="address" jump="true"></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">套餐：</label>
							        <div class="formControls"><span class="select-box"> <select class="select" id="sClass" name="sClass" jump="true">
							        	<option value=""></option>
							        	<#foreach commonPackage in commonPackages> 
							        		<option value="${commonPackage.classID}">${commonPackage.className}</option>
										</#foreach>
							        </select> </span></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">签约医生：</label>
							
							        <div class="formControls"><span class="select-box"> <select class="select" id="expertId" name="expertId" jump="true">
							        	<#if user?exists> 
							        		<option value="${user.userId}">${user.userName}</option>
							     		</#if>
							        </select> </span></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">签约人：</label>
							
							        <div class="formControls"><input type="text" class="input-text" value="${login_user.trueName}" id="addPersonName" name="addPersonName" readonly>
							        <input type="hidden"  value="${login_user.userId}" id="addPerson" name="addPerson" />
							        </div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">签约手机：</label>
							
							        <div class="formControls"><input type="text" class="input-text" value="${login_user.tel}" readonly ></div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">签约人证件：</label>
							
							        <div class="formControls"><input type="text" class="input-text" value="${login_user.card}" readonly ></div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4"><label class="form-label">签约类型：</label>
							
							        <div class="formControls"><span class="select-box"> <select class="select" id="signType" name="signType" jump="true">
							        	<!-- <option value=""></option> -->
							        	<#foreach signType in signTypes> 
							        	<!-- 通过判断编号,只显示团队签约 -->
							        		<#if signType.code=100062>
							        			<option value="${signType.code}">${signType.word}</option>
							       			</#if>
							       		</#foreach>
							        </select> </span></div>
							    </li>
							    <li class="col-md-6 col-lg-4" style="width:100%"><label class="form-label">人群分类：</label>
							        <div class="formControls"><span class="select-box"> <select class="select" id="isKey" name="isKey" jump="true" onchange="isKeyChange()">
							        	<option value="0">一般人群</option>
							        	<option value="1">重点人群</option>
							        </select> </span></div>
							    </li>
							    <li class="col-md-6 col-lg-4" style="width:100%;display:none" id="categoryLi">
							        <div class="formControls" style="margin-left:90px;margin-top:5px;width:90%" id="categoryDiv">
							        <#foreach crowdType in crowdTypes> 
								        	<input type="checkbox" name="crowdType" class="chebox" <#if '${crowdType.code}'=='100011'> style="display:none" checked="true"</#if> value="${crowdType.code}" id="crowdType${crowdType.code}"/><label for="crowdType${crowdType.code}" <#if '${crowdType.code}'=='100011'> style="display:none" </#if>>${crowdType.word}</label>
							       	</#foreach>
							        </div>
							    </li>
							    <li class="col-md-6 col-lg-4" style="width:100%;height:auto" id="common"><label class="form-label">常见疾病：</label>
							        <div class="formControls" style="margin-top:5px;width:90%" id="diseaseDiv">
							        <#foreach diseaseType in diseaseTypes> 
							        	<label class="disease-label"><input type="checkbox" name="diseaseType" class="chebox" value="${diseaseType.code}" id="diseaseType${diseaseType.code}"/><span for="diseaseType${diseaseType.code}">${diseaseType.word}</span></label>
							      	</#foreach>
							        </div>
							    </li>
							    <li class="col-md-6 col-lg-4"><label class="form-label">纸质留底：</label>
							
							        <div class="formControls"><span class="select-box"> <select class="select" id="havePaper" name="havePaper" jump="true">
							        	<option value="0">否</option>
							        	<option value="1">是</option>
							        </select> </span></div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4"><label class="form-label" style="width:80px">备注：</label>
							
							        <div class="formControls"><input type="text" class="input-text" value="" id="remark" name="remark" style="width:400px" jump="true"></div>
							    </li>
								 
								</ul>
							</div>
							<div class="col-lg-12 text-c" id="btnLi" style="margin-top:20px">
									<input type="button" class="btn btn-primary radius" value="签约"  id="signCheckBtn" jump="true"/>&emsp;
									<input type="button" class="btn btn-primary radius" value="读卡" id="readCardBtn" />
							</div>
						</form>
					</div>
					</div>
		</article>
<div>
<@choiceSign.footer />
</div>
	</div>
<script type="text/javascript">
	var globalVar = {};
	globalVar.base = '${base}';
</script>
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/static/lib/validform/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${base}/static/base/js/contract/contractRegist.js"></script>
</body>
</html>