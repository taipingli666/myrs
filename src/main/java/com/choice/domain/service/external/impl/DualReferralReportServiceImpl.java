package com.choice.domain.service.external.impl;

import com.choice.domain.entity.common.CommonArea;
import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.external.DualReferralReport;
import com.choice.domain.repository.external.DualReferralReportDao;
import com.choice.domain.service.common.CommonAreaService;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.external.DualReferralInfoService;
import com.choice.domain.service.external.DualReferralReportService;
import com.choice.sign.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 转诊报表
 * Created by administer on 2018-02-01.
 * @author lingli
 */
@Service
public class DualReferralReportServiceImpl implements DualReferralReportService {

    @Autowired
    private DualReferralReportDao dualReferralReportDao;

    @Autowired
    private CommonAreaService commonAreaService;

    @Autowired
    private DualReferralInfoService dualReferralInfoService;

    @Autowired
    private CommonHospitalService commonHospitalService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(DualReferralReport record) {
        return 0;
    }

    /**
     * 选择插入
     * @param record
     * @return
     */
    @Override
    public int insertSelective(DualReferralReport record) {
        return dualReferralReportDao.insertSelective(record);
    }

    @Override
    public DualReferralReport selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(DualReferralReport record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(DualReferralReport record) {
        return 0;
    }

    /**
     * 获取父节点编码字符串
     * @param areaCode
     * @return
     */
    @Override
    public String getParentListString(String areaCode) {
        return dualReferralReportDao.getParentListString(areaCode);
    }

    /**
     * 插入转诊统计
     * @param orgFrom 转出机构信息
     * @param orgTo 转入机构信息
     * @param referralDate 转诊日期
     * @return boolean
     */
    @Override
    public boolean insertReportCount(CommonHospital orgFrom, CommonHospital orgTo, Date referralDate) {
        boolean from = setReportCount(orgFrom,referralDate,"from",Long.parseLong("1"));
        boolean to = setReportCount(orgTo,referralDate,"to",Long.parseLong("1"));
        if( from && to){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 根据日期统计转诊
     * @param referralDate
     * @return
     */
    @Override
    public boolean countReferralByDate(Date referralDate) {
        try{
            /*清除这天统计数*/
            dualReferralReportDao.deleteByDate(referralDate);
            /*根据日期统计机构转出数量*/
            List<Map<String,Object>> fromList = dualReferralInfoService.selectFromCountByDate(referralDate);

            for(int i = 0 ; i < fromList.size(); i++){
                int aa = Integer.parseInt(fromList.get(i).get("org_id_from").toString());
                CommonHospital orgFrom = commonHospitalService.getById(aa);
                setReportCount(orgFrom,referralDate,"from",Long.parseLong(fromList.get(i).get("referraCount").toString()));
            }
            /*根据日期统计机构转入数量*/
            List<Map<String,Object>> toList = dualReferralInfoService.selectToCountByDate(referralDate);
            for(int j = 0 ; j < toList.size(); j++){
                CommonHospital orgTo = commonHospitalService.getById(Integer.parseInt(toList.get(j).get("org_id_to").toString()));
                setReportCount(orgTo,referralDate,"to",Long.parseLong(toList.get(j).get("referraCount").toString()));
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询年度报表数据
     * @param record
     * @return
     */
    @Override
    public List<DualReferralReport> selectYearReportData(DualReferralReport record) {
        return dualReferralReportDao.selectYearReportData(record);
    }

    /**
     * 插入转诊统计公共处理
     * @param orgInfo
     * @param referralDate
     * @param countType
     * @return
     */
    public boolean setReportCount(CommonHospital orgInfo, Date referralDate,String countType ,Long count ){
        try {

            /*机构数据处理*/
            DualReferralReport dualReferralReport = new DualReferralReport();
            dualReferralReport.setAreaCode(orgInfo.getHosId().toString());
            dualReferralReport.setCountDate(DateUtil.stringToDate(
                    DateUtil.dateToString(referralDate,"yyyy-MM-dd"),"yyyy-MM-dd"));
            /*查询有无数据*/
            if(dualReferralReportDao.getNowDateData(dualReferralReport) == null){
                dualReferralReport.setAreaName(orgInfo.getHosName());
                dualReferralReport.setYear(DateUtil.dateToString(referralDate,"yyyy"));
                dualReferralReport.setParentCode(orgInfo.getAreaCode());
                dualReferralReportDao.insertSelective(dualReferralReport);
            }

            DualReferralReport dualReferralReport1 = dualReferralReportDao.getNowDateData(dualReferralReport);
            if(countType.equals("from")){
                dualReferralReport.setReferralOutQuantity(count);
                dualReferralReportDao.updateReferralOut(dualReferralReport);
            }else {
                dualReferralReport.setReferralInQuantity(count);
                dualReferralReportDao.updateReferralIn(dualReferralReport);
            }
            dualReferralReport = new DualReferralReport();
            dualReferralReport.setCountDate(DateUtil.stringToDate(
                    DateUtil.dateToString(referralDate,"yyyy-MM-dd"),"yyyy-MM-dd"));

            /*区域数据处理*/
            /*获取层级数据*/
            String parentListString= dualReferralReportDao.getParentListString(orgInfo.getAreaCode());
            List<String> parentList = Arrays.asList(parentListString.split(","));
            for(int i = 0 ; i < parentList.size() ; i++ ){
                if(parentList.get(i).equals("0")){
                    continue;
                }
                dualReferralReport.setAreaCode(parentList.get(i));
                /*查询有无数据*/
                if(dualReferralReportDao.getNowDateData(dualReferralReport) == null){
                    /*查询区域信息*/
                    CommonArea commonArea = commonAreaService.selectByCode(parentList.get(i));
                    dualReferralReport.setAreaName(commonArea.getAreaName());
                    dualReferralReport.setYear(DateUtil.dateToString(referralDate,"yyyy"));
                    dualReferralReport.setParentCode(commonArea.getParentCode());
                    dualReferralReportDao.insertSelective(dualReferralReport);
                }

                if(countType.equals("from")){
                    dualReferralReport.setReferralOutQuantity(count);
                    dualReferralReportDao.updateReferralOut(dualReferralReport);
                }else {
                    dualReferralReport.setReferralInQuantity(count);
                    dualReferralReportDao.updateReferralIn(dualReferralReport);
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
