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
	</style>
	<!--[if IE 6]>
	<script type="text/javascript" src="http://static/lib.net/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
	<!--/meta 作为公共模版分离出去-->
	<script type="text/javascript" src="${base}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/layer/2.4/layer.js"></script>
	<link rel="stylesheet" type="text/css" href="${base}/static/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" />
	<script type="text/javascript"  src="${base}/static/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
</head>
<body>
	<div class="f-l" style="padding-top: 20px;padding-left: 75px">
		    <div class="panel panel-primary">
		    	<div class="panel-header" id="roleName"></div>
		    	<div class="panel-body">
		            <ul id="_tree" class="ztree"></ul>
		     		<input onclick="save()" class="btn btn-success radius" type="button" value="保存">
		    	</div>
		    </div>
	</div>
</body>
</html>
<script>
	var treeNodes,treeNo,roleId;
	var setting = {
            check: { /**复选框**/
            	enable: true,
                chkboxType: { "Y" : "s", "N" : "s" }
            },
            view: {
                //dblClickExpand: false,
                expandSpeed: 300 //设置树展开的动画速度，IE6下面没效果，
            },
            data: {
                simpleData: {   //简单的数据源，一般开发中都是从数据库里读取，API有介绍，这里只是本地的
                	enable: true,
                    idKey: "roleId",  //id和pid，这里不用多说了吧，树的目录级别
                    pIdKey: "parentId",
                    rootPId: 0   //根节点
                }
            },
            callback: {
              
            }
	};
	$(function(){
		//初始化tree
		$.post("${base}/user/getrolesbyuserid",{"id":parent.userId},function(data){
			if(data.success == true){
				//渲染树
				 treeNodes = data.data;
           		 treeNo = $.fn.zTree.init($("#_tree"),setting, treeNodes);
           		 treeNo.expandAll(true);
           		 $("#roleName").html(parent.userName);
			}else{
				//
				layer.msg("获取出错");
			}
		}).error(function(){
			layer.msg("网络错误");
		});
	});
	function save(){
		//获取所有选中的节点
		var nodes = treeNo.getCheckedNodes(true);
		//如果没有选中的就是解除所有权限
		var st = 'st';
		if(nodes != null && nodes.length>0){
			//有选中
			st = '';
		}
		$(nodes).each(function(m,n){
			st += n.roleId + ',';
		});
		$.post("${base}/user/operationuserrole",{"roleIds":st,"userId":parent.userId},function(data){
			if(data.success == true){
				//成功
				layer.msg("操作成功");
				setTimeout(function(){
	    			parent.layer.closeAll() ;
	    		},1000);
			}else{
				layer.msg("操作失败");
			}
		},'json').error(function(){
			layer.msg("服务器错误");
		});
		}
</script>