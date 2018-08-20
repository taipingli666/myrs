package com.choice.domain.service.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.entity.user.ChannelUserRole;
import com.choice.domain.repository.user.ChannelUserRoleDao;
import com.choice.domain.repository.user.UserDao;
import com.choice.domain.service.user.UserService;
import com.choice.sign.exception.ParameterException;
import com.choice.sign.util.Page;
import com.github.pagehelper.PageHelper;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private ChannelUserRoleDao channelUserRoleDao;
	public Page<ChannelUser> getPage(Integer page, Integer size, ChannelUser user) {
		// TODO Auto-generated method stub
		com.github.pagehelper.Page startPage = PageHelper.startPage(page, size);
		List<ChannelUser> list = userDao.getListByPage(user);
		//PageInfo<ChannelUser> info = new PageInfo<ChannelUser>(list);
		Long total = startPage.getTotal();
		return new Page<ChannelUser>(size, page, total.intValue(), list);
	}
	
	//查询除超级管理员外的其他所有用户信息
	public Page<ChannelUser> getPageExceptAdmin(Integer page, Integer size, ChannelUser user) {
		// TODO Auto-generated method stub
		com.github.pagehelper.Page startPage = PageHelper.startPage(page, size);
		List<ChannelUser> list = userDao.getListByPageExceptAdmin(user);
		//PageInfo<ChannelUser> info = new PageInfo<ChannelUser>(list);
		Long total = startPage.getTotal();
		return new Page<ChannelUser>(size, page, total.intValue(), list);
	}
	//增加或者更新
	public void operated(ChannelUser user) {
		if(user != null){
			if(user.getUserId()==null){
				//插入
				user.setAddTime(new Date());
				user.setIsDelete(0);
				user.setPassword(DigestUtils.md5Hex("123456".getBytes()));
				userDao.insert(user);
				//为新增的用户设置默认角色为普通用户
				//获取新插入用户的userId
				Integer userId = user.getUserId();
				List<ChannelUserRole> list = new ArrayList<ChannelUserRole>();
				ChannelUserRole channelUserRole = new ChannelUserRole();
				//这里roleId是写死的，6代表的是普通用户
				channelUserRole.setRoleId(6);
				channelUserRole.setUserId(userId);
				list.add(channelUserRole);
				channelUserRoleDao.operationUserRole(list);
			}else{
				//更新
				if(user.getPassword()!=null && !"".equals(user.getPassword())){
					user.setPassword(DigestUtils.md5Hex(user.getPassword().getBytes()));
				}
				user.setEditTime(new Date());
				userDao.updateByPrimaryKeySelective(user);
			}
		}else{
			throw new ParameterException("错误:参数为空");
		}
	}
	//批量删除
	@Transactional
	public void deleteUsers(String[] substring) {
		int i = userDao.deleteUsers(substring);
		if(i == 0){
			throw new RuntimeException();
		}
	}
	public ChannelUser getById(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}
	
	/**
	 * 专家管理分页列表
	 */
	public Page<ChannelUser> getPageForExpert(Integer page, Integer size, ChannelUser user,String[] subString) {
		com.github.pagehelper.Page startPage = PageHelper.startPage(page, size);
		List<ChannelUser> list = userDao.getListByPageForExpert(user,subString);
		Long total = startPage.getTotal();
		return new Page<ChannelUser>(size, page, total.intValue(), list);
	}
	
	/**
	 * 修改团队时列出的团队成员列表和其他可供筛选的用户列表
	 */
	public List<ChannelUser> getTeamsTableUpdateForMember(Integer id) {
		return userDao.getTeamsTableUpdateForMember(id);
	}
	
	/**
	 * 修改团队时列出的团队成员列表和其他可供筛选的用户列表
	 */
	public List<ChannelUser> getTeamsTableUpdateForUser(Integer id,String name,Integer page,Integer size) {
		return userDao.getTeamsTableUpdateForUser(id,name,page,size);
	}
	
	/**
	 * 新增团队时列出的用户列表
	 */
	public List<ChannelUser> getTeamsTableNew() {
		return userDao.getTeamsTableNew();
	}
	
	/**
	 * 没组团队的专家列表
	 */
	public List<ChannelUser> getExpertList() {
		return userDao.getExpertList();
	}
	
	/**
	 * 根据userId获取该用户
	 */
	public ChannelUser selectByPrimaryKey(Integer userId){
		return userDao.selectByPrimaryKey(userId);
	}
	
	/**
	 * 根据userId列表从团队里移除
	 */
	@Transactional
	public void userForLeaveTeam(String[] substring){
		userDao.userForLeaveTeam(substring);
	};
	
	/**
	 * 获取移除团队的userId列表
	 */
	public List<Integer> getUserIdsForLeave(int teamId){
		return userDao.getUserIdsForLeave(teamId);
	};
	
	/**
	 * 获取修改前的团队成员的id,包括专家
	 */
	public List<Integer> getPrevIds(Integer teamId){
		return userDao.getPrevIds(teamId);
	};
	
	/**
	 * 登陆
	 * @param userName，password
	 * @return
	 */
	public ChannelUser login(String userName,String password){
		return userDao.login(userName, password);
	}
	/**
	 *获取机构名称
	 * @param hosId
	 * @return
	 */
	public  List<ChannelUser> getHosName(Integer hosId){
		return userDao.getHosName(hosId);
	}
	
	/**
	 * 获取该用户的角色列表
	 * @param userId
	 * @return
	 */
	public List<Integer> getRoleIdList(int userId){
		return userDao.getRoleIdList(userId);
	};
	
	
	/**
	 * 获取医疗机构下的团队关联的所有专家列表
	 * @param hosId
	 * @return
	 */
	public List<ChannelUser> getExpectListByHosId(Integer hosId){
		return userDao.getExpectListByHosId(hosId);
	};
	
	/**
	 * 获取已经组团队的专家列表
	 * @return
	 */
	public List<ChannelUser> getJoinedExpectList(){
		return userDao.getJoinedExpectList();
	}
	
	/**
	 * 获取当前登录人所属团队的团队长
	 * @return
	 */
	public ChannelUser getExpertByTeamId(Integer teamId) {
		return userDao.getExpertByTeamId(teamId);
	}
	
	/**
	 * 获取当前登录人机构下的除当前合同签约医生之外的其他专家
	 * @return
	 */
	public List<ChannelUser> getOtherExpertList(Integer hosId, Integer expertId) {
		return userDao.getOtherExpertList(hosId,expertId);
	}

	/**
	 *根据用户信息过滤用户
	 * @param channelUser
	 * @return
	 */
	@Override
	public List<ChannelUser> selectByUserInfo(ChannelUser channelUser) {
		return userDao.selectByUserInfo(channelUser);
	}

	@Override
	public int checkUser(ChannelUser user) {
		return userDao.checkUser(user);
	}

}
