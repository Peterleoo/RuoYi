package com.ruoyi.portal.service.impl;

import com.ruoyi.portal.domain.PortalCourseLearningProgress;
import com.ruoyi.portal.mapper.PortalCourseLearningProgressMapper;
import com.ruoyi.portal.service.IPortalCourseLearningProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PortalCourseLearningProgressServiceImpl implements IPortalCourseLearningProgressService {

    @Autowired
    private PortalCourseLearningProgressMapper portalCourseLearningProgressMapper;

    @Override
    public List<PortalCourseLearningProgress> selectByMemberId(Long memberId) {
        return portalCourseLearningProgressMapper.selectByMemberId(memberId);
    }

    @Override
    public boolean saveOrUpdateProgress(Long memberId, String loginName, Long courseId, Integer progressPercent, Integer studyMinutes, String progressStatus) {
        int percent = progressPercent == null ? 0 : Math.max(0, Math.min(progressPercent, 100));
        int minutes = studyMinutes == null ? 0 : Math.max(studyMinutes, 0);
        String status = progressStatus;
        if (status == null || status.isEmpty()) {
            if (percent >= 100) {
                status = "2";
            } else if (percent > 0) {
                status = "1";
            } else {
                status = "0";
            }
        }

        PortalCourseLearningProgress exists = portalCourseLearningProgressMapper.selectByMemberAndCourse(memberId, courseId);
        Date now = new Date();
        if (exists == null) {
            PortalCourseLearningProgress insert = new PortalCourseLearningProgress();
            insert.setMemberId(memberId);
            insert.setCourseId(courseId);
            insert.setProgressPercent(percent);
            insert.setProgressStatus(status);
            insert.setStudyMinutes(minutes);
            insert.setLastStudyTime(now);
            insert.setCreateBy(loginName);
            insert.setCreateTime(now);
            return portalCourseLearningProgressMapper.insertPortalCourseLearningProgress(insert) > 0;
        }
        exists.setProgressPercent(percent);
        exists.setProgressStatus(status);
        exists.setStudyMinutes(minutes);
        exists.setLastStudyTime(now);
        exists.setUpdateBy(loginName);
        exists.setUpdateTime(now);
        return portalCourseLearningProgressMapper.updatePortalCourseLearningProgress(exists) > 0;
    }
}
