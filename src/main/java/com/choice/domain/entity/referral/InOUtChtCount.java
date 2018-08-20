package com.choice.domain.entity.referral;

/**
 * Created with IntelliJ IDEA.
 * User: 王涛 wt2510@163.com
 * 创建时间: 2017-6-15    10:27
 * 类描述：转入转出统计对象
 */
public class InOUtChtCount {
    private int num;        //销量
    private String month;    //月份
    private String total;  //

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
