<#assign base=rc.contextPath />
 <base id="base" href="${base}">
 
  <script> var contextPath = '${base}';</script>
<script type="text/javascript" src="${base}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript"  src="${base}/static/base/js/hospital/hospitalEdit.js"></script>
<script type="text/javascript" src="${base}/static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base}/static/base/js/main.js"></script> 
<script type="text/javascript" src="${base}/static/base/js/admin.page.js"></script> 
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/min.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/admin.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/base/skin/blue/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${base}/static/base/css/style.css" />

<style>
	table{
		cursor:pointer;
	}
	.ztree li span {
	    max-width: 199px;
	    display: inline-block;
	    white-space: nowrap ;
	    overflow: hidden;
	    text-overflow: ellipsis;
    }
</style>

<section class="Hui-article-box" style="left:292px;top:0">
	<div class="page-container">
	<form id="formOperated">
		<input type="hidden" id="hosId" name="hosId" value="${(info.hosId)!}" />
		<input type="hidden" id="areaId"  value="${areaId!}"/>
		<input type="hidden" id="pageNumber" value="${pageNumber!}"/>
		<div class="row cl" style="margin-top:2%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>机构编码：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="name"  name="hosNum" type="text" class="input-text" value="${(info.hosNum)!}" placeholder="医院编码" />
			</div>
		</div>
		<div class="row cl" style="margin-top:2%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>医院名称：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="name" name="hosName" type="text" class="input-text" value="${(info.hosName)!}" placeholder="" />
			</div>
		</div>
		<div class="row cl" style="margin-top:2%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>所属区域：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="name" name="areaCode" type="text" class="input-text" value="${(info.areaCode)!}" placeholder="区域编码" />
			</div>
		</div>
		<div class="row cl" style="margin-top:2%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>上级区域：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="name" name="parentCode" type="text" class="input-text" value="${(info.parentCode)!}" placeholder="区域编码" />
			</div>
		</div>
		<div class="row cl" style="margin-top:2%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>机构类型：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="name" name="hosType" type="text" class="input-text" value="${(info.hosType)!}" placeholder="" />
			</div>
            <span class="c-red">仅支持数字类型,0管理机构1医疗机构</span>
		</div>
		<div class="row cl" style="margin-top:2%;">
			<label class="form-label col-xs-4 col-sm-2 lable1"><span class="c-red">*</span>区域等级：</label>
			<div class="formControls col-xs-4 col-sm-2">
				<input id="name" name="level" type="text" class="input-text" value="${(info.level)!}" placeholder="" />
			</div>
            <span class="c-red">仅支持数字类型</span>
		</div>
		<div class="cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2" style="wdith:100%;margin-top:3%">
				<button class="btn btn-primary radius" type="button"  onclick="operated()" id='save'><i class="Hui-iconfont">&#xe632;</i> 保存</button>
				<button onClick="layer_close();" class="btn btn-default radius" style="margin-left:2%;" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
	</div>
	</section>