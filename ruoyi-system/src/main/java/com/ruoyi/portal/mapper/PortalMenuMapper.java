package com.ruoyi.portal.mapper;

import com.ruoyi.portal.domain.PortalMenu;
import java.util.List;

/**
 * 菜单管理Mapper接口
 */
public interface PortalMenuMapper {
    /**
     * 查询菜单管理
     * 
     * @param menuId 菜单管理ID
     * @return 菜单管理
     */
    public PortalMenu selectPortalMenuById(Long menuId);

    /**
     * 查询菜单管理列表
     * 
     * @param portalMenu 菜单管理
     * @return 菜单管理集合
     */
    public List<PortalMenu> selectPortalMenuList(PortalMenu portalMenu);

    /**
     * 新增菜单管理
     * 
     * @param portalMenu 菜单管理
     * @return 结果
     */
    public int insertPortalMenu(PortalMenu portalMenu);

    /**
     * 修改菜单管理
     * 
     * @param portalMenu 菜单管理
     * @return 结果
     */
    public int updatePortalMenu(PortalMenu portalMenu);

    /**
     * 删除菜单管理
     * 
     * @param menuId 菜单管理ID
     * @return 结果
     */
    public int deletePortalMenuById(Long menuId);

    /**
     * 批量删除菜单管理
     * 
     * @param menuIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePortalMenuByIds(Long[] menuIds);

    /**
     * 查询菜单管理树
     * 
     * @return 菜单管理树
     */
    public List<PortalMenu> selectPortalMenuTree();
}
