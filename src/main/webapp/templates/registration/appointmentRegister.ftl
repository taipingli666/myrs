<#assign base=rc.contextPath />
 <base id="base" href="${base}">
<@choiceSign.header />

<@choiceSign.left />
<style>
	table{
		cursor:pointer;
	}
</style>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<meta name="renderer" content="webkit" />
	<title>转诊中心 - 转出病人列表</title>
    <link rel="stylesheet" href="${base}/static/lib/registration/bootstrap.min.css"/>
	<script src="${base}/static/lib/jquery/1.9.1/jquery.js"></script>
	<script type="text/javascript" src="${base}/static/lib/registration/bootstrap.min.js"></script>
	<script type="text/javascript" src="${base}/static/lib/registration/idvalidate.js"></script>
	<link rel="stylesheet" href="/css/styleReset.css"/>
<!--	<link rel="stylesheet" href="${base}/static/lib/registration/common.css"/>-->

</head>
<body>
	<div class="wrapper wrapper-content">
	<ul class="nav nav-tabs" id="myTab">
		<li class="active" id="show0click" disabled = 'true'><a href="#show0">选择医院</a></li>
		<li id="show1click"><a href="#show1" disabled = 'true'>选择科室</a></li>
		<li id="show2click"><a href="#show2">选择排版</a></li>
		<li id="show3click"><a href="#show3">预约挂号</a></li>
	</ul>
	<div class="tab-content">
		<!-- 医院介绍以及点击预约 -->
		<div id="show0" class="tab-pane active">
			
			<div  class="yiyuan_content_1">
				<!--<div  class="span1 container-top-left"></div>-->
				<div  class="span9 container-top-left">
					<!--<div style="float: left">-->
						<img class="hospitalImg" />
					<!--</div>-->
					<div class="hospitalMain" >
						<dl class="yiyuan_co_dl">
							<dd class="yiyuan_co_dd"  >
								<p class="yiyuan_co_titl">
									<b  class="hospital-name"></b>
									<span class="yiyuan_co_ddsp" " >三级甲等</span>
								</p>
								<div class="hospitalInfo">
									<p>医院性质：<b class="yiyuan_telico"></b><span></span></p>
									<p>医院地址：<b class="yiyuan_telico"></b><span></span></p>
									<p>医院电话：<b class="yiyuan_telico1"></b><span></span></p>
								</div>
							</dd>
						</dl>
					</div>
				</div>
				<div  class="btnSubscribe"  >
					<button type="button" data-toggle="modal"  id="auth" class="btn btn-primary " onclick="'javascript:checkHospatil();'" >
						点击预约
					</button>
				</div>
			</div>
		</div>
		<!-- 排班页面 -->
		<div id="show1"   class="tab-pane">
			<button onclick="fanye(1)" class="btn  btn-default btnBack" type="button">上一步</button>
			<h2 id="_yiyuanmc"></h2>
			<div id="firstpane" class="menu_list j_GetW">
			</div>
		</div>
		<!-- 选择医生 -->
		<div id="show2"   class="tab-pane doctorTimeBox">
			<button onclick="fanye(2)" class="btn btn-default btnBack" type="button">上一步</button>
			<p>按日期选择</p>
			<div class="scheduleForms clearfix">
				<div class="col-main j_Main">
					<div class="main-wrap ">
						<div class="scheduleTableBox j_MainWrap">
							<table class="scheduleTable">
								<tbody>
								<tr id="_am">

								</tr>
								<tr id="_pm">

								</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-sub j_Sub">
					<table class="tableLeft">
						<tbody>
						<tr>
							<td>上午</td>
						</tr>
						<tr>
							<td>下午</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
			<p>按医生选择</p>
			<div id="_doctorList" class="clearfix"></div>

		</div>
		<div id="show3"  class="tab-pane">
			<button onclick="fanye(3)" class="btn" type="button">上一步</button>
			<!-- 按医生预约的界面 -->
			<form style="margin-top: 50px">
				<input type="hidden" id="haoyuan_huanzheid" name="huanzheid" />
				<input type="hidden" id="haoyuan_menzhenwz" name="menzhenwz" />
				<div class="container-fluid">
					<div class="row">
						<div class="span12">
							<ul class="thumbnails">
								<li class="span4">
									<div class="thumbnail" style="width: 500px;height: 50px">
										<div class="container-fluid">
											<div class="row">
												<div class="col-md-6 haoyuan01"><span id="haoyuan_yishengmc" style="font-size: 25px"></span></div>
												<div class="col-md-6 text-right haoyuan01"><span id="haoyuan_yishengzc"></span></div>
											</div>
										</div>
									</div>
									<div class="thumbnail div02" >
										<div class="container-fluid">
											<div class="row form-group">
												<div class="col-md-6 haoyuan01">就诊日期</div>
												<div class="col-md-6 text-right haoyuan01"  style="padding-top: 3px;"><span id="haoyuan_jiuzhensj"></span></div>
											</div>
											<hr class="hr01"/>
											<div class="row form-group">
												<div class="col-md-6 haoyuan01">挂号科室</div>
												<div class="col-md-6 text-right haoyuan01"  style="padding-top: 3px;"><span id="haoyuan_guahaoks"></span></div>
											</div>
											<hr class="hr01"/>
											<div class="row form-group">
												<div class="col-md-6 haoyuan01">就诊序号</div>
												<div class="col-md-6 text-right haoyuan01"  style="padding-top: 3px;"><select class="haoyuan03 form-control" id="haoyuan_jiuzhenxh"></select></div>
											</div>
											<hr class="hr01"/>
											<div class="row form-group">
												<div class="col-md-6 haoyuan01">就诊人姓名</div>
												<div class="col-md-6 text-right haoyuan01"  style="padding-top: 3px;"><input class="haoyuan02 form-control" type="text" id="haoyuan_huanzhexm" name="huanzhexm" /></div>
											</div>
											<hr class="hr01"/>
											<div class="row form-group">
												<div class="col-md-6 haoyuan01">身份证</div>
												<div class="col-md-6 text-right haoyuan01"  style="padding-top: 3px;"><input class="haoyuan02 form-control" id="haoyuan_shenfenzh" name="shenfenzh" type="text" /></div>
											</div>
											<hr class="hr01"/>
											<div class="row form-group">
												<div class="col-md-6 haoyuan01">年龄</div>
												<div class="col-md-6 text-right haoyuan01"  style="padding-top: 3px;">
                                                    <input type="text" class="haoyuan02 form-control" id="haoyuan_nianling" name="nianling"/>
                                                </div>
											</div>
											<hr class="hr01"/>
											<div class="row form-group">
												<div class="col-md-6 haoyuan01">性别</div>
												<div class="col-md-6 text-right haoyuan01" style="padding-top: 3px;">
													<select class="haoyuan03 form-control" id="haoyuan_xingbie" name="xingbie">
														<option value="">-请选择-</option>
														<option value="1">男</option>
														<option value="2">女</option>
													</select>
												</div>
											</div>
											<hr class="hr01"/>
											<div class="row  form-group">
												<div class="col-md-6 haoyuan01">就诊卡号</div>
												<div class="col-md-6 haoyuan01">
                                                    <label class="sr-only" for="haoyuan_jiuzhenkahao"></label>
													<div class="input-group" style="padding-top: 6px;">
													    <input  class="haoyuan04 form-control"  id="haoyuan_jiuzhenkahao" name="jiuzhenkahao"  type="text"/>
													    <a href="javascript:void(0)" class="input-group-addon btn btn-info " onclick="checkKaHao()">检测</a>
												    </div>
                                                </div>
											</div>
											<hr class="hr01"/>
											<div class="row form-group">
												<div class="col-md-6 haoyuan01">电话号码</div>
												<div class="col-md-6 text-right haoyuan01"  style="padding-top: 3px;">
                                                    <input class="haoyuan02 form-control" type="text" id="haoyuan_shoujihao" name="shoujihao" />
                                                </div>
											</div>
											<hr class="hr01"/>
											<div class="row">
												<div class="col-md-6 haoyuan01">挂号费用</div>
												<div class="col-md-6 text-right haoyuan01" ><span id="haoyuan_guahaofy"></span></div>
											</div>
											<hr class="hr01"/>
											<div class="row">
												<div class="col-md-6 haoyuan01">支付方式</div>
												<div class="col-md-6 text-right haoyuan01">到院支付</div>
											</div>
											<br/>
											<div class="text-center form-group">
												<input  onclick="startYuYue()" class="btn btn-info" style="width: 100px" type="button" value="预约" />
											</div>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!--<input style="display: none" onclick="back()" id="back" type="button" value="上一步"/><br/>-->
	<!--<input style="display: none"  onclick="next()" id="next" type="button" value="下一步"/>-->
	</div>
	<script >
		$(function () {
		    $(".j_GetW").click(function () {
		        //点击科室时候获取宽度
                var tw=$(".wrapper-content").width()-$(".j_Sub").width();
                $(".j_MainWrap").width(tw);
            })

        })
	</script>
	<script type="application/javascript">
		/*<![CDATA[*/
        //全局变量
		var _date;//
        var keshibh;
        var hospital;
        var hospitalName;
        var now = 1;
        var apipath = "/api/register/";
        var doctors;
        var haoyuan;
        //高亮左侧对应的导航项
        $(function () {
            $('#myTab a:first').tab('show');//初始化显示哪个tab
            $('#myTab a').click(function (e) {
                e.preventDefault();//阻止a链接的跳转行为
				if($(this).parent().attr('id')=="show0click"){
                    $(this).tab('show');
				}
				if($(this).parent().attr('id')=="show1click"){
					//如果点击id是科室列表的标签,判断医院的全局变量是否为空,如果不为空,才能跳转到科室列表
					if(hospital != null){
                        $(this).tab('show');
					}else{
                        layer.msg('请先选择医院');
					}
            	}
                if($(this).parent().attr('id')=="show2click"){
                    //如果点击id是排班的标签,判断科室的全局变量是否为空
                    if(keshibh != null){
                        $(this).tab('show');
                    }else{
                        layer.msg('请先选择科室');
                    }
                }
                if($(this).parent().attr('id')=="show3click"){
                    //如果点击id是排班的标签,判断科室的全局变量是否为空
                    if(haoyuan != null){
                        $(this).tab('show');
                    }else{
                        layer.msg('请先选择预约日期及医生');
                    }
                }
            })
            addDate();
            //给身份证绑定失去焦点事件
            $("#haoyuan_shenfenzh").blur(function () {
				if(checkShenFenZheng($("#haoyuan_shenfenzh").val())){
				   //说明验证正确
                    //说明身份证信息合法
                    //给年龄和性别赋值
                    // discriCard($("#card_id").val());
                    discriCard($("#haoyuan_shenfenzh").val());
				}else{
				    //说明验证错误
                    layer.msg('抱歉,身份证不合法');
                    $("#haoyuan_shenfenzh").focus();
				}
            });
        })
        //点击医院
        function  checkHospatil(orgCode,name) {
            $('#alertTest').modal({
                backdrop:true,
                keyboard:true,
                show:true
            });
            hospital = orgCode;
            hospitalName = name;
            //赋值医院名称
            $("#_yiyuanmc").html(hospitalName);
            var index = layer.load(1);
            $.post( apipath+"getDeptlist", {"orgCode":orgCode}, function (data) {
                layer.close(index);
                var st = "";
                if(data.success == true){
                    $(data.data).each(function(m,n){
                        //一级菜单
                        st+='<h3 class="menu_head ">'+n.keShiName+'</h3>';
                        st+='<div style="display:none" class="menu_body">';
                        if(n.list!=null){
                            //二级菜单
                            $(n.list).each(function(m,n){
                                st+='<a  onclick="selectKeShi(\''+n.keshibh+'\')" href="javascript:void(0)">'+n.keshimc+'</a>';
                            });
                        }
                        st+='</div>';
                    });
                }else{
                    layer.msg("加载科室出错");
				}
                $('#myTab a[href="#show1"]').tab('show');
                $("#firstpane").html(st);
                dynamicLoading.js("/js/keshi/keshi.js");
            },"json");
        }
        //点击科室,加载科室排班
        function selectKeShi(id){
            //清除原先的已选择div
			$("#show2").find("td").each(function () {
				if($(this).hasClass("active")){
                    $(this).removeClass("active");
				}
            })
            keshibh = id;
            var index = layer.load(1);
            $.post(apipath+"doctorList",{"keshibh":id,"jigoubh":hospital},function(data){
                layer.close(index);
                if(data.success==true){
                    //说明获取成功,切换到排班页面
                    doctorList(data.data);
                    $('#myTab a[href="#show2"]').tab('show');
                }else{
                    layer.msg(data.message);
				}
            },"json");
        }
        function addDate(){
            var datestr;
            var date = new Array();
            var myDate = new Date($("#time_day").val());
            $("#_date").append("");
            for (var i = 0; i < 7; i++) {
                datestr=Number(+myDate.getMonth()) + 1 + "-" + myDate.getDate();
                var arr = datestr.split("-");
                if(arr[0].length==1){
                    arr[0] = "0"+ arr[0];
                }
                if(arr[1].length==1){
                    arr[1] = "0"+ arr[1];
                }
                datestr = arr[0] + "-" + arr[1];
                date.push(arr[0] + "-" + arr[1]);
                var _day = myDate.getDay();
                myDate.setTime(myDate.getTime() + 1000*60*60*24);
                var _time = $("#time_hour").val();
                var str;
                if (_day == 0) {
                    str = "周日";
                } else if (_day == 1) {
                    str = "星期一";
                } else if (_day == 2) {
                    str = "星期二";
                } else if (_day == 3) {
                    str = "星期三";
                } else if (_day == 4) {
                    str = "星期四";
                } else if (_day == 5) {
                    str = "星期五";
                } else if (_day == 6) {
                    str = "星期六";
                }
                if(i ==0 && _time>=11.5){
                    $("#_am").append('<td><span>'+datestr+'&nbsp;&nbsp;&nbsp;'+str+'</span></td>');
				}else{
                    $("#_am").append('<td ><a  onclick="paiBanByDate(\''+datestr+',AM\',this)">'+datestr+'&nbsp;&nbsp;&nbsp;'+str+'</a></td>');
				}
				if(i ==0 && _time>=17){
                    $("#_pm").append('<td><span>'+datestr+'&nbsp;&nbsp;&nbsp;'+str+'</span></td>');
                }else{
                    $("#_pm").append('<td ><a  onclick="paiBanByDate(\''+datestr+',PM\',this)">'+datestr+'&nbsp;&nbsp;&nbsp;'+str+'</a></td>');
				}
            }
			_date = date;
        }
        function doctorList(data){
            doctors = data;
            $("#_doctorList").html("");
            $(data).each(function(m,n){
                var st = "";
                st += '<div class="subscribeDoctor j_DoctorBox"><a onclick="daysPaiBan(\''+n.yishengbh+'\')" href="javascript:void(0)"><div class="doctoritem clearfix">';
                st += '<div style="float: left;"><img style="width: 90px;height: 99px;" src="/img/doctor.jpg"/></div>';
                st += '<div class="documentInfo"><b>'+n.yishengxm+'</b><br/>';
                st += '科室:&nbsp;&nbsp;'+n.keshimc+'</div>';
                st += '</div></a></div>';
                st += '<div class="doc" id="doctor_'+n.yishengbh+'" style="display:none; margin:0 0 20px">'
                $("#_doctorList").append(st);
            });
        }
        function daysPaiBan(yishengbh){
            $(doctors).each(function(m,n){
                if(n.yishengbh == yishengbh){
                    $("#doctor_"+yishengbh).css("display","block");
                    var am = "";
                    var pm = "";
                    $("#doctor_"+yishengbh).html('');
                    var table = '<div class="scheduleForms clearfix"><div class="col-main"><div class="main-wrap"><div class="scheduleTableBox"><table class="scheduleTable  j_MainWrap2"><tbody>';
                    var oneLine = '<tr></tr>';
                    var twoLine = '<tr>';
                    var threeLine = '<tr>';
                    $(_date).each(function(x,i){
                        oneLine += '<td>'+i+'</td>';
                        var _zuozhenshijian;
                        var change1 = 0; //控制器
                        var change2 = 0; //控制器
						var change3 = 0;//控制重复
						var change4 = 0;//控制重复
                        $(n.list).each(function(m,n){
                            _zuozhenshijian = n.zuozhenrq.indexOf("-");
                            if(change3 == 0 ){
                                if(i == n.zuozhenrq.substring(_zuozhenshijian+1)&&n.menzhensdbm=="AM"&&$("#time_hour").val()>11.5&&x==0){
                                    twoLine += '<td>预约</td>';
                                    change1 = 1;
                                    change3 = 1;

                                }else if(i == n.zuozhenrq.substring(_zuozhenshijian+1)&&n.menzhensdbm=="AM"&&change1 == 0){
                                    twoLine += '<td><a href="javascript:void(0)" onclick="getHaoYuan(\''+n.yuanneipbid+'\')">预约</a></td>';
                                    change1 = 1;
                                    change3 = 1;
                                }
							}
                            if(change4 == 0){
                                if(i == n.zuozhenrq.substring(_zuozhenshijian+1)&&n.menzhensdbm=="PM"&&$("#time_hour").val()>17&&x==0){
                                    threeLine += '<td>预约</td>';
                                    change2 = 1;
                                    change4 = 1;
                                }else if(i == n.zuozhenrq.substring(_zuozhenshijian+1)&&n.menzhensdbm=="PM"&&change2 == 0){
                                    threeLine += '<td><a href="javascript:void(0)" onclick="getHaoYuan(\''+n.yuanneipbid+'\')">预约</a></td>';
                                    change2 = 1;
                                    change4 = 1;
                                }
							}
                        });
                        change3 = 0;
                        change4 = 0;
                        if(change1 == 0 ){
                            twoLine += '<td></td>';
						}else{
                            change1 = 1 ;
						}
                        if(change2 == 0 ){
                            threeLine += '<td></td>';
                        }else{
                            change2 = 1 ;
                        }
					});
                    oneLine +='</tr>';
                    twoLine +='</tr>';
					threeLine +='</tr>';
                    table += oneLine + twoLine + threeLine;
                    table +='</tbody></table></div></div></div><div class="col-sub j_Sub2"><table class="tableLeft"><tbody><tr><td>日期</td></tr><tr><td>上午</td></tr><tr><td>下午</td></tr></tbody></table></div></div>';
                    $("#doctor_"+yishengbh).append(table);
                    //#region修改动态加载table宽度
                    if(($(window).width())>760){
                        return false;
					}else {
                        var tw2=($(".j_DoctorBox").width()-$(".j_Sub2").width());
                        $(".j_MainWrap2").width(tw2);
                        $(".j_DoctorBox").width($(".j_DoctorBox").width());
					}
                    //#endregion修改动态加载table宽度
                    return false;
                }



            });
        }
        function paiBanByDate(date,th){
            if($(th).parent().hasClass("active")){
                return
			}else {
                $(th).parent().addClass("active").siblings().removeClass("active");
               $(th).parent().parent().siblings().children("td").removeClass("active");
			}
            var myDate = new Date();
            date = Number(+myDate.getFullYear())+"-"+date;
            var index = layer.load(1);
            $.post(apipath+"getPaiBanByDate",{"keshibh":keshibh,"jigoubh":hospital,"zuozhenrq":date},function(data){
                layer.close(index);
                if(data.success == true){
                    $("#_doctorList").html("");
                    $(data.data).each(function(m,n){
                        var st = "";
                        st += '<div style="width: 500px;height: 100px; border: solid 1px #E1E1E1; background: #F1F1F1;">';
                        st += '<div style="float: left;"><img style="width: 90px;height: 100px;" src="/img/doctor.jpg"/></div>';
                        st += '<div class="documentInfo"><b>'+n.yishengxm+'</b><a onclick="getHaoYuan(\''+n.yuanneipbid+'\')" href="javascript:void(0)">预约</a><br/>';
                        st += '科室:&nbsp;&nbsp;'+n.keshimc+'</div>';
                        st += '</div>';
                        $("#_doctorList").append(st);
                    });
                }else{
                    $("#_doctorList").html("");
                    layer.msg("暂无排班");
				}
            },"json");
        }
        function getHaoYuan(id){
            var index = layer.load(1);
            $.post(apipath+"getHaoYuan",{"jigoubh":hospital,"yuanneipbid":id},function(data){
                layer.close(index);
                if(data.success==true){
                    //给号源界面赋值,给全局变量号源赋值
                    haoyuan = data.data;
                    $("#haoyuan_yishengmc").html(data['data'].yishengxm);
                    $("#haoyuan_yishengzc").html(data['data'].guahaolx);
                    $("#haoyuan_guahaofy").html(data['data'].guahaofy+"元");
                    $("#haoyuan_jiuzhensj").html(data['data'].zuozhenrq);
                    $("#haoyuan_guahaoks").html(data['data'].keshimc);
                    //先清空
                    $("#haoyuan_jiuzhenxh").html("");
                    //判断是否是今日的日期
                    var d1 = data['data'].zuozhenrq;
                    var now = $("#time_hour2").val();
					if(d1 == $("#time_day")){
					    //日期相同
						//判断有没有超过15分钟
                        var min = 15*60*1000;
                        var data1 = new Date(now.replace(/-/g,"/"));
                        var data2 = null;
                        $(data['data'].lsit).each(function(m,n){
                            var s = d1+" "+ n.kaishishijian;
							data2 = new Date(s.replace(/-/g,"/"));
                            if(data2.getTime()-data1.getTime()>=min){
								//超时,什么都不做
							}else {
                                $("#haoyuan_jiuzhenxh").append('<option value="'+n.shunxuhao+','+n.kaishishijian+'-'+n.jieshushijian+'">'+n.shunxuhao+'号  '+n.kaishishijian+'-'+n.jieshushijian+'</option>');
							}
                        });
					}else{
                        $(data['data'].lsit).each(function(m,n){
                            $("#haoyuan_jiuzhenxh").append('<option value="'+n.shunxuhao+','+n.kaishishijian+'-'+n.jieshushijian+'">'+n.shunxuhao+'号  '+n.kaishishijian+'-'+n.jieshushijian+'</option>');
                        });
					}
                    //成功,跳转界面
                    $('#myTab a[href="#show3"]').tab('show');
                }else{
                    layer.alert(data.message);
				}
            },"json")
        }
        function startYuYue(){
            //预约前先做数据校验
            var message = checkSubmit();
            if(message!=null&&message.length>1){
                ///说明数据校验有问题
                layer.msg(message);
                return;
			}
            //开始预约的方法,haoyuan 是一个jason对象,拼接其他的参数
			//获取出生日期
            var birthday;
			if($("#haoyuan_shenfenzh").val()!=null&&$("#haoyuan_shenfenzh").val()!=""){
                birthday = $("#haoyuan_shenfenzh").val().substring(6, 10) + "/" + $("#haoyuan_shenfenzh").val().substring(10, 12) + "/" + $("#haoyuan_shenfenzh").val().substring(12, 14);
                var split = birthday.split("/");
                if(split[1].substring(0,1)=="0"){
                    split[1] = split[1].substring(1);
				}
				if(split[2].substring(0,1)=="0"){
                    split[2] = split[2].substring(1);
				}
                birthday = split[0]+"/"+split[1]+"/"+split[2];
            }
            haoyuan.chushengrq = birthday;
			haoyuan.yiyuanmc = hospitalName;
            haoyuan.huanzheid = $("#haoyuan_huanzheid").val();
            haoyuan.huanzhexm = $("#haoyuan_huanzhexm").val();
            haoyuan.xingbie = $("#haoyuan_xingbie").val();
            haoyuan.nianling = $("#haoyuan_nianling").val();
            haoyuan.shenfenzh = $("#haoyuan_shenfenzh").val();
            haoyuan.shoujihao = $("#haoyuan_shoujihao").val();
            var arr = $("#haoyuan_jiuzhenxh").val().split(",");
            haoyuan.shunxuhao = arr[0];
            haoyuan.yuyuejiuzhensjd = arr[1];
            haoyuan.jiuzhenkahao = $("#haoyuan_jiuzhenkahao").val();
            $.post(apipath+"yuyYue",haoyuan,function (data) {
				if(data.success == true){
                    layer.msg(data.message);
				    //跳转到列表页面
					window.location.href='/appointmentCenter';
				}else{
                    layer.msg(data.message);
				}
            },"json");
        }
        function checkKaHao(){
            var kahao = $("#haoyuan_jiuzhenkahao").val();
            var name = $("#haoyuan_huanzhexm").val()
            $.post(apipath+"checkYiYuanId",{"huanzhexm":name,"kahao":kahao,"jigoubh":hospital},function (data) {
                if(data.success==true){
                    //说明卡号正确,赋值
                    layer.msg('验证卡号正确');
                    $("#haoyuan_huanzheid").val(data.data);
                }else{
                    layer.msg(data.message);
				}
            },"json");
        }
        function fanye(i){
			if(i==1){
                $('#myTab a[href="#show0"]').tab('show');
			}else if(i==2){
                $('#myTab a[href="#show1"]').tab('show');
			}else if(i==3){
                $('#myTab a[href="#show2"]').tab('show');
			}
		}
		//上传前先校验数据
		function checkSubmit(){
            var message = "";//1是成功
			if($("#haoyuan_huanzhexm").val()==null||$("#haoyuan_huanzhexm").val()==""){
			    //说明没有填姓名
                message += "姓名不能为空<br/>";
			}
			if($("#haoyuan_xingbie").val()==null||$("#haoyuan_xingbie").val()==""){
				//没填性别
                message += "性别不能为空<br/>";
			}
			if($("#haoyuan_nianling").val()==null||$("#haoyuan_nianling").val()==""){
				//没填年龄
                message += "年龄不能为空<br/>";
			}
			if($("#haoyuan_shenfenzh").val()==null||$("#haoyuan_shenfenzh").val()==""){
			    //没填身份证号
                message += "身份证不能为空<br/>";
			}else{
			    if(!checkShenFenZheng($("#haoyuan_shenfenzh").val())){
                    message += "身份证不合法<br/>";
				}
			}
			if($("#haoyuan_jiuzhenkahao").val()==null||$("#haoyuan_jiuzhenkahao").val()==""){
				//没填就诊卡号
                message += "就诊卡号不能为空<br/>";
			}
			return message;
		}
		//验证身份证合法性
		function checkShenFenZheng(shenfenzheng){
            if (shenfenzheng != null && shenfenzheng != "") {
                //首先判断身份证是否合法
                var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
                if (!IdentityCodeValid(shenfenzheng)) {
                    return false;
                }
                return true;
            }
		}
		//根据身份证提取年龄和性别
        function discriCard(UUserCard) {
            var _options = $("#haoyuan_xingbie")[0].options;
            //获取出生日期
            UUserCard.substring(6, 10) + "/" + UUserCard.substring(10, 12) + "/" + UUserCard.substring(12, 14);
            //获取性别
            if (parseInt(UUserCard.substr(16, 1)) % 2 == 1) {
                //是男则执行代码 ...
                $(_options).each(function (m, n) {
                    if (n.value == "1") {
                        $(n).attr("selected", "selected");
                    }
                });
            } else {
                //是女则执行代码 ...
                $(_options).each(function (m, n) {
                    if (n.value == "2") {
                        $(n).attr("selected", "selected");
                    }
                });
            }
            //获取年龄
            var myDate = new Date();
            var month = myDate.getMonth() + 1;
            var day = myDate.getDate();
            var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
            if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
                age++;
            }
            $("#haoyuan_nianling").val(age);
            //年龄 age
        }

		/*]]>*/
	</script>
</body>
</html>