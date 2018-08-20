<#assign base=rc.contextPath />
 <base id="base" href="${base}">
<@choiceSign.header />

<@choiceSign.left />
<style>
	table{
		cursor:pointer;
	}
</style>
<section class="Hui-article-box">
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i>
		<a href="${base}/others.htm" class="maincolor">首页</a> 
		<span class="c-gray en">&gt;</span>系统设置
		<span class="c-gray en">&gt;</span>菜单管理
	</nav>
	<input type="hidden" id="currentPage" value="${page.currentPage}" />
	<div class="Hui-article">
		<article class="cl pd-20">
			<div class="text-c">
				<input type="text" id="contents" placeholder="名称" style="width:250px" class="input-text" value="${contents! }" />
				<button id="search" class="btn btn-success" type="button" id="search"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			</div>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
					<a class="btn btn-primary radius" data-title="添加"   id="add" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 新增</a>
					<a href="javascript:;" class="btn btn-danger radius" id="remove"><i class="Hui-iconfont">&#xe6e2;</i> 删除</a>
				</span>
				<span class="r">共有数据：<strong>
					<#if page.totalRecord??>
					${page.totalRecord}
					<#else>
					0
					</#if>
				</strong> 条</span>
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="4%"><input type="checkbox" name="box1" value=""></th>
							<th width="6%">序号</th>
							<th width="20%">栏目名称</th>
							<th>url</th>
							<th width="10%">是否显示</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<#if page.content?exists>
						<#list page.content as menu>
							<tr class="text-c" name="dicttr" data-menuid="${menu.menuId!}">
								<td><input type="checkbox" value="" name="box2" ></td>
								<td>${menu.menuId!}</td>
								<td>${menu.menuName!}</td>
								<td>${menu.url!}</td>
								<td><#if menu.display?exists && menu.display ==0>显示<#else>不显示</#if></td>
							</tr>
						</#list>
						</#if>
					</tbody>
				</table>
				<@choiceSign.signpage page/>
			</div>
		</article>
	<@choiceSign.footer />
	</div>
	<script type="text/javascript">
		var globalVar = {};			//设置全局变量，添加base属性（项目路径）
		globalVar.base = '${base}';
	</script>
	<script>
	$(function(){
		//当前页数
		globalVar.currentPage = $("#currentPage").val();
		//绑定事件
		$('#search').on('click',doSearch);
		$('#remove').on('click',doRemove);
		$('#add').on('click',function(){
			addRow('菜单新增',globalVar.base+'/commonmenu/propareAdd.htm?operationType=add',"500");
		});
		
		//tr双击触发修改
		$('#tbody').on('dblclick','tr',function(){
			var id = $(this).data('menuid');
			addRow('修改字典信息',globalVar.base+'/commonmenu/propareAdd?operationType=update&id='+id);
		});
	});
	//search
	function doSearch(pageNumber){
		var url = globalVar.base+'/commonmenu/show.do?contents='+$("#contents").val();
		window.location = url;
	}
	function changePage(pageNumber){
		var url = globalVar.base+'/commonmenu/show.do?pageNumber=' + pageNumber+"&contents="
			+(globalVar.contents == undefined?"":globalVar.contents);
		
		window.location = url;
	}
	//新增或修改
	function addRow(title,url){
		var index = layer.open({
			type: 2,
			title: title, 
			content: url+"&pageNumber="+globalVar.currentPage
		});
		layer.full(index);
	}
	
	//删除
	function doRemove(){
		var $tbody = $("#tbody input[type='checkbox']:checked"),ids = '';
		if($tbody.length==0){
			alert('请先选择要删除的数据！');
			return;
		}
		$tbody.each(function(i){
		 	ids += $(this).parents('tr').data('dictid')+',';
		});
		if(window.confirm('是否确定要删除这些数据？')){
			$.ajax({
				cache:false,//是否使用缓存
	            url:globalVar.base+"/commonmenu/delete",
	            async:true,   //是否异步，false为同步
	            type:"post",
	            data:"ids=" + ids,
	            error:function(){
	            	alert("请求失败！");
	            },
	            success:function(reply){
	            	if(reply !="fail"){
				        layer.msg('成功删除!',{icon:1,time:1000});
				        doSearch();
			        }else{
			        	layer.msg('删除失败!',{icon:2,time:1000});
			        }
			    } 
			});
		}		
	}

	
	</script>
</body>
</html>
