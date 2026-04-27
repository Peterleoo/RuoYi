package com.ruoyi.portal.mapper;

import com.ruoyi.portal.domain.PortalContentCategory;

import java.util.List;

public interface PortalContentCategoryMapper {
    PortalContentCategory selectPortalContentCategoryById(Long categoryId);

    List<PortalContentCategory> selectPortalContentCategoryList(PortalContentCategory category);

    int insertPortalContentCategory(PortalContentCategory category);

    int updatePortalContentCategory(PortalContentCategory category);

    int deletePortalContentCategoryById(Long categoryId);

    int deletePortalContentCategoryByIds(Long[] categoryIds);
}

