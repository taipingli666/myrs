/**
 * 
 */
/**
 * @author Paul.Pan
 *
 */
package com.choice.sign.web.controller.common;

import com.choice.sign.web.controller.user.AuthPassport;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.common.CommonArea;
import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.dictionary.CommonDictionaryValue;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.common.CommonAreaService;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.common.impl.CommonHospitalServiceImpl;
import com.choice.domain.service.dictionary.DictValueService;
import com.choice.sign.util.Page;
import com.choice.sign.web.controller.base.BaseController;
/**
 * common_area相关处理类
 * @author stone
 *
 */
@Controller
@RequestMapping("/commonarea")
public class CommonAreaController extends BaseController{
	@Autowired
	CommonAreaService commonAreaService;
	@Autowired
	DictValueService dictValueService;
	@Autowired
	CommonHospitalService hospitalService;
	//进入字典
	@AuthPassport
	@RequestMapping(value="/show",produces="text/html;charset=UTF-8")
	public ModelAndView display(@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="size",defaultValue="10")Integer size,String code,String parentCode ,String areaName,ModelMap modelmap)throws Exception {
	Page<CommonArea> info = commonAreaService.getPage(page,size,areaName,parentCode);
	Map<String,Object> dictionaryMap = new HashMap<String,Object>();
	//获取行政区域级别代码字典
	//List<CommonDictionaryValue> dictionaryList = dictValueService.selectValuesByCode(10008);
	modelmap.put("page", info);
	modelmap.put("areaName", areaName);
	//modelmap.put("dictionary", dictionaryList);
	return new ModelAndView("common/commonAreaList",modelmap);
	}
	/**
	 * 获取iframe界面数据
	 * @param page
	 * @param size
	 * @param parentCode
	 * @param areaName
	 * @param modelmap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/show2",produces="text/html;charset=UTF-8")
	public ModelAndView display2(@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="size",defaultValue="10")Integer size,String parentCode ,String areaName,ModelMap modelmap)throws Exception {
	Page<CommonArea> info = commonAreaService.getPage(page,size,areaName,parentCode);
	//获取行政区域级别代码字典
    List<CommonDictionaryValue> dictionaryList = dictValueService.selectValuesByCode(10008);
	modelmap.put("page", info);
	modelmap.put("areaName", areaName);
	modelmap.put("parentCode", parentCode);
	modelmap.put("dictionary", dictionaryList);
	return new ModelAndView("common/commonAreaList2",modelmap);
	}
	
	/**
	 * 打开新增界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("operatedui")
	public ModelAndView operatedUI(Integer id,String parentCode,ModelMap model){
		try {
			CommonArea commonArea = new CommonArea();
			if(id != null && id != 0){
				//修改界面
				commonArea = commonAreaService.getInfo(id);
			}else{
				commonArea.setParentCode(parentCode);
			}
			//获取行政区域级别代码字典
		    List<CommonDictionaryValue> dictionaryList = dictValueService.selectValuesByCode(10008);
		    model.put("dictionary", dictionaryList);
			model.addAttribute("info", commonArea);
		} catch (Exception e) {
			e.printStackTrace();
			//???日志记录
		}

		List<CommonArea> commonAreaList =  commonAreaService.getCommonAreaByParentCode(null);
		model.put("areaList", commonAreaList);
		return new ModelAndView("common/commonAreaOperate");
	}
	/**
	 * 获取树信息
	 * @param id
	 * @return
	 */
	@RequestMapping("getTree.do")
	@ResponseBody
	public Map<String,Object> getTreeList(HttpServletRequest request,String id){
		Map<String,Object> retMap = new HashMap<String,Object>();
		List<Map<String,Object>> commonAreaList = new ArrayList<Map<String,Object>>();
		retMap = this.getResult();
		CommonHospital commonHospital = null;
		String idValue = "";
		ChannelUser nowUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		try {
			//处理树结构
			if(id.equals("-1")){
				commonHospital = hospitalService.getById(Integer.parseInt(nowUser.getHosId()));
				idValue = commonHospital.getAreaCode();
				commonAreaList = commonAreaService.getCommonAreaByAreaCode(idValue);
				commonAreaList.get(0).put("isParent", "true");
			}else{
				commonAreaList = commonAreaService.getCommonAreaAll(id);
				for (int i = 0; i < commonAreaList.size(); i++) {
					String code = String.valueOf(commonAreaList.get(i).get("id")).trim();
					List<CommonArea> sonList = commonAreaService.getCommonAreaByParentCode(code);
					if(sonList.size()>0){
						commonAreaList.get(i).put("isParent", "true");
					}
				}
			}
			retMap.put("success",true);
			retMap.put("treeData", commonAreaList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
	
	/**
	 * 根据父节点获取子节点信息
	 * @param parentCode
	 * @return
	 */
	@RequestMapping("getCommonAreaList")
	@ResponseBody
	public Map<String,Object> getCommonAreaByParentCode(String parentCode){
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap = this.getResult();
		try {
			List<CommonArea> commonAreaList = commonAreaService.getCommonAreaByParentCode(parentCode);
			retMap.put("areaList",commonAreaList);
			retMap.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
	/**
	 * 增改
	 */
	@RequestMapping("operated")
	@ResponseBody
	public Map<String, Object> operated(HttpServletRequest request,CommonArea commonArea){
		Map<String, Object> result = this.getResult();
		try {
			commonAreaService.operated(request,commonArea);
			result.put("success", true);
			result.put("parentCode",commonArea.getParentCode());
		} catch (Exception e) {
			//???日志输出
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("deletecommonareas")
	@ResponseBody
	public Map<String,Object> deleteCommonAreas(String ids){
		Map<String,Object> retMap = this.getResult();
		try {
			commonAreaService.deleteCommonAreas(ids.split(","));
			retMap.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
	
	private Map<String, Object> getResult(){
		Map<String, Object> map = new HashMap();
		map.put("success", false);
		return map;
	}
}