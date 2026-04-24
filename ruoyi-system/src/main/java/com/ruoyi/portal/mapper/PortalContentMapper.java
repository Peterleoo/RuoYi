package com.ruoyi.portal.mapper;

import com.ruoyi.portal.domain.PortalContent;

import java.util.List;

/**
 * 内容管理Mapper接口
 */
public interface PortalContentMapper {
    /**
     * 查询内容列表
     *
     * @param portalContent 内容信息
     * @return 内容列表
     */
    List<PortalContent> selectPortalContentList(PortalContent portalContent);

    /**
     * 新增内容
     *
     * @param portalContent 内容信息
     * @return 结果
     */
    int insertPortalContent(PortalContent portalContent);

    /**
     * 修改内容
     *
     * @param portalContent 内容信息
     * @return 结果
     */
    int updatePortalContent(PortalContent portalContent);

    /**
     * 删除内容
     *
     * @param contentId 内容ID
     * @return 结果
     */
    int deletePortalContentById(Long contentId);

    /**
     * 根据ID查询内容
     *
     * @param contentId 内容ID
     * @return 内容信息
     */
    PortalContent selectPortalContentById(Long contentId);
}