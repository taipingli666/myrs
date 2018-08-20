package com.choice.domain.service.external.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.external.AppointmentCheckService;
import com.choice.domain.service.external.DualReferralReportService;
import com.choice.domain.vo.ChannelResult;
import com.choice.sign.util.ErrorCode;
import com.choice.sign.util.Page;
import com.choice.sign.util.PublicParamBuild;
import com.choice.sign.util.UuIdUtil;

/**
 * 预约检查业务类
 * @author 刘璐骞
 *
 */
@Service
public class AppointmentCheckServiceImpl implements AppointmentCheckService{

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
	/**
	 * 获取检查大类
	 */
	@Override
	public String getCheckClass(String hosCode) {
		String result;
        try{
            JSONObject parameters =new JSONObject();
            parameters.put("hosCode",hosCode);
            parameters.put("methodCode","2001");
            parameters = PublicParamBuild.publicParam(parameters);
            result = channelDownService.downDataDealJson("getCheckClass",parameters.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
            result = JSONObject.toJSONString(new ChannelResult(ErrorCode.ERROR));
        }

        return result;
	}

	/**
	 * 获取检查小类
	 */
	@Override
	public String getMiniCheckClass(InspectInfo inspectInfo) {
		String result;
        try{
            JSONObject parameters =new JSONObject();
            parameters.put("hosCode",inspectInfo.getHosCode());
            parameters.put("checkClassCode",inspectInfo.getClassCode());
            parameters.put("methodCode","2002");
            parameters = PublicParamBuild.publicParam(parameters);
            result = channelDownService.downDataDealJson("getMiniCheckClass",parameters.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
            result = JSONObject.toJSONString(new ChannelResult(ErrorCode.ERROR));
        }

        return result;
	}

	/**
	 * 获取检查项
	 */
	@Override
	public String getCheckItem(InspectInfo inspectInfo) {
		String result;
        try{
            JSONObject parameters =new JSONObject();
            parameters.put("hosCode",inspectInfo.getHosCode());
            parameters.put("methodCode","2003");
            parameters.put("checkClassCode",inspectInfo.getClassCode());
            parameters.put("miniCheckClassCode",inspectInfo.getMiniClassCode());
            parameters = PublicParamBuild.publicParam(parameters);
            result = channelDownService.downDataDealJson("getCheckItem",parameters.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
            result = JSONObject.toJSONString(new ChannelResult(ErrorCode.ERROR));
        }

        return result;
	}

	/**
	 * 保存检查单
	 */
	@Override
	public Map<String, Object> saveCheckList(HttpServletRequest request,InspectInfo inspectInfo) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		//给检查单设置添加时间
		inspectInfo.setAddTime(new Timestamp(new Date().getTime()));
		int affect = inspectInfoDao.insertInspectInfo(inspectInfo);
		
		ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
		/*获取机构信息*/
        CommonHospital orgFrom = commonHospitalService.getById(Integer.parseInt(user.getHosId()));
        CommonHospital orgTo = commonHospitalService.selectByHosCode(inspectInfo.getHosCode());
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
        dualReferralInfo.setOrgIdTo(orgTo.getHosId().toString());
        dualReferralInfo.setOrgNameTo(orgTo.getHosName());
        dualReferralInfo.setRefType("3");
        dualReferralInfo.setRefStatus("1");
        dualReferralInfo.setHealthId(inspectInfo.getCardId());
        dualReferralInfo.setIcd10(inspectInfo.getIcd10());
        dualReferralInfo.setDiag(inspectInfo.getDiagnose());
        dualReferralInfo.setRefTypeId(inspectInfo.getId());
		
		if(affect == 1) {
			dualReferralInfoDao.insertSelective(dualReferralInfo);
	        dualReferralReportService.insertReportCount(orgFrom,orgTo,inspectInfo.getAddTime());
			//插入成功,返回提示信息
			map.put("success", true);
		}else {
			//插入失败,返回提示信息
			map.put("success", false);
			map.put("errorMsg", "提交预约检查单失败~");
		}
		return map;
	}

	@Override
	public Page getCheckItemList(ItemListParam itemListParam, int pageNumber) {
		int pageSize = 10;
		Integer total = inspectCheckItemDao.totalCount(itemListParam);
		int startIndex = Page.calPageIndex(pageNumber, pageSize);
		List<Map> list = inspectCheckItemDao.itemList(itemListParam, startIndex, pageSize);
		return new Page(pageSize, pageNumber, total, list);
	}


}
