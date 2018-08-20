package com.choice.domain.service.channel;

import com.choice.domain.entity.external.MessageInfo;

import java.util.Map;

/**
 * 渠道下行
 * Created by administer on 2018-01-09.
 *@author lingli
 */
public interface ChannelDownService {

    /**
     * 下行获取数据返回json字符串
     * @param methodName 调用方法名
     * @param requestBody JSON字符串
     * @return
     */
    String downDataDealJson(String methodName,  String requestBody);

    /**
     * 下行获取数据
     * @param methodName
     * @param requestBody
     * @return
     */
    String downDataDeal(String methodName, String requestBody);

    /**
     * 短信接口数据处理
     * @param methodName
     * @param requestBody
     * @return
     */
    String msgDataDeal(String methodName, String requestBody);



}
