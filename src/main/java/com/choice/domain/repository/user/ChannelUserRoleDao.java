package com.choice.domain.repository.user;
import java.util.List;

import com.choice.domain.entity.user.ChannelUserRole;

public interface ChannelUserRoleDao {
    int deleteByPrimaryKey(Integer userRoleId);

    int insert(ChannelUserRole record);

    int insertSelective(ChannelUserRole record);

    ChannelUserRole selectByPrimaryKey(Integer userRoleId);

    int updateByPrimaryKeySelective(ChannelUserRole record);

    int updateByPrimaryKey(ChannelUserRole record);

	void delRolesByUserId(Integer userId);

	int operationUserRole(List<ChannelUserRole> list);

	void delRoles(String[] split);
	
	int roleIdByUserId(Integer userId);
}