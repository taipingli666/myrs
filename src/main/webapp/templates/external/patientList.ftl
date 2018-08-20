<#assign base=rc.contextPath />
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
    <script type="text/javascript" src="${base}/static/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/static/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${base}/static/base/js/main.js"></script>
    <script type="text/javascript" src="${base}/static/base/js/admin.page.js"></script>
    <!--/meta 作为公共模版分离出去-->
</head>
<style>
    .oprt-bar{width: 100%;padding:0px;margin:10px 0 10px;}
</style>
<body>
	<div class="Hui-article">
		<article class="cl pd-20">
			<div class="text-c">
				<form>
				<input type="text" id="content" name="content"  placeholder="请输入身份证号/电话/姓名 进行查询" style="width:400px;margin:0 40 0 5" class="input-text" value="${content!}" />
				<button id="search" class="btn btn-success" type="button" id="search"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
                </form>
			</div>
			<div class="col-md-7 oprt-bar" style="">		  
					<span class="r">共有数据：<strong>
					<#if page.totalRecord??>
					${page.totalRecord}
					<#else>
					0
					</#if>
				</strong> 条</span>
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="4%">姓名</th>
                            <th width="3%">性别</th>
                            <th width="3%">年龄</th>
                            <th width="8%">身份证</th>
                            <th width="6%">联系电话</th>
                            <th width="10%">介质卡</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<#if page.content?exists>
						<#list page.content as cont>
							<tr class="text-c" ondblclick="dbClick(this)" name="dicttr" data-contid="${cont.id!}">
								<td>${cont.patientName! }</td>
                                <#if cont.patientGender! == '1'>
                                    <td>男</td>
                                <#else >
                                <td>女</td>
                                </#if>

								<td>${cont.patientAge!}</td>
								<td>${cont.patientIdCard!}</td>
								<td>${cont.patientPhone!}</td>
								<td>${cont.patientMediumCode!}</td>
							</tr>
						</#list>
						</#if>
					</tbody>
				</table>
				<@choiceSign.signpage page/>
			</div>
		</article>
<@choiceSign.footer />
	</div>
</body>
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
	var globalVar = {};
	globalVar.base = '${base}';

</script>
<script type="text/javascript" src="${base}/static/base/js/external/patientList.js"></script>
</body>
</html>