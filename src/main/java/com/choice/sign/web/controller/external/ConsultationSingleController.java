package com.choice.sign.web.controller.external;

import com.alibaba.fastjson.JSONObject;
import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.dictionary.CommonDictionaryValue;
import com.choice.domain.entity.dictionary.Department;
import com.choice.domain.entity.external.*;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.dictionary.DepartmentService;
import com.choice.domain.service.dictionary.DictValueService;
import com.choice.domain.service.external.BussConsultationFilesService;
import com.choice.domain.service.external.BussConsultationSingleService;
import com.choice.domain.service.external.ScheduleInfoService;
import com.choice.domain.service.user.UserService;
import com.choice.sign.util.DateUtil;
import com.choice.sign.util.Page;
import com.choice.sign.util.UuIdUtil;
import com.choice.sign.web.controller.websocket.WebSocketController;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Controller
@RequestMapping(value = "/consultationSingle")
public class ConsultationSingleController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private BussConsultationSingleService bussConsultationSingleService;
	@Resource
	private UserService userService;
	@Resource
	private CommonHospitalService hospitalService;
	@Resource
    private DictValueService dictValueService;
	@Resource
    private DepartmentService departmentService;
	@Resource
    private ScheduleInfoService scheduleInfoService;
	@Resource
    private BussConsultationFilesService bussConsultationFilesService;
	/**
     * 会诊统计
     * @return
     */
	@RequestMapping("consultationstatistics")
    public ModelAndView consultationStatistics(String type,String year,HttpServletRequest request){
		ModelMap modelMap = new ModelMap();
		try {
			if(year==null || "".equals(year)) {
				year=String.valueOf((new Date()).getYear());
			}
			modelMap.addAttribute("year",year);
			modelMap.addAttribute("type",type);
		} catch (Exception e) {
			logger.info("会诊统计失败："+e.getMessage());
		}
        return new ModelAndView("/external/consultationSingle/consultationstatistics",modelMap);
    }
	
	/**
     * 会诊统计
     * @return
     */
	@RequestMapping("consultationstatisticsdata")
	@ResponseBody
    public String consultationStatisticsdata(String type,String year,HttpServletRequest request){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		try {
			ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("year", year);
			map.put("id", user.getUserId());
			map.put("state", "4");
			map.put("type", type);
			Map<String,Object> m = bussConsultationSingleService.consultationStatistics(map);
			if (m != null) {
				returnMap.put("data",m);
			}
			if(year==null || "".equals(year)) {
				year=String.valueOf((new Date()).getYear());
			}
			returnMap.put("year",year);
			returnMap.put("type",type);
		} catch (Exception e) {
			logger.info("获取会诊统计失败:" + e.getMessage());
		}
		String json=JSONObject.toJSONString(returnMap);
        return json;
    }

	
	/**
     * 会诊列表
     * @return
     */
	@RequestMapping("acceptappointment")
    public ModelAndView acceptappointment(
    		@RequestParam(value="page",defaultValue="1") Integer page,
            @RequestParam(value="size",defaultValue="10")Integer size,
            @RequestParam(value="startDate",defaultValue="") String startDate,
            @RequestParam(value="endDate",defaultValue="") String endDate,
            @RequestParam(value="state",defaultValue="") String state,
            @RequestParam(value="bingrenid",defaultValue="") String bingrenid,
            BussConsultationSingle single, ModelMap modelmap, HttpServletRequest request){
		ModelMap modelMap = new ModelMap();
		try {
			
			
			ChannelUser o = (ChannelUser) request.getSession().getAttribute("loginUser");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfhms = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        if(state==null||state.equals("")){
	        	state="3";
	        }
	        try{
	        	if(null != startDate && !"".equals(startDate)) {
	        		single.setHuizhenfangkssj(sdfhms.parse(startDate));
	        	}
	        	if(null != endDate && !"".equals(endDate)) {
	        		single.setHuizhenfangjssj(sdfhms.parse(endDate));
	        	}
	        	if(state!=null && state.equals("3")){
	                single.setShenqingzt(BussConsultationSingle.SHENQINGZT_ZHIFUCG);
	                single.setHuizhenfangzt("0");
	            }
	        	else if(state!=null && state.equals("1")){
	                single.setShenqingzt(BussConsultationSingle.SHENQINGZT_ZHIFUCG);
	                single.setHuizhenzt(BussConsultationSingle.HUIZHENZT_HUIZHENZ);
	            }
	        	single.setShenqingzt(state);
	        	single.setHuizhenysid(o.getUserId().toString());
	            Page pageobj = bussConsultationSingleService.getSingleAndChtInformationList(page,size,single,bingrenid);
	            if(pageobj != null && pageobj.getTotalRecord()>0){
	                //存在,封装在线列表及其头像,电话
//	                initOnlineAndHeadImg(list,type);
	            }
	            modelMap.addAttribute("pageobj", pageobj);
	            modelMap.addAttribute("startDate", startDate);
	            modelMap.addAttribute("endDate", endDate);
	            modelMap.addAttribute("state", state);
	            modelMap.addAttribute("bingrenid", bingrenid);
	        }catch (Exception e){
	        	e.printStackTrace();
	            return null;
	        }
	        
	        
		} catch (Exception e) {
			logger.info("获取预约列表失败:"+e.getMessage());
		}
        return new ModelAndView("/external/consultationSingle/acceptappointment",modelMap);
    }
	
	/**
     * 预约列表
     * @return
     */
	@RequestMapping("sponsorappointment")
    public ModelAndView sponsorappointment(
    		@RequestParam(value="page",defaultValue="1") Integer page,
            @RequestParam(value="size",defaultValue="10")Integer size,
            @RequestParam(value="startDate",defaultValue="") String startDate,
            @RequestParam(value="endDate",defaultValue="") String endDate,
            @RequestParam(value="state",defaultValue="") String state,
            @RequestParam(value="bingrenid",defaultValue="") String bingrenid,
            BussConsultationSingle single, ModelMap modelmap, HttpServletRequest request){
		ModelMap modelMap = new ModelMap();
		try {
			ChannelUser o = (ChannelUser) request.getSession().getAttribute("loginUser");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfhms = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        if(state==null||state.equals("")){
	        	state="1";
	        }
	        try{
	        	if(null != startDate && !"".equals(startDate)) {
	        		single.setHuizhenfangkssj(sdfhms.parse(startDate));
	        	}
	        	if(null != endDate && !"".equals(endDate)) {
	        		single.setHuizhenfangjssj(sdfhms.parse(endDate));
	        	}
	        	if(state!=null && state.equals("3")){
	                single.setShenqingzt(BussConsultationSingle.SHENQINGZT_ZHIFUCG);
	                single.setHuizhenfangzt("0");
	            }
	        	else if(state!=null && state.equals("1")){
	                single.setShenqingzt(BussConsultationSingle.SHENQINGZT_ZHIFUCG);
	                single.setHuizhenzt(BussConsultationSingle.HUIZHENZT_HUIZHENZ);
	            }
	        	single.setShenqingzt(state);
	        	single.setShenqingysid(o.getUserId().toString());
	            Page pageobj = bussConsultationSingleService.getSingleAndChtInformationList(page,size,single,bingrenid);
	            if(pageobj != null && pageobj.getTotalRecord()>0){
	                //存在,封装在线列表及其头像,电话
//	                initOnlineAndHeadImg(list,type);
	            }
	            modelMap.addAttribute("pageobj", pageobj);
	            modelMap.addAttribute("startDate", startDate);
	            modelMap.addAttribute("endDate", endDate);
	            modelMap.addAttribute("state", state);
	            modelMap.addAttribute("bingrenid", bingrenid);
	        }catch (Exception e){
	        	e.printStackTrace();
	            return null;
	        }
	        
	        
		} catch (Exception e) {
			logger.info("获取预约列表失败:"+e.getMessage());
		}
        return new ModelAndView("/external/consultationSingle/sponsorappointment",modelMap);
    }
	
	/**
     * 会诊间
     * @return
     */
	@RequestMapping("consultationroomtopa")
    public ModelAndView consultationroomtopa(HttpServletRequest request){
		ModelMap modelMap = new ModelMap();
		try {
			ChannelUser o = (ChannelUser) request.getSession().getAttribute("loginUser");
			modelMap.addAttribute("operIp",o.getUserId());
			List<CommonDictionaryValue> listap=dictValueService.selectValuesByCode(10033);
			Iterator<CommonDictionaryValue> iterator=listap.iterator();
			String socketUrl="";
        	while(iterator.hasNext()) {
        		CommonDictionaryValue cdv=iterator.next();
        		if(cdv.getCode()==100331) {
        			socketUrl=cdv.getWord();
        		}
        	}
        	modelMap.addAttribute("socketUrl",socketUrl);
		} catch (Exception e) {
			logger.info("获取会诊列表失败"+e.getMessage());
		}
        return new ModelAndView("/external/consultationSingle/consultationroomtopa",modelMap);
    }
	
	/**
     * 会诊间（支持分享桌面）
     * @return
     */
	@RequestMapping("consultationroomtopagg")
    public ModelAndView consultationroomtopagg(HttpServletRequest request){
		ModelMap modelMap = new ModelMap();
		try {
			ChannelUser o = (ChannelUser) request.getSession().getAttribute("loginUser");
			modelMap.addAttribute("operIp",o.getUserId());
			List<CommonDictionaryValue> listap=dictValueService.selectValuesByCode(10033);
			Iterator<CommonDictionaryValue> iterator=listap.iterator();
			String socketUrl="";
        	while(iterator.hasNext()) {
        		CommonDictionaryValue cdv=iterator.next();
        		if(cdv.getCode()==100331) {
        			socketUrl=cdv.getWord();
        		}
        	}
        	modelMap.addAttribute("socketUrl",socketUrl);
		} catch (Exception e) {
			logger.info("获取会诊列表失败"+e.getMessage());
		}
        return new ModelAndView("/external/consultationSingle/consultationroomtopagg",modelMap);
    }
	
	/**
     * 修改上级医院会诊间b
     * */
    @RequestMapping("consultationroomtopb")
    public ModelAndView consultationRoomTopb(HttpServletRequest request,Long singleId,
                                       String type,Integer opId){
    	ModelMap modelMap = new ModelMap();
        ChannelUser o = (ChannelUser) request.getSession().getAttribute("loginUser");
        List<CommonDictionaryValue> listap=dictValueService.selectValuesByCode(10033);
		Iterator<CommonDictionaryValue> iterator=listap.iterator();
		String socketUrl="";
    	while(iterator.hasNext()) {
    		CommonDictionaryValue cdv=iterator.next();
    		if(cdv.getCode()==100331) {
    			socketUrl=cdv.getWord();
    		}
    	}
    	BussConsultationSingle single = new BussConsultationSingle();
        single.setLiushuihao(singleId);
    	List<ConsultationSingleVo> list= bussConsultationSingleService.getSingleAndChtInformation(single);
    	modelMap.addAttribute("flagsq",0);
    	if(null != list && list.size()>0) {
    		if(o.getUserId().equals(Integer.parseInt(list.get(0).getShenqingysid()))) {
    			modelMap.addAttribute("flagsq",1);
    		}
    	}
    	modelMap.addAttribute("operIp",opId);
        modelMap.addAttribute("socketUrl",socketUrl);
        modelMap.addAttribute("singleId",singleId);
        modelMap.addAttribute("type",type);
        modelMap.addAttribute("operIpMy",o.getUserId());
        return new ModelAndView("/external/consultationSingle/consultationroomtopb",modelMap);
    }
    
    /**
     * 视频（谷歌版）
     * */
    @RequestMapping("consultationroomtopbgg")
    public ModelAndView consultationRoomTopbgg(HttpServletRequest request,Long singleId,
                                       String type,Integer opId){
    	ModelMap modelMap = new ModelMap();
        ChannelUser o = (ChannelUser) request.getSession().getAttribute("loginUser");
        List<CommonDictionaryValue> listap=dictValueService.selectValuesByCode(10033);
		Iterator<CommonDictionaryValue> iterator=listap.iterator();
		String socketUrl="";
    	while(iterator.hasNext()) {
    		CommonDictionaryValue cdv=iterator.next();
    		if(cdv.getCode()==100331) {
    			socketUrl=cdv.getWord();
    		}
    	}
    	BussConsultationSingle single = new BussConsultationSingle();
        single.setLiushuihao(singleId);
    	List<ConsultationSingleVo> list= bussConsultationSingleService.getSingleAndChtInformation(single);
    	modelMap.addAttribute("flagsq",0);
    	if(null != list && list.size()>0) {
    		if(o.getUserId().equals(Integer.parseInt(list.get(0).getShenqingysid()))) {
    			modelMap.addAttribute("flagsq",1);
    		}
    	}
    	modelMap.addAttribute("operIp",opId);
        modelMap.addAttribute("socketUrl",socketUrl);
        modelMap.addAttribute("singleId",singleId);
        modelMap.addAttribute("type",type);
        modelMap.addAttribute("operIpMy",o.getUserId());
        return new ModelAndView("/external/consultationSingle/desktopCapture-p2p/index",modelMap);
    }
    
	/**
     * 发起预约
     * @return
     */
	@RequestMapping("consultationregister")
    public ModelAndView consultationregister(HttpServletRequest request){
		ModelMap modelMap = new ModelMap();
		try {
			ChannelUser o = (ChannelUser) request.getSession().getAttribute("loginUser");
			List<CommonHospital> channelHospitals = hospitalService.getAllHospitalWithoutNull();
			modelMap.addAttribute("channelHospitals",channelHospitals);
			SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd");
			modelMap.addAttribute("day",s.format(new Date()));
			String erweimaurl = "";
			List<CommonDictionaryValue> listap=dictValueService.selectValuesByCode(10030);
			Iterator<CommonDictionaryValue> iterator=listap.iterator();
        	while(iterator.hasNext()) {
        		CommonDictionaryValue cdv=iterator.next();
        		if(cdv.getCode()==100301) {
        			erweimaurl=cdv.getWord();
        		}
        	}
			modelMap.addAttribute("erweimaurl", erweimaurl);
			modelMap.addAttribute("single",new BussConsultationSingle());
			modelMap.addAttribute("operId",o.getUserId());
		} catch (Exception e) {
			logger.info("获取发起预约失败"+e.getMessage());
			e.printStackTrace();
		}
        return new ModelAndView("/external/consultationSingle/consultationregister",modelMap);
    }
	
	/**
	 * 查看当天预约列表
	 * @param single
	 * @param request
	 * @param type
	 * @return
	 */
	@RequestMapping("gettodayconsultationlist")
    @ResponseBody
    public String getTodayConsultationList(BussConsultationSingle single,HttpServletRequest request,String type){
        ChannelUser o = (ChannelUser) request.getSession().getAttribute("loginUser");
        if(type==null||type.equals("")){
            return "";
        }
        try{
            if(type.equals("1")){
                single.setHuizhenysid(o.getUserId()+"");
            }else if(type.equals("2")){
                single.setShenqingysid(o.getUserId()+"");
            }
            List<ConsultationSingleVo> list = bussConsultationSingleService.getTodayConsultationList(1,1000,single,"");
            if(list != null && list.size()>0){
                //存在,封装在线列表及其头像,电话
                initOnlineAndHeadImg(list,type);
                JSONArray json=JSONArray.fromObject(list);
            	return json.toString();
            }
        }catch (Exception e){
        	logger.error("查看当天预约列表失败"+e.getMessage());
            return "";
        }
        return "";
    }
	
	/**
     * 取出开启会诊标志的科室
     */
    @RequestMapping("getConsultationDeptByJiGouBH")
    @ResponseBody
    public String getConsultationDeptByJiGouBH(Department department){
    	Map<String,Object> remap=new HashMap<String,Object>();
    	remap.put("success",false);
        try{
        	department.setDistanceFlag("1");
            List<Department> list = departmentService.getDepartmentList(department);
            if(list != null &&list.size()>0){
            	remap.put("success",true);
            	remap.put("data", list);
            }
        }catch (Exception  e){
        	logger.info("取出开启会诊标志的科室失败"+e.getMessage());
        	e.printStackTrace();
        	remap.put("success",false);
        }
    	return JSONObject.toJSONString(remap);
    }
    
    
    /**
     * 获取排班,带出医生简介
     * @param bs
     * @param start
     * @param length
     * @return
     */
    @RequestMapping("getSchedulingByRegister")
    @ResponseBody
    public String getSchedulingByRegister(ScheduleInfo bs,
    									@RequestParam(value="paibanrq",defaultValue="") String paibanrq,
                                             @RequestParam(value = "start", defaultValue = "0") int start,
                                             @RequestParam(value = "length", defaultValue = "10") int length){
        Map<String,Object> remap=new HashMap<String,Object>();
    	try{
    		if(null != paibanrq && !"".equals(paibanrq)) {
    			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
    			bs.setWorkDate(sdf.parse(paibanrq));
    		}
            Page p = scheduleInfoService.getScheduleInfoList(start / length + 1, length,bs,null,null);
            if(p.getContent().size()>0){
                //成功
            	remap.put("success",true);
            	remap.put("data",p);
            }else{
            	remap.put("message","无医生排班");
            }
        }catch (Exception e){
        	logger.error("获取排班,带出医生简介失败"+e.getMessage());
        	System.out.println(e.getMessage());
            remap.put("message","加载出错");
            String json=JSONObject.toJSONString(remap);
            return json;
        }
    	String json=JSONObject.toJSONString(remap);
        return json;
    }
    
	/*
	 * 封装头像和是否在线
	 */
    private void initOnlineAndHeadImg(List<ConsultationSingleVo> list,String type)throws Exception{
    	ConcurrentHashMap map = WebSocketController.getMap();
        ChannelUser o = null;
        for(ConsultationSingleVo vo:list){
            if(map.get(Integer.parseInt(type.equals("1")?vo.getShenqingysid():vo.getHuizhenysid())) != null){
                //说明在线
                vo.setOnLine("1");
            }else{
                vo.setOnLine("0");
            }
            //封装头像
            o = userService.getById(Integer.parseInt(vo.getShenqingysid()));
            if(o != null){
//                vo.setShenqingystx(o.getHeadportrait());
                vo.setShenqingysdh(o.getTel());
            }
            o = userService.getById(Integer.parseInt(vo.getHuizhenysid()));
            if(o != null){
//                vo.setHuizhenystx(o.getHeadportrait());
                vo.setHuizhenysdh(o.getTel());
            }
        }
    }
    
    /**
     * 获取某个排版下的所有号源
     * @return
     */
    @ResponseBody
    @RequestMapping("getSchedulingSource")
    public String getSchedulingSource(ScheduleInfo bs,HttpServletRequest request,Long singleId){
    	Map<String,Object> remap=new HashMap<String,Object>();
    	remap.put("tingzhenbz","0");
    	remap.put("haoyuanqybz","0");
        ChannelUser o = (ChannelUser) request.getSession().getAttribute("loginUser");
        try{
            List<ScheduleInfo> list = scheduleInfoService.getSchedulingSource(bs);
            //说明成功,插入数据
            String st = bussConsultationSingleService.initConsultationSingle(o,list.get(0),singleId);
            if(st != null && !st.equals("")){
                //说明初始化成功
            	remap.put("success",true);
            	remap.put("data",list);
            	remap.put("message",st);//st为插入后返回的流水号
            }else{
            	remap.put("message","获取号源出错");
            }
        }catch (Exception e){
        	logger.info("获取某个排版下的所有号源失败"+e.getMessage());
        	System.out.println(e.getMessage());
        	remap.put("message","获取号源出错");
        	String json=JSONObject.toJSONString(remap);
            return json;
        }
        String json=JSONObject.toJSONString(remap);
        return json;
    }
    
    /**
     * 影像资料界面
     * @return
     */
    @RequestMapping("yxzltable")
    public ModelAndView yptable(
    		@RequestParam(value="pageNumber",defaultValue="1") Integer page,
            @RequestParam(value="size",defaultValue="10")Integer size,
            @RequestParam(value="patientName",defaultValue="") String patientName,
            @RequestParam(value="code",defaultValue="7150001") String code,
            ModelMap modelmap, HttpServletRequest request){
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("patientName", patientName);
		modelMap.addAttribute("code", code);
		try {
			ChannelUser o = (ChannelUser) request.getSession().getAttribute("loginUser");
			List<CommonHospital> list = hospitalService.getAllHospital();
			modelMap.addAttribute("list",list);
	        try{
	            Map<String,Object> map=new HashMap<String,Object>();
	            map.put("patientName", patientName);
	            map.put("code", code);
	        	Page<Map<String,Object>> pageobj=bussConsultationSingleService.getyxzl(page,size,map);
	        	modelMap.addAttribute("pageobj", pageobj);
	        }catch (Exception e){
	        	e.printStackTrace();
	        }
	        
	        
		} catch (Exception e) {
			logger.info("获取影像资料界面失败:"+e.getMessage());
		}
        return new ModelAndView("/external/consultationSingle/yxzltable",modelMap);
    }
    
    /**
     * 会诊单上传图片
     * @return
     */
    @RequestMapping("uploadConsultationImg")
    @ResponseBody
    public String uploadConsultationImg(MultipartFile file,Long sysdateFileIdValue){
    	Map<String,Object> remap=new HashMap<String,Object>();
        try{
        	//拿到图片地址
            String path = "";
        	List<CommonDictionaryValue> listap=dictValueService.selectValuesByCode(10032);
			Iterator<CommonDictionaryValue> iterator=listap.iterator();
        	while(iterator.hasNext()) {
        		CommonDictionaryValue cdv=iterator.next();
        		if(cdv.getCode()==100321) {
        			path=cdv.getWord();
        		}
        	}
            //拿到图片地址
            if(StringUtils.isEmpty(path)){
                path = "";
            }
            String date = DateUtil.createYYYYMMDir(path);
            //生成uuid
            String uuid = date+"."+UuIdUtil.getUuid()+file.getOriginalFilename();
            //C:/m/d/201732/alksdjalsjdk张三.jpg
            file.transferTo(new File(path + date +"/" + uuid));
            //成功,插入数据
            BussConsultationFiles bu = new BussConsultationFiles();
            bu.setCreateTime(new Date());
            bu.setFilePath(uuid);
            bu.setFileName(file.getOriginalFilename());
            bu.setSingleLiushuihao(sysdateFileIdValue);
            int i = bussConsultationFilesService.insert(bu);
            if(i>0){
            	remap.put("msg", "success");
            }
        }catch (Exception e){
            logger.info("会诊单上传图片失败,uploadConsultationImg,参数为"+sysdateFileIdValue,e);
        }
        return JSONObject.toJSONString(remap);
    }
    
    /**
     * 创建一个会诊申请单
     * @param buss
     * @return
     */
    @RequestMapping("createConsultationSingle")
    @ResponseBody
    public String createConsultationSingle(BussConsultationSingle buss,PatientInfo patientinfo, Long hyid,Long paibanId,HttpServletRequest request){
        //这里的创建其实是更新,因为在之前就已经有了数据,buss里存放了原先的流水号,下面做更新
        //查询排班信息
    	Map<String,Object> remap=new HashMap<String,Object>();
        try {
			ChannelUser channelUser = (ChannelUser) request.getSession().getAttribute("loginUser");
			buss.setShenqingyyid(channelUser.getHosId());
			//在session中获取医院名称
			if(request.getSession()!=null&&request.getSession().getAttribute("hospitalHeader")!=null){
				CommonHospital commonHospital = (CommonHospital)request.getSession().getAttribute("hospitalHeader");
				buss.setShenqingyymc(commonHospital.getHosName());
				//这边申请科室暂时未获取
				//buss.setShenqingksmc(channelUser.getHosName());
			}
            int i = bussConsultationSingleService.createConsultationSingle(buss,patientinfo,hyid,paibanId);
            if(i == 1){
                //说明成功
            	remap.put("success", true);
            	remap.put("data",buss);
            }else{
            	remap.put("message","保存失败");
            }
        }catch (Exception e){
//            errorInfo("保存会诊单失败,createConsultationSingle,参数为"+buss.toString()+"/"+commonCht.toString()+"/"+hyid+"/"+paibanId,e);
            remap.put("message","保存失败");
//            if(e instanceof ConsultationException){
//                apiResult.setMessage(e.getMessage());
//            }
        }
        return JSONObject.toJSONString(remap);
    }
    
    /**
     * 轮询获取会诊单图片
     * @return
     */
    @ResponseBody
    @RequestMapping("getConsultationImgById")
    public String getConsultationImgById(BussConsultationFiles buss,String type){
        Map<String,Object> remap =new  HashMap<String,Object>();
        try{
            if(type!=null&&type.equals("1")){
                buss.setFileName("");
            }else if(buss.getFileName().equals("")){
                buss.setFileName("''");
            }else{
                buss.setFileName(buss.getFileName().substring(0,buss.getFileName().length()-1));
            }

            List list = bussConsultationFilesService.getConsultationImgById(buss);
            if(list != null && list.size()>0){
            	remap.put("success",true);
            	remap.put("data",list);
            }
        }catch (Exception e){
            //获取图片失败
            logger.info("轮询获取资料失败,getConsultationImgById,参数为"+buss.toString(),e);
            remap.put("message","轮询获取资料失败");
        }
        return  JSONObject.toJSONString(remap);
    }

    /**
     * 删除会诊单图片
     * @return
     */
    @RequestMapping("delImg")
    @ResponseBody
    public String delImg(Long id){
    	Map<String,Object> remap =new  HashMap<String,Object>();
        try{
            int i = bussConsultationFilesService.delById(id);
            if(i >0 ){
                //成功
            	remap.put("success",true);
            }
        }catch (Exception e){
        	logger.info("删除会诊单图片失败,delImg,参数为"+id,e);
            remap.put("message","删除会诊单图片失败");
        }
        return JSONObject.toJSONString(remap);
    }
    
    
    /**
     * 查看会诊单详情
     */
    @RequestMapping("lookConsultationSingle/{type}/{singleId}")
    public ModelAndView lookConsultationSingle(HttpServletRequest request, @PathVariable(value = "type") Integer type,
                                         @PathVariable(value = "singleId")Long singleId){
        ModelMap modelMap=new ModelMap();
    	//为防止手动输入参数看别人的会诊单,type区分发请发查看,还是受邀方查看,
        try{
            BussConsultationSingle single = new BussConsultationSingle();
            single.setLiushuihao(singleId);
            ChannelUser o = (ChannelUser) request.getSession().getAttribute("loginUser");
            ConsultationSingleVo vo = null;
            if(type != null) {
                if (type == 1) {
                    //发起方查看会诊单,
                    single.setShenqingysid(o.getUserId() + "");
                    List<ConsultationSingleVo> list= bussConsultationSingleService.getSingleAndChtInformation(single);
                    if(list!=null && list.size()>0) {
                    	vo=list.get(0);
                    }
                } else if (type == 2) {
                    //受邀方查看
                    single.setHuizhenysid(o.getUserId() + "");
                    List<ConsultationSingleVo> list= bussConsultationSingleService.getSingleAndChtInformation(single);
                    if(list!=null && list.size()>0) {
                    	vo=list.get(0);
                    }
                }
            }
            if(vo != null){
//            	String url = "";
//    			List<CommonDictionaryValue> listap=dictValueService.selectValuesByCode(10030);
//    			Iterator<CommonDictionaryValue> iterator=listap.iterator();
//            	while(iterator.hasNext()) {
//            		CommonDictionaryValue cdv=iterator.next();
//            		if(cdv.getCode()==100301) {
//            			erweimaurl=cdv.getWord();
//            		}
//            	}
//                String url = functionService.getChannelSecretByName("yxurl");
            	modelMap.addAttribute("vo",vo);
//                model.addAttribute("yxurl",url);
                return new ModelAndView("/external/consultationSingle/consultationsingleinfo",modelMap);
//                return "consultation/consultationsingleinfo";
            }else{
            	modelMap.addAttribute("info","加载会诊单失败");
                return new ModelAndView("/external/consultationSingle/yxzltable",modelMap);
//                return "errorPage/base";
            }
        }catch (Exception e) {
        	e.printStackTrace();
        	modelMap.addAttribute("info","加载会诊单失败");
            return new ModelAndView("/external/consultationSingle/yxzltable",modelMap);
//            return "errorPage/base";
        }
        
    }
    
    /**
     * 邀请进入会诊间
     * type 1 为会诊方 2 为申请方
     * @param singleId
     * @param operId
     * @param request
     * @return
     */
    @RequestMapping("inviteconsultation")
    @ResponseBody
    public String inviteConsultation(Long singleId,Integer operId,HttpServletRequest request,String type,
                                        String ksbz){
        Map<String,Object> remap =new HashMap<String,Object>();
        //验证对方是否在线
        try{
//            if(("1").equals(type)&&!isOnline(operId)&&ksbz == null){
//                //会诊方,对方不在线
//                apiResult.setMessage("抱歉,对方不在线,不能邀请");
//                return apiResult;
//            }
            //验证会诊合法性
        	ChannelUser o = (ChannelUser) request.getSession().getAttribute("loginUser");
            ConsultationSingleVo vo = bussConsultationSingleService.checkJoinCriteria(singleId,type,operId,o);
            if(vo != null){
                //可以进入
                initHeadImg(vo);//封装头像
                remap.put("success",true);
                remap.put("data",vo);
                //通知被邀请方
                if(type.equals("1") && ksbz == null){
                    WebSocketController web =  (WebSocketController)WebSocketController.getMap().get(operId);
                    if(web != null){
                        try{
                            web.sendVideo(o.getUserName(),o.getUserId(),singleId);
                        }catch (Exception e){
                            logger.info("发起会诊邀请失败",e);
                        }
                    }
                }
            }else{
            	remap.put("message","进入出错:未获取到合法的会诊单");
            }
        }catch (Exception e){
        	e.printStackTrace();
            remap.put("message","抱歉,进入出错");
        }
        return JSONObject.toJSONString(remap);
    }
    
    private void initHeadImg(ConsultationSingleVo vo){
        ChannelUser o = null;
        o = userService.getById(Integer.parseInt(vo.getShenqingysid()));
        if(o != null){
//            vo.setShenqingystx(oper.getHeadportrait());
            vo.setShenqingysdh(o.getTel());
        }
        o = userService.getById(Integer.parseInt(vo.getHuizhenysid()));
        if(o != null){
//            vo.setHuizhenystx(oper.getHeadportrait());
            vo.setHuizhenysdh(o.getTel());
        }
    }
    
    /**
     * 获取报告
     * type 1 为会诊方 2 为申请方
     * @param singleId
     * @param operId
     * @param request
     * @return
     */
    @RequestMapping("getbg")
    @ResponseBody
    public String getBg(Long singleId,Integer operId,HttpServletRequest request,String type,
                                        String ksbz){
    	 Map<String,Object> remap =new HashMap<String,Object>();
        //验证对方是否在线
        try{
            //验证会诊合法性
        	ChannelUser oper = (ChannelUser) request.getSession().getAttribute("loginUser");
            ConsultationSingleVo vo = bussConsultationSingleService.getBg(singleId,type,operId,oper);
            if(vo != null){
                //可以进入
            	remap.put("success",true);
            	remap.put("data",vo);
            }else{
            	remap.put("message","错误:未获取到报告");
            }
        }catch (Exception e){
        	e.printStackTrace();
        	remap.put("message","错误:获取报告出错");
        }
        return JSONObject.toJSONString(remap);
    }
    
    /**
     * 获取服务器时间
     * @throws Exception
     */
    @RequestMapping("getServerTime")
    @ResponseBody
    public String getServerTime(){
        Map<String,Object> remap = new HashMap<String,Object>();
        remap.put("success", true);
        try{
        	remap.put("data",(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        }catch (Exception e){
        	remap.put("success", false);
        }
        return JSONObject.toJSONString(remap);
    }
    
    
    /**
     * 会诊中通用更新的方法
     * @return
     */
    @RequestMapping("consultationUpdate")
    @ResponseBody
    public String consultationUpdate(BussConsultationSingle single,String type,HttpServletRequest request){
    	Map<String,Object> remap = new HashMap<String,Object>();
        try{
        	ChannelUser o = (ChannelUser) request.getSession().getAttribute("loginUser");
            bussConsultationSingleService.consultationUpdate(single,o,type);
            remap.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            remap.put("success", false);
        }
        return JSONObject.toJSONString(remap);
    }
    
    /**
     * 加载会诊单的图片
     */
    @RequestMapping("showConsultationImg")
    @ResponseBody
    public void showConsultationImg(String path,HttpServletResponse response){
        String dirName = path.substring(0,path.indexOf("."));
        try{
        	String basepath="";
        	List<CommonDictionaryValue> listap=dictValueService.selectValuesByCode(10032);
			Iterator<CommonDictionaryValue> iterator=listap.iterator();
        	while(iterator.hasNext()) {
        		CommonDictionaryValue cdv=iterator.next();
        		if(cdv.getCode()==100321) {
        			basepath=cdv.getWord();
        		}
        	}
        	
            path = basepath+dirName+"/"+path;
            File file = new File(path);
            if(file.exists()){
                //如果有,读取,写出
                writeFile(file,response.getOutputStream());
            }else{
                response.setContentType("text/html; charset=utf-8");
                response.getWriter().write("对不起,没有找到图片");
            }
        }catch (Exception e){
            logger.info("读取会诊单图片失败,文件地址为"+path,e);
            response.setContentType("text/html; charset=utf-8");
            try{
                response.getWriter().write("读取图片失败");
            }catch (Exception e2){
            }
        }
    }
    
    
    private void writeFile(File file,OutputStream out) throws Exception{
        FileInputStream fs = null;
        try{
           fs = new FileInputStream(file);
           //循环写入输出流
           byte[] b = new byte[1024];
           int len;
           while ((len = fs.read(b)) > 0) {
               out.write(b, 0, len);
           }
       }catch (Exception e){
            throw new RuntimeException("流写出错误");
       }finally {
            close(fs,out);
        }
    }

    private void close(InputStream is , OutputStream os){
        if(is!=null){
            try{
                is.close();
                is = null;
            }catch (Exception e2){
                logger.error("关闭文件流失败");
            }
        }
        if(os!=null){
            try{
                os.close();
                os = null;
            }catch (Exception e2){
                logger.error("关闭文件流失败");
            }
        }
    }
    
    
    /**
     * 获取检查片子地址根据编号
     */
    @RequestMapping("getyxzlbyid")
    @ResponseBody
    public String getPById(HttpServletRequest request,@RequestParam Map<String,Object> map){
    	Map<String,Object> remap = new HashMap<String,Object>();
        try{
        	ChannelUser o = (ChannelUser) request.getSession().getAttribute("loginUser");
            Map<String,Object> m = bussConsultationSingleService.getYXZlById(map);
            remap.put("success", true);
            remap.put("data", m);
        }catch (Exception e){
        	remap.put("message","获取错误");
            remap.put("success", false);
        }
        return JSONObject.toJSONString(remap);
    }
    
    
    /**
     * 取消预约
     * @return
     */
    @ResponseBody
    @RequestMapping("cancelConsultationSingle")
    public String cancelConsultationSingle(Long singleId){
        Map<String,Object> remap = new HashMap<String,Object>();
        remap.put("message","取消预约失败");
        remap.put("success",false);
        try{
            BussConsultationSingle single = bussConsultationSingleService.selectByPrimaryKey(singleId);
            if(single == null || !single.getShenqingzt().equals(BussConsultationSingle.SHENQINGZT_ZHIFUCG)){
                //直接返回
            	remap.put("message","未知的会诊单");
            }
            int i = bussConsultationSingleService.cancelConsultationSingle(single);
            if(i>0){
                //成功
            	remap.put("success",true);
            }
        }catch (Exception e){
        	e.printStackTrace();
        }
        return JSONObject.toJSONString(remap);
    }
}
