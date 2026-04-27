package com.ruoyi.portal.service;

import com.ruoyi.portal.domain.PortalCourseLearningProgress;

import java.util.List;

public interface IPortalCourseLearningProgressService {
    List<PortalCourseLearningProgress> selectByMemberId(Long memberId);

    boolean saveOrUpdateProgress(Long memberId, String loginName, Long courseId, Integer progressPercent, Integer studyMinutes, String progressStatus);
}
