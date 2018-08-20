package com.choice.sign.web.controller.reportform;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choice.domain.entity.common.CommonArea;
import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.reportform.ContractReport;
import com.choice.domain.service.common.CommonAreaService;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.common.CommonPopulationService;
import com.choice.domain.service.reportform.ContractReportService;
import org.springframework.web.servlet.ModelAndView;

/**
* @author litp
* 2018年2月2日 下午1:23:57
* 签约报表数据统计
*/
@Controller
@RequestMapping("/contractReport")
public class ContractReportController {
	
	@Resource
	private ContractReportService contractReportService;
	
	@Resource
	private CommonHospitalService commonHospitalService;
	
	@Resource
	private CommonAreaService commonAreaService;
	
	@Resource
	private CommonPopulationService commonPopulationService;

	@Scheduled(cron = "0 0 3 * * ? ")
	@RequestMapping("/tableData")
	@ResponseBody
	public void contractReportTableData() {
		//清空表
		contractReportService.delete();
		//所有医院
		List<CommonHospital> commonHospitals = commonHospitalService.getAllHospital();
		//通过医院id统计签约、续约、重点人群
		for(CommonHospital commonHospital : commonHospitals) {
			//签约
			List<ContractReport> signingInfos = contractReportService.signingInfo(commonHospital.getHosId().toString());
			//续约
			List<ContractReport> renewInfos = contractReportService.renewInfo(commonHospital.getHosId().toString());
			//重点人群
			List<ContractReport> focusGroupsInfos = contractReportService.focusGroupsInfo(commonHospital.getHosId().toString());
			//遍历签约信息
			forMethod(signingInfos);
			//遍历续约统计信息
			forMethod(renewInfos);
			//遍历重点人群统计信息
			forMethod(focusGroupsInfos);
			}
			System.out.println("---end----");
	}

	//循环签约，续约，重点人群签约list
	private void forMethod(List<ContractReport> contractReports) {
		for(ContractReport contractReport : contractReports) {
			//根据年份和areacode判断是否有这条数据
			ContractReport cr = contractReportService.contractReportInfoByYear(contractReport.getAreaCode(), contractReport.getYear());
			switch (contractReport.getLevel()) {
				case 2:
					//市级医院 判断当前市下是否有这条数据
					if (cr == null) {
						//给当前市区域加一条
						contractReportService.insert(contractReport);
					}else {
						updateNumAndRate(cr, contractReport);
						contractReportService.update(cr);
					}

					break;
				case 3:
					//区级医院 判断当前区下是否有这条数据
					if (cr == null) {
						//区加一条
						contractReportService.insert(contractReport);
						//市数据
						ContractReport scontractReport = contractReportService.contractReportInfoByYear(contractReport.getParentCode(), contractReport.getYear());
						//如果空，上级市加一条
						if (scontractReport == null) {
							contractReport.setAreaCode(contractReport.getParentCode());
							CommonArea commonArea = commonAreaService.selectByCode(contractReport.getParentCode());
							contractReport.setParentCode(commonArea.getParentCode());
							int pnum = commonPopulationService.pnumByAreaCode(contractReport.getAreaCode());
							contractReport.setPeopleNumber(pnum);
							contractReport.setAreaName(commonArea.getAreaName());
							setNumAndRate(contractReport);
							contractReport.setLevel(contractReport.getLevel() - 1);
							contractReportService.insert(contractReport);
						}else {
							//修改市
							updateNumAndRate(scontractReport, contractReport);
							contractReportService.update(scontractReport);
						}
					} else {
						//修改区
						updateNumAndRate(cr, contractReport);
						contractReportService.update(cr);
						//假设下级有数据上级就有数据
						//修改上级市数据
						ContractReport scontractReport = contractReportService.contractReportInfoByYear(contractReport.getParentCode(), contractReport.getYear());
						//修改市
						updateNumAndRate(scontractReport, contractReport);
						contractReportService.update(scontractReport);
					}
					break;
				case 4:
					//街道医院 判断当前街道下是否有这条数据
					if (cr == null) {
						//街道加一条
						contractReportService.insert(contractReport);
						//判断上级区数据
						ContractReport qcontractReport = contractReportService.contractReportInfoByYear(contractReport.getParentCode(), contractReport.getYear());
						//如果空，上级区加一条
						if (qcontractReport == null) {
							contractReport.setAreaCode(contractReport.getParentCode());
							CommonArea commonArea = commonAreaService.selectByCode(contractReport.getAreaCode());
							contractReport.setAreaName(commonArea.getAreaName());
							contractReport.setParentCode(commonArea.getParentCode());
							int pnum = commonPopulationService.pnumByAreaCode(contractReport.getAreaCode());
							contractReport.setPeopleNumber(pnum);
							setNumAndRate(contractReport);
							contractReport.setLevel(contractReport.getLevel() - 1);
							contractReportService.insert(contractReport);
							//判断上级市是否有数据
							ContractReport scontractReport = contractReportService.contractReportInfoByYear(contractReport.getParentCode(), contractReport.getYear());
							if (scontractReport == null) {
								//市添加一条
								contractReport.setAreaCode(contractReport.getParentCode());
								commonArea = commonAreaService.selectByCode(contractReport.getAreaCode());
								contractReport.setParentCode(commonArea.getParentCode());
								contractReport.setAreaName(commonArea.getAreaName());
								pnum = commonPopulationService.pnumByAreaCode(contractReport.getAreaCode());
								contractReport.setPeopleNumber(pnum);
								contractReport.setAreaName(commonArea.getAreaName());
								setNumAndRate(contractReport);
								contractReport.setLevel(contractReport.getLevel() - 1);
								contractReportService.insert(contractReport);
							}else {
								//修改市
								updateNumAndRate(scontractReport, contractReport);
								contractReportService.update(scontractReport);
							}
						}else {
							//修改区
							updateNumAndRate(qcontractReport, contractReport);
							contractReportService.update(qcontractReport);
							//修改市
							ContractReport scontractReport = contractReportService.contractReportInfoByYear(qcontractReport.getParentCode(), contractReport.getYear());
							updateNumAndRate(scontractReport, contractReport);
							contractReportService.update(scontractReport);
						}
					}else {
						//修改街道
						updateNumAndRate(cr, contractReport);
						contractReportService.update(cr);
						//修改区
						ContractReport qcontractReport = contractReportService.contractReportInfoByYear(contractReport.getParentCode(), contractReport.getYear());
						updateNumAndRate(qcontractReport, contractReport);
						contractReportService.update(qcontractReport);
						//修改市
						CommonArea commonArea = commonAreaService.selectByCode(qcontractReport.getParentCode());
						ContractReport scontractReport = contractReportService.contractReportInfoByYear(commonArea.getCode(), contractReport.getYear());
						updateNumAndRate(scontractReport, contractReport);
						contractReportService.update(scontractReport);
					}
					break;
				case 5:
					//社区医院 判断当前社区下是否有这条数据
					if (cr == null) {
						//社区信息加一条
						contractReportService.insert(contractReport);
						//判断街道
						ContractReport jdContractReport = contractReportService.contractReportInfoByYear(contractReport.getParentCode(),  contractReport.getYear());
						if (jdContractReport == null) {
							//街道加一条
							//上级街道信息
							CommonArea commonArea = commonAreaService.selectByCode(contractReport.getParentCode());
							contractReport.setAreaCode(commonArea.getCode());
							contractReport.setAreaName(commonArea.getAreaName());
							contractReport.setParentCode(commonArea.getParentCode());
							int pnum = commonPopulationService.pnumByAreaCode(commonArea.getCode());
							contractReport.setPeopleNumber(pnum);
							setNumAndRate(contractReport);
							contractReport.setLevel(contractReport.getLevel() - 1);
							contractReportService.insert(contractReport);
							//判断区
							commonArea = commonAreaService.selectByCode(contractReport.getParentCode());
							ContractReport qContractReport = contractReportService.contractReportInfoByYear(commonArea.getCode(), contractReport.getYear());
							if (qContractReport == null) {
								contractReport.setAreaCode(commonArea.getCode());
								contractReport.setAreaName(commonArea.getAreaName());
								contractReport.setParentCode(commonArea.getParentCode());
								pnum = commonPopulationService.pnumByAreaCode(commonArea.getCode());
								contractReport.setPeopleNumber(pnum);
								setNumAndRate(contractReport);
								contractReport.setLevel(contractReport.getLevel() - 1);
								contractReportService.insert(contractReport);
								//判断市
								commonArea = commonAreaService.selectByCode(contractReport.getParentCode());
								ContractReport sContractReport = contractReportService.contractReportInfoByYear(commonArea.getCode(), contractReport.getYear());
								if (sContractReport == null) {
									//市添加一条
									contractReport.setAreaCode(commonArea.getCode());
									contractReport.setAreaName(commonArea.getAreaName());
									contractReport.setParentCode(commonArea.getParentCode());
									pnum = commonPopulationService.pnumByAreaCode(commonArea.getCode());
									contractReport.setPeopleNumber(pnum);
									setNumAndRate(contractReport);
									contractReport.setLevel(contractReport.getLevel() - 1);
									contractReportService.insert(contractReport);
								}else {
									//修改市
									updateNumAndRate(sContractReport, contractReport);
									contractReportService.update(sContractReport);
								}
							} else {
								//修改区
								updateNumAndRate(qContractReport, contractReport);
								contractReportService.update(qContractReport);
								//修改市
								ContractReport sContractReport = contractReportService.contractReportInfoByYear(qContractReport.getParentCode(),  contractReport.getYear());
								updateNumAndRate(sContractReport, contractReport);
								contractReportService.update(sContractReport);
							}
						}else {
							//修改街道
							updateNumAndRate(jdContractReport, contractReport);
							contractReportService.update(jdContractReport);
							//修改区
							ContractReport qContractReport = contractReportService.contractReportInfoByYear(jdContractReport.getParentCode(),  contractReport.getYear());
							updateNumAndRate(qContractReport, contractReport);
							contractReportService.update(qContractReport);
							//修改市
							ContractReport sContractReport = contractReportService.contractReportInfoByYear(qContractReport.getParentCode(),  contractReport.getYear());
							updateNumAndRate(sContractReport, contractReport);
							contractReportService.update(sContractReport);
						}
					}else {
						//修改社区
						updateNumAndRate(cr, contractReport);
						contractReportService.update(cr);
						//修改街道
						ContractReport jdContractReport = contractReportService.contractReportInfoByYear(contractReport.getParentCode(),  contractReport.getYear());
						updateNumAndRate(jdContractReport, contractReport);
						contractReportService.update(jdContractReport);
						//修改区
						ContractReport qContractReport = contractReportService.contractReportInfoByYear(jdContractReport.getParentCode(),  contractReport.getYear());
						updateNumAndRate(qContractReport, contractReport);
						contractReportService.update(qContractReport);
						//修改市
						ContractReport sContractReport = contractReportService.contractReportInfoByYear(qContractReport.getParentCode(),  contractReport.getYear());
						updateNumAndRate(sContractReport, contractReport);
						contractReportService.update(sContractReport);
					}
					break;
				default:
					break;
			}
		}
	};

	public void updateNumAndRate(ContractReport scontractReport, ContractReport contractReport) {
		if (contractReport.getRenewNum() != 0) {
			scontractReport.setRenewNum(contractReport.getRenewNum() + scontractReport.getRenewNum());
		}
		if (contractReport.getRenewRate() != 0) {
			scontractReport.setRenewRate(scontractReport.getRenewNum() / (float)scontractReport.getPeopleNumber());
		}
		if (contractReport.getSigningNum() != 0) {
			scontractReport.setSigningNum(contractReport.getSigningNum() + scontractReport.getSigningNum());
		}
		if (contractReport.getSignatureRate() != 0) {
			scontractReport.setSignatureRate(scontractReport.getSigningNum() / (float)scontractReport.getPeopleNumber());
		}
		if (contractReport.getFocusGroupsNum() != 0) {
			scontractReport.setFocusGroupsNum(contractReport.getFocusGroupsNum() + scontractReport.getFocusGroupsNum());
		}
		if (contractReport.getFocusGroupsRate() != 0) {
			scontractReport.setFocusGroupsRate(scontractReport.getFocusGroupsNum() / (float)scontractReport.getPeopleNumber());
		}
	}

	public void setNumAndRate(ContractReport contractReport) {
		if (contractReport.getRenewNum() != 0) {
			contractReport.setRenewNum(contractReport.getRenewNum());
		}
		if (contractReport.getRenewRate() != 0) {
			contractReport.setRenewRate(contractReport.getRenewNum() / (float)contractReport.getPeopleNumber());
		}
		if (contractReport.getSigningNum() != 0) {
			contractReport.setSigningNum(contractReport.getSigningNum());
		}
		if (contractReport.getSignatureRate() != 0) {
			contractReport.setSignatureRate(contractReport.getSigningNum() / (float)contractReport.getPeopleNumber());
		}
		if (contractReport.getFocusGroupsNum() != 0) {
			contractReport.setFocusGroupsNum(contractReport.getFocusGroupsNum());
		}
		if (contractReport.getFocusGroupsRate() != 0) {
			contractReport.setFocusGroupsRate(contractReport.getFocusGroupsNum() / (float)contractReport.getPeopleNumber());
		}
	}
}
