package com.choice.domain.service.contract.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.choice.domain.entity.contract.BusinessContract;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.repository.contract.BusinessContractDao;
import com.choice.domain.service.contract.ContMangerService;
import com.choice.sign.util.Page;

@Service("contMangerService")
public class ContMangerServiceImpl implements ContMangerService {
	
	@Resource
	private BusinessContractDao contractDao;
	
	public Page getPage(int currentPage, BusinessContract contract) {
		int pageSize = 10;
		Integer total = contractDao.getTotalNumber(contract);
		int pageIndex = Page.calPageIndex(currentPage, pageSize);
		List<Map> list = contractDao.getPageInfo(contract, pageIndex, pageSize);
		return new Page<Map>(pageSize, currentPage, total, list);
	}

	public List<Map> ExportContents(BusinessContract contract) {
		Integer total = contractDao.getTotalNumber(contract);
		List<Map> list = contractDao.getPageInfo(contract, 0, total);
		return list;
	}

	public BusinessContract selectByPrimaryKey(int contractId) {
		return contractDao.selectByPrimaryKey(contractId);
	}

	public void updateByPrimaryKeySelective(BusinessContract contract) {
		contractDao.updateByPrimaryKeySelective(contract);
	}

	public Map<String, Object> contractSigned(BusinessContract contract) {
		Map<String, Object> map = new HashMap<String, Object>();
		String card = contract.getCard();
		if(card!=null&&!"".equals(card)){
			BusinessContract haveContract = contractDao.getContractByUK(contract);
			if(haveContract!=null){
				map.put("status", "1");
				try {
					map.put("message", "居民已存在 "+haveContract.getSignYear()+"年度的签约信息！"
							+ "签约时间："+haveContract.getAddTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return map;
			}
		}
		contract.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		contractDao.InsertBusinessContract(contract);
		map.put("status", "0");
		return map;
	}

	public List<String> getAllSignyear() {
		return contractDao.getAllSignyear();
	}

	public List<BusinessContract> selectByAddTime(BusinessContract contract, List<Integer> hosIds) {
		return contractDao.selectByAddTime(contract,hosIds);
	}

	/**
	 * 获取当前登录人所属团队的签约病人
	 */
	public Page getPageByTeamId(int currentPage, BusinessContract contract, Integer teamId) {
		int pageSize = 10;
		Integer total = contractDao.getTotalNumberByTeamId(contract,teamId);
		int pageIndex = Page.calPageIndex(currentPage, pageSize);
		List<Map> list = contractDao.getPageInfoByTeamId(contract,teamId, pageIndex, pageSize);
		return new Page<Map>(pageSize, currentPage, total, list);
	}
	
	/**
	 * 根据id合同当前登录人续约
	 */
	public Map<String,Object> renew (Integer contractId,ChannelUser channelUser){
		Map<String,Object> map = new HashMap<String,Object>();
		
		BusinessContract contract = contractDao.selectByPrimaryKey(contractId);
		Integer signYear = Integer.parseInt(contract.getSignYear());
		//获取当前时间
		String addTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		//设置添加时间
		contract.setAddTime(addTime);
		//设置签约年份
		Integer renewYear = signYear+1;
		contract.setSignYear(renewYear.toString());
		//设置服务开始时间
		contract.setStartTime(renewYear+"-01-01");
		//设置服务结束时间
		contract.setEndTime(renewYear+"-12-31");
		//设置status为3(续约)
		contract.setStatus("3");
		//获取当前登录人id
		int id = channelUser.getUserId();
		//设置添加人为当前登录人
		contract.setAddPerson(String.valueOf(id));
		//设置此合同为续约合同(0代表不是续约合同,1代表续约合同)
		contract.setIsRenew("1");
		//设置contractId
		String contractNo = String.format("%08d", (int) (Math.random() * 100000000));
		contract.setContractId(contractNo);
		//判断是否已有该病人下一年的续约记录
		int renewRecord = contractDao.getRenewRecordCount(contract.getCard(), contract.getSignYear());
		if(renewRecord>0) {
			map.put("success", false);
			map.put("message", "此病人已有"+renewYear+"年的续约记录,不能重复续约!");
			return map;
		}
		//新增一条记录
		int affect = contractDao.InsertBusinessContract(contract);
		if(affect>0) {
			map.put("success", true);
		}else {
			map.put("success", false);
			map.put("message", "续约失败");
		}
		
		return map; 
	}
	
}
