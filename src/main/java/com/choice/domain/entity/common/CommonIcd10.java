package com.choice.domain.entity.common;


public class CommonIcd10 {
    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private String zhenDuanicd;

    /**
     *
     */
    private String zhenDuanName;

    /**
     *
     */
    private String zhenDuanPinYin;

    /**
     *
     */
    private String zhenDuanType;

    /**
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return zhen_duanicd
     */
    public String getZhenDuanicd() {
        return zhenDuanicd;
    }

    /**
     *
     * @param zhenDuanicd
     */
    public void setZhenDuanicd(String zhenDuanicd) {
        this.zhenDuanicd = zhenDuanicd == null ? null : zhenDuanicd.trim();
    }

    /**
     *
     * @return zhen_duan_name
     */
    public String getZhenDuanName() {
        return zhenDuanName;
    }

    /**
     *
     * @param zhenDuanName
     */
    public void setZhenDuanName(String zhenDuanName) {
        this.zhenDuanName = zhenDuanName == null ? null : zhenDuanName.trim();
    }

    /**
     *
     * @return zhen_duan_pin_yin
     */
    public String getZhenDuanPinYin() {
        return zhenDuanPinYin;
    }

    /**
     *
     * @param zhenDuanPinYin
     */
    public void setZhenDuanPinYin(String zhenDuanPinYin) {
        this.zhenDuanPinYin = zhenDuanPinYin == null ? null : zhenDuanPinYin.trim();
    }

    /**
     *
     * @return zhen_duan_type
     */
    public String getZhenDuanType() {
        return zhenDuanType;
    }

    /**
     *
     * @param zhenDuanType
     */
    public void setZhenDuanType(String zhenDuanType) {
        this.zhenDuanType = zhenDuanType == null ? null : zhenDuanType.trim();
    }
}