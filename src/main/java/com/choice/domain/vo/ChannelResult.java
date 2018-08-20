package com.choice.domain.vo;

import com.choice.sign.util.ErrorCode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ChannelResult implements Serializable {
    private int resultCode = -1;
    private String errorMsg = "待处理";
    private Map<String, Object> data = new HashMap<String, Object>();

    public ChannelResult(){}

    public ChannelResult(ErrorCode errorCode){
        this.resultCode = errorCode.getCodeId();
        this.errorMsg = errorCode.getMessage();
    }

    public ChannelResult(int resultCode, String message){
        this.resultCode = resultCode;
        this.errorMsg = message;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return errorMsg;
    }

    public void setMessage(String message) {
        this.errorMsg = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
