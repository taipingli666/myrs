package com.choice.domain.service.external.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.choice.domain.entity.dictionary.CommonDictionaryValue;
import com.choice.domain.entity.external.BussConsultationSingle;
import com.choice.domain.entity.external.ConsultationSingleVo;
import com.choice.domain.entity.external.PatientInfo;
import com.choice.domain.entity.external.RegisterSourceInfo;
import com.choice.domain.entity.external.ScheduleInfo;
import com.choice.domain.entity.referral.InOUtChtCount;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.repository.dictionary.CommonDictionaryValueDao;
import com.choice.domain.repository.external.BussConsultationSingleDao;
import com.choice.domain.repository.external.PatientInfoDao;
import com.choice.domain.repository.external.RegisterSourceInfoDao;
import com.choice.domain.repository.external.ScheduleInfoDao;
import com.choice.domain.service.external.BussConsultationSingleService;
import com.choice.sign.exception.ConsultationException;
import com.choice.sign.util.HttpHelper;
import com.choice.sign.util.Page;
import com.choice.sign.util.UuIdUtil;
import com.github.pagehelper.PageHelper;

/**
 * 远程会诊会诊单的服务实现累
 */
@Service
public class BussConsultationSingleServiceImpl implements BussConsultationSingleService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private BussConsultationSingleDao bussConsultationSingleDao;
    
    @Resource
	private CommonDictionaryValueDao commonDictionaryValueDao;
    
    @Resource
	private PatientInfoDao  patientinfoDao;
    
    @Resource
	private RegisterSourceInfoDao  registerSourceInfoDao;
    
    @Resource
	private ScheduleInfoDao scheduleInfoDao;

//    /**
//     * 通用查询条件
//     * @param
//     * @param obj
//     * @return
//     */
//    @Override
//    public Object getExample(BussConsultationSingle single, Object obj) {
//        BussConsultationSingleExample example = new BussConsultationSingleExample();
//        BussConsultationSingleExample.Criteria criteria = example.createCriteria();
//        if(single != null){
//            //构建条件
//            if(StringUtil.isNotEmpty(single.getShenqingysid())){
//                //构建我发起的预约列表
//                criteria.andShenqingysidEqualTo(single.getShenqingysid());
//            }
//            if(StringUtil.isNotEmpty(single.getHuizhenysid())){
//                //构建我发收到的预约列表
//                criteria.andHuizhenysidEqualTo(single.getHuizhenysid());
//            }
//            if(StringUtil.isNotEmpty(single.getShenqingzt())){
//                criteria.andShenqingztEqualTo(single.getShenqingzt());
//            }
//        }
//        return example;
//    }
//
    /**
     * 添加会诊单
     * @param buss
     * @param
     * @return
     */
    @Override
    @Transactional
    public int createConsultationSingle(BussConsultationSingle buss, PatientInfo patientinfo,long hyid,Long paibanId) {
        //收集病人档案数据,先从数据库中查,姓名和身份证为条件
        PatientInfo info = patientinfoDao.selectByPatientIdCard(buss.getBingrenid());
        if(info!= null ){
            //说明建设过档案
            buss.setBingrenid(info.getPatientIdCard());
        }else{
            //没有建过档案,暂时先插入,日后要从医院掉接口拿数据
            try{
            	patientinfo.setId(UuIdUtil.getUuid());
            	patientinfo.setPatientIdCard(buss.getBingrenid());
            	patientinfo.setPatientPhone(buss.getShoujihm());
            	patientinfo.setPatientName(buss.getXingming());
            	patientinfo.setPatientGender(buss.getXingbie());
                int i = patientinfoDao.insertSelective(patientinfo);
            }catch (Exception e){
            	throw new ConsultationException("错误：重复的身份证号",e);
            }

            buss.setBingrenid(patientinfo.getPatientIdCard());
        }//scheduling
        //完善会诊单信息,完善信息要和建档案分开事务
        int x = this.perfectSingle(buss,hyid,paibanId);
        return x;
    }

    /**
     * 初始化会诊单
     * @param o
     * @param
     * @return
     */
    @Override
    public String initConsultationSingle(ChannelUser o, ScheduleInfo scheduleinfo,Long singleId) {
        BussConsultationSingle single = new BussConsultationSingle();
        single.setHuizhenksid(scheduleinfo.getDeptCode());//会诊科室编号
        single.setHuizhenksmc(scheduleinfo.getDeptName());//会诊科室名称
        single.setHuizhenyyid(scheduleinfo.getHosCode());//会诊医院id
        single.setHuizhenyymc(scheduleinfo.getHosName());//会诊医院名称
        single.setHuizhenysmc(scheduleinfo.getDoctorName());//会诊医生名称
        single.setHuizhenysid(scheduleinfo.getDoctorCode());//会诊医生id
        single.setYuyuerq(scheduleinfo.getWorkDate());//预约日期
        single.setShenqingysid(o.getUserId()+"");//医生id
        single.setShenqingysmc(o.getTrueName());//医生名称
        single.setShenqingyyid(o.getHosId()+"");//医院id
        single.setShenqingyymc(o.getHosName());//医院名称
        single.setHuizhenpbid(scheduleinfo.getId()+"");//会诊排班id
        single.setShangxiawbz(scheduleinfo.getWorkPeriod());
        //初始化会诊状态和申请状态
        single.setShenqingzt(BussConsultationSingle.SHENQINGZT_TIANXIEWZ);//申请状态
        single.setHuizhenzt("0");//会诊状态默认为0
        single.setYuyuerq(scheduleinfo.getWorkDate());//预约日期
        single.setHuizhenfy("0");
        single.setYuyuelx("2");//预约类型
        single.setZhifubz("0");//未付钱
        //两种情况 1.对象里有流水号,更新 2.对象里没有流水号,插入
        String st = null;
        System.out.println(JSON.toJSONString(single));
        if(singleId != null && singleId != 0){
            //有
            single.setLiushuihao(singleId);
            int i = bussConsultationSingleDao.updateByPrimaryKeySelective(single);
            if(i > 0){
                st =  singleId + "";
            }
        }else{
            //没有
            String uuid = UuIdUtil.getUuid();
            //先放到病情描述里,为了获取id
            single.setBingqingms(uuid);
            int i = bussConsultationSingleDao.insert(single);
            if(i == 0){
                //插入失败
                throw new RuntimeException("初始化会诊单出错");
            }
            st =  bussConsultationSingleDao.getLiuShuiHao(uuid);
        }
        return st;
    }

    /**
     * 取消会诊
     * @return
     */
    @Override
    @Transactional
    public int cancelConsultationSingle(BussConsultationSingle single) {
        //改变号源状态
        single.setQuxiaosj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        RegisterSourceInfo sourceInfo=new RegisterSourceInfo();
        sourceInfo.setScheduleCode(Long.parseLong(single.getHuizhenpbid()));
        sourceInfo.setSequenceNumber(Integer.parseInt(single.getYuyuexh()));
        int i = registerSourceInfoDao.releaseSource(sourceInfo);
        if(i == 0){
            //失败,抛出异常
            throw new RuntimeException("改变号源状态出错");
        }
        //排班号源总使用数-1
        int i2 = scheduleInfoDao.releaseSource(single.getLiushuihao());
        if(i == 0){
            //失败
            throw new RuntimeException("排版已使用数量-1出错");
        }
        //改变会诊单申请状态为取消预约
        int i3 = bussConsultationSingleDao.cancelConsultationSingle(single);
        if(i3 == 0){
            //失败
            throw new RuntimeException("//改变会诊单申请状态为取消预约出错");
        }
        return i3;
    }

    /**
     * 根据会诊单id获取会诊单和病人信息
     * @param
     * @return
     */
    @Override
    public List<ConsultationSingleVo> getSingleAndChtInformation(BussConsultationSingle single) {
        return bussConsultationSingleDao.getSingleAndChtInformation(single);
    }

    @Override
    public Page<ConsultationSingleVo> getSingleAndChtInformationList(Integer page, Integer size,BussConsultationSingle single,String brxm) {
    	com.github.pagehelper.Page startPage = PageHelper.startPage(page, size);
    	ConsultationSingleVo csvo=new ConsultationSingleVo();
    	csvo.setXingming(brxm);
    	csvo.setShenqingysid(single.getShenqingysid());
    	csvo.setHuizhenysid(single.getHuizhenysid());
        List<ConsultationSingleVo> list = bussConsultationSingleDao.getSingleAndChtInformationList(single);
    	Long total = startPage.getTotal();
    	return new Page<ConsultationSingleVo>(size, page, total.intValue(), list);
    }
//
//    @Override
//    public int delSingle(BussConsultationSingle single) {
//        return bussConsultationSingleMapper.delSingle(single);
//    }
//
//    @Override
//    public BussConsultationSingle perfectSingle(Long singleId, Integer ip) {
//        BussConsultationSingle single = new BussConsultationSingle();
//        single.setLiushuihao(singleId);
//        single.setShenqingysid(ip+"");
//        return bussConsultationSingleMapper.perfectSingle(single);
//    }
//
    /**
     * 进入会诊间的验证
     */
    @Override
    public ConsultationSingleVo checkJoinCriteria(Long singleId, String type, Integer opId,ChannelUser o) {
        //看类型
        BussConsultationSingle single = new BussConsultationSingle();
        single.setShenqingzt(BussConsultationSingle.SHENQINGZT_ZHIFUCG);//状态必须是支付成功的,也是就付过钱的订单
        if("1".equals(type)){
            //说明是发起方,o为发起方的session
            single.setLiushuihao(singleId);
            single.setHuizhenysid(o.getUserId()+"");
            single.setShenqingysid(opId + "");
        }else if("2".equals(type)){
            //说明是受邀方,o为受邀方的session
            single.setLiushuihao(singleId);
            single.setShenqingysid(o.getUserId()+"");
            single.setHuizhenysid(opId+"");
        }else {
        	single.setLiushuihao(singleId);
        }

        List<ConsultationSingleVo> list = bussConsultationSingleDao.getSingleAndChtInformation(single);
        ConsultationSingleVo vo=null;
        if(list!=null && list.size()>0) {
	        //会诊方状态和申请方状态 暂且空下
        	vo=list.get(0);
        }
        return vo;
    }

    /**
     * 会诊中通用更新的方法
     * @return
     */
    @Override
    public void consultationUpdate(BussConsultationSingle single, ChannelUser o,String type) {
        //根据type的值,决定业务
        BussConsultationSingle sin = new BussConsultationSingle();
        sin.setLiushuihao(single.getLiushuihao());
        Date date = new Date();
        if(type.equals("0")){
            //会诊中单纯想保存检查报告
            sin.setZhenduanbg(single.getZhenduanbg());
        }else if(type.equals("1")){
            //开始会诊
            sin.setHuizhenfangkssj(date);
            sin.setShenqingfangkssj(date);
            sin.setHuizhenfangzt("1");//会诊开始
            sin.setShenqingfangzt("1");//会诊开始
            sin.setHuizhenzt(BussConsultationSingle.HUIZHENZT_HUIZHENZ);//会诊中
        }else if(type.equals("2")){
            //结束会诊
            sin.setShenqingzt(BussConsultationSingle.SHENQINGZT_ZHENDUANWC);//会诊结束
            sin.setHuizhenzt(BussConsultationSingle.HUIZHENZT_HUIZHENJS);//会诊结束
            sin.setHuizhenfangjssj(date);
            sin.setShenqingfangjssj(date);
            sin.setZhenduanbg(single.getZhenduanbg());
            sin.setHuizhenfangzt("2");//会诊结束
            sin.setShenqingfangzt("2");//会诊结束
        }else if(type.equals("3")){
            //异常挂断 会诊异常结束
            sin.setJvjueyy(single.getJvjueyy());
            sin.setQuxiaosj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            sin.setHuizhenzt(BussConsultationSingle.HUIZHENZT_HUIZHENYCJS);
            sin.setShenqingzt(BussConsultationSingle.SHENQINGZT_HUIZHENYCJS);
        }
        int i = bussConsultationSingleDao.consultationSingleUpdate(sin);
        if(i == 0){
            throw new RuntimeException("更新会诊单状态失败");
        }
    }

    /**
     * 会诊统计
     * @param map
     * @return
     */
    @Override
    public Map<String,Object> consultationStatistics(Map map) {
        List<InOUtChtCount> list = bussConsultationSingleDao.consultationStatistics(map);
        if(list == null || list.size()==0){
            return null;
        }
        //封装画图需要的数据
        StringBuffer count = new StringBuffer("");
        StringBuffer money = new StringBuffer("");
        Map<String,Object> map2 = new HashMap<String,Object>();
        for(int i = 1;i<=12;i++){
            int x = 0;
            for(InOUtChtCount in : list){
                if(in.getMonth() != null && (Integer.parseInt(in.getMonth())==i)){
                    count.append(in.getNum()+",");
                    money.append(in.getTotal()+",");
                    x = 1;
                }
            }
            if(x == 1){
                continue;
            }
            count.append("0,");
            money.append("0,");
        }
        map2.put("count",count);
        map2.put("money",money);
        return map2;
    }
//
//    /**
//     * 查询排队情况
//     * @param id
//     * @return
//     */
//    @Override
//    public Map lookConsultationWaitQueue(Long id) {
//        return bussConsultationSingleMapper.lookConsultationWaitQueue(id);
//    }
    /**
     * 获取今日会诊列表,包括未开始,已经完成,异常挂断
     * @return
     */
    @Override
    public List<ConsultationSingleVo> getTodayConsultationList(Integer page, Integer size,BussConsultationSingle single,String brxm) {
//    	com.github.pagehelper.Page startPage = PageHelper.startPage(page, size);
    	ConsultationSingleVo csvo=new ConsultationSingleVo();
    	csvo.setXingming(brxm);
    	csvo.setShenqingysid(single.getShenqingysid());
    	csvo.setHuizhenysid(single.getHuizhenysid());
    	List<ConsultationSingleVo> list=bussConsultationSingleDao.getTodayConsultationList(csvo);
//    	Long total = startPage.getTotal();
//    	return new Page<ConsultationSingleVo>(size, page, total.intValue(), list);
    	return list;
    }
//
//    /**
//     * 获取所有预约的人
//     * @return
//     */
//    @Override
//    public List<Map<String, Object>> getRegisters(Long liushuihao) {
//        return bussConsultationSingleMapper.getRegisters(liushuihao);
//    }
//    /**
//     * 查看等待列表
//     * @param id
//     * @return
//     */
//    @Override
//    public List<Map<String, Object>> getConWaitQueue(Long id) {
//        return bussConsultationSingleMapper.getConWaitQueue(id);
//    }
    /**
     *    报告获取
     */
    @Override
    public ConsultationSingleVo getBg(Long singleId, String type, Integer opId,ChannelUser o) {
        //看类型
        BussConsultationSingle single = new BussConsultationSingle();
        single.setShenqingzt("'3','4','5'");//状态必须是支付成功的,也是就付过钱的订单
        if(type.equals("1")){
            //说明是发起方,o为发起方的session
            single.setLiushuihao(singleId);
            single.setHuizhenysid(o.getUserId()+"");
            single.setShenqingysid(opId + "");
        }else{
            //说明是受邀方,o为受邀方的session
            single.setLiushuihao(singleId);
            single.setShenqingysid(o.getUserId()+"");
            single.setHuizhenysid(opId+"");
        }

        List<ConsultationSingleVo> list=  bussConsultationSingleDao.getBg(single);
        ConsultationSingleVo  vo = null;
        if(list!=null && list.size()>0) {
        	vo = list.get(0);
        }
        //会诊方状态和申请方状态 暂且空下
        return vo;
    }

    /**
     * 获取机构下片子
     * @param map
     * @return
     */
    @Override
    public Page<Map<String,Object>> getyxzl(Integer page, Integer size, Map<String,Object> map)throws Exception{
//    	com.github.pagehelper.Page startPage = PageHelper.startPage(page, size);
        HttpHelper instance = HttpHelper.getInstance();
        String s="";
        List<CommonDictionaryValue> listapa=new ArrayList<CommonDictionaryValue>();
        listapa = commonDictionaryValueDao.getInfoByParentCode(10031);
        Iterator<CommonDictionaryValue> iterator=listapa.iterator();
    	while(iterator.hasNext()) {
    		CommonDictionaryValue cdv=iterator.next();
    		if(cdv.getCode()==100311) {
    			s=cdv.getWord();
    		}
    	}
        s = s.replace("{code}",(String)map.get("code"));
        s = s.replace("{page}",page+"");
        if(StringUtils.isNotEmpty((String)map.get("patientName"))){
            s += "/patientName/"+(String)map.get("patientName");
        }
        String responseSt = instance.getAndLog(s,
                "【ychz_yxzlsy,远程会诊调用影像服务开始,根据条件查找索引,参数地址"+s+"】",
                "【ychz_yxzlsy,远程会诊调用影像服务开始,根据条件查找索引..】"
        );
        //请求成功
        HashMap<String,Object> hashMap2 = JSON.parseObject(responseSt, HashMap.class);
        if(hashMap2 != null && (int)hashMap2.get("code")==1){
            //请求成功
            hashMap2.put("pageNum",page);
            int total = Integer.parseInt(String.valueOf(hashMap2.get("image_count")));
        	return new Page<Map<String,Object>>(size, page, total, (List)hashMap2.get("data"));
        }
        throw new Exception("错误:获取医院影像索引失败");
    }

    /**
     * 根据id获取片子
     * @param
     * @return
     */
    @Override
    public Map<String, Object> getYXZlById(Map<String,Object> map)throws Exception {
        HttpHelper instance = HttpHelper.getInstance();
        String s="";
        List<CommonDictionaryValue> listapa=new ArrayList<CommonDictionaryValue>();
        listapa = commonDictionaryValueDao.getInfoByParentCode(10031);
        Iterator<CommonDictionaryValue> iterator=listapa.iterator();
    	while(iterator.hasNext()) {
    		CommonDictionaryValue cdv=iterator.next();
    		if(cdv.getCode()==100312) {
    			s=cdv.getWord();
    		}
    	}
        s = s.replace("{id}",(String)map.get("id"));
        s = s.replace("{code}",(String)map.get("code"));
        String responseSt = instance.getAndLog(s,
        "【ychz_yxzlbyid,远程会诊调用影像服务开始,根据影像id获取浏览地址..参数地址"+s+"】",
        "【ychz_yxzlbyid,远程会诊调用影像服务结束,根据影像id获取浏览地址..】"
        );
        //请求成功
        HashMap<String,Object> hashMap = JSON.parseObject(responseSt, HashMap.class);
        if(hashMap != null&&(int)hashMap.get("code")==1){
            //请求成功
            return hashMap;
        }
        throw new Exception("错误：未获取到数据");
    }

    public int perfectSingle(BussConsultationSingle single,Long hyid,Long paibanId){
        //完善信息,申请医院信息不需要添加,因为在创建的时候就有
        single.setShenqingsj(new Date());//申请日期
        //赋值申请状态和会诊状态
        single.setShenqingzt(BussConsultationSingle.SHENQINGZT_ZHIFUCG);//预约成功,但是没有付钱，先改为已支付
        //先改变号源状态,若失败说明号源被并发占用,再更新排班号源使用数,最后插入数据,在同一事务下
        int i = registerSourceInfoDao.useHaoYuan(hyid);
        if(i == 0){
            //说明被占用,抛出异常
            throw new ConsultationException("保存失败:该号源已被占用");
        }
        int i2 = scheduleInfoDao.useHaoYuan(paibanId);
        if(i2 == 0){
            //更改排版号源使用数
            throw new ConsultationException("保存失败:更改排班状态出错");
        }
        //插入数据
        int i3 = bussConsultationSingleDao.updateByPrimaryKeySelective(single);
        //System.out.println(JSON.toJSONString(single));
        if(i3 == 0){
            logger.info(single.toString());
            //更改会诊单出错,抛出异常
            throw new ConsultationException("保存失败:保存会诊单出错");
        }
        return 1;
    }
    
    
    /**
     * 根据id获取预约
     * @param
     * @return
     */
    @Override
    public BussConsultationSingle selectByPrimaryKey(Long singleId) {
    	return bussConsultationSingleDao.selectByPrimaryKey(singleId);
    }
}
