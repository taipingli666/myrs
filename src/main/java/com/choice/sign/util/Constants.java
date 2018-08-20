package com.choice.sign.util;

/**
 * Created by baiqian@myweimai.com on 2016/12/15.
 */
public interface Constants {

    long USER_LOGIN_EXPIRE_TIME_SECOND = 366 * 24 * 60 * 60;     // 一年过期时间

    int AUTH_EXPIRE_TIME = 24 * 60;     // 有效时间校验值 单位:分

    String SYS_WORK_ID_NAME = "choice.id.workerId";

    String APP_TYPE = "weimai";

    /**
     * 密钥头部参数相关
     */
    String HANDER_DEVICE = "Win7";
    String HANDER_PRGNAME = "Assistant_Dev";
    String HANDER_ENCRYPT = "false";
    String HANDER_JIGOUBH = "0";
    String HANDER_GERENBH = "1";
    String HANDER_LOGID = "login";
    String HANDER_YINGYONGLX = "yuntongdao_w";

    String MAC_NAME = "HmacSHA1";

    String ENCODING = "UTF-8";

    String ORG_SPECIAL = "1";

    /**
     * 数据类型
     */
    int CONVERSION_XML = 0;

    int CONVERSION_JSON = 1;


}
