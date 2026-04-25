package com.ruoyi.web.controller.portal;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.portal.domain.PortalMember;
import com.ruoyi.portal.service.IPortalMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

/**
 * 分离的前台会员登录注册控制器
 */
@Controller
@RequestMapping("/portal")
public class PortalUserController extends BaseController {

    private String prefix = "portal/user";

    @Autowired
    private IPortalMemberService portalMemberService;

    /**
     * 跳转前台登录页
     */
    @GetMapping("/login")
    public String login() {
        return prefix + "/login";
    }

    /**
     * 跳转前台注册页
     */
    @GetMapping("/register")
    public String register() {
        return prefix + "/register";
    }

    /**
     * 会员注册提交
     */
    @PostMapping("/register")
    @ResponseBody
    public AjaxResult registerSave(PortalMember member) {
        if (portalMemberService.selectPortalMemberByLoginName(member.getLoginName()) != null) {
            return error("账号已存在");
        }
        return toAjax(portalMemberService.registerMember(member));
    }

    /**
     * 会员登录
     */
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult loginSave(String loginName, String password, HttpSession session) {
        try {
            PortalMember member = portalMemberService.login(loginName, password);
            if (member != null) {
                // 存入独立的 Session Key，与后台区分开
                session.setAttribute("PORTAL_MEMBER", member);
                return success("登录成功");
            }
            return error("账号或密码错误");
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 会员退出
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("PORTAL_MEMBER");
        return "redirect:/portal/login";
    }
}
