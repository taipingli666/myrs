package com.choice.domain.service.common;

import com.choice.domain.entity.common.CommonDoctorTeam;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.sign.util.Page;

public interface CommonDoctorTeamService {

	/**
	 * 团队分页列表
	 * @param page
	 * @param size
	 * @param team
	 * @return
	 */
	Page<CommonDoctorTeam> getPage(Integer page, Integer size, CommonDoctorTeam team,String[] subString);

	/**
	 * 增改
	 * @param team
	 * @param ids
	 * @param prev
	 */
	void operated(CommonDoctorTeam team,String ids,String prev);

	/**
	 * 删除团队
	 * @param substring
	 */
	void deleteTeams(String[] substring);

	/**
	 * 根据id获取该团队
	 * @param id
	 * @return
	 */
	CommonDoctorTeam getById(Integer id);

	
}
