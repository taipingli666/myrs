<#assign base=rc.contextPath />
<@choiceSign.header />
<@choiceSign.left />
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/external/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/external/telemedicine.css" />
<script type="text/javascript" src="${base}/static/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<style>
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
	.contact-name li{
	font-size:14px;
	}
	.form-examiner-info .form-label{
		width:95px;
       font-weight:normal;
	}
	.form-examiner-info .formControls{
		width:150px;
	}
	.btn-see{
		height:22px;
	}
	.mt-20{
		margin-top:30px;
	}
	.oprt-bar{
		margin:0;
		padding-right:10px;
	}
</style>

<section class="Hui-article-box">
	<nav class="breadcrumb">
		<i class="Hui-iconfont"></i>
		<a href="/" class="maincolor">首页</a> 
		<span class="c-gray en">&gt;</span>远程会诊
		<span class="c-gray en">&gt;</span>会诊列表
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<!-- 搜索表单区域start -->
			<div class="row cl btn-line-top mb-20" style="position:relative; margin: 0;">
				<div class="panel-body nopd">
					<form  method="post" class="form form-horizontal form-examiner-info" id="contractOfConditions">
						<div class="row cl">
                            <ul>
                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">开始时间：</label>
                                    <div class="formControls">
                                        <input value="${startDate! }" name="startDate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss' })"  class="input-text Wdate" value="${startDate!''}" style="width:150px">
                                    </div>
                                </li>
                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">结束时间：</label>
                                    <div class="formControls">
                                        <input value="${endDate! }" name="endDate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss' })"  class="input-text Wdate" value="${endDate!''}" style="width:150px">
                                    </div>
                                </li>
                                <li class="col-md-6 col-lg-4">

                                </li>
                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">患者：</label>
                                    <div class="formControls">
                                        <input  name="bingrenid" type="text" class="input-text" value="${bingrenid!''}" >
                                    </div>
                                </li>
                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">会诊状态：</label>
                                    <div class="formControls skin-minimal" >
                                        <select  id="state" name="state" style="width:150px" class="select-box">
                                        <#if state=='3'>
						                    <option value="3" selected="selected">等待会诊</option>
						                <#else>
						                	<option value="3">等待会诊</option>
						                </#if>
						                <#if state=='4'>
						                    <option value="4" selected="selected">会诊完成</option>
						                <#else>
						                	<option value="4">会诊完成</option>
						                </#if>
						                <#if state=='5'>
						                    <option value="5" selected="selected">会诊异常结束</option>
						                <#else>
						                	<option value="5">会诊异常结束</option>
						                </#if>
						                </select>
                                    </div>
                                </li>
                            </ul>
						</div>
					</form>
				</div>
				<div class="col-md-12" >					
					<span class="top-oprt-bar">
						<button name=""  class="btn btn-success" type="button" id="search"><i class="Hui-iconfont"></i> 查询</button>
					</span>
				</div> 
			</div>
			<!-- 搜索表单区域end -->
			<div class="col-md-7 oprt-bar" style="">
					<span class="r">共有数据：<strong>
				</strong>${pageobj.totalRecord}条</span>
			</div>
			<div class="mt-20">
				<#list pageobj.content as item>
                    <div id="_list" class="mySubscribeList">
				<div class="mySubscribeList-item">
					<ul class="contact-name clearfix">
						<li><span class="name-hd">预约单号</span>${item.liushuihao!}</li>
					</ul>
					<ul class="contact-name clearfix">
						<li><span class="name-hd">预约时间：</span>
							<#if (item.huizhenfangkssj)??>
                            		${(item.huizhenfangkssj)?string("yyyy年MM月dd日 HH:mm:ss")}
                        	</#if>
                    	</li>
                    	 <li><span class="name-hd">结束时间：</span>
							<#if (item.huizhenfangkssj)??>
                            		${(item.huizhenfangkssj)?string("yyyy年MM月dd日 HH:mm:ss")}
                        	</#if>
                    	</li>
					</ul>
					 
						<#if item.shenqingzt == '4'>
							<a href="javascript:void(0)" class="badge hint3">会诊完成</a>
                            	
                            <#elseif item.shenqingzt == '3' || item.shenqingzt == '1'>
                        		<a href="javascript:void(0)" class="badge hint2">等待会诊</a>
                             
                            <#elseif item.shenqingzt == '5'>
                            	<a href="javascript:void(0)" class="badge">会诊异常结束</a>
                            	
                            <#else>
                            <a href="javascript:void(0)" class="badge hint1">会诊中</a>
                            	
                        </#if>
					 
					<ul class="contact-name clearfix">
						<li><span class="name-hd">病人姓名：</span>${(item.xingming)!''}</li>
						<li><span class="name-hd">预约序号：</span>${item.liushuihao!}</li>
					</ul>
					<ul class="contact-name clearfix">
						<li><span class="name-hd">发起方：</span>${item.shenqingysmc!''}(${item.shenqingyymc!''})</li>
						<li> <span class="name-hd">受邀方：</span>${item.huizhenysmc!''}(${item.huizhenyymc!''}) </li>
					</ul>
					<div class="contact-main clearfix">
						<ul class="contact-name clearfix">
							<li><span class="name-hd">会诊科室：</span>${item.huizhenksmc!''}</li>
						</ul>
						<div class="contact-event clearfix">
							<a href="javascript:void(0);" onclick="javascript:lookSingle('2',${item.liushuihao!''})" class="btn btn-info btn-xs btn-see">查看详情</a>
						</div>
					</div>
				</div>
			</div>	
			</#list>
			<@choiceSign.signpage pageobj/>
			</div>
		</article>
<@choiceSign.footer />
</div>
</section>
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script>
    var contextPath = '${base}';
    function lookSingle(type,id){
	//    iframeTab(globalVar.base+apipath+'/lookConsultationSingle/2/'+$("#save_liushuihao").val(),'查看详情');
	  //查看会诊单
		$(".Hui-article").html();
	    $(".Hui-article").html('<iframe frameborder ="0" scrolling="no" ' +
	        'style="width: 100%;height: 100%;" src="'+contextPath+'/consultationSingle/lookConsultationSingle/'+type+'/'+id+'"></iframe>');
	}
</script>
<script type="text/javascript" src="${base }/static/base/js/external/acceptappointment.js"></script>
