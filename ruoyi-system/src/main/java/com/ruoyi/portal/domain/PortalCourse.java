package com.ruoyi.portal.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 课程对象 portal_course
 */
public class PortalCourse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long courseId;
    private Long schoolId;
    private String courseName;
    private String courseCategory;
    private Long teacherId;
    private String coverImage;
    private String courseIntro;
    private String classSchedule;
    private String replayVideoUrl;
    private BigDecimal tuitionFee;
    private String enrollmentStatus;
    private String status;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;

    /** 展示字段 */
    private String schoolName;
    private String teacherName;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getCourseIntro() {
        return courseIntro;
    }

    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro;
    }

    public String getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(String classSchedule) {
        this.classSchedule = classSchedule;
    }

    public String getReplayVideoUrl() {
        return replayVideoUrl;
    }

    public void setReplayVideoUrl(String replayVideoUrl) {
        this.replayVideoUrl = replayVideoUrl;
    }

    public BigDecimal getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(BigDecimal tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public String getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("courseId", courseId)
                .append("schoolId", schoolId)
                .append("courseName", courseName)
                .append("courseCategory", courseCategory)
                .append("teacherId", teacherId)
                .append("coverImage", coverImage)
                .append("courseIntro", courseIntro)
                .append("classSchedule", classSchedule)
                .append("replayVideoUrl", replayVideoUrl)
                .append("tuitionFee", tuitionFee)
                .append("enrollmentStatus", enrollmentStatus)
                .append("status", status)
                .append("createBy", createBy)
                .append("createTime", createTime)
                .append("updateBy", updateBy)
                .append("updateTime", updateTime)
                .append("remark", remark)
                .append("schoolName", schoolName)
                .append("teacherName", teacherName)
                .toString();
    }
}

