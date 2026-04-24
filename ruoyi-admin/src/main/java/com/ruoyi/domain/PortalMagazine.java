package com.ruoyi.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 杂志管理实体
 */
public class PortalMagazine extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 杂志ID */
    private Long magazineId;

    /** 杂志名称 */
    private String magazineName;

    /** 杂志封面 */
    private String coverImage;

    /** 杂志期号 */
    private String issueNumber;

    /** 发布日期 */
    private String publishDate;

    /** 杂志简介 */
    private String description;

    /** 状态（0正常 1停用） */
    private String status;

    /** 排序 */
    private Integer sort;

    // getter和setter方法
    public Long getMagazineId() {
        return magazineId;
    }

    public void setMagazineId(Long magazineId) {
        this.magazineId = magazineId;
    }

    public String getMagazineName() {
        return magazineName;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}