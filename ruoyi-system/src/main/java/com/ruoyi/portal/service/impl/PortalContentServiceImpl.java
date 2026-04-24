package com.ruoyi.portal.service.impl;

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
        return portalContentMapper.insertPortalContent(portalContent);
    }

    @Override
    public int updatePortalContent(PortalContent portalContent) {
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