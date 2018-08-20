package com.choice.sign.web.controller.external;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.external.InspectCheckItem;
import com.choice.domain.entity.external.InspectInfo;
import com.choice.domain.entity.external.InspectVO;
import com.choice.domain.entity.external.ItemListParam;
import com.choice.domain.entity.external.PatientInfo;
import com.choice.domain.entity.referral.BusDualReferral;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.external.AppointmentCheckService;
import com.choice.domain.service.external.InspectCheckItemService;
import com.choice.domain.service.external.PatientInfoService;
import com.choice.domain.service.referral.BusDualReferralService;
import com.choice.sign.util.Page;
import com.choice.sign.util.UuIdUtil;

/**
 * 预约检查控制层
 * @author 刘璐骞
 *
 */
@Controller
@RequestMapping("/appointmentCheck")
public class AppointmentCheckController {
	
	
	@Autowired
	private AppointmentCheckService checkService;
	
	@Autowired
	private BusDualReferralService busDualReferralService;
	
	@Autowired
	private InspectCheckItemService inspectCheckItemService;
	
	@Autowired
	private PatientInfoService patientInfoService;
	/**
	 * 获取检查大类
	 */
	@RequestMapping(value = "/getCheckClass",produces = "application/json; charset=utf-8")
	public ModelAndView getCheckClass(HttpServletRequest request,String hosCode,PatientInfo patientInfo,String diag,String icd10) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		//获取当前登录用户的信息
		ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
		//根据身份证号判断转诊表中是否有此用户的信息
		PatientInfo patientInfo1 = patientInfoService.selectByPatientIdCard(patientInfo.getPatientIdCard());
		//如果查询不到该身份证信息 就插入该病人信息
		if(patientInfo1 == null) {
			patientInfo.setId(UuIdUtil.getUuid());
			patientInfoService.insertSelective(patientInfo);
		}else {
			//如果查到该病人信息,则修改
			patientInfoService.updateByPatientIdCard(patientInfo);
		}
		
		//根据医院编码获取该医院检查大类列表
        String jsonString = checkService.getCheckClass(hosCode);
        jsonString = "{\"checkClassList\":[{\"checkClassCode\":\"1011\",\"checkClassName\":\"X线摄影\",\"sortId\":\"1\"},{\"checkClassCode\":\"1012\",\"checkClassName\":\"彩超\",\"sortId\":\"1\"},{\"checkClassCode\":\"1013\",\"checkClassName\":\"CT\",\"sortId\":\"1\"}],\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        //放入检查大类列表信息
        map.put("jsonString", jsonString);
        //放入医院代码
        map.put("hosCode", hosCode);
        //将接受到的页面信息带到预约检查项页面
        map.put("patientInfo", patientInfo);
        //放入诊断编码和诊断名称
        map.put("diag", diag);
        map.put("icd10", icd10);
        return new ModelAndView("external/appointmentCheck",map);
	}
	
	/**
	 * 获取检查小类
	 * @return
	 */
	@RequestMapping(value = "/getMiniCheckClass",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getMiniCheckClass(@RequestBody InspectInfo inspectInfo) {
        String jsonString = checkService.getMiniCheckClass(inspectInfo);
        String checkClassCode = inspectInfo.getClassCode();
        switch(checkClassCode) {
        case "1011":
        	jsonString = "{\"miniCheckClassList\":[{\"miniCheckClassCode\":\"1211\",\"miniCheckClassName\":\"胸部\",\"sortId\":\"1\"},{\"miniCheckClassCode\":\"1212\",\"miniCheckClassName\":\"腹部\",\"sortId\":\"1\"},{\"miniCheckClassCode\":\"1213\",\"miniCheckClassName\":\"关节\",\"sortId\":\"1\"}],\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        	break;
        case "1012":
        	jsonString = "{\"miniCheckClassList\":[{\"miniCheckClassCode\":\"1221\",\"miniCheckClassName\":\"胎儿\",\"sortId\":\"1\"},{\"miniCheckClassCode\":\"1222\",\"miniCheckClassName\":\"器官\",\"sortId\":\"1\"}],\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        	break;
        case "1013":
        	jsonString = "{\"miniCheckClassList\":[{\"miniCheckClassCode\":\"1231\",\"miniCheckClassName\":\"普通CT\",\"sortId\":\"1\"},{\"miniCheckClassCode\":\"1232\",\"miniCheckClassName\":\"增强CT\",\"sortId\":\"1\"}],\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        	break;
        }
        
        return jsonString;
	}
	
	/**
	 * 获取检查项
	 * @return
	 */
	@RequestMapping(value = "/getCheckItem",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCheckItem(@RequestBody InspectInfo inspectInfo) {
        String jsonString = checkService.getCheckItem(inspectInfo);
        String miniClassCode = inspectInfo.getMiniClassCode();
        switch(miniClassCode) {
        case "1211":
        	jsonString = "{\"checkItemList\":[{\"checkItemCode\":\"1311\",\"checkItemName\":\"胸部正位\",\"price\":\"10\",\"sortId\":\"1\"},{\"checkItemCode\":\"1312\",\"checkItemName\":\"胸部正侧位\",\"price\":\"20\",\"sortId\":\"1\"}],\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        	break;
        case "1212":
        	jsonString = "{\"checkItemList\":[{\"checkItemCode\":\"1321\",\"checkItemName\":\"腹部正位\",\"price\":\"10\",\"sortId\":\"1\"},{\"checkItemCode\":\"1322\",\"checkItemName\":\"腹部正侧位\",\"price\":\"20\",\"sortId\":\"1\"}],\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        	break;
        case "1213":
        	jsonString = "{\"checkItemList\":[{\"checkItemCode\":\"1331\",\"checkItemName\":\"关节正位\",\"price\":\"10\",\"sortId\":\"1\"},{\"checkItemCode\":\"1332\",\"checkItemName\":\"关节正侧位\",\"price\":\"20\",\"sortId\":\"1\"}],\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        	break;
        case "1221":
        	jsonString = "{\"checkItemList\":[{\"checkItemCode\":\"1341\",\"checkItemName\":\"胎盘成熟度检测\",\"price\":\"10\",\"sortId\":\"1\"},{\"checkItemCode\":\"1342\",\"checkItemName\":\"胎儿系统筛查单胎\",\"price\":\"20\",\"sortId\":\"1\"},{\"checkItemCode\":\"1343\",\"checkItemName\":\"胎儿系统筛查双胎\",\"price\":\"30\",\"sortId\":\"1\"}],\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        	break;
        case "1222":
        	jsonString = "{\"checkItemList\":[{\"checkItemCode\":\"1351\",\"checkItemName\":\"颅脑\",\"price\":\"10\",\"sortId\":\"1\"},{\"checkItemCode\":\"1352\",\"checkItemName\":\"心脏\",\"price\":\"20\",\"sortId\":\"1\"},{\"checkItemCode\":\"1353\",\"checkItemName\":\"乳腺\",\"price\":\"30\",\"sortId\":\"1\"}],\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        	break;
        case "1231":
        	jsonString = "{\"checkItemList\":[{\"checkItemCode\":\"1361\",\"checkItemName\":\"头部\",\"price\":\"10\",\"sortId\":\"1\"},{\"checkItemCode\":\"1362\",\"checkItemName\":\"胸部\",\"price\":\"20\",\"sortId\":\"1\"},{\"checkItemCode\":\"1363\",\"checkItemName\":\"腹部\",\"price\":\"30\",\"sortId\":\"1\"}],\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        	break;
        case "1232":
        	jsonString = "{\"checkItemList\":[{\"checkItemCode\":\"1371\",\"checkItemName\":\"头部增强\",\"price\":\"10\",\"sortId\":\"1\"},{\"checkItemCode\":\"1372\",\"checkItemName\":\"胸部增强\",\"price\":\"20\",\"sortId\":\"1\"},{\"checkItemCode\":\"1373\",\"checkItemName\":\"腹部增强\",\"price\":\"30\",\"sortId\":\"1\"}],\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        	break;
        }
        
        return jsonString;
	}
	
	/**
	 * 保存预约检查单
	 * @param inspectInfo
	 * @return
	 */
	@RequestMapping(value = "/saveCheckList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String,Object> saveCheckList(HttpServletRequest request,@RequestBody InspectVO inspectVO){
		ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
		String uuid = UuIdUtil.getUuid();
		InspectInfo inspectInfo = inspectVO.getInspectInfo();
		inspectInfo.setId(uuid);
		
		//给预约检查单设置添加人
		inspectInfo.setAddOper(user.getTrueName());
		//保存预约检查单
		Map<String,Object> map = checkService.saveCheckList(request,inspectInfo);
		//获取检查项列表
		List<InspectCheckItem> inspectCheckItems = inspectVO.getItemList();
		//循环遍历检查项列表,向每一个检查项对象赋值此项所对应的检查单编号(uuid)
		for(int i=0;i<inspectCheckItems.size();i++) {
			inspectCheckItems.get(i).setInspectInfoId(uuid);
			//给检查项设置添加时间
			inspectCheckItems.get(i).setAddTime(new Timestamp(new Date().getTime()).toString());
		}
		//保存检查项列表
		inspectCheckItemService.insertInspectCheckItem(inspectCheckItems);
		//返回页面此检查单总价格
		map.put("cost", inspectInfo.getCost());
		//返回页面此检查单的id
		map.put("uuid", uuid);
		return map;
	}
	
	/**
	 * 根据预约检查单id获取该检查单包含的所有检查项列表
	 * @param uuid
	 * @return
	 */
	@RequestMapping("/getCheckItemsByInfoId")
	@ResponseBody
	public List<InspectCheckItem> getCheckItemsByInfoId(String uuid) {
		List<InspectCheckItem> checkItemList = inspectCheckItemService.getCheckItemByInfoId(uuid);
		return checkItemList;
	}
	
	
	/**
	 * 跳转预约成功页面
	 */
	@RequestMapping("/appointSuccess")
	public ModelAndView appointSuccess(String uuid,String cost) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uuid", uuid);
		map.put("cost", cost);
		return new ModelAndView("external/finish",map);
	}
	
	/**
	 * 查看预约检查列表
	 * @param pageNum
	 * @param request
	 * @param orgIdFrom
	 * @param orgIdTo
	 * @return
	 */
	@RequestMapping(value="/checkList", produces = "application/json; charset=utf-8")
	public ModelAndView checkList(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum,
			HttpServletRequest request,ItemListParam itemListParam) {
		ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
		itemListParam.setHosId(user.getHosId());
		itemListParam.setType("check");
		Page page = checkService.getCheckItemList(itemListParam,pageNum);
		ModelMap modelMap = new ModelMap();
		modelMap.put("page", page);
		modelMap.put("hosId", user.getHosId());
		modelMap.put("itemListParam", itemListParam);
		return new ModelAndView("/external/checkList", modelMap);
	};
	
	/**
	 * 跳转到选择排班界面
	 */
	@RequestMapping("gotoCheckSchedule")
	public ModelAndView gotoCheckSchedule(ModelMap modelMap, String hosCode,String classCode) {
		modelMap.put("hosCode", hosCode);
		modelMap.put("classCode", classCode);
		return new ModelAndView("/external/checkSchedule",modelMap);
	}
	
	/**
	 * 获取检查项排班数据
	 * @param hosCode 医院编码
	 * @param itemCode 检查项编码
	 * @return
	 */
	@RequestMapping(value = "/getCheckSchedule",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCheckSchedule(String hosCode,String classCode,String date) {
		String checkScheduleList = "{\r\n" + 
				"	\"scheduleList\": [{\r\n" + 
				"			\"workDate\": \"2018-01-10\",\r\n" + 
				"			\"checkTimeStart\": \"2018-01-10 08:00:00\",\r\n" + 
				"			\"checkTimeEnd\": \"2018-01-10 09:00:00\"\r\n" + 
				"		}, {\r\n" + 
				"			\"workDate\": \"2018-01-10\",\r\n" + 
				"			\"checkTimeStart\": \"2018-01-10 09:00:00\",\r\n" + 
				"			\"checkTimeEnd\": \"2018-01-10 10:00:00\"\r\n" + 
				"		}, {\r\n" + 
				"			\"workDate\": \"2018-01-10\",\r\n" + 
				"			\"checkTimeStart\": \"2018-01-10 10:00:00\",\r\n" + 
				"			\"checkTimeEnd\": \"2018-01-10 11:00:00\"\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"workDate\": \"2018-01-10\",\r\n" + 
				"			\"checkTimeStart\": \"2018-01-10 11:00:00\",\r\n" + 
				"			\"checkTimeEnd\": \"2018-01-10 12:00:00\"\r\n" + 
				"		}\r\n" + 
				"	],\r\n" + 
				"	\"resultCode\": \"0\",\r\n" + 
				"	\"errorMsg\": \"错误原因\"\r\n" + 
				"}";
		return checkScheduleList;
	}
	
	
	/**
	 * 获取检查大类测试数据
	 */
	/*public String getCheckClassData(String hosCode) {
		switch(hosCode) {
			case:
		}
	}*/
	
}
