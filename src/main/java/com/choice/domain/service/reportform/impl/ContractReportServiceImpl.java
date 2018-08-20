package com.choice.domain.service.reportform.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.choice.domain.entity.reportform.ContractReport;
import com.choice.domain.repository.reportform.ContractReportDao;
import com.choice.domain.service.reportform.ContractReportService;

@Service
public class ContractReportServiceImpl implements ContractReportService{

	@Autowired
	ContractReportDao contractReportDao;
	
	@Override
	public List<ContractReport> getListBySearchValue(ContractReport contractReport) {
		return contractReportDao.getListBySearchValue(contractReport);
	}

	@Override
	public List<ContractReport> signingInfo(String hosId) {
		// TODO Auto-generated method stub
		return contractReportDao.signingInfo(hosId);
	}

	@Override
	public List<ContractReport> renewInfo(String hosId) {
		// TODO Auto-generated method stub
		return contractReportDao.renewInfo(hosId);
	}

	@Override
	public List<ContractReport> focusGroupsInfo(String hosId) {
		// TODO Auto-generated method stub
		return contractReportDao.focusGroupsInfo(hosId);
	}

	@Override
	public ContractReport contractReportInfoByYear(String areaCode, String year) {
		// TODO Auto-generated method stub
		return contractReportDao.contractReportInfoByYear(areaCode, year);
	}

	@Override
	public int insert(ContractReport contractReport) {
		// TODO Auto-generated method stub
		return contractReportDao.insert(contractReport);
	}

	@Override
	public int update(ContractReport contractReport) {
		// TODO Auto-generated method stub
		return contractReportDao.update(contractReport);
	}

	@Override
	public void delete() {
		contractReportDao.delete();
	}

	@Override
	public List<String> distinctYear() {
		return contractReportDao.distinctYear();
	}


}
