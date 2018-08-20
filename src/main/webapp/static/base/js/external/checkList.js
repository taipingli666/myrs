$(function(){
	var hosId = $("#hosId").val();
	$('#search').click(function(){
		doSearch(1);
	});
	//search
	function doSearch(pageNumber) {
		var $form = $('form');
 		url = globalVar.base+'/appointmentCheck/checkList?';
	 	if($("form").valid()){	
			url += 'pageNum=' + pageNumber +'&';
			url += $form.serialize();
			window.location = url;
	 	}
	}
	function changePage(pageNumber){
		doSearch(pageNumber);
	}
	
	//下拉框数据回显
	$('#contractOfConditions select').each(function(){
		$(this).val($(this).parents('li').find('input').val());
	});
});

//翻页
function changePage(pageNumber){
	var url = globalVar.base+"/appointmentCheck/checkList.do?pageNum="+pageNumber;
	window.location = url;
}

