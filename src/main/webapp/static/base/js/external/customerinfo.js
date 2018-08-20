var oTable,apipath="/consultationSingle/";
//去掉排班日期的时分秒
function Timeformat(s) {
    return s=s.replace(/\s[\x00-\xff]*/g,'');
}
//计算周岁
function   ages(str) {
    var   r   =   str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if(r==null)return   false;
    var   birth=   new   Date(r[1],   r[3]-1,   r[4]);
    if   (birth.getFullYear()==r[1]&&(birth.getMonth()+1)==r[3]&&birth.getDate()==r[4])
    {
        var today = new Date();
        var age = today.getFullYear()-r[1];

        if(today.getMonth()>birth.getMonth()){
            return age;
        }
        if(today.getMonth()==birth.getMonth()){
            if(today.getDate()>=birth.getDate()){
                return age;
            }else{
                return age-1;
            }
        }
        if(today.getMonth()<birth.getMonth()){
            return age-1;
        }

    }
}
var oTable2,apipath2="/api/customerdemand/";
var col =[{
    field: 'tichusj',
    align : 'center',
    valign : 'middle',
    width:"15%",
    title: '提出时间'
},{
    field: 'xuqiunr',
    align : 'center',
    valign : 'middle',
    width:"40%",
    title: '需求内容'
},{
    field: 'chuliqk',
    align : 'center',
    valign : 'middle',
    width:"40%",
    title: '处理情况'
},{
    title:'操作',
    width:180,
    align : 'center',
    valign : 'middle',
    //列数据格式化
    formatter:function(value,row,index){
        var st = '';
        st += '<a  style="text-decoration:none" class="btn btn-info btn-xs" href="javascript:void(0)"><span style="color: #fff" onclick="editInfo(\''+row.id+'\',\''+row.xuqiunr+'\',\''+row.chuliqk+'\')">修改</span></a>&nbsp;&nbsp;&nbsp;<a style="text-decoration:none" class="btn btn-danger btn-xs" href="javascript:void(0)"><span style="color: #fff" onclick="delInfo(\''+row.id+'\')">删除</span></a>'
        return st;
    }
}];
//初始化表单
$(function(){
    oTable2 = new TableInit($('#demand_Table'),apipath2+'getlistbypage?kehuid='+ yonghubhid);
    oTable2.Init();
})
function responseHandler(res) {
    return {
        "rows": res.data, // 具体每一个bean的列表
        "total": res.recordsTotal,// 总共有多少条返回数据
    }
}
//查询的方法
function queryParams(params,val){
    var json ={
        start:params.offset,
        //每页多少条数据
        length: params.limit,
    }
    return json;
}
function initBootstrapValidator() {
    $('#addForm').bootstrapValidator({
        message: '这个值是无效的',
        excluded : [':disabled'],//[':disabled', ':hidden', ':not(:visible)']排除禁用控件
        feedbackIcons: {/*input状态样式图片*/
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            xuqiunr: {
                validators: {
                    notEmpty: {
                        message: '需求内容不能为空'
                    },
                }
            },
            chuliqk:{
                validators: {
                    notEmpty: {
                        message: '处理情况不能为空'
                    },
                }
            }
        }
    }).on('success.form.bv', function(e) {//点击提交之后
        // Prevent form submission
        e.preventDefault();
//            save();
    });
}
initBootstrapValidator()
function add(){
    //开启验证
    $('#addForm').data('bootstrapValidator').validate();
    if(!$('#addForm').data('bootstrapValidator').isValid()){
        return ;
    }
    var da= $("#addForm").serializeJSON();
    da.kehuid=yonghubhid;
    var index = layer.load(1);
    $.ajax({
        url:apipath2+"save",
        dataType:"json",
        type:"post",
        data:da,
        success:function(data){
            layer.close(index);
            if(data.success == true){
                layer.msg("添加成功");
                inputClear()
                $('#tableModal_add').modal('hide');
                //刷新
                $('#demand_Table').bootstrapTable(('refresh'));
            }else{
                alert(data.message);
            }
        },
        error:function(){
            layer.close(index);
            layer.msg("网络连接异常");
        }
    });
}
//新增模板
function addUI(){
    //开启
    $('#tableModal_add').modal('show');
    $('#J_edit').hide();
    $('#J_addinfo').show();
    inputClear()
    $("#addForm")[0].reset();//新增时表单重置
    initBootstrapValidator();//新增时表单验证重置
}
function editInfo(infoid,xuqiunrText,chuliqkText){
    $('#tableModal_add').modal('show');
    $('#J_edit').show();
    $('#J_addinfo').hide();
    inputClear()
    $("#addForm")[0].reset();//新增时表单重置
    initBootstrapValidator();//新增时表单验证重置
    $("#optionId").val(infoid);//ID赋值到隐藏域
    $("#xuqiunr").html(xuqiunrText);
    $("#chuliqk").html(chuliqkText);
}
function saveinfo(){
    //开启验证
    $('#addForm').data('bootstrapValidator').validate();
    if(!$('#addForm').data('bootstrapValidator').isValid()){
        return ;
    }
    var formjson= $("#addForm").serializeJSON();
    formjson.id=$("#optionId").val();
    var index = layer.load(1);
    $.ajax({
        url:apipath2+"update",
        dataType:"json",
        type:"post",
        data:formjson,
        success:function(data){
            layer.close(index);
            if(data.success == true){
                layer.msg("修改成功");
                inputClear()
                $('#tableModal_add').modal('hide');
                //刷新
                $('#demand_Table').bootstrapTable(('refresh'));
            }else{
                alert(data.message);
            }
        },
        error:function(){
            layer.close(index);
            layer.msg("网络连接异常");
        }
    });
}
//清空方法
function inputClear() {
    $("#xuqiunr").html("");
    $("#xuqiunr").val("");
    $("#chuliqk").html("");
    $("#chuliqk").val("");
    $("#optionId").val("");
}
//删除
function delInfo(id){
    var elconfirm =layer.confirm('确认删除', {
        btn: ['确认','取消'] //按钮
    }, function(){
        var index = layer.load(1);
        $.ajax({
            url:apipath2 + "update",
            dataType:"json",
            type:"post",
            data:{"id":id,"shanchubz":1},
            success:function(data){
                layer.close(index);
                if(data.success == true){
                    layer.msg("删除成功");
                    //刷新
                    $('#demand_Table').bootstrapTable(('refresh'));
                }else{
                    layer.msg("删除失败");
                }
            },
            error:function(){
                layer.close(index);
                layer.msg("网络连接异常");
            }
        });
    }, function(){
        layer.close(elconfirm);
    });
}

//照片管理
function loadImgFile() {
    $.ajax({
        url:"/api/customerdata/getimgbycuid",
        dataType:"json",
        type:"post",
        data:{"kehuid":yonghubhid,"zhonglei":1},
        success:function(data){
            $("#J_img").children().not( $(".ImgAdd")).remove();
            var imgbox=data.data.surgerys.length,
                sslist='',
                allimg='',
                selectoption='<option data-id=" " value="">未选择</option>';
            var imgjson2=[];
            if(data.data.datas.length==0){
               $("#J_HasImg").val(0);
            }else{
                $("#J_HasImg").val(1);
            }
            if(imgbox!=0){
                $("#J_img").show();
                $("#Imgnull").hide();
                var imgfileHint2='<li class="item">';
                $(data.data.surgerys).each(function (n,m) {
                    var imgjson1=[],
                        imgfileHint='<li class="item">';
                    selectoption+='<option value="'+m.id+'" data-id=\"'+m.id+'\">'+m.xiangmumc+'</option>';
                    $(data.data.datas).each(function (n2,m2) {
                        if(m2.shoushuid==m.id){
                            imgjson1.push({"cunchulj":m2.cunchulj,"shoushuid":m2.shoushuid,"leixing":m2.leixing,"time":m2.chuangjianjs,"wenjianmc":m2.wenjianmc,"imgid":m2.id,"paixuzs":m2.paixuzs});
                        }
                    })
                    if(imgjson1.length==0){
                 /*   <em class="hint">暂无图片！</em>*/
                        imgfileHint='<li class="item nullimg">'
                    }
                    var clickjson1=JSON.stringify(imgjson1);
                    var sstime=rmStringSpaceLast(m.shoushurq);
                    sslist+=imgfileHint+'<a href="javascript:void(0)" onclick="javascript:lookImg(this)" data-imgjson=\''+clickjson1+'\'><em class="imgBg"></em><div class="info"><p><span class="title">手术项目：</span>'+m.xiangmumc+'</p><p><span class="title">手术时间：</span>'+sstime+'</p></div></a></li>'

                })

                $(data.data.datas).each(function (n2,m2) {
                    imgjson2.push({"cunchulj":m2.cunchulj,"shoushuid":m2.shoushuid,"leixing":m2.leixing,"time":m2.chuangjianjs,"wenjianmc":m2.wenjianmc,"imgid":m2.id,"paixuzs":m2.paixuzs});
                })
                if(imgjson2.length==0){
                    imgfileHint2='<li class="item nullimg"><em class="hint">暂无图片！</em>'
                }
               var clickjson2=JSON.stringify(imgjson2);
                    allimg+=imgfileHint2+'<a href="javascript:void(0)" onclick="javascript:lookImg(this,0)" data-imgjson=\''+clickjson2+'\'><em class="imgBg"></em><div class="info"><p><span class="title">全部图片</span></p></div></a></li>'



                sslist+=allimg;
                $("#J_img #J_ImgAdd").show();
                $("#J_img #J_ImgAdd").before(sslist);
                $("#shoushuidselect").html(selectoption);
            };
        },
        error:function(){
            layer.msg("网络连接异常");
        }
    });
}
//展示当前手术的照片
function lookImg(me,all) {
    if($(me).parent().hasClass("nullimg")){
        layer.msg("该项目暂无图片，请上传后再预览");
        return false;
    }
   var imglistJson=$(me).data("imgjson"),
        imgst="",
       imgstbefore="",//手术前
       imgstafter0="",//即时
       imgstafter="",//手术后
       imgallr="",//全部
        ImgUrl='/consultationSingle/getsrcimg?path=',
        Imgtype='&type=2';
    if(all==0){
        for(var i=0;i<imglistJson.length;i++){
            /*       var url2=ImgUrl+imglistJson[i].cunchulj+Imgtype;*/
            var photoFl="",
                scshijian,
                scshijian=rmStringSpaceLast(imglistJson[i].time);
            if(imglistJson[i].leixing==1){
                photoFl="分类:手术前";
                imgallr+='<li class="photo-item"><img src=\''+ImgUrl+''+imglistJson[i].cunchulj+''+Imgtype+'\' alt="img"><div class="info-text"><p class="fenlei">'+photoFl+'</p><p class="ssmc" title=\"'+imglistJson[i].wenjianmc+'\">照片名称：'+imglistJson[i].wenjianmc+'</p><p class="time">创建时间：'+scshijian+'</p><a href="javascript:void(0)" onclick="javascript:delImg(this,\''+imglistJson[i].imgid+'\')" class="event btn btn-danger btn-xs">删除</a></div></li>';
            }else if(imglistJson[i].leixing==3){
                photoFl="分类:术后即刻";
                imgallr+='<li class="photo-item"><img src=\''+ImgUrl+''+imglistJson[i].cunchulj+''+Imgtype+'\' alt="img"><div class="info-text"><p class="fenlei">'+photoFl+'</p><p class="ssmc">照片名称：'+imglistJson[i].wenjianmc+'</p><p class="time">创建时间：'+scshijian+'</p><a href="javascript:void(0)" onclick="javascript:delImg(this,\''+imglistJson[i].imgid+'\')" class="event btn btn-danger btn-xs">删除</a></div></li>';
            } else{
                photoFl="分类:手术后";
                var ms = ""
                if(imglistJson[i].paixuzs != null && imglistJson[i].paixuzs != ''){
                    ms = '<p class="fenlei">术后：'+imglistJson[i].paixuzs+'</p>'
                }
                imgallr+='<li class="photo-item"><img src=\''+ImgUrl+''+imglistJson[i].cunchulj+''+Imgtype+'\' alt="img"><div class="info-text"><p class="fenlei">'+photoFl+'</p>'+ms+'<p class="ssmc">照片名称：'+imglistJson[i].wenjianmc+'</p><p class="time">创建时间：'+scshijian+'</p><a href="javascript:void(0)" onclick="javascript:delImg(this,\''+imglistJson[i].imgid+'\')" class="event btn btn-danger btn-xs">删除</a></div></li>';
            }
        }
        imgst+='<div class="j_Gallery" id="J_ItemList"><ul class="img-list list-inline clearfix" >';
        imgst+=imgallr+'</div>';
    }else{
        for(var i=0;i<imglistJson.length;i++){
            /*       var url2=ImgUrl+imglistJson[i].cunchulj+Imgtype;*/
            var photoFl="",
                scshijian,
                scshijian=rmStringSpaceLast(imglistJson[i].time);
            if(imglistJson[i].leixing==1){
                photoFl="分类:手术前";
                imgstbefore+='<li class="photo-item"><img src=\''+ImgUrl+''+imglistJson[i].cunchulj+''+Imgtype+'\' alt="img"><div class="info-text"><p class="fenlei">'+photoFl+'</p><p class="ssmc" title=\"'+imglistJson[i].wenjianmc+'\">照片名称：'+imglistJson[i].wenjianmc+'</p><p class="time">创建时间：'+scshijian+'</p><a href="javascript:void(0)" onclick="javascript:delImg(this,\''+imglistJson[i].imgid+'\')" class="event btn btn-danger btn-xs">删除</a></div></li>';
            }else if(imglistJson[i].leixing==3){
                photoFl="分类:即时";
                imgstafter0+='<li class="photo-item"><img src=\''+ImgUrl+''+imglistJson[i].cunchulj+''+Imgtype+'\' alt="img"><div class="info-text"><p class="fenlei">'+photoFl+'</p><p class="ssmc">照片名称：'+imglistJson[i].wenjianmc+'</p><p class="time">创建时间：'+scshijian+'</p><a href="javascript:void(0)" onclick="javascript:delImg(this,\''+imglistJson[i].imgid+'\')" class="event btn btn-danger btn-xs">删除</a></div></li>';
            } else{
                photoFl="分类:手术后";
                var ms = ""
                if(imglistJson[i].paixuzs != null && imglistJson[i].paixuzs != ''){
                    ms = '<p class="fenlei">术后：'+imglistJson[i].paixuzs+'</p>'
                }
                imgstafter+='<li class="photo-item"><img src=\''+ImgUrl+''+imglistJson[i].cunchulj+''+Imgtype+'\' alt="img"><div class="info-text"><p class="fenlei">'+photoFl+'</p>'+ms+'<p class="ssmc">照片名称：'+imglistJson[i].wenjianmc+'</p><p class="time">创建时间：'+scshijian+'</p><a href="javascript:void(0)" onclick="javascript:delImg(this,\''+imglistJson[i].imgid+'\')" class="event btn btn-danger btn-xs">删除</a></div></li>';
            }
        }
        imgst+='<div class="j_Gallery" id="J_ItemList"><ul class="img-list list-inline clearfix" >';
        imgst+=imgstbefore;
        imgst+='</ul>';
        imgst+='<hr/>';
        imgst+='<ul class="img-list list-inline clearfix">';
        imgst+=imgstafter0;
        imgst+='</ul>';
        imgst+='<hr/>';
        imgst+='<ul class="img-list list-inline clearfix">';
        imgst+=imgstafter;
        imgst+='</ul></div>';
    }

    $("#J_ImgInfo").append(imgst).show();
    $(".j_Gallery").viewer();
    $("#J_ItemList ul").each(function () {
        if($(this).find("li").length==0){
            $(this).html("<li>暂无图片</li>");
        }
    })
   $("#J_img").hide();
    $("#J_DownloadBox").hide();
}

//关闭手术照片
$("#J_ImgInfo .j_Back").on("click",function (ev) {
    ev.preventDefault();
    loadImgFile();//刷新图片列表
    $("#J_ItemList").remove("")
    $("#J_ImgInfo").hide();
    $("#J_img").show();
    $("#J_DownloadBox").show();
})
//删除照片
function delImg(me,imgid){
    var delbox=layer.confirm('确定删除该照片？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        $.ajax({
            type: "POST",
            url: "/api/customerdata/deldata",
            data: {"id":imgid},            //这里上传的数据使用了formData 对象
            dataType:"json",
            success: function(){
                var meindex=$("#J_ItemList ul").index($(me).parents(".list-inline"));
                $(me).parent().parent().remove();
                if($("#J_ItemList ul").eq(meindex).find("li").length==0){
                    $("#J_ItemList ul").eq(meindex).html("<li>暂无图片</li>");
                }
            },
            error: function(){
            }
        });
        layer.close(delbox);
    }, function(){
        layer.close(delbox);
    });
}
//打开照片上传box
function addimgBox(){
    //开启
    $('#J_ImgUploading').modal('show');
    $("#J_TimeRange").removeClass("show");
    resetTime();
}
$(function () {
   /* $('#filer_input').filer({
        limit: 3,
        maxSize: 3,
        extensions: ['jpg', 'jpeg', 'png', 'gif'],
        changeInput: true,
        showThumbs: true
    });*/
    loadImgFile();
    //Modal验证销毁重构
    $('#tableModal_add').on('hidden.bs.modal', function() {
        $("#addForm").data('bootstrapValidator').destroy();
        $('#addForm').data('bootstrapValidator', null);
        initBootstrapValidator();
        //验证销毁重置
    });
 /*   imgUpload({
        inputId:'file', //input框id
        imgBox:'imgBox', //图片容器id
        buttonId:'btn', //提交按钮id
        upUrl:'/api/customerdata/save',  //提交地址
        data:'file', //参数名
        num:"5"//上传个数
    })*/

})
function select01(me) {
    $("#J_leixing").val($(me).find("option:selected").data("value"));
    if($(me).find("option:selected").data("value")==2){
        if($("#J_TimeRange").hasClass("show")){
            return
        }else{
            $("#J_TimeRange").addClass("show");
        }
    }else{
        $("#J_TimeRange").removeClass("show");
        resetTime();
    }
}
function select02(me) {
    $("#shoushuid").val($(me).find("option:selected").data("id"));
}
function danwei(me) {
    if($(me).find("option:selected").data("value")==0){
        $("#J_danwei").val("");
    }else{
        $("#J_danwei").val($(me).find("option:selected").text());
    }
}
//重置手术后时间选择
function resetTime(){
    $("#J_TimeRange input").val("");
    $("#J_danweiSelect option").eq(0).attr("selected",true).siblings().attr("selected",false)
}
//下载照片
  $("#J_Download").on("click",function () {
      if($("#J_HasImg").val()==0){
          layer.msg("暂无图片可供下载！");
          return
      }else {
          var indexdownload=layer.confirm('是否下载全部图片？', {
              btn: ['确定','取消'] //按钮
          }, function(){
              document.getElementById("J_Downbtn").click();
              layer.close(indexdownload);
          }, function(){
              layer.close(indexdownload);
          });
      }
  })


