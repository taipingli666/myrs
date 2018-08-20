<#assign base=rc.contextPath />
<@choiceSign.header />
<@choiceSign.left />
<style>
 .YearNull{
 font-size:26px;
 text-align:Center;
 color:#999;
 font-weight:center;
 }
 
 .footer{
 	position:fixed;
 	bottom:0;
 	width:100%;
 	text-align:center;
 }
</style>
<title>预约排班</title>
<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span>
		远程会诊
		<span class="c-gray en">&gt;</span>
		会诊统计
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
		<div class="Hui-article">
			<div class="pd-20">
				
    <div class="row">
        <div class="col-sm-12 col-md-2" style="float: right">
            <select id="search_type" class="form-control" onchange="init()">
                <option value="1">接收</option>
                <option value="2">发起</option>
            </select>
        </div>
        <div class="col-sm-12 col-md-2" style="float: right">
            <select onchange="init()" value="${year}" id="search_year" style="width:100%" class="form-control">
               
            </select>
        </div>
    </div>
    <div class="row ">

    </div>
    <div id="show2" class="col-md-12 col-sm-12" style="display: none">
        <h1 class="YearNull"><em class="icon-bar-chart"></em>该年份没有会诊</h1>
    </div>
    <div class="row">
        <div class="row clearfix"   id="show">
                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                <div id="main" class="col-md-6 col-sm-6" style="height:400px;"></div>

            <div class="col-md-6 col-sm-6" >
                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                <!-- 表格 -->
                <div style="padding-top: 50px">
                    <table width="100%" class="table table-striped table-bordered table-hover" id="table_operator">
                        <thead>
                        <tr>
                            <td colspan="6" style="background-color: #f0f0f0;  font-size: 14px; padding: 10px 8px;text-align: center;">
                                <span id="searchYearvalue"></span>年会诊数据
                            </td>
                        </tr>
                        </thead>
                        <tr>
                            <td >月份</td>
                            <td>会诊数</td>
                    <!--        <td>月收入</td>-->
                            <td>月份</td>
                            <td>会诊数</td>
                           <!-- <td>月收入</td>-->
                        </tr>
                        <tr>
                            <td >1</td>
                            <td><span id="showNum1"></span>例</td>
<!--
                            <td><span id="showMoney1"></span>元</td>
-->
                            <td>2</td>
                            <td><span id="showNum2"></span>例</td>
                            <!--<td><span id="showMoney2"></span>元</td>-->
                        </tr>
                        <tr>
                            <td >3</td>
                            <td><span id="showNum3"></span>例</td>
                            <!--<td><span id="showMoney3"></span>元</td>-->
                            <td>4</td>
                            <td><span id="showNum4"></span>例</td>
                            <!--<td><span id="showMoney4"></span>元</td>-->
                        </tr>
                        <tr>
                            <td >5</td>
                            <td><span id="showNum5"></span>例</td>
                            <!--<td><span id="showMoney5"></span>元</td>-->
                            <td>6</td>
                            <td><span id="showNum6"></span>例</td>
                            <!--<td><span id="showMoney6"></span>元</td>-->
                        </tr>
                        <tr>
                            <td >7</td>
                            <td><span id="showNum7"></span>例</td>
                            <!--<td><span id="showMoney7"></span>元</td>-->
                            <td>8</td>
                            <td><span id="showNum8"></span>例</td>
                            <!--<td><span id="showMoney8"></span>元</td>-->
                        </tr>
                        <tr>
                            <td >9</td>
                            <td><span id="showNum9"></span>例</td>
                           <!-- <td><span id="showMoney9"></span>元</td>-->
                            <td>10</td>
                            <td><span id="showNum10"></span>例</td>
                            <!--<td><span id="showMoney10"></span>元</td>-->
                        </tr>
                        <tr>
                            <td >11</td>
                            <td><span id="showNum11"></span>例</td>
                            <!--<td><span id="showMoney11"></span>元</td>-->
                            <td>12</td>
                            <td><span id="showNum12"></span>例</td>
                            <!--<td><span id="showMoney12"></span>元</td>-->
                        </tr>
                    </table>
                </div>
            </div>

        </div>
    </div>
    	
	</div>
		<@choiceSign.footer />
	</div>
 
</section>
<!--_footer 作为公共模版分离出去-->
 
<script type="text/javascript"  src="${base }/static/lib/echarts/3.4.0/echarts.common.min.js"></script>
<!--/_footer /作为公共模版分离出去-->

<script type="text/javascript">
	var globalVar = {};
	globalVar.base = '${base}';
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    var option = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['1月', '2月', '3月', '4月', '5月', '6月', '7月',
                    '8月', '9月', '10月', '11月', '12月'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : {
            minInterval: 1,
            name:"会诊数量",
            nameLocation:"middle",
            nameGap:"20",
            boundaryGap: ['0%', '20%']},

    };
    
	function initdata(){
		myChart.setOption(option);
    }
    // 使用刚指定的配置项和数据显示图表。
    $(function(){
    	var nd=new Date();
    	var nyear=nd.getFullYear();
    	$("#search_year").empty();  
    	for(var iii=0;iii>-8;iii--){
    		if($("#hiyear").val()==(nyear+iii)){
        		$("#search_year").append($("<option selected='true'/>").text(nyear+iii).attr("value",nyear+iii)); 
    		}else{
    			$("#search_year").append($("<option/>").text(nyear+iii).attr("value",nyear+iii)); 
    		}
    	}
    	
        init();
    })
    function init(){
    	initdata();
        //加载统计
        $.ajax({
            dataType:"JSON",
            url:globalVar.base+"/consultationSingle/consultationstatisticsdata.do",
            data:{year:$("#search_year").val(),type:$("#search_type").val()},
            type:'post',
            success:function(objdata){
                if(objdata.data!=null && objdata.data!=undefined && objdata.data!=""){
                	var data=objdata.data;
                    //初始化图表
                    var bData = data.count.toString().split(',');
                    var mData = data.money.toString().split(',');
                    myChart.setOption({
                        title: {
                            text: $("#search_year").val()+'年会诊统计'
                        },
                        series : [
                            {
                                name:'会诊数',
                                type:'bar',
                                barWidth: '60%',
                                data:bData
                            }
                        ]
                    });
                    myChart.setOption(option);
                    //渲染表格
                    $("#searchYearvalue").html($("#search_year").val());
                    for(var i=0;i<=11;i++){
                        $("#showNum"+(i+1)).html(bData[i]);
                        $("#showMoney"+(i+1)).html(mData[i]);
                    }
                    $("#show").css("display","block");
                    $("#show2").css("display","none");
                }else{
                    //该年份未查到数据,页面提示
                    $("#show").css("display","none");
                    $("#show2").css("display","block");
                }
            },
            error:function(){
                layer.msg("网络异常");
            }
        });
    }
    
</script>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base }/static/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base }/static/base/js/external/appointmentCheck.js"></script>
<!--/请在上方写此页面业务相关的脚本-->
