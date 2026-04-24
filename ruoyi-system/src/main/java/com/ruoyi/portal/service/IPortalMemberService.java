package com.ruoyi.portal.service;

import com.ruoyi.portal.domain.PortalMember;
import java.util.List;

/**
 * 会员管理Service接口
 */
public interface IPortalMemberService {
    /**
     * 查询会员管理
     * 
     * @param memberId 会员管理ID
     * @return 会员管理
     */
    public PortalMember selectPortalMemberById(Long memberId);

    /**
     * 查询会员管理列表
     * 
     * @param portalMember 会员管理
     * @return 会员管理集合
     */
    public List<PortalMember> selectPortalMemberList(PortalMember portalMember);

    /**
     * 新增会员管理
     * 
     * @param portalMember 会员管理
     * @return 结果
     */
    public int insertPortalMember(PortalMember portalMember);

    /**
     * 修改会员管理
     * 
     * @param portalMember 会员管理
     * @return 结果
     */
    public int updatePortalMember(PortalMember portalMember);

    /**
     * 删除会员管理
     * 
     * @param memberId 会员管理ID
     * @return 结果
     */
    public int deletePortalMemberById(Long memberId);

    /**
     * 批量删除会员管理
     * 
     * @param memberIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePortalMemberByIds(Long[] memberIds);
}
