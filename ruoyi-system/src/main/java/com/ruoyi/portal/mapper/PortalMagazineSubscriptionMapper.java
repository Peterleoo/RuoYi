package com.ruoyi.portal.mapper;

import com.ruoyi.portal.domain.PortalMagazineSubscription;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PortalMagazineSubscriptionMapper {
    PortalMagazineSubscription selectByMemberAndMagazine(@Param("memberId") Long memberId, @Param("magazineId") Long magazineId);

    List<PortalMagazineSubscription> selectByMemberId(Long memberId);

    int insertPortalMagazineSubscription(PortalMagazineSubscription subscription);

    int updatePortalMagazineSubscription(PortalMagazineSubscription subscription);
}
