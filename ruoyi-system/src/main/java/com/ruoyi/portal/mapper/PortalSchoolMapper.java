package com.ruoyi.portal.mapper;

import com.ruoyi.portal.domain.PortalSchool;

import java.util.List;

public interface PortalSchoolMapper {
    PortalSchool selectPortalSchoolById(Long schoolId);

    List<PortalSchool> selectPortalSchoolList(PortalSchool portalSchool);

    int insertPortalSchool(PortalSchool portalSchool);

    int updatePortalSchool(PortalSchool portalSchool);

    int deletePortalSchoolById(Long schoolId);

    int deletePortalSchoolByIds(Long[] schoolIds);
}

