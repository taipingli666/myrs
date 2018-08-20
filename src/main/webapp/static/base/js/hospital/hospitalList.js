var areaCode;
var areaPId;
var areaLevel;

function changePage(page){
    // $("#page").val(page)
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
		 	ids += $(this).data('hosid')+",";
		});
		$.post(contextPath+"/commonhospital/deletehospital",{"ids":ids},function(data){
			if(data.success==true){
				layer.msg('操作成功!',{icon:1,time:1000});
    			setTimeout(function(){
    			refresh()
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
function addRow(id){
	
	areaCode = $("#areaCode").val();
	areaPId = $("#areaPId").val();
	areaLevel = $("#areaLevel").val();

	var index = layer.open({
		type: 2,
		title: id==0?"新增医院":"编辑医院", 
		content: contextPath+"/commonhospital/editui?&id="+id+"&areaCode="+areaCode+"&areaPId="+areaPId+"&areaLevel="+areaLevel
	});
	layer.full(index);
}

function ztreeOnAsyncSuccess(event, treeId, treeNode){  
	var url = contextPath+"/commonarea/getTree.do?id=";
	var node = treeObj.getNodeByTId(treeNode.tId);
    var nodeLength =0;
    areaCode = treeNode.id;
    areaPId = treeNode.pId;
    areaLevel = treeNode.level;
    
    if(treeNode == undefined){  
        url += "0";  
    }  
    else{  
        url += treeNode.id;  
    } 
    
    if(node.children ==undefined){
      
    }else{
      nodeLength = node.children.length;
    }
    if(nodeLength > 0){
    	if(event.type == 'click'){
    		$("#ifr")[0].src=contextPath+"/commonhospital/list?areaCode="+areaCode+"&areaPId="+areaPId+"&areaLevel="+areaLevel;
    	}
    	return;
    }
       
    $.ajax({  
        type : "post",  
        url : url,  
        data : "",  
        dataType : "json",  
        async : true,  
        success : function(jsonData) {                
            if (jsonData != null) {       
                var data = jsonData.treeData;  
                if(data != null && data.length != 0){  
                    if(treeNode == undefined){  
                        treeObj.addNodes(null,data,true);// 如果是根节点，那么就在null后面加载数据  
                    }  
                    else{  
                        treeObj.addNodes(treeNode,data,true);//如果是加载子节点，那么就是父节点下面加载  
                    }  
                }  
                /*treeObj.expandNode(treeNode,true, false, false);*/// 将新获取的子节点展开  
                parentCode = treeNode.id;
                if(event.type == 'click'){
                	$("#ifr")[0].src=contextPath+"/commonhospital/list?areaCode="+areaCode+"&areaPId="+areaPId+"&areaLevel="+areaLevel;
                }
            }  
        },  
        error : function() {  
            alert("请求错误！aa");  
        }  
    });  
      
};  

function ok(e,treeId, treeNode) {
	ztreeOnAsyncSuccess(e, treeId, treeNode);
	/*var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.expandNode(treeNode);*/
} 

function init(){
  $.ajax({  
        type : "post",  
        url : contextPath+"/commonarea/getTree.do?id=-1",  
        data : "",  
        dataType : "json",  
        async : true,  
        success : function(jsonData) {                
            if (jsonData != null) {       
                var data = jsonData.treeData;  
                if(data != null && data.length != 0){  
                        treeObj.addNodes(null,data,true);// 如果是根节点，那么就在null后面加载数据  
                }  
            }  
        },  
        error : function() {  
            alert("请求错误！");  
        }  
    });  
}


function setConsultation(hosId,consultation) {
    $.post(contextPath+"/commonhospital/edit",{"hosId":hosId,"consultation":consultation},function(data){
        if(data.success == true){
            //成功
            layer.msg('操作成功!',{icon:1,time:1000});
            setTimeout(function(){
                refresh($("#page").val());
            },1200)
        }else{
            layer.msg('操作失败!',{icon:2,time:1000});
        }
    },"json").error(function(){
        layer.msg("网络错误");
    });
}