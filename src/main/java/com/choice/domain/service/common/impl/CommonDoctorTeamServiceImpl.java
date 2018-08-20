package com.choice.domain.service.common.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choice.domain.entity.common.CommonDoctorTeam;
import com.choice.domain.repository.common.CommonDoctorTeamDao;
import com.choice.domain.repository.user.UserDao;
import com.choice.domain.service.common.CommonDoctorTeamService;
import com.choice.sign.util.Page;
import com.github.pagehelper.PageHelper;

@Service
public class CommonDoctorTeamServiceImpl implements CommonDoctorTeamService{
	
	@Autowired
	private CommonDoctorTeamDao commonDoctorTeamDao;
	
	@Autowired
	private UserDao userDao;


	/**
	 * 团队分页列表
	 */
	public Page<CommonDoctorTeam> getPage(Integer page, Integer size, CommonDoctorTeam team,String[] subString) {
		com.github.pagehelper.Page startPage = PageHelper.startPage(page, size);
		List<CommonDoctorTeam> list = commonDoctorTeamDao.getListByPage(team,subString);
		Long total = startPage.getTotal();
		return new Page<CommonDoctorTeam>(size, page, total.intValue(), list);
	}

	/**
	 * 增改
	 */
	@Transactional
	public void operated(CommonDoctorTeam team,String ids,String prev) {
		if(0==team.getTeamId()){
			//新增
			int teamId = commonDoctorTeamDao.getMaxId();
			String[] substring  = ids.split(",");
			userDao.joinTeam(substring,teamId);
			team.setIsDelete(0);
			team.setTeamId(teamId);
			team.setAddTime(new Date());
			commonDoctorTeamDao.addTeam(team);
		}else{
			//修改
			int teamId = team.getTeamId();
			//移除之前的
			String[] substring2  = prev.split(",");
			userDao.userForLeaveTeam(substring2);
			//加入已选的
			String[] substring  = ids.split(",");
			userDao.joinTeam(substring,teamId);
			//修改编辑时间
			team.setEditTime(new Date());
			commonDoctorTeamDao.updateTeam(team);
		}
		
	}
	
	/**
	 * 删除团队
	 */
	@Transactional
	public void deleteTeams(String[] substring) {
		commonDoctorTeamDao.deleteTeams(substring);
	}

	/**
	 * 根据id获取该团队
	 */
	public CommonDoctorTeam getById(Integer id) {
		// TODO Auto-generated method stub
		return commonDoctorTeamDao.getById(id);
		 
	}
	
}
