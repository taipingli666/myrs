package com.choice.domain.service.common.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.choice.domain.entity.common.CommonScheduling;
import com.choice.domain.repository.common.CommonSchedulingDao;
import com.choice.domain.service.common.CommonSchedulingService;

/**
 * Created by duhuo on 2017/7/24.
 */
@Service
public class CommonSchedulingServiceImpl implements CommonSchedulingService {
    @Resource
    private CommonSchedulingDao commonSchedulingMapper;
   
    public int deleteByPrimaryKey(Long liushuihao) {
        return 0;
    }

   
    public int insert(CommonScheduling record) {
        return 0;
    }

  
    public int insertSelective(CommonScheduling record) {
        return 0;
    }

   
    public CommonScheduling selectByPrimaryKey(Long liushuihao) {
        return null;
    }

  
    public int updateByPrimaryKeySelective(CommonScheduling record) {
        return 0;
    }

 
    public int updateByPrimaryKey(CommonScheduling record) {
        return 0;
    }

   
   
    public List getPaiBan(CommonScheduling scheduling) {
        List list = commonSchedulingMapper.getPaiBan(scheduling);
        return list;
    }

 
   
    public List getPaiBanByAfterDay(CommonScheduling scheduling) {
        return commonSchedulingMapper.getPaiBanByAfterDay(scheduling);
    }

  
    public List doctorList(CommonScheduling scheduling) {

        return null;
    }

   
    
    public CommonScheduling getPaiBanById(CommonScheduling commonSchedulin) {
        return commonSchedulingMapper.getPaiBanById(commonSchedulin);
    }
}
