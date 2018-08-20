package com.choice.domain.repository.external;

import com.choice.domain.entity.external.DualReferralReport;

import java.util.Date;
import java.util.List;

/**
 * 转诊报表
 * @author lingli
 */
public interface DualReferralReportDao {

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(DualReferralReport record);

    /**
     * 选择插入
     * @param record
     * @return
     */
    int insertSelective(DualReferralReport record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    DualReferralReport selectByPrimaryKey(Long id);

    /**
     *根据主键选择更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(DualReferralReport record);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(DualReferralReport record);

    /**
     * 获取父节点编码字符串
     * @param areaCode
     * @return
     */
    String getParentListString(String areaCode);

    /**
     * 获取当天数据
     * @param record
     * @return
     */
    DualReferralReport getNowDateData(DualReferralReport record);

    /**
     * 更新转出
     * @param record
     * @return
     */
    int updateReferralOut(DualReferralReport record);

    /**
     * 更新转入
     * @param record
     * @return
     */
    int updateReferralIn(DualReferralReport record);

    /**
     * 查询年度报表数据
     * @param record
     * @return
     */
    List<DualReferralReport> selectYearReportData(DualReferralReport record);

    /**
     * 根据日期删除
     * @param referralDate
     * @return
     */
    int deleteByDate(Date referralDate);

}