package com.choice.domain.service.external.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.choice.domain.entity.common.CommonDoctorTeam;
import com.choice.domain.entity.external.*;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.repository.external.RegisterInfoDao;
import com.choice.domain.repository.external.RegisterSourceInfoDao;
import com.choice.domain.repository.external.ScheduleInfoDao;
import com.choice.domain.service.channel.ChannelDownService;
import com.choice.domain.service.channel.ChannelMsgService;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.external.DualReferralInfoService;
import com.choice.domain.service.external.MessageInfoService;
import com.choice.domain.service.external.RegisterService;
import com.choice.domain.service.user.UserService;
import com.choice.domain.vo.ChannelResult;
import com.choice.sign.exception.ParameterException;
import com.choice.sign.util.*;
import com.github.pagehelper.PageHelper;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 预约挂号
 * Created by administer on 2018-01-11.
 * @author lingli
 */
@Service
public class RegisterSerivceImpl implements RegisterService {

    @Resource
    private ChannelDownService channelDownService;

    @Resource
    private RegisterInfoDao registerInfoDao;

    @Resource
    private RegisterSourceInfoDao registerSourceInfoDao;

    @Resource
    private ScheduleInfoDao scheduleInfoDao;

    @Resource
    private DualReferralInfoService dualReferralInfoService;

    @Resource
    private UserService userService;

    @Resource
    private MessageInfoService messageInfoService;

    @Resource
    private ChannelMsgService channelMsgService;

    @Resource
    private CommonHospitalService commonHospitalService;

    private Logger logger = LoggerFactory.getLogger(RegisterSerivceImpl.class);

    /**
     * 获取科室信息
     * @return
     */
    @Override
    public String getDeptList(String hosCode) {
        String data;
        try{
            JSONObject result = new JSONObject();
              /*入参拼接*/
            StringBuffer xmlStr = new StringBuffer();
            xmlStr.append("<tem:hospitaldeptdict>");
            xmlStr.append("<tem:TransID>1005</tem:TransID>");
            xmlStr.append("<tem:hospitalCode>" + hosCode + "</tem:hospitalCode>");
            xmlStr.append("</tem:hospitaldeptdict>");

            /* 调用接口*/
            String outxml=channelDownService.downDataDeal("hospitaldeptdict" , xmlStr.toString() );

            Document doc = XmlUtil.StringToXml(outxml);

            /*出参解析*/
            Map< String , Object > map = XmlUtil.parseXmlStr_Map( doc , "/root/head/*");

            result.put("resultCode" , map.get("ErrCode"));
            result.put("errorMsg" , map.get("ErrMsg"));

            /*判断是否返回成功*/
            if( result.get("resultCode").equals("0")){
                //组装返回list
                List< Map< String , Object > > list = new ArrayList<>();
                List< String> divisionIDList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*","divisionID");
                List< String> divisionNameList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*" ,"divisionName");
                List< String> divisionCodeList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*" ,"divisionCode");
                for( int i = 0 ; i < divisionIDList.size() ; i++ ){
                    Map< String , Object > linkMap = new HashMap<String , Object>();
                    linkMap.put("divisionID",divisionIDList.get(i));
                    linkMap.put("deptName",divisionNameList.get(i));
                    linkMap.put("deptCode",divisionCodeList.get(i));
                    linkMap.put("className",divisionCodeList.get(i));
                    list.add(linkMap);
                }
                result.put("resultObjects" , list);
            }
            data = result.toJSONString();
        }catch (Exception e){
            e.printStackTrace();
            data = JSONObject.toJSONString(new ChannelResult(ErrorCode.ERROR));
        }

        return data;
    }

    /**
     * 获取排班数据
     * @return 结果
     */
    @Override
    public String getScheduleList(String hosCode ,String deptCode) {
        String data;
        try{
            JSONObject result = new JSONObject();
              /*入参拼接*/
            StringBuffer xmlStr = new StringBuffer();
            xmlStr.append("<tem:hospitalclinicforregist>");
            xmlStr.append("<tem:TransID>1007</tem:TransID>");
            xmlStr.append("<tem:hospitalCode>" + hosCode + "</tem:hospitalCode>");
            xmlStr.append("<tem:divisionId>" + deptCode + "</tem:divisionId>");
            xmlStr.append("</tem:hospitalclinicforregist>");

            /* 调用接口*/
            String outxml= channelDownService.downDataDeal("hospitalclinicforregist" , xmlStr.toString() );
            Document doc = XmlUtil.StringToXml(outxml);

            /*出参解析*/
            Map< String , Object > map = XmlUtil.parseXmlStr_Map( doc , "/root/head/*");

            result.put("resultCode" , map.get("ErrCode"));
            result.put("errorMsg" , map.get("ErrMsg"));
            result.put("resultObjects" , null);
            /*判断是否返回成功*/
            if( result.get("resultCode").equals("0")){
                //组装返回list
                List< Map< String , Object > > list = new ArrayList<>();
                List< String> PlanIDList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*","PlanID");
                List< String> PlanTypeList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*" ,"PlanType");
                List< String> PlanDateList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*" ,"PlanDate");
//                List< String> RegTypeIDList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*" ,"RegTypeID");
//                List< String> RegTypeNameList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*" ,"RegTypeName");
//                List< String> divisionIDList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*" ,"divisionID");
//                List< String> divisionNameList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*" ,"divisionName");
//                List< String> doctorIDList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*" ,"doctorID");
//                List< String> doctorNameList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*" ,"doctorName");
//                List< String> RegFeeList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*" ,"RegFee");
//                List< String> ClinicFeeList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*" ,"ClinicFee");
//                List< String> AmLimitNOList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*" ,"AmLimitNO");
//                List< String> PmLimitNOList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*" ,"PmLimitNO");
//                List< String> AvgTimeList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*" ,"AvgTime");
                List< String> ScheduleIdList = XmlUtil.parseXmlStrListItemValue( doc , "/root/body/*" ,"ScheduleId");
                for( int i = 0 ; i < PlanIDList.size() ; i++ ){
                    Map< String , Object > linkMap = new HashMap<String , Object>();
                    linkMap.put("PlanID",PlanIDList.get(i));
                    linkMap.put("PlanType",PlanTypeList.get(i));
                    linkMap.put("PlanDate",PlanDateList.get(i));
//                    linkMap.put("RegTypeID",RegTypeIDList.get(i));
//                    linkMap.put("RegTypeName",RegTypeNameList.get(i));
//                    linkMap.put("divisionID",divisionIDList.get(i));
//                    linkMap.put("divisionName",divisionNameList.get(i));
//                    linkMap.put("doctorID",doctorIDList.get(i));
//                    linkMap.put("doctorName",doctorNameList.get(i));
//                    linkMap.put("RegFee",RegFeeList.get(i));
//                    linkMap.put("ClinicFee",ClinicFeeList.get(i));
//                    linkMap.put("AmLimitNO",AmLimitNOList.get(i));
//                    linkMap.put("PmLimitNO",PmLimitNOList.get(i));
//                    linkMap.put("AvgTime",AvgTimeList.get(i));
                    linkMap.put("ScheduleId",ScheduleIdList.get(i));
                    list.add(linkMap);
                }

                List< Map< String , Object > > outPutlist = new ArrayList<>();

                /*过滤医生*/
                List< String> doctorList= (List<String>) ListUtil.dilterDuplication( PlanIDList );

                for( String str : doctorList ){
                    Map< String , Object > doctorSchedule = new HashMap<>();
                    doctorSchedule.put("doctor" , str );

                    List< Map< String , Object > > filterList = new ArrayList<>();
                    /*过滤医生排班*/
                    filterList = list.stream().filter(a -> a.get("PlanID").equals(str)).collect(Collectors.toList());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
                    Date date = new Date();
                    int day = 1;
                    ArrayList sourceList = new ArrayList();
                    while ( day <= 7){
                        List< Map< String , Object > > dateSourceList = new ArrayList();
                        date  = DateUtils.getDayBegin(DateUtil.getAddDate( date , 1));
                        String dateStr = DateUtil.dateToString( date ,"yyyy/M/d")+" 0:00:00";
                        /*过滤日期排班 */
                        dateSourceList  = filterList.stream().filter(a -> a.get("PlanDate").equals(dateStr)).collect(Collectors.toList());
                        /*根据时间段重新排序*/
                        dateSourceList = ListUtil.listMapSort( dateSourceList ,"PlanType" ,"");
                        sourceList.add(dateSourceList);
                        day++;
                    }
                    doctorSchedule.put("source",sourceList);
                    outPutlist.add( doctorSchedule);
                }

                result.put("resultObjects" , outPutlist);
            }
            data = result.toJSONString();
        }catch (Exception e){
            e.printStackTrace();
            data = JSONObject.toJSONString(new ChannelResult(ErrorCode.ERROR));
        }

        return data;
    }

    /**
     * 获取号源
     * @param registerInfo 预约信息
     * @return 结果
     */
    @Override
    public String getRegisterSource(RegisterInfo registerInfo) {

        /*黄石不使用这个接口*/
        String data;
        try{
            JSONObject result = new JSONObject();
              /*入参拼接*/
            StringBuffer xmlStr = new StringBuffer();
            xmlStr.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><root><head>");
            xmlStr.append("<TransID>1008</TransID>");
            xmlStr.append("<PlanID>" + registerInfo.getScheduleCode() + "</PlanID>");
            xmlStr.append("</head></root>");

            /* 调用接口*/
            String outxml=channelDownService.downDataDeal("1007" , xmlStr.toString() );
            Document doc = XmlUtil.StringToXml(outxml);

            /*出参解析*/
            Map< String , Object > map = XmlUtil.parseXmlStr_Map( doc , "xml/root/head/*");

            result.put("resultCode" , map.get("ErrCode"));
            result.put("resultDesc" , map.get("ErrMsg"));

            /*判断是否返回成功*/
            if( map.get("resultCode").equals("0")){
                List< Map< String , Object > > list = XmlUtil.parseXmlStr_list( doc , "xml/root/body/*" ,"detail");
                result.put("resultObjects" , list);
            }
            data = result.toJSONString();
        }catch (Exception e){
            e.printStackTrace();
            data = JSONObject.toJSONString(new ChannelResult(ErrorCode.ERROR));
        }

        return data;
    }

    /**
     * 预约挂号
     * @param registerInfo 预约信息
     * @return 结果
     */
    @Override
    public String registerReservation(RegisterInfo registerInfo) {
        String data;
        try{
            //保存预约信息
            String idCard = registerInfo.getPatientIdCard();
            registerInfo.setId(UuIdUtil.getUuid());
            registerInfo.setRegisterTime(new Date());
            registerInfo.setRegisterStatus("0");
            registerInfo.setVisitDate(registerInfo.getWorkDate());
            registerInfo.setPatientBirthday(idCard.substring(6,10)+"-"+idCard.substring(10,12)+"-" +idCard.substring(12,14));
            if( 0 == registerInfoDao.insertSelective(registerInfo)){
                throw new ParameterException("保存预约信息失败");
            }else {
                //保存到转诊统计表
                /*保存转诊数据*/
                DualReferralInfo dualReferralInfo = new DualReferralInfo();
                dualReferralInfo.setCmmitDate(registerInfo.getRegisterTime());
                dualReferralInfo.setPatName(registerInfo.getPatientName());
                dualReferralInfo.setSex(registerInfo.getPatientGender());
                dualReferralInfo.setAge(registerInfo.getPatientAge());
                dualReferralInfo.setTel(registerInfo.getPatientPhone());
                dualReferralInfo.setCardId(registerInfo.getPatientIdCard());
                dualReferralInfo.setOrgIdFrom(registerInfo.getOperHosCode());
                dualReferralInfo.setOrgIdTo(registerInfo.getHosCode());
                //门诊
                dualReferralInfo.setRefType("1");
                dualReferralInfo.setRefStatus(registerInfo.getRegisterStatus());
                dualReferralInfo.setOrgNameFrom(commonHospitalService.getById(Integer.valueOf(registerInfo.getOperHosCode())).getHosName());
                dualReferralInfo.setOrgNameTo(commonHospitalService.getById(Integer.valueOf(registerInfo.getHosCode())).getHosName());
                dualReferralInfo.setRefTypeId(registerInfo.getId());
                int result = dualReferralInfoService.insertSelective(dualReferralInfo);
            }
            //调用接口
            JSONObject result = new JSONObject();
              /*入参拼接*/
            StringBuffer xmlStr = new StringBuffer();
            xmlStr.append("<tem:hospitalQYWSappoint>");
            xmlStr.append("<tem:TransID>1010</tem:TransID>");
            xmlStr.append("<tem:plan_id>" + registerInfo.getSequenceNumber() + "</tem:plan_id>");
            xmlStr.append("<tem:name1>" + registerInfo.getPatientName() + "</tem:name1>");
            if( "1".equals(registerInfo.getPatientGender())){
                xmlStr.append("<tem:sex1>男</tem:sex1>");
            }else{
                xmlStr.append("<tem:sex1>女</tem:sex1>");
            }
            xmlStr.append("<tem:phone>" + registerInfo.getPatientPhone() + "</tem:phone>");
            xmlStr.append("<tem:date_of_birth>" + registerInfo.getPatientBirthday() + "</tem:date_of_birth>");
            xmlStr.append("<tem:id_card>" + registerInfo.getPatientIdCard() + "</tem:id_card>");
            xmlStr.append("<tem:Card_no1>" + registerInfo.getPatientMediumCode() + "</tem:Card_no1>");
            xmlStr.append("</tem:hospitalQYWSappoint>");

            /* 调用接口*/
            String outxml=channelDownService.downDataDeal("hospitalQYWSappoint" , xmlStr.toString() );
            Document doc = XmlUtil.StringToXml(outxml);

            /*出参解析*/
            Map< String , Object > map = XmlUtil.parseXmlStr_Map( doc , "Root/*");

            result.put("resultCode" , map.get("ret_code"));
            result.put("errorMsg" , map.get("ret_info"));
            result.put("reg_id" , map.get("reg_id"));

            /*判断是否返回成功*/
            if( result.get("resultCode").equals("0")){
                registerInfo.setTransactionCode(map.get("reg_id").toString());
                registerInfo.setRegisterStatus("2");
            }else{
                registerInfo.setRegisterStatus("3");
            }

            //更新预约信息
            registerInfoDao.updateByPrimaryKeySelective(registerInfo);
//            if(JSON.parseObject(result).get("resultCode").equals("0")){
//                /*插入转诊数据*/
//
//            }

            /*------发送短信----*/
            //保存短信
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //新增成功得id
            String insertId = registerInfo.getId().toString();
            //转出人信息
            ChannelUser operator = userService.getById(Integer.valueOf(registerInfo.getOperator()));
            //发给转出医生
            MessageInfo doctorMsg = new MessageInfo();
            doctorMsg.setMsgContent("【黄石市中心医院】" +operator.getTrueName()+ "医生，您好！黄石市中心医院已经通过了您为"+ registerInfo.getPatientName() +"患者发起的门诊转诊申请。接诊科室："+ registerInfo.getDeptName() +"；接诊医生："+ registerInfo.getDoctorName() +"；接诊时间："+ simpleDateFormat.format(registerInfo.getWorkDate()) +"  "+ registerInfo.getWorkPeriod() +"。地址：黄石中心医院门诊2楼“入出院服务处”；联系电话：0714-6288970。");
            doctorMsg.setMsgId(UuIdUtil.getUuid());
            doctorMsg.setTel(operator.getTel());
            doctorMsg.setMsgKey("B55E51E062EC377E376326E6620C02D279CC854CB9F178EC90864745E454C08D");
            doctorMsg.setExternalId(insertId);
            // 1住院 2转诊
            doctorMsg.setExternalType(2);


            //发给病人
            MessageInfo patientMsg = new MessageInfo();
            patientMsg.setMsgContent("【黄石市中心医院】"+ registerInfo.getPatientName() +"您好！"+ operator.getTrueName() +"医生为您预约的门诊转诊申请已通过。接诊科室："+ registerInfo.getDeptName() +"；接诊医生："+ registerInfo.getDoctorName() +"；接诊时间："+ simpleDateFormat.format(registerInfo.getWorkDate()) +"  "+ registerInfo.getWorkPeriod() + "。请携带转诊单和本人身份证到医院就诊。地址：黄石市中心医院门诊2楼“入出院服务处”；联系电话：0714-6288970。");
            patientMsg.setMsgId(UuIdUtil.getUuid());
            patientMsg.setTel(registerInfo.getPatientPhone());
            patientMsg.setMsgKey("B55E51E062EC377E376326E6620C02D279CC854CB9F178EC90864745E454C08D");
            patientMsg.setExternalId(insertId);
            patientMsg.setExternalType(2);


            //接诊客服1
            MessageInfo serveOneMsg = new MessageInfo();
            serveOneMsg.setMsgContent("【黄石市中心医院】客服，您好！有门诊患者转入本医院，请做好准备工作。患者姓名："+ registerInfo.getPatientName() +"；联系电话："+ registerInfo.getPatientPhone() +"；接诊科室："+ registerInfo.getDeptName() +"；接诊医生："+ registerInfo.getDoctorName() +"；接诊时间："+ simpleDateFormat.format(registerInfo.getWorkDate()) +"  "+ registerInfo.getWorkPeriod() +"");
            serveOneMsg.setMsgId(UuIdUtil.getUuid());
            serveOneMsg.setTel("13597657798");
            serveOneMsg.setMsgKey("B55E51E062EC377E376326E6620C02D279CC854CB9F178EC90864745E454C08D");
            serveOneMsg.setExternalId(insertId);
            serveOneMsg.setExternalType(2);


            //接诊客服2
            MessageInfo serveTwoMsg = new MessageInfo();
            serveTwoMsg.setMsgContent("【黄石市中心医院】客服，您好！有门诊患者转入本医院，请做好准备工作。患者姓名："+ registerInfo.getPatientName() +"；联系电话："+ registerInfo.getPatientPhone() +"；接诊科室："+ registerInfo.getDeptName() +"；接诊医生："+ registerInfo.getDoctorName() +"；接诊时间："+ simpleDateFormat.format(registerInfo.getWorkDate()) +"  "+ registerInfo.getWorkPeriod() +"");
            serveTwoMsg.setMsgId(UuIdUtil.getUuid());
            serveTwoMsg.setTel("13451077909");
            serveTwoMsg.setMsgKey("B55E51E062EC377E376326E6620C02D279CC854CB9F178EC90864745E454C08D");
            serveTwoMsg.setExternalId(insertId);
            serveTwoMsg.setExternalType(2);

            List<MessageInfo> messageInfoList = new ArrayList<>();

            messageInfoList.add(doctorMsg);
            messageInfoList.add(patientMsg);
            messageInfoList.add(serveOneMsg);
            messageInfoList.add(serveTwoMsg);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (MessageInfo messageInfo : messageInfoList) {
                //新增到数据库
                messageInfoService.insert(messageInfo);
                //调用短信接口 如果成功 修改短信状态 2成功 1未发送
                if (channelMsgService.channelMessage(messageInfo).equals("ok")) {
                    messageInfo.setStatus(2);
                    messageInfo.setUpdateTime(sdf.format(new Date()));
                    messageInfoService.update(messageInfo);
                }
            }

            data = result.toJSONString();
        }catch (Exception e){
            e.printStackTrace();
            data = JSONObject.toJSONString(new ChannelResult(ErrorCode.ERROR));
        }

        return data;
    }

    /**
     * 取消预约
     * @param registerInfo 预约信息
     * @return 结果
     */
    @Override
    public String cancelRegisterReservation(RegisterInfo registerInfo) {
        String data;
        try{
            //查询预约信息
            registerInfo = registerInfoDao.selectByPrimaryKey(registerInfo.getId());

            if( registerInfo == null ){
                throw new ParameterException("查询不到数据");
            }
            JSONObject result = new JSONObject();
              /*入参拼接*/
            StringBuffer xmlStr = new StringBuffer();
            xmlStr.append("<tem:hospitalQYWSappointCANCEL>");
            xmlStr.append("<tem:TransID>1011</tem:TransID>");
            xmlStr.append("<tem:reg_id>" + registerInfo.getTransactionCode() + "</tem:reg_id>");
            xmlStr.append("</tem:hospitalQYWSappointCANCEL>");

            /* 调用接口*/
            String outxml= channelDownService.downDataDeal("hospitalQYWSappointCANCEL" , xmlStr.toString() );
            Document doc = XmlUtil.StringToXml(outxml);

            /*出参解析*/
            Map< String , Object > map = XmlUtil.parseXmlStr_Map( doc , "Root/*");

            result.put("resultCode" , map.get("ret_code"));
            result.put("errorMsg" , map.get("ret_info"));

            /*判断是否返回成功*/
            if( result.get("resultCode").equals("0")){
                registerInfo.setRegisterStatus("8");
                //更新预约信息
                registerInfoDao.updateByPrimaryKeySelective(registerInfo);
            }

            data = result.toJSONString();
        }catch (Exception e){
            e.printStackTrace();
            data = JSONObject.toJSONString(new ChannelResult(ErrorCode.ERROR));
        }

        return data;
    }

    /**
     * 预约状态查询
     * @param registerInfo 预约信息
     * @return 结果
     */
    @Override
    public String selectRegisterReservation(RegisterInfo registerInfo) {
        String result;
        try{
            JSONObject parameters =new JSONObject();
            parameters.put("hosCode",registerInfo.getHosCode());
            parameters.put("methodCode","1006");
            parameters.put("deptCode",registerInfo.getDeptCode());
            parameters.put("doctorCode",registerInfo.getDoctorCode());
            parameters.put("registeredType",registerInfo.getRegisterType());
            parameters.put("workDate",registerInfo.getWorkDate());
            parameters.put("workPeriod",registerInfo.getWorkPeriod());
            parameters.put("scheduleCode",registerInfo.getScheduleCode());
            parameters.put("sequenceNumber",registerInfo.getSequenceNumber());
            parameters.put("patientCode",registerInfo.getPatientCode());
            parameters.put("patientName",registerInfo.getPatientName());
            parameters.put("patientMediumType",registerInfo.getPatientMediumType());
            parameters.put("patientMediumCode",registerInfo.getPatientMediumCode());
            parameters.put("patientGender",registerInfo.getPatientGender());
            parameters.put("patientAge",registerInfo.getPatientAge());
            parameters.put("patientBirthday",registerInfo.getPatientBirthday());
            parameters.put("patientIdCard",registerInfo.getPatientIdCard());
            parameters.put("patientPhone",registerInfo.getPatientPhone());
            parameters.put("transactionCode",registerInfo.getTransactionCode());
            parameters = PublicParamBuild.publicParam(parameters);
            result = channelDownService.downDataDealJson("selectRegisterReservation",parameters.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
            result = JSONObject.toJSONString(new ChannelResult(ErrorCode.ERROR));
        }

        return result;
    }

    /**
     * 保存挂号信息
     * @param registerInfo 预约信息
     * @return 结果
     */
    @Override
    @Transactional
    public String saveRegisterInfo(RegisterInfo registerInfo) {
        String result = "";
        try{
            //使用号源
            if(0 == registerSourceInfoDao.useHaoYuan(Long.valueOf(registerInfo.getId()))){
                logger.error("号源状态修改失败【"+JSON.toJSON(registerInfo).toString()+"】");
                throw new ParameterException("号源状态修改失败");
            }
            //修改排班剩余号源
            if(0 == scheduleInfoDao.useHaoYuan( Long.valueOf(registerInfo.getScheduleCode()) )){
                logger.error("修改剩余号源失败【"+JSON.toJSON(registerInfo).toString()+"】");
                throw new ParameterException("修改剩余号源失败");
            }
            /*保存信息*/
            registerInfo.setId(UuIdUtil.getUuid());
            registerInfo.setRegisterTime(new Date());
            registerInfo.setRegisterStatus("0");
            registerInfo.setVisitDate(registerInfo.getWorkDate());
            if( 0 == registerInfoDao.insertSelective(registerInfo)){
                throw new ParameterException("保存预约信息失败");
            }
            /*调用接口*/
//            result = registerReservation(registerInfo);

//            if(JSON.parseObject(result).get("resultCode").equals("0")){
//                /*插入转诊数据*/
//
//            }

        }catch (Exception e){
            e.printStackTrace();
            result =  JSONObject.toJSONString(new ChannelResult(ErrorCode.ERROR));
        }


        return result;
    }

    /**
     * 获取挂号分页列表
     * @param page
     * @param size
     * @param registerInfo
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public Page<RegisterInfo> getRegisterInfoList(Integer page, Integer size, RegisterInfo registerInfo, Date startDate, Date endDate, String refType) {
        com.github.pagehelper.Page startPage = PageHelper.startPage(page, size);
        List<RegisterInfo> list = registerInfoDao.getRegisterInfoList(registerInfo,startDate,endDate, refType);
        Long total = startPage.getTotal();
        return new Page<RegisterInfo>(size, page, total.intValue(), list);
    }

    /**
     * 更具主键选择更新
     * @param registerInfo
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int updateByPrimaryKeySelective(RegisterInfo registerInfo) {
        try{
            //拒绝挂号放出号源
            if("3".equals(registerInfo.getRegisterStatus())){
                return releaseSource(registerInfo);
            }else if("2".equals(registerInfo.getRegisterStatus())){
                RegisterInfo register = registerInfoDao.selectByPrimaryKey(registerInfo.getId());
                //状态2插入转诊记录
                if( 0 == dualReferralInfoService.registerInsert(register)){
                    throw new ParameterException("保存转诊信息失败");
                }

            }

            if( 0 == registerInfoDao.updateByPrimaryKeySelective(registerInfo)){
                throw new ParameterException("保存预约信息失败");
            }

            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }


    }

    @Override
    public RegisterInfo selectByPrimaryKey(String id) {
        return registerInfoDao.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor=Exception.class)
    public int releaseSource(RegisterInfo registerInfo) {
        //查找排班信息
        RegisterInfo info = registerInfoDao.selectByPrimaryKey(registerInfo.getId());
        if (info != null) {
            RegisterSourceInfo registerSourceInfo = new RegisterSourceInfo();
            registerSourceInfo.setScheduleCode(Long.valueOf(info.getScheduleCode()));
            registerSourceInfo.setSequenceNumber(Integer.valueOf(info.getSequenceNumber()));
            //释放号源
            if (0 == registerSourceInfoDao.releaseSource(registerSourceInfo)) {
                logger.error("号源状态修改失败【" + JSON.toJSON(registerInfo).toString() + "】");
                throw new ParameterException("号源状态修改失败");
            }
            //添加剩余数量
            if (0 == scheduleInfoDao.releaseSource(Long.valueOf(info.getScheduleCode()))) {
                logger.error("号源状态修改失败【" + JSON.toJSON(registerInfo).toString() + "】");
                throw new ParameterException("号源状态修改失败");
            }
            if(0 == registerInfoDao.updateByPrimaryKeySelective(registerInfo)){
                throw new ParameterException("更新失败");
            }
        }else{
            return 0;
        }
        return 1;
    }
}
