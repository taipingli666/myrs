<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<#assign base=rc.contextPath />
 <base id="base" href="${base}">
<@choiceSign.header />
<@choiceSign.left />
<html>
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/external/basic-news.css"/>
<link rel="stylesheet" type="text/css" href="${base}/static/lib/validform/Validform.css"/>
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
						<img src="${base}/static/lib/h-ui.admin/images/basic-news/alist_1.png" alt="">
				  </label>
			</div>
		<form action="${base}/assay/assayNext.do" method="post" id="form">
			<div class="regiter">
				<dl>
					<dt>身份证号：</dt>
					<dd>
						<input type="text" id="card_id" name="patientIdCard" class="input-text inputxt" value="140225198204154577">
						<span class="asterisk">*</span>
						<span id="card_id_validvalue" style="color: red"></span>
						<div class="button" id="search">
							<img src="${base}/static/lib/h-ui.admin/images/basic-news/search.png" alt="">
							<span>查询</span>
						</div>
					</dd>
				</dl>
				<dl>
					<dt>姓名：</dt>
					<dd>
						<input type="text" id="pat_name" name="patientName" class="input-text inputxt">
						<span class="asterisk">*</span>
						<span id="pat_name_validvalue" style="color: red"></span>
					</dd>
				</dl>
				<dl>
					<dt>性别：</dt>
					<dd>
						<select class="select_sex input-text" id="sex" name="patientGender">
						   <option value="1">男</option>
						   <option value="0">女</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>年龄：</dt>
					<dd>
						<input type="text" id="age" name="patientAge" class="input-text inputxt">
						<span class="asterisk">*</span>
						<span id="_age" style="color: red"></span>
					</dd>
				</dl>
				<dl>
					<dt>手机：</dt>
					<dd>
						<input type="text" id="tel" name="patientPhone" class="input-text inputxt">
						<span class="asterisk">*</span>
						<span id="tel_validvalue" style="color: red"></span>
					</dd>
				</dl>
				<dl>
					<dt>就诊卡号：</dt>
					<dd>
						<input type="text" id="healthid" name="patientMediumCode" class="input-text inputxt">
						<span class="asterisk">*</span>
					</dd>
				</dl>
				<dl>
					<dt>初步诊断：</dt>
					<dd>
						<input  class="input-text inputxt" readonly="readonly" type="text" placeholder="双击产生诊断编码" ondblclick ="showicd10model();" style="background:#EAEAEA" id="diag" name="diag" >
						<input  class="input-text inputxt" readonly="readonly" type="text" style="margin-left:10px;background:#EAEAEA" id="icd10" name="icd10">
						<span class="asterisk">*</span>
						<span id="diag_validvalue" style="color: red"></span>
					</dd>
				</dl>
				<dl>
					<dt>预约医院：</dt>
					<dd>
						<select class="select_sex input-text" id="orgIdTo" >
                            <option value="000000">鄂东医疗集团中心院区</option>
						    <#--<#list hoslist as obj>-->
					<#--　　			<option id="${obj.hosNum}" value="${obj.hosId}">${obj.hosName}</option>-->
							<#--</#list>-->
						</select>
						<!-- 赋值 
						<input type="hidden" id="orgNameTo" name="orgNameTo" value="">-->
						<input type="hidden" id="hosCode" name="hosCode" value="" class="input-text inputxt">
					</dd>
				</dl>
			</div> 	
			<div class="next" id="next">
				<a>下一步</a>
			</div>
		</form>
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
<script type="text/javascript">
	var globalVar = {};
	globalVar.base = '${base}';
</script>
<script type="text/javascript" src="${base}/static/base/js/external/baseInfo.js"></script>
</body>
</html>