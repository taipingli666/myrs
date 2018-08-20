package com.choice.sign.web.controller.contract;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.common.CommonArea;
import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.common.CommonPackage;
import com.choice.domain.entity.contract.BusinessContract;
import com.choice.domain.entity.dictionary.CommonDictionaryValue;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.repository.contract.BusinessContractDao;
import com.choice.domain.repository.dictionary.DictMangerDao;
import com.choice.domain.service.common.CommonAreaService;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.common.CommonPackageService;
import com.choice.domain.service.contract.ContMangerService;
import com.choice.domain.service.dictionary.DictValueService;
import com.choice.domain.service.user.UserService;
import com.choice.sign.exception.ParameterException;
import com.choice.sign.web.controller.base.BaseController;

@Controller
@RequestMapping("/contract")
public class ContractController extends BaseController<T>{
	
	@Resource
	private ContMangerService contMangerService;
	@Resource
	private DictMangerDao dictMangerDao;
	@Resource
	private DictValueService dictValueService;
	@Resource 
	private CommonHospitalService commonHospitalService;
	@Resource
	private UserService userService;
	@Resource
	private CommonPackageService commonPackageService;
	@Resource
	private CommonAreaService commonAreaService;
	@Resource
	private BusinessContractDao contractDao;
	
	/**	
	 * 进入合同list		
	 * @param pageNumber
	 * @param contract
	 * @param modelmap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/display",produces="text/html;charset=UTF-8")
	public ModelAndView display(@RequestParam(value="pageNumber",defaultValue="1") Integer pageNumber,
			HttpServletRequest request,BusinessContract contract,ModelMap modelmap)
			throws Exception {
		
		//当前登录人
		ChannelUser nowUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		modelmap.put("login_user", nowUser);
		//获取当前登录人所属团队的Id
		Integer teamId = nowUser.getTeamId();
		//如果teamId为0,说明是管理员用户,就查询所有的签约病人
		if(nowUser.getTeamId() == 0) {
			page = contMangerService.getPage(pageNumber,contract);
		}else {
			//teamId不为0,查询当前登录人所属团队下的病人
			page = contMangerService.getPageByTeamId(pageNumber,contract,teamId);
		}
		modelmap.put("page", page);
		//获取当前登录人所属医疗机构下的团队关联的所有专家列表
		List<ChannelUser> users = userService.getExpectListByHosId(Integer.parseInt(nowUser.getHosId()));
		modelmap.put("users", users);
		//医保类型List	
		List<CommonDictionaryValue> personTypes = dictValueService.selectValuesByCode(10003); 
		modelmap.put("insuranceTypes", personTypes);
		//签约年度
		List<String> signyears = contMangerService.getAllSignyear();
		modelmap.put("signyears", signyears);
		modelmap.put("contract", contract);
		
		return new ModelAndView("contract/contractManage",modelmap);
	}
	
	/**
	 * 操作跳转
	 * @param operationType
	 * @param contractId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/operatedUI",produces="text/html;charset=UTF-8")
	public ModelAndView edit(HttpServletRequest request,@RequestParam(value="operationType") String operationType,
			@RequestParam(value="contractId") int contractId,ModelMap model){
		String resUrl = "contract/contract";
		//首字母大写
		char[] chars = operationType.toCharArray();
		chars[0] -= 32;
		resUrl += String.valueOf(chars);
		//返回合同Contract
		BusinessContract contract = contMangerService.selectByPrimaryKey(contractId);
		model.put("info", contract);
		//通过contract中的签约人id(addPerson)查询该签约人信息
		ChannelUser channelUser = userService.getById(Integer.parseInt(contract.getAddPerson()));
		model.put("channelUser", channelUser);
		//医保类型List
		List<CommonDictionaryValue> insuranceTypes = dictValueService.selectValuesByCode(10003); 
		model.put("insuranceTypes", insuranceTypes);
		//人群分类List	
		List<CommonDictionaryValue> crowdTypes = dictValueService.selectValuesByCode(10001); 
		model.put("crowdTypes", crowdTypes);
		//居民类型List	
		List<CommonDictionaryValue> personTypes = dictValueService.selectValuesByCode(10005); 
		model.put("personTypes", personTypes);
		//签约状态List
		List<CommonDictionaryValue> signTypes  = dictValueService.selectValuesByCode(10006); 
		model.put("signTypes", signTypes );
		//签约来源List
		List<CommonDictionaryValue> sources = dictValueService.selectValuesByCode(10000); 
		model.put("sources", sources);
		//获取常见疾病
		List<CommonDictionaryValue> diseaseTypes = dictValueService.selectValuesByCode(10002); 
		model.put("diseaseTypes", diseaseTypes);
		//获取医疗机构列表
		List<CommonHospital> hospitals = commonHospitalService.getAllHospital(null);
		model.put("hospitals", hospitals);
		//获取套餐
		List<CommonPackage> commonPackages = commonPackageService.selectByAll(); 
		model.put("commonPackages", commonPackages);
		//获取区县信息
		List<CommonArea> commonAreas = commonAreaService.getCommonAreaByParentCode("370100000000");
		model.put("commonAreas", commonAreas);
		//获取乡镇信息
		List<CommonArea> commonTownAreas = commonAreaService.getCommonAreaByParentCode(contract.getCounty());
		model.put("commonTownAreas", commonTownAreas);
		//获取村街信息
		List<CommonArea> commonVillageAreas = commonAreaService.getCommonAreaByParentCode(contract.getTown());
		model.put("commonVillageAreas", commonVillageAreas);
		//获取专家医生列表
		List<ChannelUser> users = userService.getExpertList();
		model.put("users", users);
		//获取当前登录人
		ChannelUser loginUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		//获取当前合同所对应的签约医生
		ChannelUser expert = (ChannelUser)userService.selectByPrimaryKey(Integer.parseInt(contract.getExpertId()));
		model.put("expert", expert);
		//获取当前登录人机构下的除当前合同签约医生之外的其他专家
		List<ChannelUser> otherExperts = userService.getOtherExpertList(Integer.parseInt(loginUser.getHosId()),Integer.parseInt(contract.getExpertId()));
		model.put("otherExperts", otherExperts);
		return new ModelAndView(resUrl,model);
	}
	
	/**
	 * excel导出
	 * @param request
	 * @param response
	 * @param contract
	 * @param model
	 * @return
	 */
	@ResponseBody 
	@RequestMapping(value="/export",produces="text/html;charset=UTF-8")
	public Map<String, Object> export(HttpServletRequest request,HttpServletResponse response,BusinessContract contract,ModelMap model){
		Map<String, Object> result = this.getResult();
		//创建HSSFWorkbook对象(excel的文档对象)  
	    HSSFWorkbook wb = new HSSFWorkbook();  
		//建立新的sheet对象（excel的表单）  
		HSSFSheet sheet=wb.createSheet("合同列表");  
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个  
		HSSFRow row1=sheet.createRow(0);  
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个  
		HSSFCell cell=row1.createCell(0);  
		      //设置单元格内容  
		cell.setCellValue("合同一览表");  
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,14));  
		//在sheet里创建第二行  
		HSSFRow row2 = sheet.createRow(1);      
	    //创建单元格并设置单元格内容  
		//姓名,身份证号,手机号,医保类型,人群分类,签约状态,签约时间,签约医生,签约人,签约年度,签约开始时间,签约结束时间,签约机构,是否续约
	    row2.createCell(0).setCellValue("序列号");
        row2.createCell(1).setCellValue("姓名");
        row2.createCell(2).setCellValue("身份证号");
        row2.createCell(3).setCellValue("手机号");
        row2.createCell(4).setCellValue("医保类型");
        row2.createCell(5).setCellValue("人群分类");
        row2.createCell(6).setCellValue("签约状态");
        row2.createCell(7).setCellValue("签约时间");
        row2.createCell(8).setCellValue("签约医生");
        row2.createCell(9).setCellValue("签约人");
        row2.createCell(10).setCellValue("签约年度");
        row2.createCell(11).setCellValue("签约开始时间");
        row2.createCell(12).setCellValue("签约结束时间");
        row2.createCell(13).setCellValue("签约机构");
        row2.createCell(14).setCellValue("是否续约");
        List<Map> exportList = contMangerService.ExportContents(contract);
        Map map;
        for(int i=0;i<exportList.size();i++){
        	 row2 = sheet.createRow(i+2);  
	         map = exportList.get(i);
	    	 row2.createCell(0).setCellValue(i+1);
	    	 row2.createCell(1).setCellValue((String)map.get("trueName"));
	         row2.createCell(2).setCellValue((String)map.get("card"));
	         row2.createCell(3).setCellValue((String)map.get("mobile"));
	         row2.createCell(4).setCellValue((String)map.get("insuranceType"));
	         row2.createCell(5).setCellValue((String)map.get("crowdType"));
	         row2.createCell(6).setCellValue(map.get("status")+"");
	         row2.createCell(7).setCellValue(map.get("addTime")+"");
	         row2.createCell(8).setCellValue(map.get("userName")+"");
	         row2.createCell(9).setCellValue(map.get("addPerson")+"");
	         row2.createCell(10).setCellValue(map.get("signYear")+"");
	         row2.createCell(11).setCellValue(map.get("startTime")+"");
	         row2.createCell(12).setCellValue(map.get("endTime")+"");
	         row2.createCell(13).setCellValue(map.get("signType")+"");
        }
		//输出Excel文件     
	    try {
			OutputStream output=response.getOutputStream();  
			response.reset();  
			response.setHeader("Content-disposition", "attachment; filename=contractsList.xls");  
			response.setContentType("application/msexcel");          
			wb.write(output);  
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
			return result;
		}  
	    result.put("success", true);
		return result;
	}
	
	/**
	 * 所有的修改操作。包括 修改，续约，转介，解约
	 * @param contract
	 * @return
	 */
	@RequestMapping("operated")
	@ResponseBody
	public Map<String, Object> operated(BusinessContract contract){
		Map<String, Object> result = this.getResult();
		try {
			contMangerService.updateByPrimaryKeySelective(contract);
			result.put("success", true);
		} catch (Exception e) {
			result.put("message", "操作错误");
			if(e instanceof ParameterException){
				result.put("message", e.getMessage());
			}
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	/**
	 * 签约登记
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/contractRegist",produces="text/html;charset=UTF-8")
	public ModelAndView contractRegist(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()))+1;
		model.put("year", year);
		
		//获取居民类型
		List<CommonDictionaryValue> personTypes = dictValueService.selectValuesByCode(10005); 
		model.put("personTypes", personTypes);
		//获取医保类型
		List<CommonDictionaryValue> insuranceTypes = dictValueService.selectValuesByCode(10003); 
		model.put("insuranceTypes", insuranceTypes);
		//获取来源
		List<CommonDictionaryValue> sources = dictValueService.selectValuesByCode(10000); 
		model.put("sources", sources);
		//获取签约类型
		List<CommonDictionaryValue> signTypes = dictValueService.selectValuesByCode(10006); 
		model.put("signTypes", signTypes);
		//获取人群分类
		List<CommonDictionaryValue> crowdTypes = dictValueService.selectValuesByCode(10001); 
		model.put("crowdTypes", crowdTypes);
		//获取常见疾病
		List<CommonDictionaryValue> diseaseTypes = dictValueService.selectValuesByCode(10002); 
		model.put("diseaseTypes", diseaseTypes);
		//获取套餐
		List<CommonPackage> commonPackages = commonPackageService.selectByAll(); 
		model.put("commonPackages", commonPackages);
		//获取区县信息
		List<CommonArea> commonAreas = commonAreaService.getCommonAreaByParentCode("370100000000");
		model.put("commonAreas", commonAreas);
		
		//当前登录人
		ChannelUser nowUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		model.put("login_user", nowUser);
		
		//获取当前登录人所属医疗机构
		CommonHospital hospital = commonHospitalService.getById(Integer.parseInt(nowUser.getHosId()));
		//List<CommonHospital> hospital = commonHospitalService.getAllHospital();
		model.put("hospital", hospital);
		
		//获取当前登录人所属团队的团队长
		ChannelUser user = userService.getExpertByTeamId(nowUser.getTeamId());
		model.put("user", user);
		
		return new ModelAndView("contract/contractRegist",model);
	}
	
	/**
	 * 签约登记信息确认
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/contractRegistCheck",produces="text/html;charset=UTF-8")
	public ModelAndView contractRegistCheck(ModelMap model){
		String contractNo = String.format("%08d", (int) (Math.random() * 100000000));
		model.put("contractNo", contractNo);
		return new ModelAndView("contract/contractRegistCheck",model);
	}
	
	/**
	 * 合约签订
	 * @param contract
	 * @return
	 */
	@RequestMapping("contractSigned")
	@ResponseBody
	public Map<String, Object> contractSigned(BusinessContract contract){
		Map<String, Object> result = this.getResult();
		try {
			Map<String,Object> map = contMangerService.contractSigned(contract);
			if("1".equals(map.get("status"))){
				result.put("success", false);
				result.put("message", map.get("message"));
			}else{
				result.put("success", true);
			}
			
		} catch (Exception e) {
			result.put("message", "操作错误");
			if(e instanceof ParameterException){
				result.put("message", e.getMessage());
			}
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	/**
	 * 点击续约,获取续约id并返回合同签约年份
	 */
	@RequestMapping("getSignYear")
	public ModelAndView getSignYear(Integer contractId,ModelMap modelMap) {
		Integer signYear = Integer.parseInt(contMangerService.selectByPrimaryKey(contractId).getSignYear());
		modelMap.put("contractId", contractId);
		modelMap.put("signYear", signYear);
		modelMap.put("renewYear", signYear+1);
		return new ModelAndView("contract/contractRenew");
	}
	
	/**
	 * 根据接收到的合同id获取到合同信息,并新添加一条记录
	 */
	@RequestMapping("renew")
	@ResponseBody
	public Map<String,Object> renew(HttpServletRequest request,Integer contractId){
		ChannelUser channelUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		Map<String, Object> result = contMangerService.renew(contractId, channelUser);
		return result;
	}
	
	
	/**
	 * 根据父节点获取地区
	 * @param parentCode
	 * @return
	 */
	@RequestMapping("getAreaByParent")
	@ResponseBody
	public Map<String, Object> getAreaByParent(String parentCode){
		Map<String, Object> result = this.getResult();
		try{
			List<CommonArea> commonAreas = commonAreaService.getCommonAreaByParentCode(parentCode);
			result.put("success", true);
			result.put("message", commonAreas);
		} catch(Exception e){
			result.put("message", "操作错误");
			if(e instanceof ParameterException){
				result.put("message", e.getMessage());
			}
			e.printStackTrace();
			return result;
		}
		
		return result;
	}
	
	private Map<String, Object> getResult(){
		Map<String, Object> map = new HashMap();
		map.put("success", false);
		return map;
	}
	
}
