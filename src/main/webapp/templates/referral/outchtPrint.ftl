<!DOCTYPE html>
<html lang="en">
<#assign base=rc.contextPath />
<base id="base" href="${base}">
<head>
    <meta charset="UTF-8">
    <title>${(busDualReferral.orgNameFrom)!} 住院转诊单</title>
    <#--<link rel="stylesheet" type="text/css" href="${base}/static/base/css/table.css">-->
</head>
<#--<link rel="stylesheet" type="text/css" href="${base}/static/base/css/table.css">-->
<style>
/*    .btn-primary{color:#fff;background-color:#5a98de;border-color:#5a98de}
    #table{margin:0 auto;width:720px;text-align:left}
    .line {
        margin: 20px auto 20px;
        border: 2px dashed #dcdcdc; }*/

    .hide{
        width: 530px;
        padding: 20px;
        /*border: 1px solid #ccc;*/
       /* display: none;*/
    }
.hide h3 {
    font-size: 28px;
    text-align: center;
    color: #4e4c4d; }
.hide h4 {
    font-size: 18px;
    text-align: center;
    color: #4e4c4d;
    margin: 20px auto 20px; }
 .hide h4 span {
    margin: 0 30px; }
.hide .group {
    color: #868686;
    margin: 10px auto 0; }
.hide .group label {
    display: inline-block;
    width: 106px; }
.hide .line {
    margin: 20px auto 20px;
    border: 2px dashed #dcdcdc; }
 .hide .tip {
    color: #8d8b8b; }
.hide .btn {
    boder:0px!important;
    margin-top: 50px;
    -webkit-box-pack: justify;
    -webkit-justify-content: space-between;
    -ms-flex-pack: justify;
    justify-content: space-between; }
 .hide .btn a {
    background: #d2d2d2;
    margin: 0; }
 .hide .btn a.active {
    background: #37aafb;
}
.btn {
    margin-top: 100px;
    border: 0px!important;
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-pack: center;
    -webkit-justify-content: center;
    -ms-flex-pack: center;
    justify-content: center; }
.btn a {
    width: 185px;
    height: 35px;
    font-size: 18px;
    line-height: 35px;
    text-align: center;
    background-color: #d2d2d2;
    color: #fff;
    margin: 0 38px; }
 .btn a.active {
    background: #37aafb; }
.hide .btn {
    boder:0px!important;
    margin-top: 50px;
    -webkit-box-pack: justify;
    -webkit-justify-content: space-between;
    -ms-flex-pack: justify;
    justify-content: space-between; }
 .hide .btn a {
    background: #d2d2d2;
    margin: 0; }
 .hide .btn a.active {
    background: #37aafb;
}
</style>

<#--<div id="table" class="hide">
    <div class="hide" id='hide' >
        <h3>${(busDualReferral.orgNameFrom)!}转诊单</h3>
        <h4>
            <span>${(busDualReferral.patName)!}</span>
                <#if (busDualReferral.sex)! == '1'>
                <span>男</span>
                <#else >
                <span>女</span>
                </#if>
            <span>${(busDualReferral.age)!}</span>
        </h4>
        <div class="group">
            <label>身份证号: </label><span>${(busDualReferral.cardId)!}</span>
        </div>
        <div class="group">
            <label>手机号: </label><span>${(busDualReferral.tel)!}</span>
        </div>
        <div class="group">
            <label>地址: </label><span></span>
        </div>
        <div class="group" style='margin: 25px 0 0 0'>
            <label>预约医院: </label><span>${(busDualReferral.orgNameTo)!}</span>
        </div>

        <div class="group">
            <label>预约时间: </label><span>${(busDualReferral.orderDate?date)!}</span>
        </div>
        <div class="group" style='margin: 25px 0 0 0'>
            <label>转出医院: </label><span>${(busDualReferral.orgNameFrom)!}</span>
        </div>
        <div class="group">
            <label>转出科室: </label><span>${(busDualReferral.deptNameFrom)!}</span>
        </div>
        <div class="group">
            <label>转出医生: </label><span>${(busDualReferral.drNameFrom)!}</span>
        </div>
        <div class="group">
            <label>转出人电话: </label><span>${(busDualReferral.drTel)!}</span>
        </div>
        <div class="line"></div>
        <div class="tip">
            1、凭此预约单，携带本人身份证于就诊当日到就诊医院通过客服引导就诊。<br>
            2、请在就诊开始时间前到达医院联系客服，取消预约请在前一天24点前联系送转医院。<br>
            3、如遇专家门诊时间改变，请关注手机短信通知或专家医院公布的门诊排班表。<br>
            4、地址：黄石中心医院门诊2楼“入出院服务处”；客服电话：0714-6288970。<br>
            5、工作时间：<br>
            入出院正常上班时间：上午8:00-12:00 下午13:00-16:00 周六周日节假日为休息时间；<br>
            周六周日节假日由预约中心带班，预约中心电话：0714-6256769。
        </div>
    </div>

</div>
<div align="center" style="padding-top: 20px">
    <#if flag??>
        <div id="btn">
            <input type="button"  class="btn btn-primary tjdy" value="提交并打印"/>
            <input type="button"  class="btn btn-primary tj" value="提交"/>
        </div>
    </#if>
</div>-->
<div class="hide" id='hide'>
    <h3>${(busDualReferral.orgNameFrom)!}转诊单</h3>
    <h4>
        <span>${(busDualReferral.patName)!}</span>
                 <#if (busDualReferral.sex)! == '1'>
                <span>男</span>
                 <#else >
                <span>女</span>
                 </#if>
        <span>${(busDualReferral.age)!}</span>
    </h4>
    <div class="group">
        <label>身份证号: </label><span>${(busDualReferral.cardId)!}</span>
    </div>
    <div class="group">
        <label>手机号: </label><span>${(busDualReferral.tel)!}</span>
    </div>
    <div class="group">
        <label>地址: </label><span></span>
    </div>
    <div class="group" style='margin: 25px 0 0 0'>
        <label>预约医院: </label><span>${(busDualReferral.orgNameTo)!}</span>
    </div>
    <div class="group">
        <label>预约时间: </label><span>${(busDualReferral.orderDate?date)!}</span>
    </div>
    <div class="group" style='margin: 25px 0 0 0'>
        <label>转出医院: </label><span>${(busDualReferral.orgNameFrom)!}</span>
    </div>
    <div class="group">
        <label>转出科室: </label><span>${(busDualReferral.deptNameFrom)!}</span>
    </div>
    <div class="group">
        <label>转出医生: </label><span>${(busDualReferral.drNameFrom)!}</span>
    </div>
    <div class="group">
        <label>转出人电话: </label><span>${(busDualReferral.drTel)!}</span>
    </div>
    <div class="line"></div>
    <div class="tip">
        1、凭此预约单，携带本人身份证于就诊当日到就诊医院通过客服引导就诊。<br>
        2、请在就诊开始时间前到达医院联系客服，取消预约请在前一天24点前联系送转医院。<br>
        3、如遇专家门诊时间改变，请关注手机短信通知或专家医院公布的门诊排班表。<br>
        4、地址：黄石中心医院门诊2楼“入出院服务处”；客服电话：0714-6288970。<br>
        5、工作时间：<br>
        入出院正常上班时间：上午8:00-12:00 下午13:00-16:00 周六周日节假日为休息时间；<br>
        周六周日节假日由预约中心带班，预约中心电话：0714-6256769。
    </div>
    <div class="btn" id="saveBtn">
        <#if flag??>
            <a href="javascript:;" id="sp" class="active">保存并打印</a>
            <a href="javascript:;" id="save" class="active">保存</a>
        </#if>
    </div>
</div>

<script type="text/javascript" src="${base}/static/lib/jquery/1.9.1/jquery.min.js"></script>
<script>
    <#if flag??>
    var index = parent.layer.getFrameIndex(window.name);
    $("#save").click(function () {
        if (parent.ss() == 0) {
            parent.layer.close(index);
            $(":button").attr("disabled","disabled");
            $(":button").css("background-color", "grey");
        }
    });
     $("#sp").click(function () {
         if (parent.ss() == 0) {
             parent.layer.close(index);
             $(":button").attr("disabled","disabled");
             $(":button").css("background-color", "grey");
         }else {
             $("#btn").hide();
             window.print();
         }

     });
    <#else >
    window.print();
    </#if>
</script>
</html>