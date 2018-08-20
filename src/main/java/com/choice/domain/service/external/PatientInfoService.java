package com.choice.domain.service.external;

import com.choice.domain.entity.external.PatientInfo;
import com.choice.sign.util.Page;

/**
 * 患者信息
 * Created by administer on 2018-01-29.
 * @author lingli
 */
public interface PatientInfoService {

    /**
     * 新增患者信息
     * @param record
     * @return
     */
    int insertSelective(PatientInfo record);

    /**
     * 根据id查询患者信息
     * @param id
     * @return
     */
    PatientInfo selectByPrimaryKey(String id);

    /**
     * 根据ID更新患者信息
     * @param patientInfo
     * @return
     */
    int updateByPrimaryKeySelective(PatientInfo patientInfo);

    /**
     * 根据身份证查询
     * @param patientIdCard
     * @return
     */
    PatientInfo selectByPatientIdCard(String patientIdCard);


    /**
     * 根据身份证更新
     * @param patientInfo
     * @return
     */
    int updateByPatientIdCard(PatientInfo patientInfo);

    /**
     * 患者列表
     * @return
     */
    Page<PatientInfo> patientInfoList(Integer pageNum, Integer size, String content);
}
