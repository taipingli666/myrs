package com.choice.domain.repository.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.common.CommonDoctorTeam;

public interface CommonDoctorTeamDao {

	/**
	 * 团队分页列表
	 * @param team
	 * @return
	 */
	List<CommonDoctorTeam> getListByPage(
			@Param(value="team")CommonDoctorTeam team,
			@Param(value="subString")String[] subString);

	/**
	 * 根据teamId获取该团队
	 * @param id
	 * @return
	 */
	CommonDoctorTeam getById(Integer id);

	/**
	 * 删除团队
	 * @param substring
	 */
	void deleteTeams(String[] substring);
	
	/**
	 * 新增团队时获取最大teamId
	 * @return
	 */
	int getMaxId();

	/**
	 * 新增团队
	 * @param team
	 */
	void addTeam(CommonDoctorTeam team);

	/**
	 * 修改团队
	 * @param team
	 */
	void updateTeam(CommonDoctorTeam team);
	
	
}
