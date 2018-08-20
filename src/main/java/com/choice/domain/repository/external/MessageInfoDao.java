package com.choice.domain.repository.external;

import com.choice.domain.entity.external.MessageInfo;

public interface MessageInfoDao {

    int insert(MessageInfo messageInfo);

    int update(MessageInfo messageInfo);
}
