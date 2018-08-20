package com.choice.domain.service.external;

import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.external.DualReferralReport;

import java.util.Date;
import java.util.List;

/**
 * 转诊报表
 * Created by administer on 2018-02-01.
 * @author lingli
 */
public interface DualReferralReportService {

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
     * 插入转诊统计
     * @param orgFrom 转出机构信息
     * @param orgTo 转入机构信息
     * @param referralDate 转诊日期
     * @return boolean
     */
    boolean insertReportCount(CommonHospital orgFrom,CommonHospital orgTo,Date referralDate);

    /**
     * 根据日期统计转诊
     * @param referralDate
     * @return
     */
    boolean countReferralByDate(Date referralDate);
    /**
     * 查询年度报表数据
     * @param record
     * @return
     */
    List<DualReferralReport> selectYearReportData(DualReferralReport record);
}
