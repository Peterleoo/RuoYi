package com.ruoyi.portal.mapper;

import com.ruoyi.portal.domain.PortalCourseLearningProgress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PortalCourseLearningProgressMapper {
    PortalCourseLearningProgress selectByMemberAndCourse(@Param("memberId") Long memberId, @Param("courseId") Long courseId);

    List<PortalCourseLearningProgress> selectByMemberId(Long memberId);

    int insertPortalCourseLearningProgress(PortalCourseLearningProgress progress);

    int updatePortalCourseLearningProgress(PortalCourseLearningProgress progress);
}
