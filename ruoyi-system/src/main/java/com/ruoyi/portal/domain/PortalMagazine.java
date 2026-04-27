package com.ruoyi.portal.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 杂志管理对象 portal_magazine
 */
public class PortalMagazine implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 杂志ID */
    private Long magazineId;

    /** 杂志标题 */
    private String magazineTitle;

    /** 杂志封面 */
    private String magazineCover;

    /** 期号 */
    private String magazineIssue;

    /** 年份 */
    private Integer publishYear;

    /** 出版日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;

    /** 杂志内容 */
    private String magazineContent;

    /** PDF链接 */
    private String magazinePdf;

    /** H5阅读链接 */
    private String h5Url;

    /** 简介 */
    private String introduction;

    /** 状态（0正常 1停用） */
    private String status;

    /** 创建者ID */
    private Long creatorId;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;

    /** 更新者ID */
    private Long updaterId;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private Date updateTime;

    /** 备注 */
    private String remark;
    private Map<String, Object> params;

    public void setMagazineId(Long magazineId) {
        this.magazineId = magazineId;
    }

    public Long getMagazineId() {
        return magazineId;
    }

    public void setMagazineTitle(String magazineTitle) {
        this.magazineTitle = magazineTitle;
    }

    public String getMagazineTitle() {
        return magazineTitle;
    }

    public void setMagazineCover(String magazineCover) {
        this.magazineCover = magazineCover;
    }

    public String getMagazineCover() {
        return magazineCover;
    }

    public void setMagazineIssue(String magazineIssue) {
        this.magazineIssue = magazineIssue;
    }

    public String getMagazineIssue() {
        return magazineIssue;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setMagazineContent(String magazineContent) {
        this.magazineContent = magazineContent;
    }

    public String getMagazineContent() {
        return magazineContent;
    }

    public void setMagazinePdf(String magazinePdf) {
        this.magazinePdf = magazinePdf;
    }

    public String getMagazinePdf() {
        return magazinePdf;
    }

    public String getH5Url() {
        return h5Url;
    }

    public void setH5Url(String h5Url) {
        this.h5Url = h5Url;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("magazineId", getMagazineId())
                .append("magazineTitle", getMagazineTitle())
                .append("magazineCover", getMagazineCover())
                .append("magazineIssue", getMagazineIssue())
                .append("publishYear", getPublishYear())
                .append("publishDate", getPublishDate())
                .append("magazineContent", getMagazineContent())
                .append("magazinePdf", getMagazinePdf())
                .append("h5Url", getH5Url())
                .append("introduction", getIntroduction())
                .append("status", getStatus())
                .append("creatorId", getCreatorId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updaterId", getUpdaterId())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
