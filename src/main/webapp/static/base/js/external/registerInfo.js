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
            url:base+ "/register/getDeptList.do?hosCode=00000",
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
            url: base+"/register/getScheduleList.do?hosCode=00000&deptCode=" +id,
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
            shadeClose: true,
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
                //layer.msg(data.errorMsg);
            },
            error: function (e) {
                rs = 2;
                alert("接口调用错误！");
            }
        });

        return rs;
    }

    $("#sp").click(function () {
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
        if (save() == 1) {
            setTimeout(function(){
                window.location.href = base + "/reserve/registerCarryOut.do"
            }, 1000);
        }
    })
})