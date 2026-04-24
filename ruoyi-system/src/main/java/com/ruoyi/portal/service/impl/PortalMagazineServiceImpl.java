package com.ruoyi.portal.service.impl;

import com.ruoyi.portal.domain.PortalMagazine;
import com.ruoyi.portal.mapper.PortalMagazineMapper;
import com.ruoyi.portal.service.IPortalMagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 杂志管理Service实现类
 */
@Service
public class PortalMagazineServiceImpl implements IPortalMagazineService {

    @Autowired
    private PortalMagazineMapper portalMagazineMapper;

    /**
     * 查询杂志管理
     * 
     * @param magazineId 杂志管理ID
     * @return 杂志管理
     */
    @Override
    public PortalMagazine selectPortalMagazineById(Long magazineId) {
        return portalMagazineMapper.selectPortalMagazineById(magazineId);
    }

    /**
     * 查询杂志管理列表
     * 
     * @param portalMagazine 杂志管理
     * @return 杂志管理集合
     */
    @Override
    public List<PortalMagazine> selectPortalMagazineList(PortalMagazine portalMagazine) {
        return portalMagazineMapper.selectPortalMagazineList(portalMagazine);
    }

    /**
     * 新增杂志管理
     * 
     * @param portalMagazine 杂志管理
     * @return 结果
     */
    @Override
    public int insertPortalMagazine(PortalMagazine portalMagazine) {
        return portalMagazineMapper.insertPortalMagazine(portalMagazine);
    }

    /**
     * 修改杂志管理
     * 
     * @param portalMagazine 杂志管理
     * @return 结果
     */
    @Override
    public int updatePortalMagazine(PortalMagazine portalMagazine) {
        return portalMagazineMapper.updatePortalMagazine(portalMagazine);
    }

    /**
     * 删除杂志管理
     * 
     * @param magazineId 杂志管理ID
     * @return 结果
     */
    @Override
    public int deletePortalMagazineById(Long magazineId) {
        return portalMagazineMapper.deletePortalMagazineById(magazineId);
    }

    /**
     * 批量删除杂志管理
     * 
     * @param magazineIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePortalMagazineByIds(Long[] magazineIds) {
        return portalMagazineMapper.deletePortalMagazineByIds(magazineIds);
    }
}
