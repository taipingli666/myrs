$(function(){
	//获取页面隐藏域中的值
	var uuid = $("#uuid").val();

	$.ajax({
		type: "post",
		url: globalVar.base+"/appointmentCheck/getCheckItemsByInfoId?uuid="+uuid,
		success:function(r){
			//动态向表格中添加信息
			for(var i=0;i<r.length;i++){
				var str = '<tr>'+
							'<td>'+r[i].itemName+'</td>'+
							'<td>'+r[i].startTime+"--"+r[i].endTime+'</td>'+
							'<td>'+"￥"+r[i].price+'</td>'+
						  '</tr>';
				$("#info-show").append(str);
			}
		},
		error:function(){
			alert("获取失败~");
		}
	});
	
	//显示总价格
	$("#cost-show").html("￥"+cost);
	
});