<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>${operaHosName!} 门诊转诊单</title>
</head>
<style>
    #table{margin:0 auto;width:720px;text-align:left}
    .line {
        margin: 20px auto 20px;
        border: 2px dashed #dcdcdc; }
</style>
<body>
<div  id="table">
    <div class="hide" id='hide' >
        <h3>${operaHosName!}转诊单</h3>
        <h4>
            <span>${(registerInfo.patientName)!}</span>
                <#if (registerInfo.patientGender)! == '1'>
                <span>男</span>
                <#else >
                <span>女</span>
                </#if>
            <span>${(registerInfo.patientAge)!}</span>
        </h4>
        <div class="group">
            <label>身份证号: </label><span>${(registerInfo.patientIdCard)!}</span>
        </div>
        <div class="group">
            <label>手机号: </label><span>${(registerInfo.patientPhone)!}</span>
        </div>
        <div class="group">
            <label>地址: </label><span></span>
        </div>
        <div class="group" style='margin: 25px 0 0 0'>
            <label>预约医院: </label><span>${hosName!}</span>
        </div>
        <div class="group">
            <label>预约科室: </label><span id="ks">${(registerInfo.deptName)!}</span>
        </div>
        <div class="group">
            <label>号源: </label><span id="hy">${(registerInfo.visitDate?date)!}  ${(registerInfo.workPeriod)!}  ${(registerInfo.doctorName)!}</span>
        </div>
        <div class="group" style='margin: 25px 0 0 0'>
            <label>送转医院: </label><span>${operaHosName!}</span>
        </div>
        <div class="group">
            <label>送转科室: </label><span>${operaDeptName!}</span>
        </div>
        <div class="group">
            <label>送转医生: </label><span>${operatorName!}</span>
        </div>
        <div class="group">
            <label>送转人电话: </label><span>${operatorTel!}</span>
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
    </div>
    <#--<table border="1" cellspacing="0" style="border-color: #C9C9C9">
        <tr style="background-color: #C9C9C9">
            <td colspan="2" align="center" style="font-weight: bold">
            ${operaHosName!} 门诊转诊单
            </td>
        </tr>
        <tr style="background-color: #C9C9C9">
            <td colspan="2" style="font-weight: bold">
                病人信息
            </td>
        </tr>
        <tr>
            <td>患者姓名：${(registerInfo.patientName)!}</td>
            <td>性别：
            <#if (registerInfo.patientGender)! == '1'>男
            <#else>
            女
            </#if>
            </td>
        </tr>
        <tr>
            <td>身份证号：${(registerInfo.patientIdCard)!}</td>
            <td>年龄：${(registerInfo.patientAge)!}</td>
        </tr>
        <tr>
            <td>联系电话：${(registerInfo.patientPhone)!}</td>
            <td>地址：</td>
        </tr>
        <tr style="background-color: #C9C9C9">
            <td colspan="2" style="font-weight: bold">预约信息</td>
        </tr>
        <tr>
            <td>预约号：${(registerInfo.registerTime)?string("yyyyMMddhhmmss")!}</td>
            <td>挂号费：</td>
        </tr>
        <tr>
            <td>预约医院：${hosName!}</td>
            <td>预约科室：${(registerInfo.deptName)!}</td>
        </tr>
        <tr>
            <td>就诊时间：${(registerInfo.visitDate?date)!}  ${(registerInfo.workPeriod)!}</td>
            <td>转出医院：${operaHosName!}</td>
        </tr>
        <tr>
            <td>转出科室：${operaDeptName!}</td>
            <td>转出医生：${operatorName!}</td>
        </tr>
        <tr>
            <td>转送人电话：${operatorTel!}</td>
            <td>客服电话：0714-6288970</td>
        </tr>
        <tr style="background-color: #C9C9C9">
            <td colspan="2" style="font-weight: bold">
                温馨提示
            </td>
        </tr>
        <tr>
            <td colspan="2">
                1、凭此预约单，携带本人身份证于就诊当日到门诊挂号窗口取号。<br>
                2、请在就诊开始时间前取号，取消预约请在前一天24点前联系送转医院。<br>
                3、如遇专家门诊时间改变，请关注手机短信通知或专家医院公布的门诊排班表。<br>
                4、地址：黄石中心医院门诊2楼“入出院服务处”；客服电话：0714-6288970。<br>
                5、工作时间：<br>
                入出院正常上班时间：上午8:00-12:00 下午13:00-16:00 周六周日节假日为休息时间；周六周日节假日由预约中心带班，预约中心电话：0714-6256769。
            </td>
        </tr>
    </table>-->
</div>
</body>
<script>
    window.print();
</script>
</html>