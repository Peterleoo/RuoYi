package com.ruoyi.portal.service.impl;

import com.ruoyi.portal.domain.PortalMember;
import com.ruoyi.portal.mapper.PortalMemberMapper;
import com.ruoyi.portal.service.IPortalMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 会员管理Service实现类
 */
@Service
public class PortalMemberServiceImpl implements IPortalMemberService {

    @Autowired
    private PortalMemberMapper portalMemberMapper;

    /**
     * 查询会员管理
     * 
     * @param memberId 会员管理ID
     * @return 会员管理
     */
    @Override
    public PortalMember selectPortalMemberById(Long memberId) {
        return portalMemberMapper.selectPortalMemberById(memberId);
    }

    /**
     * 查询会员管理列表
     * 
     * @param portalMember 会员管理
     * @return 会员管理集合
     */
    @Override
    public List<PortalMember> selectPortalMemberList(PortalMember portalMember) {
        return portalMemberMapper.selectPortalMemberList(portalMember);
    }

    /**
     * 新增会员管理
     * 
     * @param portalMember 会员管理
     * @return 结果
     */
    @Override
    public int insertPortalMember(PortalMember portalMember) {
        return portalMemberMapper.insertPortalMember(portalMember);
    }

    /**
     * 修改会员管理
     * 
     * @param portalMember 会员管理
     * @return 结果
     */
    @Override
    public int updatePortalMember(PortalMember portalMember) {
        return portalMemberMapper.updatePortalMember(portalMember);
    }

    /**
     * 删除会员管理
     * 
     * @param memberId 会员管理ID
     * @return 结果
     */
    @Override
    public int deletePortalMemberById(Long memberId) {
        return portalMemberMapper.deletePortalMemberById(memberId);
    }

    /**
     * 批量删除会员管理
     * 
     * @param memberIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePortalMemberByIds(Long[] memberIds) {
        return portalMemberMapper.deletePortalMemberByIds(memberIds);
    }
}
