package com.choice.sign.web.controller.external;

import com.alibaba.fastjson.JSONObject;
import com.choice.domain.entity.external.ScheduleTemplate;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.external.ScheduleTemplateService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 排班模板
 * @author lingli
 */
@RestController
@RequestMapping(value = "/scheduleTemplate")
public class ScheduleTemplateController {

    @Resource
    private ScheduleTemplateService scheduleTemplateService;

    /**
     * 模板页
     * @param modelmap
     * @param request
     * @return
     */
    @RequestMapping(value="/getScheduleTemplatePage")
    public ModelAndView getScheduleTemplatePage( ModelMap modelmap, HttpServletRequest request) {

        ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
        ModelMap modelMap = new ModelMap();
        modelMap.put("hosId", user.getHosId());
        return new ModelAndView("/external/register/scheduletemplatelist", modelMap);
    }

    /**
     * 获取模板数据
     * @param scheduleTemplate
     * @param modelmap
     * @param request
     * @return
     */
    @RequestMapping(value="/getscheduleTemplateList")
    @ResponseBody
    public List<ScheduleTemplate> getScheduleTemplateList(ScheduleTemplate scheduleTemplate, ModelMap modelmap, HttpServletRequest request) {

        return scheduleTemplateService.selectSetList(scheduleTemplate);
    }

    /**
     * 列表插入
     * @param scheduleTemplateList
     * @param modelmap
     * @param request
     * @return
     */
    @RequestMapping(value = "/insertList", produces = "application/text; charset=utf-8")
    @ResponseBody
    public String insertList(@RequestBody List<ScheduleTemplate> scheduleTemplateList, ModelMap modelmap, HttpServletRequest request) {
        JSONObject data = new JSONObject();
        try{
            ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
            int result = scheduleTemplateService.insertList(scheduleTemplateList,user);
            if(result == 1){
                data.put("resultCode","0");
                data.put("errorMsg","保存成功！");
            }else{
                data.put("resultCode","0");
                data.put("errorMsg","保存失败！");
            }

        }catch (Exception e){
            e.printStackTrace();
            data.put("resultCode","-1");
            data.put("errorMsg","保存出错！");
        }
        return data.toJSONString();
    }

    /**
     * 生成排班
     * @param buildDate
     * @param request
     * @return
     */
    @RequestMapping(value="/buildSchedule", produces = "application/text; charset=utf-8")
    @ResponseBody
    public String buildSchedule(@DateTimeFormat(pattern="yyyy-MM-dd") Date buildDate, HttpServletRequest request) {
        JSONObject data = new JSONObject();
        ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
        if(scheduleTemplateService.buildScheduleInfo(buildDate,user.getHosId())){
            data.put("resultCode","0");
            data.put("errorMsg","生成成功！");
        }else{
            data.put("resultCode","-1");
            data.put("errorMsg","生成失败！");
        }
        return data.toJSONString();
    }

}
