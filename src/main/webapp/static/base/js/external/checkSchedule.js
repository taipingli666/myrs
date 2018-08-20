$(function(){
	
	
})
//获取医院编码
var hosCode = $("#hosCode").val();
//获取检查大类编码
var classCode = $("#classCode").val();
//选择时间时，改变事件
function pickTime($this){
	
	//获取选择日期
	var date = $($this).val();
	
	$.ajax({
		type:"post",
		data:{"hosCode":hosCode,"classCode":classCode,"date":date},
		url:globalVar.base+"/appointmentCheck/getCheckSchedule.do",
		success:function(r){
			var str = '';
			var scheduleList = r.scheduleList;
			for(var i=0;i<scheduleList.length;i++){
				str = '<option value="">'+scheduleList[i].checkTimeStart+'--'+scheduleList[i].checkTimeEnd+'</option>'
				$("#time").append(str);
			}
		},
		error:function(r){
			alert("请求失败");
		}
	});
}

function sure(){
	
	
	var time=$('#time').find('option:selected').text();
	console.log(time);
	var timeArray=time.split("--");
	console.log(timeArray);
	var startTime =parent.$('#'+classCode+" td[class='startTime']").html(timeArray[0]);
	console.log(startTime);
	var startTime=parent.$('#'+classCode+" td[class='endTime']").html(timeArray[1]);
	//关闭弹出层
	layer_close();
}
