package com.choice.domain.service.external;

import java.util.List;
import java.util.Map;

import com.choice.domain.entity.external.BussConsultationSingle;
import com.choice.domain.entity.external.ConsultationSingleVo;
import com.choice.domain.entity.external.PatientInfo;
import com.choice.domain.entity.external.ScheduleInfo;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.sign.util.Page;

/**
 * 远程会诊单的服务类
 */
public interface BussConsultationSingleService {
	public int createConsultationSingle(BussConsultationSingle buss, PatientInfo patientinfo,long hyid,Long paibanId);
//
    String initConsultationSingle(ChannelUser o, ScheduleInfo bussConsultationScheduling,Long singleId);
//
    int cancelConsultationSingle(BussConsultationSingle single);
//
    public List<ConsultationSingleVo> getSingleAndChtInformation(BussConsultationSingle single);
//
	public Page<ConsultationSingleVo> getSingleAndChtInformationList(Integer page, Integer size,BussConsultationSingle single,String brxm);
//
//    int delSingle(BussConsultationSingle single);
//
//    BussConsultationSingle perfectSingle(Long singleId, Integer ip);
//
    ConsultationSingleVo checkJoinCriteria(Long singleId, String type, Integer opId,ChannelUser o);
//
    void consultationUpdate(BussConsultationSingle single, ChannelUser o,String type);
//
	public Map<String,Object> consultationStatistics(Map map);
//
//    Map lookConsultationWaitQueue(Long id);
//
	public List<ConsultationSingleVo> getTodayConsultationList(Integer page, Integer size,BussConsultationSingle single,String brxm);
//
//    /**
//     * 获取所有预约的人
//     * @return
//     */
//    List<Map<String,Object>> getRegisters(Long liushuihao);
//
//    /**
//     * 查看等待列表
//     * @param id
//     * @return
//     */
//    List<Map<String,Object>> getConWaitQueue(Long id);
//
    ConsultationSingleVo getBg(Long singleId, String type, Integer opId,ChannelUser o);
//
    /**
     * 获取某机构的所有片子
     * @param map
     * @return
     */
	public Page<Map<String,Object>> getyxzl(Integer page, Integer size, Map<String,Object> map)throws Exception;
    /**
     * 根据id获取片子
     * @return
     */
    Map<String,Object> getYXZlById(Map<String,Object> map)throws Exception;
    
    /**
     * 
     */
    BussConsultationSingle selectByPrimaryKey(Long singleId);
}
