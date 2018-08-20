package com.choice.domain.entity.external;

import java.io.Serializable;

/**
 * @author 
 */

public class RegisterSourceInfo implements Serializable {

    private Long id;

    /**
     * 排班id
     */
    private Long scheduleCode;

    /**
     * 号源序号
     */
    private Integer sequenceNumber;

    /**
     * 就诊起始时间
     */
    private String visitStart;

    /**
     * 就诊结束时间
     */
    private String visitEnd;

    /**
     * 号源状态 0未使用 ，1已使用
     */
    private String sourceStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScheduleCode() {
        return scheduleCode;
    }

    public void setScheduleCode(Long scheduleCode) {
        this.scheduleCode = scheduleCode;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getVisitStart() {
        return visitStart;
    }

    public void setVisitStart(String visitStart) {
        this.visitStart = visitStart;
    }

    public String getVisitEnd() {
        return visitEnd;
    }

    public void setVisitEnd(String visitEnd) {
        this.visitEnd = visitEnd;
    }

    public String getSourceStatus() {
        return sourceStatus;
    }

    public void setSourceStatus(String sourceStatus) {
        this.sourceStatus = sourceStatus;
    }
}