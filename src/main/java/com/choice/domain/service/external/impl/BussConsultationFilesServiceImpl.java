package com.choice.domain.service.external.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.choice.domain.entity.external.BussConsultationFiles;
import com.choice.domain.repository.external.BussConsultationFilesDao;
import com.choice.domain.service.external.BussConsultationFilesService;

/**
 * 远程会诊的文件服务类
 */
@Service
public class BussConsultationFilesServiceImpl implements BussConsultationFilesService {
    @Resource
    private BussConsultationFilesDao bussConsultationFilesDao;
    
    @Override
    public int insert(BussConsultationFiles bussConsultationFiles) {
        return bussConsultationFilesDao.insert(bussConsultationFiles);
    }

    /**
     * 获取排班图片
     * @param buss
     * @return
     */
    @Override
    public List getConsultationImgById(BussConsultationFiles buss) {
        return bussConsultationFilesDao.getConsultationImgById(buss);
    }

    @Override
    public int delById(Long id) {
        return bussConsultationFilesDao.deleteByPrimaryKey(id);
    }
}
