$(function(){
	init();
	doSearch();
	//绑定事件	查询显示，导出
	$('#search').on('click',doSearch);
	$('#export').on('click',doExport);
	//参数即时更新	
	$('#subordinateId,#signingTime').on('change',function(){
		globalVar.isChange = true;
		var attr = $(this).attr('id');
		globalVar.param[attr] = $(this).val();
	});
});
function init(){
	globalVar.isChange = true;		//查询的参数是否变化
	globalVar.param = {};
	globalVar.param.subordinateId = $('#subordinateIdFormBackstage').val();
	globalVar.param.level = $('#level').val();
	globalVar.param.signingTimeStr = $('#signingTime option:eq(0)').val();
	//level为1，表示为医生团队，则没有下辖级别的下拉框显示
	if(globalVar.param.level == 1) $('.filter .doct-hidden').hide();
}

function doSearch(){
	if(globalVar.param.signingTimeStr == undefined || globalVar.param.signingTimeStr == '') return;
	if(globalVar.isChange){
		$.post(globalVar.base+"/reportStatistics/queryTableDate.do",globalVar.param,function(data){
			if(data.success==true){
				var list = data.yearList,$tbody = $('#tbody'),trs = '';
				if(list.length > 1){
					for(var i=0;i<list.length;i++){
						if(list[i] != null) {
							trs += '<tr class="text-c" name="dicttr" data-rsid="'+'list[i].reportId'+'"> <td>'+
				            (i+1)+'</td> <td>'+
				           formatDate(list[i].signingTime) +'</td><td>'+
				           list[i].signingNum +'</td><td>'+
				           list[i].signatureRate +'%</td><td>'+
				           list[i].renewNum +'</td><td>'+
				           list[i].renewRate +'%</td><td>'+
				           list[i].signingNumOfKey +'</td><td>'+
				           list[i].signatureRateOfKey +'%</td></tr>';
						}
					}
					$tbody.empty().append(trs);
					$tbody.find('tr:last td:eq(1)').text(globalVar.param.signingTimeStr+'汇总');
					globalVar.isChange = false;
				}
			}else{
				layer.msg("删除失败");
			}
		},"json").error(function(){
			layer.msg("网络异常");
		});
	}
}

function doExport(){
 	var $form = $('<form></form>'),url;
 	url  = globalVar.base+'/reportStatistics/export.do?useless=';
 	for(var attr in globalVar.param){
 		url += '&'+attr+'='+globalVar.param[attr];
 	}
    // 设置属性  
    $form.attr('action', url);  
    $form.attr('method', 'post'); 
    $(document.body).append($form);
	$form.submit();
}

function formatDate(now) {
    if (now == undefined || now == "" || now == null)  return '';
    var now = new Date(now);
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    return year + "-" + fixZero(month, 2);
}

function fixZero(num, length) {
    var str = "" + num;
    var len = str.length;
    var s = "";
    for (var i = length; i-- > len;) {
        s += "0";
    }
    return s + str;
}