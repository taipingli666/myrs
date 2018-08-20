package com.choice.domain.entity.external;

import java.util.Date;

public class BussConsultationMessage {
    /**
     * 未阅读
     */
    public static final String NOREAD = "0";

    /**
     * 已阅读
     */
    public static final String READ = "1";

    /**
     * 唯一标记
     */
    private Long id;

    /**
     * 种类 1.好友 2.会诊 3.视频通话
     */
    private String type;

    /**
     * 状态 0.未读 1.已读
     */
    private String state;

    /**
     * 创建人
     */
    private String creater;

    /**
     * 创建人编号
     */
    private Integer createrCode;

    /**
     * 接收人
     */
    private String accepter;

    /**
     * 接收人编号
     */
    private Integer accepterCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 阅读时间
     */
    private Date lookTime;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 备用字段
     */
    private String spare;

    /**
     * 结果 0.默认 1.同意 2.拒绝
     */
    private String result;

    /**
     * 唯一标记
     * @return id 唯一标记
     */
    public Long getId() {
        return id;
    }

    /**
     * 唯一标记
     * @param id 唯一标记
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 种类 1.好友 2.会诊 3.视频通话
     * @return type 种类 1.好友 2.会诊 3.视频通话
     */
    public String getType() {
        return type;
    }

    /**
     * 种类 1.好友 2.会诊 3.视频通话
     * @param type 种类 1.好友 2.会诊 3.视频通话
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 状态 0.未读 1.已读
     * @return state 状态 0.未读 1.已读
     */
    public String getState() {
        return state;
    }

    /**
     * 状态 0.未读 1.已读
     * @param state 状态 0.未读 1.已读
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 创建人
     * @return creater 创建人
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 创建人
     * @param creater 创建人
     */
    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    /**
     * 创建人编号
     * @return creater_code 创建人编号
     */
    public Integer getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编号
     * @param createrCode 创建人编号
     */
    public void setCreaterCode(Integer createrCode) {
        this.createrCode = createrCode;
    }

    /**
     * 接收人
     * @return accepter 接收人
     */
    public String getAccepter() {
        return accepter;
    }

    /**
     * 接收人
     * @param accepter 接收人
     */
    public void setAccepter(String accepter) {
        this.accepter = accepter == null ? null : accepter.trim();
    }

    /**
     * 接收人编号
     * @return accepter_code 接收人编号
     */
    public Integer getAccepterCode() {
        return accepterCode;
    }

    /**
     * 接收人编号
     * @param accepterCode 接收人编号
     */
    public void setAccepterCode(Integer accepterCode) {
        this.accepterCode = accepterCode;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 阅读时间
     * @return look_time 阅读时间
     */
    public Date getLookTime() {
        return lookTime;
    }

    /**
     * 阅读时间
     * @param lookTime 阅读时间
     */
    public void setLookTime(Date lookTime) {
        this.lookTime = lookTime;
    }

    /**
     * 消息内容
     * @return content 消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 消息内容
     * @param content 消息内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 备用字段
     * @return spare 备用字段
     */
    public String getSpare() {
        return spare;
    }

    /**
     * 备用字段
     * @param spare 备用字段
     */
    public void setSpare(String spare) {
        this.spare = spare == null ? null : spare.trim();
    }

    /**
     * 结果 0.默认 1.同意 2.拒绝
     * @return result 结果 0.默认 1.同意 2.拒绝
     */
    public String getResult() {
        return result;
    }

    /**
     * 结果 0.默认 1.同意 2.拒绝
     * @param result 结果 0.默认 1.同意 2.拒绝
     */
    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }
}