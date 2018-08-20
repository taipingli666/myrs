package com.choice.domain.service.common.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.choice.domain.entity.common.CommonDept;
import com.choice.domain.repository.common.CommonDeptDao;
import com.choice.domain.service.common.CommonDeptService;
import com.choice.sign.util.Page;
import com.github.pagehelper.PageHelper;

/**
 * 科室的维护
 * Created by duhuo on 2017/8/11.
 */
@Service
public class CommonDeptServiceImpl implements CommonDeptService {
    @Resource
    private CommonDeptDao commonDeptMapper ;

   
    public int deleteByPrimaryKey(Long liushuihao) {
        return 0;
    }

    
    public int insert(CommonDept record) {
        return 0;
    }

    
    public int insertSelective(CommonDept record) {
        return 0;
    }

   
    public CommonDept selectByPrimaryKey(Long liushuihao) {
        return null;
    }

    
    public int updateByPrimaryKeySelective(CommonDept record) {
        return commonDeptMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(CommonDept record) {
        return 0;
    }


    /**
     * 批量更新
     * @param list
     * @return
     */
  
    public int updateKeshilb(List<CommonDept> list) {
        int count = 0;
        try{
            for(CommonDept commonDept : list){
                if(commonDept.getLiushuihao()!=null){
                    CommonDept commonDept1 = commonDeptMapper.selectByPrimaryKey(commonDept.getLiushuihao());
                    if(commonDept1!=null){
                        //说明存在,更新科室类别
                        int i = commonDeptMapper.updateKeshilb(commonDept);
                        if(i>0){
                            count++;
                        }
                    }
                }
            }
        }catch (Exception e){
            return count;
        }
        return count;
    }

    
    public List<CommonDept> getConsultationDeptByJiGouBH(CommonDept commonDept) {
        return commonDeptMapper.getConsultationDeptByJiGouBH(commonDept);
    }
}
