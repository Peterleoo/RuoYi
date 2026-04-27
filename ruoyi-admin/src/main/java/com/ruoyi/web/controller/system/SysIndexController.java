package com.ruoyi.web.controller.system;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.CookieUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.portal.domain.PortalActivity;
import com.ruoyi.portal.domain.PortalContent;
import com.ruoyi.portal.domain.PortalCourse;
import com.ruoyi.portal.domain.PortalMedia;
import com.ruoyi.portal.domain.PortalMember;
import com.ruoyi.portal.domain.PortalSchool;
import com.ruoyi.portal.domain.PortalTeacher;
import com.ruoyi.portal.service.IPortalActivityService;
import com.ruoyi.portal.service.IPortalContentService;
import com.ruoyi.portal.service.IPortalCourseService;
import com.ruoyi.portal.service.IPortalMediaService;
import com.ruoyi.portal.service.IPortalMemberService;
import com.ruoyi.portal.service.IPortalSchoolService;
import com.ruoyi.portal.service.IPortalTeacherService;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysMenuService;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private IPortalSchoolService portalSchoolService;

    @Autowired
    private IPortalCourseService portalCourseService;

    @Autowired
    private IPortalTeacherService portalTeacherService;

    @Autowired
    private IPortalMemberService portalMemberService;

    @Autowired
    private IPortalContentService portalContentService;

    @Autowired
    private IPortalActivityService portalActivityService;

    @Autowired
    private IPortalMediaService portalMediaService;

    // 系统首页
    @GetMapping({ "/admin/index", "/index" })
    public String index(ModelMap mmap, HttpServletRequest request)
    {
        // 取身份信息
        SysUser user = getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        Boolean footer = Convert.toBool(configService.selectConfigByKey("sys.index.footer"), true);
        Boolean tagsView = Convert.toBool(configService.selectConfigByKey("sys.index.tagsView"), true);
        mmap.put("footer", footer);
        mmap.put("tagsView", tagsView);
        mmap.put("mainClass", contentMainClass(footer, tagsView));
        mmap.put("copyrightYear", RuoYiConfig.getCopyrightYear());
        mmap.put("demoEnabled", RuoYiConfig.isDemoEnabled());
        mmap.put("isDefaultModifyPwd", initPasswordIsModify(user.getPwdUpdateDate()));
        mmap.put("isPasswordExpired", passwordIsExpiration(user.getPwdUpdateDate()));
        mmap.put("isMobile", ServletUtils.checkAgentIsMobile(ServletUtils.getRequest().getHeader("User-Agent")));

        // 菜单导航显示风格
        String menuStyle = configService.selectConfigByKey("sys.index.menuStyle");
        // 移动端，默认使左侧导航菜单，否则取默认配置
        String indexStyle = ServletUtils.checkAgentIsMobile(ServletUtils.getRequest().getHeader("User-Agent")) ? "index" : menuStyle;

        // 优先Cookie配置导航菜单
        Cookie[] cookies = ServletUtils.getRequest().getCookies();
        for (Cookie cookie : cookies)
        {
            if (StringUtils.isNotEmpty(cookie.getName()) && "nav-style".equalsIgnoreCase(cookie.getName()))
            {
                indexStyle = cookie.getValue();
                break;
            }
        }
        String webIndex = "topnav".equalsIgnoreCase(indexStyle) ? "index-topnav" : "index";
        // CSRF Token
        request.getSession().setAttribute(ShiroConstants.CSRF_TOKEN, ServletUtils.generateToken());
        return webIndex;
    }

    // 锁定屏幕
    @GetMapping({ "/admin/lockscreen", "/lockscreen" })
    public String lockscreen(ModelMap mmap)
    {
        mmap.put("user", getSysUser());
        ServletUtils.getSession().setAttribute(ShiroConstants.LOCK_SCREEN, true);
        return "lock";
    }

    // 解锁屏幕
    @PostMapping({ "/admin/unlockscreen", "/unlockscreen" })
    @ResponseBody
    public AjaxResult unlockscreen(String password)
    {
        SysUser user = getSysUser();
        if (StringUtils.isNull(user))
        {
            return AjaxResult.error("服务器超时，请重新登录");
        }
        if (passwordService.matches(user, password))
        {
            ServletUtils.getSession().removeAttribute(ShiroConstants.LOCK_SCREEN);
            return AjaxResult.success();
        }
        return AjaxResult.error("密码不正确，请重新输入。");
    }

    // 切换主题
    @GetMapping({ "/admin/system/switchSkin", "/system/switchSkin" })
    public String switchSkin()
    {
        return "skin";
    }

    // 切换菜单
    @GetMapping({ "/admin/system/menuStyle/{style}", "/system/menuStyle/{style}" })
    public void menuStyle(@PathVariable String style, HttpServletResponse response)
    {
        CookieUtils.setCookie(response, "nav-style", style);
    }

    // 系统介绍
    @GetMapping({ "/admin/system/main", "/system/main" })
    public String main(ModelMap mmap)
    {
        List<PortalSchool> schools = safeList(portalSchoolService.selectPortalSchoolList(new PortalSchool()));
        List<PortalCourse> courses = safeList(portalCourseService.selectPortalCourseList(new PortalCourse()));
        List<PortalTeacher> teachers = safeList(portalTeacherService.selectPortalTeacherList(new PortalTeacher()));
        List<PortalMember> members = safeList(portalMemberService.selectPortalMemberList(new PortalMember()));
        List<PortalContent> contents = safeList(portalContentService.selectPortalContentList(new PortalContent()));
        List<PortalActivity> activities = safeList(portalActivityService.selectPortalActivityList(new PortalActivity()));
        List<PortalMedia> medias = safeList(portalMediaService.selectPortalMediaList(new PortalMedia()));

        mmap.put("schoolCount", schools.size());
        mmap.put("courseCount", courses.size());
        mmap.put("teacherCount", teachers.size());
        mmap.put("memberCount", members.size());
        mmap.put("contentCount", contents.size());
        mmap.put("activityCount", activities.size());
        mmap.put("mediaCount", medias.size());

        mmap.put("activeSchoolCount", countByStatus(schools.stream().map(PortalSchool::getStatus).toList(), "0"));
        mmap.put("activeCourseCount", countByStatus(courses.stream().map(PortalCourse::getStatus).toList(), "0"));
        mmap.put("activeTeacherCount", countByStatus(teachers.stream().map(PortalTeacher::getStatus).toList(), "0"));
        mmap.put("activeMemberCount", countByStatus(members.stream().map(PortalMember::getStatus).toList(), "0"));
        mmap.put("pendingAuditCount", countByStatus(contents.stream().map(PortalContent::getAuditStatus).toList(), "0"));
        mmap.put("onGoingActivityCount", countByStatus(activities.stream().map(PortalActivity::getActivityStatus).toList(), "1"));

        long videoMediaCount = medias.stream().filter(m -> "video".equalsIgnoreCase(StringUtils.nvl(m.getMediaType(), ""))).count();
        long audioMediaCount = medias.stream().filter(m -> "audio".equalsIgnoreCase(StringUtils.nvl(m.getMediaType(), ""))).count();
        mmap.put("videoMediaCount", videoMediaCount);
        mmap.put("audioMediaCount", audioMediaCount);

        List<PortalContent> latestContents = new ArrayList<>(contents);
        latestContents.sort(Comparator.comparing(PortalContent::getCreateTime, Comparator.nullsLast(Comparator.reverseOrder())));
        if (latestContents.size() > 6)
        {
            latestContents = latestContents.subList(0, 6);
        }

        List<PortalActivity> latestActivities = new ArrayList<>(activities);
        latestActivities.sort(Comparator.comparing(PortalActivity::getCreateTime, Comparator.nullsLast(Comparator.reverseOrder())));
        if (latestActivities.size() > 6)
        {
            latestActivities = latestActivities.subList(0, 6);
        }

        mmap.put("latestContents", latestContents);
        mmap.put("latestActivities", latestActivities);
        mmap.put("version", RuoYiConfig.getVersion());
        return "main_portal";
    }

    private <T> List<T> safeList(List<T> list)
    {
        return list == null ? new ArrayList<>() : list;
    }

    private long countByStatus(List<String> statusList, String target)
    {
        return statusList.stream().filter(s -> target.equals(StringUtils.nvl(s, ""))).count();
    }

    // content-main class
    public String contentMainClass(Boolean footer, Boolean tagsView)
    {
        if (!footer && !tagsView)
        {
            return "tagsview-footer-hide";
        }
        else if (!footer)
        {
            return "footer-hide";
        }
        else if (!tagsView)
        {
            return "tagsview-hide";
        }
        return StringUtils.EMPTY;
    }

    // 检查初始密码是否提醒修改
    public boolean initPasswordIsModify(Date pwdUpdateDate)
    {
        Integer initPasswordModify = Convert.toInt(configService.selectConfigByKey("sys.account.initPasswordModify"));
        return initPasswordModify != null && initPasswordModify == 1 && pwdUpdateDate == null;
    }

    // 检查密码是否过期
    public boolean passwordIsExpiration(Date pwdUpdateDate)
    {
        Integer passwordValidateDays = Convert.toInt(configService.selectConfigByKey("sys.account.passwordValidateDays"));
        if (passwordValidateDays != null && passwordValidateDays > 0)
        {
            if (StringUtils.isNull(pwdUpdateDate))
            {
                // 如果从未修改过初始密码，直接提醒过期
                return true;
            }
            Date nowDate = DateUtils.getNowDate();
            return DateUtils.differentDaysByMillisecond(nowDate, pwdUpdateDate) > passwordValidateDays;
        }
        return false;
    }
}
