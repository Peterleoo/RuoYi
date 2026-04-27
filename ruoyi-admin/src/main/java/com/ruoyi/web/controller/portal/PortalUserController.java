package com.ruoyi.web.controller.portal;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.portal.domain.PortalCourse;
import com.ruoyi.portal.domain.PortalCourseLearningProgress;
import com.ruoyi.portal.domain.PortalMagazine;
import com.ruoyi.portal.domain.PortalMagazineSubscription;
import com.ruoyi.portal.domain.PortalMember;
import com.ruoyi.portal.service.IPortalCourseLearningProgressService;
import com.ruoyi.portal.service.IPortalCourseService;
import com.ruoyi.portal.service.IPortalMagazineService;
import com.ruoyi.portal.service.IPortalMagazineSubscriptionService;
import com.ruoyi.portal.service.IPortalMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * 分离的前台会员登录注册控制器
 */
@Controller
@RequestMapping("/portal")
public class PortalUserController extends BaseController {

    private String prefix = "portal/user";

    @Autowired
    private IPortalMemberService portalMemberService;

    @Autowired
    private IPortalMagazineService portalMagazineService;

    @Autowired
    private IPortalCourseService portalCourseService;

    @Autowired
    private IPortalMagazineSubscriptionService portalMagazineSubscriptionService;

    @Autowired
    private IPortalCourseLearningProgressService portalCourseLearningProgressService;

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

    /**
     * 查询当前会员的杂志订阅列表
     */
    @GetMapping("/member/subscriptions")
    @ResponseBody
    public AjaxResult memberSubscriptions(HttpSession session) {
        PortalMember member = currentMember(session);
        if (member == null) {
            return error("请先登录会员账号");
        }
        List<PortalMagazineSubscription> list = portalMagazineSubscriptionService.selectByMemberId(member.getMemberId());
        AjaxResult ajax = success();
        ajax.put("data", list);
        return ajax;
    }

    /**
     * 订阅杂志
     */
    @PostMapping("/member/magazine/subscribe")
    @ResponseBody
    public AjaxResult subscribeMagazine(Long magazineId, HttpSession session) {
        PortalMember member = currentMember(session);
        if (member == null) {
            return error("请先登录会员账号");
        }
        if (magazineId == null) {
            return error("缺少杂志ID");
        }
        PortalMagazine magazine = portalMagazineService.selectPortalMagazineById(magazineId);
        if (magazine == null || !"0".equals(magazine.getStatus())) {
            return error("杂志不存在或已下线");
        }
        boolean ok = portalMagazineSubscriptionService.subscribe(member.getMemberId(), member.getLoginName(), magazineId);
        return ok ? success("订阅成功") : error("订阅失败");
    }

    /**
     * 取消订阅杂志
     */
    @PostMapping("/member/magazine/unsubscribe")
    @ResponseBody
    public AjaxResult unsubscribeMagazine(Long magazineId, HttpSession session) {
        PortalMember member = currentMember(session);
        if (member == null) {
            return error("请先登录会员账号");
        }
        if (magazineId == null) {
            return error("缺少杂志ID");
        }
        boolean ok = portalMagazineSubscriptionService.unsubscribe(member.getMemberId(), member.getLoginName(), magazineId);
        return ok ? success("已取消订阅") : error("取消订阅失败");
    }

    /**
     * 查询当前会员课程学习进度
     */
    @GetMapping("/member/course/progress")
    @ResponseBody
    public AjaxResult memberCourseProgress(HttpSession session) {
        PortalMember member = currentMember(session);
        if (member == null) {
            return error("请先登录会员账号");
        }
        List<PortalCourseLearningProgress> list = portalCourseLearningProgressService.selectByMemberId(member.getMemberId());
        AjaxResult ajax = success();
        ajax.put("data", list);
        return ajax;
    }

    /**
     * 保存课程学习进度
     */
    @PostMapping("/member/course/progress")
    @ResponseBody
    public AjaxResult saveCourseProgress(Long courseId, Integer progressPercent, Integer studyMinutes, String progressStatus, HttpSession session) {
        PortalMember member = currentMember(session);
        if (member == null) {
            return error("请先登录会员账号");
        }
        if (courseId == null) {
            return error("缺少课程ID");
        }
        PortalCourse course = portalCourseService.selectPortalCourseById(courseId);
        if (course == null || !"0".equals(course.getStatus())) {
            return error("课程不存在或已下线");
        }
        boolean ok = portalCourseLearningProgressService.saveOrUpdateProgress(
                member.getMemberId(),
                member.getLoginName(),
                courseId,
                progressPercent,
                studyMinutes,
                progressStatus
        );
        return ok ? success("进度已保存") : error("保存失败");
    }

    private PortalMember currentMember(HttpSession session) {
        Object member = session.getAttribute("PORTAL_MEMBER");
        if (member instanceof PortalMember) {
            return (PortalMember) member;
        }
        return null;
    }
}
