<#assign base=rc.contextPath />
<@choiceSign.header />
<@choiceSign.left />
 
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/external/appointment-check.css"/>
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/external/telemedicine.css" />
<!--<link rel="stylesheet" type="text/css" href="${base}/static/base/css/external/modal.css"/>-->
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/external/uploadd.css"/>
<link rel="stylesheet" href="${base}/static/lib/bootstrapvalidator/css/bootstrapValidator.min.css"/>
<script type="text/javascript" src="${base}/static/lib/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<title>发起预约</title>
<style>
.arrangeBox{
margin:0;
}

</style>
<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span>
		远程会诊
		<span class="c-gray en">&gt;</span>
		发起预约
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article" style="overfow:visible">
		<!-- 隐藏存值区 start -->
		<input type="hidden" id="hidde_operId" value="${operId}"/>
		
		<!-- 隐藏存值区 end -->
		<div>
			<div >
				<div >
					  <label for="">
							<div class="toprate" id="J_Progress"  >
						        <div class="straight-line j_StraightLine"><span class="mask-line j_MaskLine"></span></div>
						        <ul class="progress-list clearfix j_Progress">
						            <li class="pass">
						            <p class="title-top">选择科室医生</p>
						            <div class="num">1</div>
						            </li>
						            <li>
						            <p class="title-top">填写问诊资料</p>
						            <div class="num">2</div>
						            </li>
						            <li>
						            <p class="title-top">预约成功</p>
						            <div class="num">3</div>
						            </li>
						        <!--    <li><div class="num">4</div><p class="title">支付问诊费用</p></li>
						            <li><p class="title-top">等待医生接诊</p><div class="num">5</div></li>
						            <li><div class="num">6</div><p class="title">查看医生会诊报告</p></li>-->
						            <!--<li><div class="num">4</div><p class="title">支付成功</p></li>-->
						            <!--<li><p class="title-top">查看医生会诊报告</p><div class="num">5</div></li>-->
						        </ul>
						    </div>
					  </label>
				</div>
	  			<div class="appointment">
		  			<div class="arrangeBox" id="arrangeBox" >
						<div class="conditionBox">
				            <div class="hospital">
				                <!-- 全局日期 -->
				                <input id="time_day" type="hidden" value="${day}" />
				                <!-- 二维码地址 -->
				                <input id="erweimaurl" type="hidden" value="${erweimaurl}" />
				                <input id="hid_liushui" type="hidden" value="${(single.liushuihao)!}"/>
				                <h3 class="label">医院</h3>
				                <ul class="hospital-list clearfix j_Tab">
				                	<#list channelHospitals as item>
				                		<#if item_index==0>
				                        	<li >
					                            <input id="startHosId" value="${item.hosId}" type="hidden"  />
					                            <a href="#" onclick="javascript:changeParame(1,'${item.hosId}')"  class="active" >${item.hosName}</a>
					                        </li>
				                        <#else>
				                             <li>
				                               <input id="startHosId" value="${item.hosId}" type="hidden"  />
				                        		<a href="#" onclick="javascript:changeParame(1,'${item.hosId}')" >${item.hosName}</a>
					                        </li>
				                        </#if>
				                  	</#list>
				                </ul>
				            </div>
				            <div class="hospital hospital-department">
				                <h3 class="label">科室</h3>
				                <ul class="hospital-list clearfix j_Tab" id="keshiUl">
				                    <!--<li>
				                        <a href="#" class="active" title="金华市中心医院">全部</a>
				                    </li>
				                    <li>
				                        <a href="#" title="金华市中心医院">内科</a>
				                    </li>
				                    <li>
				                        <a href="#" title="金华市中心医院">皮肤科</a>
				                    </li>-->
				                </ul>
				            </div>
				            <div class="hospital condition-time">
				                <h3 class="label">预约日期</h3>
				                <ul class="hospital-list clearfix j_Tab" id="dateUl">
				                    <!--  <li>
				                          <a href="#" class="active">9月7日</a>
				                      </li>-->
				                </ul>
				            </div>
				            <div class="hospital condition-time">
				                <h3 class="label">预约午别</h3>
				                <ul class="hospital-list clearfix j_Tab">
				                    <li>
				                        <a href="javascript:void(0);" class="active" onclick="changeParame(4,'AM')">上午</a>
				                    </li>
				                    <li>
				                        <a href="javascript:void(0);" onclick="changeParame(4,'PM')">下午</a>
				                    </li>
				                </ul>
				            </div>
				        </div>
				        <div class="consultation-doctor">
			                <ul class="doctor-list clearfix" id="doctorUl">
			
			                    <!--<li class="item">
			                        <div class="docimg"></div>
			                        <div class="docname">丁颖果</div>
			                        <div class="deptname" title="皮肤科专家门诊">皮肤科专家门诊</div>
			                        <div class="docgoodin" title="简介：1994-2004年就读于北京大学医学部，获临床医学学士、硕士学位和皮肤病与性病学博士学位。1999至2004年在北京大学第一医院皮肤科担任住院医师工作。2004年8月起就职于浙江大学医学院附属第一医院皮肤科。">简介：1994-2004年就读于北京大学医学部，获临床医学学士、硕士学位和皮肤病与性病学博士学位。1999至2004年在北京大学第一医院皮肤科担任住院医师工作。2004年8月起就职于浙江大学医学院附属第一医院皮肤科。</div>
			                        <div class="orderdocbut">预约(9个号)</div>
			                    </li>-->
			                </ul>
			                <div id="loading">
			                    <!--<div class="morefamdoc"><span id="J_More">更多专家</span></div>-->
			                </div>
			            </div>
		  			</div>
					<!--<div class="submit">
						<a href="javascript:;" id="submit">提交</a>
					</div>-->
				</div>
				
				
				
				
				
				<div class="writeInfoBox" id="writeInfoBox" style="display: none;">
			        <form id="saveForm">
			            <!-- 隐藏域区 -->
			            <input id="save_yuyuesj" type="hidden" name="yuyuesj" />
			            <input id="save_liushuihao" name="liushuihao" type="hidden" value="${(single.liushuihao)!}"/>
			            <input id="save_hyid" name="hyid" type="hidden" />
			            <input id="save_paibanId" name="paibanId" type="hidden"  value="${(single.huizhenpbid)!}" />
			            <ul class="subscribeInfo clearfix">
			                <li class="item">
			                    <label class="title">预约科室:</label>
			                    <input value="${(single.huizhenksmc)!}" id="keshimc" name="huizhenksmc" class="form-control text-input" readonly="readonly" type="text" />
			                </li>
			                <li class="item">
			                    <label class="title">预约医生:</label>
			                    <input value="${(single.huizhenysmc)!}"  id="yishengxm"  name="huizhenysmc" class="form-control text-input" readonly="readonly" type="text" />
			                </li>
			                <li class="item">
			                    <label class="title">预约日期:</label>
		                    	<input value=""  id="paibanrq"  name="paibanrq" class="form-control text-input" readonly="readonly" type="text" />
			                </li>
			                <li class="item">
			                    <label class="title">预约午别:</label>
			                    <input  id="shangxiawubz" class="form-control text-input" readonly="readonly" type="text" />
			                </li>
			                
			                <li class="item form-group">
			                    <label class="title">预约号源:</label>
			                    <select id="J_List"  class="form-control text-select j_SelectList">
			                    </select>
			                    <span class="refresBtn icon-undo" onclick="refreshSource()"></span>
			                    <input name="yuyuexh" id="J_SelectVal" class="input-text form-control"   type="hidden" />
			                </li>
			            </ul>
			            <div class="needwrite clearfix">
			                <ul class="subscribeInfo">
			                    <li class="item form-group">
			                        <label class="title">患者姓名:</label>
			                        <input  name="xingming" class="form-control text-input" type="text" />
			                    </li>
                                <li class="item form-group">
                                    <label class="title">年龄:</label>
                                    <input  name="age" class="form-control text-input" type="text" />
                                </li>
                                <li class="item form-group">
                                    <label class="title">性别:</label>
                                    <select id="sexSelect"  name ='sexSelect' class="form-control text-select" >
                                        <option value="1">男</option>
                                        <option value="2">女</option>
                                    </select>
                                </li>
			                    <li class="item form-group">
			                        <label class="title">手机号:</label>
			                        <input  name="shoujihm" class="form-control text-input" type="text" />
			                    </li>
			                    <li class="item form-group">
			                        <label class="title">身份证号:</label>
			                        <input  name="bingrenid" id="shenfenzh" class="form-control text-input" type="text" />
			                        <input  name="chushengrq" id="chushengrq" class=" text-input" type="hidden" />
			                        <input  name="xingbie" id="xingbie" class=" text-input" type="hidden" />
			                    </li>

			                    <#--<li class="item form-group videoZl" style="position: relative;">
			                        <label class="title">影像资料编号:</label>
			                        <div class="form-control text-textarea2" id="nrList" style="border:1px solid #666;padding-right: 42px;box-sizing: border-box;"></div>
			                        <!--                  <input type="text" class="form-control text-input" name="yybh" readonly="readonly" />&ndash;&gt;
			                        <button type="button" onclick="addVideoN()" class="addText">+</button>
			                    </li>-->
			                </ul>
			                <ul class="summarize-field  summarize-xs clearfix">
			                    <li class="item form-group">
			                        <label class="title">病情描述:</label>
			                        <textarea name="bingqingms" class="form-control text-textarea"></textarea>
			                    </li>
			                    <li class="item form-group">
			                        <label class="title">病史小结:</label>
			                        <textarea name="binglixx" class="form-control text-textarea"></textarea>
			                    </li>
			                </ul>
			                <ul class="summarize-field  summarize-xs clearfix">
			
			                </ul>
			            </div>
			        </form>
			        <!-- 二维码 -->
			        <div class="uploadingBox clearfix" align="center">
			            <div id="imgs" class="imgsList"></div>
			            <div class="uploadingBox-main" id="_erweima01">
			                <h2 class="title">资料上传</h2>
			                <button style="margin-bottom: 10px"  onclick="sctpUI()" type="button"  class="btn btn-primary"  data-toggle="modal" data-target="myModal">本地文件上传</button>
			                <div class="uploading-qrcode " id="qrcode"></div>
			            </div>
			        </div>
			        <!-- 文件上传 -->
			         
			<!-- 文件上传 -->
        <div class="modal fade" id="myModal"  role="dialog" aria-labelledby="J_ImgUploading">
            <div class="modal-dialog" role="document" style="width:730px">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">照片上传</h4>
                    </div>
                    <div class="modal-body popupBox">
                        <!--          <input  class="form-control" name="optionid" id="optionId" type="hidden" />-->
                        <div style="width: 100%;position: relative;">
                            <div class="fileBox">

                                <p class="fileInputP vm">
                                    <i>选择文件</i>
                                    <input type="file" multiple="multiple" id="fileInput" />
                                </p>
                                <!--  <span id="fileSpan" class="vm">或者将文件拖到此处</span>-->
                                <div class="mask"></div>
                            </div>
                            <div class="fileList"></div>
                        </div>
                    </div>
                    <div  class="modal-footer " id="formEvent2">
                        <button type="button" id="fileBtn" class="btn btn-primary">上传</button>
                        <!--     <button type="button" onclick="add()" id="J_addinfo2" class="btn btn-primary">保存</button>-->
                    </div>
                </div>
            </div>
        </div>

        <!-- 文件上传end -->
        <!--<div class="container-fluid row-fluid" >-->
            <!--<div class="span2 container-top">-->
            <!--</div>-->

        <!--</div>-->
			        <!-- 文件上传end -->
			        <!--<div class="container-fluid row-fluid" >-->
			            <!--<div class="span2 container-top">-->
			            <!--</div>-->
			
			        <!--</div>-->
			        <div class="container-fluid row-fluid">
			            <div class="span2 container-top">
			            </div>
			            <div class="span10 row-fluid">
			                <input type="hidden" id="showImgId" name="showImgId" value=""/>
			                <div id="showImg"></div>
			            </div>
			        </div>
			        <!-- 二维码end -->
			        <div class="event-btns clearfix" align="center">
			            <input class="btn-blue" onclick="changeUI(2)" type="button" value="上一步"/>
			            <input class="btn-blue"  onclick="submitF()" type="button" value="保存"/>
			        </div>
			    </div>
			    <!-- 预约成功界面 -->
			    <div id="successBox" class="subscribeHint" style="display: none">
			        <div class="title">预约成功</div>
			        <ul class="hintList j_hintList">
			        </ul>
			        <div class="subscribeHint-event">
			            <!--\<a href="javascript:void(0);" onclick="cancel()" class="btn btn-yellow">取消预约</a>
			            <a href="javascript:void(0)" onclick="goPay()" class="btn btn-blue">支付</a>-->
			            <a href="javascript:void(0)" onclick="lookSingle()" class="btn btn-blue">查看会诊单</a>
			        </div>
			    </div>
			    <div class="video-a-report" id="iFrame_div">
			    	
				</div>
			</div>
			<@choiceSign.footer />
		</div>
	</div>
</section>
<!--_footer 作为公共模版分离出去
<script type="text/javascript" src="${base}/static/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/lib/layer/2.4/layer.js"></script>-->
<!--/_footer /作为公共模版分离出去-->
<script type="text/javascript">
	var globalVar = {};
	globalVar.base = '${base}';
</script>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base}/static/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/static/base/js/external/consultationregister.js"></script>
<script type="text/javascript" src="${base}/static/base/js/external/jquery-qrcode-master/qrcode.min.js"></script>
<script type="text/javascript" src="${base}/static/base/js/external/uploadd.js"></script>


