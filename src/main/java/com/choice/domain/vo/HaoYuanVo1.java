package com.choice.domain.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 号源的包装类
 * Created by duhuo on 2017/7/25.
 */
public class HaoYuanVo1 {
    private String jigoubianhao;
    private String yuanneipbid;
    private String guahaolx;    //挂号类型
    private String guahaofy;    //挂号费用
    private String keshimc; //科室名称
    private String keshibh; //科室编号
    private String zhuanjiapbbz; //专家排版标志
    private String yishengxm;   //医生姓名
    private String yishengbh;   //医生编号
    private String zuozhenrq; //坐诊日期
    private String guahaolb; //挂号类别
    private String zhibanlb; //值班时间段
    private String menzhenwz;//门诊位置

    public String getMenzhenwz() {
        return menzhenwz;
    }

    public void setMenzhenwz(String menzhenwz) {
        this.menzhenwz = menzhenwz;
    }

    private List lsit = new ArrayList();  //里面放顺序号的对象

    public String getJigoubianhao() {
        return jigoubianhao;
    }

    public void setJigoubianhao(String jigoubianhao) {
        this.jigoubianhao = jigoubianhao;
    }

    public String getYuanneipbid() {
        return yuanneipbid;
    }

    public void setYuanneipbid(String yuanneipbid) {
        this.yuanneipbid = yuanneipbid;
    }

    public String getGuahaolx() {
        return guahaolx;
    }

    public void setGuahaolx(String guahaolx) {
        this.guahaolx = guahaolx;
    }

    public String getGuahaofy() {
        return guahaofy;
    }

    public void setGuahaofy(String guahaofy) {
        this.guahaofy = guahaofy;
    }

    public String getKeshimc() {
        return keshimc;
    }

    public void setKeshimc(String keshimc) {
        this.keshimc = keshimc;
    }

    public String getYishengxm() {
        return yishengxm;
    }

    public void setYishengxm(String yishengxm) {
        this.yishengxm = yishengxm;
    }

    public List getLsit() {
        return lsit;
    }

    public void setLsit(List lsit) {
        this.lsit = lsit;
    }

    public String getZuozhenrq() {
        return zuozhenrq;
    }

    public void setZuozhenrq(String zuozhenrq) {
        this.zuozhenrq = zuozhenrq;
    }

    public String getKeshibh() {
        return keshibh;
    }

    public void setKeshibh(String keshibh) {
        this.keshibh = keshibh;
    }

    public String getZhuanjiapbbz() {
        return zhuanjiapbbz;
    }

    public void setZhuanjiapbbz(String zhuanjiapbbz) {
        this.zhuanjiapbbz = zhuanjiapbbz;
    }

    public String getYishengbh() {
        return yishengbh;
    }

    public void setYishengbh(String yishengbh) {
        this.yishengbh = yishengbh;
    }

    public String getGuahaolb() {
        return guahaolb;
    }

    public void setGuahaolb(String guahaolb) {
        this.guahaolb = guahaolb;
    }

    public String getZhibanlb() {
        return zhibanlb;
    }

    public void setZhibanlb(String zhibanlb) {
        this.zhibanlb = zhibanlb;
    }
}
