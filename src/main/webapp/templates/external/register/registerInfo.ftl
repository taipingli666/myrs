<!DOCTYPE html>
<html lang="en">
<#assign base=rc.contextPath />
<base id="base" href="${base}">
<@choiceSign.header />
<@choiceSign.left />
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" type="text/css" href="${base}/static/base/css/table.css">
  <title></title>
</head>
<body>
<section class="Hui-article-box">
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
        <span class="c-gray en">&gt;</span>
        双向转诊管理
        <span class="c-gray en">&gt;</span>
        门诊转诊
        <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
    </nav>
    <div class="Hui-article">
  <div class="index">
      <div class="navBox">
          <label for="">
              <img src="${base}/static/lib/h-ui.admin/images/basic-news/register/list_2.png" alt="">
          </label>
      </div>
    <div class="wrap">

        <div class="top">
            <div class="left">
                <span>科室:</span>
                <div>
                    <div class="dropdown"> 
                        <p>- 请选择科室 -</p>
                        <ul id='dropdown'> 
                        </ul> 
                    </div>
                </div>
            </div>
            <div class='right'>
                <span>${registerInfo.patientName!}</span>
                <#if registerInfo.patientGender! == '1'>
                <span>男</span>
                <#else >
                <span>女</span>
                </#if>
                <span>${registerInfo.patientAge!}</span>
                <span>|</span>
                <span>${hosName!}</span>
            </div>
        </div>
        <div class="main clearfix">
            <table cellspacing="0" id='table' class='fl'>
                <thead>
                <tr><th></th><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th></tr>
                </thead>
                <tbody>
                <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
                <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
                <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
                <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
                </tbody>
            </table>
            <div class="time fr">
                <div>号源</div>
                <ul id='hao'>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>
        </div>
        <div class="btn">
            <a onclick="javascript:history.back(-1);" class='active'>上一步</a>
            <a href="javascript:;" id='next'>下一步</a>
        </div>
        <div class="hide" id='hide'>
            <h3>${hospitalHeader.hosName!}转诊单</h3>
            <h4>
                <span>${registerInfo.patientName!}</span>
                <#if registerInfo.patientGender! == '1'>
                <span>男</span>
                <#else >
                <span>女</span>
                </#if>
                <span>${registerInfo.patientAge!}</span>
            </h4>
            <div class="group">
                <label>身份证号: </label><span>${registerInfo.patientIdCard!}</span>
            </div>
            <div class="group">
                <label>手机号: </label><span>${registerInfo.patientPhone!}</span>
            </div>
            <div class="group">
                <label>地址: </label><span></span>
            </div>
            <div class="group" style='margin: 25px 0 0 0'>
                <label>预约医院: </label><span>${hosName!}</span>
            </div>
            <div class="group">
                <label>预约科室: </label><span id="ks"></span>
            </div>
            <div class="group">
                <label>号源: </label><span id="hy">2018-07-07 13:35 李老师</span>
            </div>
            <div class="group" style='margin: 25px 0 0 0'>
                <label>转出医院: </label><span>${hospitalHeader.hosName!}</span>
            </div>
            <div class="group">
                <label>转出科室: </label><span>${operatorDeptName!}</span>
            </div>
            <div class="group">
                <label>转出医生: </label><span>${operator.trueName!}</span>
            </div>
            <div class="group">
                <label>转出人电话: </label><span>${operator.tel!}</span>
            </div>
            <div class="line"></div>
            <div class="tip">
                1、凭此预约单，携带本人身份证于就诊当日到门诊挂号窗口取号。<br>
                2、请在就诊开始时间前取号，取消预约请在前一天24点前联系送转医院。<br>
                3、如遇专家门诊时间改变，请关注手机短信通知或专家医院公布的门诊排班表。<br>
                4、地址：黄石中心医院门诊2楼“入出院服务处”；客服电话：0714-6288970。<br>
                5、工作时间：<br>
                入出院正常上班时间：上午8:00-12:00 下午13:00-16:00 周六周日节假日为休息时间；<br>
                周六周日节假日由预约中心带班，预约中心电话：0714-6256769。
            </div>
            <div class="btn" id="saveBtn">
                <a href="javascript:;" id="sp" class="active">保存并打印</a>
                <a href="javascript:;" id="save" class="active">保存</a>
            </div>
        </div>
    </div>
  </div>
    </div>
</section>
</body>
<script>
    var base = "${base}";

    $(function(){
        function setDate() {
            for (var i = 1; i < 8; i++) {
                var data = new Date()
                data.setDate(data.getDate() + i)
                var date = data.getDate()
                var month = data.getMonth() + 1
                var day = data.getDay()
                var arr =["周日", "周一", "周二", "周三", "周四", "周五", "周六"]
                var str = month + '/' + date + '<br/>' + arr[day]
                // var str = month>9? month: '0' + month + '' + (date>9? date: '0' + date)
                $('#table thead tr th').eq(i).html(str)
            }
        }
        setDate();
        function getList(){
            $.ajax({
                url:base+ "/register/getDeptList.do?hosCode=${hosNum!}",
                success: function(res){
                    var data = res.resultObjects
                    data.forEach(function(item, index){
                        // console.log(item)
                        $('#dropdown').append('<li><a data-id="'+item.deptCode+'">' +item.deptName + '</a></li>')
                    })
                }});
        }
        getList();

        $(".dropdown p").click(function(){
            var ul = $(this).next('ul');
            if(ul.css("display")=="none"){
                ul.slideDown("fast");
            }else{
                ul.slideUp("fast");
            }
        });
        $(document).on('click',".dropdown ul li a",function(){
            var txt = $(this).text();
            var id = $(this).attr('data-id')
            $(this).closest('ul').siblings('p').html(txt).attr('id',id);
            //$(this).closest('ul').siblings('p');
            $(this).closest('ul').hide();
            getData(id);
        });

        function getData(id) {
            var table = $('#table')
            $('#hao').empty();
            // $.get(base+"/register/getScheduleList.do",{hosCode : 0000, deptCode : id}, function (res) {})
            $.ajax({
                type: 'post',
                url: base+"/register/getScheduleList.do?hosCode=${hosNum!}&deptCode=" +id,
                success: function (res) {
                    // layer.close(loadIndex)
                    var data = res.resultObjects
                    $('#table tbody').empty()
                    data.forEach(function(item, index){
                        var doctor = item.doctor
                        var inner = ''
                        item.source.forEach(function(item1, index){
                            inner += item1.length != 0? "<td data-id="+JSON.stringify(item1).replace(/\ /g,"-")+">可预约</td>": "<td></td>"
                        })
                        $('#table tbody').append('<tr><td>'+doctor+'</td>'+inner+'</tr>')
                    })
                }
            })
            chek();
        }


        $('#table').on('click', 'td', function(){
            var data = $(this).attr('data-id')
            if (!data) {
                return
            }
            $(this).addClass('active').siblings().removeClass('active').parent().siblings().find('td').removeClass('active')
            var obj = JSON.parse(data)
            $('#hao').empty()
            console.log(obj)
            obj.forEach(function(item, index){
                $('#hao').append('<li data-id='+item.ScheduleId+'>' +item.PlanType+ '</li>')
            })
            chek();
        })

        $('#hao').on('click', 'li', function(){
            var id = $(this).attr('data-id')
            if (id == undefined) {
                return
            }
            $(this).addClass('active').siblings().removeClass('active')
            chek();
        })

        $('#next').click(function(){
            if(!$(this).hasClass('active')){
                return
            }
            var keshi = $('.dropdown p').html(); //科室
            var doctor = $('#table .active').parent().find('td').eq(0).html();
            var time = $('#hao .active').html();
            var day = JSON.parse($('#table .active').attr('data-id'))[0].PlanDate;
            var index = day.indexOf('-')
            var date = day.substring(0, index)
            $("#ks").html(keshi);
            $("#hy").html(date +" "+ time +" "+doctor);
            console.log(keshi, doctor, time, date)
            layer.open({
                type: 1,
                title: false,
                shade: 0,
                offset: '40px',
                area: '540px',
                content: $('#hide')
            });
        })

        function chek() {
            if ($('#hao .active').length > 0) {
                $('#next').addClass('active')
            } else {
                $('#next').removeClass('active')
            }
        }
        /**
         * 时间对象的格式化;
         */
        Date.prototype.format = function(format){
            var o = {
                "M+" : this.getMonth()+1, //month
                "d+" : this.getDate(), //day
                "h+" : this.getHours(), //hour
                "m+" : this.getMinutes(), //minute
                "s+" : this.getSeconds(), //second
                "q+" : Math.floor((this.getMonth()+3)/3), //quarter
                "S" : this.getMilliseconds() //millisecond
            }

            if(/(y+)/.test(format)) {
                format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
            }

            for(var k in o) {
                if(new RegExp("("+ k +")").test(format)) {
                    format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
                }
            }
            return format;
        }

        function formatWorkDate(date) {
            var newDate = new Date(date);
            return newDate.format('yyyy-MM-dd')
        }

        function save() {
            var rs = 1;
            var day = JSON.parse($('#table .active').attr('data-id'))[0].PlanDate;
            var index = day.indexOf('-');
            var data = {};
            data.sequenceNumber = $('#hao .active').attr("data-id");
            data.hosCode = '${hosCode!}';
            data.hosName = '${hosName!}';
            data.deptCode = $('.dropdown p').attr('id');
            data.deptName = $('.dropdown p').html();
            data.doctorName = $('#table .active').parent().find('td').eq(0).html();
            data.workDate = formatWorkDate(day.substring(0, index));
            data.workPeriod = $('#hao .active').html();
            data.patientName = '${patientInfo.patientName!}';
            data.patientMediumCode = '${patientInfo.patientMediumCode!}';
            data.patientGender = '${patientInfo.patientGender!}';
            data.patientAge = '${patientInfo.patientAge!}';
            data.patientIdCard = '${patientInfo.patientIdCard!}';
            data.patientPhone = '${patientInfo.patientPhone!}';
            //JSON.stringify(data);
            console.log(data);
            $.ajax({
                url: base + "/register/registerReservation.do",
                type: "post",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(data),
                async : false,
                dataType: "json",
                success: function (data) {
                    if( data.resultCode == "0"){
                        window.location.href = base + "/reserve/registerCarryOut.do";
                    }else{
                        $("#saveBtn a").removeClass('active');
                        rs = 2;
                        alert("预约挂号失败！");
                    }
                },
                error: function (e) {
                    rs = 2;
                    alert("接口调用错误！");
                }
            });

            return rs;
        }

        $("#sp").click(function () {
            if(!$(this).hasClass('active')){
                return
            }
            if (save() == 1) {
                $("#saveBtn").hide();
                $("#hide").print();
                //layer.close();
                //$("#saveBtn").show();
                setTimeout(function(){
                    window.location.href = base + "/reserve/registerCarryOut.do"
                }, 1000);

            };

            //window.print();
        })

        $("#save").click(function () {
            if(!$(this).hasClass('active')){
                return
            }
            if (save() == 1) {
                setTimeout(function(){
                    window.location.href = base + "/reserve/registerCarryOut.do"
                }, 1000);
            }
        })
    })
</script>
<script type="text/javascript" src="${base}/static/lib/jquery.print/jQuery.print.js"></script>
<#--<script type="text/javascript" src="${base}/static/base/js/external/registerInfo.js"></script>-->
</html>