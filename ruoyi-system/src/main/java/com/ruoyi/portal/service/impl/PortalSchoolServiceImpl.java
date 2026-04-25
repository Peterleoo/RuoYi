package com.ruoyi.portal.service.impl;

import com.ruoyi.portal.domain.PortalSchool;
import com.ruoyi.portal.mapper.PortalSchoolMapper;
import com.ruoyi.portal.service.IPortalSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PortalSchoolServiceImpl implements IPortalSchoolService {

    @Autowired
    private PortalSchoolMapper portalSchoolMapper;

    @Override
    public PortalSchool selectPortalSchoolById(Long schoolId) {
        return portalSchoolMapper.selectPortalSchoolById(schoolId);
    }

    @Override
    public List<PortalSchool> selectPortalSchoolList(PortalSchool portalSchool) {
        return portalSchoolMapper.selectPortalSchoolList(portalSchool);
    }

    @Override
    public int insertPortalSchool(PortalSchool portalSchool) {
        portalSchool.setCreateTime(new Date());
        return portalSchoolMapper.insertPortalSchool(portalSchool);
    }

    @Override
    public int updatePortalSchool(PortalSchool portalSchool) {
        portalSchool.setUpdateTime(new Date());
        return portalSchoolMapper.updatePortalSchool(portalSchool);
    }

    @Override
    public int deletePortalSchoolById(Long schoolId) {
        return portalSchoolMapper.deletePortalSchoolById(schoolId);
    }

    @Override
    public int deletePortalSchoolByIds(Long[] schoolIds) {
        return portalSchoolMapper.deletePortalSchoolByIds(schoolIds);
    }
}

