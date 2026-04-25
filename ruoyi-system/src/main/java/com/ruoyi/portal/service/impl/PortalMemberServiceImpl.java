package com.ruoyi.portal.service.impl;

import com.ruoyi.portal.domain.PortalMember;
import com.ruoyi.portal.mapper.PortalMemberMapper;
import com.ruoyi.portal.service.IPortalMemberService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;

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

    @Override
    public PortalMember selectPortalMemberByLoginName(String loginName) {
        return portalMemberMapper.selectPortalMemberByLoginName(loginName);
    }

    /**
     * 注册会员
     */
    @Override
    public int registerMember(PortalMember member) {
        // 生成盐，限制在数据库字段长度范围内（默认3个字节即6个十六进制字符）
        String salt = new SecureRandomNumberGenerator().nextBytes(3).toHex();
        member.setSalt(salt);
        // 加密密码
        member.setPassword(encryptPassword(member.getLoginName(), member.getPassword(), salt));
        member.setCreateTime(new Date());
        member.setStatus("0"); // 默认正常
        member.setMemberType("0"); // 默认普通会员
        return portalMemberMapper.insertPortalMember(member);
    }

    /**
     * 登录校验
     */
    @Override
    public PortalMember login(String loginName, String password) {
        PortalMember member = portalMemberMapper.selectPortalMemberByLoginName(loginName);
        if (member == null) {
            return null;
        }
        if ("1".equals(member.getStatus())) {
            throw new RuntimeException("账号已被停用");
        }
        String entryPwd = encryptPassword(loginName, password, member.getSalt());
        if (member.getPassword().equals(entryPwd)) {
            return member;
        }
        return null;
    }

    /**
     * 加密方法
     */
    public String encryptPassword(String loginName, String password, String salt) {
        return DigestUtils.md5Hex(loginName + password + salt);
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
