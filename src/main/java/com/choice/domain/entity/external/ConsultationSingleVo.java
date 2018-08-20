package com.choice.domain.entity.external;

/**
 * 会诊单的包装类
 */
public class ConsultationSingleVo extends BussConsultationSingle{
    //身份证号
    private String shenfenzh;
    //姓名
    private String xingming;
    //手机号
    private String shoujihm;

    //性别
    private String xingbie;
    //出生日期
    private String chushengrq;

    //是否在线
    private String onLine;

    //申请方头像路径
    private String shenqingystx;
    //会诊方
    private String huizhenystx;
    //会诊医生电话
    private String huizhenysdh;
    //申请医生电话
    private String shenqingysdh;

    public String getShenfenzh() {
        return shenfenzh;
    }

    public void setShenfenzh(String shenfenzh) {
        this.shenfenzh = shenfenzh;
    }

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    public String getShoujihm() {
        return shoujihm;
    }

    public void setShoujihm(String shoujihm) {
        this.shoujihm = shoujihm;
    }

    public String getXingbie() {
        return xingbie;
    }

    public void setXingbie(String xingbie) {
        this.xingbie = xingbie;
    }

    public String getChushengrq() {
        return chushengrq;
    }

    public void setChushengrq(String chushengrq) {
        this.chushengrq = chushengrq;
    }

    public String getOnLine() {
        return onLine;
    }

    public void setOnLine(String onLine) {
        this.onLine = onLine;
    }

    public String getShenqingystx() {
        return shenqingystx;
    }

    public void setShenqingystx(String shenqingystx) {
        this.shenqingystx = shenqingystx;
    }

    public String getHuizhenystx() {
        return huizhenystx;
    }

    public void setHuizhenystx(String huizhenystx) {
        this.huizhenystx = huizhenystx;
    }

    public String getHuizhenysdh() {
        return huizhenysdh;
    }

    public void setHuizhenysdh(String huizhenysdh) {
        this.huizhenysdh = huizhenysdh;
    }

    public String getShenqingysdh() {
        return shenqingysdh;
    }

    public void setShenqingysdh(String shenqingysdh) {
        this.shenqingysdh = shenqingysdh;
    }
}
