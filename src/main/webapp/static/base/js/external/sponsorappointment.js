/**
 * Created by administer on 2018-02-06.
 */
$(function(){
    var hosId = $("#hosId").val();
    $('#search').click(function(){
        doSearch(1);
    });
    //search
    function doSearch(pageNumber) {
        var $form = $('form');
        url = contextPath+'/consultationSingle/sponsorappointment?';
        if($("form").valid()){
            url += 'page=' + pageNumber +'&';
            url += $form.serialize();
            window.location = url;
        }
    }
    function changePage(pageNumber){
        doSearch(pageNumber);
    }

//    //下拉框数据回显
//    $('#contractOfConditions select').each(function(){
//        $(this).val($(this).parents('li').find('input').val());
//    });
});

//翻页
function changePage(pageNumber){
	var $form = $('form');
    url = contextPath+'/consultationSingle/sponsorappointment?';
    if($("form").valid()){
        url += 'page=' + pageNumber +'&';
        url += $form.serialize();
        window.location = url;
    }
//    var url = contextPath+"/consultationSingle/sponsorappointment.do?page="+pageNumber;
//    window.location = url;
}


