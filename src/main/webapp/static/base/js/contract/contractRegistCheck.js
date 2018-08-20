
function aa(){
	$("#contractInfo").html(parent.$("#contractInfo").html());
	console.log(parent.$("#contractInfo").html());
	$(".form-examiner-info .input-text").attr("readonly",true);
	$(".form-examiner-info select").attr("disabled",true);
	$("#btnLi").html('<input class="btn btn-primary radius" value="完成签约"  id="signCheckBtn" onclick="sign()"/>&emsp;'+
	'<input class="btn btn-primary radius" value="取消" id="cancelBtn" onclick="cancel()"/>');
	var category = "";
	$("#categoryDiv input:checkbox:checked").each(function(){
		category = category+$(this).next("label").text()+"&emsp;";
	});
	$("#categoryDiv").html(category);
	var disease = "";
	$("#diseaseDiv input:checkbox:checked").each(function(){
		disease = disease+$(this).next("label").text()+"&emsp;";
	});
	$("#diseaseDiv").html(disease);
	parent.$("#contractId").val($("#contractNo").val());
}

$(function(){
	aa();
	
});
window.onresize = function(){
	aa();
};
function sign(){
	parent.registContract();
	layer_close();
}

function cancel(){
	layer_close();
}
