var setting = {
            check: { /**复选框**/
            	enable: true,
                chkboxType: { "Y" : "ps", "N" : "ps" }
            },
            view: {
                //dblClickExpand: false,
                expandSpeed: 300 //设置树展开的动画速度，IE6下面没效果，
            },
            data: {
                simpleData: {   //简单的数据源，一般开发中都是从数据库里读取，API有介绍，这里只是本地的
                	enable: true,
                    idKey: "menuId",  //id和pid，这里不用多说了吧，树的目录级别
                    pIdKey: "parentId",
                    rootPId: 0   //根节点
                }
            },
            callback: {
              
            }
};
$(function(){
	
	$('#tbody').on('dblclick','tr',function(){
		var id = $(this).data('roleid');
		addRow(id);
	});
});
var treeNodes,treeNo,roleId;
function changePage(page){
	refresh(page); 
}
function refresh(page){
	if(page != null){
			$("#page").val(page);
		}else{
			$("#page").val("1");
		}
		$("#listForm").submit();
	}
function dels(){
	layer.confirm('是否要删除数据?', {
	  btn: ['是','否'] //按钮
	}, function(){
	 	var $tbody = $("#tbody input[type='checkbox']:checked"),ids = '';
		if($tbody.length==0){
			layer.msg('请先选择要删除的数据！');
			return;
		}
		$tbody.each(function(i){
		 	ids += $(this).data('roleid')+",";
		});
		$.post(contextPath+"/role/deleteroles",{"ids":ids},function(data){
			if(data.success==true){
				layer.msg('操作成功!',{icon:1,time:1000});
    			setTimeout(function(){
    			window.location=contextPath+"/role/display";
    		},1200)
			}else{
				layer.msg("删除失败");
			}
		},"json").error(function(){
			layer.msg("服务器错误");
		});
	}, function(){
	 
	});
	
}
function operationRoleMenu(){
	//获取所有选中的节点
	var nodes = treeNo.getCheckedNodes(true);
	//如果没有选中的就是解除所有权限
	var st = 'st';
	if(nodes != null && nodes.length>0){
		//有选中
		st = '';
	}
	$(nodes).each(function(m,n){
		st += n.menuId + ',';
	});
	$.post(contextPath+"/power/operationrolemenu",{"ids":st,"roleId":roleId},function(data){
		if(data.success == true){
			//成功
			layer.msg("操作成功");
		}else{
			layer.msg(data.message);
		}
	},'json').error(function(){
		layer.msg("服务器错误");
	});
	
}
function jurisdiction(id,name){
	$.post(contextPath+"/role/getmenusbyroleId",{"id":id},function(data){
		//加载菜单
		if(data.success == true){
			roleId = id;
			//显示div
			$("#_menus").show();
			$("#roleName").html(name);
			//初始化tree
            treeNodes = data.data;
            treeNo = $.fn.zTree.init($("#_tree"),setting, treeNodes);
            treeNo.expandAll(true);
		}else{
			//获取数据失败
			$("#_menus").hide();
			layer.msg("抱歉,获取失败");
		}
	}).error(function(){
		layer.msg("服务器错误");
	});
}
function addRow(id){
	var index = layer.open({
		type: 2,
		title: id==0?"新增角色":"更新角色", 
		content: contextPath+"/role/operatedui?&id="+id
	});
	layer.full(index);
}