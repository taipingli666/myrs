<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<#assign base=rc.contextPath />
 <base id="base" href="${base}">
<@choiceSign.header />
<@choiceSign.left />
<html>
<!--/meta 作为公共模版分离出去-->
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/external/finish.css"/>
<body>

<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span>
		系统管理
		<span class="c-gray en">&gt;</span>
		预约化验
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
		<div class="pd-20">
			<div class="navBox">
				  <label for="">
						<img src="${base}/static/lib/h-ui.admin/images/basic-news/alist_3.png" alt="">
				  </label>
			</div>
			<div class="success">
				<img src="${base}/static/lib/h-ui.admin/images/basic-news/success.png" alt="">
				<div class="suc_detail">
				<input type="hidden" id="item" value="${item}">
				<input type="hidden" id="price" value="${price}">
				<input type="hidden" id="time" value="${time}">
					<table>
						<thead>
							<tr>
								<td>预约项目</td>
								<td>预约时间</td>
								<td>金额</td>
							</tr>
						</thead>
						<tbody id="info">
						</tbody>
						<tfoot>
							<tr>
								<td colspan="3">
									总计金额：
									<span id="cost">￥${cost}</span>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
				<div class="btnNav" style="font-size:0">
					<button  id="conApp" value="继续预约" onclick="javascript:history.back(-1);">继续预约</button>
					<button  id="goPay" value="去支付">去支付</button>
				</div>
			</div>
	 		<@choiceSign.footer /> 
		</div>
	</div>
</section>
<!--/请在上方写此页面业务相关的脚本-->
<script type="text/javascript">
$(document).ready(function(){
	var item = $("#item").val();
	var price = $("#price").val();
	var time = $("#time").val();
	var itemArr = item.split(",");
	var priceArr = price.split(",");
	var str = "";
	for(var i=0;i<itemArr.length;i++) {
		str+="<tr><td>"+itemArr[i]+"</td/><td>"+time+"</td><td>"+priceArr[i]+"</td/></tr>"
	}
	$("#info").append(str);
})
	
</script>
</body>
</html>