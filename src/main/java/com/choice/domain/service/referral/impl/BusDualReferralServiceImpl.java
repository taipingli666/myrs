package com.choice.domain.service.referral.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.choice.domain.entity.external.DualReferralInfo;
import com.choice.domain.entity.external.MessageInfo;
import com.choice.domain.service.channel.ChannelDownService;
import com.choice.domain.service.channel.ChannelMsgService;
import com.choice.domain.service.external.DualReferralInfoService;
import com.choice.domain.service.external.MessageInfoService;
import com.choice.sign.exception.ParameterException;
import com.choice.sign.util.UuIdUtil;
import com.choice.sign.util.XmlUtil;
import org.dom4j.Document;
import org.springframework.stereotype.Service;

import com.choice.domain.entity.dictionary.CommonDictionary;
import com.choice.domain.entity.referral.BusDualReferral;
import com.choice.domain.repository.referral.BusDualReferralDao;
import com.choice.domain.service.referral.BusDualReferralService;
import com.choice.sign.util.Page;
import org.springframework.transaction.annotation.Transactional;

@Service("busDualReferralService")
public class BusDualReferralServiceImpl implements BusDualReferralService {

	@Resource
	private BusDualReferralDao busDualReferralDao;

	@Resource
	private DualReferralInfoService dualReferralInfoService;

	@Resource
	private ChannelMsgService channelMsgService;

	@Resource
	private MessageInfoService messageInfoService;

	public Page getPage(int currentPage, BusDualReferral busDualReferral) {
		int pageSize = 10;
		Integer total = busDualReferralDao.getTotalNumber(busDualReferral);
		int pageIndex = Page.calPageIndex(currentPage, pageSize);
		List<BusDualReferral> list = busDualReferralDao.getPageInfo(busDualReferral, pageIndex, pageSize);
		return new Page<BusDualReferral>(pageSize, currentPage, total, list);
	}

	
	public List<BusDualReferral> selectByDrIdFrom(BusDualReferral busDualReferral) {
		List<BusDualReferral> list = busDualReferralDao.selectByDrIdFrom(busDualReferral);
		Integer total = list.size();
		return list;
	}

	public int insertSelective(BusDualReferral busDualReferral) {

		int rs = busDualReferralDao.insertSelective(busDualReferral);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (rs == 1) {
			//保存到统计表
			/*保存转诊数据*/
			DualReferralInfo dualReferralInfo = new DualReferralInfo();
			dualReferralInfo.setCmmitDate(busDualReferral.getCmmitDate());
			dualReferralInfo.setPatName(busDualReferral.getPatName());
			dualReferralInfo.setSex(busDualReferral.getSex());
			dualReferralInfo.setAge(busDualReferral.getAge());
			dualReferralInfo.setTel(busDualReferral.getTel());
			dualReferralInfo.setCardId(busDualReferral.getCardId());
			dualReferralInfo.setOrgIdFrom(busDualReferral.getOrgIdFrom());
			dualReferralInfo.setOrgNameFrom(busDualReferral.getOrgNameFrom());
			dualReferralInfo.setOrgIdTo(busDualReferral.getOrgIdTo());
			dualReferralInfo.setOrgNameTo(busDualReferral.getOrgNameTo());
			//住院
			dualReferralInfo.setRefType("2");
			dualReferralInfo.setRefStatus(busDualReferral.getRefStatus());
			dualReferralInfo.setHealthId(busDualReferral.getHealthId());
			dualReferralInfo.setRefTypeId(busDualReferral.getId().toString());
			int result = dualReferralInfoService.insertSelective(dualReferralInfo);

			//新增成功得id
			String insertId = busDualReferral.getId().toString();
			//保存短信
			//发给转出医生
			MessageInfo doctorMsg = new MessageInfo();
			doctorMsg.setMsgContent("【黄石市中心医院】" +busDualReferral.getDrNameFrom()+ "医生，您好！黄石市中心医院已经通过了您为" +busDualReferral.getPatName()+ "患者发起的住院转诊申请。" +
					"接诊时间：" +busDualReferral.getOrderDate()+ "。地址：黄石中心医院门诊2楼“入出院服务处”；联系电话：0714-6288970。");
			doctorMsg.setMsgId(UuIdUtil.getUuid());
			doctorMsg.setTel(busDualReferral.getDrTel());
			doctorMsg.setMsgKey("B55E51E062EC377E376326E6620C02D279CC854CB9F178EC90864745E454C08D");
			doctorMsg.setExternalId(insertId);
			// 1住院 2转诊
			doctorMsg.setExternalType(1);


			//发给病人
			MessageInfo patientMsg = new MessageInfo();
			patientMsg.setMsgContent("【黄石市中心医院】" +busDualReferral.getPatName()+ "，您好！" +busDualReferral.getDrNameFrom()+ "医生为您预约的住院转诊申请已通过。接诊时间：" +busDualReferral.getOrderDate()+
					" 。请携带转诊单和本人身份证到医院就诊。地址：黄石中心医院门诊2楼“入出院服务处”；联系电话：0714-6288970。");
			patientMsg.setMsgId(UuIdUtil.getUuid());
			patientMsg.setTel(busDualReferral.getTel());
			patientMsg.setMsgKey("B55E51E062EC377E376326E6620C02D279CC854CB9F178EC90864745E454C08D");
			patientMsg.setExternalId(insertId);
			patientMsg.setExternalType(1);


			//接诊客服1
			MessageInfo serveOneMsg = new MessageInfo();
			serveOneMsg.setMsgContent("【黄石市中心医院】客服，您好！有住院患者转入本医院，请做好准备工作。患者姓名：" +busDualReferral.getPatName()+ "；联系电话：" +busDualReferral.getTel()+ "；接诊时间：" +busDualReferral.getOrderDate()+ "。");
			serveOneMsg.setMsgId(UuIdUtil.getUuid());
			serveOneMsg.setTel("13597657798");
			serveOneMsg.setMsgKey("B55E51E062EC377E376326E6620C02D279CC854CB9F178EC90864745E454C08D");
			serveOneMsg.setExternalId(insertId);
			serveOneMsg.setExternalType(1);


			//接诊客服2
			MessageInfo serveTwoMsg = new MessageInfo();
			serveTwoMsg.setMsgContent("【黄石市中心医院】客服，您好！有住院患者转入本医院，请做好准备工作。患者姓名：" +busDualReferral.getPatName()+ "；联系电话：" +busDualReferral.getTel()+ "；接诊时间：" +busDualReferral.getOrderDate()+ "。");
			serveTwoMsg.setMsgId(UuIdUtil.getUuid());
			serveTwoMsg.setTel("13451077909");
			serveTwoMsg.setMsgKey("B55E51E062EC377E376326E6620C02D279CC854CB9F178EC90864745E454C08D");
			serveTwoMsg.setExternalId(insertId);
			serveTwoMsg.setExternalType(1);

			List<MessageInfo> messageInfoList = new ArrayList<>();

			messageInfoList.add(doctorMsg);
			messageInfoList.add(patientMsg);
			messageInfoList.add(serveOneMsg);
			messageInfoList.add(serveTwoMsg);

			try {
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
			}catch (Exception e) {
				busDualReferral.setRefStatus("5");
				busDualReferralDao.updateByPrimaryKeySelective(busDualReferral);
				rs = 0;
			}

		}
		return rs;
	};
	
	public BusDualReferral selectByPrimaryKey(Long id) {
		return busDualReferralDao.selectByPrimaryKey(id);
	};
	
	public BusDualReferral infoByCardId(String cardId, String hosId) {
		return busDualReferralDao.infoByCardId(cardId, hosId);
	}


	@Override
	@Transactional(rollbackFor=Exception.class)
	public int updateByPrimaryKeySelective(BusDualReferral busDualReferral) {
		// TODO Auto-generated method stub
		try{
			if("2".equals(busDualReferral.getRefStatus())){
				BusDualReferral referral= busDualReferralDao.selectByPrimaryKey(busDualReferral.getId());
				//状态2插入转诊记录
				if( 0 == dualReferralInfoService.detailInsert(referral)){
					throw new ParameterException("保存转诊信息失败");
				}
			}

			if( 0 == busDualReferralDao.updateByPrimaryKeySelective(busDualReferral)){
				throw new ParameterException("修改预约信息失败");
			}
			return 1;
		}catch (Exception e){
			e.printStackTrace();
			return 0;
		}


	}


	@Override
	public List<Map> referralReport(String year, String refType, String hosId) {
		// TODO Auto-generated method stub
		return busDualReferralDao.referralReport(year, refType, hosId);
	}


	@Override
	public List<Map> distinctYear() {
		// TODO Auto-generated method stub
		return busDualReferralDao.distinctYear();
	}

}
