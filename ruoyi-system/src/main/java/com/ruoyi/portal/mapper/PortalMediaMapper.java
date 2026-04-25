package com.ruoyi.portal.mapper;

import com.ruoyi.portal.domain.PortalMedia;

import java.util.List;

public interface PortalMediaMapper {
    PortalMedia selectPortalMediaById(Long mediaId);

    List<PortalMedia> selectPortalMediaList(PortalMedia portalMedia);

    int insertPortalMedia(PortalMedia portalMedia);

    int updatePortalMedia(PortalMedia portalMedia);

    int deletePortalMediaById(Long mediaId);

    int deletePortalMediaByIds(Long[] mediaIds);
}
