/**
 * 
 */
/**
 * @author Paul.Pan
 *
 */
package com.choice.domain.service.common.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choice.domain.entity.common.CommonArea;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.common.CommonAreaService;
import com.choice.sign.util.Page;
import com.github.pagehelper.PageHelper;
import com.choice.domain.repository.common.CommonAreaDao;

@Service("CommonAreaService")
public class CommonAreaServiceImpl implements CommonAreaService{
	@Autowired
	private CommonAreaDao commonAreaDao;
	public Page<CommonArea> getPage(int page,int size,String areaName,String parentCode) {
		com.github.pagehelper.Page startPage = PageHelper.startPage(page, size);
		List<CommonArea> list = commonAreaDao.getListByPage(areaName,parentCode);
		Long total = startPage.getTotal();
		return new Page<CommonArea>(size, page, total.intValue(), list);
	}
    
	public Page<CommonArea> getPageByParentCode(int page, int size, String parentCode) {
		com.github.pagehelper.Page startPage = PageHelper.startPage(page,size);
		List<CommonArea> list = commonAreaDao.getCommonAreaByParentCode(parentCode);
		Long total = startPage.getTotal();
		return new Page<CommonArea>(size, page, total.intValue(), list);
	}
	//更新或者插入1 插入,2更新
	public void operated(HttpServletRequest request,CommonArea commonArea) {
		ChannelUser nowUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		if(commonArea != null){
			if(commonArea.getAreaId()==null){
				//插入
				commonArea.setAddTime(new Date());
				commonArea.setIsDelete(0);
				nowUser.setAddPerson(nowUser.getUserId());
				commonAreaDao.insert(commonArea);
			}else{
				//更新
				commonArea.setEditTime(new Date());
				commonArea.setEditPerson(nowUser.getUserId());
				commonAreaDao.updateByPrimaryKeySelective(commonArea);
			}
		}else{
			throw new RuntimeException("【commonArea对象为空】");
		}
	}
	public void deleteCommonArea(int areaId) {
		commonAreaDao.deleteCommonArea(areaId);
	}

	public CommonArea getInfo(int areaId) {
		return commonAreaDao.selectByPrimaryKey(areaId);
	}
	
	public void deleteCommonAreas(String[] substring){
		commonAreaDao.deleteCommonAreas(substring);
	}
	
	public List<Map<String,Object>> getCommonAreaAll(String parentCode){
		return commonAreaDao.getCommonAreaAll(parentCode);
	}
	
	public List<CommonArea> getCommonAreaByParentCode(String parentCode){
		return commonAreaDao.getCommonAreaByParentCode(parentCode);
	}
	
	//根据行政区划代码获取行政区划信息
    public List<Map<String,Object>> getCommonAreaByAreaCode(String areaCode){
    	return commonAreaDao.getCommonAreaByAreaCode(areaCode);
    }
  //获取同级别地区
  	public List<CommonArea> getListByLevel(Integer level){
  		return commonAreaDao.getListByLevel(level);
  	}
  	public List<String> allAreaCode() {
  		return commonAreaDao.allAreaCode();
  	}

	/**
	 * 根据行政编码查询信息
	 * @param code
	 * @return
	 */
	@Override
	public CommonArea selectByCode(String code) {
		return commonAreaDao.selectByCode(code);
	}
}