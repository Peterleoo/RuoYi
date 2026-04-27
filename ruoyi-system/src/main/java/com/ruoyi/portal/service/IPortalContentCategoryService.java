package com.ruoyi.portal.service;

import com.ruoyi.portal.domain.PortalContentCategory;

import java.util.List;

public interface IPortalContentCategoryService {
    PortalContentCategory selectPortalContentCategoryById(Long categoryId);

    List<PortalContentCategory> selectPortalContentCategoryList(PortalContentCategory category);

    int insertPortalContentCategory(PortalContentCategory category);

    int updatePortalContentCategory(PortalContentCategory category);

    int deletePortalContentCategoryById(Long categoryId);

    int deletePortalContentCategoryByIds(Long[] categoryIds);
}

