package com.choice.sign.web.controller.external;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.choice.sign.exception.ParameterException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.dictionary.CommonDictionaryValue;
import com.choice.domain.entity.dictionary.Department;
import com.choice.domain.entity.external.ScheduleInfo;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.repository.user.ChannelRoleDao;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.dictionary.DepartmentService;
import com.choice.domain.service.dictionary.DictValueService;
import com.choice.domain.service.external.ScheduleInfoService;
import com.choice.sign.util.DateJsonValueProcessor;
import com.choice.sign.util.DateUtil;
import com.choice.sign.util.Page;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 排班信息
 * Created by administer on 2018-02-06.
 * @author lingli
 */
@RestController
@RequestMapping(value = "/scheduleInfo")
public class ScheduleInfoController {
    @Resource
    private ScheduleInfoService scheduleInfoService;
    @Resource
    private CommonHospitalService commonhospitalService;
    @Resource
    private DictValueService dictValueService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private ChannelRoleDao channelRoleDao;
    
    /**
     * 分页查询排班信息
     * @param page
     * @param size
     * @param startDate
     * @param endDate
     * @param scheduleInfo
     * @param modelmap
     * @param request
     * @return
     */
    @RequestMapping(value="/getScheduleInfoList")
    public ModelAndView getScheduleInfoList(@RequestParam(value="page",defaultValue="1") Integer page,
                                            @RequestParam(value="size",defaultValue="10")Integer size,
                                            @RequestParam(value="startDate",defaultValue="") String startDate,
                                            @RequestParam(value="endDate",defaultValue="") String endDate,
                                            ScheduleInfo scheduleInfo, ModelMap modelmap, HttpServletRequest request) {
    	
        
    	ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
    	Page<ScheduleInfo> registerInfoList = null;
    	//获取当前登录人的id
    	Integer userId = user.getUserId();
    	//根据用户id查询角色id
    	Integer roleId = channelRoleDao.getRoleByUserId(userId).getRoleId();
    	//判断当前登录用户所属角色等级
    	if(roleId == 1 || roleId == 2) {
    		//说明当前登录人属于超级管理员或区级管理员，则查询所有排班信息
    		registerInfoList = scheduleInfoService.getScheduleInfoList(page,size,scheduleInfo,
    				DateUtil.stringToDate(startDate,"yyyy-MM-dd"),DateUtil.stringToDate(endDate,"yyyy-MM-dd"));
    	}else {
    		//说明当前登录人为普通管理员，则查询当前登录人所在医院的排班信息
    		scheduleInfo.setHosCode(user.getHosId().toString());
    		registerInfoList = scheduleInfoService.getScheduleInfoList(page,size,scheduleInfo,
    				DateUtil.stringToDate(startDate,"yyyy-MM-dd"),DateUtil.stringToDate(endDate,"yyyy-MM-dd"));
    	}
        ModelMap modelMap = new ModelMap();
        modelMap.put("page", page);
        modelMap.put("hosId", user.getHosId());
        modelMap.put("scheduleInfo", scheduleInfo);
        modelMap.put("startDate", startDate);
        modelMap.put("endDate", endDate);
        modelMap.put("page", registerInfoList);
        return new ModelAndView("/external/register/scheduleinfolist", modelMap);
    }
    
    @RequestMapping(value = "getDataList", produces = "application/text; charset=utf-8")
    @ResponseBody
    public String getDataList(@RequestParam(value="page",defaultValue="1") Integer page,
            @RequestParam(value="size",defaultValue="10")Integer size,
            @RequestParam(value="startDate",defaultValue="") String startDate,
            @RequestParam(value="endDate",defaultValue="") String endDate,
            ScheduleInfo scheduleInfo, ModelMap modelmap, HttpServletRequest request){
    	Page<ScheduleInfo> pager = scheduleInfoService.getScheduleInfoList(page,size,scheduleInfo,
                DateUtil.stringToDate(startDate,"yyyy-MM-dd"),DateUtil.stringToDate(endDate,"yyyy-MM-dd"));
    	JsonValueProcessor jsonProcessor = new DateJsonValueProcessor();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class,
				jsonProcessor);
		// 转换为JSON数据格式
		JSONObject pagerJSON = JSONObject.fromObject(pager, jsonConfig);
		return pagerJSON.toString();
    }


    /**
     * 排版信息
     * @param id
     * @return
     */
    @RequestMapping("scheduleInfo")
    public ModelAndView operatedUI(Long id,HttpServletRequest request){
        ModelMap modelMap = new ModelMap();
        List<CommonDictionaryValue> listap=dictValueService.selectValuesByCode(10010);
		String am = "";
        String pm = "";
        try{
        	Iterator<CommonDictionaryValue> iterator=listap.iterator();
        	while(iterator.hasNext()) {
        		CommonDictionaryValue cdv=iterator.next();
        		if(cdv.getCode()==100101) {
        			am=cdv.getWord();
        		}
        		if(cdv.getCode()==100102) {
        			pm=cdv.getWord();
        		}
        	}
            if(am == null || am.equals("")){
                am = "08:00-12:00";
            }
            if(pm == null || pm.equals("")){
                pm = "14:00-17:00";
            }
        }catch (Exception e){
            am = "08:00-12:00";
            pm = "14:00-17:00";
        }
        modelMap.addAttribute("AM",am);
        modelMap.addAttribute("PM",pm);
        ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
        try {
            CommonHospital hospital = commonhospitalService.getById(Integer.parseInt(user.getHosId()));
            ScheduleInfo scheduleInfo = new ScheduleInfo();

            if(id != null && id != 0){
                scheduleInfo = scheduleInfoService.selectByPrimaryKey(id);
            }
            modelMap.put("hosId", hospital.getHosId());
            modelMap.put("hosName", hospital.getHosName());
            modelMap.put("scheduleInfo", scheduleInfo);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ModelAndView("/external/register/scheduleinfo",modelMap);
    }

    /**
     * 保存排班
     * @param scheduleInfo
     * @return
     */
    @RequestMapping("saveScheduleInfo")
    @ResponseBody
    public Map<String, Object> saveScheduleInfo(ScheduleInfo scheduleInfo){
        Map<String, Object> result = new HashMap();
        try {

            result.put("success", true);
            result.put("data", scheduleInfoService.saveScheduleInfo(scheduleInfo));
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "保存失败");
            if(e instanceof ParameterException){
                result.put("message", e.getMessage());
            }
            e.printStackTrace();
            return result;
        }
        return result;
    }


    /**
     * 批量删除
     */
    @RequestMapping("deleteSchedule")
    @ResponseBody
    public Map<String, Object> deleteSchedule(String ids){
        Map<String, Object> result = new HashMap();
        try {

            result.put("success", true);
            result.put("message", scheduleInfoService.deleteSchedule(ids.split(",")));
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", "删除失败");
            //日志记录
            return result;
        }
        return result;
    }
}
