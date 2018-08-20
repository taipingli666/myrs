package com.choice.sign.web.controller.external;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.external.InspectCheckItem;
import com.choice.domain.entity.external.InspectInfo;
import com.choice.domain.entity.external.ItemListParam;
import com.choice.domain.entity.external.PatientInfo;
import com.choice.domain.entity.referral.BusDualReferral;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.external.AppointmentAssayService;
import com.choice.domain.service.external.InspectCheckItemService;
import com.choice.domain.service.external.PatientInfoService;
import com.choice.domain.service.referral.BusDualReferralService;
import com.choice.sign.util.Page;
import com.choice.sign.util.UuIdUtil;

import net.sf.json.JSONObject;

/**
 * 预约化验接口
 * @author litp
 * 2018-1-10 15:25:38
 */
@Controller
@RequestMapping("/assay")
public class AppointmentAssayController {

	@Autowired
	private AppointmentAssayService appointmentAssayService;
	
	@Autowired
	private CommonHospitalService commonHospitalService;
	
	@Autowired
	private BusDualReferralService busDualReferralService;
	
	@Autowired
	private InspectCheckItemService InspectCheckItemService;
	
	@Autowired
	private PatientInfoService patientInfoService;
	/**
	 * 化验大类
	 * @param inspectInfo
	 * @return
	 */
	@RequestMapping(value = "/assayClass",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAssayClass(InspectInfo inspectInfo) {
        String data = appointmentAssayService.getAssayClass(inspectInfo.getHosCode());
        data= "{\"assayClassList\":[{\"assayClassCode\":\"1000\",\"assayClassName\":\"化验科\",\"sortId\":\"1\"}],"
        		+ "\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        return data;
	};
	
	/**
	 * 化验小类
	 * @param inspectInfo
	 * @return
	 */
	@RequestMapping(value = "miniAssayClass",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getMiniAssayClass(InspectInfo inspectInfo) {
        String data = appointmentAssayService.getMiniAssayClass(inspectInfo);
        switch (inspectInfo.getClassCode()) {
			case "1000" :
				data = "{\"miniAssayClassList\":[{\"miniAssayClassCode\":\"1323\",\"miniAssayClassName\":\"化验\",\"sortId\":\"1\"},"
						+ "{\"miniAssayClassCode\":\"1324\",\"miniAssayClassName\":\"病理\",\"sortId\":\"2\"},"
						+ "{\"miniAssayClassCode\":\"1325\",\"miniAssayClassName\":\"遗传\",\"sortId\":\"3\"}],"
						+ "\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
				break;
			/*case "1224" :
				data = "{\"miniAssayClassList\":[{\"miniAssayClassCode\":\"1331\",\"miniAssayClassName\":\"妇科\",\"sortId\":\"1\"},"
						+ "{\"miniAssayClassCode\":\"1332\",\"miniAssayClassName\":\"产科\",\"sortId\":\"2\"}],"
						+ "\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
				break;
			case "1225" :
				data = "{\"miniAssayClassList\":[{\"miniAssayClassCode\":\"1333\",\"miniAssayClassName\":\"神经外科\",\"sortId\":\"1\"},"
						+ "{\"miniAssayClassCode\":\"1334\",\"miniAssayClassName\":\"心胸外科\",\"sortId\":\"2\"},"
						+ "{\"miniAssayClassCode\":\"1335\",\"miniAssayClassName\":\"泌尿外科\",\"sortId\":\"3\"}],"
						+ "\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
				break;*/

		}
        return data;
	};
	
	/**
	 * 化验项目
	 * @param inspectInfo
	 * @return
	 */
	@RequestMapping(value = "assayItem",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAssayItem(InspectInfo inspectInfo) {
        String data = appointmentAssayService.getAssayItem(inspectInfo);
        switch (inspectInfo.getMiniClassCode()) {
		case "1323": //化验类 - 血液
			if (inspectInfo.getSampleCode().equals("1523")) {
				data = "{\"assayItemList\":[{\"assayItemCode\":\"1224\",\"assayItemName\":\"EB病毒\",\"price\":\"20.6\",\"sortId\":\"1\"},"
		        		+ "{\"assayItemCode\":\"1225\",\"assayItemName\":\"血型（ABO）\",\"price\":\"77.7\",\"sortId\":\"2\"},"
		        		+ "{\"assayItemCode\":\"1227\",\"assayItemName\":\"C反应蛋白\",\"price\":\"50.0\",\"sortId\":\"4\"},"
		        		+ "{\"assayItemCode\":\"1226\",\"assayItemName\":\"凝血四项\",\"price\":\"32.5\",\"sortId\":\"3\"}],"
		        		+ "\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
			//化验 -尿液	
			} else if (inspectInfo.getSampleCode().equals("1525")) {
				data = "{\"assayItemList\":[{\"assayItemCode\":\"1228\",\"assayItemName\":\"尿常规\",\"price\":\"20.6\",\"sortId\":\"1\"},"
		        		+ "{\"assayItemCode\":\"1221\",\"assayItemName\":\"24h尿蛋白\",\"price\":\"77.7\",\"sortId\":\"2\"},"
		        		+ "{\"assayItemCode\":\"1222\",\"assayItemName\":\"尿培养\",\"price\":\"50.0\",\"sortId\":\"4\"},"
		        		+ "{\"assayItemCode\":\"1223\",\"assayItemName\":\"尿碘\",\"price\":\"32.5\",\"sortId\":\"3\"}],"
		        		+ "\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
			}else {
				data = "{\"assayItemList\":[],"
		        		+ "\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
			}
			break;
		case "1324": //病理 - 血液
			if (inspectInfo.getSampleCode().equals("1523")) {
				data = "{\"assayItemList\":[{\"assayItemCode\":\"1220\",\"assayItemName\":\"微量元素\",\"price\":\"20.6\",\"sortId\":\"1\"}],"
		        		+ "\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
			//病理 -尿液	
			} /*else if (inspectInfo.getSampleCode().equals("1525")) {
				data = "{\"assayItemList\":[{\"assayItemCode\":\"1228\",\"assayItemName\":\"尿常规\",\"price\":\"20.6\",\"sortId\":\"1\"},"
		        		+ "{\"assayItemCode\":\"1221\",\"assayItemName\":\"24h尿蛋白\",\"price\":\"77.7\",\"sortId\":\"2\"},"
		        		+ "{\"assayItemCode\":\"1222\",\"assayItemName\":\"尿培养\",\"price\":\"50.0\",\"sortId\":\"4\"},"
		        		+ "{\"assayItemCode\":\"1223\",\"assayItemName\":\"尿碘\",\"price\":\"32.5\",\"sortId\":\"3\"}],"
		        		+ "\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
			}*/else {
				data = "{\"assayItemList\":[],"
		        		+ "\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
			}
			break;
		case "1325": //遗传 - 血液
			if (inspectInfo.getSampleCode().equals("1523")) {
				data = "{\"assayItemList\":[{\"assayItemCode\":\"1219\",\"assayItemName\":\"尿动脉加压采血\",\"price\":\"20.6\",\"sortId\":\"1\"},"
		        		+ "{\"assayItemCode\":\"1218\",\"assayItemName\":\"产前基因检测\",\"price\":\"77.7\",\"sortId\":\"2\"},"
		        		+ "{\"assayItemCode\":\"1217\",\"assayItemName\":\"全血铅测定\",\"price\":\"50.0\",\"sortId\":\"4\"},"
		        		+ "{\"assayItemCode\":\"1216\",\"assayItemName\":\"甲胎蛋白\",\"price\":\"32.5\",\"sortId\":\"3\"}],"
		        		+ "\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
			//遗传 -粘膜	
			} else if (inspectInfo.getSampleCode().equals("1524")) {
				data = "{\"assayItemList\":[{\"assayItemCode\":\"1228\",\"assayItemName\":\"叶酸代谢障碍分子医学检验\",\"price\":\"20.6\",\"sortId\":\"1\"},"
		        		+ "{\"assayItemCode\":\"1215\",\"assayItemName\":\"儿童铅中毒分子医学检验\",\"price\":\"77.7\",\"sortId\":\"2\"},"
		        		+ "{\"assayItemCode\":\"1214\",\"assayItemName\":\"体重管理分子医学检验\",\"price\":\"50.0\",\"sortId\":\"4\"},"
		        		+ "{\"assayItemCode\":\"1213\",\"assayItemName\":\"子宫癌分子医学检验\",\"price\":\"32.5\",\"sortId\":\"3\"}],"
		        		+ "\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
			}else {
				data = "{\"assayItemList\":[],"
		        		+ "\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
			}
			break;

		default:
			data = "{\"assayItemList\":[],"
	        		+ "\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
			break;
		}
        
        return data;
	};
	
	/**
	 * 化验样本
	 * @param inspectInfo
	 * @return
	 */
	@RequestMapping(value = "assaySample",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAssaySample(InspectInfo inspectInfo) {
        String data = appointmentAssayService.getAssaySample(inspectInfo);
        data = "{\"assaySampleList\":[{\"assaySampleCode\":\"1525\",\"assaySampleName\":\"尿液\",\"sortId\":\"1\"},"
        		+ "{\"assaySampleCode\":\"1524\",\"assaySampleName\":\"粘膜\",\"sortId\":\"2\"},"
        		+ "{\"assaySampleCode\":\"1523\",\"assaySampleName\":\"血液\",\"sortId\":\"3\"}],"
        		+ "\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        return data;
	};
	
	/**
	 * 查看化验列表
	 * @param pageNum
	 * @param request
	 * @param itemListParam
	 * @return
	 */
	@RequestMapping(value="/assayList", produces = "application/json; charset=utf-8")
	public ModelAndView assayList(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum,
			HttpServletRequest request, ItemListParam itemListParam) {
		ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
		itemListParam.setHosId(user.getHosId());
		itemListParam.setType("assay");
		Page page = appointmentAssayService.assayList(pageNum, itemListParam);
		ModelMap modelMap = new ModelMap();
		modelMap.put("page", page);
		modelMap.put("itemListParam", itemListParam);
		return new ModelAndView("/external/assayList", modelMap);
	};
	
	/**
	 * 预约化验基本信息页
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("/baseInfo")
	public ModelAndView assay(ModelMap map, HttpServletRequest request) {
		
		//当前登录人
		ChannelUser nowUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		map.put("login_user", nowUser);
		//医院列表
		List<CommonHospital> list = commonHospitalService.selectHospitalList(nowUser.getHosId());
		map.put("hoslist", list);
		return new ModelAndView("/external/assayBaseInfo", map);
	}
	
	/**
	 * 保存基本信息并进入选择化验项目界面
	 * @param request
	 * @param patientInfo
	 * @param hosCode
	 * @param icd10
	 * @param diag
	 * @return
	 */
	@RequestMapping("/assayNext")
	public ModelAndView addInfo(HttpServletRequest request, PatientInfo patientInfo, String hosCode, String icd10, String diag) {
		
		ChannelUser nowUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		if(patientInfoService.selectByPatientIdCard(patientInfo.getPatientIdCard()) != null) {
			patientInfoService.updateByPatientIdCard(patientInfo);
		}else {
			patientInfo.setId(UuIdUtil.getUuid());
			patientInfoService.insertSelective(patientInfo);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("patientInfo", patientInfo);
		map.put("hosCode", hosCode);
		map.put("icd10", icd10);
		map.put("diag", diag);
		return new ModelAndView("/external/appointmentAssay", map);
	}
	
	/**
	 * 预约化验提交
	 * @param request
	 * @param inspectInfo
	 * @param itemCode
	 * @param itemName
	 * @param itemPrice
	 * @return
	 */
	@RequestMapping("/assayCommit")
	@ResponseBody
	public Map<String, Object> assayCommit(HttpServletRequest request, InspectInfo inspectInfo, String itemCode, String itemName, String itemPrice) {
		ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
		inspectInfo.setId(UuIdUtil.getUuid());
		inspectInfo.setAddOper(user.getUserName());
		inspectInfo.setAddTime(new Timestamp(new Date().getTime()));
		JSONObject jsonObject = JSONObject.fromObject(inspectInfo);
		Map<String, Object> map = new HashMap<String, Object>();  
		int result = appointmentAssayService.saveAssayInfo(inspectInfo, request); 
		//如果保存预约信息成功
		if (result != 0) {   
			map.put("resultCode", "0");
			map.put("message", "预约成功!");
			map.put("cost", inspectInfo.getCost());
			map.put("appointmentTime", inspectInfo.getAppointmentTime().toString());
			//保存到检查项表   
			List<InspectCheckItem> inspectCheckItems = new ArrayList<InspectCheckItem>();
			String itemCodeArr[] = itemCode.split(",");
			String itemNameArr[] = itemName.split(",");
			String itemPriceArr[] = itemPrice.split(",");
			for(int i = 0; i<itemCodeArr.length; i++) {
				InspectCheckItem inspectCheckItem = new InspectCheckItem();
				inspectCheckItem.setInspectInfoId(inspectInfo.getId());
				inspectCheckItem.setPrice(Double.parseDouble(itemPriceArr[i]));
				inspectCheckItem.setItemCode(itemCodeArr[i]);
				inspectCheckItem.setItemName(itemNameArr[i]);
				inspectCheckItem.setAddTime(new Timestamp(new Date().getTime()).toString());
				inspectCheckItem.setStartTime(new Timestamp(new Date().getTime()).toString());
				inspectCheckItem.setEndTime(new Timestamp(new Date().getTime()).toString());
				inspectCheckItems.add(inspectCheckItem);
			}
			InspectCheckItemService.insertInspectCheckItem(inspectCheckItems);
		}else {
			map.put("resultCode", "1");
			map.put("message", "预约失败!");
		}
		return map;
	}
	
	/**
	 * 跳转完成界面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/assayFinish")
	public ModelAndView assayFinish(ModelMap modelMap,String time, String cost, String item, String price) {
		modelMap.put("item", item);
		modelMap.put("price", price);
		modelMap.put("cost", cost);
		modelMap.put("time", time);
		return new ModelAndView("/external/assayFinish", modelMap);
	}
}
