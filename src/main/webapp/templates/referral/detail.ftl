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
				<div class="panel-body nopd" style="padding-left:30px">
					<form action="" id="contForm" method="post" class="form form-horizontal form-examiner-info" >
						<input type="hidden" class="input-text" value="${info.id!}" id="id" name="contractId">
						<div class="row cl">
							<ul id="displayUL">
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">姓名：</label>
							        <div class="formControls">
							        	<input type="text" class="input-text" value="${info.patName!}" >
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">性别：</label>
							        <div class="formControls">
							        	 <#if info.sex! == "1">
                                             <input type="text" class="input-text" value="男" readonly>
										 <#elseif info.sex! == "2">
                                            <input type="text" class="input-text" value="女" readonly>
										 <#else>
                                            <input type="text" class="input-text" value="" readonly>
										 </#if>
							        </div>
							        <input type="hidden" class="input-text" value="${info.sex!}">
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">年龄：</label>
							        <div class="formControls">
							        	<input type="text" class="input-text" value="${info.age!}" >
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">身份证号：</label>
							        <div class="formControls">
							        	<input type="text" class="input-text" value="${info.cardId!}" >
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">手机：</label>
							        <div class="formControls">
							        	<input type="text" class="input-text" value="${info.tel!}" >
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">意向科室：</label>
							        <div class="formControls">
							        	<input type="text" class="input-text" value="${info.intentionDepart!}" >
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">转诊机构：</label>
							        <div class="formControls">
							        	<input type="text" class="input-text" value="${info.orgNameFrom!}">
							        </div>
							    </li>
							    
							     <li class="col-md-6 col-lg-4">
							    	<label class="form-label">接诊机构：</label>
							        <div class="formControls">
							        	<input type="text" class="input-text"  value="${info.orgNameTo!}">
							        </div>
							    </li>
							    
							    <li class="col-md-6 col-lg-4">
							    	<label class="form-label">添加时间：</label>
							        <div class="formControls">
							        	<input type="text" class="input-text"  value="${info.cmmitDate!}">
							        </div>
							    </li>

								   <#if hosId! == info.orgIdTo! && info.refStatus! = "1">
                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">预约状态：</label>
                                    <div class="formControls">
                                        <span class="select-box">
								        	<select class="select" id="refStatus">
								        		<option value="0" <#if info.refStatus! = "1">selected</#if>>待接诊</option>
								        		<option value="2" <#if info.refStatus! = "2">selected</#if>>已接诊</option>
								        		<option value="3" <#if info.refStatus! = "3">selected</#if>>已拒绝</option>
								        	</select>
							        	</span>
                                    </div>
                                </li>

                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">反馈信息：</label>
                                    <div class="formControls">
                                        <input type="text" class="input-text" value="${info.feedback!}" id="feedback" >
                                    </div>
                                </li>
								   <#else>
                                 <li class="col-md-6 col-lg-4">
                                     <label class="form-label">预约状态：</label>
                                     <div class="formControls">
                                         <#if info.refStatus! == "1" >
                                             <input info="text" class="input-text" value="待接诊" readonly>
										 <#elseif info.refStatus! == "2">
                                             <input type="text" class="input-text" value="已接诊" readonly>
										 <#elseif info.refStatus! == "3">
                                             <input type="text" class="input-text" value="已拒绝" readonly>
										 <#else >
											 <input type="text" class="input-text" value="待接诊" readonly>
										 </#if>

                                     </div>
                                 </li>

                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">反馈信息：</label>
                                    <div class="formControls">
                                        <input type="text" class="input-text" value="${info.feedback!}"  readonly>
                                    </div>
                                </li>
								   </#if>
							</ul>
						</div>
					</form>
				</div>
			</div>
			
	<div class="col-md-12">					
		<span class="l top-oprt-bar">
			<#if hosId! == info.orgIdTo!>
			      <input class=" btn btn-primary radius" type="submit"  onClick="save();" value="保存">
			</#if>
			<input class=" btn btn-primary radius" type="submit"  onClick="layer_close();" value="关闭">
		</span>
	</div>
	
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>		
	<script type="text/javascript">
		var globalVar = {};
		globalVar.base = '${base}';

        /**
         * 修改状态
         * @param data
         */
        function save() {
            var data={};
            data.id = $("#id").val();
            data.refStatus = $("#refStatus").val();
            data.feedback = $("#feedback").val();

            $.ajax({
                url:globalVar.base +"/referral/changeDetail",
                dataType:"json",
                contentType:"application/json",
                type:"post",
                data:JSON.stringify(data),
                success:function(data){
                    if(data.resultCode == 0){
                        layer.msg(data.errorMsg);
                        layer_close();
                    }else{
                        layer.msg(data.errorMsg);
                    }
                },
                error:function(){
                    initFlag = false;
                    layer.msg("网络连接异常");
                }
            });
        }
	</script>
	<script type="text/javascript" src="${base}/static/base/js/referral/detail.js"></script>
</body>
</html>