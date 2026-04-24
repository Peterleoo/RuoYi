package com.ruoyi.portal.service;

import com.ruoyi.portal.domain.PortalUniversity;
import java.util.List;

/**
 * 大学管理Service接口
 */
public interface IPortalUniversityService {
    /**
     * 查询大学管理
     * 
     * @param universityId 大学管理ID
     * @return 大学管理
     */
    public PortalUniversity selectPortalUniversityById(Long universityId);

    /**
     * 查询大学管理列表
     * 
     * @param portalUniversity 大学管理
     * @return 大学管理集合
     */
    public List<PortalUniversity> selectPortalUniversityList(PortalUniversity portalUniversity);

    /**
     * 新增大学管理
     * 
     * @param portalUniversity 大学管理
     * @return 结果
     */
    public int insertPortalUniversity(PortalUniversity portalUniversity);

    /**
     * 修改大学管理
     * 
     * @param portalUniversity 大学管理
     * @return 结果
     */
    public int updatePortalUniversity(PortalUniversity portalUniversity);

    /**
     * 删除大学管理
     * 
     * @param universityId 大学管理ID
     * @return 结果
     */
    public int deletePortalUniversityById(Long universityId);

    /**
     * 批量删除大学管理
     * 
     * @param universityIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePortalUniversityByIds(Long[] universityIds);
}
