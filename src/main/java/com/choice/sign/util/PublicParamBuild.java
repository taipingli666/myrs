package com.choice.sign.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 公共参数构建
 * Created by administer on 2018-01-10.
 * @author lingli
 */
public class PublicParamBuild {

    /**
     * 添加公共参数
     * @param json
     * @return
     */
    public static JSONObject publicParam(JSONObject json){
        /*暂时写死后期根据需求改造*/
        StringBuffer strXml=new StringBuffer();
        json.put("accessToken","DASHFQWE3214312WERWEQRJQWERK");
        return json;
    }
}
