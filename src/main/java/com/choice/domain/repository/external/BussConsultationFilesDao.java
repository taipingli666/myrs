package com.choice.domain.repository.external;

import java.util.List;
import com.choice.domain.entity.external.BussConsultationFiles;

public interface BussConsultationFilesDao {
	
	int insert(BussConsultationFiles bussConsultationFiles);

    List getConsultationImgById(BussConsultationFiles buss);

    int deleteByPrimaryKey(Long id);
    
}
