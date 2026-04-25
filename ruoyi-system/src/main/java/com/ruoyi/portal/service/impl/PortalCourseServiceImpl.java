package com.ruoyi.portal.service.impl;

import com.ruoyi.portal.domain.PortalCourse;
import com.ruoyi.portal.mapper.PortalCourseMapper;
import com.ruoyi.portal.service.IPortalCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PortalCourseServiceImpl implements IPortalCourseService {

    @Autowired
    private PortalCourseMapper portalCourseMapper;

    @Override
    public PortalCourse selectPortalCourseById(Long courseId) {
        return portalCourseMapper.selectPortalCourseById(courseId);
    }

    @Override
    public List<PortalCourse> selectPortalCourseList(PortalCourse portalCourse) {
        return portalCourseMapper.selectPortalCourseList(portalCourse);
    }

    @Override
    public List<PortalCourse> selectPortalCourseBySchoolId(Long schoolId) {
        return portalCourseMapper.selectPortalCourseBySchoolId(schoolId);
    }

    @Override
    public int insertPortalCourse(PortalCourse portalCourse) {
        portalCourse.setCreateTime(new Date());
        return portalCourseMapper.insertPortalCourse(portalCourse);
    }

    @Override
    public int updatePortalCourse(PortalCourse portalCourse) {
        portalCourse.setUpdateTime(new Date());
        return portalCourseMapper.updatePortalCourse(portalCourse);
    }

    @Override
    public int deletePortalCourseById(Long courseId) {
        return portalCourseMapper.deletePortalCourseById(courseId);
    }

    @Override
    public int deletePortalCourseByIds(Long[] courseIds) {
        return portalCourseMapper.deletePortalCourseByIds(courseIds);
    }
}

