package com.choice.sign.util;

/**
 * Created by baiqian@myweimai.com on 2016/12/1.
 */
public enum ErrorCode {

    /**
     * 错误信息
     */
    PARAMETERS_TIMEOUT(-1,"入参错误"),
    CONNECT_TIMEOUT(-1,"接入平台链接超时"),
    ERROR(-1,"其他错误");

    private int codeId;
    private String message;

    ErrorCode(int codeId, String message) {
        this.codeId = codeId;
        this.message = message;
    }

    public String getValue() {
        return toString();
    }

    public static int getCodeId(ErrorCode ec) {
        for (ErrorCode c : ErrorCode.values()) {
            if (c == ec) {
                return c.codeId;
            }
        }
        return -1;
    }

    public static String getMessage(ErrorCode ec) {
        for (ErrorCode c : ErrorCode.values()) {
            if (c == ec) {
                return c.message;
            }
        }
        return "";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

}
