package com.ruoyi.portal.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.portal.domain.PortalMedia;
import com.ruoyi.portal.mapper.PortalMediaMapper;
import com.ruoyi.portal.service.IPortalMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortalMediaServiceImpl implements IPortalMediaService {

    @Autowired
    private PortalMediaMapper portalMediaMapper;

    @Override
    public PortalMedia selectPortalMediaById(Long mediaId) {
        return portalMediaMapper.selectPortalMediaById(mediaId);
    }

    @Override
    @DataScope(deptAlias = "su", userAlias = "m", userField = "creator_id")
    public List<PortalMedia> selectPortalMediaList(PortalMedia portalMedia) {
        return portalMediaMapper.selectPortalMediaList(portalMedia);
    }

    @Override
    public int insertPortalMedia(PortalMedia portalMedia) {
        portalMedia.setCreateTime(DateUtils.getNowDate());
        portalMedia.setCreatorId(ShiroUtils.getUserId());
        portalMedia.setCreateBy(ShiroUtils.getLoginName());
        return portalMediaMapper.insertPortalMedia(portalMedia);
    }

    @Override
    public int updatePortalMedia(PortalMedia portalMedia) {
        portalMedia.setUpdateTime(DateUtils.getNowDate());
        portalMedia.setUpdaterId(ShiroUtils.getUserId());
        portalMedia.setUpdateBy(ShiroUtils.getLoginName());
        return portalMediaMapper.updatePortalMedia(portalMedia);
    }

    @Override
    public int deletePortalMediaById(Long mediaId) {
        return portalMediaMapper.deletePortalMediaById(mediaId);
    }

    @Override
    public int deletePortalMediaByIds(Long[] mediaIds) {
        return portalMediaMapper.deletePortalMediaByIds(mediaIds);
    }
}
