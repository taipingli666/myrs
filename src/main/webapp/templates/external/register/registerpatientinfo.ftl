<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<#assign base=rc.contextPath />
 <base id="base" href="${base}">
<@choiceSign.header />
<@choiceSign.left />
<html>
<style>
    .select_sex{
        width: 300px;
    }
</style>
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/external/basic-news.css"/>
<link rel="stylesheet" type="text/css" href="${base}/static/lib/validform/Validform.css"/>
<body>
<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span>
		双向转诊管理
		<span class="c-gray en">&gt;</span>
		门诊转诊
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
		<div class="pd-20">
			<div class="navBox">
				  <label for="">
						<img src="${base}/static/lib/h-ui.admin/images/basic-news/register/list_1.png" alt="">
				  </label>
			</div>
			<div class="regiter">
                <form class="registerform" id="form" action="">
                    <div class="regiter">
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
                            <dt> 姓名：</dt>
                            <dd>
                                <input type="text" id="patientName" class="input-text inputxt" name="patientName" value="${(hisPatientInfo.patientName)!}" datatype='zh1-6'  nullmsg="请填写您的姓名">
                                <span class="asterisk">*</span>
                            </dd>
                        </dl>
                        <dl>
                            <dt>身份证号：</dt>
                            <dd>
                                <input type="text" name="patientIdCard" id="patientIdCard" value="${(hisPatientInfo.patientIdCard)!}" class="input-text inputxt" datatype="idcard" nullmsg="请填写您的身份证号码！" errormsg="您填写的身份证号码不对！" >
                                <span class="asterisk">*</span>
                            </dd>
                        </dl>

                        <dl>
                            <dt>性别：</dt>
                            <dd>
                                <select name="patientGender" id="patientGender" class="select_sex input-text"  >
                                    <option value="1" <#if (hisPatientInfo.patientSex)! == "男">selected</#if>>男</option>
                                    <option value="2" <#if (hisPatientInfo.patientSex)! == "女">selected</#if>>女</option>
                                </select>
                            </dd>
                        </dl>
                        <dl>
                            <dt>年龄：</dt>
                            <dd>
                                <input type="text" name="patientAge" id="patientAge" value="${(hisPatientInfo.patientAge)!}" class="input-text inputxt" datatype="age" nullmsg="请填写您的年龄">
                                <span class="asterisk">*</span>
                            </dd>
                        </dl>
                        <dl>
                            <dt>手机：</dt>
                            <dd>
                                <input type="text" name="patientPhone" id="patientPhone" value="${(hisPatientInfo.mobile)!}" class="input-text inputxt" datatype="phone">
                                <span class="asterisk">*</span>
                            </dd>
                        </dl>
                        <dl>
                            <dt>就诊卡号：</dt>
                            <dd>
                                <input type="text"id="patientMediumCode"  name="patientMediumCode" value="${(hisPatientInfo.medicalCard)!}" class="input-text inputxt">
								<#--<span class="asterisk">*</span>-->
                            </dd>
                        </dl>
                        <dl>
                            <dt>诊断编码：</dt>
                            <dd>
                                <input type="text" placeholder="双击产生诊断编码"  class="input-text inputxt" ondblclick ="showicd10model();" style="background:#EAEAEA" id="diag" name="icd10Name">
                                <#--<input type="text" style="margin-left:10px;background:#EAEAEA" id="icd10" name="icd10Code"   class="input-text inputxt">-->
                            </dd>
                        </dl>
                        <dl>
                            <dt>预约医院：</dt>
                            <dd>
                                <select name="hosCode" class="select_sex input-text">
                                    <#list hoslist as obj>
                                        <option id="${obj.hosNum!}" value="${obj.hosId!}">${obj.hosName!}</option>
                                    </#list>
                                        <#--<option id="" value="000000">鄂东医疗集团中心院区</option>-->
                                </select>
                            </dd>
                        </dl>
                    </div>
                    <div class="next" align="left" style="margin-left: 220px">
                        <a href="##" id="next">下一步</a>
                    </div>
                </form>
			</div>
		</div>
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
<script type="text/javascript" src="${base}/static/lib/validform/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${base}/static/base/js/external/registerpatientinfo.js"></script>
<script type="text/javascript">
	var base = '${base}';
</script>
</body>
</html>