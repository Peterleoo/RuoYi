package com.ruoyi.portal.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 学校/机构对象 portal_school
 */
public class PortalSchool implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long schoolId;
    private Long deptId;
    private String schoolName;
    private String logo;
    private String introduction;
    private String address;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String contactPhone;
    private String transportGuide;
    private String enrollmentBrochure;
    private String coverImage;
    private String status;
    private Long creatorId;
    private String createBy;
    private Date createTime;
    private Long updaterId;
    private String updateBy;
    private Date updateTime;
    private String remark;
    private Map<String, Object> params;

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getTransportGuide() {
        return transportGuide;
    }

    public void setTransportGuide(String transportGuide) {
        this.transportGuide = transportGuide;
    }

    public String getEnrollmentBrochure() {
        return enrollmentBrochure;
    }

    public void setEnrollmentBrochure(String enrollmentBrochure) {
        this.enrollmentBrochure = enrollmentBrochure;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
                .append("schoolId", schoolId)
                .append("deptId", deptId)
                .append("schoolName", schoolName)
                .append("logo", logo)
                .append("introduction", introduction)
                .append("address", address)
                .append("longitude", longitude)
                .append("latitude", latitude)
                .append("contactPhone", contactPhone)
                .append("transportGuide", transportGuide)
                .append("enrollmentBrochure", enrollmentBrochure)
                .append("coverImage", coverImage)
                .append("status", status)
                .append("creatorId", creatorId)
                .append("createBy", createBy)
                .append("createTime", createTime)
                .append("updaterId", updaterId)
                .append("updateBy", updateBy)
                .append("updateTime", updateTime)
                .append("remark", remark)
                .toString();
    }
}
