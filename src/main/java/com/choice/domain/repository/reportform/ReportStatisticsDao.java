package com.choice.domain.repository.reportform;

import java.util.List;

import com.choice.domain.entity.reportform.ReportStatistics;

public interface ReportStatisticsDao {
	
	void insertReportStatistics(ReportStatistics reportStatistics);
	
	void updateReportStatisticsSelective(ReportStatistics reportStatistics);
	
	List<ReportStatistics> displayReportOfYear(ReportStatistics reportStatistics);
	
	List<Integer> getTotalYearsDistinct(ReportStatistics reportStatistics);
}
