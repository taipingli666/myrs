package com.choice.sign.web.controller.external;

import com.choice.domain.entity.external.PatientInfo;
import com.choice.domain.service.external.PatientInfoService;
import com.choice.domain.vo.ApiResult;
import com.choice.sign.util.Page;
import com.choice.sign.util.UuIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 患者信息
 * Created by administer on 2018-01-30.
 * @author lingli
 */
@RestController
@RequestMapping(value = "/patientInfo")
public class PatientInfoController {

    @Autowired
    private PatientInfoService patientInfoService;

    /**
     * 根据身份证查询
     * @param patientIdCard
     * @return
     */
    @RequestMapping(value = "/selectByPatientIdCard",produces = "application/json; charset=utf-8")
    @ResponseBody
    public ApiResult selectByPatientIdCard(String patientIdCard){
        ApiResult apiResult = new ApiResult();
        try{
            PatientInfo patientInfo = patientInfoService.selectByPatientIdCard(patientIdCard);
            apiResult.setSuccess(true);
            apiResult.setMessage("获取成功！");
            apiResult.setData(patientInfo);
        }catch (Exception e){
            e.printStackTrace();
            apiResult.setSuccess(false);
            apiResult.setMessage("获取失败！");
        }

        return apiResult;
    }

    /**
     * 根据身份证更新
     * @param patientInfo
     * @return
     */
    @RequestMapping(value = "/updateByPatientIdCard",produces = "application/json; charset=utf-8")
    @ResponseBody
    public ApiResult updateByPatientIdCard(PatientInfo patientInfo){
        ApiResult apiResult = new ApiResult();
        try{
            if(patientInfoService.selectByPatientIdCard(patientInfo.getPatientIdCard()) != null){
                patientInfoService.updateByPatientIdCard(patientInfo);
            }else if(patientInfo.getPatientIdCard()!= null && !patientInfo.getPatientIdCard().equals("")){
                patientInfo.setId(UuIdUtil.getUuid());
                patientInfoService.insertSelective(patientInfo);
            }else{
                apiResult.setSuccess(false);
                apiResult.setMessage("更新失败！");
                return apiResult;
            }
            apiResult.setSuccess(true);
            apiResult.setMessage("更新成功！");
        }catch (Exception e){
            e.printStackTrace();
            apiResult.setSuccess(false);
            apiResult.setMessage("更新失败！");
        }

        return apiResult;
    }

    //患者信息列表
    @RequestMapping(value = "/patients" ,produces = "application/json; charset=utf-8")
    public ModelAndView patients(
            @RequestParam(value="pageNumber",defaultValue="1") Integer page,
            @RequestParam(value="size",defaultValue="10")Integer size,
            String content){
        ModelMap modelMap = new ModelMap();
        Page patientList = patientInfoService.patientInfoList(page, size, content);
        modelMap.put("content", content);
        modelMap.put("page", patientList);
        return new ModelAndView("/external/patientList",modelMap);
    }
}
