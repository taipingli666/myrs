package com.choice.sign.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by wjm on 2017/3/8.
 */
public class ErrorUtil {

    /**
     * 错误返回处理 根据类型返回xml和json
     * @param dataType
     * @param ec
     * @return
     */
    public static String outError(int dataType, ErrorCode ec){
        if(dataType == Constants.CONVERSION_XML){
            return XmlResult.xmlReturn(ec);
        }else{
            return JSONObject.toJSONString(new Result(ec));
        }
    }

}
