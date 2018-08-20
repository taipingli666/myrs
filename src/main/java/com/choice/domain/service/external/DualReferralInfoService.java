package com.choice.domain.service.external;

import com.choice.domain.entity.external.DualReferralInfo;
import com.choice.domain.entity.external.RegisterInfo;
import com.choice.domain.entity.referral.BusDualReferral;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 转诊信息
 * Created by administer on 2018-01-30.
 * @author lingli
 */
public interface DualReferralInfoService {


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
     * 预约挂号插入
     * @param registerInfo
     * @return
     */
    int registerInsert(RegisterInfo registerInfo);

    /**
     * 预约住院插入
     * @param busDualReferral
     * @return
     */
    int detailInsert(BusDualReferral busDualReferral);

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
     * 转出
     * @param endTime
     * @param startTime
     * @param orgIdFrom
     * @return
     */
    List<Map<String, Object>> dualOut(String endTime, String startTime, String orgIdFrom);

    /**
     * 转入
     * @param endTime
     * @param startTime
     * @param orgIdTo
     * @return
     */
    List<Map<String, Object>> dualIn(String endTime, String startTime, String orgIdTo);
}
