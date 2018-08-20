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
<link rel="Shortcut Icon" href="${base}/static/base/images/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${base}/static/lib/html5.js"></script>
<script type="text/javascript" src="${base}/static/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/min.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/admin.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/base/skin/blue/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://static/lib.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<script type="text/javascript" src="${base}/static/lib/jquery/jquery-2.1.0.js"></script> 
<script type="text/javascript" src="${base}/static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base}/static/base/js/main.js"></script> 
<script type="text/javascript" src="${base}/static/base/js/admin.page.js"></script>
<script type="text/javascript" src="${base}/static/lib/bootstrap.min.js"></script>
<link rel="Shortcut Icon" href="${base}/static/base/css/external/bootstrap.css"/>
<!--/meta 作为公共模版分离出去-->
</head>
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
	.Hui-article-box {
    position: absolute;
    top:0;
    right: 0;
    bottom: 0;
    left:0;
    overflow: hidden;
    z-index: 1;
    background-color: #fff;
}
.Hui-article {
    position: absolute;
    top:0;
    bottom: 0;
    left: 0;
    right: 0;
    overflow: auto;
    z-index: 1;
}
</style>
<body>
<section class="Hui-article-box">
	<div class="Hui-article">
		<article class="cl pd-20">
			<!-- 搜索表单区域start -->
			<div class="row cl btn-line-top mb-20" style="position:relative; margin: 0;">
				<div class="panel-body nopd">
					<form  method="post" class="form form-horizontal form-examiner-info" id="contractOfConditions">
						<div class="row cl">
                            <ul>
                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">患者：</label>
                                    <div class="formControls">
                                        <input id="search_value" name="patientName" type="text" class="input-text" value="${patientName!''}" >
                                    </div>
                                </li>
                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">医院：</label>
                                    <div class="formControls skin-minimal" >
                                        <select  id="search_code" name="code" style="width: 141px" class="select-box" >
						                <#list list as item>
						                	<option value="${item.hosNum!}" <#if item.hosNum == (code!'')>selected</#if> >${item.hosName!}</option>
						                </#list>
						                </select>
                                    </div>
                                    <input type="hidden" class="input-text" value="">
                                </li>
                            </ul>
						</div>
					</form>
				</div>
				<div class="col-md-12" >					
					<span class="top-oprt-bar">
						<button name=""  class="btn btn-success" type="button" id="search" onclick="search()"><i class="Hui-iconfont"></i> 查询</button>
					</span>
				</div> 
			</div>
			<!-- 搜索表单区域end -->
			<div class="col-md-7 oprt-bar" style="">
					<span class="r">共有数据：<strong>
				</strong>${(pageobj.totalRecord)!0}条</span>
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th style="display:none"></th>
                            <th width="30%">姓名</th>
                            <th width="30%">拍摄日期</th>
                            <th width="30%">影像编号</th>
						</tr>
					</thead>
					<tbody id="tbody" class="text-c">
					<#if pageobj??>
						<#list pageobj.content as item>
		                    <tr class="text-c" name="dicttr" ondblclick="dbClick(this)">
		                    	<td style="display:none">${item.hospitalId!0}</td>
		                        <td>${item.patientName!}</td>
		                        <td>${item.studyDate!}</td>
		                        <td>${item.studyId!}</td>
		                    </tr>
						</#list>
					<#else>
						<tr class="text-c" name="dicttr">
						<td colspan="3">无数据</td>
						</tr>
					</#if>
					</tbody>
				</table>
			<#if pageobj??>
				<@choiceSign.signpage pageobj/>
			</#if>
			</div>
		</article>
<@choiceSign.footer />
</div>
</body>
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script>
    var contextPath = '${base}';
    var globalVar = {};
	globalVar.base = '${base}';
    function changePage(pageNumber){
    	var $form = $("#contractOfConditions");
		var url = globalVar.base+'/consultationSingle/yxzltable?pageNumber=' + pageNumber+'&';
		url += $form.serialize();
		window.location = (url);
	}
	function search(){
		var $form = $("#contractOfConditions");
		var url = globalVar.base+'/consultationSingle/yxzltable?1=1&';
		url += $form.serialize();
		window.location = (url);
        //$("#contractOfConditions").attr("action",globalVar.base+'/consultationSingle/yxzltable');
        //$("#contractOfConditions").submit();
    }
    function dbClick(row){
    	var $td= $(row).children('td'); 
        var index = parent.layer.getFrameIndex(window.name);
        var code = $td[0].innerHTML;
        code = code.substring(1);
        parent.saveaddVideoN($td[3].innerHTML,code);
        parent.layer.close(index);
    }
</script>
</html>
