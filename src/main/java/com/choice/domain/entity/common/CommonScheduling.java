package com.choice.domain.entity.common;

public class CommonScheduling {
    /**
     * 流水号
     */
    private Long liushuihao;

    /**
     * 机构编号
     */
    private String jigoubh;

    /**
     * 
     */
    private Long yingyongbh;

    /**
     * 院内排版ID
     */
    private String yuanneipbid;

    /**
     * 科室编号
     */
    private String keshibh;

    /**
     * 科室名称
     */
    private String keshimc;

    /**
     * 医生编号
     */
    private String yishengbh;

    /**
     * 医生姓名
     */
    private String yishengxm;

    /**
     * 坐诊日期
     */
    private String zuozhenrq;

    /**
     * 挂号类型
     */
    private String guahaolx;

    /**
     * 挂号费
     */
    private Float guahaofei;

    /**
     * 门诊时段编码
     */
    private String menzhensdbm;

    /**
     * 开始时间
     */
    private Integer kaishisj;

    /**
     * 结束时间
     */
    private Integer jieshusj;

    /**
     * 门诊位置
     */
    private String menzhenwz;

    /**
     * 号源总数量
     */
    private Float haoyuanzsl;

    /**
     * 号源列表
     */
    private String haoyuanlb;

    /**
     * 挂号总数量
     */
    private Float guahaozsl;

    /**
     * 挂号列表
     */
    private String guahaolb;

    /**
     * 停诊标志
     */
    private String tingzhenbz;

    /**
     * 备注说明
     */
    private String beizhusm;

    /**
     * 删除标志
     */
    private String shanchubz;

    /**
     * 专家判别标志
     */
    private String zhuanjiapbbz;

    /**
     * 上传标志0：未上传 1：以上传
     */
    private String shangchuanbz;

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
     * 
     * @return yingyongbh 
     */
    public Long getYingyongbh() {
        return yingyongbh;
    }

    /**
     * 
     * @param yingyongbh 
     */
    public void setYingyongbh(Long yingyongbh) {
        this.yingyongbh = yingyongbh;
    }

    /**
     * 院内排版ID
     * @return yuanneipbid 院内排版ID
     */
    public String getYuanneipbid() {
        return yuanneipbid;
    }

    /**
     * 院内排版ID
     * @param yuanneipbid 院内排版ID
     */
    public void setYuanneipbid(String yuanneipbid) {
        this.yuanneipbid = yuanneipbid == null ? null : yuanneipbid.trim();
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
     * 医生编号
     * @return yishengbh 医生编号
     */
    public String getYishengbh() {
        return yishengbh;
    }

    /**
     * 医生编号
     * @param yishengbh 医生编号
     */
    public void setYishengbh(String yishengbh) {
        this.yishengbh = yishengbh == null ? null : yishengbh.trim();
    }

    /**
     * 医生姓名
     * @return yishengxm 医生姓名
     */
    public String getYishengxm() {
        return yishengxm;
    }

    /**
     * 医生姓名
     * @param yishengxm 医生姓名
     */
    public void setYishengxm(String yishengxm) {
        this.yishengxm = yishengxm == null ? null : yishengxm.trim();
    }

    /**
     * 坐诊日期
     * @return zuozhenrq 坐诊日期
     */
    public String getZuozhenrq() {
        return zuozhenrq;
    }

    /**
     * 坐诊日期
     * @param zuozhenrq 坐诊日期
     */
    public void setZuozhenrq(String zuozhenrq) {
        this.zuozhenrq = zuozhenrq == null ? null : zuozhenrq.trim();
    }

    /**
     * 挂号类型
     * @return guahaolx 挂号类型
     */
    public String getGuahaolx() {
        return guahaolx;
    }

    /**
     * 挂号类型
     * @param guahaolx 挂号类型
     */
    public void setGuahaolx(String guahaolx) {
        this.guahaolx = guahaolx == null ? null : guahaolx.trim();
    }

    /**
     * 挂号费
     * @return guahaofei 挂号费
     */
    public Float getGuahaofei() {
        return guahaofei;
    }

    /**
     * 挂号费
     * @param guahaofei 挂号费
     */
    public void setGuahaofei(Float guahaofei) {
        this.guahaofei = guahaofei;
    }

    /**
     * 门诊时段编码
     * @return menzhensdbm 门诊时段编码
     */
    public String getMenzhensdbm() {
        return menzhensdbm;
    }

    /**
     * 门诊时段编码
     * @param menzhensdbm 门诊时段编码
     */
    public void setMenzhensdbm(String menzhensdbm) {
        this.menzhensdbm = menzhensdbm == null ? null : menzhensdbm.trim();
    }

    /**
     * 开始时间
     * @return kaishisj 开始时间
     */
    public Integer getKaishisj() {
        return kaishisj;
    }

    /**
     * 开始时间
     * @param kaishisj 开始时间
     */
    public void setKaishisj(Integer kaishisj) {
        this.kaishisj = kaishisj;
    }

    /**
     * 结束时间
     * @return jieshusj 结束时间
     */
    public Integer getJieshusj() {
        return jieshusj;
    }

    /**
     * 结束时间
     * @param jieshusj 结束时间
     */
    public void setJieshusj(Integer jieshusj) {
        this.jieshusj = jieshusj;
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
     * 号源总数量
     * @return haoyuanzsl 号源总数量
     */
    public Float getHaoyuanzsl() {
        return haoyuanzsl;
    }

    /**
     * 号源总数量
     * @param haoyuanzsl 号源总数量
     */
    public void setHaoyuanzsl(Float haoyuanzsl) {
        this.haoyuanzsl = haoyuanzsl;
    }

    /**
     * 号源列表
     * @return haoyuanlb 号源列表
     */
    public String getHaoyuanlb() {
        return haoyuanlb;
    }

    /**
     * 号源列表
     * @param haoyuanlb 号源列表
     */
    public void setHaoyuanlb(String haoyuanlb) {
        this.haoyuanlb = haoyuanlb == null ? null : haoyuanlb.trim();
    }

    /**
     * 挂号总数量
     * @return guahaozsl 挂号总数量
     */
    public Float getGuahaozsl() {
        return guahaozsl;
    }

    /**
     * 挂号总数量
     * @param guahaozsl 挂号总数量
     */
    public void setGuahaozsl(Float guahaozsl) {
        this.guahaozsl = guahaozsl;
    }

    /**
     * 挂号列表
     * @return guahaolb 挂号列表
     */
    public String getGuahaolb() {
        return guahaolb;
    }

    /**
     * 挂号列表
     * @param guahaolb 挂号列表
     */
    public void setGuahaolb(String guahaolb) {
        this.guahaolb = guahaolb == null ? null : guahaolb.trim();
    }

    /**
     * 停诊标志
     * @return tingzhenbz 停诊标志
     */
    public String getTingzhenbz() {
        return tingzhenbz;
    }

    /**
     * 停诊标志
     * @param tingzhenbz 停诊标志
     */
    public void setTingzhenbz(String tingzhenbz) {
        this.tingzhenbz = tingzhenbz == null ? null : tingzhenbz.trim();
    }

    /**
     * 备注说明
     * @return beizhusm 备注说明
     */
    public String getBeizhusm() {
        return beizhusm;
    }

    /**
     * 备注说明
     * @param beizhusm 备注说明
     */
    public void setBeizhusm(String beizhusm) {
        this.beizhusm = beizhusm == null ? null : beizhusm.trim();
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
     * 专家判别标志
     * @return zhuanjiapbbz 专家判别标志
     */
    public String getZhuanjiapbbz() {
        return zhuanjiapbbz;
    }

    /**
     * 专家判别标志
     * @param zhuanjiapbbz 专家判别标志
     */
    public void setZhuanjiapbbz(String zhuanjiapbbz) {
        this.zhuanjiapbbz = zhuanjiapbbz == null ? null : zhuanjiapbbz.trim();
    }

    /**
     * 上传标志0：未上传 1：以上传
     * @return shangchuanbz 上传标志0：未上传 1：以上传
     */
    public String getShangchuanbz() {
        return shangchuanbz;
    }

    /**
     * 上传标志0：未上传 1：以上传
     * @param shangchuanbz 上传标志0：未上传 1：以上传
     */
    public void setShangchuanbz(String shangchuanbz) {
        this.shangchuanbz = shangchuanbz == null ? null : shangchuanbz.trim();
    }
}