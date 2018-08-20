package com.choice.domain.service.common.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.repository.common.CommonHospitalDao;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.sign.util.Page;
import com.github.pagehelper.PageHelper;

@Service
public class CommonHospitalServiceImpl implements CommonHospitalService {
	@Autowired
	private CommonHospitalDao commonhospitalDao;

	// 分页查询
	public Page<CommonHospital> getPage(Integer page, Integer size, CommonHospital hospital) {
		// TODO Auto-generated method stub
		com.github.pagehelper.Page startPage = PageHelper.startPage(page, size);
		List<CommonHospital> list = commonhospitalDao.getListByPage(hospital);
		Long total = startPage.getTotal();
		return new Page<CommonHospital>(size, page, total.intValue(), list);
	}

	// 插入或更新
	public void operated(CommonHospital hospital) {
		// TODO Auto-generated method stub
		if (hospital != null) {
			// 根据主键判断是否为插入
			if (hospital.getHosId() == null) {
				hospital.setAddTime(new Date());
				hospital.setIsDelete(0);
				commonhospitalDao.insertSelective(hospital);
			} else {
				hospital.setEditTime(new Date());
				commonhospitalDao.updateByPrimaryKeySelective(hospital);
			}
		} else {
			throw new RuntimeException("【医院信息对象为空】");
		}
	}

	// 批量删除
	public void deleteHospital(String[] substring) {
		// TODO Auto-generated method stub
		commonhospitalDao.deleteHospital(substring);
	}

	// 根据id查询
	public CommonHospital getById(Integer id) {
		// TODO Auto-generated method stub
		return commonhospitalDao.selectByPrimaryKey(id);
	}

	
	// 根据所有医院
	public List<CommonHospital> getAllHospital(CommonHospital hospital) {
		// TODO Auto-generated method stub
		return commonhospitalDao.getAllHospital();
	}
	
	//获取医疗机构，给用户模块
	public List<CommonHospital> getHospitalForUser(String areaCode){
		return commonhospitalDao.getHospitalForUser(areaCode);
	}
	
	//团队管理筛选
	public List<Integer> getHosIdListForSelect(String areaCode){
		return commonhospitalDao.getHosIdListForSelect(areaCode);
	};
	public List<CommonHospital> getAllHospital(){
		return commonhospitalDao.getAllHospital();
	}

	@Override
	public List<CommonHospital> selectHospitalList(String hosId) {
		// TODO Auto-generated method stub
		return commonhospitalDao.selectHospitalList(hosId);
	}

	public List<Integer> hosIdByAreaCode(String areaCode) {
		return commonhospitalDao.hosIdByAreaCode(areaCode);
	}

	@Override
	public CommonHospital selectByHosCode(String hosCode) {
		return commonhospitalDao.selectByHosCode(hosCode);
	}
	
	public List<CommonHospital> getAllHospitalWithoutNull(){
		return commonhospitalDao.getAllHospitalWithoutNull();
	}
}
