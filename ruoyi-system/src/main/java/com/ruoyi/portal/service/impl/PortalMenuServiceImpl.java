package com.ruoyi.portal.service.impl;

import com.ruoyi.portal.domain.PortalMenu;
import com.ruoyi.portal.mapper.PortalMenuMapper;
import com.ruoyi.portal.service.IPortalMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 菜单管理Service实现类
 */
@Service
public class PortalMenuServiceImpl implements IPortalMenuService {

    @Autowired
    private PortalMenuMapper portalMenuMapper;

    /**
     * 查询菜单管理
     * 
     * @param menuId 菜单管理ID
     * @return 菜单管理
     */
    @Override
    public PortalMenu selectPortalMenuById(Long menuId) {
        return portalMenuMapper.selectPortalMenuById(menuId);
    }

    /**
     * 查询菜单管理列表
     * 
     * @param portalMenu 菜单管理
     * @return 菜单管理集合
     */
    @Override
    public List<PortalMenu> selectPortalMenuList(PortalMenu portalMenu) {
        return portalMenuMapper.selectPortalMenuList(portalMenu);
    }

    /**
     * 新增菜单管理
     * 
     * @param portalMenu 菜单管理
     * @return 结果
     */
    @Override
    public int insertPortalMenu(PortalMenu portalMenu) {
        return portalMenuMapper.insertPortalMenu(portalMenu);
    }

    /**
     * 修改菜单管理
     * 
     * @param portalMenu 菜单管理
     * @return 结果
     */
    @Override
    public int updatePortalMenu(PortalMenu portalMenu) {
        return portalMenuMapper.updatePortalMenu(portalMenu);
    }

    /**
     * 删除菜单管理
     * 
     * @param menuId 菜单管理ID
     * @return 结果
     */
    @Override
    public int deletePortalMenuById(Long menuId) {
        return portalMenuMapper.deletePortalMenuById(menuId);
    }

    /**
     * 批量删除菜单管理
     * 
     * @param menuIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePortalMenuByIds(Long[] menuIds) {
        return portalMenuMapper.deletePortalMenuByIds(menuIds);
    }

    /**
     * 查询菜单管理树
     * 
     * @return 菜单管理树
     */
    @Override
    public List<PortalMenu> selectPortalMenuTree() {
        return portalMenuMapper.selectPortalMenuTree();
    }
}
