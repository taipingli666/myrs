<#assign base=rc.contextPath />
<base id="base" href="${base}">
<@choiceSign.header />

<@choiceSign.left />
<style>
	table{
		cursor:pointer;
	}
	.ztree li span {
		max-width: 199px;
	    display: inline-block;
    white-space:nowrap; 
    overflow: hidden;
    text-overflow: ellipsis;
    }
</style>

<link rel="stylesheet" href="${base}/static/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统设置 <span class="c-gray en">&gt;</span> 区域人口管理<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="Hui-article" style="overflow:hidden;">
		<div class="pos-a" style="width:292px;left:0;top:0; bottom:0; height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5;overflow:scroll">
			<ul id="treeDemo" class="ztree">
			
			</ul>
		</div>
	
	        <iframe id="ifr" style="width:100%;height:100%;" src="${base}/commonpopulation/show3.do?areaCode=${areaCode!}"></iframe>
	
		<input type="hidden" value="${areaCode!}" id="areaCode">
	</div>
</section>
<@choiceSign.footer />


</div>
<script>
	var contextPath = '${base}';
	
</script>

<script type="text/javascript">
var areaCode = '';
$(document).ready(function() {
	areaCode = $("#areaCode").val();
	if(areaCode == '') {
		$(".pos-a").hide();
		$("section").css("left","111px");
	}
})
var setting = {  
        async:{  
            autoParam:["pId"],    
            enable:false,   
            type:"post",    
            url:null,  
            dataFilter:"",  
        },  
        view: {  
           dblClickExpand: false, 
           addDiyDom:null  
        },  
        data : {  
            key : {  
                name : "name"  
            },  
            simpleData : {  
                enable : true,  
                idKey : "id",  
                pIdKey : "pId",  
                rootPId : 0  
            }  
        },  
        callback : {  
            beforeAsync : ztreeOnAsyncSuccess,  
            onAsyncSuccess : ztreeOnAsyncSuccess, 
       		onClick:ok,
       		 onCollapse: ztreeOnAsyncSuccess ,
       		onExpand: ztreeOnAsyncSuccess ,
       		
        }  
    };  
function ok(e,treeId, treeNode) {
	ztreeOnAsyncSuccess(e, treeId, treeNode);
	/* var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.expandNode(treeNode); */
} 
 
function ztreeOnAsyncSuccess(event, treeId, treeNode){  
    var node = treeObj.getNodeByTId(treeNode.tId);
    var nodeLength =0;
    var url = contextPath+"/commonarea/getTree.do?id=";  
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
	      $("#ifr")[0].src=contextPath+"/commonpopulation/show3.do?areaCode="+treeNode.id;
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
                /* treeObj.expandNode(treeNode,true, false, false); */// 将新获取的子节点展开  
                if(event.type == 'click'){
	                $("#ifr")[0].src=contextPath+"/commonpopulation/show3.do?areaCode="+treeNode.id;
                }
            }  
        },  
        error : function() {  
            alert("请求错误！");  
        }  
    });  
      
};  

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
var treeObj = null;
  $(document).ready(function(){  
            $.fn.zTree.init($("#treeDemo"), setting);  
            treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            init();
        });  
</script>

<script type="text/javascript" src="${base}/static/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>


</body>
</html>
