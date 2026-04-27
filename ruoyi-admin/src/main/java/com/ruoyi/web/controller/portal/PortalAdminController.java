package com.ruoyi.web.controller.portal;

import com.ruoyi.portal.domain.PortalMagazine;
import com.ruoyi.portal.domain.PortalUniversity;
import com.ruoyi.portal.domain.PortalMember;
import com.ruoyi.portal.domain.PortalMenu;
import com.ruoyi.portal.domain.PortalContent;
import com.ruoyi.portal.domain.PortalContentCategory;
import com.ruoyi.portal.domain.PortalSchool;
import com.ruoyi.portal.domain.PortalTeacher;
import com.ruoyi.portal.domain.PortalCourse;
import com.ruoyi.portal.domain.PortalMedia;
import com.ruoyi.portal.domain.PortalActivity;
import com.ruoyi.portal.service.IPortalMagazineService;
import com.ruoyi.portal.service.IPortalUniversityService;
import com.ruoyi.portal.service.IPortalMemberService;
import com.ruoyi.portal.service.IPortalMenuService;
import com.ruoyi.portal.service.IPortalContentService;
import com.ruoyi.portal.service.IPortalContentCategoryService;
import com.ruoyi.portal.service.IPortalSchoolService;
import com.ruoyi.portal.service.IPortalTeacherService;
import com.ruoyi.portal.service.IPortalCourseService;
import com.ruoyi.portal.service.IPortalMediaService;
import com.ruoyi.portal.service.IPortalActivityService;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.system.service.ISysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 门户网站后台管理控制器
 */
@Controller
@RequestMapping("/admin/portal")
public class PortalAdminController extends BaseController {

    @Autowired
    private IPortalMagazineService portalMagazineService;

    @Autowired
    private IPortalUniversityService portalUniversityService;

    @Autowired
    private IPortalMemberService portalMemberService;

    @Autowired
    private IPortalMenuService portalMenuService;

    @Autowired
    private IPortalContentService portalContentService;

    @Autowired
    private IPortalContentCategoryService portalContentCategoryService;

    @Autowired
    private IPortalSchoolService portalSchoolService;

    @Autowired
    private IPortalTeacherService portalTeacherService;

    @Autowired
    private IPortalCourseService portalCourseService;

    @Autowired
    private IPortalMediaService portalMediaService;

    @Autowired
    private IPortalActivityService portalActivityService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 杂志管理页面
     */
    @RequiresPermissions("portal:magazine:view")
    @GetMapping("/magazine")
    public String magazine() {
        return "admin/portal/magazine";
    }

    /**
     * 杂志管理列表
     */
    @RequiresPermissions("portal:magazine:list")
    @PostMapping("/magazine/list")
    @ResponseBody
    public TableDataInfo magazineList(PortalMagazine portalMagazine) {
        startPage();
        List<PortalMagazine> list = portalMagazineService.selectPortalMagazineList(portalMagazine);
        return getDataTable(list);
    }

    /**
     * 新增杂志页面
     */
    @GetMapping("/magazine/add")
    public String addMagazine() {
        return "admin/portal/magazine-add";
    }

    /**
     * 新增保存杂志
     */
    @RequiresPermissions("portal:magazine:add")
    @PostMapping("/magazine/add")
    @ResponseBody
    public AjaxResult addMagazineSave(PortalMagazine portalMagazine) {
        return toAjax(portalMagazineService.insertPortalMagazine(portalMagazine));
    }

    /**
     * 编辑杂志页面
     */
    @GetMapping("/magazine/edit/{magazineId}")
    public String editMagazine(@PathVariable("magazineId") Long magazineId, ModelMap mmap) {
        mmap.put("magazine", portalMagazineService.selectPortalMagazineById(magazineId));
        return "admin/portal/magazine-edit";
    }

    /**
     * 修改保存杂志
     */
    @RequiresPermissions("portal:magazine:edit")
    @PostMapping("/magazine/edit")
    @ResponseBody
    public AjaxResult editMagazineSave(PortalMagazine portalMagazine) {
        return toAjax(portalMagazineService.updatePortalMagazine(portalMagazine));
    }

    /**
     * 删除杂志
     */
    @RequiresPermissions("portal:magazine:remove")
    @PostMapping("/magazine/remove")
    @ResponseBody
    public AjaxResult removeMagazine(Long[] magazineIds) {
        return toAjax(portalMagazineService.deletePortalMagazineByIds(magazineIds));
    }

    /**
     * 内容管理页面
     */
    @RequiresPermissions("portal:content:view")
    @GetMapping("/content")
    public String content(ModelMap mmap) {
        mmap.put("schools", portalSchoolService.selectPortalSchoolList(new PortalSchool()));
        PortalContentCategory categoryQuery = new PortalContentCategory();
        categoryQuery.setStatus("0");
        mmap.put("categories", portalContentCategoryService.selectPortalContentCategoryList(categoryQuery));
        return "admin/portal/content";
    }

    /**
     * 内容管理列表
     */
    @RequiresPermissions("portal:content:list")
    @PostMapping("/content/list")
    @ResponseBody
    public TableDataInfo contentList(PortalContent portalContent) {
        startPage();
        List<PortalContent> list = portalContentService.selectPortalContentList(portalContent);
        return getDataTable(list);
    }

    /**
     * 新增内容页面
     */
    @GetMapping("/content/add")
    public String addContent(ModelMap mmap) {
        mmap.put("schools", portalSchoolService.selectPortalSchoolList(new PortalSchool()));
        PortalContentCategory categoryQuery = new PortalContentCategory();
        categoryQuery.setStatus("0");
        mmap.put("categories", portalContentCategoryService.selectPortalContentCategoryList(categoryQuery));
        return "admin/portal/content-add";
    }

    /**
     * 新增保存内容
     */
    @RequiresPermissions("portal:content:add")
    @PostMapping("/content/add")
    @ResponseBody
    public AjaxResult addContentSave(PortalContent portalContent) {
        return toAjax(portalContentService.insertPortalContent(portalContent));
    }

    /**
     * 编辑内容页面
     */
    @GetMapping("/content/edit/{contentId}")
    public String editContent(@PathVariable("contentId") Long contentId, ModelMap mmap) {
        mmap.put("content", portalContentService.selectPortalContentById(contentId));
        mmap.put("schools", portalSchoolService.selectPortalSchoolList(new PortalSchool()));
        PortalContentCategory categoryQuery = new PortalContentCategory();
        categoryQuery.setStatus("0");
        mmap.put("categories", portalContentCategoryService.selectPortalContentCategoryList(categoryQuery));
        return "admin/portal/content-edit";
    }

    /**
     * 内容分类管理页面
     */
    @RequiresPermissions("portal:contentCategory:view")
    @GetMapping("/content/category")
    public String contentCategory() {
        return "admin/portal/content-category";
    }

    /**
     * 内容分类列表
     */
    @RequiresPermissions("portal:contentCategory:list")
    @PostMapping("/content/category/list")
    @ResponseBody
    public TableDataInfo contentCategoryList(PortalContentCategory category) {
        startPage();
        List<PortalContentCategory> list = portalContentCategoryService.selectPortalContentCategoryList(category);
        return getDataTable(list);
    }

    /**
     * 新增内容分类页面
     */
    @GetMapping("/content/category/add")
    public String addContentCategory() {
        return "admin/portal/content-category-add";
    }

    /**
     * 新增保存内容分类
     */
    @RequiresPermissions("portal:contentCategory:add")
    @PostMapping("/content/category/add")
    @ResponseBody
    public AjaxResult addContentCategorySave(PortalContentCategory category) {
        return toAjax(portalContentCategoryService.insertPortalContentCategory(category));
    }

    /**
     * 编辑内容分类页面
     */
    @GetMapping("/content/category/edit/{categoryId}")
    public String editContentCategory(@PathVariable("categoryId") Long categoryId, ModelMap mmap) {
        mmap.put("category", portalContentCategoryService.selectPortalContentCategoryById(categoryId));
        return "admin/portal/content-category-edit";
    }

    /**
     * 修改保存内容分类
     */
    @RequiresPermissions("portal:contentCategory:edit")
    @PostMapping("/content/category/edit")
    @ResponseBody
    public AjaxResult editContentCategorySave(PortalContentCategory category) {
        return toAjax(portalContentCategoryService.updatePortalContentCategory(category));
    }

    /**
     * 删除内容分类
     */
    @RequiresPermissions("portal:contentCategory:remove")
    @PostMapping("/content/category/remove")
    @ResponseBody
    public AjaxResult removeContentCategory(Long[] categoryIds) {
        return toAjax(portalContentCategoryService.deletePortalContentCategoryByIds(categoryIds));
    }

    /**
     * 修改保存内容
     */
    @RequiresPermissions("portal:content:edit")
    @PostMapping("/content/edit")
    @ResponseBody
    public AjaxResult editContentSave(PortalContent portalContent) {
        return toAjax(portalContentService.updatePortalContent(portalContent));
    }

    /**
     * 删除内容
     */
    @RequiresPermissions("portal:content:remove")
    @PostMapping("/content/remove")
    @ResponseBody
    public AjaxResult removeContent(@RequestParam("ids") Long[] ids) {
        int rows = 0;
        if (ids != null) {
            for (Long contentId : ids) {
                rows += portalContentService.deletePortalContentById(contentId);
            }
        }
        return toAjax(rows);
    }

    /**
     * 会员管理页面
     */
    @RequiresPermissions("portal:member:view")
    @GetMapping("/member")
    public String member() {
        return "admin/portal/member";
    }

    /**
     * 会员管理列表
     */
    @RequiresPermissions("portal:member:list")
    @PostMapping("/member/list")
    @ResponseBody
    public TableDataInfo memberList(PortalMember portalMember) {
        startPage();
        List<PortalMember> list = portalMemberService.selectPortalMemberList(portalMember);
        return getDataTable(list);
    }

    /**
     * 大学管理页面
     */
    @RequiresPermissions("portal:university:view")
    @GetMapping("/university")
    public String university() {
        return "admin/portal/university";
    }

    /**
     * 大学管理列表
     */
    @RequiresPermissions("portal:university:list")
    @PostMapping("/university/list")
    @ResponseBody
    public TableDataInfo universityList(PortalUniversity portalUniversity) {
        startPage();
        List<PortalUniversity> list = portalUniversityService.selectPortalUniversityList(portalUniversity);
        return getDataTable(list);
    }

    /**
     * 学校管理页面
     */
    @RequiresPermissions("portal:school:view")
    @GetMapping("/school")
    public String school() {
        return "admin/portal/school";
    }

    /**
     * 学校管理列表
     */
    @RequiresPermissions("portal:school:list")
    @PostMapping("/school/list")
    @ResponseBody
    public TableDataInfo schoolList(PortalSchool portalSchool) {
        startPage();
        List<PortalSchool> list = portalSchoolService.selectPortalSchoolList(portalSchool);
        return getDataTable(list);
    }

    /**
     * 新增学校页面
     */
    @GetMapping("/school/add")
    public String addSchool(ModelMap mmap) {
        mmap.put("deptList", deptService.selectDeptList(new SysDept()));
        return "admin/portal/school-add";
    }

    /**
     * 新增保存学校
     */
    @RequiresPermissions("portal:school:add")
    @PostMapping("/school/add")
    @ResponseBody
    public AjaxResult addSchoolSave(PortalSchool portalSchool) {
        return toAjax(portalSchoolService.insertPortalSchool(portalSchool));
    }

    /**
     * 编辑学校页面
     */
    @GetMapping("/school/edit/{schoolId}")
    public String editSchool(@PathVariable("schoolId") Long schoolId, ModelMap mmap) {
        PortalSchool school = portalSchoolService.selectPortalSchoolById(schoolId);
        mmap.put("school", school);
        mmap.put("deptList", deptService.selectDeptList(new SysDept()));
        return "admin/portal/school-edit";
    }

    /**
     * 修改保存学校
     */
    @RequiresPermissions("portal:school:edit")
    @PostMapping("/school/edit")
    @ResponseBody
    public AjaxResult editSchoolSave(PortalSchool portalSchool) {
        return toAjax(portalSchoolService.updatePortalSchool(portalSchool));
    }

    /**
     * 删除学校
     */
    @RequiresPermissions("portal:school:remove")
    @PostMapping("/school/remove")
    @ResponseBody
    public AjaxResult removeSchool(Long[] schoolIds) {
        return toAjax(portalSchoolService.deletePortalSchoolByIds(schoolIds));
    }

    /**
     * 教师管理页面
     */
    @RequiresPermissions("portal:teacher:view")
    @GetMapping("/teacher")
    public String teacher(ModelMap mmap) {
        mmap.put("schools", portalSchoolService.selectPortalSchoolList(new PortalSchool()));
        return "admin/portal/teacher";
    }

    /**
     * 教师管理列表
     */
    @RequiresPermissions("portal:teacher:list")
    @PostMapping("/teacher/list")
    @ResponseBody
    public TableDataInfo teacherList(PortalTeacher portalTeacher) {
        startPage();
        List<PortalTeacher> list = portalTeacherService.selectPortalTeacherList(portalTeacher);
        return getDataTable(list);
    }

    /**
     * 新增教师页面
     */
    @GetMapping("/teacher/add")
    public String addTeacher(ModelMap mmap) {
        mmap.put("schools", portalSchoolService.selectPortalSchoolList(new PortalSchool()));
        return "admin/portal/teacher-add";
    }

    /**
     * 新增保存教师
     */
    @RequiresPermissions("portal:teacher:add")
    @PostMapping("/teacher/add")
    @ResponseBody
    public AjaxResult addTeacherSave(PortalTeacher portalTeacher) {
        return toAjax(portalTeacherService.insertPortalTeacher(portalTeacher));
    }

    /**
     * 编辑教师页面
     */
    @GetMapping("/teacher/edit/{teacherId}")
    public String editTeacher(@PathVariable("teacherId") Long teacherId, ModelMap mmap) {
        mmap.put("teacher", portalTeacherService.selectPortalTeacherById(teacherId));
        mmap.put("schools", portalSchoolService.selectPortalSchoolList(new PortalSchool()));
        return "admin/portal/teacher-edit";
    }

    /**
     * 修改保存教师
     */
    @RequiresPermissions("portal:teacher:edit")
    @PostMapping("/teacher/edit")
    @ResponseBody
    public AjaxResult editTeacherSave(PortalTeacher portalTeacher) {
        return toAjax(portalTeacherService.updatePortalTeacher(portalTeacher));
    }

    /**
     * 删除教师
     */
    @RequiresPermissions("portal:teacher:remove")
    @PostMapping("/teacher/remove")
    @ResponseBody
    public AjaxResult removeTeacher(Long[] teacherIds) {
        return toAjax(portalTeacherService.deletePortalTeacherByIds(teacherIds));
    }

    /**
     * 课程管理页面
     */
    @RequiresPermissions("portal:course:view")
    @GetMapping("/course")
    public String courseManage(ModelMap mmap) {
        mmap.put("schools", portalSchoolService.selectPortalSchoolList(new PortalSchool()));
        mmap.put("teachers", portalTeacherService.selectPortalTeacherList(new PortalTeacher()));
        return "admin/portal/course";
    }

    /**
     * 课程管理列表
     */
    @RequiresPermissions("portal:course:list")
    @PostMapping("/course/list")
    @ResponseBody
    public TableDataInfo courseList(PortalCourse portalCourse) {
        startPage();
        List<PortalCourse> list = portalCourseService.selectPortalCourseList(portalCourse);
        return getDataTable(list);
    }

    /**
     * 新增课程页面
     */
    @GetMapping("/course/add")
    public String addCourse(ModelMap mmap) {
        mmap.put("schools", portalSchoolService.selectPortalSchoolList(new PortalSchool()));
        mmap.put("teachers", portalTeacherService.selectPortalTeacherList(new PortalTeacher()));
        return "admin/portal/course-add";
    }

    /**
     * 新增保存课程
     */
    @RequiresPermissions("portal:course:add")
    @PostMapping("/course/add")
    @ResponseBody
    public AjaxResult addCourseSave(PortalCourse portalCourse) {
        return toAjax(portalCourseService.insertPortalCourse(portalCourse));
    }

    /**
     * 编辑课程页面
     */
    @GetMapping("/course/edit/{courseId}")
    public String editCourse(@PathVariable("courseId") Long courseId, ModelMap mmap) {
        mmap.put("course", portalCourseService.selectPortalCourseById(courseId));
        mmap.put("schools", portalSchoolService.selectPortalSchoolList(new PortalSchool()));
        mmap.put("teachers", portalTeacherService.selectPortalTeacherList(new PortalTeacher()));
        return "admin/portal/course-edit";
    }

    /**
     * 修改保存课程
     */
    @RequiresPermissions("portal:course:edit")
    @PostMapping("/course/edit")
    @ResponseBody
    public AjaxResult editCourseSave(PortalCourse portalCourse) {
        return toAjax(portalCourseService.updatePortalCourse(portalCourse));
    }

    /**
     * 删除课程
     */
    @RequiresPermissions("portal:course:remove")
    @PostMapping("/course/remove")
    @ResponseBody
    public AjaxResult removeCourse(Long[] courseIds) {
        return toAjax(portalCourseService.deletePortalCourseByIds(courseIds));
    }

    /**
     * 多媒体管理页面
     */
    @RequiresPermissions("portal:media:view")
    @GetMapping("/media")
    public String media(ModelMap mmap) {
        mmap.put("articles", portalContentService.selectPortalContentList(new PortalContent()));
        mmap.put("courses", portalCourseService.selectPortalCourseList(new PortalCourse()));
        return "admin/portal/media";
    }

    /**
     * 多媒体管理列表
     */
    @RequiresPermissions("portal:media:list")
    @PostMapping("/media/list")
    @ResponseBody
    public TableDataInfo mediaList(PortalMedia portalMedia) {
        startPage();
        List<PortalMedia> list = portalMediaService.selectPortalMediaList(portalMedia);
        return getDataTable(list);
    }

    /**
     * 新增多媒体页面
     */
    @GetMapping("/media/add")
    public String addMedia(ModelMap mmap) {
        mmap.put("articles", portalContentService.selectPortalContentList(new PortalContent()));
        mmap.put("courses", portalCourseService.selectPortalCourseList(new PortalCourse()));
        return "admin/portal/media-add";
    }

    /**
     * 新增保存多媒体
     */
    @RequiresPermissions("portal:media:add")
    @PostMapping("/media/add")
    @ResponseBody
    public AjaxResult addMediaSave(PortalMedia portalMedia) {
        return toAjax(portalMediaService.insertPortalMedia(portalMedia));
    }

    /**
     * 编辑多媒体页面
     */
    @GetMapping("/media/edit/{mediaId}")
    public String editMedia(@PathVariable("mediaId") Long mediaId, ModelMap mmap) {
        mmap.put("media", portalMediaService.selectPortalMediaById(mediaId));
        mmap.put("articles", portalContentService.selectPortalContentList(new PortalContent()));
        mmap.put("courses", portalCourseService.selectPortalCourseList(new PortalCourse()));
        return "admin/portal/media-edit";
    }

    /**
     * 修改保存多媒体
     */
    @RequiresPermissions("portal:media:edit")
    @PostMapping("/media/edit")
    @ResponseBody
    public AjaxResult editMediaSave(PortalMedia portalMedia) {
        return toAjax(portalMediaService.updatePortalMedia(portalMedia));
    }

    /**
     * 删除多媒体
     */
    @RequiresPermissions("portal:media:remove")
    @PostMapping("/media/remove")
    @ResponseBody
    public AjaxResult removeMedia(Long[] mediaIds) {
        return toAjax(portalMediaService.deletePortalMediaByIds(mediaIds));
    }

    /**
     * 活动管理页面
     */
    @RequiresPermissions("portal:activity:view")
    @GetMapping("/activity")
    public String activity(ModelMap mmap) {
        mmap.put("schools", portalSchoolService.selectPortalSchoolList(new PortalSchool()));
        return "admin/portal/activity";
    }

    /**
     * 活动管理列表
     */
    @RequiresPermissions("portal:activity:list")
    @PostMapping("/activity/list")
    @ResponseBody
    public TableDataInfo activityList(PortalActivity portalActivity) {
        startPage();
        List<PortalActivity> list = portalActivityService.selectPortalActivityList(portalActivity);
        return getDataTable(list);
    }

    /**
     * 新增活动页面
     */
    @GetMapping("/activity/add")
    public String addActivity(ModelMap mmap) {
        mmap.put("schools", portalSchoolService.selectPortalSchoolList(new PortalSchool()));
        return "admin/portal/activity-add";
    }

    /**
     * 新增保存活动
     */
    @RequiresPermissions("portal:activity:add")
    @PostMapping("/activity/add")
    @ResponseBody
    public AjaxResult addActivitySave(PortalActivity portalActivity) {
        return toAjax(portalActivityService.insertPortalActivity(portalActivity));
    }

    /**
     * 编辑活动页面
     */
    @GetMapping("/activity/edit/{activityId}")
    public String editActivity(@PathVariable("activityId") Long activityId, ModelMap mmap) {
        mmap.put("activity", portalActivityService.selectPortalActivityById(activityId));
        mmap.put("schools", portalSchoolService.selectPortalSchoolList(new PortalSchool()));
        return "admin/portal/activity-edit";
    }

    /**
     * 修改保存活动
     */
    @RequiresPermissions("portal:activity:edit")
    @PostMapping("/activity/edit")
    @ResponseBody
    public AjaxResult editActivitySave(PortalActivity portalActivity) {
        return toAjax(portalActivityService.updatePortalActivity(portalActivity));
    }

    /**
     * 删除活动
     */
    @RequiresPermissions("portal:activity:remove")
    @PostMapping("/activity/remove")
    @ResponseBody
    public AjaxResult removeActivity(Long[] activityIds) {
        return toAjax(portalActivityService.deletePortalActivityByIds(activityIds));
    }

    /**
     * 菜单管理页面
     */
    @RequiresPermissions("portal:menu:view")
    @GetMapping("/menu")
    public String menu() {
        return "admin/portal/menu";
    }

    /**
     * 菜单管理列表
     */
    @RequiresPermissions("portal:menu:list")
    @PostMapping("/menu/list")
    @ResponseBody
    public List<PortalMenu> menuList(PortalMenu portalMenu) {
        List<PortalMenu> list = portalMenuService.selectPortalMenuList(portalMenu);
        return list;
    }

    /**
     * 文件上传
     */
    @PostMapping("/content/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file, String type) {
        try {
            String fileName = file.getOriginalFilename();
            String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
            
            if ("image".equals(type)) {
                if (!StringUtils.inStringIgnoreCase(extension, MimeTypeUtils.IMAGE_EXTENSION)) {
                    return AjaxResult.error("只支持图片文件上传");
                }
            } else if ("media".equals(type)) {
                if (!StringUtils.inStringIgnoreCase(extension, MimeTypeUtils.MEDIA_EXTENSION)
                        && !StringUtils.inStringIgnoreCase(extension, MimeTypeUtils.VIDEO_EXTENSION)
                        && !extension.equals("pdf")) {
                    return AjaxResult.error("只支持视频、音频和PDF文件上传");
                }
            }
            String filePath = RuoYiConfig.getUploadPath();
            String uploadFileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + uploadFileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", uploadFileName);
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }
}
