package com.choice.domain.entity.external;

import java.util.Date;

public class BussConsultationSingle {
    /**
     * 申请状态 申请状态：1.填写问诊2.预约成功3.支付成功4.会诊中5.诊断完成6.退款申请7.已退款8.取消预约成功9.预约失效
     */
    public static final String SHENQINGZT_TIANXIEWZ = "1"; //填写问诊
    public static final String SHENQINGZT_YUYUECG = "2"; //预约成功
    public static final String SHENQINGZT_ZHIFUCG = "3"; //支付成功
    public static final String SHENQINGZT_ZHENDUANWC = "4"; //会诊完成
    public static final String SHENQINGZT_HUIZHENYCJS = "5"; //会诊异常结束
    public static final String SHENQINGZT_TUIKUANSQ = "6"; //退款申请
    public static final String SHENQINGZT_YITUIK= "7"; //已退款
    public static final String SHENQINGZT_QUXIAOYY= "8"; //取消预约
    /**
     * 会诊状态
     */
    public static final String HUIZHENZT_HUIZHENZ = "1";//会诊中
    public static final String HUIZHENZT_HUIZHENJS = "2"; //会诊结束
    public static final String HUIZHENZT_HUIZHENYCJS = "3"; //会诊异常结束

    /**
     * 流水号会诊id
     */
    private Long liushuihao;

    /**
     * 申请医院id
     */
    private String shenqingyyid;

    /**
     * 申请科室id
     */
    private String shenqingksid;

    /**
     * 申请医生id
     */
    private String shenqingysid;

    /**
     * 病人id
     */
    private String bingrenid;

    /**
     * 病情描述
     */
    private String bingqingms;

    /**
     * 病例信息
     */
    private String binglixx;

    /**
     * 会诊排班id
     */
    private String huizhenpbid;

    /**
     * 会诊状态 0 申请中 1.待安排2.待支付3.待会诊4.会诊中5.会诊结束6.已拒绝7.已关闭8已过期
     */
    private String huizhenzt;

    /**
     * 会诊医院id
     */
    private String huizhenyyid;

    /**
     * 会诊科室id
     */
    private String huizhenksid;

    /**
     * 会诊医生id
     */
    private String huizhenysid;

    /**
     * 预约类型 1.门诊 2.预约
     */
    private String yuyuelx;

    /**
     * 预约日期
     */
    private Date yuyuerq;

    /**
     * 预约时间段
     */
    private String yuyuesj;

    /**
     * 预约序号
     */
    private String yuyuexh;

    /**
     * 会诊费用
     */
    private String huizhenfy;

    /**
     * 申请时间
     */
    private Date shenqingsj;

    /**
     * 申请状态：1.填写问诊/2.申请/3.支付/4.问诊中/5.诊断完成
     */
    private String shenqingzt;

    /**
     * 支付标志 0 默认值  1.支付成功
     */
    private String zhifubz;

    /**
     * 支付流水号
     */
    private String zhifulsh;

    /**
     * 支付时间
     */
    private Date zhifusj;

    /**
     * 取消时间
     */
    private String quxiaosj;

    /**
     * 拒绝原因
     */
    private String jvjueyy;

    /**
     * 申请方状态0申请中 1.进入视频间 2.离开视频
     */
    private String shenqingfangzt;

    /**
     * 申请方开始时间
     */
    private Date shenqingfangkssj;

    /**
     * 申请方结束时间
     */
    private Date shenqingfangjssj;

    /**
     * 申请方提醒工号
     */
    private String shenqingfangtxgh;

    /**
     * 会诊方状态 0. 未开始1.开始会诊  2.会诊结束
     */
    private String huizhenfangzt;

    /**
     * 会诊方开始时间
     */
    private Date huizhenfangkssj;

    /**
     * 会诊方结束时间
     */
    private Date huizhenfangjssj;

    /**
     * 会诊方提醒工号
     */
    private String huizhenfangtxgh;

    /**
     * 诊断报告
     */
    private String zhenduanbg;

    /**
     * 申请医院名称
     */
    private String shenqingyymc;

    /**
     * 申请科室名称
     */
    private String shenqingksmc;

    /**
     * 申请医生名称
     */
    private String shenqingysmc;

    /**
     * 会诊医院名称
     */
    private String huizhenyymc;

    /**
     * 会诊科室名称
     */
    private String huizhenksmc;

    /**
     * 会诊医生名称
     */
    private String huizhenysmc;

    /**
     * 上下午标志
     */
    private String shangxiawbz;

    /**
     * 影像编号
     */
    private String yingxiangbh;
    
    /**
     * 手机号码
     */
    private String shoujihm;
    
    /**
     * 病人姓名
     */
    private String xingming;
    
    /**
     * 病人性别
     */
    private String xingbie;
    
    /**
     * 病人出生日期
     */
    private  String chushengrq;

    /**
     * 病人年龄
     */
    private String age;
    /**
     * 流水号会诊id
     * @return liushuihao 流水号会诊id
     */
    public Long getLiushuihao() {
        return liushuihao;
    }

    /**
     * 流水号会诊id
     * @param liushuihao 流水号会诊id
     */
    public void setLiushuihao(Long liushuihao) {
        this.liushuihao = liushuihao;
    }

    /**
     * 申请医院id
     * @return shenqingyyid 申请医院id
     */
    public String getShenqingyyid() {
        return shenqingyyid;
    }

    /**
     * 申请医院id
     * @param shenqingyyid 申请医院id
     */
    public void setShenqingyyid(String shenqingyyid) {
        this.shenqingyyid = shenqingyyid == null ? null : shenqingyyid.trim();
    }

    /**
     * 申请科室id
     * @return shenqingksid 申请科室id
     */
    public String getShenqingksid() {
        return shenqingksid;
    }

    /**
     * 申请科室id
     * @param shenqingksid 申请科室id
     */
    public void setShenqingksid(String shenqingksid) {
        this.shenqingksid = shenqingksid == null ? null : shenqingksid.trim();
    }

    /**
     * 申请医生id
     * @return shenqingysid 申请医生id
     */
    public String getShenqingysid() {
        return shenqingysid;
    }

    /**
     * 申请医生id
     * @param shenqingysid 申请医生id
     */
    public void setShenqingysid(String shenqingysid) {
        this.shenqingysid = shenqingysid == null ? null : shenqingysid.trim();
    }

    /**
     * 病人id
     * @return bingrenid 病人id
     */
    public String getBingrenid() {
        return bingrenid;
    }

    /**
     * 病人id
     * @param bingrenid 病人id
     */
    public void setBingrenid(String bingrenid) {
        this.bingrenid = bingrenid == null ? null : bingrenid.trim();
    }

    /**
     * 病情描述
     * @return bingqingms 病情描述
     */
    public String getBingqingms() {
        return bingqingms;
    }

    /**
     * 病情描述
     * @param bingqingms 病情描述
     */
    public void setBingqingms(String bingqingms) {
        this.bingqingms = bingqingms == null ? null : bingqingms.trim();
    }

    /**
     * 病例信息
     * @return binglixx 病例信息
     */
    public String getBinglixx() {
        return binglixx;
    }

    /**
     * 病例信息
     * @param binglixx 病例信息
     */
    public void setBinglixx(String binglixx) {
        this.binglixx = binglixx == null ? null : binglixx.trim();
    }

    /**
     * 会诊排班id
     * @return huizhenpbid 会诊排班id
     */
    public String getHuizhenpbid() {
        return huizhenpbid;
    }

    /**
     * 会诊排班id
     * @param huizhenpbid 会诊排班id
     */
    public void setHuizhenpbid(String huizhenpbid) {
        this.huizhenpbid = huizhenpbid == null ? null : huizhenpbid.trim();
    }

    /**
     * 会诊状态 0 申请中 1.待安排2.待支付3.待会诊4.会诊中5.会诊结束6.已拒绝7.已关闭8已过期
     * @return huizhenzt 会诊状态 0 申请中 1.待安排2.待支付3.待会诊4.会诊中5.会诊结束6.已拒绝7.已关闭8已过期
     */
    public String getHuizhenzt() {
        return huizhenzt;
    }

    /**
     * 会诊状态 0 申请中 1.待安排2.待支付3.待会诊4.会诊中5.会诊结束6.已拒绝7.已关闭8已过期
     * @param huizhenzt 会诊状态 0 申请中 1.待安排2.待支付3.待会诊4.会诊中5.会诊结束6.已拒绝7.已关闭8已过期
     */
    public void setHuizhenzt(String huizhenzt) {
        this.huizhenzt = huizhenzt == null ? null : huizhenzt.trim();
    }

    /**
     * 会诊医院id
     * @return huizhenyyid 会诊医院id
     */
    public String getHuizhenyyid() {
        return huizhenyyid;
    }

    /**
     * 会诊医院id
     * @param huizhenyyid 会诊医院id
     */
    public void setHuizhenyyid(String huizhenyyid) {
        this.huizhenyyid = huizhenyyid == null ? null : huizhenyyid.trim();
    }

    /**
     * 会诊科室id
     * @return huizhenksid 会诊科室id
     */
    public String getHuizhenksid() {
        return huizhenksid;
    }

    /**
     * 会诊科室id
     * @param huizhenksid 会诊科室id
     */
    public void setHuizhenksid(String huizhenksid) {
        this.huizhenksid = huizhenksid == null ? null : huizhenksid.trim();
    }

    /**
     * 会诊医生id
     * @return huizhenysid 会诊医生id
     */
    public String getHuizhenysid() {
        return huizhenysid;
    }

    /**
     * 会诊医生id
     * @param huizhenysid 会诊医生id
     */
    public void setHuizhenysid(String huizhenysid) {
        this.huizhenysid = huizhenysid == null ? null : huizhenysid.trim();
    }

    /**
     * 预约类型 1.门诊 2.预约
     * @return yuyuelx 预约类型 1.门诊 2.预约
     */
    public String getYuyuelx() {
        return yuyuelx;
    }

    /**
     * 预约类型 1.门诊 2.预约
     * @param yuyuelx 预约类型 1.门诊 2.预约
     */
    public void setYuyuelx(String yuyuelx) {
        this.yuyuelx = yuyuelx == null ? null : yuyuelx.trim();
    }

    /**
     * 预约日期
     * @return yuyuerq 预约日期
     */
    public Date getYuyuerq() {
        return yuyuerq;
    }

    /**
     * 预约日期
     * @param yuyuerq 预约日期
     */
    public void setYuyuerq(Date yuyuerq) {
        this.yuyuerq = yuyuerq;
    }

    /**
     * 预约时间段
     * @return yuyuesj 预约时间段
     */
    public String getYuyuesj() {
        return yuyuesj;
    }

    /**
     * 预约时间段
     * @param yuyuesj 预约时间段
     */
    public void setYuyuesj(String yuyuesj) {
        this.yuyuesj = yuyuesj == null ? null : yuyuesj.trim();
    }

    /**
     * 预约序号
     * @return yuyuexh 预约序号
     */
    public String getYuyuexh() {
        return yuyuexh;
    }

    /**
     * 预约序号
     * @param yuyuexh 预约序号
     */
    public void setYuyuexh(String yuyuexh) {
        this.yuyuexh = yuyuexh == null ? null : yuyuexh.trim();
    }

    /**
     * 会诊费用
     * @return huizhenfy 会诊费用
     */
    public String getHuizhenfy() {
        return huizhenfy;
    }

    /**
     * 会诊费用
     * @param huizhenfy 会诊费用
     */
    public void setHuizhenfy(String huizhenfy) {
        this.huizhenfy = huizhenfy == null ? null : huizhenfy.trim();
    }

    /**
     * 申请时间
     * @return shenqingsj 申请时间
     */
    public Date getShenqingsj() {
        return shenqingsj;
    }

    /**
     * 申请时间
     * @param shenqingsj 申请时间
     */
    public void setShenqingsj(Date shenqingsj) {
        this.shenqingsj = shenqingsj;
    }

    /**
     * 申请状态：1.填写问诊/2.申请/3.支付/4.问诊中/5.诊断完成
     * @return shenqingzt 申请状态：1.填写问诊/2.申请/3.支付/4.问诊中/5.诊断完成
     */
    public String getShenqingzt() {
        return shenqingzt;
    }

    /**
     * 申请状态：1.填写问诊/2.申请/3.支付/4.问诊中/5.诊断完成
     * @param shenqingzt 申请状态：1.填写问诊/2.申请/3.支付/4.问诊中/5.诊断完成
     */
    public void setShenqingzt(String shenqingzt) {
        this.shenqingzt = shenqingzt == null ? null : shenqingzt.trim();
    }

    /**
     * 支付标志 0 默认值  1.支付成功
     * @return zhifubz 支付标志 0 默认值  1.支付成功
     */
    public String getZhifubz() {
        return zhifubz;
    }

    /**
     * 支付标志 0 默认值  1.支付成功
     * @param zhifubz 支付标志 0 默认值  1.支付成功
     */
    public void setZhifubz(String zhifubz) {
        this.zhifubz = zhifubz == null ? null : zhifubz.trim();
    }

    /**
     * 支付流水号
     * @return zhifulsh 支付流水号
     */
    public String getZhifulsh() {
        return zhifulsh;
    }

    /**
     * 支付流水号
     * @param zhifulsh 支付流水号
     */
    public void setZhifulsh(String zhifulsh) {
        this.zhifulsh = zhifulsh == null ? null : zhifulsh.trim();
    }

    /**
     * 支付时间
     * @return zhifusj 支付时间
     */
    public Date getZhifusj() {
        return zhifusj;
    }

    /**
     * 支付时间
     * @param zhifusj 支付时间
     */
    public void setZhifusj(Date zhifusj) {
        this.zhifusj = zhifusj;
    }

    /**
     * 取消时间
     * @return quxiaosj 取消时间
     */
    public String getQuxiaosj() {
        return quxiaosj;
    }

    /**
     * 取消时间
     * @param quxiaosj 取消时间
     */
    public void setQuxiaosj(String quxiaosj) {
        this.quxiaosj = quxiaosj == null ? null : quxiaosj.trim();
    }

    /**
     * 拒绝原因
     * @return jvjueyy 拒绝原因
     */
    public String getJvjueyy() {
        return jvjueyy;
    }

    /**
     * 拒绝原因
     * @param jvjueyy 拒绝原因
     */
    public void setJvjueyy(String jvjueyy) {
        this.jvjueyy = jvjueyy == null ? null : jvjueyy.trim();
    }

    /**
     * 申请方状态0申请中 1.进入视频间 2.离开视频
     * @return shenqingfangzt 申请方状态0申请中 1.进入视频间 2.离开视频
     */
    public String getShenqingfangzt() {
        return shenqingfangzt;
    }

    /**
     * 申请方状态0申请中 1.进入视频间 2.离开视频
     * @param shenqingfangzt 申请方状态0申请中 1.进入视频间 2.离开视频
     */
    public void setShenqingfangzt(String shenqingfangzt) {
        this.shenqingfangzt = shenqingfangzt == null ? null : shenqingfangzt.trim();
    }

    /**
     * 申请方开始时间
     * @return shenqingfangkssj 申请方开始时间
     */
    public Date getShenqingfangkssj() {
        return shenqingfangkssj;
    }

    /**
     * 申请方开始时间
     * @param shenqingfangkssj 申请方开始时间
     */
    public void setShenqingfangkssj(Date shenqingfangkssj) {
        this.shenqingfangkssj = shenqingfangkssj;
    }

    /**
     * 申请方结束时间
     * @return shenqingfangjssj 申请方结束时间
     */
    public Date getShenqingfangjssj() {
        return shenqingfangjssj;
    }

    /**
     * 申请方结束时间
     * @param shenqingfangjssj 申请方结束时间
     */
    public void setShenqingfangjssj(Date shenqingfangjssj) {
        this.shenqingfangjssj = shenqingfangjssj;
    }

    /**
     * 申请方提醒工号
     * @return shenqingfangtxgh 申请方提醒工号
     */
    public String getShenqingfangtxgh() {
        return shenqingfangtxgh;
    }

    /**
     * 申请方提醒工号
     * @param shenqingfangtxgh 申请方提醒工号
     */
    public void setShenqingfangtxgh(String shenqingfangtxgh) {
        this.shenqingfangtxgh = shenqingfangtxgh == null ? null : shenqingfangtxgh.trim();
    }

    /**
     * 会诊方状态 0. 未开始1.开始会诊  2.会诊结束
     * @return huizhenfangzt 会诊方状态 0. 未开始1.开始会诊  2.会诊结束
     */
    public String getHuizhenfangzt() {
        return huizhenfangzt;
    }

    /**
     * 会诊方状态 0. 未开始1.开始会诊  2.会诊结束
     * @param huizhenfangzt 会诊方状态 0. 未开始1.开始会诊  2.会诊结束
     */
    public void setHuizhenfangzt(String huizhenfangzt) {
        this.huizhenfangzt = huizhenfangzt == null ? null : huizhenfangzt.trim();
    }

    /**
     * 会诊方开始时间
     * @return huizhenfangkssj 会诊方开始时间
     */
    public Date getHuizhenfangkssj() {
        return huizhenfangkssj;
    }

    /**
     * 会诊方开始时间
     * @param huizhenfangkssj 会诊方开始时间
     */
    public void setHuizhenfangkssj(Date huizhenfangkssj) {
        this.huizhenfangkssj = huizhenfangkssj;
    }

    /**
     * 会诊方结束时间
     * @return huizhenfangjssj 会诊方结束时间
     */
    public Date getHuizhenfangjssj() {
        return huizhenfangjssj;
    }

    /**
     * 会诊方结束时间
     * @param huizhenfangjssj 会诊方结束时间
     */
    public void setHuizhenfangjssj(Date huizhenfangjssj) {
        this.huizhenfangjssj = huizhenfangjssj;
    }

    /**
     * 会诊方提醒工号
     * @return huizhenfangtxgh 会诊方提醒工号
     */
    public String getHuizhenfangtxgh() {
        return huizhenfangtxgh;
    }

    /**
     * 会诊方提醒工号
     * @param huizhenfangtxgh 会诊方提醒工号
     */
    public void setHuizhenfangtxgh(String huizhenfangtxgh) {
        this.huizhenfangtxgh = huizhenfangtxgh == null ? null : huizhenfangtxgh.trim();
    }

    /**
     * 诊断报告
     * @return zhenduanbg 诊断报告
     */
    public String getZhenduanbg() {
        return zhenduanbg;
    }

    /**
     * 诊断报告
     * @param zhenduanbg 诊断报告
     */
    public void setZhenduanbg(String zhenduanbg) {
        this.zhenduanbg = zhenduanbg == null ? null : zhenduanbg.trim();
    }

    /**
     * 申请医院名称
     * @return shenqingyymc 申请医院名称
     */
    public String getShenqingyymc() {
        return shenqingyymc;
    }

    /**
     * 申请医院名称
     * @param shenqingyymc 申请医院名称
     */
    public void setShenqingyymc(String shenqingyymc) {
        this.shenqingyymc = shenqingyymc == null ? null : shenqingyymc.trim();
    }

    /**
     * 申请科室名称
     * @return shenqingksmc 申请科室名称
     */
    public String getShenqingksmc() {
        return shenqingksmc;
    }

    /**
     * 申请科室名称
     * @param shenqingksmc 申请科室名称
     */
    public void setShenqingksmc(String shenqingksmc) {
        this.shenqingksmc = shenqingksmc == null ? null : shenqingksmc.trim();
    }

    /**
     * 申请医生名称
     * @return shenqingysmc 申请医生名称
     */
    public String getShenqingysmc() {
        return shenqingysmc;
    }

    /**
     * 申请医生名称
     * @param shenqingysmc 申请医生名称
     */
    public void setShenqingysmc(String shenqingysmc) {
        this.shenqingysmc = shenqingysmc == null ? null : shenqingysmc.trim();
    }

    /**
     * 会诊医院名称
     * @return huizhenyymc 会诊医院名称
     */
    public String getHuizhenyymc() {
        return huizhenyymc;
    }

    /**
     * 会诊医院名称
     * @param huizhenyymc 会诊医院名称
     */
    public void setHuizhenyymc(String huizhenyymc) {
        this.huizhenyymc = huizhenyymc == null ? null : huizhenyymc.trim();
    }

    /**
     * 会诊科室名称
     * @return huizhenksmc 会诊科室名称
     */
    public String getHuizhenksmc() {
        return huizhenksmc;
    }

    /**
     * 会诊科室名称
     * @param huizhenksmc 会诊科室名称
     */
    public void setHuizhenksmc(String huizhenksmc) {
        this.huizhenksmc = huizhenksmc == null ? null : huizhenksmc.trim();
    }

    /**
     * 会诊医生名称
     * @return huizhenysmc 会诊医生名称
     */
    public String getHuizhenysmc() {
        return huizhenysmc;
    }

    /**
     * 会诊医生名称
     * @param huizhenysmc 会诊医生名称
     */
    public void setHuizhenysmc(String huizhenysmc) {
        this.huizhenysmc = huizhenysmc == null ? null : huizhenysmc.trim();
    }

    public String getShangxiawbz() {
        return shangxiawbz;
    }

    public void setShangxiawbz(String shangxiawbz) {
        this.shangxiawbz = shangxiawbz;
    }

    public String getYingxiangbh() {
        return yingxiangbh;
    }

    public void setYingxiangbh(String yingxiangbh) {
        this.yingxiangbh = yingxiangbh;
    }

    public String getShoujihm() {
		return shoujihm;
	}

	public void setShoujihm(String shoujihm) {
		this.shoujihm = shoujihm;
	}

	public String getXingming() {
		return xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "BussConsultationSingle{" +
                "liushuihao=" + liushuihao +
                ", shenqingyyid='" + shenqingyyid + '\'' +
                ", shenqingksid='" + shenqingksid + '\'' +
                ", shenqingysid='" + shenqingysid + '\'' +
                ", bingrenid='" + bingrenid + '\'' +
                ", bingqingms='" + bingqingms + '\'' +
                ", binglixx='" + binglixx + '\'' +
                ", huizhenpbid='" + huizhenpbid + '\'' +
                ", huizhenzt='" + huizhenzt + '\'' +
                ", huizhenyyid='" + huizhenyyid + '\'' +
                ", huizhenksid='" + huizhenksid + '\'' +
                ", huizhenysid='" + huizhenysid + '\'' +
                ", yuyuelx='" + yuyuelx + '\'' +
                ", yuyuerq=" + yuyuerq +
                ", yuyuesj='" + yuyuesj + '\'' +
                ", yuyuexh='" + yuyuexh + '\'' +
                ", huizhenfy='" + huizhenfy + '\'' +
                ", shenqingsj=" + shenqingsj +
                ", shenqingzt='" + shenqingzt + '\'' +
                ", zhifubz='" + zhifubz + '\'' +
                ", zhifulsh='" + zhifulsh + '\'' +
                ", zhifusj=" + zhifusj +
                ", quxiaosj='" + quxiaosj + '\'' +
                ", jvjueyy='" + jvjueyy + '\'' +
                ", shenqingfangzt='" + shenqingfangzt + '\'' +
                ", shenqingfangkssj=" + shenqingfangkssj +
                ", shenqingfangjssj=" + shenqingfangjssj +
                ", shenqingfangtxgh='" + shenqingfangtxgh + '\'' +
                ", huizhenfangzt='" + huizhenfangzt + '\'' +
                ", huizhenfangkssj=" + huizhenfangkssj +
                ", huizhenfangjssj=" + huizhenfangjssj +
                ", huizhenfangtxgh='" + huizhenfangtxgh + '\'' +
                ", zhenduanbg='" + zhenduanbg + '\'' +
                ", shenqingyymc='" + shenqingyymc + '\'' +
                ", shenqingksmc='" + shenqingksmc + '\'' +
                ", shenqingysmc='" + shenqingysmc + '\'' +
                ", huizhenyymc='" + huizhenyymc + '\'' +
                ", huizhenksmc='" + huizhenksmc + '\'' +
                ", huizhenysmc='" + huizhenysmc + '\'' +
                ", shangxiawbz='" + shangxiawbz + '\'' +
                ", yingxiangbh='" + yingxiangbh + '\'' +
                ", shoujihm='" + shoujihm + '\'' +
                ", xingming='" + xingming + '\'' +
                ", xingbie='" + xingbie + '\'' +
                ", chushengrq='" + chushengrq + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}