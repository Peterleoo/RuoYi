package com.ruoyi.portal.service.impl;

import com.ruoyi.portal.domain.PortalUniversity;
import com.ruoyi.portal.mapper.PortalUniversityMapper;
import com.ruoyi.portal.service.IPortalUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 大学管理Service实现类
 */
@Service
public class PortalUniversityServiceImpl implements IPortalUniversityService {

    @Autowired
    private PortalUniversityMapper portalUniversityMapper;

    /**
     * 查询大学管理
     * 
     * @param universityId 大学管理ID
     * @return 大学管理
     */
    @Override
    public PortalUniversity selectPortalUniversityById(Long universityId) {
        return portalUniversityMapper.selectPortalUniversityById(universityId);
    }

    /**
     * 查询大学管理列表
     * 
     * @param portalUniversity 大学管理
     * @return 大学管理集合
     */
    @Override
    public List<PortalUniversity> selectPortalUniversityList(PortalUniversity portalUniversity) {
        return portalUniversityMapper.selectPortalUniversityList(portalUniversity);
    }

    /**
     * 新增大学管理
     * 
     * @param portalUniversity 大学管理
     * @return 结果
     */
    @Override
    public int insertPortalUniversity(PortalUniversity portalUniversity) {
        return portalUniversityMapper.insertPortalUniversity(portalUniversity);
    }

    /**
     * 修改大学管理
     * 
     * @param portalUniversity 大学管理
     * @return 结果
     */
    @Override
    public int updatePortalUniversity(PortalUniversity portalUniversity) {
        return portalUniversityMapper.updatePortalUniversity(portalUniversity);
    }

    /**
     * 删除大学管理
     * 
     * @param universityId 大学管理ID
     * @return 结果
     */
    @Override
    public int deletePortalUniversityById(Long universityId) {
        return portalUniversityMapper.deletePortalUniversityById(universityId);
    }

    /**
     * 批量删除大学管理
     * 
     * @param universityIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePortalUniversityByIds(Long[] universityIds) {
        return portalUniversityMapper.deletePortalUniversityByIds(universityIds);
    }
}
