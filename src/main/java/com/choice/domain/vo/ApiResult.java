package com.choice.domain.vo;

/**
 * API返回结果对象
 * Created by wangbin on 17/3/14.
 */
public class ApiResult<T> {
    private boolean success;
    private int errorCode;
    private String message;
    private Long useTime;
    private T Data;
    //添加欢迎页
    private String welcomePage;

    public String getWelcomePage() {
        return welcomePage;
    }

    public void setWelcomePage(String welcomePage) {
        this.welcomePage = welcomePage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "success=" + success +
                ", errorCode=" + errorCode +
                ", message='" + message + '\'' +
                ", useTime=" + useTime +
                ", Data=" + Data +
                '}';
    }
}
