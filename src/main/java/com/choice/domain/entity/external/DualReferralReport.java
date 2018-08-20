package com.choice.domain.entity.external;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class DualReferralReport implements Serializable {
    private Long id;

    /**
     * 区域编码
     */
    private String areaCode;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 上级编码
     */
    private String parentCode;

    /**
     * 统计日期
     */
    private Date countDate;

    /**
     * 转出统计数量
     */
    private Long referralOutQuantity;

    /**
     * 转入统计数量
     */
    private Long referralInQuantity;

    /**
     * 年度
     */
    private String year;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Date getCountDate() {
        return countDate;
    }

    public void setCountDate(Date countDate) {
        this.countDate = countDate;
    }

    public Long getReferralOutQuantity() {
        return referralOutQuantity;
    }

    public void setReferralOutQuantity(Long referralOutQuantity) {
        this.referralOutQuantity = referralOutQuantity;
    }

    public Long getReferralInQuantity() {
        return referralInQuantity;
    }

    public void setReferralInQuantity(Long referralInQuantity) {
        this.referralInQuantity = referralInQuantity;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}