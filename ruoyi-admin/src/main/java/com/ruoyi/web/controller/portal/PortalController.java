package com.ruoyi.web.controller.portal;

import com.ruoyi.portal.domain.PortalCourse;
import com.ruoyi.portal.domain.PortalSchool;
import com.ruoyi.portal.domain.PortalTeacher;
import com.ruoyi.portal.service.IPortalCourseService;
import com.ruoyi.portal.service.IPortalSchoolService;
import com.ruoyi.portal.service.IPortalTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * 门户网站控制器
 */
@Controller
@RequestMapping({"/", "/portal"})
public class PortalController {

    @Autowired
    private IPortalSchoolService portalSchoolService;

    @Autowired
    private IPortalTeacherService portalTeacherService;

    @Autowired
    private IPortalCourseService portalCourseService;

    /**
     * 根路径 - 直接跳转到官网首页
     */
    @GetMapping("")
    public String root() {
        return "portal/index";
    }

    /**
     * 首页
     */
    @GetMapping("index")
    public String index() {
        return "portal/index";
    }

    /**
     * 杂志天地
     */
    @GetMapping("magazine")
    public String magazine() {
        return "portal/magazine";
    }

    /**
     * 大学联盟
     */
    @GetMapping("university")
    public String university(ModelMap mmap) {
        PortalSchool query = new PortalSchool();
        query.setStatus("0");
        mmap.put("schools", portalSchoolService.selectPortalSchoolList(query));
        return "portal/university";
    }

    /**
     * 大学详情
     */
    @GetMapping("university/detail/{id}")
    public String universityDetail(@PathVariable("id") Long id, ModelMap mmap) {
        PortalSchool school = portalSchoolService.selectPortalSchoolById(id);
        List<PortalTeacher> teachers = portalTeacherService.selectPortalTeacherBySchoolId(id);
        List<PortalCourse> courses = portalCourseService.selectPortalCourseBySchoolId(id);
        mmap.put("school", school);
        mmap.put("teachers", teachers);
        mmap.put("courses", courses);
        return "portal/university-detail";
    }

    /**
     * 会员中心
     */
    @GetMapping("member")
    public String member(HttpSession session) {
        // 仅校验前台会员会话，不依赖后台管理员登录态
        if (session.getAttribute("PORTAL_MEMBER") == null) {
            return "redirect:/portal/login";
        }
        return "portal/member";
    }

    /**
     * 银龄动态
     */
    @GetMapping("news")
    public String news() {
        return "portal/news";
    }

    /**
     * 老干部大学协会
     */
    @GetMapping("association")
    public String association() {
        return "portal/association";
    }

    /**
     * 联系我们
     */
    @GetMapping("contact")
    public String contact() {
        return "portal/contact";
    }

    /**
     * 下载中心
     */
    @GetMapping("download")
    public String download() {
        return "portal/download";
    }

    /**
     * 在线课程
     */
    @GetMapping("course")
    public String course(ModelMap mmap) {
        PortalCourse query = new PortalCourse();
        query.setStatus("0");
        mmap.put("courses", portalCourseService.selectPortalCourseList(query));
        return "portal/course";
    }

    /**
     * 活动报名
     */
    @GetMapping("activity")
    public String activity() {
        return "portal/activity";
    }

    /**
     * 隐私政策
     */
    @GetMapping("privacy")
    public String privacy() {
        return "portal/privacy";
    }

    /**
     * 使用协议
     */
    @GetMapping("terms")
    public String terms() {
        return "portal/terms";
    }
}
