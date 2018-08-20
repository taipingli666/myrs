<#assign base=rc.contextPath />
<base id="base" href="${base}">
<@choiceSign.header />
<@choiceSign.left />
<style type="text/css">
.dow {
	position: relative;
	display: inline-block;
	background: #D0EEFF;
	border: 1px solid #99D3F5;
	border-radius: 4px;
	padding: 4px 12px;
	overflow: hidden;
	color: #1E88C7;
	text-decoration: none;
	text-indent: 0;
	line-height: 30px;
}

.file {
	position: relative;
	display: inline-block;
	background: #D0EEFF;
	border: 1px solid #99D3F5;
	border-radius: 4px;
	padding: 4px 12px;
	overflow: hidden;
	color: #1E88C7;
	text-decoration: none;
	text-indent: 0;
	line-height: 30px;
}

.file input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
}

.file:hover {
	background: #AADFFD;
	border-color: #78C3F3;
	color: #004974;
	text-decoration: none;
}

.demo {
	margin: 20px auto 0 auto;
	height: 70px;
	text-align: center;
}

.button {
	display: inline-block;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	font: 16px/100% 'Microsoft yahei', Arial, Helvetica, sans-serif;
	padding: .5em 2em .55em;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .3);
	-webkit-border-radius: .5em;
	-moz-border-radius: .5em;
	border-radius: .5em;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
}

.button:hover {
	text-decoration: none;
}

.button:active {
	position: relative;
	top: 1px;
}

/* blue */
.blue {
	color: #d9eef7;
	border: solid 1px #0076a3;
	background: #0095cd;
	background: -webkit-gradient(linear, left top, left bottom, from(#00adee),
		to(#0078a5) );
	background: -moz-linear-gradient(top, #00adee, #0078a5);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#00adee',
		endColorstr='#0078a5' );
}

.blue:hover {
	background: #007ead;
	background: -webkit-gradient(linear, left top, left bottom, from(#0095cc),
		to(#00678e) );
	background: -moz-linear-gradient(top, #0095cc, #00678e);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#0095cc',
		endColorstr='#00678e' );
}

.blue:active {
	color: #80bed6;
	background: -webkit-gradient(linear, left top, left bottom, from(#0078a5),
		to(#00adee) );
	background: -moz-linear-gradient(top, #0078a5, #00adee);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#0078a5',
		endColorstr='#00adee' );
}

#result {
	width: 50%;
	height: 100px;
	float: left;
}
#resultup {
	width: 100%;
	height: 100px;
	text-align: center;
	color:#00F
}
</style>
	<div id="resultup" style="display:none">
		<p>上传结束！请返回电脑端填写图片描述</p>
        <p>继续上传请点击：<button class="button blue" onclick="windowreload()">继续上传</button></p>
	</div>

	<div id="ifsuccess" style="display:none">
		<p>上传结果：<progress style="color: #52e136"></progress></p>
        <p id="progress">0 bytes</p>
        <p id="info"></p>
	</div>

	<div id="demo" style="text-align: center;height:150px;">
        <p>点击选择照片按钮进行文件上传</p>
		<a href="javascript:;" class="file" style="margin-top: 20px">选择照片<input class="inputfile"
			type="file" id="imageFile" name="img" multiple="multiple"
			capture="camera" accept="image/*" /> </a>

	</div>

	<div id="demo1" class="demo" style="height:100px;">
		<button class="button blue" onclick="uploadFile()">确定上传</button>
        <button class="button blue" onclick="windowreload()">重新选择</button>
	</div>


	<div id="imgtext"></div>


	<div layout:fragment="page-script">

		<script type="application/javascript">
			/*<![CDATA[*/
            function GetQueryString(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg);  //获取url中"?"符后的字符串并正则匹配
                var context = "";
                if (r != null)
                    context = r[2];
                reg = null;
                r = null;
                return context == null || context == "" || context == "undefined" ? "" : context;
            }

		var _url = "/api/outcht/insertselectivebyiphone";
        var sysdateFileIdValue =GetQueryString("sysdateFileIdValue") ;
        var type =GetQueryString("type") ;
        if(type == 2){
            //说明是会诊上传图片
            _url = "/api/consultationSingle/uploadConsultationImg";
		}
        var ifsuccess=true;
        var locationhref= location.href+"&kk="+Math.random();//页面路径保存
        var totalSize = 0;



        /*上传文件*/
        function uploadFile(){
            var input = document.getElementById("imageFile");
            var files = input.files;
            if(files.length<=0){
                alert("请先选择图片！");
                return false;
            }
            //显示结果
            $("#ifsuccess").css('display','block');
            //循环上传图片
            for ( var i = 0; i < files.length; i++) {
                submitFile(files[i]);
            }
            $("#resultup").css('display','block');
            $("#demo").css('display','none');
            $("#demo1").css('display','none');
            $("#demo2").css('display','none');
            $("#imgtext").css('display','none');
        }

        function submitFile(file){
//            name = file.name;
            size = file.size;
//            type = file.type;
            //$("#progress").next().html("文件名：" + name + " 文件类型：" + type + " 文件大小：" + size);
            totalSize += size;
            if (totalSize > 1024*1024*1024){
                alert("文件大于1G，有可能导致上传失败，请重新选择！！！");
                return false;
            }
            $("#info").html("总大小: " +  (totalSize/(1024*1024)).toFixed(3)  + "M");

            var fd = new FormData();
            fd.append("file", file);
            fd.append("sysdateFileIdValue", sysdateFileIdValue);
            $.ajax({
                type:'POST',
                url:_url,
                data:fd,
                /*必须false才会自动加上正确的Content-Type*/
                contentType:false,
                dataType:"json",
                /*** 必须false才会避开jQuery对 formdata 的默认处理 XMLHttpRequest会对 formdata 进行正确的处理*/
                processData:false,
                xhr: function(){ //获取ajaxSettings中的xhr对象，为它的upload属性绑定progress事件的处理函数
                    myXhr = $.ajaxSettings.xhr();
                    if(myXhr.upload){ //检查upload属性是否存在
                        //绑定progress事件的回调函数
                        myXhr.upload.addEventListener('progress',progressHandlingFunction, false);
                    }
                    return myXhr; //xhr对象返回给jQuery使用
                }
            }).then(function(data){
                //doneCal
                try {
                    if(data == "success"){
                        $("#ifsuccess").append("<p style='color:#52e136'>          上传成功！</p>");
                    }else{
                        $("#ifsuccess").append("<p style='color:#F00'>           上传失败！</p>");
                    }
                } catch (e) {
                }
            },function(){
                //failCal
                alert("上传失败");
            });
            return false;
        };

//            var xhr = null;
//        function uploadFilet(file) {
//            uploadFailed = function(evt) {
//                alert("上传失败");
//            },
//            uploadCanceled = function(evt) {
//                // 取消上传或网络连接断开！
//                alert("取消上传或网络连接断开");
//            };
//            var fd = new FormData();
//            fd.append("img", file);
//            fd.append("sysdateFileIdValue", sysdateFileIdValue);
//            xhr = new XMLHttpRequest();
//            xhr.addEventListener("error", uploadFailed, false);
//            xhr.addEventListener("abort", uploadCanceled, false);
//            xhr.open("POST", "/api/outcht/insertselectivebyiphone", false);
////            xhr.overrideMimeType('text/plain; charset=utf-8');
////            xhr.setRequestHeader('Content-Type', 'multipart/form-data; charset=utf-8');
//            xhr.onload = function(e){
//                alert(this.response);
//                try {
//                    var b =JSON.parse(xhr.responseText);
//                    if(b.success == true){
//                        $("#ifsuccess").append("<p style='color:#00F'>"+b.message+"           上传成功！</p>");
//                    }else{
//                        $("#ifsuccess").append("<p style='color:#F00'>"+b.message+"           上传失败！</p>");
//                    }
//                } catch (e) {
//                }
//
//                if(this.status == 200){
//                    $("#ifsuccess").innerHTML(this.response) ;
//                }
//
//            };
//            xhr.send(fd);
//        }

            //上传进度回调函数：
            function progressHandlingFunction(e) {
                if (e.lengthComputable) {
                    $('progress').attr({value : e.loaded, max : e.total}); //更新数据到进度条
                    var percent = e.loaded/e.total*100;
                    $('#progress').html(e.loaded + "/" + e.total+" bytes. " + percent.toFixed(2) + "%");
                }
            }



        window.onload = function(){
            var input = document.getElementById("imageFile");
            var result,div;

            if(typeof FileReader==='undefined'){
                layer.alert("抱歉，你的浏览器不支持 FileReader");
                input.setAttribute('disabled','disabled');
            }else{
                //绑定上传之后的监听事件
                input.addEventListener('change',readFile,false);

            }
            //选择图片之后
            function readFile(){
                //手机不能多选，留下bug，这里每次change事件的时候，都进行值清空，再进行上传
                $("#imgtext").empty() ;
                for(var i=0;i<this.files.length;i++){
                        if (!input['value'].match(/.jpg|.gif|.png/i)){　　//判断上传文件格式
                              return alert("上传的图片格式不正确，请重新选择");
                    　}
                    var reader = new FileReader();
                    reader.readAsDataURL(this.files[i]);
                    reader.onload = function(e){
                        result = '<div id="result"><img src="'+this.result+'" width="100%" height="300px" alt=""/></div>';
                        $("#imgtext").append(result);
                    }
                }
            }

        }

        //Math.random() 防缓存
        function windowreload(){
            window.location.replace(locationhref);
        }
			/*]]>*/
		</script>
	</div>


