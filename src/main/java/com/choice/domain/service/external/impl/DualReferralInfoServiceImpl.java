package com.choice.domain.service.external.impl;

import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.external.DualReferralInfo;
import com.choice.domain.entity.external.RegisterInfo;
import com.choice.domain.entity.referral.BusDualReferral;
import com.choice.domain.repository.external.DualReferralInfoDao;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.external.DualReferralInfoService;
import com.choice.domain.service.external.DualReferralReportService;
import com.choice.sign.exception.ParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 转诊信息
 * Created by administer on 2018-01-30.
 * @author lingli
 */
@Service
public class DualReferralInfoServiceImpl implements DualReferralInfoService {

    @Autowired
    private DualReferralInfoDao dualReferralInfoDao;

    @Autowired
    private CommonHospitalService commonHospitalService;

    @Autowired
    private DualReferralReportService dualReferralReportService;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(DualReferralInfo record) {
        return 0;
    }

    /**
     * 选择插入
     * @param record
     * @return
     */
    @Override
    public int insertSelective(DualReferralInfo record) {
        return dualReferralInfoDao.insertSelective(record);
    }

    @Override
    public DualReferralInfo selectByPrimaryKey(Long id) {
        return null;
    }

    /**
     * 根据主键选择更新
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(DualReferralInfo record) {
        return dualReferralInfoDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DualReferralInfo record) {
        return 0;
    }

    /**
     * 预约挂号插入
     * @param registerInfo
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int registerInsert(RegisterInfo registerInfo) {

        try{
            /*获取机构信息*/
            CommonHospital orgFrom = commonHospitalService.getById(Integer.parseInt(registerInfo.getOperHosCode()));
            CommonHospital orgTo = commonHospitalService.getById(Integer.parseInt(registerInfo.getHosCode()));
            /**/
            /*保存转诊数据*/
            DualReferralInfo dualReferralInfo = new DualReferralInfo();
            dualReferralInfo.setCmmitDate(registerInfo.getRegisterTime());
            dualReferralInfo.setPatName(registerInfo.getPatientName());
            dualReferralInfo.setSex(registerInfo.getPatientGender());
            dualReferralInfo.setAge(registerInfo.getPatientAge());
            dualReferralInfo.setTel(registerInfo.getPatientPhone());
            dualReferralInfo.setCardType(registerInfo.getPatientMediumType());
            dualReferralInfo.setCardId(registerInfo.getPatientMediumCode());
            dualReferralInfo.setOrgIdFrom(registerInfo.getOperHosCode());
            dualReferralInfo.setOrgNameFrom(orgFrom.getHosName());
            dualReferralInfo.setOrgIdTo(registerInfo.getHosCode());
            dualReferralInfo.setOrgNameTo(orgTo.getHosName());
            dualReferralInfo.setRefType("1");
            dualReferralInfo.setRefStatus("1");
            dualReferralInfo.setHealthId("");
            dualReferralInfo.setIcd10(registerInfo.getIcd10Code());
            dualReferralInfo.setDiag(registerInfo.getIcd10Name());
            dualReferralInfo.setRefTypeId(registerInfo.getId());

            if( 0 == dualReferralInfoDao.insertSelective(dualReferralInfo)){
                throw new ParameterException("保存信息失败");
            }


            dualReferralReportService.insertReportCount(orgFrom,orgTo,registerInfo.getRegisterTime());
            return 1;
        }catch ( Exception e){
            e.printStackTrace();
            return 0;
        }


    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int detailInsert(BusDualReferral busDualReferral) {
        try{
            /*获取机构信息*/
            CommonHospital orgFrom = commonHospitalService.getById(Integer.parseInt(busDualReferral.getOrgIdFrom()));
            CommonHospital orgTo = commonHospitalService.getById(Integer.parseInt(busDualReferral.getOrgIdTo()));
            /*保存转诊数据*/
            DualReferralInfo dualReferralInfo = new DualReferralInfo();
            dualReferralInfo.setCmmitDate(busDualReferral.getCmmitDate());
            dualReferralInfo.setPatName(busDualReferral.getPatName());
            dualReferralInfo.setSex(busDualReferral.getSex());
            dualReferralInfo.setAge(busDualReferral.getAge());
            dualReferralInfo.setTel(busDualReferral.getTel());
            dualReferralInfo.setCardType(busDualReferral.getCardType());
            dualReferralInfo.setCardId(busDualReferral.getCardId());
            dualReferralInfo.setOrgIdFrom(busDualReferral.getOrgIdFrom());
            dualReferralInfo.setOrgNameFrom(busDualReferral.getOrgNameFrom());
            dualReferralInfo.setOrgIdTo(busDualReferral.getOrgIdTo());
            dualReferralInfo.setOrgNameTo(busDualReferral.getOrgNameTo());
            dualReferralInfo.setRefType("1");
            dualReferralInfo.setRefStatus("1");
            dualReferralInfo.setHealthId("");
            dualReferralInfo.setIcd10(busDualReferral.getIcd10());
            dualReferralInfo.setDiag(busDualReferral.getDiag());
            dualReferralInfo.setRefTypeId(String.valueOf(busDualReferral.getId()));


            if( 0 == dualReferralInfoDao.insertSelective(dualReferralInfo)){
                throw new ParameterException("保存信息失败");
            }
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            dualReferralReportService.insertReportCount(orgFrom,orgTo,df.parse(busDualReferral.getOrderDate()));
            return 1;
        }catch ( Exception e){
            e.printStackTrace();
            return 0;
        }


    }

    /**
     * 根据日期统计转出
     * @param referralDate
     * @return
     */
    @Override
    public List<Map<String, Object>> selectFromCountByDate(Date referralDate) {
        return dualReferralInfoDao.selectFromCountByDate(referralDate);
    }

    /**
     * 根据日期统计转入
     * @param referralDate
     * @return
     */
    @Override
    public List<Map<String, Object>> selectToCountByDate(Date referralDate) {
        return dualReferralInfoDao.selectToCountByDate(referralDate);
    }

    @Override
    public List<Map<String, Object>> dualOut(String endTime, String startTime, String orgIdFrom) {
        return dualReferralInfoDao.dualOut(endTime, startTime, orgIdFrom);
    }

    @Override
    public List<Map<String, Object>> dualIn(String endTime, String startTime, String orgIdTo) {
        return dualReferralInfoDao.dualIn(endTime, startTime, orgIdTo);
    }

}
