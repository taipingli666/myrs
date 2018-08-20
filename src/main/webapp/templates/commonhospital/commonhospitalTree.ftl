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
<script type="text/javascript"  src="${base}/static/base/js/hospital/hospitalList.js"></script>
<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统设置 <span class="c-gray en">&gt;</span> 医疗机构 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="Hui-article" style="overflow:hidden;">
		<div class="pos-a" style="width:292px;left:0;top:0; bottom:0; height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5;overflow:scroll">
			<ul id="treeDemo" class="ztree">
			
			</ul>
		</div>
	    <iframe id="ifr" style="width:100%;height:100%;" src="${base}/commonhospital/list"></iframe>
	</div>
</section>
<@choiceSign.footer />
<script>
	var contextPath = '${base}';
	var parentCode;
	var treeObj = null;
</script>
<script type="text/javascript">
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
       		onCollapse: ztreeOnAsyncSuccess,
       		onExpand: ztreeOnAsyncSuccess 
        }  
    };  
    
 
  $(document).ready(function(){  
            $.fn.zTree.init($("#treeDemo"), setting);  
            treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            init();
        });  
</script>
<script type="text/javascript" src="${base}/static/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>