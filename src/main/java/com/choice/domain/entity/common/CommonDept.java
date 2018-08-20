package com.choice.domain.entity.common;
import java.util.Date;

public class CommonDept {
    /**
     * 流水号
     */
    private Long liushuihao;

    /**
     * 机构编号
     */
    private String jigoubh;

    /**
     * 院区编号
     */
    private String yuanqubh;

    /**
     * 科室编号
     */
    private String keshibh;

    /**
     * 科室名称
     */
    private String keshimc;

    /**
     * 科室介绍
     */
    private String keshijj;

    /**
     * 门诊位置
     */
    private String menzhenwz;

    /**
     * 删除标志
     */
    private String shanchubz;

    /**
     * 时间戳
     */
    private Date shijianchuo;

    /**
     * 科主任（对应docter表医生代码）
     */
    private String kezhuren;

    /**
     * 科主任名称
     */
    private String mingcheng;

    /**
     * 科室类别
     */
    private String keshilb;

    /**
     * 门诊标志
     */
    private String menzhenbz;

    /**
     * 急诊标志
     */
    private String jizhenbz;

    /**
     * 住院标志
     */
    private String zhuyuanbz;

    /**
     * 联系电话
     */
    private String lianxidh;

    /**
     * 门诊类别
     */
    private String menzhenlb;

    /**
     * 会诊科室标记
     */
    private String huizhenksbj;

    /**
     * 流水号
     * @return liushuihao 流水号
     */
    public Long getLiushuihao() {
        return liushuihao;
    }

    /**
     * 流水号
     * @param liushuihao 流水号
     */
    public void setLiushuihao(Long liushuihao) {
        this.liushuihao = liushuihao;
    }

    /**
     * 机构编号
     * @return jigoubh 机构编号
     */
    public String getJigoubh() {
        return jigoubh;
    }

    /**
     * 机构编号
     * @param jigoubh 机构编号
     */
    public void setJigoubh(String jigoubh) {
        this.jigoubh = jigoubh == null ? null : jigoubh.trim();
    }

    /**
     * 院区编号
     * @return yuanqubh 院区编号
     */
    public String getYuanqubh() {
        return yuanqubh;
    }

    /**
     * 院区编号
     * @param yuanqubh 院区编号
     */
    public void setYuanqubh(String yuanqubh) {
        this.yuanqubh = yuanqubh == null ? null : yuanqubh.trim();
    }

    /**
     * 科室编号
     * @return keshibh 科室编号
     */
    public String getKeshibh() {
        return keshibh;
    }

    /**
     * 科室编号
     * @param keshibh 科室编号
     */
    public void setKeshibh(String keshibh) {
        this.keshibh = keshibh == null ? null : keshibh.trim();
    }

    /**
     * 科室名称
     * @return keshimc 科室名称
     */
    public String getKeshimc() {
        return keshimc;
    }

    /**
     * 科室名称
     * @param keshimc 科室名称
     */
    public void setKeshimc(String keshimc) {
        this.keshimc = keshimc == null ? null : keshimc.trim();
    }

    /**
     * 科室介绍
     * @return keshijj 科室介绍
     */
    public String getKeshijj() {
        return keshijj;
    }

    /**
     * 科室介绍
     * @param keshijj 科室介绍
     */
    public void setKeshijj(String keshijj) {
        this.keshijj = keshijj == null ? null : keshijj.trim();
    }

    /**
     * 门诊位置
     * @return menzhenwz 门诊位置
     */
    public String getMenzhenwz() {
        return menzhenwz;
    }

    /**
     * 门诊位置
     * @param menzhenwz 门诊位置
     */
    public void setMenzhenwz(String menzhenwz) {
        this.menzhenwz = menzhenwz == null ? null : menzhenwz.trim();
    }

    /**
     * 删除标志
     * @return shanchubz 删除标志
     */
    public String getShanchubz() {
        return shanchubz;
    }

    /**
     * 删除标志
     * @param shanchubz 删除标志
     */
    public void setShanchubz(String shanchubz) {
        this.shanchubz = shanchubz == null ? null : shanchubz.trim();
    }

    /**
     * 时间戳
     * @return shijianchuo 时间戳
     */
    public Date getShijianchuo() {
        return shijianchuo;
    }

    /**
     * 时间戳
     * @param shijianchuo 时间戳
     */
    public void setShijianchuo(Date shijianchuo) {
        this.shijianchuo = shijianchuo;
    }

    /**
     * 科主任（对应docter表医生代码）
     * @return kezhuren 科主任（对应docter表医生代码）
     */
    public String getKezhuren() {
        return kezhuren;
    }

    /**
     * 科主任（对应docter表医生代码）
     * @param kezhuren 科主任（对应docter表医生代码）
     */
    public void setKezhuren(String kezhuren) {
        this.kezhuren = kezhuren == null ? null : kezhuren.trim();
    }

    /**
     * 科主任名称
     * @return mingcheng 科主任名称
     */
    public String getMingcheng() {
        return mingcheng;
    }

    /**
     * 科主任名称
     * @param mingcheng 科主任名称
     */
    public void setMingcheng(String mingcheng) {
        this.mingcheng = mingcheng == null ? null : mingcheng.trim();
    }

    /**
     * 科室类别
     * @return keshilb 科室类别
     */
    public String getKeshilb() {
        return keshilb;
    }

    /**
     * 科室类别
     * @param keshilb 科室类别
     */
    public void setKeshilb(String keshilb) {
        this.keshilb = keshilb == null ? null : keshilb.trim();
    }

    /**
     * 门诊标志
     * @return menzhenbz 门诊标志
     */
    public String getMenzhenbz() {
        return menzhenbz;
    }

    /**
     * 门诊标志
     * @param menzhenbz 门诊标志
     */
    public void setMenzhenbz(String menzhenbz) {
        this.menzhenbz = menzhenbz == null ? null : menzhenbz.trim();
    }

    /**
     * 急诊标志
     * @return jizhenbz 急诊标志
     */
    public String getJizhenbz() {
        return jizhenbz;
    }

    /**
     * 急诊标志
     * @param jizhenbz 急诊标志
     */
    public void setJizhenbz(String jizhenbz) {
        this.jizhenbz = jizhenbz == null ? null : jizhenbz.trim();
    }

    /**
     * 住院标志
     * @return zhuyuanbz 住院标志
     */
    public String getZhuyuanbz() {
        return zhuyuanbz;
    }

    /**
     * 住院标志
     * @param zhuyuanbz 住院标志
     */
    public void setZhuyuanbz(String zhuyuanbz) {
        this.zhuyuanbz = zhuyuanbz == null ? null : zhuyuanbz.trim();
    }

    /**
     * 联系电话
     * @return lianxidh 联系电话
     */
    public String getLianxidh() {
        return lianxidh;
    }

    /**
     * 联系电话
     * @param lianxidh 联系电话
     */
    public void setLianxidh(String lianxidh) {
        this.lianxidh = lianxidh == null ? null : lianxidh.trim();
    }

    /**
     * 门诊大科室类别
     * @return
     */
    public String getMenzhenlb() {
        return menzhenlb;
    }
    public void setMenzhenlb(String menzhenlb) {
        this.menzhenlb = menzhenlb;
    }

    public String getHuizhenksbj() {
        return huizhenksbj;
    }

    public void setHuizhenksbj(String huizhenksbj) {
        this.huizhenksbj = huizhenksbj;
    }

    @Override
    public String toString() {
        return "CommonDept{" +
                "liushuihao=" + liushuihao +
                ", jigoubh='" + jigoubh + '\'' +
                ", keshibh='" + keshibh + '\'' +
                ", keshimc='" + keshimc + '\'' +
                ", keshilb='" + keshilb + '\'' +
                ", menzhenlb='" + menzhenlb + '\'' +
                ", huizhenksbj='" + huizhenksbj + '\'' +
                '}';
    }
}