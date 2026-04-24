package com.ruoyi.web.controller.portal;

import com.ruoyi.portal.domain.PortalMagazine;
import com.ruoyi.portal.domain.PortalUniversity;
import com.ruoyi.portal.domain.PortalMember;
import com.ruoyi.portal.domain.PortalMenu;
import com.ruoyi.portal.domain.PortalContent;
import com.ruoyi.portal.service.IPortalMagazineService;
import com.ruoyi.portal.service.IPortalUniversityService;
import com.ruoyi.portal.service.IPortalMemberService;
import com.ruoyi.portal.service.IPortalMenuService;
import com.ruoyi.portal.service.IPortalContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 门户网站后台管理控制器
 */
@Controller
@RequestMapping("/admin/portal")
public class PortalAdminController {

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

    /**
     * 杂志管理页面
     */
    @GetMapping("/magazine")
    public String magazine(ModelMap mmap) {
        // 查询杂志列表
        PortalMagazine portalMagazine = new PortalMagazine();
        List<PortalMagazine> magazines = portalMagazineService.selectPortalMagazineList(portalMagazine);
        mmap.put("magazines", magazines);
        return "admin/portal/magazine";
    }

    /**
     * 大学管理页面
     */
    @GetMapping("/university")
    public String university(ModelMap mmap) {
        // 查询大学列表
        PortalUniversity portalUniversity = new PortalUniversity();
        List<PortalUniversity> universities = portalUniversityService.selectPortalUniversityList(portalUniversity);
        mmap.put("universities", universities);
        return "admin/portal/university";
    }

    /**
     * 会员管理页面
     */
    @GetMapping("/member")
    public String member(ModelMap mmap) {
        // 查询会员列表
        PortalMember portalMember = new PortalMember();
        List<PortalMember> members = portalMemberService.selectPortalMemberList(portalMember);
        mmap.put("members", members);
        return "admin/portal/member";
    }

    /**
     * 菜单管理页面
     */
    @GetMapping("/menu")
    public String menu(ModelMap mmap) {
        // 查询菜单列表
        PortalMenu portalMenu = new PortalMenu();
        List<PortalMenu> menus = portalMenuService.selectPortalMenuList(portalMenu);
        mmap.put("menus", menus);
        return "admin/portal/menu";
    }

    /**
     * 菜单管理列表
     */
    @PostMapping("/menu/list")
    @ResponseBody
    public List<PortalMenu> menuList() {
        // 查询菜单树
        return portalMenuService.selectPortalMenuTree();
    }

    /**
     * 新增杂志
     */
    @PostMapping("/magazine/add")
    @ResponseBody
    public int addMagazine(PortalMagazine portalMagazine) {
        return portalMagazineService.insertPortalMagazine(portalMagazine);
    }

    /**
     * 修改杂志
     */
    @PostMapping("/magazine/edit")
    @ResponseBody
    public int editMagazine(PortalMagazine portalMagazine) {
        return portalMagazineService.updatePortalMagazine(portalMagazine);
    }

    /**
     * 删除杂志
     */
    @PostMapping("/magazine/delete")
    @ResponseBody
    public int deleteMagazine(Long magazineId) {
        return portalMagazineService.deletePortalMagazineById(magazineId);
    }

    /**
     * 新增大学
     */
    @PostMapping("/university/add")
    @ResponseBody
    public int addUniversity(PortalUniversity portalUniversity) {
        return portalUniversityService.insertPortalUniversity(portalUniversity);
    }

    /**
     * 修改大学
     */
    @PostMapping("/university/edit")
    @ResponseBody
    public int editUniversity(PortalUniversity portalUniversity) {
        return portalUniversityService.updatePortalUniversity(portalUniversity);
    }

    /**
     * 删除大学
     */
    @PostMapping("/university/delete")
    @ResponseBody
    public int deleteUniversity(Long universityId) {
        return portalUniversityService.deletePortalUniversityById(universityId);
    }

    /**
     * 新增会员
     */
    @PostMapping("/member/add")
    @ResponseBody
    public int addMember(PortalMember portalMember) {
        return portalMemberService.insertPortalMember(portalMember);
    }

    /**
     * 修改会员
     */
    @PostMapping("/member/edit")
    @ResponseBody
    public int editMember(PortalMember portalMember) {
        return portalMemberService.updatePortalMember(portalMember);
    }

    /**
     * 删除会员
     */
    @PostMapping("/member/delete")
    @ResponseBody
    public int deleteMember(Long memberId) {
        return portalMemberService.deletePortalMemberById(memberId);
    }

    /**
     * 新增菜单
     */
    @PostMapping("/menu/add")
    @ResponseBody
    public int addMenu(PortalMenu portalMenu) {
        return portalMenuService.insertPortalMenu(portalMenu);
    }

    /**
     * 修改菜单
     */
    @PostMapping("/menu/edit")
    @ResponseBody
    public int editMenu(PortalMenu portalMenu) {
        return portalMenuService.updatePortalMenu(portalMenu);
    }

    /**
     * 删除菜单
     */
    @PostMapping("/menu/delete")
    @ResponseBody
    public int deleteMenu(Long menuId) {
        return portalMenuService.deletePortalMenuById(menuId);
    }

    /**
     * 内容管理页面
     */
    @GetMapping("/content")
    public String content() {
        return "admin/portal/content";
    }

    /**
     * 内容管理列表
     */
    @PostMapping("/content/list")
    @ResponseBody
    public List<PortalContent> contentList(PortalContent portalContent) {
        return portalContentService.selectPortalContentList(portalContent);
    }

    /**
     * 新增内容
     */
    @GetMapping("/content/add")
    public String addContent() {
        return "admin/portal/content-add";
    }

    /**
     * 新增保存内容
     */
    @PostMapping("/content/add")
    @ResponseBody
    public int addContentSave(PortalContent portalContent) {
        return portalContentService.insertPortalContent(portalContent);
    }

    /**
     * 修改内容
     */
    @GetMapping("/content/edit/{contentId}")
    public String editContent(@PathVariable("contentId") Long contentId, ModelMap mmap) {
        mmap.put("content", portalContentService.selectPortalContentById(contentId));
        return "admin/portal/content-edit";
    }

    /**
     * 修改保存内容
     */
    @PostMapping("/content/edit")
    @ResponseBody
    public int editContentSave(PortalContent portalContent) {
        return portalContentService.updatePortalContent(portalContent);
    }

    /**
     * 删除内容
     */
    @PostMapping("/content/remove")
    @ResponseBody
    public int removeContent(Long[] contentIds) {
        int result = 0;
        if (contentIds != null) {
            for (Long contentId : contentIds) {
                result += portalContentService.deletePortalContentById(contentId);
            }
        }
        return result;
    }
}