package com.choice.domain.repository.user;

import java.util.List;

import com.choice.domain.entity.user.ChannelPower;

public interface ChannelPowerDao {
    int deleteByPrimaryKey(Integer powerId);

    int insert(ChannelPower record);

    int insertSelective(ChannelPower record);

    ChannelPower selectByPrimaryKey(Integer powerId);

    int updateByPrimaryKeySelective(ChannelPower record);

    int updateByPrimaryKey(ChannelPower record);

	int delMenusByRoleId(int i);

	int operationRoleMenu(List<ChannelPower> list);
}