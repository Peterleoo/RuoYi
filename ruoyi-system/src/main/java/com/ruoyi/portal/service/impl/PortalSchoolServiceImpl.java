package com.ruoyi.portal.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.ShiroUtils;
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
    @DataScope(deptAlias = "ps", userAlias = "ps", userField = "creator_id")
    public List<PortalSchool> selectPortalSchoolList(PortalSchool portalSchool) {
        return portalSchoolMapper.selectPortalSchoolList(portalSchool);
    }

    @Override
    public int insertPortalSchool(PortalSchool portalSchool) {
        portalSchool.setCreateTime(new Date());
        portalSchool.setCreatorId(ShiroUtils.getUserId());
        portalSchool.setCreateBy(ShiroUtils.getLoginName());
        return portalSchoolMapper.insertPortalSchool(portalSchool);
    }

    @Override
    public int updatePortalSchool(PortalSchool portalSchool) {
        portalSchool.setUpdateTime(new Date());
        portalSchool.setUpdaterId(ShiroUtils.getUserId());
        portalSchool.setUpdateBy(ShiroUtils.getLoginName());
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
