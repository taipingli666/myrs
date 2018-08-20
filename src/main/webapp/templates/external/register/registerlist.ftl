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
		<span class="c-gray en">&gt;</span>门诊转诊列表
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<input type="hidden" id="hosId" value="${hosId }">
            <input type="hidden" class="input-text" value="1" id="page">
			<!-- 搜索表单区域start -->
			<div class="row cl btn-line-top mb-20" style="position:relative; margin: 0;">
				<div class="panel-body nopd">
					<form  method="post" class="form form-horizontal form-examiner-info" id="contractOfConditions">
						<div class="row cl">
							<ul>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">开始时间：</label>
									<div class="formControls">
										<input value="${startDate! }" name="startDate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd 00:00:00' })"  class="input-text Wdate">
									</div>
								</li>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">结束时间：</label>
									<div class="formControls">
										<input value="${endDate! }" name="endDate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd 00:00:00' })"  class="input-text Wdate">
									</div>
								</li>
								<li class="col-md-6 col-lg-4">
                                    <label class="form-label">转入/转出：</label>
                                    <div class="formControls">
                                    <select  name="refType" type="select" style="width:140px;" class="select-box" id="refType" >
                                        <option data-hosnum="" value="">--请选择--</option>
                                        <option value="1">转出</option>
                                        <option value="2">转入</option>
                                    </select>
									</div>
								</li>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">姓名：</label>
									<div class="formControls skin-minimal" >
										<input  name="patientName" type="text" class="input-text" value="${registerInfo.patientName! }" >
									</div>
									<input type="hidden" class="input-text" value="">
								</li>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">身份证号：</label>
									<div class="formControls">
										<input  name="patientIdCard" type="text" class="input-text" value="${registerInfo.patientIdCard! }" >
									</div>
								</li>
								<li class="col-md-6 col-lg-4">
									<label class="form-label">手机号：</label>
									<div class="formControls">
										<input   name="patientPhone" type="text" class="input-text" value="${registerInfo.patientPhone! }" >
									</div>
								</li>
							</ul>
						</div>
					</form>
				</div>
				<div class="col-md-12" >					
					<span class="top-oprt-bar">
						<button name=""  class="btn btn-success" type="button" id="search" "><i class="Hui-iconfont"></i> 查询</button>
						<#--<input class=" btn btn-primary radius" type="button" id="export"  value="导出">-->
					</span>
				</div> 
			</div>
			<!-- 搜索表单区域end -->
			<div class="col-md-7 oprt-bar" style="">		  
					<span class="r">共有数据：<strong>
				</strong>${registerInfoList.totalRecord}条</span>
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
                            <#--<th width="13%">预约医院</th>-->
							<th width="6%">姓名</th>
							<th width="12%">身份证</th>
							<th width="5%">健康卡号</th>
							<th width="5%">类型</th>
							<th width="8%">接诊科室</th>
							<th width="7%">接诊医生</th>
							<th width="9%">就诊日期</th>
							<th width="5%">预约状态</th>
                            <th width="23%">操作</th>
						</tr>
					</thead>
					<tbody id="tbody" class="text-c">
					<#list registerInfoList.content as obj>
					<tr>
						<#--<td>${obj.hosName! }</td>-->
						<td>${obj.patientName! }</td>
						<td>${obj.patientIdCard! }</td>
						<td>${obj.patientMediumCode! }</td>
						<#if obj.operHosCode! == hosId>
								<td>转出</td>
						<#else >
						<td>转入</td>
						</#if>
						<td>${obj.deptName! }</td>
						<td>${obj.doctorName! }</td>
						<td>${(obj.visitDate?string("yyyy-MM-dd"))!}</td>
						<td>
							<#if obj.registerStatus! == "0" >
								预约中
							<#elseif obj.registerStatus! == "2">
								预约成功
							<#elseif obj.registerStatus! == "3">
								预约失败
							<#elseif obj.registerStatus! == "8">
								取消预约
							<#else>
								预约中
							</#if></td>
                        <td>
                           <input type="submit" class="btn btn-primary radius check" value="查看"/>
                            <input type="hidden" value="${obj.id }"/>
							<#--转出方-->
							<#if obj.operHosCode! == hosId && obj.registerStatus! != "8" <#--&& .now?date < obj.visitDate?date!-->>
                            <a onclick="feedback('${obj.id! }')" href="javascript:void(0)" class="btn btn-primary radius"><i class="Hui-iconfont"></i>取消预约</a>
							</#if>
							<#--<#if obj.operHosCode! != hosId>-->
							<#--接收方-->
							 <a onclick="feedback2('${obj.id! }')" href="javascript:void(0)" class="btn btn-primary radius"><i class="Hui-iconfont"></i>备注</a><input id="${obj.id! }" type="text" value="${obj.feedback! }" style="display: none">
							<#--</#if>-->
                            <input onclick="registerPrint('${obj.id! }')" type="button" class="btn btn-primary radius print" value="打印"/>
						</td>
					</tr>
					</#list>
					</tbody>
				</table>
			<@choiceSign.signpage registerInfoList/>
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
	<#if refType??>
	    $("#refType").val(${refType!});
	</#if>
</script>
<script type="text/javascript" src="${base }/static/base/js/external/registerlist.js"></script>
</body>
</html>