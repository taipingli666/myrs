package com.choice.domain.service.channel.impl;

import com.choice.domain.entity.external.MessageInfo;
import com.choice.domain.service.channel.ChannelDownService;
import com.choice.domain.service.channel.ChannelMsgService;
import com.choice.sign.util.XmlUtil;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ChannelMsgServiceImpl implements ChannelMsgService {

    @Resource
    private ChannelDownService channelDownService;

    private Logger logger = Logger.getLogger(ChannelMsgServiceImpl.class);

    //调用短信接口
    public String channelMessage(MessageInfo messageInfo) {

        /*入参拼接*/
        StringBuffer xmlStr = new StringBuffer();
        xmlStr.append("<SendSMS xmlns=\"http://tempuri.org/\">");
        xmlStr.append("<msgid>" +messageInfo.getMsgId()+ "</msgid>");
        xmlStr.append("<msgcontent>" +messageInfo.getMsgContent()+ "</msgcontent>");
        xmlStr.append("<tel>" +messageInfo.getTel()+ "</tel>");
        xmlStr.append("<key>" +messageInfo.getMsgKey()+ "</key>");
        xmlStr.append("</SendSMS>");


        /* 调用接口*/
        String outxml= channelDownService.msgDataDeal("SendSMS", xmlStr.toString() );
        //String outxml = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body><SendSMSResponse xmlns=\"http://tempuri.org/\"><SendSMSResult>ok</SendSMSResult></SendSMSResponse></soap:Body></soap:Envelope>";
        Document doc = XmlUtil.StringToXml(outxml);
        Element root = doc.getRootElement();
        Element body = root.element("Body");
        Element web = body.element("SendSMS"+"Response");
        Element OutXml = web.element("SendSMS"+"Result");
        //resultPonseBody = OutXml.getText();

        /*出参解析*/
        String map = OutXml.getText();;
        logger.info("【出参解析为】" + map);
        return map;
    }
}
