package com.choice.domain.repository.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.user.ChannelUser;

public interface UserDao {
	int deleteByPrimaryKey(Integer userId);

	int insert(ChannelUser record);

	int insertSelective(ChannelUser record);

	ChannelUser selectByPrimaryKey(Integer userId);

	int updateByPrimaryKeySelective(ChannelUser record);

	int updateByPrimaryKey(ChannelUser record);

	List<ChannelUser> getListByPage(ChannelUser user);
	
	List<ChannelUser> getListByPageExceptAdmin(ChannelUser user);

	int deleteUsers(String[] substring);

	/**
	 * 根据用户名检查是否已经存在此用户名
	 * @param user
	 * @return
	 */
	int checkUser(ChannelUser user);
	
	/**
	 * 根据用户信息过滤用户
	 * @param user
	 * @return
	 */
	List<ChannelUser> selectByUserInfo(ChannelUser user);
	/**
	 * 登陆
	 * @param userName，password
	 * @return
	 */
	ChannelUser login(@Param(
			value="userName")String userName,
			@Param(value="password")String password);
	
	/**
	 * 专家管理分页列表
	 * @param user
	 * @return
	 */
	List<ChannelUser> getListByPageForExpert(
			@Param(value="user")ChannelUser user,
			@Param(value="subString")String[] subString);

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
	List<ChannelUser> getTeamsTableUpdateForUser(
			@Param(value="teamId")Integer teamId,
			@Param(value="name")String name,
			@Param(value="page")Integer page,
			@Param(value="size")Integer size);


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
	 * 加入团队
	 * @param substring
	 * @param teamId
	 */
	void joinTeam(@Param(value="substring")String[] substring,@Param(value="teamId")int teamId);

	/**
	 * 根据userId列表从团队里移除
	 * @param substring
	 */
	void userForLeaveTeam(String[] substring);

	/**
	 * 获取移除团队的userId列表
	 * @param teamId
	 * @return
	 */
	List<Integer> getUserIdsForLeave(@Param(value="teamId")int teamId);

	/**
	 * 获取修改前的团队成员的id,包括专家
	 * @param teamId
	 * @return
	 */
	List<Integer> getPrevIds(Integer teamId);
	/**
	 *获取机构名称
	 * @param hosId
	 * @return
	 */
	List<ChannelUser> getHosName(@Param(value="hosId")Integer hosId);
	
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
	 * @return
	 */
	List<ChannelUser> getOtherExpertList(Integer hosId, Integer expertId);
	
	
}
