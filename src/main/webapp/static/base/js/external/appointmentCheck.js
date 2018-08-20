
$(function(){
	var point,kind,itemCode,itemName,con,itemPrice,className,className1;
	var hosCode = $("#hosCode").val();
	var startTime = '';
	var endTime = '';
	var itemArray = new Array();
	//创建一个预约检查单对象
	var inspectInfo = {};
	//创建一个检查项对象数组
	var itemList = [];
	//展示列表所有检查项名字拼接
	var itemNames = "";
	//展示列表所有价格拼接
	var prices = "";
	//检查大类点击事件
	$('.sin_point').click(function(){
	    //切换检查大类时隐藏检查项模块
	    $('.appointment_item').css('display','none'); 
		
		if($(this).prop('checked')){
			 //设置所有.sin_point 的checked属性值为false
			 $('.sin_point').prop('checked',false);
			 //设置当前点击的.sin_point 的checked属性值为true
			 $(this).prop('checked',true);
			 //获取下一个兄弟元素的html内容(在此的作用是获取大类的名称)
			 point=$(this).parent().next().html();
			 var hosCode = $("#hosCode").val();
			 var checkClassCode = $(this).val();
			 $("#checkClassCode").val(checkClassCode);
			 var data = {"hosCode":hosCode,"classCode":checkClassCode}; 
			 //异步请求获取检查小类
			 $.ajax({
				 type:"post",
				 url:globalVar.base+"/appointmentCheck/getMiniCheckClass",
				 data:JSON.stringify(data),
				 contentType:"application/json",
				 success:function(r){
					 var miniCheckClassList = r.miniCheckClassList;
					 var str = "";
					 //首先清空原来的检查小类
					 $(".miniCheckClass").remove();
					 //循环加载检查小类
					 for(var i=0;i<miniCheckClassList.length;i++){
						 str = '<tr class="miniCheckClass">'+
									'<td class="td_first">'+
									'<input type="checkbox" class="sin_kinds" value="'+miniCheckClassList[i].miniCheckClassCode+'">'+
									'</td>'+
									'<td>'+miniCheckClassList[i].miniCheckClassName+'</td>'+
									'</tr>';
						 $("#miniCheckClassList").append(str);
					 }
					 //让检查小类模块显示
					 $('.appointment_kinds').css('display','block'); 
					
				 },
				 error:function(e){
					 alert(e.errorMsg);
				 }
			 });
		}
	}); 
			 
	
	//检查小类点击事件
	$('body').on("click",".sin_kinds",function(){
		 if($(this).prop('checked')){
			 $('.sin_kinds').prop('checked',false);
			 $(this).prop('checked',true);
			 //获取选取的小类名称
			 kind=$(this).parent().next().html();
			  
			 var hosCode = $("#hosCode").val();
			 //var checkClassCode = $("#checkClassCode").val();
			 var miniCheckClassCode = $(this).val();
			 $("#miniCheckClassCode").val(miniCheckClassCode);
			 var data = {"hosCode":hosCode,"miniClassCode":miniCheckClassCode};
			 //异步请求获取检查项
			 $.ajax({
				 type:"post",
				 url:globalVar.base+"/appointmentCheck/getCheckItem",
				 data:JSON.stringify(data),
				 contentType:"application/json",
				 success:function(r){
					 var checkItemList = r.checkItemList;
					 var str = "";
					 //首先清空原来的检查项
					 $(".checkItem").remove();
					 //循环加载检查项
					 for(var i=0;i<checkItemList.length;i++){
						 str = '<tr class="checkItem">'+
									'<td class="td_first">'+
									'<input type="checkbox" class="sin_items" value="'+checkItemList[i].checkItemCode+'">'+
									'<input type="hidden" class="price" value="'+checkItemList[i].price+'">'+
									'</td>'+
									'<td>'+checkItemList[i].checkItemName+'</td>'+
									'</tr>';
						 $("#checkItemList").append(str);
					 }
					 //让检查项模块显示
					 $('.appointment_item').css('display','block'); 
				 },
				 error:function(e){
					 alert(e.errorMsg);
				 }
			 });
			 
			 
		 }
	});
	
	//点击检查项事件
	$('body').on("click",".sin_items",function(){
		 var checked = $(this).prop('checked');
		 //当前点击检查项名称
		 itemName = $(this).parent().next().html();
		 //当前点击检查项code
		 itemCode = $(this).val();
		 //当前点击检查项价格
	 	 itemPrice = $(this).next().val();
		 //获取对应检查大类的code
		 className=$(".sin_point:checkbox:checked").val();
		 //if(选中)  else(取消选中)
	 	 if(checked){
		 	 //如果所点击的检查项已经在数组itemArray中存在,则提示用户
		 	 var result = $.inArray($(this).val(),itemArray);
		 	 if(result != -1){
		 		 console.info(itemArray);
		 		 alert("您已添加了此检查项,不能重复添加!");
		 		 //取消勾选状态
		 		 $(this).prop('checked',false);
		 		 return;
		 	 }
		 	 //将点击的检查项的itemCode放入数组itemArray中
		 	 itemArray.push($(this).val());
		 	 //判断是否已经添加对应大类的行
		 	 if($("tr[id='"+className+"']").length>0){
		 		 var str = $("#"+className+" .item-show").html();
		 		 //增加检查项显示
		 		 str += ","+itemName;
		 		 $("#"+className+" .item-show").html(str);
		 		 //在该行后添加隐藏input存放添加检查项的itemCode,itemName,itemPrice
		 		 var append = '<input class="itemCodeHidden" type="hidden" value="'+itemCode+'"/>'+
					'<input class="itemNameHidden" type="hidden" value="'+itemName+'"/>'+
					'<input class="itemPriceHidden" type="hidden" value="'+itemPrice+'"/>';
		 		 $("#"+className).children("td:last-child").append(append);
		 		 //更改价格
		 		 var price1 = $("#"+className).find(".price-show").html().substring(1);
		 		 price1 = parseFloat(price1);
		 		 price1 += parseFloat(itemPrice);
		 		 $("#"+className).find(".price-show").html("￥"+price1);
		 		 //向当前大类行中item-show td内添加隐藏input
		 		 
		 	 }else{
		 		con=
				 	 '<tr id='+className+'>'+
						'<td>'+point+'</td>'+
						'<td>'+kind+'</td>'+
						'<td class="item-show">'+","+itemName+'</td>'+
						'<td class="startTime">'+startTime+'</td>'+
						'<td class="endTime">'+endTime+'</td>'+
						'<td style="color:#d53f3f" class="price-show">￥'+itemPrice+'</td>'+
						'<td style="color:red;cursor:pointer">'+
							'<a href="javascript:;" style="color:red" class="appoint">'+
								'预约'+
							'</a>&nbsp;&nbsp;'+
							'<a href="javascript:;" style="color:red" class="delete">'+
								'删除'+
							'</a>'+
							'<input class="itemCodeHidden" type="hidden" value="'+itemCode+'"/>'+
							'<input class="itemNameHidden" type="hidden" value="'+itemName+'"/>'+
							'<input class="itemPriceHidden" type="hidden" value="'+itemPrice+'"/>'+
						'</td>'+
					  '</tr>';  
				 	  console.log($(".price"));
		             $('.selected_detail').append(con);
		 	 }
             //刷新总消费
		 	 getCost();
	 	 }else{ 
 			 //取消勾选时,将该检查项从itemArray中移除
 			 itemArray = remove(itemArray,$(this).val());
 			 //移除对应检查项
 			 var str = $(".item-show").html();
 			 str = str.replace(","+itemName,'');
 			 $(".item-show").html(str);
 			 //移除该检查项在列表中对应的隐藏input
 			 $("#"+className).find(".itemCodeHidden[value='"+itemCode+"']").remove();
 			 $("#"+className).find(".itemNameHidden[value='"+itemName+"']").remove();
 			 $("#"+className).find(".itemPriceHidden[value='"+itemPrice+"']").remove();
 			 //修改价格
 			 var price1 = $("#"+className).find(".price-show").html().substring(1);
	 		 price1 = parseFloat(price1);
	 		 price1 -= parseFloat(itemPrice);
	 		 $("#"+className).find(".price-show").html("￥"+price1)
	 		 //如果移除之后检查项一栏为空,则移除该行
 			 if(str==''){
 				 $("#"+className).remove();
 			 }
	 		 //刷新总消费
		 	 getCost();
 		 }
 	 });
	
	
	//删除按钮点击事件
	$("body").on("click",".delete",function(){
		//获取删除按钮后面所有的class=itemCodeHidden input的值
		var arr = $(this).nextAll('input[class="itemCodeHidden"]');
		for(var i=0;i<arr.length;i++){
			var itemCode = $(arr[i]).val();
			//移除数组itemArray中选中的检查项的itemCode
			itemArray = remove(itemArray,itemCode);
			//移除对应检查项的勾选状态
			$(".sin_items[value='"+itemCode+"']").prop("checked",false);
		}
		//删除本行
		$(this).parent().parent().remove();
		//刷新总消费
	 	getCost();
	});
	
	//提交按钮点击事件
	$("#submit").on("click",function(){
		//给隐藏表单中的总消费赋值
		$("#cost").val(getCost());
		inspectInfo = getInspectInfo(inspectInfo);
		itemList = getCheckItemList(itemList);
		var json = {"inspectInfo":inspectInfo,"itemList":itemList};
		//提交表单
		$.ajax({
			type:"post",
			url:globalVar.base+"/appointmentCheck/saveCheckList",
			data: JSON.stringify(json),
			contentType:"application/json",
			cache:false,
			success:function(r){
				if(r.success == true){
					//提交成功,跳转预约成功页面
					window.location = globalVar.base+"/appointmentCheck/appointSuccess?uuid="+r.uuid+"&cost="+r.cost;
//					$.post(globalVar.base+"/appointmentCheck/appointSuccess",{"appointmentTime":r.appointmentTime,"cost":r.cost,"itemNames":itemNames,"prices":prices});
				}else if(success == false){
					alert(r.errorMsg);
				}
			},
			error:function(){
				itemList = [];
				alert("预约请求失败，请选择预约时间~");
			}
		});
		/*$("form").submit(function(r){
			
		});*/
	});
	
	
	//从数组中移除元素
	function remove(array,item){
		array = $.grep(array,function(value){
			return value != item;
		});
		return array;
	}
	
	
	//遍历列表累加价格并改变显示的总消费
	function getCost(){
		var cost = 0;
		$("td[class='price-show']").each(function(){
			cost += parseFloat($(this).html().substring(1));
		});
		//更改列表下方的金额总计
		$("#cost-show").html("￥"+cost);
		return cost;
	}
	
	
	//弹出预约成功页面
	function success(){
		var url = globalVar.base+"/appointmentCheck/appointSuccess";
		var index = layer.open({
			type: 2,
			content: url
		});
		layer.full(index);
	}
	
	//填充预约检查单对象
	function getInspectInfo(inspectInfo){
		inspectInfo.name = $("#name").val();
		inspectInfo.age = $("#age").val();
		inspectInfo.sex = $("#sex").val();
		inspectInfo.idCard = $("#idCard").val();
		inspectInfo.cardId = $("#cardId").val();
		inspectInfo.type = $("#type").val();
		inspectInfo.cost = $("#cost").val();
		inspectInfo.icd10 = $("#icd10").val();
		inspectInfo.diagnose = $("#diagnose").val();
		inspectInfo.hosCode = $("#hosCode").val();
		inspectInfo.phoneNumber = $("#phoneNumber").val();
		return inspectInfo;
	}
	
	//遍历显示列表中所有隐藏input的值,填充检查项数组
	function getCheckItemList(itemObject){
		//先循环行,再循环每一行中的各个input
		var $tr = $(".selected_detail").find("tr");
		var itemCodeHidden,itemNameHidden,itemPriceHidden;
		//i从1开始,因为第一行是标题栏
		for(var i=1;i<$tr.length;i++){
			startTime = $($tr[i]).find("td[class='startTime']").html();
			endTime = $($tr[i]).find("td[class='endTime']").html();
			itemCodeHidden = $($tr[i]).find(".itemCodeHidden");
			itemNameHidden = $($tr[i]).find(".itemNameHidden");
			itemPriceHidden = $($tr[i]).find(".itemPriceHidden");
			for(var j=0;j<itemCodeHidden.length;j++){
				var item = {};
				item.itemCode = $(itemCodeHidden[j]).val();
				item.itemName = $(itemNameHidden[j]).val();
				item.price = $(itemPriceHidden[j]).val();
				item.startTime = startTime;
				item.endTime = endTime;
				itemList.push(item);
			}
		}
		return itemList;
	}
	
	
	//预约点击事件
	$("body").on("click",".appoint",function(){
		var classCode = $(this).parent().parent().prop("id");
		var url = globalVar.base+"/appointmentCheck/gotoCheckSchedule.do?hosCode="+hosCode+"&classCode="+classCode;
		layer.open({
			  type: 2,
			  title:'选择预约时间',
			  area: ['420px', '300px'], 
			  content: url
		});
	});
});



