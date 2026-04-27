package com.ruoyi.portal.service;

import com.ruoyi.portal.domain.PortalMagazineSubscription;

import java.util.List;

public interface IPortalMagazineSubscriptionService {
    List<PortalMagazineSubscription> selectByMemberId(Long memberId);

    boolean subscribe(Long memberId, String loginName, Long magazineId);

    boolean unsubscribe(Long memberId, String loginName, Long magazineId);
}
