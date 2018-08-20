<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<#assign base=rc.contextPath />
 <base id="base" href="${base}">
 <@choiceSign.header />

<@choiceSign.left />
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp"/>
<link rel="Bookmark" href="favicon.ico" >
<link rel="Shortcut Icon" href="favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/blue/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script><![endif]-->
<!--/meta 作为公共模版分离出去-->
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/external/finish.css"/>
<script type="text/javascript" src="${base}/static/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<title>完成</title>
</head>
<body>
<section class="Hui-article-box">
	<!-- 隐藏域接收controller传递过来的参数  start-->
	
	<input type="hidden" id="uuid" value="${uuid }"/>
	<input type="hidden" id="costHidden" value="${cost }"/>
	
	<!-- 隐藏域接收controller传递过来的参数  end-->
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span>
		系统管理
		<span class="c-gray en">&gt;</span>
		基本设置
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
		<div class="pd-20">
			<div class="navBox">
				  <label for="">
						<img src="${base }/static/lib/h-ui.admin/images/basic-news/list_3.png" alt="">
				  </label>
			</div>
			<div class="success">
				<img src="${base }/static/lib/h-ui.admin/images/basic-news/success.png" alt="">
				<div class="suc_detail">
					<table>
						<thead>
							<tr>
								<td>预约项目</td>
								<td>预约时间</td>
								<td>金额</td>
							</tr>
						</thead>
						<tbody id="info-show">
						</tbody>
						<tfoot>
							<tr>
								<td colspan="3">
									总计金额：
									<span id="cost-show">￥${cost }</span>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
				<div class="btnNav" style="font-size:0">
					<button onclick="javascript:history.back(-1);" id="conApp" value="">继续预约</button>
					<button  id="goPay" value="">去支付</button>
				</div>
			</div>
  		 	<@choiceSign.footer /> 	 
		</div>
	</div>
</section>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${base }/static/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.page.js"></script>
<!--/_footer /作为公共模版分离出去-->

<script type="text/javascript">
	var globalVar = {};
	globalVar.base = '${base}';
</script>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
 <script type="text/javascript" src="${base}/static/base/js/external/finish.js"></script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>