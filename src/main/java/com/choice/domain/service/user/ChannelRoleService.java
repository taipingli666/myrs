package com.choice.domain.service.user;

import java.util.List;
import java.util.Map;

import com.choice.domain.entity.user.ChannelRole;
import com.choice.sign.util.Page;

public interface ChannelRoleService {

	Page<ChannelRole> getPage(Integer page, Integer size, ChannelRole role);

	List<Map> getMenusByRoleId(Integer roleId);

	ChannelRole getById(Integer id);

	void operated(ChannelRole channelRole);

	void deleteRoles(String[] split);

	List<Map> getRolesByUserId(Integer loginUserId,Integer id);
	
}
