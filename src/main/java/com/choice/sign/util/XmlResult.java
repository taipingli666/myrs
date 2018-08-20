package com.choice.sign.util;

/**
 * xml格式返回信息
 * Created by wjm on 2017/3/8.
 */
public class XmlResult {

    private int retCode;

    private String retMessage;

    public XmlResult(int retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public XmlResult(ErrorCode errorCode) {
        this.retCode = errorCode.getCodeId();
        this.retMessage = errorCode.getMessage();
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    public static String xmlReturn(ErrorCode errorCode){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><wemay><head><retcode>"+ errorCode.getCodeId()+"</retcode><retmessage>"+errorCode.getMessage()+"</retmessage></head><body></body></wemay>";
    }

}
