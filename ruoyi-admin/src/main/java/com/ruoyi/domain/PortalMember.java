package com.ruoyi.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会员管理实体
 */
public class PortalMember extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 会员ID */
    private Long memberId;

    /** 会员姓名 */
    private String memberName;

    /** 会员账号 */
    private String memberAccount;

    /** 密码 */
    private String password;

    /** 会员等级（0普通 1金卡） */
    private String memberLevel;

    /** 联系方式 */
    private String contactInfo;

    /** 状态（0正常 1停用） */
    private String status;

    // getter和setter方法
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}