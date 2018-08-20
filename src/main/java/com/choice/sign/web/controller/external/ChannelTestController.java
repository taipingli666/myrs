package com.choice.sign.web.controller.external;

import com.alibaba.fastjson.JSONObject;
import com.choice.domain.service.channel.ChannelDownService;
import com.choice.sign.util.PublicParamBuild;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by administer on 2018-01-09.
 */
@RestController
@RequestMapping(value = "/channelTest")
public class ChannelTestController {

    @Resource
    private ChannelDownService channelDownService;

    @RequestMapping(value = "/getCheckClass",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getCheckClass(){
        JSONObject parameters =new JSONObject();
        parameters = PublicParamBuild.publicParam(parameters);
        String jsonString = channelDownService.downDataDealJson("getCheckClass",parameters.toJSONString());
        return jsonString;
    }


    /**
     * 返回测试数据
     * @return
     */
    @RequestMapping(value = "testData",produces = "application/json; charset=utf-8")
    @ResponseBody
    public JSONObject testData( ) {
        //门诊登记号
        String json= "{\"checkClassList\":[{\"checkClassCode\":\"1222\",\"大类名称\":\"内科\",\"sortId\":\"1\"}],\"resultCode\":\"0\",\"errorMsg\":\"错误原因\"}";
        return JSONObject.parseObject(json);
    }
}
