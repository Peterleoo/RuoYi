package com.ruoyi.portal.service.impl;

import com.ruoyi.portal.domain.PortalTeacher;
import com.ruoyi.portal.mapper.PortalTeacherMapper;
import com.ruoyi.portal.service.IPortalTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PortalTeacherServiceImpl implements IPortalTeacherService {

    @Autowired
    private PortalTeacherMapper portalTeacherMapper;

    @Override
    public PortalTeacher selectPortalTeacherById(Long teacherId) {
        return portalTeacherMapper.selectPortalTeacherById(teacherId);
    }

    @Override
    public List<PortalTeacher> selectPortalTeacherList(PortalTeacher portalTeacher) {
        return portalTeacherMapper.selectPortalTeacherList(portalTeacher);
    }

    @Override
    public List<PortalTeacher> selectPortalTeacherBySchoolId(Long schoolId) {
        return portalTeacherMapper.selectPortalTeacherBySchoolId(schoolId);
    }

    @Override
    public int insertPortalTeacher(PortalTeacher portalTeacher) {
        portalTeacher.setCreateTime(new Date());
        return portalTeacherMapper.insertPortalTeacher(portalTeacher);
    }

    @Override
    public int updatePortalTeacher(PortalTeacher portalTeacher) {
        portalTeacher.setUpdateTime(new Date());
        return portalTeacherMapper.updatePortalTeacher(portalTeacher);
    }

    @Override
    public int deletePortalTeacherById(Long teacherId) {
        return portalTeacherMapper.deletePortalTeacherById(teacherId);
    }

    @Override
    public int deletePortalTeacherByIds(Long[] teacherIds) {
        return portalTeacherMapper.deletePortalTeacherByIds(teacherIds);
    }
}

