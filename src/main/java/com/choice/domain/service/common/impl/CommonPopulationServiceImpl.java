/**
 * 
 */
/**
 * @author Paul.Pan
 *
 */
package com.choice.domain.service.common.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.choice.domain.entity.common.CommonPopulation;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.repository.common.CommonPopulationDao;
import com.choice.domain.service.common.CommonPopulationService;
import com.choice.sign.util.Page;
import com.github.pagehelper.PageHelper;

@Service("CommonPopulationService")
public class CommonPopulationServiceImpl implements CommonPopulationService{
	@Autowired
	private CommonPopulationDao commonPopulationDao;
	public Page<CommonPopulation> getPage(int page,int size,String areaCode,String searchValue) {
		com.github.pagehelper.Page startPage = PageHelper.startPage(page, size);
		List<CommonPopulation> list = commonPopulationDao.getListByPage(areaCode,searchValue);
		Long total = startPage.getTotal();
		return new Page<CommonPopulation>(size, page, total.intValue(), list);
	}
	//更新或者插入1 插入,2更新
	public void operated(HttpServletRequest request,CommonPopulation commonPopulation) {
		ChannelUser nowUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		if(commonPopulation != null){
			if(commonPopulation.getpId()==null){
				//插入
				commonPopulation.setAddTime(new Date());
				commonPopulation.setIsDelete(0);
				commonPopulation.setAddPerson(nowUser.getUserId());
				commonPopulationDao.insert(commonPopulation);
			}else{
				//更新
				commonPopulation.setEditTime(new Date());
				commonPopulation.setEditPerson(nowUser.getUserId());
				commonPopulationDao.updateByPrimaryKeySelective(commonPopulation);
			}
		}else{
			throw new RuntimeException("【commonPopilation对象为空】");
		}
	}

	public void deleteCommonPopulation(int pId) {
	}
    
	public void deleteCommonPopulations(String[] substring){
		commonPopulationDao.deleteCommonPopulations(substring);
	}
	
	public CommonPopulation getInfo(int pId) {
		return commonPopulationDao.selectByPrimaryKey(pId);
	}
	
	//查询区域人口数
    public int pnumByAreaCode(String areaCode) {
    	return commonPopulationDao.pnumByAreaCode(areaCode);
    }
	@Override
	public int selectBySearchValue(CommonPopulation commonPopulation) {
		return commonPopulationDao.selectBySearchValue(commonPopulation);
	}
}