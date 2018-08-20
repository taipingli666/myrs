package com.choice.domain.entity.common;

import com.choice.domain.entity.base.BaseEntity;

public class CommonPopulation extends BaseEntity{
    private Integer pId;

    private String areaCode;

    private Integer pNumber;

    private Integer countYear;

	public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getpNumber() {
        return pNumber;
    }

    public void setpNumber(Integer pNumber) {
        this.pNumber = pNumber;
    }

    public Integer getCountYear() {
        return countYear;
    }

    public void setCountYear(Integer countYear) {
        this.countYear = countYear;
    }

}