package com.choice.domain.service.external;


import java.util.List;
import com.choice.domain.entity.external.BussConsultationFiles;

/**
 * Created by 远程会诊文件服务类 on 2017/9/22 0022.
 */

public interface BussConsultationFilesService {
    int insert(BussConsultationFiles bussConsultationFiles);

    List getConsultationImgById(BussConsultationFiles buss);

    int delById(Long id);
}
