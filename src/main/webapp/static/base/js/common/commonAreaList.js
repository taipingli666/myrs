function changePage(page){
	refresh(page); 
}
function refresh(page){
	if(page != null){
			$("#page").val(page);
		}else{
			$("#page").val("1");
		}
		var parentCode = $("#parentCode").val();
		var action = globalVar.base+"/commonarea/show2?page="+page+"&parentCode="+parentCode;
		//给listForm的action重新赋值
		$("#listForm").attr("action",action);
		$("#listForm").submit();
	}
function product_del(){
	var parentCode = window.document.getElementById("parentCode").value;
	layer.confirm('是否要删除数据?', {
	  btn: ['是','否'] //按钮
	}, function(){
	 	var $tbody = $("#tbody input[type='checkbox']:checked"),ids = '';
	 	if($tbody.length==0){
			layer.msg('请先选择要删除的数据！');
			return;
		}
		$tbody.each(function(i){
		 	ids += $(this).data('area')+",";
		});
		$.post(contextPath+"/commonarea/deletecommonareas.do",{"ids":ids},function(data){
			if(data.success==true){
				layer.msg('操作成功!',{icon:1,time:1000});
    			setTimeout(function(){
    		    parent.initTree();
    			window.location=contextPath+"/commonarea/show2?parentCode="+parentCode;
    		},1200)
			}else{
				layer.msg("删除失败");
			}
		},"json").error(function(){
			layer.msg("网络异常");
		});
	}, function(){
	 
	});
	
}
function product_add(id){
	var nodes = parent.treeObj.getSelectedNodes();
	var index = layer.open({
		type: 2,
		area: ['340px', '215px'],
		title: id==0?"新增用户":"更新用户", 
		content: contextPath+"/commonarea/operatedui?id="+id+"&parentCode="+nodes[0].id
	});
	layer.full(index);
}

function tree_list(){
//	 var setting = {
//				view: {
//					dblClickExpand: false,
//					showLine: false,
//					selectedMulti: false
//				},
//				data: {
//					simpleData: {
//						enable:true,
//						idKey: "id",
//						pIdKey: "pId",
//						rootPId: ""
//					}
//				},
//				callback: {
//					beforeClick: function(treeId, treeNode) {
//						var zTree = $.fn.zTree.getZTreeObj("tree");
//						if (treeNode.isParent) {
//							zTree.expandNode(treeNode);
//							return false;
//						} else {
//							demoIframe.attr("src",treeNode.file + ".html");
//							return true;
//						}
//					}
//				}
//			};
//	 var zNodes;
//	$.ajax({
//		type : "GET",
//		url : contextPath+"/commonarea/getTree",
//		data : {},
//		dataType : "JSON",
//		cache : false,
//		async : false, //true，异步，false，同步
//		success : function(data){
//			if(data.success == true){
//				zNodes = data.treeData;
//			}else{
//				layer.msg("获取树节点失败!");
//			}
//		}
//	});
//	
//	var t = $("#treeDemo");
//	t = $.fn.zTree.init(t, setting, zNodes);
////	demoIframe = $("#testIframe");
////	demoIframe.bind("load", loadReady);
//	var zTree = $.fn.zTree.getZTreeObj("tree");
////	zTree.selectNode(zTree.getNodeByParam("id",'11'));
}



