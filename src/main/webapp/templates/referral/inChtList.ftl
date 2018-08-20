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
<input type="hidden" id="workGroupId" value="${workGroupId!}" />
<section class="Hui-article-box">
	<nav class="breadcrumb">
		<i class="Hui-iconfont"></i>
		<a href="/" class="maincolor">首页</a> 
		<span class="c-gray en">&gt;</span>签约管理
		<span class="c-gray en">&gt;</span>签约管理
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<div class="text-c">
				姓名:
				<input type="text" id="patName" name="patName" placeholder="病人姓名" style="width:250px;margin:0 40 0 20" class="input-text" value="" />
				状态：
				<select  id="refStatus" name="refStatus" type="select" style="width:250px;margin-left:20" class="select-box"  >
						<option data-hosnum="" value="">--请选择--</option>
	                    <option value="1">待接诊</option>
	                    <option value="2">已接诊</option>
	                    <option value="3">已拒绝</option>
				</select>
				<button id="search" class="btn btn-success" type="button" id="search"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
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
							<th width="5%">转诊时间</th>
							<th width="5%">姓名</th>
							<th width="10%">身份证</th>
							<th width="8%">联系电话</th>
							<th width="7%">类型</th>
							<th width="5%">送转机构</th>
							<th width="9%">接诊机构</th>
							<th width="5%">转诊状态</th>
							<th width="5%">操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<#if page.content?exists>
						<#list page.content as cont>
							<tr class="text-c" name="dicttr" data-contid="${cont.id!}">
								<td>${cont.cmmitDate! }</td>
								<td>${cont.patName!}</td>
								<td>${cont.cardId!}</td>
								<td>${cont.tel!}</td>
								<#if cont.refType?exists>
									<#switch cont.refType>
										<#case "1"><td>转出</td><#break>
										<#case "2"><td>转入</td><#break>
									</#switch>
									<#else>
									<td>无</td>
								</#if>
								<td>${cont.orgNameFrom!}</td>
								<td>${cont.orgNameTo!}</td>
								<#if cont.refStatus?exists>
									<#switch cont.refStatus>
										<#case "1"><td>待接诊</td><#break>
										<#case "2"><td>已接诊</td><#break>
										<#case "3"><td>已拒绝</td><#break>
									</#switch>
									<#else>
									<td>无</td>
								</#if>
								<td>否</td>
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
</section>
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
	var globalVar = {};
	globalVar.base = '${base}';
</script>
<script type="text/javascript" src="${base}/static/base/js/referral/inChtList.js"></script>
</body>
</html>