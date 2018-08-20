package com.choice.sign.web.controller.registration;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.Document;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.common.CommonScheduling;
import com.choice.domain.service.common.CommonSchedulingService;
import com.choice.domain.vo.ApiResult;
import com.choice.domain.vo.HaoYuanVo1;
import com.choice.domain.vo.HaoYuanVo3;
import com.choice.sign.util.DateUtil;
//import com.sun.tools.internal.ws.util.xml.XmlUtil;

import freemarker.template.utility.StringUtil;

/**
 * 预约挂号的控制类
 * Created by duhuo on 2017/7/19.
 */
@RestController
@RequestMapping(value = "/registration")
public class BussRegistrationController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private CommonSchedulingService commonSchedulingService;
    /**
     * 预约挂号界面
     * @return
     */
    @RequestMapping("/show")
    public ModelAndView registration(){
    	return new ModelAndView("registration/appointmentRegister");
    }
    
    /**
     * 传入医院机构编号，获取医院内的科室
     * @param orgCode
     * @return
     */
    @RequestMapping(value = "/getDeptlist" ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ApiResult getDeptlist(@RequestParam(value = "orgCode", defaultValue = "0") String orgCode)  {
        ApiResult apiResult = new ApiResult();
        apiResult.setSuccess(false);
        try{
        	//获取所有科室
        }catch (Exception e){
            logger.info("加载科室出错:",e);
            return apiResult;
        }
        return apiResult;
    }

    /**
     * 根据科室查询医生排班,按今天后多少天
     */
    @RequestMapping(value = "/doctorList",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ApiResult getDoctorByKeShi(CommonScheduling scheduling){
        ApiResult apiResult = new ApiResult();
        apiResult.setSuccess(false);
        if(scheduling==null||scheduling.equals("")){
            return apiResult;
        }
        if(scheduling.getJigoubh()==null||scheduling.getJigoubh().equals("")||scheduling.getKeshibh()==null||scheduling.getKeshibh().equals("")){
            return apiResult;
        }
        scheduling.setZuozhenrq(DateUtil.getAfterDay(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),6));
        List list = commonSchedulingService.getPaiBanByAfterDay(scheduling);
        if(list!=null&&list.size()>0){
            apiResult.setSuccess(true);
            apiResult.setData(list);
        }else{
            apiResult.setMessage("该科室目前没有排班");
        }
        return apiResult;
    }
    /**
     * 根据排班日期
     * @param scheduling
     * @return
     */
    @RequestMapping("getPaiBanByDate")
    @ResponseBody
    public ApiResult getPaiBanByDate(CommonScheduling scheduling){
        ApiResult apiResult = new ApiResult();
        apiResult.setSuccess(false);
        if(scheduling==null){
            return apiResult;
        }
        if(scheduling.getJigoubh()==null||scheduling.getJigoubh().equals("")||scheduling.getKeshibh()==null||scheduling.getKeshibh().equals("")){
            return apiResult;
        }
        if(scheduling.getZuozhenrq()!=null && !"".equals(scheduling.getZuozhenrq())){
            if(scheduling.getZuozhenrq().indexOf(",")!=-1){
                String[] split = scheduling.getZuozhenrq().split(",");
                System.out.println(split.length);
                scheduling.setZuozhenrq(split[0]);
                scheduling.setMenzhensdbm(split[1]);
            }
        }
        List list = commonSchedulingService.getPaiBan(scheduling);
        if(list!=null&&list.size()>0){
            apiResult.setSuccess(true);
            apiResult.setData(list);
        }
        return apiResult;
    }
    /**
     * 根据排班id 获取号源
     * @param
     * @return
     */
    @RequestMapping("getHaoYuan")
    @ResponseBody
    public ApiResult getHaoYuan(CommonScheduling commonScheduling){
        ApiResult apiResult = new ApiResult();
        apiResult.setSuccess(false);
        //先根据院内排班抓取对象
        CommonScheduling cs = commonSchedulingService.getPaiBanById(commonScheduling);
        if(cs!=null){
           //获取号源
        }
        return apiResult;
    }
    @RequestMapping("checkYiYuanId")
    @ResponseBody
    public ApiResult checkYiYuanId(String huanzhexm,String kahao,String jigoubh){
        ApiResult apiResult = new ApiResult();
        apiResult.setSuccess(false);
        try {
            //检验患者id
        }catch (Exception e){
            apiResult.setMessage("抱歉,服务器连接异常");
        }
        return apiResult;
    }
    @RequestMapping(value = "yuyYue",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ApiResult yuyYue(HaoYuanVo3 haoYuanVo3,HttpServletRequest request){
        ApiResult apiResult = new ApiResult();
        try{
            apiResult.setSuccess(false);
            //预约方法
        }catch (Exception e){
            apiResult.setMessage("未知的错误");
        }
        return apiResult;
    }
}
