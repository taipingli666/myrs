package com.choice.domain.service.channel.impl;

import com.alibaba.fastjson.JSONObject;
import com.choice.domain.entity.external.MessageInfo;
import com.choice.domain.service.channel.ChannelDownService;
import com.choice.domain.vo.ChannelResult;
import com.choice.sign.util.*;
import com.choice.sign.util.http.HttpHelperTime30;
import com.choice.sign.util.http.HttpResult;
import org.dom4j.Document;
import org.dom4j.Element;
import org.apache.log4j.Logger;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 下行服务
 * Created by administer on 2018-01-09.
 * @author lingli
 */
@Service
public class ChannelDownServiceImpl implements ChannelDownService {

    private Logger logger=Logger.getLogger(ChannelDownServiceImpl.class);
    private static final String URL="http://localhost:8080/choice-sign/channelTest/testData";

    /**
     * 下行获取数据返回json字符串
     * @param methodName
     * @param requestBody
     * @return
     */
    
    @Override
    public String  downDataDealJson(String methodName, String requestBody) {
        long beginTimes = System.currentTimeMillis();
        String uuid = UuIdUtil.getUuid();
        logger.info(" 【第 1步：下行业务处理_开始】【方法：" + methodName +  "】- 【EventId："+ uuid +"】  - 【" +
                        DateUtil.dateToString( new Date(),"yyyyMMddHHmmssSSS")+"】+【请求requestBody：" + requestBody +"】\n");

        HttpResult result = null;
        try{
            HttpHelperTime30 helper = HttpHelperTime30.getInstance();
            byte[] body = requestBody.getBytes("utf-8");
            Map map = new HashMap();
            map.put("Content-Type", "text/json;charset=UTF-8");

            result = helper.post(URL, map, null, body);
        }catch (Exception e){
            logger.error("【第 2.error 步： 下行业务处理_报错】- 【EventId：" + uuid + "】 - 【"+
                            DateUtil.dateToString( new Date(),"yyyyMMddHHmmssSSS") +"】【ERROR】+【方法："+
                            methodName + "】【入参：" + requestBody + "】【出参："+ result +"】【用时:" +
                            String.valueOf(System.currentTimeMillis() - beginTimes) +"毫秒,执行出错了！错误信息如下："+e+"】\n");
            return JSONObject.toJSONString(new ChannelResult(ErrorCode.CONNECT_TIMEOUT));
        }

        //判断出参
        if (null == result || "".equals(result)) {
            logger.info("【第 2 步：下行业务处理_结束_出参为空】 - 【EventId：+ uuid +】 - 【用时:" +
                            String.valueOf(System.currentTimeMillis() - beginTimes) + "毫秒】【INFO】+【" +
                    System.getProperty("os.name")+"】【调用方法："+methodName+"】【请求入参："+requestBody+"】\n");
            return JSONObject.toJSONString(new ChannelResult( ErrorCode.CONNECT_TIMEOUT));
        } else if (200 != result.getStatusCode()) {
            logger.info(" 【第 2 步：下行业务处理_结束_网络状态不正常（非200）】 - 【EventId："+ uuid +"】 - 【用时:"+
                            String.valueOf(System.currentTimeMillis() - beginTimes)+"毫秒】【INFO】+【"+
                            System.getProperty("os.name")+"】【调用方法："+methodName+"】【请求入参："+requestBody+"】\n");
            return JSONObject.toJSONString(new ChannelResult(ErrorCode.ERROR));
        } else {
            //返回出参内容
            String resultPonseBody = new String(result.getResponseBodyAsString());


            logger.info("【第 4 步：下行业务处理_结束】 - 【EventId："+uuid+"】 - 【用时:"+
                            String.valueOf(System.currentTimeMillis() - beginTimes)+"毫秒】【INFO】+【"+
                            System.getProperty("os.name")+"】【调用方法："+methodName+"】【返回信息："+resultPonseBody+"】\n");

            return resultPonseBody;
        }
    }

    private  static final String WS_URL="http://172.16.80.42:99/ClinicalPath.asmx";
    private  static final String MSG_URL="http://172.16.80.93/SMSService.asmx";
    private String soapHead="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "${inxml}" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    /**
     * 下行获取数据
     * @param methodName
     * @param requestBody
     * @return
     */
    @Override
    public String downDataDeal(String methodName, String requestBody) {
        long beginTimes = System.currentTimeMillis();
        String uuid = UuIdUtil.getUuid();
        logger.info(" 【第 1步：下行WS业务处理_开始】【方法："+methodName+"】- 【EventId："+uuid+"】  - 【"+
                        DateUtil.getSSSCurrentDate()+"】+【请求requestBody："+requestBody+"】\n");

        //机构编号从xml取出， 进行相应业务比对，处理--------------------------------------------------------------------------------------

        HttpResult result = null;
        try {
            requestBody = soapHead.replace("${inxml}", requestBody);
            byte[] body = requestBody.getBytes("UTF-8");
            //下行地址
            HttpHelperTime30 helper = HttpHelperTime30.getInstance();
            Map map = new HashMap();
            map.put("Content-Type", "text/xml;charset=UTF-8");

            result = helper.post(WS_URL, map, null, body);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("【第 2.error 步： 下行业务处理_报错】- 【EventId："+uuid+"】 - 【"+
                            DateUtil.getSSSCurrentDate()+"】【ERROR】+【方法："+methodName+"】【入参："+
                            requestBody+"】【出参："+result+"】【用时:"+
                            String.valueOf(System.currentTimeMillis() - beginTimes)+"毫秒,执行出错了！错误信息如下："+e+"】\n");
            return ErrorUtil.outError(1, ErrorCode.CONNECT_TIMEOUT);
        }
        //判断出参
        if (null == result || "".equals(result)) {
            logger.info("【第 2 步：提供者_下行业务处理_结束_出参为空】 - 【EventId："+uuid+"】 - 【用时:"+
                    String.valueOf(System.currentTimeMillis() - beginTimes) +"毫秒】【INFO】+【"+System.getProperty("os.name")+"】【调用方法："+methodName+"】【请求入参："+requestBody+"】\n");
            return ErrorUtil.outError(1, ErrorCode.CONNECT_TIMEOUT);
        } else if (200 != result.getStatusCode()) {
            logger.info(" 【第 2 步：提供者_下行业务处理_结束_网络状态不正常（非200）】 - 【EventId："+uuid+"】 - 【用时:"+
                            String.valueOf(System.currentTimeMillis() - beginTimes) +"毫秒】【INFO】+【"+
                    System.getProperty("os.name")+"】【调用方法："+methodName+"】【请求入参："+requestBody+"】【请求出参："+
                    result.toString()+"】\n");
            return ErrorUtil.outError(1, ErrorCode.ERROR);
        } else {
            //返回出参内容
            String resultPonseBody ="";
            try {
                resultPonseBody =  new String(result.getResponseBody(),"UTF-8");
            }catch ( Exception e){
                e.printStackTrace();
                logger.info("转码出错");
            }
            logger.info("【返回出参】"+resultPonseBody);
            /*webservice 解析出参内容*/
            if (!StringUtil.isEmpty("222___")) {
            		/*解析出参内容*/
                Document doc = XmlUtil.StringToXml(resultPonseBody);

                Element root = doc.getRootElement();
                Element body = root.element("Body");
                Element web = body.element(methodName+"Response");
                Element OutXml = web.element(methodName+"Result");
                resultPonseBody = OutXml.getText();

            }
            logger.info("【第 4 步：提供者_下行业务处理_结束】 - 【EventId："+uuid+"】 - 【用时:"+
                            String.valueOf(System.currentTimeMillis() - beginTimes)+"毫秒】【INFO】+【"+
                    System.getProperty("os.name")+"】【调用方法："+methodName+"】【返回信息："+resultPonseBody+"】\n");
            return resultPonseBody;
        }
    }

    /**
     * 短信接口数据处理
     * @param methodName
     * @param requestBody
     * @return
     */
    public String msgDataDeal(String methodName, String requestBody) {
        long beginTimes = System.currentTimeMillis();
        String uuid = UuIdUtil.getUuid();
        logger.info(" 【第 1步：下行WS业务处理_开始】【方法："+methodName+"】- 【EventId："+uuid+"】  - 【"+
                DateUtil.getSSSCurrentDate()+"】+【请求requestBody："+requestBody+"】\n");

        //机构编号从xml取出， 进行相应业务比对，处理--------------------------------------------------------------------------------------

        HttpResult result = null;
        try {
            requestBody = soapHead.replace("${inxml}", requestBody);
            byte[] body = requestBody.getBytes("UTF-8");
            //下行地址
            HttpHelperTime30 helper = HttpHelperTime30.getInstance();
            Map map = new HashMap();
            map.put("Content-Type", "text/xml;charset=UTF-8");

            result = helper.post(MSG_URL, map, null, body);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("【第 2.error 步： 下行业务处理_报错】- 【EventId："+uuid+"】 - 【"+
                    DateUtil.getSSSCurrentDate()+"】【ERROR】+【方法："+methodName+"】【入参："+
                    requestBody+"】【出参："+result+"】【用时:"+
                    String.valueOf(System.currentTimeMillis() - beginTimes)+"毫秒,执行出错了！错误信息如下："+e+"】\n");
            return ErrorUtil.outError(1, ErrorCode.CONNECT_TIMEOUT);
        }
        //判断出参
        if (null == result || "".equals(result)) {
            logger.info("【第 2 步：提供者_下行业务处理_结束_出参为空】 - 【EventId："+uuid+"】 - 【用时:"+
                    String.valueOf(System.currentTimeMillis() - beginTimes) +"毫秒】【INFO】+【"+System.getProperty("os.name")+"】【调用方法："+methodName+"】【请求入参："+requestBody+"】\n");
            return ErrorUtil.outError(1, ErrorCode.CONNECT_TIMEOUT);
        } else if (200 != result.getStatusCode()) {
            logger.info(" 【第 2 步：提供者_下行业务处理_结束_网络状态不正常（非200）】 - 【EventId："+uuid+"】 - 【用时:"+
                    String.valueOf(System.currentTimeMillis() - beginTimes) +"毫秒】【INFO】+【"+
                    System.getProperty("os.name")+"】【调用方法："+methodName+"】【请求入参："+requestBody+"】【请求出参："+
                    result.toString()+"】\n");
            return ErrorUtil.outError(1, ErrorCode.ERROR);
        } else {
            //返回出参内容
            String resultPonseBody ="";
            try {
                resultPonseBody =  new String(result.getResponseBody(),"UTF-8");
            }catch ( Exception e){
                e.printStackTrace();
                logger.info("转码出错");
            }
            logger.info("【返回出参】"+resultPonseBody);
            //webservice 解析出参内容
            if (!StringUtil.isEmpty("222___")) {
                /*解析出参内容*/
                Document doc = XmlUtil.StringToXml(resultPonseBody);

                Element root = doc.getRootElement();
                resultPonseBody = root.asXML();

            }
            logger.info("【第 4 步：提供者_下行业务处理_结束】 - 【EventId："+uuid+"】 - 【用时:"+
                    String.valueOf(System.currentTimeMillis() - beginTimes)+"毫秒】【INFO】+【"+
                    System.getProperty("os.name")+"】【调用方法："+methodName+"】【返回信息："+resultPonseBody+"】\n");
            return resultPonseBody;
        }
    }

}
