package com.ruoyi.web.controller.portal;

import com.ruoyi.portal.domain.PortalCourse;
import com.ruoyi.portal.domain.PortalContent;
import com.ruoyi.portal.domain.PortalMagazine;
import com.ruoyi.portal.domain.PortalMedia;
import com.ruoyi.portal.domain.PortalActivity;
import com.ruoyi.portal.domain.PortalSchool;
import com.ruoyi.portal.domain.PortalTeacher;
import com.ruoyi.portal.service.IPortalCourseService;
import com.ruoyi.portal.service.IPortalContentService;
import com.ruoyi.portal.service.IPortalMagazineService;
import com.ruoyi.portal.service.IPortalMediaService;
import com.ruoyi.portal.service.IPortalActivityService;
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

    @Autowired
    private IPortalContentService portalContentService;

    @Autowired
    private IPortalMagazineService portalMagazineService;

    @Autowired
    private IPortalMediaService portalMediaService;

    @Autowired
    private IPortalActivityService portalActivityService;

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
    public String index(ModelMap mmap) {
        PortalCourse courseQuery = new PortalCourse();
        courseQuery.setStatus("0");
        List<PortalCourse> courses = portalCourseService.selectPortalCourseList(courseQuery);

        PortalActivity activityQuery = new PortalActivity();
        activityQuery.setStatus("0");
        List<PortalActivity> activities = portalActivityService.selectPortalActivityList(activityQuery);

        PortalContent contentQuery = new PortalContent();
        contentQuery.setStatus("0");
        contentQuery.setAuditStatus("1");
        List<PortalContent> articles = portalContentService.selectPortalContentList(contentQuery);

        mmap.put("hotCourses", limitList(courses, 4));
        mmap.put("miniCourses", limitList(courses, 4));
        mmap.put("upcomingActivities", limitList(activities, 5));
        mmap.put("popularArticles", limitList(articles, 5));
        return "portal/index";
    }

    /**
     * 杂志天地
     */
    @GetMapping("magazine")
    public String magazine(ModelMap mmap) {
        PortalMagazine magazineQuery = new PortalMagazine();
        magazineQuery.setStatus("0");
        List<PortalMagazine> magazines = portalMagazineService.selectPortalMagazineList(magazineQuery);

        PortalMedia mediaQuery = new PortalMedia();
        mediaQuery.setStatus("0");
        List<PortalMedia> mediaList = portalMediaService.selectPortalMediaList(mediaQuery);

        mmap.put("magazines", magazines);
        mmap.put("mediaList", limitList(mediaList, 8));
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
    public String news(ModelMap mmap) {
        PortalContent query = new PortalContent();
        query.setStatus("0");
        query.setAuditStatus("1");
        mmap.put("articles", portalContentService.selectPortalContentList(query));
        return "portal/news";
    }

    /**
     * 银龄动态详情
     */
    @GetMapping("news/{id}")
    public String newsDetail(@PathVariable("id") Long id, ModelMap mmap) {
        PortalContent article = portalContentService.selectPortalContentById(id);
        if (article == null || !"0".equals(article.getStatus())) {
            return "redirect:/portal/news";
        }
        PortalContent query = new PortalContent();
        query.setStatus("0");
        query.setAuditStatus("1");
        mmap.put("article", article);
        mmap.put("latestArticles", limitList(portalContentService.selectPortalContentList(query), 8));
        return "portal/news-detail";
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
    public String activity(ModelMap mmap) {
        PortalActivity query = new PortalActivity();
        query.setStatus("0");
        mmap.put("activities", portalActivityService.selectPortalActivityList(query));
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

    private <T> List<T> limitList(List<T> list, int limit) {
        if (list == null || list.isEmpty()) {
            return list;
        }
        if (list.size() <= limit) {
            return list;
        }
        return list.subList(0, limit);
    }
}
