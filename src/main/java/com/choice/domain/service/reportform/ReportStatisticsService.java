package com.choice.domain.service.reportform;

import java.text.ParseException;
import java.util.List;

import com.choice.domain.entity.reportform.ReportStatistics;

public interface ReportStatisticsService {
	
	void insertReportStatistics(ReportStatistics reportStatistics);
	
	//根据signingTime，subordinateId，level更新其他
	void updateReportStatisticsSelective(ReportStatistics reportStatistics);
	
	//根据signingTime，subordinateId，level展现该年的所有月份信息
	List<ReportStatistics> displayReportOfYear(ReportStatistics reportStatistics)throws ParseException;
	
	public ReportStatistics getOneReportStatistics(ReportStatistics reportStatistics,boolean isMonth) throws ParseException;
	
	//根据subordinateId，level展现拥有合同纪录的所有年份
	List<Integer> getTotalYearsDistinct(ReportStatistics reportStatistics);
}
