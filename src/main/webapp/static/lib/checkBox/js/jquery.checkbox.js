/*
 * checkbox - jQuery plugin for checkbox  images
 * Version:  1.1.1  
 * 20151013 17:23
 */
$.fn.extend({
	checkbox: function(option) {
		var defaults = {
			reqUnit:function(){},
			reqAll:function(){}
		};
		var _this = $(this);
		var $labelall = _this.find("label")
		var $label = _this.find("label.unit-box");
		var $checkall = _this.find(".check-all");
		var $box = _this.find("input[name='unit-box']");
		var timeout = null;
		var defaults  = {};
		var settings = $.extend(defaults, option || {});
		function labelclick(obj){
			if(obj.hasClass("checked")){
				obj.removeClass("checked");
				obj.siblings("input").prop("checked",false);
			} else{
				obj.addClass("checked");
				obj.siblings("input").prop("checked",true);
			}
		}
		$checkall.click(function() {
			labelclick($(this));
			if($(this).hasClass("checked")){
				$box.prop("checked",true);
				$labelall.addClass("checked");
			} else {
				$box.prop("checked",false);
				$labelall.removeClass("checked");
			}
			if(timeout != null){
	            clearInterval(timeout);
	            timeout = null;
	        }
	        timeout = setTimeout(function(){
	          settings.reqAll();
	        }, 1000);
		});
		$label.click(function(){
			labelclick($(this));
			var  checkedl = _this.find("input[name='unit-box']:checked").length;
			var  alll = _this.find("input[name='unit-box']").length;
			if(checkedl == alll){
				$checkall.addClass("checked");
				$checkall.siblings().prop("checked",true);
			} else {
				$checkall.removeClass("checked");
				$checkall.siblings().prop("checked",false);
			}
			if(timeout != null){
	            clearInterval(timeout);
	            timeout = null;
	        }
	        timeout = setTimeout(function(){
	            settings.reqUnit();
	        }, 1000);
		});	
	}
});