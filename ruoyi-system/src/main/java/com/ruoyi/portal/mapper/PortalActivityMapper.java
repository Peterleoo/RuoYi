package com.ruoyi.portal.mapper;

import com.ruoyi.portal.domain.PortalActivity;

import java.util.List;

public interface PortalActivityMapper {
    PortalActivity selectPortalActivityById(Long activityId);

    List<PortalActivity> selectPortalActivityList(PortalActivity portalActivity);

    int insertPortalActivity(PortalActivity portalActivity);

    int updatePortalActivity(PortalActivity portalActivity);

    int deletePortalActivityById(Long activityId);

    int deletePortalActivityByIds(Long[] activityIds);
}
