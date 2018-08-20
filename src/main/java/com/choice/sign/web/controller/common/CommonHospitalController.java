package com.choice.sign.web.controller.common;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.repository.user.ChannelRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.common.CommonArea;
import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.dictionary.CommonDictionaryValue;
import com.choice.domain.service.common.CommonAreaService;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.dictionary.DictValueService;
import com.choice.sign.util.Page;
import com.choice.sign.web.controller.base.BaseController;
import com.github.pagehelper.PageInfo;

/**
 * 处理医院信息
 * 
 * @author zjj
 *
 */
@Controller
@RequestMapping("/commonhospital")
public class CommonHospitalController extends BaseController {
	@Autowired
	CommonAreaService commonAreaService;
	@Autowired
	private CommonHospitalService commonhospitalService;
	@Autowired
	DictValueService dictValueService;
	@Resource
	ChannelRoleDao channelRoleDao;

	/**
	 * 分页展示
	 * 
	 * @param page
	 * @param size
	 * @param user
	 * @param modelmap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/hospital",produces="text/html;charset=UTF-8")
	public ModelAndView hospital(@RequestParam(value="page",defaultValue="1") Integer page,
								@RequestParam(value="size",defaultValue="10")Integer size,CommonHospital hospital,ModelMap modelmap)
			throws Exception {
		Page<CommonHospital> info = commonhospitalService.getPage(page,size,hospital);
		modelmap.put("page", info);
		modelmap.put("hospital", hospital);
		return new ModelAndView("commonhospital/commonhospitalTree",modelmap);
	}
	
	/**
	 * 获取iframe界面数据
	 * @param page
	 * @param size
	 * @param modelmap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list",produces="text/html;charset=UTF-8")
	public ModelAndView list(HttpServletRequest request,@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="size",defaultValue="10")Integer size,String areaCode,String areaPId,Integer areaLevel,CommonHospital hospital,ModelMap modelmap)throws Exception {
	Page<CommonHospital> info = commonhospitalService.getPage(page,size,hospital);
	//获取行政区域级别代码字典
    List<CommonDictionaryValue> dictionaryList = dictValueService.selectValuesByCode(10008);
    ChannelUser loginUser = (ChannelUser)request.getSession().getAttribute("loginUser");
    Integer roleId = channelRoleDao.getRoleByUserId(loginUser.getUserId()).getRoleId();
	modelmap.put("page", info);
	modelmap.put("hospital", hospital);
	modelmap.put("areaCode", areaCode);
	modelmap.put("areaPId", areaPId);
	modelmap.put("areaLevel", areaLevel);
	modelmap.put("dictionary", dictionaryList);
	modelmap.put("role", roleId);
	return new ModelAndView("commonhospital/commonhospitalList",modelmap);
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping("deletehospital")
	@ResponseBody
	public Map<String, Object> deleteHospital(String ids){
		Map<String, Object> result = this.getResult();
		try {
			commonhospitalService.deleteHospital(ids.split(","));
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			//日志记录???
		}
		return result;
	}
	/**
	 * 去新增或者修改的界面
	 * @param ids
	 * @return
	 */
	@RequestMapping("editui")
	public ModelAndView operatedUI(Integer id,String areaCode,String areaPId,Integer areaLevel,Model model){
		try {
			CommonHospital hospital = new CommonHospital();
			if(id != null && id != 0){
				//修改界面
				hospital = commonhospitalService.getById(id);
			}else{
				hospital.setAreaCode(areaCode);
				hospital.setParentCode(areaPId);
				hospital.setLevel(areaLevel);
			}
			model.addAttribute("info", hospital);
		} catch (Exception e) {
			e.printStackTrace();
			//???日志记录
		}
		return new ModelAndView("commonhospital/commonhospitalEdit");
	}
	
	/**
	 * 增改
	 */
	@RequestMapping("edit")
	@ResponseBody
	public Map<String, Object> edit(CommonHospital hospital){
		Map<String, Object> result = this.getResult();
		try {
			commonhospitalService.operated(hospital);
			result.put("success", true);
		} catch (Exception e) {
			//???日志输出
			e.printStackTrace();
		}
		return result;
	}


	private Map<String, Object> getResult() {
		Map<String, Object> map = new HashMap();
		map.put("success", false);
		return map;
	}
}
