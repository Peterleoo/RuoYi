package com.ruoyi.portal.domain;

import com.ruoyi.system.domain.SysDept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 大学管理对象（使用系统部门表）
 */
public class PortalUniversity extends SysDept {
    private static final long serialVersionUID = 1L;

    /** 联系电话 */
    private String contactPhone;

    /** 网站地址 */
    private String website;

    /** 大学描述 */
    private String universityDesc;

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }

    public void setUniversityDesc(String universityDesc) {
        this.universityDesc = universityDesc;
    }

    public String getUniversityDesc() {
        return universityDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("deptId", getDeptId())
                .append("deptName", getDeptName())
                .append("parentId", getParentId())
                .append("ancestors", getAncestors())
                .append("orderNum", getOrderNum())
                .append("leader", getLeader())
                .append("phone", getPhone())
                .append("email", getEmail())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("contactPhone", getContactPhone())
                .append("website", getWebsite())
                .append("universityDesc", getUniversityDesc())
                .toString();
    }
}