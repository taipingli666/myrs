package com.choice.domain.repository.external;

import com.choice.domain.entity.external.DualReferralInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 转诊信息
 * @author lingli
 */
public interface DualReferralInfoDao {

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
    int insert(DualReferralInfo record);

    /**
     * 选择插入
     * @param record
     * @return
     */
    int insertSelective(DualReferralInfo record);

    /**
     *根据主键查询
     * @param id
     * @return
     */
    DualReferralInfo selectByPrimaryKey(Long id);

    /**
     * 根据主键选择更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(DualReferralInfo record);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(DualReferralInfo record);

    /**
     * 根据日期统计转出
     * @param referralDate
     * @return
     */
    List<Map<String,Object>> selectFromCountByDate(Date referralDate);

    /**
     * 根据日期统计转入
     * @param referralDate
     * @return
     */
    List<Map<String,Object>> selectToCountByDate(Date referralDate);

    /**
     * 转入
     * @param endTime
     * @param startTime
     * @param orgIdTo
     * @return
     */
    List<Map<String, Object>> dualIn( String endTime, String startTime, String orgIdTo);

    /**
     * 转出
     * @param endTime
     * @param startTime
     * @param orgIdFrom
     * @return
     */
    List<Map<String, Object>> dualOut(String endTime, String startTime, String orgIdFrom);
}