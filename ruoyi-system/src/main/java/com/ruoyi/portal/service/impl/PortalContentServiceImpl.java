package com.ruoyi.portal.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.portal.domain.PortalContent;
import com.ruoyi.portal.mapper.PortalContentMapper;
import com.ruoyi.portal.service.IPortalContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内容管理Service实现类
 */
@Service
public class PortalContentServiceImpl implements IPortalContentService {

    @Autowired
    private PortalContentMapper portalContentMapper;

    @Override
    public List<PortalContent> selectPortalContentList(PortalContent portalContent) {
        return portalContentMapper.selectPortalContentList(portalContent);
    }

    @Override
    public int insertPortalContent(PortalContent portalContent) {
        portalContent.setCreateTime(DateUtils.getNowDate());
        // 注意：PortalContent 的 createBy 是 Long 类型，但 ShiroUtils.getLoginName() 是 String
        // 如果数据库是 Long，通常存储的是 userId。我们需要确认 createBy 的类型。
        // 根据之前的代码，PortalContent.createBy 是 Long。
        portalContent.setCreateBy(ShiroUtils.getUserId());
        return portalContentMapper.insertPortalContent(portalContent);
    }

    @Override
    public int updatePortalContent(PortalContent portalContent) {
        portalContent.setUpdateTime(DateUtils.getNowDate());
        portalContent.setUpdateBy(ShiroUtils.getUserId());
        return portalContentMapper.updatePortalContent(portalContent);
    }

    @Override
    public int deletePortalContentById(Long contentId) {
        return portalContentMapper.deletePortalContentById(contentId);
    }

    @Override
    public PortalContent selectPortalContentById(Long contentId) {
        return portalContentMapper.selectPortalContentById(contentId);
    }
}