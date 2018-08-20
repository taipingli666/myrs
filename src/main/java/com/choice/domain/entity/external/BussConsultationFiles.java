package com.choice.domain.entity.external;

import java.util.Date;

/**
 *
 */
public class BussConsultationFiles {
    /**
     * 
     */
    private Long id;

    /**
     * 转诊单流水号
     */
    private Long singleLiushuihao;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 转诊单流水号
     * @return single_liushuihao 转诊单流水号
     */
    public Long getSingleLiushuihao() {
        return singleLiushuihao;
    }

    /**
     * 转诊单流水号
     * @param singleLiushuihao 转诊单流水号
     */
    public void setSingleLiushuihao(Long singleLiushuihao) {
        this.singleLiushuihao = singleLiushuihao;
    }

    /**
     * 文件路径
     * @return file_path 文件路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 文件路径
     * @param filePath 文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * 文件名
     * @return file_name 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 文件名
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
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

    @Override
    public String toString() {
        return "BussConsultationFiles{" +
                "id=" + id +
                ", singleLiushuihao=" + singleLiushuihao +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}