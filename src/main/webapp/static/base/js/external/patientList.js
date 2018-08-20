$("button").click(function () {
    doSearch(1);
})

function doSearch(pageNumber){
    var $form = $("form");
    var url = globalVar.base+'/patientInfo/patients.do?';
    url += $form.serialize();
    window.location = url;
}
function changePage(pageNumber){
    var $form = $("form");
    var url = globalVar.base+'/patientInfo/patients.do?';
    url += $form.serialize();
    url += '&pageNumber=' +pageNumber;
    window.location = url;
}

function dbClick(row){
    var $td= $(row).children('td');
    var index = parent.layer.getFrameIndex(window.name);
    var data = {};
    data.name = $td[0].innerHTML;
    data.gender = $td[1].innerHTML;
    data.age = $td[2].innerHTML;
    data.cardId = $td[3].innerHTML;
    data.tel = $td[4].innerHTML;
    data.mediumId = $td[5].innerHTML;
    parent.addInfo(data);
    parent.layer.close(index);
}