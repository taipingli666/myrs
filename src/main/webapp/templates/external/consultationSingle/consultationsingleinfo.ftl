<#assign base=rc.contextPath />
<base id="base" href="${base}">
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/external/bootstrap.css"/>
 <script type="text/javascript" src="${base}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
 <script type="text/javascript" src="${base}/static/lib/bootstrap.min.js"></script>
 <script type="text/javascript" src="${base }/static/lib/layer/2.4/layer.js"></script>
<link rel="stylesheet" href="${base}/static/lib/photoswipe/photoswipe.css"/>
<link rel="stylesheet" href="${base}/static/lib/photoswipe/default-skin/default-skin.css"/>
<script src="${base}/static/lib/photoswipe/photoswipe.min.js"></script>
<script src="${base}/static/lib/photoswipe/photoswipe-ui-default.min.js"></script>
<script src="${base}/static/lib/photoswipe/creatPhotoswipe.js"></script>
<style>
    html,body{
        height: 100% !important;
        overflow: hidden !important;
        padding:0 !important;
    }
    .main2{
        height: 100%;
        -webkit-overflow-scrolling: touch;
    }
    .panel-default {
        margin: 20px;
    }
    .sheetInfo-main .panel-default {
        margin:0 0 20px;
    }
    .main2 .sheetInfo-hd{
     font-size:2em;
     text-align:center;
     line-height:3em;
     color:#ee9822;
    }
.sheetInfo-main .imgBox-list .imgShow-event {
    width: 10em;
    height: 10em;
    border-radius: 5px;
    float: left;
    background: #fff;
    position: relative;
    margin-right: 2.25em;
    margin-bottom: .625em;
    overflow: hidden;
}
.sheetInfo-main .imgBox-list .imgShow-event img {
    height: 100%;
    left: -1%;
    position: absolute;
    right: -1%;
    width: 100%;
    z-index: 1;
}
</style>

<div class="main2">
    <h1 class="sheetInfo-hd"><span class="user-name" >${(vo.xingming)!''}</span>的会诊单</h1>
    <input id="singleid" type="hidden" value="${(vo.liushuihao)!''}" />
    <input id="yxurl" type="hidden" value="${(yxurl)!''}" />
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="sheetInfo-main">
                <!-- 文字信息 -->
                <input id="hidden_yxbh" type="hidden" value="${(vo.yingxiangbh)!''}" />
                <div class="row" style="margin-bottom: 30px">
                    <div style="margin-bottom: 10px">
                        <div class="col-md-12" >
                            <div class="item">
                                <label>会诊单号:</label>
                                <span id="huanzhe_liushuihao" >${(vo.liushuihao)!''}</span>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6" >
                            <div class="item">
                                <label>患者姓名:</label>
                                <span id="huanzhe_name" >${(vo.xingming)!''}</span>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6" >
                            <div class="item">
                                <label>性别:</label>
                            	<#if (vo.xingbie)??>
	                                <#if (vo.xingbie)=='1'>
	                                	<span>男</span>
	                                <#else>
	                                	<span>女</span>
	                                </#if>
                                </#if>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6" >
                            <div class="item">
                                <label>年龄:</label>
                                <span>${(vo.age)!''}</span>
                            </div>
                        </div>
                    </div>
                    <div style="margin-bottom: 10px">
                        <div class="col-md-4 col-sm-6" >
                            <div class="item">
                                <label>身份证号:</label>
                                <span id="card_id">${(vo.shenfenzh)!''}</span>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6" >
                            <div class="item">
                                <label>手机号:</label>
                                <span>${(vo.shoujihm)!''}</span>
                            </div>
                        </div>
                    </div>
                    <div style="margin-bottom: 10px">
                        <div class="col-md-4 col-sm-6"  >
                            <div class="item">
                                <label>预约日期:</label>
                                <span>${((vo.yuyuerq)!'')?string("yyyy-MM-dd")}</span>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6" >
                            <div class="item">
                                <label>预约午别:</label>
                                <#if (vo.shangxiawbz)=='AM'>
                                	<span>上午</span>
                                <#else>
                                	<span>下午</span>
                                </#if>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6" >
                            <div class="item">
                                <label>预约号源:</label>
                                <span>${(vo.yuyuexh)!''}</span>(<span>${(vo.yuyuesj)!''}</span>)
                            </div>
                        </div>
                    </div>
                    <div style="margin-bottom: 10px">
                        <div class="col-md-4 col-sm-6" >
                            <div class="item">
                                <label>发起方:</label>
                                <span>${(vo.shenqingysmc)!''}</span>(<span>${(vo.shenqingyymc)!''}</span>)
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6" >
                            <div class="item">
                                <label>接收方:</label>
                                <span>${(vo.huizhenysmc)!''}</span>(<span>${(vo.huizhenyymc)!''}</span>)
                            </div>
                        </div>
                    </div>
                </div>
                <!--  end -->
                <!-- 会诊日期 -->
                <div class="panel panel-danger visible-md">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-md-4 " >
                                <span>会诊日期</span>
                            </div>
                            <div class="col-md-4" >
                                <span>开始时间</span>
                            </div>
                            <div class="col-md-4" >
                                <span>结束时间</span>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-4" >
                            	<span>${((vo.yuyuerq)!'')?string("yyyy-MM-dd")}</span>
                            </div>
                            <div class="col-md-4" >
                            	<#if (vo.huizhenfangkssj)??>
                            		<span>${(vo.huizhenfangkssj)?string("yyyy年MM月dd日 HH:mm:ss")}</span>
                            	</#if>
									
                            </div>
                            <div class="col-md-4" >
                            	<#if (vo.huizhenfangjssj)??>
                            		<span>${(vo.huizhenfangjssj)?string("yyyy年MM月dd日 HH:mm:ss")}</span>
                            	</#if>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end -->
                <!-- 会诊报告 -->
                <div>
                    <label class="panel-hd">会诊报告:</label>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <span>${(vo.zhenduanbg)!''}</span>
                        </div>
                    </div>
                </div>
                <!-- end -->
                <!-- 病情描述 -->
                <div>
                    <label class="panel-hd">病情小结:</label>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <span>${(vo.binglixx)!''}</span>
                        </div>
                    </div>
                </div>
                <!-- end -->
                <!-- 病情小结 -->
                <div>
                    <label class="panel-hd">病情描述:</label>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <span>${(vo.bingqingms)!''}</span>
                        </div>
                    </div>
                </div>
                <div>
                    <label class="panel-hd">影像链接:</label>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div id="yxzls"></div>
                        </div>
                    </div>
                </div>
                <!-- end -->
                <!-- 已上传的图片 -->
                <div class="imgBox">
                    <label class="panel-hd">已上传的图片:</label>
                    <div class="imgBox-list clearfix" id="conImg">
                    </div>
                </div>
                <!-- end -->
            </div>
        </div>
    </div>
    <!--<div class="panel panel-default">
        <div class="panel-body">
            <div class="sheetInfo-main">
                &lt;!&ndash; 患者档案 &ndash;&gt;
                <div class="imgBox">
                    <label class="panel-hd">患者档案</label>
                    <h2 class="panel-hd2">就诊记录</h2>
                    <div id="J_UserInfo" class="userInfo clearfix">
                        <div class="medicalLog">
                            <div id="noData" class="medicalLog-empty" style="display: none;">
                                <em  class="icon icon-file-alt"></em>
                                未查到数据
                            </div>
                            <ul class="medicalList" id="seeDoctorIndexUl">
                                &lt;!&ndash;<li>
                                    <div class="logItem">
                                    <div class="logItem-title">慢性乙型病毒性肝炎<a href="javascript:void(0)" onclick="seeDoctorDetail('9832998,6052788','912000000145524553','9832998')" class="logItem-event btn-blue j_LookInfo">查看详情</a></div><div class="logItem-other clearfix"><p class="logItem-otherInfo"><label class="info-title">就诊时间：</label>2017-08-27 17:15:00</p><p class="logItem-otherInfo"><label class="info-title">就诊医院：</label>金华市中心医院</p><p class="logItem-otherInfo"><label class="info-title">就诊科室：</label>感染科专家门诊</p></div></div></li>&ndash;&gt;
                            </ul>
                        </div>
                    </div>
                    &lt;!&ndash; 处方检验页面 &ndash;&gt;
                    <div class="medicalInfo" id="J_MedicalInfo" style="display: none;">
                        <p class="icon-remove-sign j_Hide"></p>
                        <ul class="medicalInfo-list">
                            <li class="medicalInfo-item">
                                <div  class="medicalInfo-title">处方信息</div>
                                <ul class="recipeList" id="chufangIndex">
&lt;!&ndash;
                                    <li> <a onclick="cfDetail('912000000145524553','9832998,6052788','5202030')" href="javascript:void(0);"><div class="logItem-other clearfix"> <p class="logItem-otherInfo"> <label class="info-title">就诊医院：</label>金华市中心医院 </p><p class="logItem-otherInfo"><label class="info-title">处方单：</label>5202030</p><p class="logItem-otherInfo"><label class="info-title">开单时间：</label>2017-08-27</p> </div> </a> </li>
&ndash;&gt;
                                </ul>
                            </li>
                            <li class="medicalInfo-item">
                                <div  class="medicalInfo-title">报告单</div>
                                <ul class="reportList" id="jianyanIndexUl">
&lt;!&ndash;
                                    <li><a onclick="jyDetail('912000000145524553','111709101472')" href="javascript:void(0);"><div class="logItem-other clearfix"><p class="logItem-otherInfo"><label class="info-title">就诊医院：</label>金华市中心医院 </p><p class="logItem-otherInfo"><label class="info-title">检验项目：</label>肝功能常规检查</p><p class="logItem-otherInfo"><label class="info-title">开单时间：</label>2017-08-27 17:13:02</p> </div> </a> </li>
&ndash;&gt;
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
                &lt;!&ndash; end &ndash;&gt;
            </div>
        </div>
    </div>-->
</div>
<div class="mask j_Mask"></div>
<script type="text/javascript" src="${base }/static/base/js/external/jquery.nicescroll.js"></script>
<script type="text/javascript">
	var globalVar = {};
	globalVar.base = '${base}';

    var path = "/consultationSingle/";//全局url
    var cardId = $("#card_id").html();//全局身份证号
    var name =  $("#huanzhe_name").html();//全局名字
    $(function(){
        var yxbh = $("#hidden_yxbh").val();
        if(yxbh != ""){
            var st = yxbh.split(",");
            var node = $("#yxzls");
            $(st).each(function(m,n){
                if(n != ''){
                    node.append('<a class="videodownload" style="text-decoration:underline " onclick="openUrl(\''+n+'\')" href="javascript:void(0)">'+n+'</a>&nbsp;&nbsp;&nbsp;&nbsp;');
                }
            });
        }
        //页面加载完,获取会诊单图片
        //$("#conImg").html("");
        var index = layer.load(1);
        $.ajax({
            url:globalVar.base+"/consultationSingle/getConsultationImgById",
            data:{"singleLiushuihao":$("#singleid").val(),"type":1},
            dataType:"json",
            type:'post',
            success:function(data){
                layer.close(index);
                if(data.success == true){
                    //赋值
                    var st = ''
                    var path = globalVar.base+"/consultationSingle/showConsultationImg?path=";
                    $(data.data).each(function(m,n){
                        st = '<div class="j_Gallery imgShow-event"><figure itemprop="associatedMedia">' +
                            '<a href="'+ path + n.filePath + '" itemprop="contentUrl" data-size="300x300">' +
                            '<img class="report-item" src="'+ path + n.filePath + '" itemprop="thumbnail" alt="" />' +
                            '</a></figure></div>';
                        $("#conImg").append(st);
                    });
                    initPhotoSwipeFromDOM($('.j_Gallery'));
                }else{
                    $("#conImg").append('<div class="imgHint-null">未上传图片</div>');
                }
            },
            error:function(){
                layer.close(index);
                layer.alert("获取会诊图片失败:网络错误");
            }
        });
    });
    function openUrl(parame){
        //获取地址
        var index = layer.load(1);
        var sp = parame.split(":");
        $.post(globalVar.base+"/consultationSingle/getyxzlbyid",{"id":sp[1],"code":sp[0]},function(data){
            layer.close(index);
            if(data.success == true){
                window.open(data.data.data);
            }else{
                layer.msg("获取地址失败");
            }
        },"json").error(function(){
            layer.close(index);
            layer.msg("服务器错误");
        })
        //
    }
    function seeDoctorDetail(jzid,jigoubh,huanzheid){
        //要查询处方和检验报告
        var i = 0;//控制器
        $.post(path+"getJYBGByIndex",{"jigoubh":jigoubh,"index":jzid,"huanzheid":huanzheid,"cardId":cardId,"name":name},function(data){
            $("#jianyanIndexUl").html("");
            var st = "";
            if(data.success == true){
                //说明有数据
                $(data.data).each(function(m,n){
                    st+='<li><a onclick="jyDetail(\''+n.orgCode+'\',\''+n.jianyanid+'\')" href="javascript:void(0);"><div class="logItem-other clearfix"><p  class="logItem-otherInfo"><label class="info-title">就诊医院：</label>'+n.jigoumc+' </p>'+
                        '<p  class="logItem-otherInfo"><label class="info-title">检验项目：</label>'+n.jianyanxmmc+'</p><p  class="logItem-otherInfo"><label class="info-title">开单时间：</label>'+n.jianyanrq+
                        '</p> </div> </a> </li>';
                });
            }else{
                //没有数据
                st+='<li class="dataNo"><span>未查到数据</span></li>';
            }
            $("#jianyanIndexUl").html(st);
            i++;
        },"json");
        $.post(path+"getCFByIndex",{"jigoubh":jigoubh,"index":jzid,"huanzheid":huanzheid,"cardId":cardId,"name":name},function(data){
            $("#chufangIndex").html("");
            var st = "";
            if(data.success == true){
                //说明有数据
                $(data.data).each(function(m,n){
                    st+='<li> <a onclick="cfDetail(\''+n.jigoubh+'\',\''+n.jiuzhenid+'\',\''+n.yiyuancfid+'\')" href="javascript:void(0);"><div class="logItem-other clearfix"> <p  class="logItem-otherInfo"> <label class="info-title">就诊医院：</label>'+n.jigoumc+' </p>'+
                        '<p  class="logItem-otherInfo"><label class="info-title">处方单：</label>'+n.yiyuancfid+'</p><p  class="logItem-otherInfo"><label class="info-title">开单时间：</label>'+n.kaifangsj+
                        '</p> </div> </a> </li>';
                });
            }else{
                //没有数据
                st+='<li class="dataNo"><span>未查到数据</span></li>';
            }
            $("#chufangIndex").html(st);
            i++;
        },"json");
        var J_MedicalInfo = $("#J_MedicalInfo"),
            elMask = $(".j_Mask");//遮罩层
            J_MedicalInfo.fadeIn(200);//详情显示
            elMask.show();//显示遮罩层
            J_MedicalInfo.css('left', ($(window).width() - J_MedicalInfo.outerWidth()) / 2);//弹出层左右居中
            J_MedicalInfo.css('top', ($(window).height() - J_MedicalInfo.outerHeight()) / 2);//弹出层上下居中
        //关闭弹出层
        J_MedicalInfo.find(".j_Hide").click(function () {
            J_MedicalInfo.hide();
            $("#jianyanIndexUl").html("");//清空数据
            $("#chufangIndex").html("");//清空数据
            elMask.hide();
        });
    }
    function cfDetail(jigoubh,index,cfid){
        window.open(path+"getCFById/"+jigoubh+"/"+index+"/"+cfid+"/"+cardId);
    }
    function jyDetail(jigoubh,jyid){
        window.open(path+"getJYById/"+jigoubh+"/"+jyid+"/"+cardId);
    }
    $('.main2').niceScroll({
                cursorcolor: "#ccc",//#CC0071 光标颜色
                cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0
                touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备
                cursorwidth: "5px", //像素光标的宽度
                cursorborder: "0", //   游标边框css定义
                cursorborderradius: "5px",//以像素为光标边界半径
                 autohidemode:false,//是否隐藏滚动条
                cursorheight:"100px"
            });
</script>
