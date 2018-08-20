package com.choice.sign.web.controller.external;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.choice.domain.entity.common.CommonDoctorTeam;
import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.dictionary.Department;
import com.choice.domain.entity.external.ItemListParam;
import com.choice.domain.entity.external.PatientInfo;
import com.choice.domain.entity.external.RegisterInfo;
import com.choice.domain.entity.external.ScheduleInfo;
import com.choice.domain.entity.referral.BusDualReferral;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.dictionary.DepartmentService;
import com.choice.domain.service.external.PatientInfoService;
import com.choice.domain.service.external.RegisterService;
import com.choice.domain.service.external.ScheduleInfoService;
import com.choice.domain.service.user.UserService;
import com.choice.sign.util.DateUtil;
import com.choice.sign.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 预约挂号
 * Created by administer on 2018-01-11.
 * @author lingli
 */
@RestController
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Resource
    private ScheduleInfoService scheduleInfoService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private CommonHospitalService commonHospitalService;

    @Resource
    private UserService userService;

    /**
     * 预约信息展示
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value="/register",produces="text/html;charset=UTF-8")
    public ModelAndView register(String id,ModelMap modelMap, HttpServletRequest request) {
        ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
        RegisterInfo registerInfo = registerService.selectByPrimaryKey(id);
        modelMap.put("hosId",user.getHosId());
        modelMap.put("info",registerInfo);
        return new ModelAndView("external/register/register");
    }

    @RequestMapping(value = "/patientInfo",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String patientInfo(String hosCode){
        String data;
        data="";
        return data;
    }

    /**
     * 获取科室信息
     * @return
     */
    @RequestMapping(value = "/getDeptList",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getDeptList( @RequestParam(value="hosCode",defaultValue="")String hosCode){
        String data = null;
        try{
//            Department department = new Department();
//            department.setDepartmentid(Integer.valueOf(hosCode));
//            List<Department> departmentList = departmentService.getDepartmentList(department);
//            JSONArray deptArray = new JSONArray();
//            for(int i = 0 ; i< departmentList.size();i++){
//                JSONObject dept = new JSONObject();
//                dept.put("deptCode",departmentList.get(i).getDepartmentid());
//                dept.put("deptName",departmentList.get(i).getDepartmentname());
//                dept.put("deptSpell",null);
//                dept.put("sortId",null);
//                dept.put("className",departmentList.get(i).getDepartmentname());
//                deptArray.add(dept);
//            }
//            data.put("resultCode","0");
//            data.put("errorMsg","成功！");
//            data.put("deptList",deptArray);
        data = registerService.getDeptList(hosCode);

        }catch (Exception e){
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("resultCode","-1");
            result.put("errorMsg","失败！");
            data = result.toJSONString();
        }

//        return data.toJSONString();
        return data;
    }


    /**
     * 获取排班数据
     * @return
     */
    @RequestMapping(value = "/getScheduleList",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getScheduleList(String hosCode,String deptCode){

        String data = null;
        try{
//            ScheduleInfo scheduleInfo = new ScheduleInfo();
//            scheduleInfo.setHosCode(hosCode);
//            List<ScheduleInfo> scheduleInfoList = scheduleInfoService.selectByScheduleInfo(scheduleInfo,new Date(),null);
//            data.put("resultCode","0");
//            data.put("errorMsg","成功！");
//            data.put("scheduleList",scheduleInfoList);
            data = registerService.getScheduleList(hosCode, deptCode);

        }catch (Exception e){
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("resultCode","-1");
            result.put("errorMsg","失败！");
            data = result.toJSONString();
        }
        return data;
    }


    /**
     * 获取号源
     * @param registerInfo
     * @return
     */
    @RequestMapping(value = "/getRegisterSource",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getRegisterSource(@RequestBody RegisterInfo registerInfo){
//        String data = registerService.getRegisterSource(registerInfo);
        JSONObject data = new JSONObject();
        try{
            ScheduleInfo scheduleInfo = new ScheduleInfo();
            scheduleInfo.setId(Long.parseLong(registerInfo.getId()));
            List source = scheduleInfoService.getSchedulingSource(scheduleInfo);
            JSONObject json  = (JSONObject) JSON.toJSON(source.get(0));
            json.put("sourceList",json.get("list"));
            data.put("resultCode","0");
            data.put("errorMsg","成功！");
            data.put("registerSource",json);
        }catch (Exception e){
            e.printStackTrace();
            data.put("resultCode","-1");
            data.put("errorMsg","失败！");
        }


//        data = "{\"registerSource\":{\"deptCode\":\"1222\",\"deptName\":\"眼科\",\"doctorCode\":\"001\",\"doctorName\":\"医生1\",\"workDate\":\"2018-01-10\",\"workPeriod\":\"am\",\"workTimeStart\":\"2018-01-10 08:00\",\"workTimeEnd\":\"2018-01-10 17:30\",\"registeredType\":\"dd\",\"registeredCost\":\"2.0000\",\"sourceList\":[{\"scheduleCode\":\"123456\",\"sequenceNumber\":\"1\",\"visitStart\":\"2018-01-10 08:00:00\",\"visitEnd\":\"2018-01-10 08:05:00\"},{\"scheduleCode\":\"123456\",\"sequenceNumber\":\"2\",\"visitStart\":\"2018-01-10 08:05:00\",\"visitEnd\":\"2018-01-10 08:10:00\"}]},\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        return data.toJSONString();
    }

    /**
     * 预约挂号(访问接口)
     * @param registerInfo
     * @return
     */
    @RequestMapping(value = "/registerReservation",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String registerReservation(@RequestBody RegisterInfo registerInfo, HttpServletRequest request){
        ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
        //his业务取不同的值
        if("hisRegister".equals(user.getRemark())){
            registerInfo.setOperator(user.getUserName());
        }else{
            registerInfo.setOperator(user.getUserId().toString());
        }

        registerInfo.setRegisterTime(new Date());
        registerInfo.setOperHosCode(user.getHosId());
        String data = registerService.registerReservation(registerInfo);
        return data;
    }

    /**
     * 取消预约(接口)
     * @param registerInfo
     * @return
     */
    @RequestMapping(value = "/cancelRegisterReservation",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String cancelRegisterReservation(@RequestBody RegisterInfo registerInfo){

        String data = registerService.cancelRegisterReservation(registerInfo);
        return data;
    }

    /**
     * 查询挂号状态
     * @param registerInfo
     * @return
     */
    @RequestMapping(value = "/selectRegisterReservation",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String selectRegisterReservation(RegisterInfo registerInfo){

        String data = registerService.selectRegisterReservation(registerInfo);
        data = "{\"registerReservation\":{\"TransactionCode\":\"123456789\",\"scheduleId\":\"123456\",\"sequenceNumber\":\"1\",\"visitDate\":\"2018-01-10\",\"visitStart\":\"2018-01-10 08:00:00\",\"visitEnd\":\"2018-01-10 08:05:00\"},\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        return data;
    }


    /**
     * 预约挂号提交保存
     * @param registerInfo
     * @return
     */
    @RequestMapping(value = "/saveRegisterInfo",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String saveRegisterInfo(@RequestBody RegisterInfo registerInfo,HttpServletRequest request){
        JSONObject data = new JSONObject();
        try{
            ChannelUser nowUser = (ChannelUser)request.getSession().getAttribute("loginUser");
            registerInfo.setOperator(nowUser.getUserId().toString());
            registerInfo.setOperHosCode(nowUser.getHosId());
            data.put("resultCode","0");
            data.put("errorMsg","预约挂号成功！");
            data.put("registerReservation",registerService.saveRegisterInfo(registerInfo));
//            data = "{\"registerReservation\":{\"TransactionCode\":\"123456789\",\"scheduleId\":\"123456\",\"sequenceNumber\":\"1\",\"visitDate\":\"2018-01-10\",\"visitStart\":\"2018-01-10 08:00:00\",\"visitEnd\":\"2018-01-10 08:05:00\"},\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        }catch (Exception e){
            e.printStackTrace();
            data.put("resultCode","-1");
            data.put("errorMsg","失败！");
        }

        return data.toJSONString();
    }

    /**
     * 挂号列表
     * @param page
     * @param size
     * @param registerInfo
     * @param startDate
     * @param endDate
     * @param modelmap
     * @param request
     * @return
     */
    @RequestMapping(value="/getRegisterInfoList", produces = "application/json; charset=utf-8")
    public ModelAndView getRegisterInfoList(@RequestParam(value="page",defaultValue="1") Integer page,
                                            @RequestParam(value="size",defaultValue="10")Integer size,
                                            @RequestParam(value="startDate",defaultValue="") String startDate,
                                            @RequestParam(value="endDate",defaultValue="") String endDate,
                                            @RequestParam(value="refType",defaultValue="") String refType,
                                            RegisterInfo registerInfo,ModelMap modelmap, HttpServletRequest request) {
        ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
        registerInfo.setOperHosCode(user.getHosId());
        Page<RegisterInfo> registerInfoList = registerService.getRegisterInfoList(page,size,registerInfo,
                DateUtil.stringToDate(startDate,"yyyy-MM-dd"),DateUtil.stringToDate(endDate,"yyyy-MM-dd"), refType);
        ModelMap modelMap = new ModelMap();
        modelMap.put("page", page);
        modelMap.put("hosId", user.getHosId());
        modelMap.put("registerInfo", registerInfo);
        modelMap.put("startDate", startDate);
        modelMap.put("endDate", endDate);
        modelMap.put("refType", refType);
        modelMap.put("registerInfoList", registerInfoList);
        return new ModelAndView("/external/register/registerlist", modelMap);
    }


    /**
     * 修改信息
     * @param registerInfo
     * @return
     */
    @RequestMapping(value = "/changeRegisterInfo",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String changeRegisterInfo(@RequestBody RegisterInfo registerInfo){
        JSONObject data = new JSONObject();
        try{
            int i = registerService.updateByPrimaryKeySelective(registerInfo);
            if( i == 1){
                data.put("resultCode","0");
                data.put("errorMsg","操作成功！");
            }else{
                data.put("resultCode","-1");
                data.put("errorMsg","保存失败！");
            }

        }catch (Exception e){
            e.printStackTrace();
            data.put("resultCode","-1");
            data.put("errorMsg","操作失败！");
        }
        return data.toJSONString();
    }


    //挂号门诊打印
    @RequestMapping(value = "/registerPrint",produces = "application/json; charset=utf-8")
    public ModelAndView registerPrint(String id){
        RegisterInfo registerInfo = registerService.selectByPrimaryKey(id);
        ModelMap modelMap = new ModelMap();
        CommonHospital commonHospital = commonHospitalService.getById(Integer.valueOf(registerInfo.getOperHosCode()));
        //发起方医院名称
        modelMap.put("operaHosName", commonHospital.getHosName());
        ChannelUser user = userService.getById(Integer.valueOf(registerInfo.getOperator()));
        //发起医生所属部门
        String operaDeptName = departmentService.getInfo(Long.parseLong(user.getDeptCode())).getDepartmentname();
        modelMap.put("operaDeptName", operaDeptName);
        //发起方医生名
        modelMap.put("operatorName", user.getTrueName());
        //医生电话
        modelMap.put("operatorTel", user.getTel());
        //预约医院
        modelMap.put("hosName", commonHospitalService.getById(Integer.valueOf(registerInfo.getHosCode())).getHosName());
        //门诊转诊信息
        modelMap.put("registerInfo", registerInfo);
        return new ModelAndView("external/register/registerPrint", modelMap);
    }

}
