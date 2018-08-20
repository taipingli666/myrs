package com.choice.sign.web.controller.reportform;

import com.choice.domain.entity.external.DualReferralInfo;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.external.DualReferralInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 双向转诊报表（门诊/住院）
 */
@Controller
@RequestMapping(value = "/dualReport")
public class DualReportController {

    @Resource
    private DualReferralInfoService dualReferralInfoService;

    /**
     * 转入统计
     * @return
     */
    @RequestMapping(value = "/dualInCount" ,produces="text/html;charset=UTF-8")
    public ModelAndView dualInCount(String endTime, String startTime, HttpServletRequest request) {

        ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
        List<Map<String, Object>> mapList = dualReferralInfoService.dualIn(endTime, startTime, user.getHosId());
        ModelMap modelMap  = new ModelMap();
        modelMap.put("list", mapList);
        modelMap.put("endTime", endTime);
        modelMap.put("startTime", startTime);
        modelMap.put("dualType", 1);
        return new ModelAndView("/report/dualOutCount", modelMap);
    }

    /**
     * 转出统计
     * @return
     */
    @RequestMapping(value = "/dualOutCount" ,produces="text/html;charset=UTF-8")
    public ModelAndView dualOutCount(String endTime, String startTime, HttpServletRequest request) {

        ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
        List<Map<String, Object>> mapList = dualReferralInfoService.dualOut(endTime, startTime, user.getHosId());
        ModelMap modelMap  = new ModelMap();
        modelMap.put("list", mapList);
        modelMap.put("endTime", endTime);
        modelMap.put("startTime", startTime);
        modelMap.put("dualType", 2);
        return new ModelAndView("/report/dualOutCount", modelMap);
    }
}
