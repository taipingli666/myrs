<#assign base=rc.contextPath />
 <base id="base" href="${base}">
<@choiceSign.header />

<@choiceSign.left />
<style >
    tbody th{height:46px !important ;}
    .Wdate{padding: 0;}
    .select-box{height:30px;}
    .col-md-10{margin-top:10px;}
    .col-md-5{margin-bottom:10px;}
    label{width:70px;}
    #sampleNo{width:100%;height:100%;padding:0;border:none;}
    .btn{ cursor:pointer;}
    #tPage{overflow:scroll;height:400px;}
    #tPagei .pt_th{padding:0;}
    #tPage th input{display:inline-block;width:100%;height:100%;}
    .oprt-bar{width: 100%;padding:0px;margin:10px 0 10px;}
    .oprt-bar>.btn{ margin:0 5px 0; }
    .top-oprt-bar{
        text-align: center;
        display: block;
    }
    #tbody .curTr{
        background-color:#e4e4e4;
    }
    #contractOfConditions .cl{
        margin-top: 0;
    }
    #query{
        margin-right:25px;
    }

    .doctorList dl{
        border-bottom:1px solid #ccc;
        height:38px;
        line-height:38px;
        color:#333;
        font-size:14px;
    }

    .listTop dl{
        font-weight:bold;
    }
    .doctorList dl dt{
        width:10%;
        float:left;
        text-align:center;
        height:38px;
    }
    .doctorList dl dd{
        float:left;
        width:90%;
    }
    .doctorList dl dd .a_list{
        margin-top:7px;
    }
    .doctorList dl dd li{
        float:left;
        width:10%;
        text-align:center;
    }
    .doctorList dl dd li span{
        display:block;
        border-radius:14px;
        text-decoration:none;
        width:72px;
        height:25px;
        line-height:25px;
        text-align:center;
        cursor:pointer;

    }
    .active,.doctorList dl dd li span:hover{
        background:#2B8AE2 ;
        color:#fff;
    }
    #deptCodeSelect{
        width:150px;
        height:24px;
        margin-top:7px;
    }

    .doctorList  .doctor li{
        width:16.5%;
        float:left;
        text-align:center;
    }
    .doctorList  .doctor li input{
        width:140px;
        height:24px;
        outline:none;
        border:1px solid #E5E4E1;
        padding:6px 8px;
        text-align:center;
        font-weight:normal;
    }
    .copy{
        width:70px;
        height:25px;
        border-radius:14px;
        background:#FFA342;
        border:0;
        color:#fff;
        font-size:14px;
        cursor:pointer;
    }
    .clear{
        width:70px;
        height:25px;
        border-radius:14px;
        background: #ff3b33;
        border:0;
        color:#fff;
        font-size:14px;
        cursor:pointer;
    }
    #listTitle{
        height:40px;
        line-height:40px;

    }
    #listTitle li{
        float:left;
        width:16.5%;
        text-align:center;
    }
    .listBottom dl:nth-of-type(1){
        font-weight:bold;
    }
    .listBox{
        width:100%;
        height:156px;
        overflow:auto;
    }
    .button{
        text-align: center;
        margin-top:50px;
    }
    #save{
        width:115px;
        height:36px;
        border:0;
        cursor:pointer;
        background:#508ce4;
        font-size:16px;
        color:#fff;
        border-radius:7px;
        margin:0 auto;
    }

</style>
<script type="text/javascript" src="${base}/static/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<input type="hidden" id="workGroupId" value="${workGroupId!}" />
<section class="Hui-article-box">
    <nav class="breadcrumb">
        <i class="Hui-iconfont"></i>
        <a href="/" class="maincolor">首页</a>
        <span class="c-gray en">&gt;</span>系统设置
        <span class="c-gray en">&gt;</span>排班模板
    </nav>
    <div class="Hui-article">
        <article class="cl pd-20">
            <form id="formDate">
                <input type="hidden" id="hosCode" name="hosCode" value="${hosId!}" />
                <input type="hidden" id="scheduleType" name="scheduleType" value="1">
                <input type="hidden" id="weekDay" name="weekDay" value="1">
                <input type="hidden" id="deptCode" name="deptCode" value="">
                <input type="hidden" id="workPeriod" name="workPeriod" value="AM">
            </form>
            <div class="doctorList">
                <div class="listTop">
                    <dl>
                        <dt>排班类型 ：</dt>
                        <dd>
                            <ul class="clearfix a_list selM">
                                <#--<li><span class="active" data-type="scheduleType" data-scheduletype="0">预约挂号</span></li>-->
                                <li><span href="##" data-type="scheduleType" data-scheduletype="1">远程会诊</span></li>
                            </ul>
                        </dd>
                    </dl>
                    <dl>
                        <dt>选择日期 ：</dt>
                        <dd>
                            <ul class="clearfix selM a_list">
                                <li><span data-type="weekDay" data-weekday="1" class="active">星期一</span></li>
                                <li><span data-type="weekDay" data-weekday="2">星期二</span></li>
                                <li><span data-type="weekDay" data-weekday="3">星期三</span></li>
                                <li><span data-type="weekDay" data-weekday="4">星期四</span></li>
                                <li><span data-type="weekDay" data-weekday="5">星期五</span></li>
                                <li><span data-type="weekDay" data-weekday="6">星期六</span></li>
                                <li><span data-type="weekDay" data-weekday="7">星期日</span></li>
                            </ul>
                        </dd>
                    </dl>

                    <dl>
                        <dt>选择科室 ：</dt>
                        <dd>
                            <select id="deptCodeSelect" onchange="deptChange()">
                                <option value="" selected>所有科室</option>
                            </select>
                        </dd>
                    </dl>
                    <dl>
                        <dt>选择午别 ：</dt>
                        <dd>
                            <ul class="clearfix a_list selM">
                                <li>
                                    <span data-type="workPeriod" data-workperiod="AM" class="active">上午</span>
                                </li>
                                <li>
                                    <span data-type="workPeriod" data-workperiod="PM">下午</span>
                                </li>
                            </ul>
                        </dd>
                    </dl>
                </div>
                <div class="listBottom">
                    <dl>
                        <dt>科室</dt>
                        <dd>
                            <ul id="listTitle" class="clearfix">
                                <li>医生</li>
                                <li>起始时间</li>
                                <li>结束时间</li>
                                <li>号源数量</li>
                            </ul>
                        </dd>
                    </dl>
                </div>
                <div class="listBox"id="scheduleTemplate">
                </div>
                <div class="button">
                    <button id="save" onclick="save()">保存</button>
                </div>
            </div>
        </article>
<@choiceSign.footer />
    </div>
    <script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
    <script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script>
    <script type="text/javascript" src="${base}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
    <script type="text/javascript">
        var globalVar = {};
        globalVar.base = '${base}';
    </script>
    <script type="text/javascript" src="${base}/static/base/js/contract/contractManage.js"></script>
    <script type="text/javascript" src="${base}/static/base/js/external/scheduletemplatelist.js"></script>

    </body>
    </html>