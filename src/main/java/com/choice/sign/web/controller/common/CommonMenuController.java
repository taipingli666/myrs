package com.choice.sign.web.controller.common;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.common.CommonMenu;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.common.CommonMenuService;
import com.choice.sign.util.Page;
import com.choice.sign.web.controller.base.BaseController;
import com.choice.sign.web.controller.user.AuthPassport;

@Controller
@RequestMapping("/commonmenu")
public class CommonMenuController extends BaseController{
	
	@Resource
	CommonMenuService commonMenuService;
	
	//进入栏目列表
	@AuthPassport
	@RequestMapping(value="/show",produces="text/html;charset=UTF-8")
	public ModelAndView show(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="contents",defaultValue="")String contents,@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,CommonMenu menu,ModelMap modelmap)
			throws Exception {
		Page<CommonMenu> info = commonMenuService.getPage(pageNumber,pageSize,menu,URLDecoder.decode(contents, "UTF-8"));
		modelmap.put("page", info);
		modelmap.put("contents", contents);
		return new ModelAndView("system/menu");
	}
	@RequestMapping(value="/propareAdd",produces="text/html;charset=UTF-8")
	public ModelAndView propareAdd(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="operationType",defaultValue="")String operationType,
			@RequestParam(value="id",defaultValue="")String id,CommonMenu menu,ModelMap modelmap){
		
		List<CommonMenu> fristList = commonMenuService.getFristMenus();
		modelmap.put("fristList", fristList);
		modelmap.put("operationType", operationType);
		modelmap.put("menuId", id);
		if("update".equals(operationType)){
			CommonMenu editmenu = new CommonMenu();
			editmenu = commonMenuService.getMenuById(Integer.valueOf(id));
			modelmap.put("editmenu", editmenu);
		}
		return new ModelAndView("system/add_menu",modelmap);
		
	}
	
	/**
	 * 增改
	 */
	@RequestMapping("save")
	@ResponseBody
	public Map<String, Object> save(
			@RequestParam(value="operationType",defaultValue="")String operationType,CommonMenu commonMenu){
		Map<String, Object> result = new HashMap();
		if(commonMenu.getParentId() == 0){
			commonMenu.setIsLeaf(0);
		}else{
			commonMenu.setIsLeaf(1);
		}
		try {
			if("add".equals(operationType)){
				commonMenuService.insertMenu(commonMenu);
				result.put("success", true);
			}else{
				commonMenu.setEditPerson(0);//暂时先写成0，后面用真是用户id
				commonMenuService.updateMenu(commonMenu);
				result.put("success", true);
			}
			
		} catch (Exception e) {
			//???日志输出
		}
		return result;
	}
	/**
	 * 删除
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Map<String, Object> delete(String ids){
		Map<String, Object> result = new HashMap();
		try {
			commonMenuService.delMenu(ids.split(","));
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			//日志记录???
			return result;
		}
		return result;
	}
}
