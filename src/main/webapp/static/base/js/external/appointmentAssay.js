var inspectInfo = {hosCode : $("#hosCode").val()};
	//所选化验项目code 用于计算是否重复选择化验项
	var assayItemArray = new Array();
	var point,kind,item,con,className,className1,sample;
	//删除统计项
	function del(otr,val) {
		//删除选中项数组元素
		assayItemArray.splice($.inArray(val.toString(),assayItemArray),1);
		//取消对应勾选
		$('.sin_items[value="'+val+'"').prop("checked",false);
		//console.log(assayItemArray);
	   	var a=otr.parentNode.parentNode; 
	   	var idName='name'+a.id.slice(3)
	   	$("#"+idName).prop('checked',false)
	    a.parentNode.removeChild(a); 
	   	getPrice();
	} 
	$(document).ready(function() {
		//查询样本
		$.post(globalVar.base+"/assay/assaySample.do", inspectInfo, function(data,status) {
			if(status == 'success') {
				 $.each(data.assaySampleList,function(m,k) {
			         	$("#sampleTr").after("<tr><td class='td_first'><input type='checkbox' class='sin_sample' value="+k.assaySampleCode+"></td><td>"+k.assaySampleName+"</td></tr>");
		         })
		         //默认选中第一个化验项
		         //$(".sin_sample:eq(0)").attr("checked","checked");
				 //inspectInfo.assaySampleCode = $(".sin_sample").val();
			}
		})
		
		//点击样本
		$('body').on('click','.sin_sample',function() {
	     	 $(".assayItemClass").remove();
	       	 if($(this).prop('checked')) {
	       		$('.appointment_item').css("display","none");
				$('.sin_sample').prop('checked',false);
				//取消已选择的小类
				$('.sin_kinds').prop('checked',false);
				$(this).prop('checked',true);
				sample=$(this).parent().siblings().html();
				//$('.appointment_kinds').css('display','block'); 
				inspectInfo.sampleCode = $(this).val();
			}else{
				//取消选择样本，取消已选择的小类，清空化验项
				$('.appointment_item').css("display","none");
				inspectInfo.sampleCode = null;
				$('.sin_kinds').prop('checked',false);
				$(".assayItemClass").remove();
				
			}
		})	
		
		//查询大类
		$.post(globalVar.base+"/assay/assayClass.do", inspectInfo, function(data,status) {
			if(status == 'success'){
				var result = JSON.stringify(data);
				 $.each(data.assayClassList,function(m,k) {
		         	$("#ksmc").after("<tr><td class='td_first'><input type='checkbox' class='sin_point' value="+k.assayClassCode+"></td><td>"+k.assayClassName+"</td></tr>");
		         })
			}
		})
		
	    //点击大类加载小类
	  	$('body').on('click','.sin_point',function() {
	  		//移除小类/化验项选项
	  		$(".miniAssayClass").remove();
            $(".assayItemClass").remove();
            $('.appointment_item').css('display','none');
            if($(this).prop('checked')) {
				$('.sin_point').prop('checked',false);
	  			$(this).prop('checked',true);
		   		point=$(this).parent().siblings().html();
				$('.appointment_kinds').css('display','block'); 
				inspectInfo.classCode = $(this).val();
				//查询小类
				$.post(globalVar.base+"/assay/miniAssayClass.do", inspectInfo, function(data,status) {
					if(status == 'success') {
						 $.each(data.miniAssayClassList,function(m,k) {
					         	$("#xzlb").after("<tr class='miniAssayClass'><td class='td_first'><input type='checkbox' class='sin_kinds' value="+k.miniAssayClassCode+"></td><td>"+k.miniAssayClassName+"</td></tr>");
				         })
					}
				})
			 }else {
				 //取消选中隐藏小类和项目
				 $('.appointment_kinds').css('display','none'); 
				 $('.appointment_item').css('display','none'); 
			 }
	  	})
	  	
	  	//点击小类加载项目
		$('body').on('click','.sin_kinds',function() {
		 	//移除化验项
			$(".assayItemClass").remove();
	  	 	if($(this).prop('checked')) {
				$('.sin_kinds').prop('checked',false);
			 	$(this).prop('checked',true);
			 	kind=$(this).parent().siblings().html();
				inspectInfo.miniClassCode = $(this).val();
				//查询化验项,如果样本不为空显示项目
				if(inspectInfo.sampleCode != null) {
					$('.appointment_item').css("display","block");
					$.post(globalVar.base+"/assay/assayItem.do",inspectInfo, function(data,status) {
						if(status == 'success') {
							 $.each(data.assayItemList,function(m,k) {
					         	$("#assayItemTr").after("<tr class='assayItemClass'><td class='td_first'>"+
					         			"<input type='checkbox' class='sin_items' value="+k.assayItemCode+">"+
					         			"<input type='hidden' value='"+k.price+"'></td>"+
					         			"<td>"+k.assayItemName+"</td></tr>");
					         })
						}
					})
				}
	 		}else {
	 			//小类取消选中,隐藏项目
	 			$('.appointment_item').css("display","none");
	 		}
		})
		
		//点击项目加载统计表格
		$('body').on('click','.sin_items',function() {
		 	if($(this).prop('checked')) {
		 		var itemVal = $(this).val();
		 		//alert(typeof itemVal);
		 		//判断统计表格中是否已经选择过此项目,没有就添加一行
		 		if($.inArray(itemVal,assayItemArray) == -1) {
		 			item=$(this).parent().siblings().html();
		 			var price = $(this).next().val();
			  		 con=
		 		 	'<tr id='+itemVal+' class="itemCode">'+
					'<td>'+point+'</td>'+
					'<td>'+kind+'</td>'+
					'<td>'+sample+'</td>'+
					'<td class="itemName">'+item+'</td>'+
					'<td style="color:#d53f3f" class="price">'+price+'</td>'+
					'<td style="color:red;cursor:pointer">'+
					'<a href="javascript:;" style="color:red"  onclick="del(this,'+itemVal+')">'+
					'删除'+
					'</a>'+
					'</td>'+
					'</tr>';  
		           	$('.selected_detail').append(con);
		 			assayItemArray.push(itemVal);
		 			getPrice();
		 		}
		 	 }else{
		 		 var itemVal = $(this).val();
		 		 //取消选择时候从数组中删除选择项的assayItemCode
	 			 assayItemArray.splice($.inArray(itemVal,assayItemArray),1);
		 	 	 $("#"+itemVal).remove();
 	 			 getPrice();
		 	 }
	 	})
	})/*document end*/

	//点击提交按钮
	$("#submit").click(function() {
		//表格中化验项名-完成界面用-并保存到化验项表
		var itemName = "";
		//表格中每个化验项对应的金额-完成页面用-并保存到化验项表
		var priceArr = new Array();
		//表格中每个化验项对应的code保存到化验项表
		var itemCodeArr = new Array();
		$("td[class='itemName']").each(function(){
			 itemName += $(this).html()+",";
		}) 
		$(".price").each(function() {
			priceArr.push($(this).html());
		})
		$(".itemCode").each(function() {
			itemCodeArr.push($(this).attr("id"));
		})
		itemName = itemName.substring(0,itemName.length-1)
		$("#itemName").val(itemName);
		$("#itemCode").val(itemCodeArr.toString()); 
		$("#itemPrice").val(priceArr.toString()); 
		$("#cost").val($("#price").html());
		//日期验证
		if($("#appointmentTime").val() == "" ||$("#appointmentTime").val() == null) {
			alert("请选择预约时间！");
			return false;
		}
		//判断是否有选择化验项
		if(assayItemArray.length == 0){
			alert("请选择化验项！")
		}else{
			//$("form").submit();
			var object = $("#form").serialize();
			//化验提交，提交成功跳转成功界面
			$.post(globalVar.base+"/assay/assayCommit.do", object, function(data, status) {
				if(status == 'success') {
					if(data.resultCode == 0) {
						window.location.href = globalVar.base+"/assay/assayFinish.do?item="
								+itemName+"&price="+priceArr+"&time="+data.appointmentTime+"&cost="+data.cost;
					}else {
						alert(data.message);
					}
				}else {
					alert("提交失败！")
				}
			})
		}
	})
	
	//计算化验总金额
    function getPrice(){
		var price = 0;
		 $("td[class='price']").each(function(){
			 price += parseFloat($(this).html());
		})  
		$("#price").html(price.toFixed(1));
	}