package com.choice.domain.service.external.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.external.DualReferralInfo;
import com.choice.domain.entity.external.InspectInfo;
import com.choice.domain.entity.external.ItemListParam;
import com.choice.domain.entity.referral.BusDualReferral;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.repository.external.DualReferralInfoDao;
import com.choice.domain.repository.external.InspectCheckItemDao;
import com.choice.domain.repository.external.InspectInfoDao;
import com.choice.domain.service.channel.ChannelDownService;
import com.choice.domain.service.channel.impl.ChannelDownServiceImpl;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.external.AppointmentAssayService;
import com.choice.domain.service.external.DualReferralInfoService;
import com.choice.domain.service.external.DualReferralReportService;
import com.choice.domain.vo.ChannelResult;
import com.choice.sign.util.DateUtil;
import com.choice.sign.util.ErrorCode;
import com.choice.sign.util.Page;
import com.choice.sign.util.PublicParamBuild;
import com.choice.sign.util.UuIdUtil;
import com.choice.sign.util.http.HttpHelperTime30;
import com.choice.sign.util.http.HttpResult;

@Service
public class AppointmentAssayServiceImpl implements AppointmentAssayService {

	@Autowired
	private ChannelDownService channelDownService;
	
	@Autowired
	private InspectInfoDao inspectInfoDao;
	
	@Autowired
	private InspectCheckItemDao inspectCheckItemDao;
	
	@Autowired 
	private CommonHospitalService commonHospitalService;
	
	@Autowired
	private DualReferralInfoDao dualReferralInfoDao;
	
	@Autowired
	private DualReferralReportService dualReferralReportService;
	
	private Logger logger = LoggerFactory.getLogger(ChannelDownServiceImpl.class);
	private static final String URL="http://localhost:8080/choice-sign/channelTest/testData";
	 
	@Override
	public String getAssayClass(String hosCode) {
		 String result;
	        try{
	            JSONObject parameters = new JSONObject();
	            parameters.put("hosCode",hosCode);
	            parameters.put("methodCode","3001");
	            parameters = PublicParamBuild.publicParam(parameters);
	            result = channelDownService.downDataDealJson("getAssayClass",parameters.toJSONString());
	        }catch (Exception e){
	            e.printStackTrace();
	            result = JSONObject.toJSONString(new ChannelResult(ErrorCode.ERROR));
	        }

	        return result;
	}

	@Override
	public String getMiniAssayClass(InspectInfo inspectInfo) {
		String result;
        try{
            JSONObject parameters = new JSONObject();
            parameters.put("hosCode", inspectInfo.getHosCode());
            parameters.put("assayClassCode", inspectInfo.getClassCode());
            parameters.put("methodCode", "3002");
            parameters = PublicParamBuild.publicParam(parameters);
            result = channelDownService.downDataDealJson("getMiniAssayClass", parameters.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
            result = JSONObject.toJSONString(new ChannelResult(ErrorCode.ERROR));
        }

        return result;
	}

	@Override
	public String getAssayItem(InspectInfo inspectInfo) {
		String result;
        try{
            JSONObject parameters = new JSONObject();
            parameters.put("hosCode", inspectInfo.getHosCode());
            parameters.put("miniAssayClassCode", inspectInfo.getMiniClassCode());
            parameters.put("assayClassCode", inspectInfo.getClassCode());
            parameters.put("assaySampleCode", inspectInfo.getSampleCode());
            parameters.put("methodCode", "3003");
            parameters = PublicParamBuild.publicParam(parameters);
            result = channelDownService.downDataDealJson("getAssayItem",parameters.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
            result = JSONObject.toJSONString(new ChannelResult(ErrorCode.ERROR));
        }

        return result;
	}

	@Override
	public String getAssaySample(InspectInfo info) {
		String result;
        try{
            JSONObject parameters = new JSONObject();
            parameters.put("hosCode",info.getHosCode());
            parameters.put("methodCode","3004");
            parameters = PublicParamBuild.publicParam(parameters);
            result = channelDownService.downDataDealJson("getAssaySample",parameters.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
            result = JSONObject.toJSONString(new ChannelResult(ErrorCode.ERROR));
        }

        return result;
	}

	@Override
	public Page assayList(Integer pageNum, ItemListParam itemListParam) {
		// TODO Auto-generated method stub
		int pageSize = 10;
		Integer total = inspectCheckItemDao.totalCount(itemListParam);
		int startIndex = Page.calPageIndex(pageNum, pageSize);
		List<Map> list = inspectCheckItemDao.itemList(itemListParam, startIndex, pageSize);
		return new Page(pageSize, pageNum, total, list);
	}

	@Override
	public int saveAssayInfo(InspectInfo inspectInfo, HttpServletRequest request) {
		// TODO Auto-generated method stub
		ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
		/*获取机构信息*/
        CommonHospital orgFrom = commonHospitalService.getById(Integer.parseInt(user.getHosId()));
        CommonHospital orgTo = commonHospitalService.selectByHosCode(inspectInfo.getHosCode());
        /**/
        /*保存转诊数据*/
        DualReferralInfo dualReferralInfo = new DualReferralInfo();
        dualReferralInfo.setCmmitDate(inspectInfo.getAddTime());
        dualReferralInfo.setPatName(inspectInfo.getName());
        dualReferralInfo.setSex(inspectInfo.getSex().toString());
        dualReferralInfo.setAge(String.valueOf(inspectInfo.getAge()));
        dualReferralInfo.setTel(inspectInfo.getPhoneNumber());
        dualReferralInfo.setCardType("1");
        dualReferralInfo.setCardId(inspectInfo.getIdCard());
        dualReferralInfo.setOrgIdFrom(orgFrom.getHosId().toString());
        dualReferralInfo.setOrgNameFrom(orgFrom.getHosName());
        dualReferralInfo.setOrgIdTo(inspectInfo.getHosCode());
        dualReferralInfo.setOrgNameTo(orgTo.getHosName());
        dualReferralInfo.setRefType("1");
        dualReferralInfo.setRefStatus("1");
        dualReferralInfo.setHealthId(inspectInfo.getCardId());
        dualReferralInfo.setIcd10(inspectInfo.getIcd10());
        dualReferralInfo.setDiag(inspectInfo.getDiagnose());
        dualReferralInfo.setRefTypeId(inspectInfo.getId());
        int result = inspectInfoDao.insertInspectInfo(inspectInfo);
        if (result == 0) {
			return result;
		}else {
			dualReferralInfoDao.insertSelective(dualReferralInfo);
	        dualReferralReportService.insertReportCount(orgFrom,orgTo,inspectInfo.getAddTime());
		}
        
		return result;
	}

}
