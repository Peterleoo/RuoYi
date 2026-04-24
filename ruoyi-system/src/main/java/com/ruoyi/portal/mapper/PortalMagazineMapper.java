package com.ruoyi.portal.mapper;

import com.ruoyi.portal.domain.PortalMagazine;
import java.util.List;

/**
 * 杂志管理Mapper接口
 */
public interface PortalMagazineMapper {
    /**
     * 查询杂志管理
     * 
     * @param magazineId 杂志管理ID
     * @return 杂志管理
     */
    public PortalMagazine selectPortalMagazineById(Long magazineId);

    /**
     * 查询杂志管理列表
     * 
     * @param portalMagazine 杂志管理
     * @return 杂志管理集合
     */
    public List<PortalMagazine> selectPortalMagazineList(PortalMagazine portalMagazine);

    /**
     * 新增杂志管理
     * 
     * @param portalMagazine 杂志管理
     * @return 结果
     */
    public int insertPortalMagazine(PortalMagazine portalMagazine);

    /**
     * 修改杂志管理
     * 
     * @param portalMagazine 杂志管理
     * @return 结果
     */
    public int updatePortalMagazine(PortalMagazine portalMagazine);

    /**
     * 删除杂志管理
     * 
     * @param magazineId 杂志管理ID
     * @return 结果
     */
    public int deletePortalMagazineById(Long magazineId);

    /**
     * 批量删除杂志管理
     * 
     * @param magazineIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePortalMagazineByIds(Long[] magazineIds);
}
