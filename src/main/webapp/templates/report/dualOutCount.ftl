<#assign base=rc.contextPath />
 <base id="base" href="${base}">
<@choiceSign.header />

<@choiceSign.left />
<style >
 .Wdate{
	 width: 200px;
 }
</style>
<script type="text/javascript" src="${base}/static/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<input type="hidden" id="workGroupId" value="${workGroupId!}" />
<section class="Hui-article-box">
	<nav class="breadcrumb">
		<i class="Hui-iconfont"></i>
		<a href="/" class="maincolor">首页</a> 
		<span class="c-gray en">&gt;</span>系统报表
		<span class="c-gray en">&gt;</span>转出统计
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<div class="text-l">
				<form>
				<span>开始时间：</span><input value="${startTime! }" name="startTime" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd' })"  class="input-text Wdate">
				<span style="margin-left: 20px">结束时间：</span><input value="${endTime! }" name="endTime" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd' })"  class="input-text Wdate">
				<button id="search" class="btn btn-success" type="button" id="search" style="margin-left: 20px"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
                </form>
			</div>

			<div class="mt-20" style="margin-top: 40px">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<#if dualType! == 2>
							<th width="20%">转出机构</th>
							<#else ><th width="20%">转入机构</th>
							</#if>
							<th width="20%">门诊转诊</th>
							<th width="20%">住院转诊</th>
							<th width="20%">小计</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<#if list?exists>
						<#list list as obj>
						<tr class="text-c">
							<td class="jg">${obj.hosName!}</td>
							<td class="mz">${obj.mz!0}</td>
							<td class="zy">${obj.zy!0}</td>
							<td class="xj">${obj.xj!0}</td>
						</tr>
						</#list>
						</#if>
					<tr class="text-c">
						<td>合计</td>
						<td id="mz"></td>
						<td id="zy"></td>
						<td id="total"></td>
					</tr>
					</tbody>
				</table>
			</div>

			<div id="echart" style="height:400px; margin-top: 20px"></div>

		</article>
<@choiceSign.footer />
	</div>
</section>
<script type="text/javascript"  src="${base }/static/lib/echarts/3.4.0/echarts.common.min.js"></script>
<script type="text/javascript" src="${base}/static/lib/My97DatePicker/4.8/WdatePicker.js"></script>

<script type="text/javascript">
	var globalVar = {};
	globalVar.base = '${base}';
	//统计图 机构 数量
    var xjArray = new Array();
    var jgArray = new Array();
	// 查询
	$("#search").click(function () {
		var info = $("form").serialize();
		<#if dualType! == 2>
		    var url = globalVar.base + "/dualReport/dualOutCount.do?";
		<#else>
			var url = globalVar.base + "/dualReport/dualInCount.do?";
		</#if>
		url += info;
        window.location = url;
        })

	//合计统计
    $(document).ready(function () {
        var total = 0;
        var mz = 0;
        var zy = 0;

		$(".xj").each(function (i) {
		    xjArray[i] = $(this).html();
			total += parseInt($(this).html());
        })
		$(".zy").each(function () {
			zy += parseInt($(this).html());
        })
		$(".mz").each(function () {
			mz += parseInt($(this).html());
        })
        $(".jg").each(function (i) {
            jgArray[i] =  $(this).html();
        })
		$("#total").html(total);
		$("#mz").html(mz);
		$("#zy").html(zy);

        initdata();
    })
	//柱形图
    var echart = echarts.init(document.getElementById('echart'));
    option = {
        title: {
            text: '转出统计'
		},
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
                name:'机构',
                type : 'category',
                data : ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                name: '数量',
                type : 'value'
            }
        ],
        series : [
            {
                name:'转出',
                type:'bar',
                barWidth: '60%',
                data:[10, 52, 200, 334, 390, 330, 220]
            }
        ]
    };
    function initdata(){
        <#if list??>
            option.xAxis[0].data = jgArray;
            option.series[0].data = xjArray;
        </#if>
		<#if dualType! == 1>
		    option.title.text = '转入统计';
		    option.series[0].name = '转入';
		</#if>
        echart.setOption(option);
    }


</script>
<#--<script type="text/javascript" src="${base}/static/base/js/referral/outChtList.js"></script>-->
</body>
</html>