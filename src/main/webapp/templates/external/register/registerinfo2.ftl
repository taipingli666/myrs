<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<#assign base=rc.contextPath />
<base id="base" href="${base}">
<@choiceSign.header />
<@choiceSign.left />
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-site app"/>
    <link rel="Bookmark" href="favicon.ico" >
    <link rel="Shortcut Icon" href="favicon.ico" />
    <link rel="stylesheet" type="text/css" href="${base}/static/base/css/external/appointment-reg.css"/>
    <link rel="stylesheet" href="${base}/static/lib/font/iconfont/iconfont.css">
    <title>预约检查</title>
</head>
<body>

<section class="Hui-article-box">
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
        <span class="c-gray en">&gt;</span>
        双向转诊管理
        <span class="c-gray en">&gt;</span>
        预约挂号
        <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
    </nav>
    <div class="Hui-article">
        <div class="pd-20">
            <div class="navBox">
                <label for="">
                    <img src="${base}/static/lib/h-ui.admin/images/basic-news/register/list_2.png" alt="">
                </label>
            </div>
            <div class="appointment">
                <div class="appointment_select">
                    <!-- 选择科室 -->
                    <div class="appointment_office" style="margin-right:20px">
                        <div class="sel_title" style="height: 34px;">选择科室分类</div>

                        <div class="sel_con">
                            <table class="office" cellpadding='0' cellspacing='0' border='0' id="firstDept">

                            </table>
                        </div>
                    </div>
                    <!-- 选择类别 -->
                    <div class="appointment_kinds" style="margin-right:20px;display: none">
                        <div class="sel_title" style="height: 34px;">选择科室</div>
                        <div class="sel_con">
                            <table class="office kinds" cellpadding='0' cellspacing='0' border='0' id="secondDept">

                            </table>
                        </div>
                    </div>
                    <!-- 检查项目 -->
                    <div class="appointment_item item" style="display: none">
                        <div class="sel_title" style="height: 34px;">选择医生</div>
                        <div class="sel_con">
                            <table class="office" cellpadding='0' cellspacing='0' border='0' id="doctor">

                            </table>
                        </div>
                    </div>
                </div>

                <!-- 所选项目统计 -->
                <div class="selected_items">
                    <span class="selected_title">医生排班列表：</span>
                    <table border="0" cellpadding="0" cellspacing="0" class="selected_detail" >
                        <thead>
                        <tr>
                            <th width="150">科室</th>
                            <th width="100">医生</th>
                            <th width="100">坐诊日期</th>
                            <#--<th width="130">坐诊时段</th>-->
                            <th width="100">门诊类别</th>
                            <th width="100">操作</th>
                        </tr>
                        </thead>
                        <tbody id="schedule">

                        </tbody>
                    </table>

                </div>
            </div>
        </div>
	<@choiceSign.footer />
    </div>
    <div id="register" class="alertBox">
        <input type="hidden" id="scheduleCode">
        <div class="close">
            <i class="iconfont" onclick="registerClose()">&#xe637;</i>
        </div>
        <dl>
            <dt>科室：</dt>
            <dd>
                <input type="text" readonly="readonly" id="deptName">
            </dd>
        </dl>
        <dl>
            <dt>医生：</dt>
            <dd>
                <input type="text" readonly="readonly" id="doctorName">
            </dd>
        </dl>
        <dl>
            <dt>预约日期：</dt>
            <dd>
                <input type="text" readonly="readonly" id="workDate">
            </dd>
        </dl>
        <#--<dl>-->
            <#--<dt>预约时段：</dt>-->
            <#--<dd>-->
                <#--<select id="workPeriod">-->
                <#--</select>-->
            <#--</dd>-->
        <#--</dl>-->
        <dl>
            <dt>号源：</dt>
            <dd>
                <select id="registerSource">
                </select>
            </dd>
        </dl>
        <dl>
            <dt>姓名：</dt>
            <dd>
                <input type="text" readonly="readonly" id="patientName">
            </dd>
        </dl>
        <dl>
            <dt>介质卡号：</dt>
            <dd>
                <input type="text" readonly="readonly" id="patientMediumCode">
            </dd>
        </dl>

        <button id="tijiao" onclick="saveRegisterInfo()">提交</button>
    </div>

    <!-- 遮罩 -->
    <div class="zhezhao"></div>
    <!-- 遮罩 -->

    <!-- 患者信息-->
    <form id="patient" style="display: none">
        <input type="hidden" name="patientIdCard" id="patientIdCard" value="${registerInfo.patientIdCard!}">
        <input type="hidden" name="patientName" value="${registerInfo.patientName!}">
        <input type="hidden" name="patientGender" value="${registerInfo.patientGender!}">
        <input type="hidden" name="patientAge" value="${registerInfo.patientAge!}">
        <input type="hidden" name="patientPhone" value="${registerInfo.patientPhone!}">
        <input type="hidden" name="patientMediumType" value="${registerInfo.patientMediumType!}">
        <input type="hidden" name="patientMediumCode" value="${registerInfo.patientMediumCode!}">
        <input type="hidden" id="hosCode" name="hosCode" value="${hosCode!}">
        <input type="hidden" name="icd10Code" value="${icd10Code!}">
        <input type="hidden" name="icd10Name" value="${icd10Name!}">
    </form>
</section>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    var base = "${base}";
</script>
<script type="text/javascript"  src="${base}/static/base/js/external/registerInfo2.js"></script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>