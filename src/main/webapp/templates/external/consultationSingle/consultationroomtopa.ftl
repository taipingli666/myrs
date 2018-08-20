<#assign base=rc.contextPath />
<@choiceSign.header />
<@choiceSign.left />
 
 
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/doctor.css"/>
<title>会诊间</title>
<style>
	.clearfix:after {
	  visibility: hidden;
	  display: block;
	  font-size: 0;
	  content: " ";
	  clear: both;
	  height: 0;
	}
	.logopic h1{
		display:block;
		border-bottom:0;
		padding:0;
	}
	.video-a-report{
		width: 68%;
		position: absolute;
		left: 0;
		top: 0;
		bottom: 0;
		box-sizing: border-box;
		overflow: auto;
		border-right:1px #b3b3b3 solid;
	}
	.Hui-article{
		top:39px;
	}
	.black-default{
		border:1px #ccc solid;
		margin:20px;
		border-radius:5px;
		position:relative;
		padding-top:38px;
	}
	.opinionBox-hd,.report-event {
		margin:20px;
	}
	.opinionBox-hd .panel-default{
		border-radius:5px;
	}
	.black-default .panel-heading{
		position: absolute;
		top: 0;
		left: 0;
		right:0;
		background:#f1f1f1;
		border-bottom:1px #ddd solid;
		border-radius:5px 5px 0 0;
	}
	.black-default .panel-heading .panel-title{
		padding: 10px;
		font-size: 16px;
	}
	.black-default .patientInfo-3 p{
		float:left;
		width: 33%;
	}
	.opinionBox-text  textarea{
		width:100%;
		max-width:100%;
		border:1px #ddd solid;
		border-radius:5px;
	}
</style>
<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span>
		视频会诊管理
		<span class="c-gray en">&gt;</span>
		会诊间
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
		<div class="Hui-article" style="oveflow:hidden">
			<div class="pd-20">
			 	<!-- 隐藏 -->
				<input id="hidden_role" type="hidden" value="${role!''}" />
				<input id="hidden_socketUrl" type="hidden" value="${socketUrl!''}" />
				<input id="hidden_operIp" type="hidden" value="${operIp!''}" />
				<!-- end -->
				<div class="tab-content">
				    <!-- 视频 -->
				    <div  class="tab-pane active videoMainBox" id="show_video">
				        
				        <div class="listNav">
							<ul class="list-title">
								<li><span  class="select active">待诊列表</span></li>
	                            <li><span class="select">预约列表</span></li>
							</ul>
							<p class="videoMainBox-hint">当前共有0条排队记录</p>
					 	
						    <div class="listCon" style="position:absolute;top:70px;left:0;bottom:0;right:0;overflow-y:hidden">
								<ul class="list-itemNav" id="J_Tab01">
								<!--
									<li>
										<div class="waitItem">
											<div class="head">
		                                        <img src="" alt="">
												<p>在线</p>
											</div>
											<div class="head-detail clearfix">
													<p class="name">金华医生03</p>
													<p class="time">2号(14:10-14:20)</p>
													<p class="state text-blue">等待中</p>
											</div>
		                                    <div class="main clearfix">
												<span class="other-name">病人姓名:fghfh</span>
		
													<a href="##"   class="btn2 reportBtn">会诊报告</a>
													<a href="##" class="btn2">发起视频</a>
		
											</div>
		                                    <p style="font-size: 12px" class="other-name">医生医院:金华市中心医院&nbsp;&nbsp;&nbsp;&nbsp;电话:15162250500 </p>
										</div>
									</li>
		                            <li>
		                                <div class="waitItem">
		                                    <div class="head">
		                                        <img src="" alt="">
		                                        <p>在线</p>
		                                    </div>
		                                    <div class="head-detail clearfix">
		                                        <p class="name">金华医生01</p>
		                                        <p class="time">2号(14:10-14:20)</p>
		                                        <p class="state text-blue">等待中</p>
		                                    </div>
		                                    <div class="main clearfix">
		                                        <span class="other-name">病人姓名:fghfh</span>
		
		                                        <a href="##"   class="btn2 reportBtn">会诊报告</a>
		                                        <a href="##" class="btn2">发起视频</a>
		
		                                    </div>
		                                    <p style="font-size: 12px" class="other-name">医生医院:金华市中心医院&nbsp;&nbsp;&nbsp;&nbsp;电话:15162250500 </p>
		                                </div>
		                            </li>
		                            -->
								</ul>
		                        <ul class="list-itemNav" style="display:none" id="J_Tab02">
		                            <!--
		                            <li>
		                                <div class="waitItem">
		                                    <div class="head">
		                                        <img src="" alt="">
		                                        <p>在线</p>
		                                    </div>
		                                    <div class="head-detail clearfix">
		                                        <p class="name">金华医生01</p>
		                                        <p class="time">2号(14:10-14:20)</p>
		                                        <p class="state text-blue">等待中</p>
		                                    </div>
		                                    <div class="main clearfix">
		                                        <span class="other-name">病人姓名:fghfh</span>
		
		                                        <a href="##"   class="btn2 reportBtn">会诊报告</a>
		                                        <a href="##" class="btn2">发起视频</a>
		
		                                    </div>
		                                    <p style="font-size: 12px" class="other-name">医生医院:金华市中心医院&nbsp;&nbsp;&nbsp;&nbsp;电话:15162250500 </p>
		                                </div>
		                            </li>
		                            -->
		                        </ul>
							</div>
						 
					</div>
				        <div class="videoMain videoMain2">
				            <!-- ifram页面 -->
						    <div class="mainVideo" id="iFrame_div">
								<div class="video" >
									<#--<p style="font-size:20px">注意事项：若想分享桌面需安装谷歌浏览器并下载插件（若未下载安装过，请下载<a href="${base}/static/lib/RTCMultiConnection-master/Screen-Capturing_v3.4.crx">Screen-Capturing</a>和<a href="${base}/static/lib/RTCMultiConnection-master/WebRTC-Desktop-Sharing_v3.6.crx">WebRTC-Desktop-Sharing</a>插件，下载完成后,打开Chrome浏览器的扩展程序管理界面，选择在该界面的右上方的【开发者模式】按钮,把下载完成的插件拖拽到中心位置即可）
									
									</p>-->
								</div>
							 
							</div>
				            
				             <div  class="video-a-report" style="display: none">
				                    <div class="panel black-default" id="J_UserInfo">
				                        <div class="panel-heading">
				                            <h3 class="panel-title">会诊患者</h3>
				                        </div>
				                        <div class="panel-body">
				                            <div class="patientInfo-3 clearfix">
				                                <p>姓名：<span ></span></p>
				                            </div>
				                            <div class="patientInfo-3 clearfix">
				                                <p>性别：</p>
				                                <p>年龄：<span id="J_Age"></span></p>
				                                <p>出生日期：<span id="J_Birthday"></span></p>
				                            </div>
				                            <div class="patientInfo-3 clearfix">
				                                <p>身份证号：<span></span></p>
				                                <p>手机号：<span></span></p>
				                            </div>
				                        </div>
				                    </div>
				                    <div class="opinionBox">
				                        <div class="opinionBox-hd">病情描述:
				                            <div class="panel panel-default">
				                                <div class="panel-body">
				                                    <span id="bingqingms"></span>
				                                </div>
				                            </div>
				                        </div>
				                        <div class="opinionBox-hd">病史小结:
				                            <div class="panel panel-default">
				                                <div class="panel-body">
				                                    <span id="binglixx"></span>
				                                </div>
				                            </div>
				                        </div>
				                        <div class="opinionBox-hd">会诊意见:
				                            <div class="opinionBox-text">
				                                <textarea id="update_hzyj" name=""></textarea>
				                            </div>
				                        </div>
				                    </div>
				                    <div  class="report-event">
				                        <button type="button" onclick="up(0)" class="btn btn-primary">保存</button>
				                    </div>
				                </div>
				        </div>
				    </div>
				</div>
				<@choiceSign.footer />
			</div>
			
		</div>
</section>
 


<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base }/static/base/js/external/consultationroom.js"></script>
<!--<script type="text/javascript" src="${base }/static/base/js/external/indexsocket2.js"></script>-->
<script type="text/javascript" src="${base }/static/base/js/external/consultationroomtopa.js"></script>
<script type="text/javascript" src="${base }/static/base/js/external/jquery.nicescroll.js"></script>
<!--/请在上方写此页面业务相关的脚本-->
<script type="text/javascript">
	var globalVar = {};
	globalVar.base = '${base}';

	$(document).ready(function() {
        $(".video-a-report").niceScroll({cursorborder:"",cursorcolor:"#00adee",autohidemode:false});
  /*       $("#J_Tab02").niceScroll({cursorborder:"",cursorcolor:"#00adee",autohidemode:false});
        $(".videoMain2").niceScroll({cursorborder:"",cursorcolor:"#ccc",autohidemode:false}); */
    });
 
$('.select').each(function(index,ele){
	$(this).click(function(){
	 	$(this).addClass('active').parent().siblings().find('.select').removeClass('active');
        $('.list-itemNav').eq(index).css("display","block").siblings().css("display","none");
        getList(index+1);
	})
    
})
$('.listCon').niceScroll({
                cursorcolor: "#1a9fe0",//#CC0071 光标颜色
                cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0
                touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备
                cursorwidth: "5px", //像素光标的宽度
                cursorborder: "0", //   游标边框css定义
                cursorborderradius: "5px",//以像素为光标边界半径
                 autohidemode:true,//是否隐藏滚动条
            });
</script>



