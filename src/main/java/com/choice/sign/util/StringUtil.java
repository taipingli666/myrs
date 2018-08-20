package com.choice.sign.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by baiqian@myweimai.com on 2016/12/21.
 */
public class StringUtil {
    private StringUtil(){}

    public static boolean isEmpty(String target){
        if (null == target || "".equals(target)){
            return true;
        }
        return false;
    }

    /**
     * 去掉字符串中的特殊字符
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n"); // 有空格时:\\s*|\t|\r|\n
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static long strTolong(String str) {
        long value = 0;
        try {
            value = Long.parseLong(str);
        } catch (Exception e) {
            value = -1;
        }
        return value;
    }

}
