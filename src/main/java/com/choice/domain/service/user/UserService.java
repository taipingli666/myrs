package com.choice.domain.service.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.user.ChannelUser;
import com.choice.sign.util.Page;
import com.github.pagehelper.PageInfo;

public interface UserService {

	Page<ChannelUser> getPage(Integer page, Integer size, ChannelUser user);

	Page<ChannelUser> getPageExceptAdmin(Integer page, Integer size, ChannelUser user);
	
	void operated(ChannelUser user);

	void deleteUsers(String[] substring);

	ChannelUser getById(Integer id);

	/**
	 * 根据用户名查询是否存在此用户
	 * @param user
	 * @return
	 */
	int checkUser(ChannelUser user);
	
	/**
	 * 专家管理分页列表
	 * @param page
	 * @param size
	 * @param user
	 * @return
	 */
	Page<ChannelUser> getPageForExpert(Integer page, Integer size, ChannelUser user,String[] subString);

	/**
	 * 修改团队时列出的团队成员列表和其他可供筛选的用户列表
	 * @param id
	 * @return
	 */
	List<ChannelUser> getTeamsTableUpdateForMember(Integer id);
	
	/**
	 * 修改团队时列出的团队成员列表和其他可供筛选的用户列表
	 * @param id
	 * @return
	 */
	List<ChannelUser> getTeamsTableUpdateForUser(Integer teamId,String name,Integer page,Integer size);

	/**
	 * 新增团队时列出的用户列表
	 * @return
	 */
	List<ChannelUser> getTeamsTableNew();
	
	/**
	 * 没组团队的专家列表
	 * @return
	 */
	List<ChannelUser> getExpertList();
	
	/**
	 * 根据userId获取该用户
	 * @param userId
	 * @return
	 */
	ChannelUser selectByPrimaryKey(Integer userId);

	/**
	 * 根据userId列表从团队里移除
	 * @param substring
	 */
	void userForLeaveTeam(String[] substring);

	/**
	 * 获取移除团队的userId列表
	 * @param parseInt
	 * @return
	 */
	List<Integer> getUserIdsForLeave(int parseInt);

	
	/**
	 * 获取修改前的团队成员的id,包括专家
	 * @param id
	 * @return
	 */
	List<Integer> getPrevIds(Integer id);
	
	/**
	 * 登陆
	 * @param userName，password
	 * @return
	 */
	ChannelUser login(String userName,String password);
	/**
	 *获取机构名称
	 * @param hosNum
	 * @return
	 */
	List<ChannelUser> getHosName(Integer hosId);
	
	/**
	 * 获取该用户的角色列表
	 * @param userId
	 * @return
	 */
	List<Integer> getRoleIdList(int userId);
	
	/**
	 * 获取医疗机构下的团队关联的所有专家列表
	 * @param hosId
	 * @return
	 */
	List<ChannelUser> getExpectListByHosId(Integer hosId);

	
	/**
	 * 获取已经组团队的专家列表
	 * @return
	 */
	List<ChannelUser> getJoinedExpectList();
	
	/**
	 * 获取当前登录人所属团队的团队长
	 * @return
	 */
	ChannelUser getExpertByTeamId(Integer teamId);
	
	/**
	 * 获取当前登录人机构下的除当前合同签约医生之外的其他专家
	 */
	List<ChannelUser> getOtherExpertList(Integer hosId,Integer expertId);

	/**
	 * 根据用户信息过滤用户
	 * @param channelUser
	 * @return
	 */
	List<ChannelUser> selectByUserInfo(ChannelUser channelUser);
}
