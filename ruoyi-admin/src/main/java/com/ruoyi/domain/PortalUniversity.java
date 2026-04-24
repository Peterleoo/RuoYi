package com.ruoyi.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 大学管理实体
 */
public class PortalUniversity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 大学ID */
    private Long universityId;

    /** 大学名称 */
    private String universityName;

    /** 所在城市 */
    private String city;

    /** 地址 */
    private String address;

    /** 联系方式 */
    private String contactInfo;

    /** 学校简介 */
    private String description;

    /** 办学特色 */
    private String features;

    /** 师资力量 */
    private String teachers;

    /** 校园环境 */
    private String environment;

    /** 状态（0正常 1停用） */
    private String status;

    /** 排序 */
    private Integer sort;

    // getter和setter方法
    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
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