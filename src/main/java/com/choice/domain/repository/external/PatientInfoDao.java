package com.choice.domain.repository.external;

import com.choice.domain.entity.external.PatientInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lingli
 */
public interface PatientInfoDao {

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入
     * @param patientInfo
     * @return
     */
    int insert(PatientInfo patientInfo);

    /**
     * 选择插入
     * @param patientInfo
     * @return
     */
    int insertSelective(PatientInfo patientInfo);

    /**
     * 更具主键查询
     * @param id
     * @return
     */
    PatientInfo selectByPrimaryKey(String id);

    /**
     * 更具主键选择更新
     * @param patientInfo
     * @return
     */
    int updateByPrimaryKeySelective(PatientInfo patientInfo);

    /**
     * 更具主键更新
     * @param patientInfo
     * @return
     */
    int updateByPrimaryKey(PatientInfo patientInfo);

    /**
     * 根据身份证查询
     * @param patientIdCard
     * @return
     */
    PatientInfo selectByPatientIdCard(String patientIdCard);

    /**
     * 根据身份证更新
     * @param patientIdCard
     * @return
     */
    int updateByPatientIdCard(PatientInfo patientIdCard);

    //病人列表
    List<PatientInfo> patientInfoList(@Param("content") String content);
}