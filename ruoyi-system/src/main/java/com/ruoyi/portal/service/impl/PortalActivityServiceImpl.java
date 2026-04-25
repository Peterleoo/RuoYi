package com.ruoyi.portal.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.portal.domain.PortalActivity;
import com.ruoyi.portal.mapper.PortalActivityMapper;
import com.ruoyi.portal.service.IPortalActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortalActivityServiceImpl implements IPortalActivityService {

    @Autowired
    private PortalActivityMapper portalActivityMapper;

    @Override
    public PortalActivity selectPortalActivityById(Long activityId) {
        return portalActivityMapper.selectPortalActivityById(activityId);
    }

    @Override
    public List<PortalActivity> selectPortalActivityList(PortalActivity portalActivity) {
        return portalActivityMapper.selectPortalActivityList(portalActivity);
    }

    @Override
    public int insertPortalActivity(PortalActivity portalActivity) {
        portalActivity.setCreateTime(DateUtils.getNowDate());
        portalActivity.setCreateBy(ShiroUtils.getLoginName());
        return portalActivityMapper.insertPortalActivity(portalActivity);
    }

    @Override
    public int updatePortalActivity(PortalActivity portalActivity) {
        portalActivity.setUpdateTime(DateUtils.getNowDate());
        portalActivity.setUpdateBy(ShiroUtils.getLoginName());
        return portalActivityMapper.updatePortalActivity(portalActivity);
    }

    @Override
    public int deletePortalActivityById(Long activityId) {
        return portalActivityMapper.deletePortalActivityById(activityId);
    }

    @Override
    public int deletePortalActivityByIds(Long[] activityIds) {
        return portalActivityMapper.deletePortalActivityByIds(activityIds);
    }
}
