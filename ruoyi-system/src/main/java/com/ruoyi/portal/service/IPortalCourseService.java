package com.ruoyi.portal.service;

import com.ruoyi.portal.domain.PortalCourse;

import java.util.List;

public interface IPortalCourseService {
    PortalCourse selectPortalCourseById(Long courseId);

    List<PortalCourse> selectPortalCourseList(PortalCourse portalCourse);

    List<PortalCourse> selectPortalCourseBySchoolId(Long schoolId);

    int insertPortalCourse(PortalCourse portalCourse);

    int updatePortalCourse(PortalCourse portalCourse);

    int deletePortalCourseById(Long courseId);

    int deletePortalCourseByIds(Long[] courseIds);
}

