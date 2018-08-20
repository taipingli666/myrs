package com.choice.domain.service.external.impl;

import com.choice.domain.entity.external.PatientInfo;
import com.choice.domain.repository.external.PatientInfoDao;
import com.choice.domain.service.external.PatientInfoService;
import com.choice.sign.util.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 患者信息
 * Created by administer on 2018-01-29.
 * @author lingli
 */
@Service
public class PatientInfoServiceImpl implements PatientInfoService {

    @Autowired
    private PatientInfoDao patientInfoDao;

    /**
     * 新增患者信息
     * @param patientInfo
     * @return
     */
    @Override
    public int insertSelective(PatientInfo patientInfo) {
        return patientInfoDao.insertSelective(patientInfo);
    }

    /**
     *根据id查询患者信息
     * @param id
     * @return
     */
    @Override
    public PatientInfo selectByPrimaryKey(String id) {
        return null;
    }

    /**
     * 根据ID更新患者信息
     * @param patientInfo
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(PatientInfo patientInfo) {
        return 0;
    }

    /**
     * 根据身份证查询
     * @param patientIdCard
     * @return
     */
    @Override
    public PatientInfo selectByPatientIdCard(String patientIdCard) {
        return patientInfoDao.selectByPatientIdCard(patientIdCard);
    }

    /**
     * 根据身份证更新
     * @param patientInfo
     * @return
     */
    @Override
    public int updateByPatientIdCard(PatientInfo patientInfo) {
        return patientInfoDao.updateByPatientIdCard(patientInfo);
    }

    @Override
    public Page<PatientInfo> patientInfoList(Integer pageNum, Integer size, String content) {
        com.github.pagehelper.Page startPage = PageHelper.startPage(pageNum, size);
        List<PatientInfo> list = patientInfoDao.patientInfoList(content);
        Long total = startPage.getTotal();
        return new Page(size, pageNum, total.intValue(), list);
    }
}
