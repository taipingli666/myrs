package com.choice.sign.web.controller.dictionary;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.dictionary.CommonDictionary;
import com.choice.domain.entity.dictionary.CommonDictionaryValue;
import com.choice.domain.service.dictionary.DictMangerService;
import com.choice.domain.service.dictionary.DictValueService;
import com.choice.sign.web.controller.base.BaseController;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController extends BaseController<T>{
	
	@Resource
	private DictMangerService dictMangerService;
	@Resource
	private DictValueService dictValueService;
	
	/**
	 * 分页进入字典展示
	 * @param pageNumber
	 * @param contents
	 * @param modelmap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/display",produces="text/html;charset=UTF-8")
	public ModelAndView display(@RequestParam(value="pageNumber",defaultValue="1") Integer pageNumber,
			@RequestParam(value="contents",defaultValue="")String contents,ModelMap modelmap)
			throws Exception {
		contents = URLDecoder.decode(contents, "UTF-8");
		page = dictMangerService.getPage(pageNumber,contents);
		modelmap.put("page", page);
		modelmap.put("contents", contents);
		return new ModelAndView("dictionary/dictionaryList",modelmap);
	}
	
	/**
	 * 进入字典操作页面
	 * @param pageNumber
	 * @param operationType
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/operating",produces="text/html;charset=UTF-8")
	public ModelAndView operating(@RequestParam(value="pageNumber",defaultValue="1") Integer pageNumber,
			@RequestParam(value="operationType",defaultValue="")String operationType,
			@RequestParam(value="id",defaultValue="1")int id,ModelMap model){
		model.put("pageNumber", pageNumber);
		model.put("operationType", operationType);
		if("update".equals(operationType)){
			CommonDictionary info = dictMangerService.getInfo(id);
			model.put("info",info);
			List<CommonDictionaryValue> values = dictValueService.selectValuesByCode(Integer.valueOf(info.getCode()));
			model.put("values",values);
			model.put("dictionaryid",id);
		}
		return new ModelAndView("dictionary/dictionaryOperate",model);
	}
	
	/**
	 * 字典增改操作
	 * @param request
	 * @param response
	 * @param dictionary
	 * @return
	 * @throws Exception
	 */
	@ResponseBody 
	@RequestMapping("/operated")
	public Map<String, Object> operated(CommonDictionary dictionary,
			@RequestParam(value="codes")String codes,@RequestParam(value="operationType")String operationType)
			throws Exception {
		Map<String, Object> result = this.getResult();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String nowTime = df.format(new Date());
		try {
			if("update".equals(operationType)){
				dictionary.setEditPerson("110");
				dictionary.setEditTime(nowTime);
				dictMangerService.updateDict(dictionary,codes);
			}else{
				dictionary.setIsDelete("0");
				dictionary.setAddPerson("120");
				dictionary.setAddTime(nowTime);
				dictMangerService.insertDict(dictionary,codes);
			}
			result.put("success", true);
		} catch (Exception e) {
			// 打印日志
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody 
	@RequestMapping("/delete")
	public Map<String, Object> delete(String ids)
			throws Exception {
		Map<String, Object> result = this.getResult();
		try {
			dictMangerService.deleteDicts(ids.substring(0, ids.length()-1));
			result.put("success", true);
		} catch (Exception e) {
			// 打印日志
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	/**
	 * 检验字典code
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody 
	@RequestMapping("/checkCode")
	public String checkCode(String code)
			throws Exception {
		String result = "false";
		try {
			int checkCode = dictMangerService.checkCode(Integer.valueOf(code));
			if(checkCode == 0) result = "true";
		} catch (Exception e) {
			// 打印日志
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
