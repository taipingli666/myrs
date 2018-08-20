package com.choice.sign.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by 12192 on 2017/3/30 0030.
 */
public class CommonUtil {

    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);


    /**
     * 从map中获取值，key不区分大小写
     * @param map
     * @param name
     * @return
     */
    public static String getMapId(Map<String, Object> map, String name) {
        if(name == null)
            return null;
        if (map.get(name) != null)
            return map.get(name).toString();
        if (map.get(name.toUpperCase()) != null)
            return map.get(name.toUpperCase()).toString();
        else return null;
    }

    public static int strToint(String str) {
        int value;
        if (str == null || "".equals(str)) {
            return 0;
        }
        try {
            value = Integer.parseInt(str);
        } catch (Exception e) {
            logger.info("strToint转化出错:"+str);
            value = -1;
        }
        return value;
    }

    public static long strTolong(String str) {
        long value;
        if (str == null || "".equals(str)) {
            return 0;
        }
        try {
            value = Long.parseLong(str);
        } catch (Exception e) {
            logger.info("strToint转化出错:"+str);
            value = -1;
        }
        return value;
    }

    public static float strTofloat(String str) {
        float value;
        if (str == null || "".equals(str)) {
            return 0;
        }
        try {
            value = Float.parseFloat(str);
        } catch (Exception e) {
            logger.info("strToint转化出错:"+str);
            value = -1;
        }
        return value;
    }

}
