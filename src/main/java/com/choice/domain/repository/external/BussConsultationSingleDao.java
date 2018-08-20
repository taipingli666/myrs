package com.choice.domain.repository.external;

import java.util.List;
import java.util.Map;

import com.choice.domain.entity.external.BussConsultationSingle;
import com.choice.domain.entity.external.ConsultationSingleVo;
import com.choice.domain.entity.referral.InOUtChtCount;
import com.choice.domain.entity.user.ChannelUser;

/**
 * 远程会诊单的服务类
 */
public interface BussConsultationSingleDao {
	/**
     * 插入
     * @param buss
     * @return
     */
    int insert(BussConsultationSingle buss);
    
    /**
     * 插入
     * @param buss
     * @return
     */
    int insertSelective(BussConsultationSingle buss);
    
    /**
     * 根据主键选择跟新
     * @param buss
     * @return
     */
    int updateByPrimaryKeySelective(BussConsultationSingle buss);
    
    String getLiuShuiHao(String uuid);
    
//    int createConsultationSingle(BussConsultationSingle buss, CommonCht commonCht,long hyid,Long paibanId);
//
//    String initConsultationSingle(ChannelUser o, BussConsultationScheduling bussConsultationScheduling,Long singleId);
//
    int cancelConsultationSingle(BussConsultationSingle single);
//
    public List<ConsultationSingleVo> getSingleAndChtInformation(BussConsultationSingle single);
//
	public List<ConsultationSingleVo> getSingleAndChtInformationList(BussConsultationSingle single);
//
//    int delSingle(BussConsultationSingle single);
//
//    BussConsultationSingle perfectSingle(Long singleId, Integer ip);
//
//    ConsultationSingleVo checkJoinCriteria(Long singleId, String type, Integer opId,ChannelUser o);
//
//    void consultationUpdate(BussConsultationSingle single, ChannelUser o,String type);
//
	public List<InOUtChtCount> consultationStatistics(Map map);
//
//    Map lookConsultationWaitQueue(Long id);
//
      List getTodayConsultationList(BussConsultationSingle single);
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
      List<ConsultationSingleVo> getBg(BussConsultationSingle single);
//
//    /**
//     * 获取某机构的所有片子
//     * @param map
//     * @return
//     */
//    PageList<Map<String,Object>> getyxzl(int start, Map<String,Object> map)throws Exception;
//    /**
//     * 根据id获取片子
//     * @return
//     */
//    Map<String,Object> getYXZlById(Map<String,Object> map)throws Exception;
      
      int consultationSingleUpdate(BussConsultationSingle sin);
      
      BussConsultationSingle selectByPrimaryKey(Long singleId);
}
