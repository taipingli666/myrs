    /**
     * Created by admin on 2018/2/7.
     */
    $(document).ready(function() {

        /**
         * 表单验证
         */
        var demo=$("#form").Validform({
            btnSubmit:"#next",
            tiptype:4,
            showAllError:true,
            datatype:{//传入自定义datatype类型【方式二】;
                "idCard":
                function(gets,obj,curform,datatype){
                    //该方法由佚名网友提供;

                    var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子;
                    var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值，10代表X;

                    if (gets.length == 15) {
                        return isValidityBrithBy15IdCard(gets);
                    }else if (gets.length == 18){
                        var a_idCard = gets.split("");// 得到身份证数组
                        if (isValidityBrithBy18IdCard(gets)&&isTrueValidateCodeBy18IdCard(a_idCard)) {
                            return true;
                        }
                        return false;
                    }
                    return false;

                    function isTrueValidateCodeBy18IdCard(a_idCard) {
                        var sum = 0; // 声明加权求和变量
                        if (a_idCard[17].toLowerCase() == 'x') {
                            a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作
                        }
                        for ( var i = 0; i < 17; i++) {
                            sum += Wi[i] * a_idCard[i];// 加权求和
                        }
                        valCodePosition = sum % 11;// 得到验证码所位置
                        if (a_idCard[17] == ValideCode[valCodePosition]) {
                            return true;
                        }
                        return false;
                    }

                    function isValidityBrithBy18IdCard(idCard18){
                        var year = idCard18.substring(6,10);
                        var month = idCard18.substring(10,12);
                        var day = idCard18.substring(12,14);
                        var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
                        // 这里用getFullYear()获取年份，避免千年虫问题
                        if(temp_date.getFullYear()!=parseFloat(year) || temp_date.getMonth()!=parseFloat(month)-1 || temp_date.getDate()!=parseFloat(day)){
                            return false;
                        }
                        return true;
                    }

                    function isValidityBrithBy15IdCard(idCard15){
                        var year =  idCard15.substring(6,8);
                        var month = idCard15.substring(8,10);
                        var day = idCard15.substring(10,12);
                        var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
                        // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
                        if(temp_date.getYear()!=parseFloat(year) || temp_date.getMonth()!=parseFloat(month)-1 || temp_date.getDate()!=parseFloat(day)){
                            return false;
                        }
                        return true;
                    }

                },
                "name":/^[\u4E00-\u9FA5\uf900-\ufa2d]{1,6}$/,
                "age":/^([1-9]|[0-9]{2}|1000)$/,
                "tel":/^1[3|4|5|7|8][0-9]\d{4,8}$/,
                "healthId":/\d/
                /*"sex":/^[0|1]$/*/

            },
            ajaxPost:false,
            callback:function(data){
                return true;
            }
        });
        /**
         * 设置表单验证的提示信息
         */
        demo.addRule([
            {
                ele:"#card_id",
                datatype:"idCard",
                nullmsg:"请输入您的身份证号！",
                errormsg:"请输入正确的身份证号！"
            },
            {
                ele:"#pat_name",
                datatype:"name",
                nullmsg:"请输入您的姓名！",
                errormsg:"请输入1到6个中文字符！"
            },
            {
                ele:"#age",
                datatype:"age",
                nullmsg:"请输入您的年龄！",
                errormsg:"请输入正确的年龄！"
            },
          /*  {
                ele:"#sex",
                datatype:"sex",
                nullmsg:"请选择您的性别！",
                errormsg:"请选择正确的性别！"
            },*/
            {
                ele:"#tel",
                datatype:"tel",
                nullmsg:"请输入您的手机号！",
                errormsg:"请输入正确的手机号！"
            },
            {
                ele:"#healthid",
                datatype:"healthId",
                nullmsg:"请输入您的就诊卡号！",
                errormsg:"请输入正确的就诊卡号！"
            }
        ]);
    })
    //下一步
    $("#next").click(function() {

        var hosCode = $("#orgIdTo").find("option:selected").attr("id");
        $("#hosCode").val(hosCode);
        //console.log(id);*/
        $("form").submit();
    })

    //身份证查询
    $("#search").click(function() {
        var cardId = $("#card_id").val();
        $.get(globalVar.base+"/patientInfo/selectByPatientIdCard.do",{"patientIdCard":cardId},function(data) {
            if(data.data != null) {
                $("#sex").val(data.data.patientGender);
                $("#age").val(data.data.patientAge);
                $("#pat_name").val(data.data.patientName);
                $("#healthid").val(data.data.patientMediumCode);
                $("#tel").val(data.data.patientPhone);
            }else{
                $("#sex").val("");
                $("#age").val("");
                $("#pat_name").val("");
                $("#healthid").val("");
                $("#tel").val("");
            }
        });
    })


    //赋予ICD10 赋值方法
    $('#icd10modeltable tbody').on('dblclick', 'tr', function () {
        var data = $(this);
        $("#icd10").val(data.children("td:eq(0)").text());
        $("#diag").val(data.children("td:eq(1)").text());
        //赋值完毕后， 关闭model层
        $('#icd10model').modal('hide');
    });
    //icd10 展现
    showicd10model = function() {
        $("#icd10model").modal('show');
        searchicd10();

    }

    //icd10分页
    var size = 10;
    var page=1;
    $("#lastpage").click(function () {
        if (page > 1) {
            page = page - 1;
            searchicd10();
        }
    });


    $("#nextpage").click(function () {
        var pageIndex = $("#all_page").html();
        if(page < pageIndex) {
            page = parseInt(page) + 1;
            searchicd10();
        }
    });

    $("#jumppage").click(function() {
        pageIndex = $("#all_page").html();
        jumpto = $("#pageinput").val();
        if(parseInt(jumpto) <= pageIndex) {
            page = jumpto;
            searchicd10();
        }else {
            alert("超出范围！");
        }
    });
    //回车
    function entersearchicd10() {
        page = 1;
        searchicd10();
    }

    //搜索icd10
    function searchicd10() {
        var icdvalue = $('#searchIcdcode').val();
        $("#icd10tbody").empty();
        $.get(globalVar.base+"/referral/icd10list.do", {"searchValue":icdvalue, "pageNum":page, "pageSize":size}, function (response) {
            var total = response.total;
            $("#all_page").html(Math.round((total+size-1)/size));
            $("#pageshow").val(page);
            $.each(response.icd10List,function(m,k){
                $("#icd10tbody").append("<tr><td>"+k.zhenDuanicd+"</td>" +
                    "<td>"+k.zhenDuanName+"</td>" +
                    "<td>"+k.zhenDuanPinYin+"</td></tr>");
            })

        });
    }