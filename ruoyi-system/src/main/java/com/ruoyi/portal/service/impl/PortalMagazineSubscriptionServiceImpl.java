package com.ruoyi.portal.service.impl;

import com.ruoyi.portal.domain.PortalMagazineSubscription;
import com.ruoyi.portal.mapper.PortalMagazineSubscriptionMapper;
import com.ruoyi.portal.service.IPortalMagazineSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PortalMagazineSubscriptionServiceImpl implements IPortalMagazineSubscriptionService {

    @Autowired
    private PortalMagazineSubscriptionMapper portalMagazineSubscriptionMapper;

    @Override
    public List<PortalMagazineSubscription> selectByMemberId(Long memberId) {
        return portalMagazineSubscriptionMapper.selectByMemberId(memberId);
    }

    @Override
    public boolean subscribe(Long memberId, String loginName, Long magazineId) {
        PortalMagazineSubscription exists = portalMagazineSubscriptionMapper.selectByMemberAndMagazine(memberId, magazineId);
        Date now = new Date();
        if (exists == null) {
            PortalMagazineSubscription insert = new PortalMagazineSubscription();
            insert.setMemberId(memberId);
            insert.setMagazineId(magazineId);
            insert.setStatus("0");
            insert.setSubscribeTime(now);
            insert.setCreateBy(loginName);
            insert.setCreateTime(now);
            return portalMagazineSubscriptionMapper.insertPortalMagazineSubscription(insert) > 0;
        }
        exists.setStatus("0");
        exists.setCancelTime(null);
        exists.setSubscribeTime(now);
        exists.setUpdateBy(loginName);
        exists.setUpdateTime(now);
        return portalMagazineSubscriptionMapper.updatePortalMagazineSubscription(exists) > 0;
    }

    @Override
    public boolean unsubscribe(Long memberId, String loginName, Long magazineId) {
        PortalMagazineSubscription exists = portalMagazineSubscriptionMapper.selectByMemberAndMagazine(memberId, magazineId);
        if (exists == null) {
            return true;
        }
        exists.setStatus("1");
        exists.setCancelTime(new Date());
        exists.setUpdateBy(loginName);
        exists.setUpdateTime(new Date());
        return portalMagazineSubscriptionMapper.updatePortalMagazineSubscription(exists) > 0;
    }
}
