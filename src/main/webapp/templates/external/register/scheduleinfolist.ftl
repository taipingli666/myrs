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
		<span class="c-gray en">&gt;</span>系统设置
		<span class="c-gray en">&gt;</span>排班设置
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<input type="hidden" id="hosId" value="${hosId }">
			<!-- 搜索表单区域start -->
			<div class="row cl btn-line-top mb-20" style="position:relative; margin: 0;">
				<div class="panel-body nopd">
					<form  method="post" class="form form-horizontal form-examiner-info" id="contractOfConditions">
						<div class="row cl">
                            <ul>
                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">开始时间：</label>
                                    <div class="formControls">
                                        <input value="${startDate! }" name="startDate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd' })"  class="input-text Wdate">
                                    </div>
                                </li>
                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">结束时间：</label>
                                    <div class="formControls">
                                        <input value="${endDate! }" name="endDate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd' })"  class="input-text Wdate">
                                    </div>
                                </li>
                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">排班类型：</label>
                                    <div class="formControls">
                                       <select name="scheduleType" class="input-text">
										   <option value="">所有</option>
                                           <option value="0">预约挂号</option>
                                           <option value="1">远程会诊</option>
									   </select>
                                    </div>
                                </li>
                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">医院：</label>
                                    <div class="formControls skin-minimal" >
                                        <input  name="hosName" type="text" class="input-text" value="${scheduleInfo.hosName! }" >
                                    </div>
                                    <input type="hidden" class="input-text" value="">
                                </li>
                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">科室：</label>
                                    <div class="formControls">
                                        <input  name="deptName" type="text" class="input-text" value="${scheduleInfo.deptName! }" >
                                    </div>
                                </li>
                                <li class="col-md-6 col-lg-4">
                                    <label class="form-label">医生：</label>
                                    <div class="formControls">
                                        <input   name="doctorName" type="text" class="input-text" value="${scheduleInfo.doctorName! }" >
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
				<span class="l">
					<a class="btn btn-primary radius" data-title="添加" _href="article-add.html" onclick="addRow(0)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 新增</a>
					<a href="javascript:;" class="btn btn-danger radius" onclick="dels()"><i class="Hui-iconfont">&#xe6e2;</i> 删除</a>
					<a href="javascript:;" class="btn btn-primary radius" onclick="buildSchedulePage()"><i class="Hui-iconfont">&#xe600;</i> 生成排班</a>
				</span>
					<span class="r">共有数据：<strong>
				</strong>${page.totalRecord}条</span>
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
                            <th width="4%"><input type="checkbox" name="box1" value=""></th>
                            <th width="8%">排班类型</th>
                            <th width="20%">医院</th>
                            <th width="10%">科室</th>
                            <th width="10%">医生</th>
                            <th width="10%">日期</th>
                            <th width="5%">时段</th>
                            <th width="9%">时间</th>
                            <th width="8%">号源数量</th>
                            <#--<th width="">操作</th>-->
						</tr>
					</thead>
					<tbody id="tbody" class="text-c">
					<#list page.content as item>
                    <tr class="text-c" name="dicttr">
                        <td><input type="checkbox" data-id="${item.id!}" value="" name="box2" ></td>
                        <td>
							<#if item.scheduleType! == "0">
							预约挂号
							<#elseif item.scheduleType! == "1">
							远程会诊
							</#if>
						</td>
                        <td>${item.hosName!}</td>
                        <td>${item.deptName!}</td>
                        <td>${item.doctorName!}</td>
                        <td>${(item.workDate?string("yyyy.MM.dd"))!''}  </td>
                        <td>${item.workPeriod!}</td>
                        <td>${item.workTimeStart!}-${item.workTimeEnd!}</td>
                        <td>${item.registerQuantity!}</td>
                        <#--<td>-->
                            <#--<a onclick="addRow(${item.id!})" href="javascript:void(0)"><i class="Hui-iconfont"></i>修改</a>-->
                        <#--</td>-->
                    </tr>
					</#list>
					</tbody>
				</table>
			<@choiceSign.signpage page/>
			</div>
		</article>
<@choiceSign.footer />
	</div>

<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
    <script>
        var contextPath = '${base}';
        $(function(){
			getDataList();
		});
        function getDataList(){
			$.ajax({
			   url: contextPath+'/scheduleInfo/getDataList.do',
			   type: 'post',
			   dataType:'json',
			   //async:false,
			   data: $("#contractOfConditions").serializeArray(),
			   success:function(data){
			   		console.log(data);
			   		var list=data.content;
			   },
			   error:function(data){
			   }
			});
		}
    </script>
<script type="text/javascript" src="${base }/static/base/js/external/scheduleinfolist.js"></script>
</body>
    <div id="buildSchedule" style="display:none">
        <div class="panel-body">
            <div class="row">
                <div class="col-md-3 col-lg-2">
                    <label class="form-label">生成时间：</label>

                </div>
                <div class="col-md-6 col-lg-4">
                    <div class="formControls">
                        <input id="bulidDate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd',minDate:'%y-%M-%d' })"  class="input-text Wdate">
                    </div>
                </div>
                <div class="col-md-6 col-lg-4">
					<span class="top-oprt-bar">
						<button class="btn btn-success" type="button" onclick="buildSchedule()" id="buildScheduleBtn"><i class="Hui-iconfont">&#xe600;</i> 生成</button>
					</span>
                </div>
            </div>
        </div>
    </div>
</html>