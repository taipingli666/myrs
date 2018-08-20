<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<#assign base=rc.contextPath />
 <base id="base" href="${base}">
<@choiceSign.header />

<@choiceSign.left />
<style>
    .select_sex{
        width: 300px;
    }
</style>
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${base}/static/lib/validform/Validform.css"/>
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/external/basic-news.css"/>
</head>
<body>
<section class="Hui-article-box">
	<nav class="breadcrumb">
		<i class="Hui-iconfont"></i>
		<a href="/" class="maincolor">首页</a> 
		<span class="c-gray en">&gt;</span>双向转诊管理
		<span class="c-gray en">&gt;</span>预约住院
	</nav>
	
	<div class="Hui-article">
		<article class="pd-20">
			<form action="" method="post"  id="form-referral-info">
            <div class="regiter" id="outcht">
                        <dl>
                            <dt> 查询/读卡：</dt>
                            <dd>
                                <input type="text" name="content" class="input-text inputxt content" placeholder="可根据身份证号/电话/姓名模糊查询"/>
                                <div id="auth" class="button" style="width: 95px">
                                    <span>查询/读卡</span>
                                </div>
                            </dd>
                        </dl>
                        <dl>
                            <dt>身份证号：</dt>
                            <dd>
                                <input type="text" id="card_id" name="cardId" class="input-text inputxt"  value="${(hisPatientInfo.patientIdCard)!}">
                                <span class="asterisk">*</span>
                                <span id="card_id_validvalue" style="color: red"></span>
                            </dd>
                        </dl>
                        <dl>
                            <dt> 姓名：</dt>
                            <dd>
                                <input type="text" value="${(hisPatientInfo.patientName)!}" class="input-text inputxt" id="pat_name" name="patName" jump="true"/>
                                <span class="asterisk">*</span>
                                <span id="pat_name_validvalue" style="color: red"></span>
                            </dd>
                        </dl>
                        <dl>
                            <dt> 性别：</dt>
                            <dd>
                                <select class="select_sex input-text" id="sex" name="sex" jump="true">
                                        <option value="">-请选择-</option>
                                        <option value="1" <#if (hisPatientInfo.patientSex)! == "男">selected</#if>>男</option>
                                        <option value="2" <#if (hisPatientInfo.patientSex)! == "女">selected</#if>>女</option>
                                </select>
                                <span id="sex_validvalue" style="color: red"></span>
                            </dd>
                        </dl>
                        <dl>
                            <dt> 年龄：</dt>
                            <dd>
                                <input type="text" value="${(hisPatientInfo.patientAge)!}" class="input-text inputxt"
                                       id="age" name="age" jump="true"/>
                                <span class="asterisk">*</span>
                                <span id="_age" style="color: red"></span>
                            </dd>
                        </dl>
                        <dl>
                            <dt> 手机：</dt>
                            <dd>
                                <input type="text" value="${(hisPatientInfo.mobile)!}" class="input-text inputxt" id="tel" name="tel"  jump="true">
                                <span class="asterisk">*</span>
                                <span id="tel_validvalue" style="color: red"></span>
                            </dd>
                        </dl>
                        <dl>
                            <dt> 就诊卡号：</dt>
                            <dd>
                                <input type="text" class="input-text inputxt" value="${(hisPatientInfo.medicalCard)!}" id="healthid" name="healthId"  jump="true">
                            </dd>
                        </dl>
                        <dl>
                            <dt> 初步诊断：</dt>
                            <dd>
                                <input type="text" class="input-text" value="" id="diag" name="diag"  jump="true"
                                       placeholder="双击-弹出诊断编码"  style="background:#EAEAEA"
                                       readonly="readonly"  ondblclick ="showicd10model();">
                            <#--<input type="text" style="margin-left:10px;background:#EAEAEA" class="input-text" value=""
                                   id="icd10" name="icd10"  jump="true" readonly="readonly">-->
                            </dd>
                        </dl>
                        <dl>
                            <dt> 预约医院：</dt>
                            <dd>
                                <select class="select_sex input-text" id="tohos" name="orgIdTo" jump="true">
                                    <#list hoslist as obj>
                                        <option value="${obj.hosId!}">${obj.hosName!}</option>
                                    </#list>
                                        <#--<option value="7">鄂东医疗集团中心院区</option>-->
                                </select>
                                <input type="hidden" id="orgNameTo" name="orgNameTo" value="">
                            </dd>
                        </dl>
                        <dl>
                            <dt> 预约时间：</dt>
                            <dd>
                                <input type="text" readonly="readonly" name="orderDate" id="orderDate" class="Wdate"
                                       onFocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd', minDate:'%y-%M-%d'})" style="width: 278px">
                            </dd>
                        </dl>
                        <dl>
                            <dt> 病情描述：</dt>
                            <dd>
                                <textarea class="input-text inputxt" style="width: 300px; height: 80px;float:left" value="" id="illnessDescription" name="illnessDescription"></textarea>
                                <span id="textDescription_validvalue" style="color: red"></span>
							</dd>
                        </dl>
				</div>
            <div align="center" style="margin-top:70px;width: 600px">
                <button id="_sub" type="button" class="btn" style="height: 40px;width: 150px;background:#38AAFB;color:#fff;">下一步
                </button>
            </div>
			</form>
		</article>
		<@choiceSign.footer />
	</div>
</section>

<!-- ICD10 -->
<div class="modal fade" id="icd10model" tabindex="-1" role="dialog" aria-hidden="true"
     style="display: none;width: 100%; margin: 0 auto;">
   <div class="modal-dialog">
       <div class="modal-content">
           <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
               <h5 class="modal-title" style="font-weight: 500">
               <input placeholder="输入汉字或拼音简码回车检索" id="searchIcdcode" class="pull-left"
                               onkeydown="if (event.keyCode == 13){entersearchicd10();}"/>
                   双击ICD10诊断选中带入</h5>
           </div>
           <div class="modal-body">

               <table width="100%" class="table table-striped table-bordered table-hover" id="icd10modeltable">
                   <thead>
                   <tr>
                       <th style="width: 20%">ICD10</th>
                       <th style="width: 50%">诊断名称</th>
                       <th style="width: 30%">诊断名称</th>
                   </tr>
                   </thead>
                   <tbody style="text-align: left;" id="icd10tbody">
                   		
                   </tbody>
                </table><p></p>
             <div id="pagediv">
		    	 <div class="" style="display: inline;">
	                 <span class="" style="">跳转至</span>
	                 <input class="" type="text" style="width: 40px" id="pageinput"
	                 onkeyup="this.value=this.value.replace(/\D/g,'')" 
	                 onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
	                 <input class="btn btn-write" type="button" value="跳转" id="jumppage"/>
                 </div>
					    	
	    	    <div class="" style="float: right">
                     <button class="btn btn-white" type="button" id="lastpage">上一页</button>
                     <input class="btn btn-primary" type="button" id="pageshow" value="1"	>
                     <button class="btn btn-white" type="button" id="nextpage">下一页</button>
                      <span >共</span><a id="all_page" class="all_page">0</a><span>页</span>
       			</div>
            </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	var globalVar = {};
	globalVar.base = '${base}';
</script>
<script type="text/javascript"  src="${base}/static/lib/validform/Validform_v5.3.2.js"></script>
<script type="text/javascript"  src="${base}/static/base/js/referral/referraloutcht.js"></script>
<script type="text/javascript"  src="${base}/static/base/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${base}/static/lib/My97DatePicker/4.8/WdatePicker.js"></script>
</body>
</html>