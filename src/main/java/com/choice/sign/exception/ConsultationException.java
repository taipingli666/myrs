package com.choice.sign.exception;

/**
 * 会诊的异常
 * Created by duhuo on 2017/9/15.
 */
public class ConsultationException extends RuntimeException {
    public ConsultationException(String msg) {
        super(msg);
    }
    public ConsultationException(String msg,Exception e) {
        super(msg,e);
    }
    public ConsultationException() {
    }
}
