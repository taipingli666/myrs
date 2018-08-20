package com.choice.domain.service.user;

public interface ChannelUserRoleService {
	void operationRoleMenu(String ids,Integer userId);

	void delRoles(String[] split);
	
	//根据用户iD查权限
	int roleIdByUserId(Integer userId);
}
