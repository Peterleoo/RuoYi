package com.ruoyi.portal.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.portal.domain.PortalContentCategory;
import com.ruoyi.portal.mapper.PortalContentCategoryMapper;
import com.ruoyi.portal.service.IPortalContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortalContentCategoryServiceImpl implements IPortalContentCategoryService {

    @Autowired
    private PortalContentCategoryMapper portalContentCategoryMapper;

    @Override
    public PortalContentCategory selectPortalContentCategoryById(Long categoryId) {
        return portalContentCategoryMapper.selectPortalContentCategoryById(categoryId);
    }

    @Override
    public List<PortalContentCategory> selectPortalContentCategoryList(PortalContentCategory category) {
        return portalContentCategoryMapper.selectPortalContentCategoryList(category);
    }

    @Override
    public int insertPortalContentCategory(PortalContentCategory category) {
        category.setCreateBy(ShiroUtils.getLoginName());
        category.setCreateTime(DateUtils.getNowDate());
        return portalContentCategoryMapper.insertPortalContentCategory(category);
    }

    @Override
    public int updatePortalContentCategory(PortalContentCategory category) {
        category.setUpdateBy(ShiroUtils.getLoginName());
        category.setUpdateTime(DateUtils.getNowDate());
        return portalContentCategoryMapper.updatePortalContentCategory(category);
    }

    @Override
    public int deletePortalContentCategoryById(Long categoryId) {
        return portalContentCategoryMapper.deletePortalContentCategoryById(categoryId);
    }

    @Override
    public int deletePortalContentCategoryByIds(Long[] categoryIds) {
        return portalContentCategoryMapper.deletePortalContentCategoryByIds(categoryIds);
    }
}

