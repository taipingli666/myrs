package com.choice.domain.service.external.impl;

import com.choice.domain.entity.external.MessageInfo;
import com.choice.domain.repository.external.MessageInfoDao;
import com.choice.domain.service.external.MessageInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageServiceImpl implements MessageInfoService {

    @Resource
    private MessageInfoDao messageInfoDao;

    @Override
    public int insert(MessageInfo messageInfo) {
        return messageInfoDao.insert(messageInfo);
    }

    @Override
    public int update(MessageInfo messageInfo) {
        return messageInfoDao.update(messageInfo);
    }
}
