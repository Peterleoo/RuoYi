package com.ruoyi.portal.service.impl;

import com.ruoyi.common.annotation.DataScope;
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
    @DataScope(deptAlias = "s", userAlias = "c", userField = "creator_id")
    public List<PortalContent> selectPortalContentList(PortalContent portalContent) {
        return portalContentMapper.selectPortalContentList(portalContent);
    }

    @Override
    public int insertPortalContent(PortalContent portalContent) {
        portalContent.setCreateTime(DateUtils.getNowDate());
        portalContent.setCreatorId(ShiroUtils.getUserId());
        portalContent.setCreateBy(ShiroUtils.getLoginName());
        return portalContentMapper.insertPortalContent(portalContent);
    }

    @Override
    public int updatePortalContent(PortalContent portalContent) {
        portalContent.setUpdateTime(DateUtils.getNowDate());
        portalContent.setUpdaterId(ShiroUtils.getUserId());
        portalContent.setUpdateBy(ShiroUtils.getLoginName());
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
