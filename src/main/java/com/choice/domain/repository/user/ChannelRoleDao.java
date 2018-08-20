package com.choice.domain.repository.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.common.CommonMenu;
import com.choice.domain.entity.user.ChannelRole;

public interface ChannelRoleDao {
	int deleteByPrimaryKey(Integer roleId);

    int insert(ChannelRole record);

    int insertSelective(ChannelRole record);

    ChannelRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(ChannelRole record);

    int updateByPrimaryKey(ChannelRole record);

	List<ChannelRole> getList(ChannelRole channelRole);

	int deleteRoles(String[] split);

	List<Map> getRolesByUserId(@Param("loginUserId")Integer loginUserId,@Param("id")Integer id);
	
	//根据用户id获取用户的最高等级角色id
	ChannelRole getRoleByUserId(Integer id);
}
