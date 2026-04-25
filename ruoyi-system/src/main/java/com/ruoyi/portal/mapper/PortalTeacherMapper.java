package com.ruoyi.portal.mapper;

import com.ruoyi.portal.domain.PortalTeacher;

import java.util.List;

public interface PortalTeacherMapper {
    PortalTeacher selectPortalTeacherById(Long teacherId);

    List<PortalTeacher> selectPortalTeacherList(PortalTeacher portalTeacher);

    List<PortalTeacher> selectPortalTeacherBySchoolId(Long schoolId);

    int insertPortalTeacher(PortalTeacher portalTeacher);

    int updatePortalTeacher(PortalTeacher portalTeacher);

    int deletePortalTeacherById(Long teacherId);

    int deletePortalTeacherByIds(Long[] teacherIds);
}

