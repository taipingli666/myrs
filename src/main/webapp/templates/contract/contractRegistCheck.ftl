<#assign base=rc.contextPath />
<!DOCTYPE HTML>
<html>
<head>
 	<base id="base" href="${base}">

	<link rel="stylesheet" type="text/css" href="${base}/static/base/css/min.css" />
	<link rel="stylesheet" type="text/css" href="${base}/static/base/css/admin.css" />
	<link rel="stylesheet" type="text/css" href="${base}/static/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${base}/static/base/skin/blue/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="${base}/static/base/css/style.css" />
	<link rel="stylesheet" type="text/css" href="${base}/static/base/css/main.css" />
	<style>
	tbody th{height:46px !important ;}
	.Wdate{padding: 0;}
	.select-box{height:30px;}
	.col-md-10{margin-top:10px;}
	.col-md-5{margin-bottom:10px;}
	 label{width:90px;}
	 #sampleNo{width:100%;height:100%;padding:0;border:none;}
	 .btn{ cursor:pointer;}
	 #tPage{overflow:scroll;height:400px;}
	 #tPagei .pt_th{padding:0;}
	 #tPage th input{display:inline-block;width:100%;height:100%;}
	 .oprt-bar{width: 100%;padding:0px;margin:10px 0 10px;}
	 .oprt-bar>.btn{ margin:0 5px 0; }
	 .top-oprt-bar{float:right;}
	 #tbody .curTr{
		background-color:#e4e4e4;
	}
		.form-examiner-info .form-label{
		display: inline-block;
		float: left;
		width: 90px;
		padding: 0 0 0 15px;
		text-align:right;
	}
	.form-examiner-info .input-text{width:220px;border:none}
	.form-examiner-info span{width:220px;border:none}
	.chebox{margin-right:2px;margin-left:13px}
	.form-examiner-info select{
		appearance:none;
  		-moz-appearance:none;
  		-webkit-appearance:none;
  		cursor:text;
  	}
  	html,body{
  		height:100%;
  	}
	</style>
	<script type="text/javascript" src="${base}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="${base}/static/base/js/main.js"></script> 
	<script type="text/javascript" src="${base}/static/base/js/admin.page.js"></script> 
</head>
<body>
	<input type="hidden" value="${contractNo}" id="contractNo"/>
	 
	<div  class="page-container" id="contractInfo" style="width:100%;height:85%;margin:0 auto;-webkit-overflow-scrolling:touch; overflow-y: scroll;">
	
	</div>
	 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
	<script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<script type="text/javascript">
		var globalVar = {};
		globalVar.base = '${base}';
	</script>
	<script type="text/javascript" src="${base}/static/base/js/contract/contractRegistCheck.js"></script>
	<script>
	var toScrollFrame = function(iFrame, mask) {  
        if (!navigator.userAgent.match(/iPad|iPhone/i))  
            return false;  
        //do nothing if not iOS devie  

        var mouseY = 0;  
        var mouseX = 0;  
        jQuery(iFrame).ready(function() {//wait for iFrame to load  
            //remeber initial drag motition  
            jQuery(iFrame).contents()[0].body.addEventListener('touchstart', function(e) {  
                mouseY = e.targetTouches[0].pageY;  
                
            });  

            //update scroll position based on initial drag position  
            jQuery(iFrame).contents()[0].body.addEventListener('touchmove', function(e) {  
                e.preventDefault();  
                //prevent whole page dragging  

                var box = jQuery(mask);  
                 
                box.scrollTop(box.scrollTop() + mouseY - e.targetTouches[0].pageY);  
                //mouseX and mouseY don't need periodic updating, because the current position  
                //of the mouse relative to th iFrame changes as the mask scrolls it.  
            });  
        });  

        return true;  
    };  

   
	</script>
</body>
</html>